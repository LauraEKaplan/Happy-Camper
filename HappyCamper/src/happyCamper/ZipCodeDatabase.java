package happyCamper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

/*------------------------------------------------------------------------------------
 * ZipCodeDatabase Description:
 * Processes a database containing every zip-code in NY and its associated longitude & latitude.
 * 
 * NOTE: This is NOT utilizing the GPS class. 
 * This is a different type of location-based structure and we made its information distinct
 * because we will never use this particular package of data as a key for other information. 
 * Instead this zip code data is collected in an 'unpacked' way so that math can be performed on the
 * longitude & latitude values associated with a zip code.
 * 
 * Used by: used by PersonalizedResult for calculating which zip codes have the preferred
 * distance from the user.
 *------------------------------------------------------------------------------------*/
public class ZipCodeDatabase
{
	/*_________DATA & COLLECTIONS_________*/
	public static String fileLocation = "F:\\College\\Classwork\\Semester 4 - 2021 fall\\CSC 330 - Iacona\\Group Project\\All Final Datasets\\NY_ZipCodes_Final.txt";
	private Map<String, Double> zipAndLatitude; //contains latitude and longitude in that order
	private Map<String, Double> zipAndLongitude; //contains zipCode, (latitude & longitude) 
	
	/*_________CONSTRUCTORS_________*/
	public ZipCodeDatabase()
	{
		zipAndLatitude = new HashMap<String, Double>();
	    zipAndLongitude = new HashMap<String, Double>();
	    readInZipCodeDatabase();
	}
	
	/*_________TOP LEVEL METHODS_________*/
	public void readInZipCodeDatabase()
	{
		String tempZip = "0";
		double tempLat = 0;
		double tempLon = 0;
		Scanner zipCodeData = null;
		File file = new File(fileLocation);
		
	    try 
	    {
	    	zipCodeData = new Scanner(file);
	        while (zipCodeData.hasNextLine()) 
	        {
	        	String row = zipCodeData.nextLine();
	            String[] rowArray = row.split(",");
	            
    			tempZip = rowArray[0];
    			tempLat = Double.parseDouble(rowArray[1]);			
    			tempLon = Double.parseDouble(rowArray[2]);

	            zipAndLatitude.put(tempZip, tempLat);
	            zipAndLongitude.put(tempZip, tempLon);
		   }
	    } catch (FileNotFoundException e) {
	    	System.out.println("An Error Occurred in CampsiteData Read-In");
			System.exit(0);
	        e.printStackTrace();
	    } finally {
	        zipCodeData.close();
	    }
	}

	/*_________GETTERS & SETTERS_________*/
	public Map<String, Double> getZipAndLatitude() 
	{
		return zipAndLatitude;
	}

	public void setZipAndLatitude(Map<String, Double> zipAndLatitude) 
	{
		this.zipAndLatitude = zipAndLatitude;
	}

	public Map<String, Double> getZipAndLongitude() 
	{
		return zipAndLongitude;
	}

	public void setZipAndLongitude(Map<String, Double> zipAndLongitude) 
	{
		this.zipAndLongitude = zipAndLongitude;
	}
	
	/*_________TO STRING_________*/
	@Override
	public String toString() {
		return "ZipCodeDatabase [zipAndLatitude=" + zipAndLatitude + ", zipAndLongitude=" + zipAndLongitude + "]";
	}

	
}
