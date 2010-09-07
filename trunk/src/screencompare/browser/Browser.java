package screencompare.browser;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import java.util.HashMap;

import screencompare.Screencompare;
import screencompare.driver.Driver;
import screencompare.screen.Screen;
import screencompare.screen.ScreenInfo;


public abstract class Browser {
	public final static Browser CHROME = new BrowserChrome();
	public final static Browser FIREFOX = new BrowserFirefox();
	public final static Browser IE = new BrowserIE();
	public final static Browser SAFARI = new BrowserSafari();
	
	public enum BrowserModel { CHROME, FIREFOX, IE, SAFARI };


	private HashMap<String, Rectangle> _viewports = new HashMap<String, Rectangle>();
	private Driver _driver;
	protected BrowserModel _model;

	public abstract String getName();
	
	public abstract String getWindowTitle();
	
	public abstract BrowserModel getModel();

	public void setDriver(Driver driver) {
		_driver = driver;
	}

	public Driver getDriver() {
		return _driver;
	}

	public void open(String url) {
		_getViewport();
		_driver.start(url, this);
	}

	public void close() {
		_driver.stop();
	}

	public void setBackground(Color color) {
		String c = String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
		_driver.runJs("document.body.style.backgroundColor='" + c + "';");
	}

	public Screen getScreen(String testInfo) {
		_maxWindow();
		Screen screen = new Screen(new ScreenInfo(_driver.getUrl(), this, testInfo));
		screen = screen.clip(_getViewport());
		return screen;
	}

	private Rectangle _getViewport() {
		if (!_viewports.containsKey(getName())) {
			_driver.start(null, this);
			setBackground(Color.GREEN);
			_maxWindow();
			Screen screen = new Screen(new ScreenInfo());
			_driver.stop();
			_viewports.put(getName(), screen.findViewport(Color.GREEN));
		}

		return _viewports.get(getName());
	}

	private void _maxWindow() {
		_driver.runJs("window.moveTo(0,1);");
		_driver.runJs("window.moveTo(0,0);");
		_driver.runJs("window.resizeTo(screen.availWidth,screen.availHeight);");
		_driver.runJs("scroll(0,0);");
		_bringWindowToFront();
	}

	private void _bringWindowToFront() {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.indexOf("mac") >= 0) {
			Screencompare._exec(new String[] { "osascript", "-e", "tell application \"" + getName() + "\" to activate" });
		} else if (os.indexOf("win") >= 0) {
			String toTop = Screencompare.getLibPath("totop"+File.separator+"totop.exe");
			Screencompare._exec("\"" + toTop + "\" " + getWindowTitle());
		} else {
			System.err.println("Unsupported OS, cannot bring browser window to front");
			System.exit(1);
		}
		Screencompare.sleep(300);
	}
}
