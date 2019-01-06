package Main;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class SFX {
	//Currently only supports 1 channel, so only 1 SFX at a time.
	private static AudioInputStream stream;
	private static AudioFormat format;
    private static DataLine.Info info;
    private static Clip clip;
    
	public static void initialize() {
		//Hey, we have nothing to initialize!
	}
	
	public static void PlayRain () {
		try {
		File path = new File("SFX/");
		if (!path.exists()) {
			Settings.Settings.sfx = false;
			Settings.Settings.Save();
			return;
		}
		File yourFile = new File("sfx/Rain_sfx.wav");

	    stream = AudioSystem.getAudioInputStream(yourFile);
	    format = stream.getFormat();
	    info = new DataLine.Info(Clip.class, format);
	    clip = (Clip) AudioSystem.getLine(info);
	    clip.open(stream);
	    clip.loop(666);
		} catch (Exception ex) {
			Crash.cause = "Could not play SFX for: Rain";
			ex.printStackTrace();
		}
	}
	
	public static void Stop () {
		clip.stop();
	}
}
