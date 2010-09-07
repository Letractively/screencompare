package screencompare.driver;

import screencompare.browser.Browser;

public abstract class Driver {
	private String _url = null;
	private boolean _running = false;

	public void start(String url, Browser browser) {
		_url = url;
		_running = true;
		_start(url, browser);
	}
	
	public void runJs(String javascript) {
		throw new UnsupportedOperationException();
	}
	
	public void waitForCondition(String javascript) {
		throw new UnsupportedOperationException();
	}
	
	public String getUrl() {
		return _url;
	}
	
	public void stop() {
		if (_running) {
			_stop();
			_running = false;
		}
	}
	
	protected abstract void _start(String url, Browser browser);

	protected abstract void _stop();

}
