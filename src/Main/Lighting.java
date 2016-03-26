package Main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import Settings.Video_Settings;

public class Lighting {

	public static BufferedImage[] pixels;
	public static Color[] colors;
	public static BufferedImage light = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
	public static BufferedImage newlight;
	public static int quality = 80;
	
	public static void main(String[] args) {
		try {
			File file = new File("images\\light.png");
			light = ImageIO.read(file);
		} catch (Exception ex) {
			
		}
		//Fill pixels with standard info
		pixels = new BufferedImage[(Video_Settings.window_size_x/quality) * (Video_Settings.window_size_y/quality)];
		colors = new Color[(Video_Settings.window_size_x/quality) * (Video_Settings.window_size_y/quality)];
		for (int i = 0; i < Video_Settings.window_size_y/quality; i++) {
			for (int j = 0; j < Video_Settings.window_size_x/quality; j++) {
				newlight = light;
				Color c = new Color(255,255,255,40);
				colors[i*j] = c;
				pixels[i*j] = light;
			}
		}
	}
	
}
