import screencompare.*;
import screencompare.browser.*;
import screencompare.screen.*;
import screencompare.screen.Screen.Alignment;

public class ScreenshotsWinTest extends ScreencompareTestCase {

	public void test() throws Exception {
		Browser[] browsers = { Browser.FIREFOX, Browser.IE, Browser.CHROME };

		for (Browser browser : browsers) {
			start("http://www.google.ch", browser);
			captureScreen().save();
			stop();
		}
	}

}