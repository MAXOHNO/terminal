package algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import core.Terminal;

public class Executer {

	URL fileURL;
	File config;
	Scanner scan;
	Scanner scan2count;

	String[] configContent;

	int linesOfFile;

	public Executer() {
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

	public void execute(String arg) {

		loadConfig();

		for (int i = 0; i < configContent.length; i++) {

			String[] splitted = configContent[i].split(" ");

			if (splitted[1].contains(arg)) {
				if (splitted[0].contains("execute")) {

					// Starting the excecutable
					try {                                                                                                                            
						if (splitted.length == 3) {
							Runtime.getRuntime().exec(splitted[2]);
						} else if (splitted.length == 4) {
							Runtime.getRuntime().exec(splitted[2] + " " + splitted[3]);
						} else if (splitted.length == 5) {
							Runtime.getRuntime().exec(splitted[2] + " " + splitted[3] + " " + splitted[4]);
						} else if (splitted.length == 6) {
							Runtime.getRuntime().exec(splitted[2] + " " + splitted[3] + " " + splitted[4] + " " + splitted[5]);
						} else if (splitted.length == 7) {
							Runtime.getRuntime().exec(splitted[2] + " " + splitted[3] + " " + splitted[4] + " " + splitted[5] + " " + splitted[6]);
						} else if (splitted.length == 8) {
							Runtime.getRuntime().exec(splitted[2] + " " + splitted[3] + " " + splitted[4] + " " + splitted[5] + " " + splitted[6] + " " + splitted[7]);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				// Schleife wird gestoppt
				i = 999;
			} else {
			}

		}

	}
}
