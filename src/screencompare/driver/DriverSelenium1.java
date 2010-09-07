package screencompare.driver;

import java.net.URL;

import screencompare.browser.Browser;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;

public class DriverSelenium1 extends Driver {

	private Selenium _selenium;

	protected void _start(String url, Browser browser) {
		try {
			String urlHost=null, urlPath=null;
			if (url == null) {
				urlHost = "http://localhost";
			} else {
				URL _url = new URL(url);
				urlHost = _url.getProtocol() + "://" + _url.getAuthority();
				urlPath = _url.getPath();
				if (_url.getQuery() != null)
					urlPath += "?" + _url.getQuery();
				if (_url.getRef() != null)
					urlPath += "#" + _url.getRef();				
			}
			
			String browserStartCommand = null;
			switch (browser.getModel()) {
				case CHROME:
					browserStartCommand = "*googlechrome";
					break;
				case FIREFOX:
					browserStartCommand = "*firefox";
					break;
				case IE:
					browserStartCommand = "*iexplore";
					break;
				case SAFARI:
					browserStartCommand = "*safari";
					break;
				default:
					throw new UnsupportedOperationException();
			}

			_selenium = new DefaultSelenium("localhost", 4444, browserStartCommand, urlHost);
			_selenium.start();
			if (urlPath != null) _selenium.open(urlPath);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	protected void _stop() {
		_selenium.stop();
	}

	public void runJs(String javascript) {
		_selenium.runScript(javascript);
	}
	
	public void waitForCondition(String javascript) {
		try {
			_selenium.waitForCondition(javascript, "20000");
		} catch(SeleniumException e) {
			stop();
			throw e;
		}
	}

}
