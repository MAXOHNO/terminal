package algorithms;

import java.net.MalformedURLException;
import java.net.URL;

import inheritance.WebpageOpener;

public class Wikipedia extends WebpageOpener{

	public void wikiSearch(String arg) {

		try {
			openWebpage(new URL("http://en.wikipedia.org/w/index.php?search=" + arg));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}
	
}