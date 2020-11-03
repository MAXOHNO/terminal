package core;

public class Terminal {
	
	// TODO: Beim executer, opener, visiter etc. muss die config immer reloaded werden
	// TODO: sonst werden neue geaddete commands nicht erkannt
	
	static String version = "1.4";
	static String password = "unlock";
	static boolean unlocked = false;
	
	public static String configPATH = "src/core/config.cfg";
	public static String loggerPATH = "src/core/logger.cfg";
	
	public static String soundType = "sounds/type.wav";
	public static String soundRemove = "sounds/remove.wav";
	public static String soundChange = "sounds/change.wav";
	public static String soundConfirm = "sounds/change.wav";

	public static void main(String[] args) {

		//GUI gui = new GUI(1200, 800);
		GUI gui = new GUI(1080, 808);
		gui.changeTitle("Bats' Terminal " + version);

	}
	
	public static String getConfigPath() {
		return configPATH;
	}
	
	public static String getLoggerPath() {
		return loggerPATH;
	}
	
	public static String getSoundType() {
		return soundType;
	}
	
	public static String getSoundRemove() {
		return soundRemove;
	}
	
	public static String getSoundChange() {
		return soundChange;
	}
	
	public static String getSoundConfirm() {
		return soundConfirm;
	}
	
}
