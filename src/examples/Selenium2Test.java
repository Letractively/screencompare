import screencompare.*;
import screencompare.browser.*;
import screencompare.screen.*;
import screencompare.screen.Screen.Alignment;
import screencompare.driver.*;

public class Selenium2Test extends ScreencompareTestCase {

	public void test() throws Exception {
		setDriver(new DriverSelenium2());
		start("http://www.google.com/ncr", Browser.FIREFOX);
		captureScreen().save();
		stop();
	}

}