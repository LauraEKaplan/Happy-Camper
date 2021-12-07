package happyCamper;

/*------------------------------------------------------------------------------------
 * GPS Description:
 * This class acts as the key for most Happy Camper collections objects.
 * Used in: Campsite, PersonalizedResult, SingleDayForecast, Weather, WeatherConditions
 *------------------------------------------------------------------------------------*/
public class GPS
{
	/*_________DATA & COLLECTIONS_________*/
	private double latitude;
	private double longitude;
	private String zipCode;
	private boolean inRange; //"in-range" is dependent on information from HappyCamperPreferences

	/*_________CONSTRUCTORS_________*/
	//Default - Initialize Location Data to 0
	public GPS() 
	{
		setLatitude(0);
		setLongitude(0);
		setZipCode("00000");
		System.out.println("Please note, GPS has not recieved data. All members initialized to 0.");
	}	
	//3-Parameter
	public GPS (double lat, double lon, String zip)
	{
		setLatitude(lat);
		setLongitude(lon);
		setZipCode(zip);
	}
	
	/*_________GETTERS & SETTERS_________*/
	public double getLatitude() 
	{
		return latitude;
	}

	public void setLatitude(double latitude) 
	{
		this.latitude = latitude;
	}

	public double getLongitude() 
	{
		return longitude;
	}

	public void setLongitude(double longitude) 
	{
		this.longitude = longitude;
	}

	public String getZipCode() 
	{
		return zipCode;
	}

	public void setZipCode(String zipCode) 
	{
		this.zipCode = zipCode;
	}
	
	public boolean isInRange() 
	{
		return inRange;
	}

	public void setWithinRange(boolean inRange) 
	{
		this.inRange = inRange;
	}
	
	/*_________TO STRING_________*/
	@Override
	public String toString() 
	{
		return "\nGPS [lat= " + latitude + ", lon= " + longitude + ", zip= " + zipCode + "] ";
	}

	//Check if searched zip code matches existing zip code.
	public boolean equalsZip(String zip) 
	{
		return zip.equalsIgnoreCase(this.zipCode);
	}	
}
