/*
PRIORITIZATION ORDER!
The list of locations is first filtered by distance
Then filtered by date
Then filtered by weather (temperature & rain) 
Then this filtered list is added to our campsite list.
*/

/*  
*   MVC Implementation: MODEL : for Happy Camper
*   Interprets the actions received by the view, and asks the model to change state.
*   Implements 2 listeners: ActionListener and WindowListener.
*/

package happyCamper;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.awt.EventQueue;

/*------------------------------------------------------------------------------------
 * PersonalizedResult Description:
 * [MVC Implementation: MODEL] for Happy Camper
 * This is Happy Camper's primary workhorse class.
 * Here, all input values and file data are compared, sorted, and filtered.
 * 
 * NOTE: some campsite names are associated with multiple zip codes BECAUSE
 * some longitude & latitude values needed to be given random zip codes in weather data
 * for prototype demonstration.
 * This is not a code-related error, it's a file-contents related error.
 *------------------------------------------------------------------------------------*/

//this extends Weather class for access to methods like forecastDateToIndex()
public class PersonalizedResult extends Weather 
{
	/*_________DATA & COLLECTIONS_________*/
	//User-Input
	public CamperPreferences userPreferences;
	//Data Access
	public Weather weatherData;
	public Campsite campsiteData;
	public ZipCodeDatabase zipCodeMatchDatabase;
	//Filtering Systems
	//note: filtering done in stages so future features can access a user's search parameters individually. 
	public List<GPS> locationsByRange;			//sub-list of locations specified in weatherData
	public List<GPS> locationsByWeather;		//sub-list of locationsByRange based on weatherData
	public List<GPS> locationsWithPerfectMatch;	//sub-list of locationsByWeather based on weatherData
	
	//Final Results
	//note: GPS matches between Campsites & Weather only match by zipcode. GPS keys are not identical.
	public Map<GPS, String> filteredCampsites;
	Map<GPS, WeatherConditions> conditionsByFilteredGPS;

		
	/*_________MAIN_________*/
	//NOTE: Main function exists for testing purposes ONLY. Not for program implementation.
	public static void main(String[] args) throws HappyCamperException
	{
		/*RUN THIS SET TO SEE TEST PARAMETERS*/
//		PersonalizedResult user1 = new PersonalizedResult("11209", "06/05/20", 90, 150, false, 50, 90);
//		System.out.println("\nUser 1 Results:");		
//		user1.combinedDisplayForUser();
	}
	
	/*_________CONSTRUCTORS_________*/
	public PersonalizedResult() throws HappyCamperException
	{
		//Instantiate Members
		weatherData = new Weather();
		campsiteData = new Campsite();
		userPreferences = new CamperPreferences();
		
		locationsByRange = new ArrayList<GPS>();
		locationsByWeather = new ArrayList<GPS>();
		locationsWithPerfectMatch = new ArrayList<GPS>();
		
		filteredCampsites = new HashMap<GPS, String>();
		conditionsByFilteredGPS = new HashMap<GPS, WeatherConditions>();
		zipCodeMatchDatabase = new ZipCodeDatabase();	
	}
	
	//Constructor with parameters for forced test data.
	public PersonalizedResult(String zip, String date, int minDist, int maxDist, boolean rain, int lowT, int highT) throws HappyCamperException
	{
		//Instantiate Members
		weatherData = new Weather();
		campsiteData = new Campsite();
		userPreferences = new CamperPreferences(zip, date, minDist, maxDist, rain, lowT, highT); //test preferences!
		
		locationsByRange = new ArrayList<GPS>();
		locationsByWeather = new ArrayList<GPS>();
		locationsWithPerfectMatch = new ArrayList<GPS>();
		
		zipCodeMatchDatabase = new ZipCodeDatabase();
		filteredCampsites = new HashMap<GPS, String>();
		conditionsByFilteredGPS = new HashMap<GPS, WeatherConditions>();
		
		//Perform Calculations
		locationsByRange = filterByRange();
		locationsByWeather = filterByWeather();
		locationsWithPerfectMatch = findPerfectMatch(locationsByRange, locationsByWeather);
		//filteredCampsites = generateCampsitesFromMatch(locationsWithPerfectMatch);
		//conditionsByFilteredGPS = findWeatherForPerfectMatch();
	}	
	
	
	/*_______TOP LEVEL METHODS_______*/
	//package level method to be called by happyCamperController
	void initializePersonalizedResult() 
	{
		//Perform Calculations
		locationsByRange = filterByRange();
		locationsByWeather = filterByWeather();
		locationsWithPerfectMatch = findPerfectMatch(locationsByRange, locationsByWeather);
		filteredCampsites = generateCampsitesFromMatch(locationsWithPerfectMatch);
		conditionsByFilteredGPS = findWeatherForPerfectMatch();
	}
	
