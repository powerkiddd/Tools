package Game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import Main.Collision;
import Main.Crash;
import Main.Input;
import Settings.Video_Settings;

public class Player2 {
	
	public static BufferedImage right;
	public static BufferedImage jetpack;
	public static float player_x = 0;
	public static float player_y = 0;
	public static short i = 102;
	public static float momentum = 0;
	public static Byte playerspeed = 0;
	public static boolean atborder = false;
	public static boolean isFalling = true;
	public static boolean isJumping = false;
	public static boolean isInWater = false;
	public static boolean hasJetpack = false;
	public static boolean lookingatside = true;
	public static Timer jumptimer = new Timer();
	public static TimerTask jump;
	public static Rectangle playerrect = new Rectangle(0,0,0,0);
	public static String collision;
	public static boolean collisiondown = false;
	public static Rectangle collisionpos;
	public static boolean overridespeed = false;
	public static byte health = 100;
	public static byte hunger = 100;
	public static byte thirst = 100;
	
	public static void initialize() {
		try {
			File file = new File("images\\player.png");
			right = ImageIO.read(file);
			File file2 = new File("images\\jetpack.png");
			jetpack = ImageIO.read(file2);
		}
		catch (IOException ex) {
			ex.printStackTrace();
			Crash.cause = "Could not find the player images, are they read protected or are they missing?";
		}
		jump = new TimerTask () {
			public void run () {
				World2.newCamera_y = player_y - Video_Settings.window_size_y/2;
				if (isFalling == true && collisiondown == false && World2.buildingworld == false && hasJetpack == false || isJumping == true && hasJetpack == false) {
					if (i == 0) {
						isFalling = false;
						isJumping = true;
					}
					else if (i < 100) {
						isFalling = false;
						isJumping = true;
						collisiondown = false;
						if (isInWater) {
							player_y -= 0.1;
						}
						else {
							player_y--;
						}
						momentum = 0;
					}
					else if (i == 101) {
						if (isInWater == false) {
							player_y += momentum;
							momentum = -1;
						}
						else {
							player_y -= 0.1;
						}
					}
					else if (collisiondown == false) {
						if (isInWater == false) {
							for (float j = 0; j < 10; j++) {
								player_y += momentum/10;
							}
							isFalling = true;
							isJumping = false;
							momentum += 0.01;
							if (player_y > World2.world_y) { //Player is deaded
								player_y = -67;
								player_x = 0;
								momentum = 0;
							}
						}
						else {
							player_y -= 0.1;
						}
					}
					else {
						i = 0;
						isFalling = false;
						momentum = 0;
					}
					i++;
				}
				else if (hasJetpack == true) {
					if (Input.space) {
						if (World2.canplayermovey) {
							player_y -= momentum;
							if (momentum < 3) {
								momentum += 0.1;
							}
						}
						else {
							World2.newCamera_y -= momentum;
							if (momentum < 3) {
								momentum += 0.1;
							}
						}
					}
					else if (collisiondown == false) {
						if (World2.canplayermovey) {
							player_y += momentum/10;
							momentum += 0.01;
						}
						else {
							World2.newCamera_y += momentum/10;
							momentum += 0.01;
						}
					}
					else {
						momentum = 0;
					}
				}
				else {
					isFalling = false;
					isJumping = false;
					momentum = 0;
				}
			}
		};
		Player2.jumptimer.scheduleAtFixedRate(Player2.jump, 10, 10);
	}
	
	public static void changeside(Byte type) {
		if (type == 0) {
			lookingatside = true;
		}
		else if (type == 1) {
			lookingatside = false;
		}
	}
}