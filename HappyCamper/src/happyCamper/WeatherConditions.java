package happyCamper;

/*------------------------------------------------------------------------------------
 * SingleDayForecast Description:
 * This class is utilized by the Weather class as an array of SingleDayForecasts.
 * Original implementation was a map within the weather class,
 * but the program requires 7 iterable instances of SingleDayForecast, one for each day.
 * A singleDayForecast Map -> List implementation inside Weather class was considered, but:
 * 		the structure seemed important enough to warrant a class, 
 * 		array-indexing was a straightforward solution, 
 * 		the Weather class was beginning to look overly-complex.
 * However, implementing this as a class structure did end up complicating data access.
 * 
 * ! CONSIDER REVISING FOR FUTURE STAGES
 *------------------------------------------------------------------------------------*/
public class WeatherConditions{
	/*_______DATA MEMBERS & COLLECTIONS_______*/
	private double precipitationAmt; //CHANGE TO BOOL FOR CURRENT IMPLEMENTATION
	private boolean precipitationBool;
	private int tempMax;
	private int tempMin;
	
	/*_________CONSTRUCTORS_________*/
	//Default - Initialize Weather Data to 0
	public WeatherConditions() 
	{
		setPrecipitationAmt(0.0);
		setPrecipitationBool(precipitationAmt);
		setTempMax(0);
		setTempMin(0);
	}
	
	//3-Parameter
	public WeatherConditions(double precip, int tMax, int tMin)
	{
		setPrecipitationAmt(precip);
		setPrecipitationBool(precip);
		setTempMax(tMax);
		setTempMin(tMin);
	}
	
	/*_________GETTERS & SETTERS_________*/	
	public double getPrecipitationAmt() 
	{
		return precipitationAmt;
	}

	public void setPrecipitationAmt(double precipitationAmt) 
	{
		this.precipitationAmt = precipitationAmt;
	}

	public boolean getPrecipitationBool() 
	{
		return precipitationBool;
	}

	public void setPrecipitationBool(double precipitationAmt) 
	{
		if(precipitationAmt > 0.0)
			this.precipitationBool = true;
		else
			this.precipitationBool = false;
	}
	
	public int getTempMax() 
	{
		return tempMax;
	}

	public void setTempMax(int tempMax) 
	{
		this.tempMax = tempMax;
	}

	public int getTempMin() 
	{
		return tempMin;
	}

	public void setTempMin(int tempMin) 
	{
		this.tempMin = tempMin;
	}

	/*_________TO STRING_________*/
	@Override
	public String toString() 
	{
		return " WeatherConditions [precipitationAmt=" + precipitationAmt 
				+ ", precipitationBool=" + precipitationBool
				+ ", tempMax=" + tempMax 
				+ ", tempMin=" + tempMin + "] ";
	}
}