	private List<GPS> filterByRange()
	{
		//FAKE LATITUDE AND LONGITUDE FROM ZIP CODE FOR DEMO
		//double userLatitude = 40.730610;
		//double userLongitude = -73.935242;
		
		String startingZip = userPreferences.getZipCode();
		double userLatitude = zipCodeMatchDatabase.getZipAndLatitude().get(startingZip);
		double userLongitude = zipCodeMatchDatabase.getZipAndLongitude().get(startingZip);
		
		int minDistanceFromUser = userPreferences.getRangeLowerBound();
		int maxDistanceFromUser = userPreferences.getRangeUpperBound();
		
		//for each loop to gather and process latitude and longitude from GPS in weather.
		for(String date : weatherData.getCompleteForecast().keySet())
		{
			int dateIndex = forecastDateToIndex(date);
			SingleDayForecast releventForecast = weatherData.getSingleDayForecastObj(dateIndex);
		
			for(GPS gps : releventForecast.getSingleDayGPS())
			{
				double currentLatitude = gps.getLatitude();
				double currentLongitude = gps.getLongitude();
				double distanceFromUser = findDistance(userLatitude, userLongitude,
														currentLatitude, currentLongitude);

				//System.out.println(gps + " has distance:  " + distanceFromUser);
				//add to locationsByRange
				if(distanceFromUser > minDistanceFromUser && distanceFromUser < maxDistanceFromUser)
				{
					locationsByRange.add(gps);		
				}
					
			}
		}
		return locationsByRange;
	}
	
	private List<GPS> filterByWeather()
	{
		int userLowT = userPreferences.getPreferredTemperatureLowerBound();
		int userHighT = userPreferences.getPreferredTemperatureUpperBound();
		boolean userRain = userPreferences.getAllowRain();
		//System.out.println("USER INFO: " + userLowT + " " + userHighT + " " + userRain);
		
		SingleDayForecast relevantForecast = filterWeatherByDate();

		for(GPS gps : relevantForecast.getSingleDayGPS())
			{
				int thisTempLow = relevantForecast.getSingleDayWeather(gps).getTempMin();
				int thisTempHigh = relevantForecast.getSingleDayWeather(gps).getTempMax();
				boolean thisRainBool = relevantForecast.getSingleDayWeather(gps).getPrecipitationBool();
				//System.out.println(thisTempLow + " " + thisTempHigh + " " + thisRainBool);

				if(userLowT <= thisTempLow && userHighT >= thisTempHigh  && (userRain == thisRainBool))
				{
					//System.out.println("The location below has been added!");
					locationsByWeather.add(gps);
				}
			}
		return locationsByWeather;
	}
	
	private List<GPS> findPerfectMatch(List<GPS> priority1, List<GPS> priority2)
	{
		List<GPS> combinedList = new ArrayList<GPS>();
		//System.out.println(locationsByRange.size() + " " + locationsByWeather.size());
		//System.out.println(locationsByWeather);
		for(GPS gps : priority1)	 
		{
			if(priority2.contains(gps))	
			{
				//System.out.println("gps added to final choice");
				combinedList.add(gps);
			}	
		}
		return combinedList;
	}
	
