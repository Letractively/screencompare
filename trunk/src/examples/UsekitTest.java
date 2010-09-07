import screencompare.*;
import screencompare.browser.*;
import screencompare.screen.*;
import screencompare.screen.Screen.Alignment;
import java.awt.Color;


public class UsekitTest extends ScreencompareTestCase {
	Screen reference;
	
	public void setUp() throws Exception {
		start(Browser.SAFARI);
		setBackground(Color.GREEN);
		usekitLoad();
		reference = captureScreen().mask(Color.GREEN);
		stop();
	}

	public void test() throws Exception {
		String[] urls = {"http://www.retoonline.com/",
						"http://www.heise.de/newsticker/classic/",
						"http://www.restaurantkelim.ch/"};
		
		for (String url:urls) {
			start(url, Browser.SAFARI);
			usekitLoad();
			verifySame(reference, captureScreen());
			stop();
		}
	}
	
	void usekitLoad() {
		js("var e=document.createElement('script');e.setAttribute('type','text/javascript');e.setAttribute('src','http://js.usekit.com/load.js');document.body.appendChild(e);void(0);");
		waitElementVisible("useKit_Mission_26");
	}

}