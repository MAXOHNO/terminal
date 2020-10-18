package algorithms;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import core.Terminal;

public class Adder {
	
	File config;
	
	
	
	public Adder() {
		
		config = new File(Terminal.configPATH);
		
	}
	
	public void addCommand(String cmd) {
					
			try {
				
				FileWriter fw = new FileWriter(config, true);
				
				fw.append(System.lineSeparator() + cmd);

				fw.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

}
