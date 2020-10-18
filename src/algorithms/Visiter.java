package algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import core.Terminal;
import inheritance.WebpageOpener;

public class Visiter extends WebpageOpener {

	File config;
	Scanner scan;
	Scanner scan2count;

	String[] configContent;

	int linesOfFile;

	public Visiter() {
		linesOfFile = 0;
		config = new File(Terminal.configPATH);
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
		linesOfFile = 0;
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

	public void visitWebpage(String arg) {

		loadConfig();

		for (int i = 0; i < configContent.length; i++) {
			System.out.println("inside");
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

	}
}
