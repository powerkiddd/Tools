package Main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Lighting {

	public static BufferedImage[] pixels;
	public static Color[] colors;
	public static BufferedImage light = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
	public static Color daynight;
	public static BufferedImage newlight;
	public static Color torch;
	public static int quality = 80;
	
	public static void main(String[] args) {
		try {
			File file = new File("images\\light.png");
			light = ImageIO.read(file);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(0);
		}
		
		daynight = new Color(255,255,255,60);
		torch = new Color(255,255,255,0);
	}
	
}
