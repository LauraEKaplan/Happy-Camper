package happyCamper;

import java.awt.EventQueue;

/*------------------------------------------------------------------------------------
 * CamperPreferences Description:  
*   Subclass for the [MVC Implementation: MODEL] for Happy Camper
*   Interprets the actions received by the view, and asks the model to change state.
*   Implements 2 listeners: ActionListener and WindowListener.
*------------------------------------------------------------------------------------*/
public class CamperPreferences
{
	/*_________DATA MEMBERS & COLLECTIONS_________*/

	private HappyCamperView guiInput;
	private static String zipCode;
	private static String preferredDate;
	private static int rangeLowerBound;
	private static int rangeUpperBound;
	private static boolean allowRain;
	private static int preferredTemperatureLowerBound;
	private static int preferredTemperatureUpperBound;
	
	/*_________CONSTRUCTORS_________*/
	public CamperPreferences() 
	{
	}

	//non-default used only for testing
	public CamperPreferences(String zip, String date, int minDist, int maxDist, boolean rain, int lowT, int highT) 
	{
		zipCode = zip;
		preferredDate = date;
		rangeLowerBound = minDist;
		rangeUpperBound = maxDist;
		allowRain = rain;
		preferredTemperatureLowerBound = lowT;
		preferredTemperatureUpperBound = highT;
	}

	/*_________MAIN_________*/
	//Used for testing
	public static void main(String[] args) throws HappyCamperException
	{
//		CamperPreferences preferences = new CamperPreferences();
//		System.out.println(preferences);
//		CamperPreferences testPreferences = new CamperPreferences("10001", "06/01/20", 50, 150, "no", 60, 90);
	}
	
	/*_________TOP LEVEL METHODS_________*/
	public void resetMembers()
	{
		zipCode = null;
		preferredDate = null;
		rangeLowerBound = 0;
		rangeUpperBound = 0;
		allowRain = false;
		preferredTemperatureLowerBound = 0;
		preferredTemperatureUpperBound = 0;
	
	}
	
	/*_________GETTERS & SETTERS_________*/	
	// get Zipcode
	public String getZipCode() 
	{
		return zipCode;
	}

	// set Zipcode
	public void setZipCode(String zipCode) 
	{
		CamperPreferences.zipCode = zipCode;
	}
		
	// get Preferred Date
	public String getPreferredDate() 
	{
		return preferredDate;
	}

	// set Preferred Date
	public void setPreferredDate(String preferredDate) 
	{
		CamperPreferences.preferredDate = preferredDate;
	}

	// get Distance Range Lower Bound
	public int getRangeLowerBound() 
	{
		return rangeLowerBound;
	}

	// set Distance Range Lower Bound
	public void setRangeLowerBound(int rangeLowerBound) 
	{
		CamperPreferences.rangeLowerBound = rangeLowerBound;
	}

	// get Distance Range Upper Bound
	public int getRangeUpperBound() 
	{
		return rangeUpperBound;
	}

	// set Distance Range Upper Bound
	public void setRangeUpperBound(int rangeUpperBound)
	{
		CamperPreferences.rangeUpperBound = rangeUpperBound;
	}
	
	// set whether or not rain is allowed
	public void setAllowRain(boolean allowRain)
	{
		CamperPreferences.allowRain = allowRain;
	}
	
	// get whether or net rain is allowed
	public boolean getAllowRain() 
	{
		return allowRain;
	}

	// get Preferred Temperature Range Lower Bound
	public int getPreferredTemperatureLowerBound() 
	{
		return preferredTemperatureLowerBound;
	}

	// set Preferred Temperature Range Lower Bound
	public void setPreferredTemperatureLowerBound(int preferredTemperatureLowerBound) 
	{
		CamperPreferences.preferredTemperatureLowerBound = preferredTemperatureLowerBound;
	}

	// get Preferred Temperature Range Upper Bound
	public int getPreferredTemperatureUpperBound() 
	{
		return preferredTemperatureUpperBound;
	}

	// set Preferred Temperature Range Upper Bound
	public void setPreferredTemperatureUpperBound(int preferredTemperatureUpperBound) 
	{
		CamperPreferences.preferredTemperatureUpperBound = preferredTemperatureUpperBound;
	}

	@Override
	public String toString() 
	{
		return "Camper Preferences: \n\tStarting Zip Code =" + getZipCode() + ", Preferred Travel Date =" + getPreferredDate() 
				+ ", Minimum Distance Away =" + getRangeLowerBound() + ", Maximum Distance Away =" + getRangeUpperBound() 
				+ ", Rainy Campsites Allowed? =" + getAllowRain() 
				+ ", Minimum Temp =" + getPreferredTemperatureLowerBound()
				+ ", MaximumTemp =" + getPreferredTemperatureUpperBound() + "\n";
	}
}