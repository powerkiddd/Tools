package Game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Settings.Video_Settings;

public class PauseMenu {

	public static boolean paused = false;
	public static BufferedImage mainmenubutton;
	public static Rectangle mainmenubuttonrect;
	
	public static void main(String [] args) {
		try {
			File file = new File("images\\mainmenu_button.png");
			mainmenubutton = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void PauseChanged () {
		paused = Player.pause;
		mainmenubuttonrect = new Rectangle(Video_Settings.window_size_x / 2 - 110, 100,200,60);
	}
	
}
