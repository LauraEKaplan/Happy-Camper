# CSC 330 Happy Camper
## This code currently functions as a prototype model. The weather data is static historic, and date data is frozen to match.

Laura Kaplan, Victoria Jaczynski, Gary Feng

<br>
<br><u>How to run:</u>
<br>	With GUI: Happy Camper should be run from the HappyCamperView class in order to interract with the GUI.
<br>	Without GUI: Functionality without the GUI should be tested from the PersonalizedResult class.
<br>	Testing Classes: Most classes are set up with their own commented-out main method for individual class testing.
	
	There are 3 files which need to be adjusted to the user's specific file-path. They are located in the classes:
		- Weather
		- Campsite
		- ZipCodeDatabase

<br><u>About the data and results:</u>
<br>	<b>DATE RANGE: Must be between 06/01/20 and 06/08/20</b>
<br>	User's initial zip code should be a New York state zip code.
<br>	Not all dates have good weather. The weather on 06/01/20 and 06/02/20 is particularly cold and rainy.
<br>	More campsites available at ranges over 100 miles from NYC.
<br>
<br><u>Realism Note:</u>
<br>	Some campsite names associated with multiple zip codes due the nature of our datasets. 
<br>	This issue would not be present with more accurate datasets.
