import screencompare.*;
import screencompare.browser.*;
import screencompare.screen.*;
import screencompare.screen.Screen.Alignment;

public class RegressionStoredTest extends ScreencompareTestCase {

	public void test() throws Exception {
		String[] urls = {"http://www.retokaiser.com/",
						"http://www.retokaiser.com/Unterrichtsmaterialien Informatik/"};

		for (String url:urls) {
			start(url, Browser.SAFARI);
			verifySame(captureScreen(), loadScreen(url, Browser.SAFARI));
			stop();
		}
	}

}