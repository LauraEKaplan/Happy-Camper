package happyCamper;

/*---------------------------------------------------------------------------------- 
 * HappyCamperException Description:
 * Note: exception class functionality not visible via GUI
 * This class primarily handles exceptions for data-processing
------------------------------------------------------------------------------------*/
public class HappyCamperException extends Exception
{
	// static publicly defined error codes 
	public static int INVALID_READIN = 0;	  
	public static int INVALID_DATE_REFERENCE = 1; 
	public static int INVALID_SUBMISSION = 2;
	//public static int INVALID_ZIP = 3;
	
	protected int code;  // have this exception carry a code as well as 
	
	// static publicly defined exception messages  
	public static String[]  MESSAGE = 
	{ 
		"Requested Data Could Not Be Accessed", 
	  		"Invalid Date Reference", 
	  		"The user has submitted invalid data",
	  		"The zipcode entered is invalid",
	 } ;
	
	// Constructors ... 
	private  HappyCamperException() 
	{
		
	}

	// with thrower message 
    public HappyCamperException(String message) 
    { 
    	super(message); 
    }
	
	// with thrower message & code 
    public HappyCamperException(String message, int code) 
    { 
    	super(message);
    	this.code = code;
    } 
    
	// with thrower message & cause
    public HappyCamperException(String message, Throwable cause) 
    { 
    	super(message, cause); 
    } 
    
    // with thrower message and code 
    public HappyCamperException(Throwable cause, int code) 
    { 
    	super(cause);
    	this.code = code;
    } 
    
    // with thrower message and code 
    public HappyCamperException(String message, Throwable cause, int code) 
    { 
    	super(message, cause);
    	this.code = code;
    }
    
	public int getCode() 
	{ 
		return code;
	}
	
    @Override
	public String toString() 
    {
	return "HappyCamperException \nError Code = " + code + "\nDescription = "
				+ super.toString() + "!\n" + MESSAGE[code] +"\n";
	}    
	   
}
