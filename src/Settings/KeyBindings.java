package Settings;

import java.io.File;

import Main.ReadFromFile;
import Main.WriteToFile;

public class KeyBindings {
	public static String walkleft = "a";
	public static String walkright = "d";
	public static String inventory = "i";
	public static String snap = "Ctrl";
	public static String run = "Shift";
	public static String jump = "Space";
	
	public static void main(String[] args) {
		File file = new File("settings/KeyBindings.ini");
		if (!file.exists()) {
			Save();
		}
		else {
			String derp = ReadFromFile.readstuff("settings", "KeyBindings.ini", "");
			derp = derp.replaceAll("\\s+", "");
			String[] derps = derp.split(":");
			try {
				walkleft = derps[1];
				walkright = derps[3];
				inventory = derps[5];
				snap = derps[7];
				run = derps[9];
				jump = derps[11];
			}
			catch (Exception ex) {
				ex.printStackTrace();
				Corrupt();
			}
		}
	}
	
	public static void Corrupt () {
		File file = new File("settings/KeyBindings.ini");
		file.delete();
		System.out.println("KeyBindings config corrupted! Creating new config file.");
		Save();
	}
	
	public static void Save () {
		try {
			String text = "";
			text = text + "walkleft: " + walkleft + ":\n";
			text = text + "walkright: " + walkright + ":\n";
			text = text + "inventory: " + inventory + ":\n";
			text = text + "snap: " + snap + ":\n";
			text = text + "run: " + run + ":\n";
			text = text + "jump: " + jump + ":";
			WriteToFile.writestuff("settings/", "KeyBindings", ".ini", text);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}