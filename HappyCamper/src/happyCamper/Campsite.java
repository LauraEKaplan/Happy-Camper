package happyCamper;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;

/*------------------------------------------------------------------------------------
 * Campsite Description:
 * Reads and stores campsite data from the csv files into various collections
 * contains a map collection of type Map<campsiteGPS, campsiteName> which
 * makes data accessible/searchable to other classes.
 * This class is primarily utilized during the filtering stage performed by PersonalizedResult
 *------------------------------------------------------------------------------------*/
public class Campsite
{
	/*_________DATA & COLLECTIONS_________*/
	private static String fileLocation = "F:\\College\\Classwork\\Semester 4 - 2021 fall\\CSC 330 - Iacona\\Group Project\\All Final Datasets\\NY_Campgrounds_Final.txt";
	private String campsiteName; //stores a particular campsite name
	private GPS campsiteGPS; //stores a particular campsite GPS
	private Map<GPS, String> campsiteMap; //note! <campsiteGPS, campsiteName>
	
	/*_________MAIN_________*/
	//Used for testing
	public static void main(String[] args)
	{
//		Campsite myCampsite = new Campsite();
//		System.out.println(myCampsite);
	}
	
	/*_________CONSTRUCTORS_________*/
	public Campsite() {
		readInCampsiteData();
	}
	
	/*_______TOP LEVEL METHODS_______*/
	public void readInCampsiteData() 
	{
        campsiteMap = new HashMap<GPS, String>();
        double tempLat = 0;
		double tempLon = 0;
		String tempZip = "0";
		Scanner campData = null;
		File file = new File(fileLocation);
		
	    try 
	    {
	    	campData = new Scanner(file);
	        while (campData.hasNextLine()) 
	        {
	        	String row = campData.nextLine();
	            String[] rowArray = row.split(",");
	    		
	            //Indexes refer to columns in comma-separated-file
				setCampsiteName(rowArray[0]);
				tempLat = Double.parseDouble(rowArray[1]);			
				tempLon = Double.parseDouble(rowArray[2]);
				tempZip = rowArray[3];

	            campsiteGPS = new GPS(tempLat, tempLon, tempZip);
		    	//zipCodeList.add(tempZip);
		    	//campsiteNameList.add(campsiteName);
	            campsiteMap.put(campsiteGPS, campsiteName);
		   }
	    } catch (FileNotFoundException e) {
	    	System.out.println("An Error Occurred in SingleDayForecast Read-In");
			System.exit(0);
	    	e.printStackTrace();
	    } finally {
	        campData.close();
	    }
	    
	}//end readInCampsiteData
	
	/*_________TO STRING_________*/
	@Override
	public String toString() {
		return "Size of Map=" + campsiteMap.size() + "\nLast Entered campsiteName=" + campsiteName 
				+ "\nLast Entered campsiteGPS=" + campsiteGPS 
				+ "\nFull campsiteMap=\n" + campsiteMap + "]";
	}

	/*_________GETTERS & SETTERS_________*/
	public Map<GPS, String> getCampsiteMap()
	{
		return campsiteMap;
	}
	
	public Map<GPS, String> setCampsiteMap()
	{
		campsiteMap.put(campsiteGPS, campsiteName);
		return campsiteMap;
	}
	
	public String getCampsiteName() 
	{
		return campsiteName;
	}

	public void setCampsiteName(String campsiteName) 
	{
		this.campsiteName = campsiteName;
	}

	public GPS getCampsiteGPS() 
	{
		return campsiteGPS;
	}

	public void setCampsiteGPS(GPS campsiteGPS) 
	{
		this.campsiteGPS = campsiteGPS;
	}
}
