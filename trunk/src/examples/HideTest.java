import screencompare.*;
import screencompare.browser.*;
import screencompare.screen.*;
import screencompare.screen.Screen.Alignment;

public class HideTest extends ScreencompareTestCase {

	public void test() throws Exception {
		start("http://photography.nationalgeographic.com/photo-of-the-day/", Browser.FIREFOX);
		captureScreen().save("nationalgeographic-1.png");
		hideElement("headerboard");
		captureScreen().save("nationalgeographic-2.png");
		hideText();
		captureScreen().save("nationalgeographic-3.png");
		stop();
	}

}