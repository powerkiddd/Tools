package Game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Main.Crash;
import Main.SFX;

public class Weather {
	
	public static boolean isRaining = false;
	
	public static void Rain () { //Rain down the bombardment upon our foes! Let them dine in hell!
		try {
			isRaining = true;
			File file1 = new File("images\\skybox_rain.jpg");
			World2.image = ImageIO.read(file1);
			SFX.PlayRain();
		} catch (IOException e) {
			Crash.cause = "Could not load weather type: Rain";
			e.printStackTrace();
		}
	}
	
	//It's a beautiful day isn't it? The birds are singing, the flowers are blooming...
	public static void Clear () {
		try {
			File file1 = new File("images\\skybox.jpg");
			World2.image = ImageIO.read(file1);
			isRaining = false;
			SFX.Stop();
		} catch (IOException e) {
			Crash.cause = "Could not load weather type: Clear";
			e.printStackTrace();
		}
		try {
			World2.RainX = new ArrayList<Integer>();
			World2.RainY = new ArrayList<Integer>();
		} catch (Exception ex) {
			Crash.cause = "Could not clear weather arrays: Rain";
			ex.printStackTrace();
		}
	}
}
