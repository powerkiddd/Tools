package Settings;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import Main.ReadFromFile;
import Main.WriteToFile;

public class Video_Settings {
	
	public static int window_size_x = 640;
	public static int window_size_y = 400;
	public static int mapsize = 2400;
	public static GraphicsDevice gs;
	public static GraphicsEnvironment ge;
	public static GraphicsDevice[] gd;
	public static boolean VSync = false;
	public static int framelimit = 120;

	public static void main(String [] args) throws IOException {	
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		gs = ge.getDefaultScreenDevice();
		gd = ge.getScreenDevices();
		File file = new File("settings/video_settings.ini");
		if (!file.exists()) {
			WriteToFile.writestuff("settings/", "video_settings" ,".ini", "window_size_x:" + window_size_x + ":\n" + "window_size_y:" + window_size_y + ":\n" + "mapsize:" + mapsize + ":");
		}
		else{
			try {
				/*BufferedReader reader = new BufferedReader(new FileReader(file));
				String derp = "";
				String notderp = "";
				while ((notderp = reader.readLine()) != null) {
					derp = derp + "" + notderp;
				}
				reader.close();
				*///This code is legendary! Because it looks very derpy ;) I'll keep this.
				String derp = ReadFromFile.readstuff("settings", "video_settings.ini", "");
				derp = derp.replaceAll("\\s+", "");
				String[] derps = derp.split(":");
				try {
					window_size_x = Integer.parseInt(derps[1]);
					window_size_y = Integer.parseInt(derps[3]);
					mapsize = Integer.parseInt(derps[5]);
					VSync = Boolean.parseBoolean(derps[7]);
					framelimit = Integer.parseInt(derps[9]);
				}
				catch(NumberFormatException e2){
					System.out.println("NumberFormatException in config file.");
					corrupt();
				}
				catch (ArrayIndexOutOfBoundsException e3) {
					System.out.println("ArrayIndexOutOfBoundsException in config file.");
					corrupt();
				}
			} catch (Exception ex) {
				System.out.println("Unknown Exception in config file.");
				corrupt();
			}
		}
	}
	
	public static void corrupt () {
		File file = new File("settings/video_settings.ini");
		file.delete();
		System.out.println("Config file corrupted, creating new config file.");
		createstandard();
	}
	
	public static void createstandard () {
		WriteToFile.writestuff("settings/", "video_settings", ".ini", "Window_size_x:640:\nWindow_size_y:400:\nMapsize:2400:\nUseFramelimit:false:\nFramelimit:120:");
	}
	
	public static void SaveConfig () {
		File file = new File("settings/video_settings.ini");
		file.delete();
		WriteToFile.writestuff("settings/", "video_settings", ".ini", "Window_size_x:"+ window_size_x +":\nWindow_size_y:"+ window_size_y +":\nMapsize:"+ mapsize +":\nUseFramelimit:"+ VSync + ":\nFramelimit:" + framelimit + ":");
	}
	
}