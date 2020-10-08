package core;

public class Terminal {
	
	static String version = "1.1";

	public static void main(String[] args) {

		GUI gui = new GUI(1200, 800);
		gui.changeTitle("Bats' Terminal " + version);

	}

}
