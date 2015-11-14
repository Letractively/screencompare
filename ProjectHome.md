## About ##
_Screencompare_ is a [Selenium](http://seleniumhq.org/)-based JUnit-framework to compare browser-viewports pixel-wise.

It basically opens your webpages in different browsers, makes screenshots and allows you to compare those automatically. This allows you to create _automated visual layout-tests_ for full webpages or specific elements.
This software was created as part of my [bachelor thesis about Website Testing](http://retokaiser.com/Projects/screencompare/Automatisiertes_Webauftritt-Testen.pdf) (pdf, german) at the [University of Basel](http://informatik.unibas.ch/).

## Example ##
Compare the rendering of "google.ch" in Firefox and IE with a maximum allowed difference of 5%:
```
public class CompareFirefoxIETest extends ScreencompareTestCase {
	public void test() throws Exception {
		start("http://www.google.ch", Browser.FIREFOX);
		Screen firefox = captureScreen();
		stop();

		start("http://www.google.ch", Browser.IE);
		Screen ie = captureScreen();
		stop();

		verifySame(firefox, ie, 0.05f, Alignment.TOP_CENTER);
	}
}
```
If this test fails (ie the screenshots differ more than 5%), a PDF-report will be created so you can take a look at what went wrong ([see example report](http://www.retokaiser.com/Projects/screencompare/report_CompareFirefoxIETest.pdf)).

You can see this test running in a screencast: http://www.youtube.com/watch?v=e_tKplaAtZ4

## Getting started ##
This software is a prototype! You should be able to create and run your own tests, but also might run into problems and not find all functionality you need. _Screencompare_ was tested on Mac OS X and Windows XP.

You can find a very short introduction, some examples and videos at GettingStarted.