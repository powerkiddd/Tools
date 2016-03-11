package Game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import Main.Crash;

public class Player2 {
	
	public static BufferedImage right;
	public static BufferedImage left;
	public static float player_x = 0;
	public static float player_y = 0;
	//private static float staticplayer_y = 0;
	public static short i = 102;
	public static float momentum = 0;
	public static Byte playerspeed = 0;
	public static boolean atborder = false;
	public static boolean isFalling = true;
	public static boolean isJumping = false;
	public static boolean isInWater = false;
	public static Timer jumptimer = new Timer();
	public static TimerTask jump;
	public static Rectangle playerrect = new Rectangle(0,0,0,0);
	public static String collision;
	public static boolean collisiondown = false;
	public static Rectangle collisionpos;
	public static boolean overridespeed = false;
	
	public static void main(String[] args) {
		try {
			File file = new File("images\\player.png");
			right = ImageIO.read(file);
			File file2 = new File("images\\player_turned.png");
			left = ImageIO.read(file2);
		}
		catch (IOException ex) {
			ex.printStackTrace();
			Crash.cause = "Could not find the player images, are they read protected or are they missing?";
		}
		jump = new TimerTask () {
			public void run () {
				if (isFalling == true && collisiondown == false || isJumping == true) {
					if (i == 0) {
						//staticplayer_y = player_y;
						isFalling = false;
						isJumping = true;
					}
					else if (i < 100) {
						isFalling = false;
						isJumping = true;
						if (isInWater) {
							//i = 1;
							player_y-=0.1;
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
					/*else if (i > 100 && i < 200) {
						isFalling = true;
						isJumping = false;
						if (player_y < staticplayer_y) {
							player_y++;
						}
					}*/
					else if (collisiondown == false) {
						if (isInWater == false) {
							for (float j = 0; j < 10; j++) {
								player_y += momentum/10;
							}
							isFalling = true;
							isJumping = false;
							momentum += 0.01;
							if (player_y > World2.f.getHeight()) {
								player_y = World2.f.getSize().height-(World2.f.getSize().height/4)-67;
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
				else {
					isFalling = false;
					isJumping = false;
					momentum = 0;
				}
			}
		};
		Player2.jumptimer.scheduleAtFixedRate(Player2.jump, 10, 10);
	}
	
	public static BufferedImage changeimage(Byte type) {
		if (type == 0) {
			return right;
		}
		if (type == 1) {
			return left;
		}
		return null;
	}
}