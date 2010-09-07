import screencompare.*;
import screencompare.browser.*;
import screencompare.screen.*;
import screencompare.screen.Screen.Alignment;

public class ScreenshotsMacTest extends ScreencompareTestCase {

	public void test() throws Exception {
		Browser[] browsers = { Browser.SAFARI, Browser.FIREFOX };

		for (Browser browser : browsers) {
			start("http://www.google.ch", browser);
			captureScreen().save();
			stop();
		}
	}

}