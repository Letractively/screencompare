package screencompare.browser;


public class BrowserSafari extends Browser {
	
	public String getName() {
		return "Safari";
	}
	
	public String getWindowTitle() {
		return "Safari";
	}
	
	public BrowserModel getModel() {
		return BrowserModel.SAFARI;
	}
	
}
