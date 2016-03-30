package Game;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class Music {
	
	public static void main(String[] args) {
		try {
			if (Settings.Settings.music) {
				File path = new File("music/");
				if (!path.exists()) {
					Settings.Settings.music = false;
					Settings.Settings.Save();
					return;
				}
				File yourFile = new File("music/action1.wav");
			    AudioInputStream stream;
			    AudioFormat format;
			    DataLine.Info info;
			    Clip clip;
				
			    stream = AudioSystem.getAudioInputStream(yourFile);
		    	format = stream.getFormat();
		    	info = new DataLine.Info(Clip.class, format);
		    	clip = (Clip) AudioSystem.getLine(info);
		    	clip.open(stream);
		    	clip.start();
			}
		}
		catch (Exception ex) {
			System.out.println("Could not start music! + " + ex.getStackTrace() + "," + ex.getMessage());
		}
	}
	
	public static void GameOver() {
		try {
			//if (Settings.Settings.music) {
				File path = new File("music/");
				if (!path.exists()) {
					Settings.Settings.music = false;
					Settings.Settings.Save();
					return;
				}
				File yourFile = new File("music/GameOver.wav");
			    AudioInputStream stream;
			    AudioFormat format;
			    DataLine.Info info;
			    Clip clip;
	
			    stream = AudioSystem.getAudioInputStream(yourFile);
			    format = stream.getFormat();
			    info = new DataLine.Info(Clip.class, format);
			    clip = (Clip) AudioSystem.getLine(info);
			    clip.open(stream);
			    clip.start();
			//}
		}
		catch (Exception ex) {
			System.out.println("Could not start music! + " + ex.getStackTrace() + "," + ex.getMessage());
		}
	}
	
}
