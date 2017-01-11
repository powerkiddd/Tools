package Game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import Main.Crash;
import Main.SFX;

public class Weather {
	
	public static boolean isRaining = false;
	public static Random chance = new Random();
	
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
			World2.RainX.clear();
			World2.RainY.clear();
		} catch (Exception ex) {
			Crash.cause = "Could not clear weather arrays: Rain";
			ex.printStackTrace();
		}
	}
	
	/**
	 * Calculates chance for weather clearance and rain spawns.
	 */
	public static void rainChance () {
		if (Weather.isRaining) {
			if (chance.nextInt(10000) == 1000) {
				Weather.Clear();
			}
			if (chance.nextInt(10) == 0) {
				for (int j = 0; j < World2.f.getSize().width; j += chance.nextInt(1000)) {
					if (j == 0 && chance.nextInt(100) == 0) {
						World2.RainX.add(j);
						World2.RainY.add(0);
					}
					else if (j != 0) {
						World2.RainX.add(j);
						World2.RainY.add(0);
					}
				}
			}
		}
		else {
			if (chance.nextInt(10000) == 1000) {
				Weather.Rain();
			}
		}
	}
}
