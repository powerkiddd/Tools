package Game;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TestPlayer extends TestWorld {

	BufferedImage player;
	static int x = 300;
	static double y = 150;
	static double fallspeed = 0;
	static double xspeed = 0;
	
public void paintComponent(Graphics g) {
	super.paintComponents(g);
	g.drawString("This is the current player. DEAL WITH IT!", x - 100, (int) (y - 5));
	g.drawImage(player, x, (int) y, null);
	y += fallspeed;
	fallspeed += 0.05;
	try {
		File file = new File("images\\player.png");
		player = ImageIO.read(file);
		}
		catch (IOException ex) {
			//je weet zeluf
	}
}

public static void move (KeyEvent e) {
	boolean derp = false;
	if (e.getKeyCode() == 83) {
		System.out.println("DAT WAS NUTTELOOS");
	}
	if (e.getKeyCode() == 87) {
		if (fallspeed == 0) {
			boolean jump = true;
			System.out.println("JUMP JUMP JUMP!");
		}
		else {
			System.out.println("NOPE, YOU'RE FALLING!");
		}
	}
	if (e.getKeyCode() == 65) {
		if (xspeed > 0) {
			xspeed = 0;
		}
		xspeed -= 0.2;
		x += xspeed;
		System.out.println("GO LEFT");
	}
	if (e.getKeyCode() == 68) {
		if (xspeed < 0) {
			xspeed = 0;
		}
		xspeed += 0.2;
		x += xspeed;
		System.out.println("GO RIGHT");
	}
}

}