package happyCamper;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;

/*------------------------------------------------------------------------------------  
*   HappyCamperView Description:
*   [MVC Implementation: VIEW] for Happy Camper
*   Program should be run from HappyCamperView
*   User interacts with the view, and view may be asked to change by controller.
*   Extends a Frame - runnable class.
*------------------------------------------------------------------------------------*/
public class HappyCamperView extends JFrame
{
	private static final long serialVersionUID = 1L;
	/*_________DATA & COLLECTIONS_________*/
	private HappyCamperController controller;
	private static String zip;
	private static String date;
	private static int rangeLBound;
	private static int rangeUBound;
	private static boolean rain = false;
	private static int tempLBound;
	private static int tempUBound;

	//GUI Component Instance Data Members -----------------
	//-----------------------------------------------------
	private JFrame frame;
	private JTextField zipCode_Text;
	private JTextField preferredDate_Text;
	private JTextField rangeLowerBound_Text;
	private JTextField rangeUpperBound_Text;
	private JRadioButton rainyCampsitesTrue_radio;
	private JRadioButton rainyCampsitesFalse_radio;
	private JTextField tempLower_Text;
	private JTextField tempUpper_Text;
	private JButton submitButton;
	private JButton resetButton;
	
	private JLabel happyCamper_Label;
	private JLabel zipCode_Label;
	private JLabel preferredDate_Label;
	private JLabel dateFormat_Label;
	private JLabel range_Label;
	private JLabel range2_Label;
	private JLabel range3_Label;
	private JLabel range4_Label;
	private JLabel preferredRain_Label;
	private JLabel temperateRange_Label;
	private JLabel tempRange2_Label;
	private JLabel tempRange3_Label;
	private JLabel tempRange4_Label;
	
	//Search Results
	private JScrollPane scrollPane;
	JTextArea searchResultTextArea;
	JLabel searchResultLabel;

	//-----------------------------------------------------
	
	// Listeners (Controllers)  
	private ActionListener actionListener;  
	private WindowListener windowListener;
	
