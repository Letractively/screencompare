package screencompare;

import java.awt.Color;
import java.io.PrintWriter;
import java.io.StringWriter;

import junit.framework.TestCase;
import screencompare.browser.Browser;
import screencompare.driver.Driver;
import screencompare.driver.DriverSelenium1;
import screencompare.screen.Screen;
import screencompare.screen.ScreenInfo;
import screencompare.screen.Screen.Alignment;

public class ScreencompareTestCase extends TestCase {
	private Browser _browser = null;
	private Driver _driver = null;
	private StringBuffer _verificationErrors = new StringBuffer();

	public Driver getDriver() {
		if (_driver == null) {
			// Default driver
			_driver = new DriverSelenium1();
		}
		return _driver;
	}
	
	public void setDriver(Driver driver) {
		_driver = driver;
	}

	public void start(String url, Browser browser) {
		_browser = browser;
		_browser.setDriver(getDriver());
		_browser.open(url);
		_initPage();
	}
	
	public void start(Browser browser) {
		start(null, browser);
	}

	public void stop() {
		_browser.close();
	}

	public void js(String javascript) {
		getDriver().runJs(javascript);
	}

	public Screen captureScreen() {
		return _browser.getScreen(getTestName());
	}
	
	public Screen loadScreen(String url, Browser browser) {
		ScreenInfo info = new ScreenInfo(url, browser, getTestName());
		return new Screen(info.getFilename(), info);
	}

	public void setBackground(Color color) {
		_browser.setBackground(color);
	}
	
	public void hideElement(String elementId) {
		js("document.getElementById('" + elementId + "').style.display='none';");
	}
	
	public void hideText(String elementId) {
		js("document.getElementById('" + elementId + "').style.color='transparent';");
	}
	
	public void hideText() {
		js("var all=document.getElementsByTagName('*'); for(var i=0; (e=all[i])!=null; i++) {e.style.color='transparent';}");
	}

	public void verifySame(Screen screen1, Screen screen2, Alignment align) {
		verifySame(screen1, screen2, 0f, align);
	}
	public void verifySame(Screen screen1, Screen screen2) {
		verifySame(screen1, screen2, null);
	}

	public void verifySame(Screen screen1, Screen screen2, double threshold, Alignment align) {
		try {
			assertSame(screen1, screen2, threshold, align);
		} catch (Error e) {
			screen1.diffReport(screen2, align);
			_verificationErrors.append(_throwableToString(e));
		}
	}
	public void verifySame(Screen screen1, Screen screen2, double threshold) {
		verifySame(screen1, screen2, threshold, null);
	}

	public void assertSame(Screen screen1, Screen screen2, Alignment align) {
		assertSame(screen1, screen2, 0f, align);
	}
	public void assertSame(Screen screen1, Screen screen2) {
		assertSame(screen1, screen2, null);
	}

	public void assertSame(Screen screen1, Screen screen2, double threshold, Alignment align) {
		float diffVal = screen1.diffVal(screen2, align);
		assertTrue("Screen difference: " + diffVal + " (threshold: " + threshold + ")", diffVal <= threshold);
	}
	public void assertSame(Screen screen1, Screen screen2, double threshold) {
		assertSame(screen1, screen2, threshold, null);
	}

	static public void assertTrue(String message, boolean condition) {
		if (!condition) {
			fail(message);
		}
	}

	public static void fail(String message) {
		throw new AssertionError(message);
	}
	
	public void waitElementVisible(String elementId) {
		_driver.waitForCondition("selenium.browserbot.getCurrentWindow().document.getElementById('" + elementId + "')" +
									" && selenium.browserbot.getCurrentWindow().document.getElementById('" + elementId + "').style.display != 'none'");
	}

	public void waitJqueryAjaxActive() {
		_driver.waitForCondition("selenium.browserbot.getCurrentWindow().jQuery && selenium.browserbot.getCurrentWindow().jQuery.active > 0");
	}

	public void waitJqueryAjaxInactive() {
		_driver.waitForCondition("selenium.browserbot.getCurrentWindow().jQuery && selenium.browserbot.getCurrentWindow().jQuery.active == 0");
	}

	public void waitJqueryAjaxDone() {
		waitJqueryAjaxActive();
		waitJqueryAjaxInactive();
	}
	
	
	
	private void _initPage() {
		js("document.getElementsByTagName('html')[0].style.overflow='hidden';");
	}

	private static String _throwableToString(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		return sw.toString();
	}

	private void _checkForVerificationErrors() {
		String verificationErrorString = _verificationErrors.toString();
		if (!verificationErrorString.equals("")) {
			fail(verificationErrorString);
		}
	}
	
	private String getTestName() {
		StackTraceElement callerTest = new Throwable().fillInStackTrace().getStackTrace()[2];
		String testName = callerTest.getClassName() + "." + callerTest.getMethodName() + "()";
		return testName;
	}

	public void tearDown() throws Exception {
		_checkForVerificationErrors();
		stop();
	}

}
