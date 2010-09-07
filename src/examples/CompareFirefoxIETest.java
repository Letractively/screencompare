import screencompare.*;
import screencompare.browser.*;
import screencompare.screen.*;
import screencompare.screen.Screen.Alignment;


public class CompareFirefoxIETest extends ScreencompareTestCase {

	public void test() throws Exception {
		String[] urls = {"http://www.google.ch",
						"http://weatherflash.com/"};

		for (String url:urls) {
			start(url, Browser.FIREFOX);
			Screen firefox = captureScreen();
			stop();
			
			start(url, Browser.IE);
			Screen ie = captureScreen();
			stop();
			
			verifySame(firefox, ie, 0.05f, Alignment.TOP_CENTER);
		}
	}

}