	private Map<GPS, String> generateCampsitesFromMatch(List<GPS> filteredLocations)
	{
		for(GPS locGPS : filteredLocations)
		{
			//if the campsiteData contains a zipCode associated with the filtered locations
			String locationZip = locGPS.getZipCode();
			for(GPS campGPS : campsiteData.getCampsiteMap().keySet())
			{
				String campsiteZip = campGPS.getZipCode();
				if(campsiteZip.equals(locationZip))
				{
					String tempCampsiteName = campsiteData.getCampsiteMap().get(campGPS);
					filteredCampsites.put(campGPS, tempCampsiteName);	
				}
			}
		}
		return filteredCampsites;
	}
	
	private Map<GPS, WeatherConditions> findWeatherForPerfectMatch()
	{
		for(GPS campGPS : filteredCampsites.keySet())
		{
			//if the campsiteData contains a zipCode associated with the filtered locations
			String campsiteZip = campGPS.getZipCode();
			SingleDayForecast relevantForecast = filterWeatherByDate();
			
			for(GPS weatherGPS : relevantForecast.getSingleDayGPS())
			{
				String weatherZip = weatherGPS.getZipCode();
				//matches the gps from campsites to the weather from applicable gps in weatherData
				//comparison is by zipcode
				if(campsiteZip.equals(weatherZip))
				{
					conditionsByFilteredGPS.put(campGPS, relevantForecast.getSingleDayWeather(weatherGPS));	
				}
			}
		}
		return conditionsByFilteredGPS;
	}
	
	//CLEAR COLLECTIONS
	public void clearCollections()
	{
		//weatherData.getCompleteForecast().clear();
		//campsiteData.getCampsiteMap().clear();
		//zipCodeMatchDatabase.getZipAndLatitude().clear();
		//zipCodeMatchDatabase.getZipAndLongitude().clear();
		
		userPreferences.resetMembers();
		locationsByRange.clear();
		locationsByWeather.clear();
		locationsWithPerfectMatch.clear();
		filteredCampsites.clear();
		conditionsByFilteredGPS.clear();
	}
	
	/*_______LOWER LEVEL METHODS_______*/
	//Reduces Redundancy
	private SingleDayForecast filterWeatherByDate()
	{
		String userPreferredDate = userPreferences.getPreferredDate();
		int dateIndex = forecastDateToIndex(userPreferredDate);
		SingleDayForecast relevantForecast = weatherData.getSingleDayForecastObj(dateIndex);

		return relevantForecast;
	}
	
	//IMPORTANT! This distance calculates the direct distance in miles, not miles driving.
	private static double findDistance(double lat1, double lon1, double lat2, double lon2) 
	{
		//If the compared locations are the same, distance is 0.
		if ((lat1 == lat2) && (lon1 == lon2)) 
		{
			return 0;
		}
		//else, calculate the distances
		else 
		{
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			return (dist);
		}
	}

	/*_______DISPLAY METHODS_______*/
	public void displayGPSList(List<GPS> someList)
	{
		for(GPS gps : someList)
		{
			System.out.println(gps);
		}
	}
	
	public void displayListSizes()
	{
		System.out.println("__________LIST SIZES__________"
						+"\nLocations by Range Size: " + locationsByRange.size()
						+"\nLocations by Weather Size: " + locationsByWeather.size()
						+"\nLocations with Perfect Match Size: " + locationsWithPerfectMatch.size()
						+"\nFiltered Campsites Size: " + filteredCampsites.size());
	}
	
	//string version for GUI display
	public String displayListSizesString()
	{
		 return "__________LIST SIZES__________"
						+"\nLocations by Range Size: " + locationsByRange.size()
						+"\nLocations by Weather Size: " + locationsByWeather.size()
						+"\nLocations with Perfect Match Size: " + locationsWithPerfectMatch.size()
						+"\nFiltered Campsites Size: " + filteredCampsites.size();
	}
	
