package happyCamper;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;


import java.util.HashMap;

/*------------------------------------------------------------------------------------
 * Weather Description:
 * reads and stores weather data from the csv files into various collections
 * makes it accessible/search-able as a collection to other classes
 * read-in is based on a specific number of columns that are file-specific.
 * future implementation should have more flexible file read-in methods.
 * 
 * Utilizes GPS, SingleDayForecast, and WeatherConditions
 *------------------------------------------------------------------------------------*/
public class Weather
{
	/*_________DATA & COLLECTIONS_________*/
	private static final int DAYS_IN_FORECAST = 8;
	private static String fileLocation = "F:\\College\\Classwork\\Semester 4 - 2021 fall\\CSC 330 - Iacona\\Group Project\\All Final Datasets\\NY_Weather_Final.txt";
	private WeatherConditions conditions; //contains PRCP,TMAX,TMIN
	private GPS weatherGPS; //contains latitude longitute zip
	public SingleDayForecast dailyForecast[] = new SingleDayForecast[DAYS_IN_FORECAST]; //class containing a map of GPS & weather conditions for dailyForecast
	public Map<String, SingleDayForecast> completeForecast; //each DATE associated with its singleDayForecast
	
	/*_________MAIN_________*/
	//NOTE: Main function exists for testing purposes ONLY. Not for program implementation.
	public static void main(String[] args) throws HappyCamperException
	{
//		Weather myWeather = new Weather();
//		System.out.println(myWeather); 	//note: dataset size prevents console from showing full display
//		myWeather.outputWeatherFor("06/03/20", "13066");	
	}
	
	/*_________CONSTRUCTORS_________*/
	//Default - Initialize Location Data to 0
	public Weather() throws HappyCamperException 
	{
		readInWeatherData();
	}
	
	/*_______TOP LEVEL METHODS_______*/
	//read in csv file for weather
	private void readInWeatherData() throws HappyCamperException 
	{
		//Instantiate collections objects
		for(int i = 0; i < DAYS_IN_FORECAST; i++)
		{
			dailyForecast[i] = new SingleDayForecast();
		}
		completeForecast = new HashMap<String, SingleDayForecast>();
		
		//Temporary data members for file read in
		double tempLat = 0;
		double tempLon = 0;
		String tempZip = "0";
		double tempPrecip = 0;
		int tempTMin = 0;
		int tempTMax = 0;
		String tempDate = "0";
		int forecastIndex = 0;
		Scanner weatherData = null;
		
		//File Read-In
		try
		{
			File file = new File(fileLocation);
			weatherData = new Scanner(file);
			while (weatherData.hasNextLine()) 
			{
				String row = weatherData.nextLine();
				String[] rowArray = row.split(",");
				
				//Index values specific to weather dataset columns
					// 0 = Date, 1 = Latitude, 2 = Longitude, 3 = ZipCode,
					// 4 = Precipitation, 5 = TemperatureMax, 6 = TemperatureMin
				tempDate = rowArray[0];
				forecastIndex = forecastDateToIndex(tempDate);
				tempLat = Double.parseDouble(rowArray[1]);					
				tempLon = Double.parseDouble(rowArray[2]);						
				tempZip = rowArray[3];	
				tempPrecip = Double.parseDouble(rowArray[4]);
				tempTMax = Integer.parseInt(rowArray[5]);
				tempTMin = Integer.parseInt(rowArray[6]);

				
				//Initialize objects & collections using read-in data
				weatherGPS = new GPS(tempLat, tempLon, tempZip);
				conditions = new WeatherConditions(tempPrecip, tempTMax, tempTMin);	
				//Initialize collections using above objects
				dailyForecast[forecastIndex].setSingleDayForecast(weatherGPS, conditions);
				completeForecast.put(tempDate, dailyForecast[forecastIndex]);
			}
		} catch (Exception e) {
			//System.out.println("An Error Occurred in SingleDayForecast Read-In");
			throw new HappyCamperException(e.getMessage(), HappyCamperException.INVALID_READIN);
		} finally {
			weatherData.close();
		}
	}

	//Currently hard-coded but future implementation should be dynamic to # of forecast dates
	public static int forecastDateToIndex(String date)
	{
		if(date.equals("06/01/20"))
			return 0;
		else if(date.equals("06/02/20"))
			return 1;
		else if(date.equals("06/03/20"))
			return 2;
		else if(date.equals("06/04/20"))
			return 3;
		else if(date.equals("06/05/20"))
			return 4;
		else if(date.equals("06/06/20"))
			return 5;
		else if(date.equals("06/07/20"))
			return 6;
		else if(date.equals("06/08/20"))
			return 7;
		else
		{
			System.out.println("Date from weather file could not be identified.");
			//throw exception here later
			System.exit(0);
		}
		return 0;
	}
	
	public void outputWeatherFor(String preferredDate, String preferredZip)
	{
		//retrieve index for accessing the correct SingleDayForecast
		int tempIndex = forecastDateToIndex(preferredDate);
		
		//Iterate by Date
		for(String date : completeForecast.keySet())
		{
			//Filter by Specific Date
			if(date.contains(preferredDate))
			{
				//retrieve single map for forecast on the preferredDate
				Map<GPS,WeatherConditions> forecastMapShallowCopy = getSingleDayForecastObj(tempIndex).getSingleDayForecastMap();

				//Iterate by GPS
				for(GPS gps: forecastMapShallowCopy.keySet())
				{
					//Filter by specific Zip Code
					if(gps.getZipCode().equals(preferredZip))
						System.out.println("The weather data at..." + gps
								+ "\nOn date " + preferredDate
								+ " is: "+ forecastMapShallowCopy.get(gps));
				}
			}
		}
	}
	
	/*_________TO STRING_________*/
	@Override
	public String toString() 
	{
		return "Weather [Last Read weatherConditions = " + conditions 
				+ "\nLast Read WeatherGPS = " + weatherGPS 
				+ "\nsingleDayForecast for 6/2/2020 = " + dailyForecast[1] 
				+ "\ncompleteForecast = " + completeForecast.get("6/2/2020").getSingleDayForecastMap()
		+ "]";
	}

	/*_________GETTERS & SETTERS_________*/	
	//NOTE: No setters available as the data-processing should be fully internal
	
	//This method is used to take the array of SingleDayForecast objects
	//and select just one SingleDayForecastObject for further breakdown/search/access
	public SingleDayForecast getSingleDayForecastObj(int index) 
	{
		return dailyForecast[index];
	}

	public Map<String, SingleDayForecast> getCompleteForecast() {
		return completeForecast;
	}


	
}

