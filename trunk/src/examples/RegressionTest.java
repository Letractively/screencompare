import screencompare.*;
import screencompare.browser.*;
import screencompare.screen.*;
import screencompare.screen.Screen.Alignment;
import java.util.ArrayList;

public class RegressionTest extends ScreencompareTestCase {

	public void test() throws Exception {
		

		// Get references
		String[] urls_old = {"http://www.retokaiser.com/.old/Howtos/Reauthor DVD without menus.txt",
							"http://www.retokaiser.com/.old/Unterrichtsmaterialien Informatik/"};
		ArrayList<Screen> references = new ArrayList<Screen>();
		for (String url:urls_old) {
			start(url, Browser.SAFARI);
			references.add(captureScreen());
			stop();
		}
	
		// Compare with current
		String[] urls_new = {"http://www.retokaiser.com/Howtos/Reauthor DVD without menus.txt",
							"http://www.retokaiser.com/Unterrichtsmaterialien Informatik/"};
		int i=0;
		for (String url:urls_new) {
			start(url, Browser.SAFARI);
			verifySame(captureScreen(), references.get(i++));
			stop();
		}
	}

}