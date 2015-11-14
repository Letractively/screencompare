# Introduction #
After you [downloaded](http://code.google.com/p/screencompare/downloads/list) the Screencompare-package, you'll have four folders:
  * **lib**: Contains the "screencompare.jar" plus all other required jars (Selenium, JUnit, iText, ...)
  * **doc**: Javadoc for _screencompare_ (Unfortunately almost no method-comments, sorry)
  * **data**: Output like screenshots and pdf-reports will be stored here
  * **examples**: Example tests, some of which are described on this page

You'll also see the following scripts for easy test-running from the command line:
  * **startSelenium.sh/bat**: Start the Selenium server which is needed for almost all tests
  * **runExample.sh/bat**: Run one of the example tests


# Requirements #
  * Windows or Mac OS X
  * JRE & JDK (_java_ and _javac_ on your path)
  * The browsers you'd like to run tests with


# Examples #
The Selenium-server needs to be running for these tests, you can start it from a terminal with the included script:

Mac OS X: `./startSelenium.sh`<br />
Windows: `startSelenium.bat`

Then start the test case in a second terminal by specifying an example's name as parameter for "runExample.sh":

Mac OS X: `./runExample.sh ScreenshotsMacTest`<br />
Windows: `runExample.bat ScreenshotsMacTest`


## ScreenshotsWinTest ##
We'll start by executing a basic test-case which opens a webpage in various browsers and saves the screenshots to disk.
```
import screencompare.*;
import screencompare.browser.*;
import screencompare.screen.*;
import screencompare.screen.Screen.Alignment;

public class ScreenshotsWinTest extends ScreencompareTestCase {

	public void test() throws Exception {
		Browser[] browsers = { Browser.FIREFOX, Browser.IE, Browser.CHROME };

		for (Browser browser : browsers) {
			start("http://www.google.ch", browser);
			captureScreen().save();
			stop();
		}
	}

}
```
This will result in three screenshots from the different browsers in your "data/screens/" folder.

<a href='http://www.youtube.com/watch?feature=player_embedded&v=JFA-hXStq-k' target='_blank'><img src='http://img.youtube.com/vi/JFA-hXStq-k/0.jpg' width='425' height=344 /></a>


## CompareFirefoxIETest ##
This will open two sites in Firefox and IE. The resulting screenshots are compared by _verifySame()_. A threshold of 0.05 is passed along, so 5% of the pixels may differ for the test to succeed.
```
import screencompare.*;
import screencompare.browser.*;
import screencompare.screen.*;
import screencompare.screen.Screen.Alignment;

public class CompareFirefoxIETest extends ScreencompareTestCase {

	public void test() throws Exception {
		String[] urls = {"http://www.google.ch",
				"http://weatherflash.com/"};

		for (String url:urls) {
			start(url, Browser.FIREFOX);
			Screen firefox = captureScreen();
			stop();
			
			start(url, Browser.IE);
			Screen ie = captureScreen();
			stop();
			
			verifySame(firefox, ie, 0.05f, Alignment.TOP_CENTER);
		}
	}

}
```
In this case both visited webpages differ more than 5% in IE6 and Firefox. A PDF-report for each page is created in your "data/reports/"-folder. You can take a look at an example report [here](http://www.retokaiser.com/Projects/screencompare/report_CompareFirefoxIETest.pdf).

<a href='http://www.youtube.com/watch?feature=player_embedded&v=e_tKplaAtZ4' target='_blank'><img src='http://img.youtube.com/vi/e_tKplaAtZ4/0.jpg' width='425' height=344 /></a>


## CompareFirefoxSafariTest ##
The same goal as above, but with Firefox and Safari for testing on Mac OS X.
```
import screencompare.*;
import screencompare.browser.*;
import screencompare.screen.*;
import screencompare.screen.Screen.Alignment;

public class CompareFirefoxSafariTest extends ScreencompareTestCase {

	public void test() throws Exception {
		String[] urls = {"http://www.google.ch",
				"http://weatherflash.com/"};

		for (String url:urls) {
			start(url, Browser.FIREFOX);
			Screen firefox = captureScreen();
			stop();
			
			start(url, Browser.SAFARI);
			Screen safari = captureScreen();
			stop();
			
			verifySame(firefox, safari, 0.05f, Alignment.TOP_CENTER);
		}
	}

}
```

<a href='http://www.youtube.com/watch?feature=player_embedded&v=P4MyFk18mdg' target='_blank'><img src='http://img.youtube.com/vi/P4MyFk18mdg/0.jpg' width='425' height=344 /></a>


## RegressionTest ##
The idea of this test is to compare an old and new version of a website visually, let's say
  * _http://www.retokaiser.com/.old/_ is the old version and
  * _http://www.retokaiser.com/_ is the new version.
We'd like to check whether some pages on this site have changed between the versions.
```
import screencompare.*;
import screencompare.browser.*;
import screencompare.screen.*;
import screencompare.screen.Screen.Alignment;

public class RegressionTest extends ScreencompareTestCase {

	public void test() throws Exception {
		// Get references
		String[] urls_old = {"http://www.retokaiser.com/.old/Howtos/Reauthor DVD without menus.txt",
					"http://www.retokaiser.com/.old/Unterrichtsmaterialien Informatik/"};
		ArrayList<Screen> references = new ArrayList<Screen>();
		for (String url:urls_old) {
			start(url, Browser.SAFARI);
			references.add(captureScreen());
			stop();
		}
	
		// Compare with current
		String[] urls_new = {"http://www.retokaiser.com/Howtos/Reauthor DVD without menus.txt",
					"http://www.retokaiser.com/Unterrichtsmaterialien Informatik/"};
		int i=0;
		for (String url:urls_new) {
			start(url, Browser.SAFARI);
			verifySame(captureScreen(), references.get(i++));
			stop();
		}
	}

}
```
This test will fail and create a PDF-report, since the page _"/Unterrichtsmaterialien Informatik/"_ has changed between the versions. <br />
Tests like these allow you to check for undetected UI-glitches that might result when you change something on a website or web application. You could also store screenshots of some version to disk, and later on compare them with another version of the website (See _RegressionStore.java_ and _RegressionStoredTest.java_).

<a href='http://www.youtube.com/watch?feature=player_embedded&v=rg_U1vAq0rs' target='_blank'><img src='http://img.youtube.com/vi/rg_U1vAq0rs/0.jpg' width='425' height=344 /></a>


## HideTest ##
This last example shows you how to hide specific elements on a webpage with _hideElement()_, which can be useful if you have ads on a page, but don't want to compare them.<br />
Additionally _hideText()_ is used to make all text transparent. Often text is rendered differently in different browsers, which is usually not a bug, so you might want to ignore text in your visual comparison.
```
import screencompare.*;
import screencompare.browser.*;
import screencompare.screen.*;
import screencompare.screen.Screen.Alignment;

public class HideTest extends ScreencompareTestCase {

	public void test() throws Exception {
		start("http://photography.nationalgeographic.com/photo-of-the-day/", Browser.FIREFOX);
		captureScreen().save("nationalgeographic-1.png");
		hideElement("headerboard");
		captureScreen().save("nationalgeographic-2.png");
		hideText();
		captureScreen().save("nationalgeographic-3.png");
		stop();
	}

}
```
This will result in three screenshots in your "data/screens/"-folder. The first with the full page, the second without ad, and the third without text.

<a href='http://www.youtube.com/watch?feature=player_embedded&v=4aLL029tyuM' target='_blank'><img src='http://img.youtube.com/vi/4aLL029tyuM/0.jpg' width='425' height=344 /></a>