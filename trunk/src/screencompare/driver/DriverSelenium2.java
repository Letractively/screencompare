package screencompare.driver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import screencompare.browser.Browser;


public class DriverSelenium2 extends Driver {

	private WebDriver _selenium;

	protected void _start(String url, Browser browser) {
		switch (browser.getModel()) {
			case CHROME:
				_selenium = new ChromeDriver();
				break;
			case FIREFOX:
				_selenium = new FirefoxDriver();
				break;
			case IE:
				_selenium = new InternetExplorerDriver();
				break;
			default:
				throw new UnsupportedOperationException();
		}
		
		if (url != null) _selenium.get(url);
	}

	protected void _stop() {
		_selenium.quit();
	}

	public void runJs(String javascript) {
		((JavascriptExecutor) _selenium).executeScript(javascript);
	}

}
