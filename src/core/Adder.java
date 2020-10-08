package core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Adder {
	
	File config;
	FileWriter writer;
	
	// TODO: Neuen FileWriter machen, dieser kann nur einmal schreiben, danach closed er sich selber und ist unnutzbar.
	
	public Adder() {
		
		config = new File("src/core/config.cfg");
		try {
			writer = new FileWriter(config, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addCommand(String cmd) {
		try {
			
			writer.write(System.lineSeparator() + cmd);
			
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
