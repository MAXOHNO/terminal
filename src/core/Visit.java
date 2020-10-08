package core;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class Visit {

	URL fileURL;
	File config;
	Scanner scan;
	Scanner scan2count;

	String[] configContent;

	int linesOfFile;

	public Visit() {
		linesOfFile = 0;
		config = new File("src/core/config.cfg");
		try {
			scan = new Scanner(config);
			scan2count = new Scanner(config);
		} catch (FileNotFoundException e) {
			System.out.println("no config.cfg found");
			e.printStackTrace();
		}

		// Die Länge des Arrays configContent wird so lang gemacht, wie die Anzahl der
		// Zeilen der config.cfg
		// Neuer scanner ist dafür benötigt, weil der gleiche Scanner nicht wieder von
		// der 1. Zeile anfangen kann zu lesen?
		// Das verusacht probleme später beim executen
		while (scan2count.hasNextLine()) {
			linesOfFile++;
			scan2count.nextLine();
		}

		configContent = new String[linesOfFile];

	}

	public void loadConfig() {

		int i = 0;
		while (scan.hasNextLine()) {
			configContent[i] = scan.nextLine();
			i++;
		}
		System.out.println("Loaded Config");
	}

	public void visit(String arg) {

		loadConfig();

		for (int i = 0; i < configContent.length; i++) {

			String[] splitted = configContent[i].split(" ");

			if (splitted[1].contains(arg)) {
				if (splitted[0].contains("visit")) {
					System.out.println(splitted[2]);

					// Visiting the website
					try {
						if (splitted.length == 3) {
							// Process p = Runtime.getRuntime().exec(splitted[2]);
							if (splitted[2].startsWith("https://")) {
								openWebpage(new URL(splitted[2]));
							} else {
								openWebpage(new URL("https://" + splitted[2]));
							}
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				// Schleife wird gestoppt
				i = 999;
			} else {
				if (i == configContent.length - 1) {
					try {
						if (arg.startsWith("https://")) {
							openWebpage(new URL(arg));
						} else {
							openWebpage(new URL("https://" + arg));
						}
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
				}
			}

		}

		/*
		 * while(!foundCommand) { if (scan.hasNextLine()) { String[] splitted =
		 * scan.nextLine().split(" "); if (splitted[1].contains(arg)) {
		 * System.out.println(scan.nextLine()); foundCommand = true; return; } } else {
		 * foundCommand = true; System.out.println("Command not found lolxdxdxd");
		 * return; } }
		 */
	}

	// Credits: https://stackoverflow.com/questions/10967451/open-a-link-in-browser-with-java-button
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
