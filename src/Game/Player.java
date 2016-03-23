package Game;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import Game.BlockStorage;

public class Player extends World {

	BufferedImage backtomainmenubutton;
	public static int x = 300;
	public static double y = 150;
	static double fallspeed = 0;
	static double xspeed = 0;
	static boolean pause = false;
	static boolean onblock = false;
	static boolean calledonblock = false;
	static boolean timerfinished = true;
	static Rectangle playerrect;
	
public static void Update () {
	playerrect = new Rectangle(x, (int) y, 25, 67);
	for (Rectangle grassrect : BlockStorage.grassblocks) {
		if (grassrect != null) {
			if (playerrect.intersects(grassrect)) {
				calledonblock = true;
				isonblock();
			}
			else{
				if (timerfinished == true) {
					timerfinished = false;
					new Timer().schedule(new TimerTask() {          
					    @Override
					    public void run() {
					    	isnotonblock();
					    }
					}, 100);
				}
			}
		}
	}
	if (onblock == false) {
		if (fallspeed < 0.1) {
			fallspeed += 0.0001;
		}
	}
	if (fallspeed > 0 && onblock == false) {
		y += fallspeed;
	}
	if (onblock == true) {
		fallspeed = 0;
	}
}

public static void move (KeyEvent e) {
	boolean derp = false;
	if (e.getKeyCode() == 83) {
		//System.out.println("DAT WAS NUTTELOOS");
	}
	if (e.getKeyCode() == 87) {
		if (fallspeed == 0) {
			boolean jump = true;
			//System.out.println("JUMP JUMP JUMP!");
		}
		else {
			//System.out.println("NOPE, YOU'RE FALLING!");
		}
	}
	if (e.getKeyCode() == 65) {
		if (xspeed > 0) {
			xspeed = 0;
		}
		if (xspeed > -1) {
			xspeed -= 0.2;
		}
		x += xspeed;
		//System.out.println("GO LEFT");
	}
	if (e.getKeyCode() == 68) {
		if (xspeed < 0) {
			xspeed = 0;
		}
		if (xspeed < 1) {
			xspeed += 0.2;
		}
		x += xspeed;
		//System.out.println("GO RIGHT");
	}
	if (e.getKeyCode() == 27) {
		if (pause == false) {
			pause = true;
			//System.out.println("GO PAUSEMENU");
		}
		else {
			pause = false;
		}
	}
}

public static void isonblock() {
	onblock = true;
}

public static void isnotonblock() {
	timerfinished = true;
	if (calledonblock == false) {
		onblock = false;
	}
	else {
		calledonblock = false;
	}
}

}