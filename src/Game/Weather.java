package Game;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.Crash;

public class Weather {
	
	public static boolean isRaining = false;
	
	public static void Rain () { //Rain down the bombardment upon our foes! Let them dine in hell!
		try {
			isRaining = true;
			File file1 = new File("images\\skybox_rain.jpg");
			World2.image = ImageIO.read(file1);
		} catch (IOException e) {
			Crash.cause = "Could not load weather type: Rain";
			e.printStackTrace();
		}
	}
	
	//It's a beautiful day isn't it? The birds are singing, the flowers are blooming...
	public static void Clear () {
		isRaining = false;
	}
}
