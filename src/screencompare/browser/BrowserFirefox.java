package screencompare.browser;



public class BrowserFirefox extends Browser {
	
	public String getName() {
		return "Firefox";
	}
	
	public String getWindowTitle() {
		return "Mozilla Firefox";
	}
	
	public BrowserModel getModel() {
		return BrowserModel.FIREFOX;
	}
	
}
