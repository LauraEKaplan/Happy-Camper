package happyCamper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*------------------------------------------------------------------------------------
 * SingleDayForecast Description:
 * This class is utilized by the Weather class as an array of SingleDayForecasts.
 * Original implementation was a map within the weather class,
 * but the program requires 7 iterable instances of SingleDayForecast, one for each day.
 * A singleDayForecast Map -> List implementation inside Weather class was considered,
 * but the structure seemed important enough to warrant a class, array-indexing was a straightforward solution, 
 * and the Weather class was beginning to look overly-complex.
 *------------------------------------------------------------------------------------*/
public class SingleDayForecast 
{
	/*_______DATA MEMBERS & COLLECTIONS_______*/
	private Map<GPS, WeatherConditions> singleDayForecast;
	
	/*_________CONSTRUCTORS_________*/
	public SingleDayForecast() 
	{
		singleDayForecast = new HashMap<GPS, WeatherConditions>();
	}

	/*_________GETTERS & SETTERS_________*/	
	public Map<GPS, WeatherConditions> getSingleDayForecastMap() 
	{
		return singleDayForecast;
	}
	
	public Set<GPS> getSingleDayGPS()
	{
		return singleDayForecast.keySet();
	}
	
	public WeatherConditions getSingleDayWeather(GPS key) 
	{
		return singleDayForecast.get(key);
	}

	public void setSingleDayForecast(GPS gps, WeatherConditions conditions) 
	{
		this.singleDayForecast.put(gps, conditions);
	}
	
	/*_________TO STRING_________*/
	@Override
	public String toString() 
	{
		return "singleDayForecast =[" + singleDayForecast + "]";
	}

}