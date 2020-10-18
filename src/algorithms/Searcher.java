package algorithms;

import java.net.MalformedURLException;
import java.net.URL;

import inheritance.WebpageOpener;

public class Searcher extends WebpageOpener {

	URL fileURL;

	public void searchGoogle(String arg) {

		try {
			openWebpage(new URL("https://www.google.com/search?q=" + arg));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}
}