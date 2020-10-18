package inheritance;

import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class WebpageOpener {
	
	// Credits:
		// https://stackoverflow.com/questions/10967451/open-a-link-in-browser-with-java-button
		public static boolean openWebpage(URI uri) {
			Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
			if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
				try {
					desktop.browse(uri);
					return true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return false;
		}

		public static boolean openWebpage(URL url) {
			try {
				return openWebpage(url.toURI());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return false;
		}

}
