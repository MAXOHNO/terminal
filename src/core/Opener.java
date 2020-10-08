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

public class Opener {

	URL fileURL;
	File config;
	Scanner scan;
	Scanner scan2count;

	String[] configContent;

	int linesOfFile;

	public Opener() {
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

	public void open(String arg) {

		loadConfig();

		for (int i = 0; i < configContent.length; i++) {

			String[] splitted = configContent[i].split(" ");

			if (splitted[1].contains(arg)) {
				if (splitted[0].contains("open")) {
					System.out.println(splitted[2]);

					if (splitted.length == 3) {
						openLocation(splitted[2]);
					} else if (splitted.length == 4) {
						openLocation(splitted[2] + " " + splitted[3]);
					} else if (splitted.length == 5) {
						openLocation(splitted[2] + " " + splitted[3] + " " + splitted[4]);
					}

				}

				// Schleife wird gestoppt
				i = 999;
			}

		}

	}

	public void openLocation(String path) {
		File location = new File(path);
		try {
			Desktop.getDesktop().open(location);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
