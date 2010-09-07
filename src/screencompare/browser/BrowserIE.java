package screencompare.browser;



public class BrowserIE extends Browser {
	
	public String getName() {
		return "Internet Explorer";
	}
	
	public String getWindowTitle() {
		return "Internet Explorer";
	}
	
	public BrowserModel getModel() {
		return BrowserModel.IE;
	}
	
}
