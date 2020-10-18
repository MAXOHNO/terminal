package algorithms;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import core.Terminal;

public class Logger {
	
	File logger;
	
	// TODO: Neuen FileWriter machen, dieser kann nur einmal schreiben, danach closed er sich selber und ist unnutzbar.
	
	public Logger() {
		
		logger = new File(Terminal.loggerPATH);
		try {
			logger.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addLog(String cmd) {
					
			try {
				
				FileWriter fw = new FileWriter(logger, true);
				
				fw.append(System.lineSeparator() + cmd);

				fw.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

}
