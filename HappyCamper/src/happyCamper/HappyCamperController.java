package happyCamper;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/*---------------------------------------------------------------------------------- 
*   HappyCamperController Description:
*   [MVC Implementation: CONTROLLER] for Happy Camper
*   Interprets the actions received by the view, and asks the model to change state.
*   Implements 2 listeners: ActionListener and WindowListener.
------------------------------------------------------------------------------------*/
public class HappyCamperController implements ActionListener, WindowListener
{
	private PersonalizedResult camperModel; 	// Happy Camper model instance 
	private HappyCamperView camperView;  	// Happy Camper view instance

	// default constructor 
	public HappyCamperController() throws HappyCamperException 
	{
		initialize();
	}
	
	public void setHappyCamperView(HappyCamperView camperView) 
	{
		this.camperView = camperView;
	}
	
	// private instance methods 
	private void initialize() throws HappyCamperException 
	{
		camperModel = new PersonalizedResult();
	}
	
	
	/*-----------------------------*/
	/*-----------EVENTS------------*/
	/*-----------------------------*/
	public void actionPerformed(ActionEvent ae)
	{
			Object source = ae.getSource();
			System.out.println("HappyCamper actionPerformed: "  + source.getClass().getSimpleName());
			
			if(source == this.camperView.getSubmitButtonObj())
			{
				System.out.println("entered submit");
				//Sets all data values for backend and displays output
				try {
					submitButtonState();
				} catch (HappyCamperException e) {
					e.printStackTrace();
				}
			}			
			else if(source == this.camperView.getResetButtonObj())
			{
				System.out.println("entered reset");
				resetButtonState();
			}	
			else
				System.out.println("something horrible happened");
		
			//displayToCheckMemberValues(zip, date, rangeLBound, rangeUBound, rain, tempLBound, tempUBound);
	};
	
	/*KNOWN BUG DOCUMMENTATION:
	 * The submit button needs to be limited so that it can only be pressed after all data is reentered.
	 */
	public void submitButtonState() throws HappyCamperException
	{
		camperView.getSubmitButton().setBackground(new Color(32, 178, 170));
		String zip;

		zip = camperView.getZipCode_Text().getText();
		String date = camperView.getPreferredDate_Text().getText();
		int rangeLBound = Integer.parseInt(camperView.getRangeLowerBound_Text().getText());
		int rangeUBound = Integer.parseInt(camperView.getRangeUpperBound_Text().getText());
		boolean rain = false;
		if(camperView.getRainyCampsitesTrue_radio().isSelected()) 
		{
			rain = true;
		}
		else if (camperView.getRainyCampsitesFalse_radio().isSelected()) 
		{
			rain = false;
		}
		int tempLBound = Integer.parseInt(camperView.getTempLower_Text().getText());
		int tempUBound = Integer.parseInt(camperView.getTempUpper_Text().getText());
		
		//Set Zip Code
		if(zip.length() == 5)
			camperModel.getUserPreferences().setZipCode(zip);
		else
			throw new HappyCamperException("Invalid Zip Code", HappyCamperException.INVALID_SUBMISSION);
		//Set Date
		if(date.length() == 8)
			camperModel.getUserPreferences().setPreferredDate(date);
		else
			throw new HappyCamperException("Invalid Zip Code", HappyCamperException.INVALID_SUBMISSION);
		//Note, future implementations should have more thrown exceptions.
		camperModel.getUserPreferences().setRangeLowerBound(rangeLBound);
		camperModel.getUserPreferences().setRangeUpperBound(rangeUBound);
		camperModel.getUserPreferences().setAllowRain(rain);
		camperModel.getUserPreferences().setPreferredTemperatureLowerBound(tempLBound);
		camperModel.getUserPreferences().setPreferredTemperatureUpperBound(tempUBound);
		
		//fills all the collections within PersonalizedResult
		camperModel.initializePersonalizedResult();
		System.out.println(camperModel.getUserPreferences());
		//output to console
		camperModel.combinedDisplayForUser();
		//output to GUI
		camperView.getSearchResultTextArea().setText(camperModel.displayAllAsString());
	}
	
	public void resetButtonState()
	{
		//reset background data members & collections for next use
		//MAY BE POINT OF BUG FOR SECOND SUBMIT BUTTON REQUEST
		camperModel.clearCollections();
		//camperView.getResetButton().setBackground(new Color(32, 178, 170));
		camperView.getZipCode_Text().setText("");
		camperView.getPreferredDate_Text().setText("");
		camperView.getRangeLowerBound_Text().setText("");
		camperView.getRangeUpperBound_Text().setText("");
		camperView.getRainyCampsitesTrue_radio().setSelected(false);
		camperView.getRainyCampsitesFalse_radio().setSelected(false);
		camperView.getTempLower_Text().setText("");
		camperView.getTempUpper_Text().setText("");
		camperView.getSubmitButton().setSelected(false);
		camperView.getSearchResultTextArea().setText("Search Result Text Area\nhit submit to view results...");
	}
	
	//Used only internally when it is necessary to confirm user-preference values from this class specifically while debugging
	private void displayToCheckMemberValues(String zip, String date, int rangeLBound, int rangeUBound,
			boolean rain, int tempLBound, int tempUBound)
	{
		System.out.println(zip);
		System.out.println(date);
		System.out.println(rangeLBound);
		System.out.println(rangeUBound);
		System.out.println(rain);
		System.out.println(tempLBound);
		System.out.println(tempUBound);
	}
	
	@Override
	public void windowClosing(WindowEvent e) 
	{
		String ObjButtons[] = {"YES","NO"};
		
	    int popup = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?", "Exit Campsite Finder", 
	        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
	        ObjButtons,ObjButtons[1]);
	    
	    System.out.println("windowClosing() " + popup);
	    if(popup == 0)   
	    	System.exit(0);  
	    // otherwise, stay alive
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
