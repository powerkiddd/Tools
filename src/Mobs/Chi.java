package Mobs;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import Game.Player2;
import Main.Input;
import Settings.Video_Settings;

public class Chi {
	
	public static float chix = 0;
	public static float chiy = Video_Settings.window_size_y-Video_Settings.window_size_y/4-67;
	public static float chispeed = (float) 0.3;
	private static Timer update_ = new Timer();
	public static BufferedImage image;
	public static BufferedImage right;
	public static BufferedImage left;
	
	public static void main(String[] args) {
		if (Settings.Settings.chi == true) {
		try {
			File file = new File("images\\chi.png");
			image = ImageIO.read(file);
			right = ImageIO.read(file);
			File file2 = new File("images\\chi_turned.png");
			left = ImageIO.read(file2);
		}
		catch (IOException ex) {
			ex.getStackTrace();
		}
		TimerTask update = new TimerTask () {
			public void run () {
				if (Player2.player_x+10 > chix) {
					if (Player2.atborder == true) {
						if (Input.a) {
							chispeed = (float) 3;
						}
						else {
							chispeed = (float) 0.3;
						}
					}
					else {
						chispeed = (float) 0.3;
					}
					//walk right towards player
					image = right;
					chix += chispeed;
				}
				if (Player2.player_x-10 < chix) {
					if (Player2.atborder == true) {
						if (Input.d) {
							chispeed = (float) 3;
						}
						else {
							chispeed = (float) 0.3;
						}
					}
					else {
						chispeed = (float) 0.3;
					}
					//walk left towards player
					image = left;
					chix -= chispeed;
				}
			}
		};
		update_.scheduleAtFixedRate(update, 10, 10);
		}
	}
}
