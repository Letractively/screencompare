package screencompare.screen;

import screencompare.browser.Browser;

public class ScreenInfo {
	private String _url;
	private Browser _browser;
	private String _test;
	
	public ScreenInfo() {
	}
	
	public ScreenInfo(String url, Browser browser, String test) {
		_url = url;
		_browser = browser;
		_test = test;
	}
	
	public String getUrl() {
		return _url;
	}
	
	public String getBrowserName() {
		if (_browser != null) {
			return _browser.getName();
		}
		return null;
	}
	
	public String getTest() {
		return _test;
	}
	
	public String getBrowserAndUrl() {
		StringBuffer result = new StringBuffer();
		result.append(getUrl());
		if (getBrowserName() != null) {
			result.append(" (" + getBrowserName() + ")");
		}
		return result.toString();
	}
	
	public String getFilename() {
		String filename = getBrowserAndUrl() + ".png";
		filename = filename.replaceAll("[^\\w\\d\\s\\.\\_\\(\\)-]+", "_");
		return filename;
	}

}
