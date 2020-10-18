package algorithms;

import java.net.MalformedURLException;
import java.net.URL;

import inheritance.WebpageOpener;

public class YouTuber extends WebpageOpener{

	public void youtube(String arg) {

		try {
			openWebpage(new URL("https://www.youtube.com/search?q=" + arg));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}
	
}