	/*_________CONSTRUCTORS & ACTIVATOR_________*/
	//Default Constructor
	public HappyCamperView()
	{
		initialize();
	}
	//Constructor with 1 parameter: listener (aka: MVC Controller)
	public HappyCamperView(HappyCamperController listener) // throws HeadlessException
	{
		actionListener = listener; 
		windowListener = listener;
		initialize();
	}
	//Activate launches application safely 
	public static void activate() throws HappyCamperException
	{
		 //Start GUI thread without interference
	      EventQueue.invokeLater(new Runnable() 
	      {
	            public void run() 
	            {
	            	HappyCamperController listener;
	            	try {
						listener = new HappyCamperController();
						HappyCamperView window = new HappyCamperView(listener);
		            	window.frame.setVisible(true);
		            	listener.setHappyCamperView(window);
		            	window.setVisible(false); 
					} catch (HappyCamperException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}     
	            }
	        }
	      );
	}

	
	/*_________MAIN_________*/
	public static void main(String[] args) throws HappyCamperException
	{
		// create viewable instance 
		activate();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		frame.setBackground(new Color(32, 178, 170));
		frame.getContentPane().setBackground(new Color(143, 188, 143));
		frame.setBounds(100, 100, 1421, 695);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//HEADER
		happyCamper_Label = new JLabel("Hello Fellow Happy Campers!");
		happyCamper_Label.setBackground(new Color(32, 178, 170));
		happyCamper_Label.setHorizontalAlignment(SwingConstants.CENTER);
		happyCamper_Label.setFont(new Font("Century Gothic", Font.BOLD, 30));
		happyCamper_Label.setBounds(10, 0, 857, 80);
		frame.getContentPane().add(happyCamper_Label);
		
		//ZIP CODE
		zipCode_Label = new JLabel("What is your current zip code?");
		zipCode_Label.setForeground(new Color(0, 0, 0));
		zipCode_Label.setBounds(30, 100, 361, 35);
		zipCode_Label.setFont(new Font("SansSerif", Font.PLAIN, 25));
		frame.getContentPane().add(zipCode_Label);
		
		zipCode_Text = new JTextField();
		zipCode_Text.setForeground(new Color(0, 0, 0));
		zipCode_Text.setBackground(new Color(245, 222, 179));
		zipCode_Text.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(102, 205, 170), new Color(32, 178, 170), new Color(32, 178, 170), null));
		zipCode_Text.setHorizontalAlignment(SwingConstants.CENTER);
		zipCode_Text.setFont(new Font("SansSerif", Font.PLAIN, 30));
		zipCode_Text.setColumns(10);
		zipCode_Text.setBounds(390, 95, 250, 40);
		frame.getContentPane().add(zipCode_Text);
		
		//DATE
		preferredDate_Label = new JLabel("What date do you want to go?");
		preferredDate_Label.setBounds(30, 160, 361, 35);
		preferredDate_Label.setFont(new Font("SansSerif", Font.PLAIN, 25));
		frame.getContentPane().add(preferredDate_Label);
		
		preferredDate_Text = new JTextField();
		preferredDate_Text.setBackground(new Color(245, 222, 179));
		preferredDate_Text.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(102, 205, 170), new Color(32, 178, 170), new Color(32, 178, 170), null));
		preferredDate_Text.setHorizontalAlignment(SwingConstants.CENTER);
		preferredDate_Text.setFont(new Font("SansSerif", Font.PLAIN, 30));
		preferredDate_Text.setColumns(10);
		preferredDate_Text.setBounds(390, 155, 250, 40);
		frame.getContentPane().add(preferredDate_Text);
		
		dateFormat_Label = new JLabel("format: MM/DD/YY");
		dateFormat_Label.setFont(new Font("SansSerif", Font.PLAIN, 20));
		dateFormat_Label.setBounds(650, 164, 188, 31);
		frame.getContentPane().add(dateFormat_Label);
		
		//RANGE
		range_Label = new JLabel("How far do you want to go?");
		range_Label.setBounds(30, 220, 361, 35);
		range_Label.setFont(new Font("SansSerif", Font.PLAIN, 25));
		frame.getContentPane().add(range_Label);
		
		range2_Label = new JLabel("at least");
		range2_Label.setFont(new Font("SansSerif", Font.PLAIN, 25));
		range2_Label.setBounds(99, 280, 111, 35);
		frame.getContentPane().add(range2_Label);
		
		range3_Label = new JLabel("miles away  |  no more than");
		range3_Label.setFont(new Font("SansSerif", Font.PLAIN, 25));
		range3_Label.setBounds(300, 280, 317, 34);
		frame.getContentPane().add(range3_Label);
		
		range4_Label = new JLabel("miles away");
		range4_Label.setFont(new Font("SansSerif", Font.PLAIN, 25));
		range4_Label.setBounds(726, 280, 128, 34);
		frame.getContentPane().add(range4_Label);
		
		rangeLowerBound_Text = new JTextField();
		rangeLowerBound_Text.setBackground(new Color(245, 222, 179));
		rangeLowerBound_Text.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(102, 205, 170), new Color(32, 178, 170), new Color(32, 178, 170), null));
		rangeLowerBound_Text.setHorizontalAlignment(SwingConstants.CENTER);
		rangeLowerBound_Text.setFont(new Font("SansSerif", Font.PLAIN, 30));
		rangeLowerBound_Text.setBounds(190, 275, 100, 40);
		frame.getContentPane().add(rangeLowerBound_Text);
		rangeLowerBound_Text.setColumns(10);
		
		rangeUpperBound_Text = new JTextField();
		rangeUpperBound_Text.setBackground(new Color(245, 222, 179));
		rangeUpperBound_Text.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(102, 205, 170), new Color(32, 178, 170), new Color(32, 178, 170), null));
		rangeUpperBound_Text.setHorizontalAlignment(SwingConstants.CENTER);
		rangeUpperBound_Text.setFont(new Font("SansSerif", Font.PLAIN, 30));
		rangeUpperBound_Text.setColumns(10);
		rangeUpperBound_Text.setBounds(616, 275, 100, 40);
		frame.getContentPane().add(rangeUpperBound_Text);
		
		//RAIN
		preferredRain_Label = new JLabel("How do you feel about rainy campsites?");
		preferredRain_Label.setBounds(30, 340, 564, 35);
		preferredRain_Label.setFont(new Font("SansSerif", Font.PLAIN, 25));
		frame.getContentPane().add(preferredRain_Label);
		
		rainyCampsitesTrue_radio = new JRadioButton("rainy campsites are okay!");
		rainyCampsitesTrue_radio.setBackground(new Color(143, 188, 143));
		rainyCampsitesTrue_radio.setFont(new Font("SansSerif", Font.PLAIN, 25));
		rainyCampsitesTrue_radio.setBounds(99, 400, 327, 35);
		frame.getContentPane().add(rainyCampsitesTrue_radio);
		
		rainyCampsitesFalse_radio = new JRadioButton("no rainy campsites!\r\n");
		rainyCampsitesFalse_radio.setBackground(new Color(143, 188, 143));
		rainyCampsitesFalse_radio.setFont(new Font("SansSerif", Font.PLAIN, 25));
		rainyCampsitesFalse_radio.setBounds(450, 400, 327, 35);
		frame.getContentPane().add(rainyCampsitesFalse_radio);
		
		//TEMPERATURE
		temperateRange_Label = new JLabel("What temperature do you prefer?");
		temperateRange_Label.setBounds(30, 460, 492, 35);
		temperateRange_Label.setFont(new Font("SansSerif", Font.PLAIN, 25));
		frame.getContentPane().add(temperateRange_Label);
		
		tempLower_Text = new JTextField();
		tempLower_Text.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(102, 205, 170), new Color(32, 178, 170), new Color(32, 178, 170), null));
		tempLower_Text.setBackground(new Color(245, 222, 179));
		tempLower_Text.setHorizontalAlignment(SwingConstants.CENTER);
		tempLower_Text.setFont(new Font("SansSerif", Font.PLAIN, 30));
		tempLower_Text.setColumns(10);
		tempLower_Text.setBounds(190, 516, 100, 40);
		frame.getContentPane().add(tempLower_Text);
		
		tempUpper_Text = new JTextField();
		tempUpper_Text.setBackground(new Color(245, 222, 179));
		tempUpper_Text.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(102, 205, 170), new Color(32, 178, 170), new Color(32, 178, 170), null));
		tempUpper_Text.setHorizontalAlignment(SwingConstants.CENTER);
		tempUpper_Text.setFont(new Font("SansSerif", Font.PLAIN, 30));
		tempUpper_Text.setColumns(10);
		tempUpper_Text.setBounds(616, 516, 100, 40);
		frame.getContentPane().add(tempUpper_Text);
		
		tempRange2_Label = new JLabel("at least");
		tempRange2_Label.setFont(new Font("SansSerif", Font.PLAIN, 25));
		tempRange2_Label.setBounds(100, 520, 111, 35);
		frame.getContentPane().add(tempRange2_Label);

		tempRange3_Label = new JLabel("degrees    |    no more than");
		tempRange3_Label.setFont(new Font("SansSerif", Font.PLAIN, 25));
		tempRange3_Label.setBounds(300, 521, 317, 35);
		frame.getContentPane().add(tempRange3_Label);
		
		//submitButton = new JButton("submit"); //SUBMIT BUTTON
		submitButton = createButton("submit");
		resetButton = createButton("reset");

		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, new Color(102, 205, 170), new Color(32, 178, 170), new Color(32, 178, 170), null));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(877, 11, 519, 638);
		frame.getContentPane().add(scrollPane);
		
		searchResultTextArea = new JTextArea();
		searchResultTextArea.setLineWrap(true);
		searchResultTextArea.setEditable(false);
		searchResultTextArea.setText("Invisible text");
		searchResultTextArea.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		searchResultTextArea.setText("Search Result Text Area\nhit submit to view results...");
		scrollPane.setViewportView(searchResultTextArea);

		searchResultLabel = new JLabel("Search Results");
		searchResultLabel.setBackground(new Color(245, 222, 179));
		searchResultLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		searchResultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(searchResultLabel);
		
		tempRange4_Label = new JLabel("degrees.");
		tempRange4_Label.setFont(new Font("SansSerif", Font.PLAIN, 25));
		tempRange4_Label.setBounds(726, 522, 128, 34);
		frame.getContentPane().add(tempRange4_Label);
		

	
		addActionListeners();
		addOtherListeners();
	}
	
	/*________LISTENERS________*/
	private void addActionListeners() 
	{
		if(this.actionListener == null)
				return; 

		this.submitButton.addActionListener(this.actionListener);
		this.resetButton.addActionListener(this.actionListener);
	}
	
	private void addOtherListeners() 
	{
		addWindowListener(windowListener);
		
	}
	
	/*________BUTTON CREATION________*/
	private JButton createButton(String label)
	{
		JButton button = new JButton(label);
		if(label.equals("submit"))
		{
			//button.setBackground(new Color(102, 205, 170));
			button.setFont(new Font("SansSerif", Font.PLAIN, 25));
			button.setBounds(30, 581, 225, 40);
		}
		if(label.equals("reset"))
		{
			button.setFont(new Font("SansSerif", Font.PLAIN, 25));
			button.setBounds(280, 581, 225, 40);
		}
		frame.getContentPane().add(button);
		return button;
	}

	/*________GUI ELEMENT GETTERS________*/
	public JFrame getFrame() {
		return frame;
	}

	public JTextField getZipCode_Text() {
		return zipCode_Text;
	}

	public JTextField getPreferredDate_Text() 
	{
		return preferredDate_Text;
	}

	public JTextField getRangeLowerBound_Text() 
	{
		return rangeLowerBound_Text;
	}

	public JTextField getRangeUpperBound_Text() 
	{
		return rangeUpperBound_Text;
	}

	public JRadioButton getRainyCampsitesTrue_radio() 
	{
		return rainyCampsitesTrue_radio;
	}

	public JRadioButton getRainyCampsitesFalse_radio() 
	{
		return rainyCampsitesFalse_radio;
	}

	public JTextField getTempLower_Text() {
		return tempLower_Text;
	}

	public JTextField getTempUpper_Text() {
		return tempUpper_Text;
	}
	
	public Object getSubmitButtonObj() 
	{
		return submitButton;
	}
	
	public JButton getSubmitButton() 
	{
		return submitButton;
	}
	
	public Object getResetButtonObj() 
	{
		return resetButton;
	}
	
	public JButton getResetButton() 
	{
		return resetButton;
	}
	
	public JTextArea getSearchResultTextArea() 
	{
		return searchResultTextArea;
	}

	public void setFrame(JFrame frame) 
	{
		this.frame = frame;
	}
	
	public void setZipCode_Text(JTextField zipCode_Text) 
	{
		this.zipCode_Text = zipCode_Text;
	}

	public void setPreferredDate_Text(JTextField preferredDate_Text) 
	{
		this.preferredDate_Text = preferredDate_Text;
	}
	
	public void setRangeLowerBound_Text(JTextField rangeLowerBound_Text) 
	{
		this.rangeLowerBound_Text = rangeLowerBound_Text;
	}

	public void setRangeUpperBound_Text(JTextField rangeUpperBound_Text) 
	{
		this.rangeUpperBound_Text = rangeUpperBound_Text;
	}
	
	public void setRainyCampsitesTrue_radio(JRadioButton rainyCampsitesTrue_radio) 
	{
		this.rainyCampsitesTrue_radio = rainyCampsitesTrue_radio;
	}
	
	public void setRainyCampsitesFalse_radio(JRadioButton rainyCampsitesFalse_radio) 
	{
		this.rainyCampsitesFalse_radio = rainyCampsitesFalse_radio;
	}
	
	public void setTempLower_Text(JTextField tempLower_Text) 
	{
		this.tempLower_Text = tempLower_Text;
	}
	
	public void setTempUpper_Text(JTextField tempUpper_Text) 
	{
		this.tempUpper_Text = tempUpper_Text;
	}
	
	public void setSearchResultTextArea(JTextArea searchResultTextArea) 
	{
		this.searchResultTextArea = searchResultTextArea;
	}
	

	@Override
	public String toString() 
	{
		return "HappyCamperView [zipCode_Text=" + zipCode_Text + ", preferredDate_Text="
				+ preferredDate_Text + ", rangeLowerBound_Text=" + rangeLowerBound_Text + ", rangeUpperBound_Text="
				+ rangeUpperBound_Text + ", rainyCampsitesTrue_radio=" + rainyCampsitesTrue_radio
				+ ", rainyCampsitesFalse_radio=" + rainyCampsitesFalse_radio + ", tempLower_Text=" + tempLower_Text
				+ ", tempUpper_Text=" + tempUpper_Text + "]";
	}
}
	