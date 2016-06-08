package Settings;

import java.io.File;

import Main.ReadFromFile;
import Main.WriteToFile;

public class Settings {
	public static boolean fullscreen = false;
	public static boolean chi = false;
	public static boolean music = true;
	public static boolean sfx = true;
	
	public static void main(String[] args) {
		File file = new File("settings/Settings.ini");
		if (!file.exists()) {
			Save();
		}
		else {
			String derp = ReadFromFile.readstuff("settings", "Settings.ini", "");
			derp = derp.replaceAll("\\s+", "");
			String[] derps = derp.split(":");
			try {
				fullscreen = Boolean.parseBoolean(derps[1]);
				chi = Boolean.parseBoolean(derps[3]);
				music = Boolean.parseBoolean(derps[5]);
				sfx = Boolean.parseBoolean(derps[7]);
			}
			catch (Exception ex) {
				ex.printStackTrace();
				Corrupt();
			}
		}
	}
	
	public static void Changed () {
		Save();
	}
	
	public static void Corrupt () {
		File file = new File("settings/Settings.ini");
		file.delete();
		System.out.println("Settings config corrupted! Creating new config file.");
		Save();
	}
	
	public static void Save () {
		try {
			String text = "";
			text = text + "fullscreen: " + fullscreen + ":\n";
			text = text + "chi: " + chi + ":\n";
			text = text + "music: " + music + ":\n";
			text = text + "sfx: " + sfx + ":";
			WriteToFile.writestuff("settings/", "Settings", ".ini", text);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}