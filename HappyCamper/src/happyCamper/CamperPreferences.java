package happyCamper;

public class CamperPreferences 
{
	private static boolean preferredRain;
	private String zipCode;
	
	private int rangeLowerBound;
	private int rangeUpperBound;
	private int preferredTempLowerBound;
	private int preferrredTempUpperBound;
	
	// boolean to describe if it is raining or not
	// false = no rain
	// true = rain
	
	public static void noRain() 
    {
        preferredRain = false;
    }
			
	public static boolean yesRain()
    {
        return preferredRain == true;
    }
 	
	public CamperPreferences() 
	{
		// TODO Auto-generated constructor stub
	}

	// getters and setters
	
	public boolean isPreferredRain() 
	{
		return preferredRain;
	}

	public void setPreferredRain(boolean preferredRain) 
	{
		this.preferredRain = preferredRain;
	}

	public String getZipCode() 
	{
		return zipCode;
	}

	public void startZipCode(String zipCode) 
	{
		this.zipCode = zipCode;
	}
	
	public int getRangeLowerBound() 
	{
		return rangeLowerBound;
	}

	public void setRangeLowerBound(int rangeLowerBound) 
	{
		this.rangeLowerBound = rangeLowerBound;
	}

	public int getRangeUpperBound() 
	{
		return rangeUpperBound;
	}

	public void setRangeUpperBound(int rangeUpperBound) 
	{
		this.rangeUpperBound = rangeUpperBound;
	}

	public int getPreferredTempLowerBound() 
	{
		return preferredTempLowerBound;
	}

	public void setPreferredTempLowerBound(int preferredTempLowerBound) 
	{
		this.preferredTempLowerBound = preferredTempLowerBound;
	}

	public int getPreferrredTempUpperBound() 
	{
		return preferrredTempUpperBound;
	}

	public void setPreferrredTempUpperBound(int preferrredTempUpperBound) 
	{
		this.preferrredTempUpperBound = preferrredTempUpperBound;
	}
	
	//main
	public static void main(String[] args) 	
	{
		
		// return rain preference 
		System.out.println(preferredRain); 
	
	}

}
