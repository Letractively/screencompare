import screencompare.*;
import screencompare.browser.*;
import screencompare.screen.*;
import screencompare.screen.Screen.Alignment;


public class CompareFirefoxSafariTest extends ScreencompareTestCase {

	public void test() throws Exception {
		String[] urls = {"http://www.google.ch",
						"http://weatherflash.com/"};

		for (String url:urls) {
			start(url, Browser.FIREFOX);
			Screen firefox = captureScreen();
			stop();
			
			start(url, Browser.SAFARI);
			Screen safari = captureScreen();
			stop();
			
			verifySame(firefox, safari, 0.05f, Alignment.TOP_CENTER);
		}
	}

}