	public void displayFilteredCampsites()
	{
		System.out.println("\n__________MATCHING CAMPSITES__________\n");
		if(filteredCampsites.isEmpty())
		{
			System.out.println("Sorry! No campsites match your request. Try again with different parameters.");
		}
		else 
		{
			for(GPS gps: filteredCampsites.keySet())
			{
				System.out.println(filteredCampsites.get(gps));
			}	
		}
	}
	
	//String version for GUI display
	public String displayFilteredCampsitesString()
	{
		String allString = "\n\n__________MATCHING CAMPSITES__________\n";
		if(filteredCampsites.isEmpty())
		{
			allString+= "Sorry! No campsites match your request. Try again with different parameters.";
		}
		else if(!filteredCampsites.isEmpty()) 
		{
			for(GPS gps: filteredCampsites.keySet())
			{
				allString += filteredCampsites.get(gps);
				allString += "\n";
			}	
		}
		else
			allString+= "The list of filtered campsites could not be displayed.";
		
		return allString;
		
	}
	
	public void displayFilteredCampsitesWithWeather()
	{
		String allString = "\n\n__________MATCHING CAMPSITES WITH WEATHER__________\n"
				+ "for date: " + userPreferences.getPreferredDate() + "\n";
		for(GPS gps: filteredCampsites.keySet())
		{	
			System.out.println(filteredCampsites.get(gps)
							+ "\n\t\tWEATHER CONDITIONS:\n\t\tRain Predicted: " 
							+ conditionsByFilteredGPS.get(gps).getPrecipitationBool()
							+ "\n\t\tPrecipitation Amt: " + conditionsByFilteredGPS.get(gps).getPrecipitationAmt()
							+ "\n\t\tTemperature High: " + conditionsByFilteredGPS.get(gps).getTempMax()
							+ "\n\t\tTemperature Low: " + conditionsByFilteredGPS.get(gps).getTempMin());
		}	
	}
	
	//String version for GUI display
	public String displayFilteredCampsitesWithWeatherString()
	{
		String allString = "\n\n__________MATCHING CAMPSITES WITH WEATHER__________\n"
						+ "for date: " + userPreferences.getPreferredDate() + "\n";
		for(GPS gps: filteredCampsites.keySet())
		{	
			allString += "\n" + filteredCampsites.get(gps)
							+ "\n\t\tWEATHER CONDITIONS:\n\t\tRain Predicted: " 
							+ conditionsByFilteredGPS.get(gps).getPrecipitationBool()
							+ "\n\t\tPrecipitation Amt: " + conditionsByFilteredGPS.get(gps).getPrecipitationAmt()
							+ "\n\t\tTemperature High: " + conditionsByFilteredGPS.get(gps).getTempMax()
							+ "\n\t\tTemperature Low: " + conditionsByFilteredGPS.get(gps).getTempMin();
		}	
		return allString;
	}
	
	public void combinedDisplayForUser()
	{
		displayListSizes();
		displayFilteredCampsites();
		displayFilteredCampsitesWithWeather();
		/*
		// ALTERNATE DISPLAYS
		//displayGPSList(locationsByRange);
		//displayGPSList(locationsByWeather);
		//displayGPSList(locationsWithPerfectMatch);

		for(GPS gps: locationsWithPerfectMatch)
		{
			System.out.println( gps + " " 
								+ weatherData.getCompleteForecast().get("06/01/20").getSingleDayForecastMap().get(gps));
		}
		*/
	}

	public String displayAllAsString()
	{
		return displayListSizesString() 
				+ displayFilteredCampsitesString()
				+ displayFilteredCampsitesWithWeatherString();
	}
	
	/*_______GETTERS & SETTERS_______*/
	public CamperPreferences getUserPreferences() 
	{
		return userPreferences;
	}

	public void setUserPreferences(CamperPreferences userPreferences) 
	{
		this.userPreferences = userPreferences;
	}
	
	/*_______TO STRING_______*/
	@Override
	public String toString() 
	{
		return "PersonalizedResult "
				+ ", filteredCampsites=" + filteredCampsites
				+ "]";
	}
}

