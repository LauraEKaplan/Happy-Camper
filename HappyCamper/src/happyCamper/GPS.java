package happyCamper;

public class GPS 
{
	private int latitude;
	private int longitude;
	private String zipCode;
	private boolean inRange;
	
	

	/*_________CONSTRUCTORS_________*/
	//Default - Initialize Location Data to 0
	public GPS() 
	{
		setLatitude(0);
		setLongitude(0);
		setZipCode("00000");
	}
	
	//3-Parameter
	public GPS (int lat, int lon, String zip)
	{
		setLatitude(lat);
		setLongitude(lon);
		setZipCode(zip);
	}
	
	/*_________GETTERS & SETTERS_________*/
	public int getLatitude() 
	{
		return latitude;
	}

	public void setLatitude(int latitude) 
	{
		this.latitude = latitude;
	}

	public int getLongitude() 
	{
		return longitude;
	}

	public void setLongitude(int longitude) 
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

	/*_________GETTERS & SETTERS_________*/
	
	
}
