package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Execute {

	URL fileURL;
	File config;
	Scanner scan;
	Scanner scan2count;

	String[] configContent;
	
	int linesOfFile;

	public Execute() {
		linesOfFile = 0;
		config = new File("src/core/config.cfg");
		try {
			scan = new Scanner(config);
			scan2count = new Scanner(config);
		} catch (FileNotFoundException e) {
			System.out.println("no config.cfg found");
			e.printStackTrace();
		}
		

		// Die Länge des Arrays configContent wird so lang gemacht, wie die Anzahl der Zeilen der config.cfg
		// Neuer scanner ist dafür benötigt, weil der gleiche Scanner nicht wieder von der 1. Zeile anfangen kann zu lesen?
		// Das verusacht probleme später beim executen
		while(scan2count.hasNextLine()) {
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
				if(splitted[0].contains("execute")) {
					System.out.println(splitted[2]);
					
					// Starting the excecutable
					try {
						if (splitted.length == 3) {
							Process p = Runtime.getRuntime().exec(splitted[2]);
						} else if (splitted.length == 4) {
							Process p = Runtime.getRuntime().exec(splitted[2] + " " + splitted[3]);
						} else if (splitted.length == 5) {
							Process p = Runtime.getRuntime().exec(splitted[2] + " " + splitted[3] + " " + splitted[4]);
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

		/*
		 * while(!foundCommand) { if (scan.hasNextLine()) { String[] splitted =
		 * scan.nextLine().split(" "); if (splitted[1].contains(arg)) {
		 * System.out.println(scan.nextLine()); foundCommand = true; return; } } else {
		 * foundCommand = true; System.out.println("Command not found lolxdxdxd");
		 * return; } }
		 */
	}
}
