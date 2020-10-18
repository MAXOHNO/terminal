package algorithms;

import java.net.MalformedURLException;
import java.net.URL;

import inheritance.WebpageOpener;

public class FaceitStats extends WebpageOpener{

	URL fileURL;

	public void getStats(String arg) {

		try {
			openWebpage(new URL("https://faceitstats.com/player," + arg));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}
	
}