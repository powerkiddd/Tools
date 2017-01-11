package Main;

import java.awt.Rectangle;

import Game.Player2;
import Game.World2;

public class Collision {
	
	static boolean acol = false;
	public static long starttime;
	public static long stoptime;
	
	//Returns nothing, tests player collision with blocks
	public static void testplayercol (int i) {
		starttime = System.currentTimeMillis();
		Rectangle temprect = new Rectangle();
		temprect = (Rectangle) World2.blockcollisions[i].clone();
		
		if (World2.blocks[i].equalsIgnoreCase("Water")) {
			if (Player2.playerrect.intersects(temprect)) {
				acol = true;
				Player2.overridespeed = true;
				Player2.playerspeed = 1;
				World2.NextFrame_Water = true;
				Player2.isInWater = true;
				stoptime = System.currentTimeMillis();
				return;
			}
			else if (World2.blocks.length-1 == i && Player2.isFalling == false && Player2.isJumping == false && acol == false) {
				Player2.collisiondown = false;
				Player2.isFalling = true;
				Player2.i = 102;
				Player2.momentum = 0.1f;
			}
			else if (World2.blocks.length-1 == i && acol == true) {
				acol = false;
			}
		}
		else if (Player2.playerrect.intersects(temprect)) {
			acol = true;
			Player2.collisionpos = temprect;
			//System.out.println("YPOS: " + temprect.getMaxY() + "|" + Player2.playerrect.getMaxY());
			if (Player2.playerrect.x < temprect.getCenterX() && Player2.playerrect.getMaxY()-70 <= temprect.getMaxY() && Player2.playerrect.getMaxY() >= temprect.getMinY()+5) {
				//Sta links van blok
				Player2.collision = "d";
			}
			else if (Player2.playerrect.x > temprect.getCenterX() && Player2.playerrect.getMaxY()-70 <= temprect.getMaxY() && Player2.playerrect.getMaxY() >= temprect.getMinY()+5) {
				//Sta rechts van blok
				Player2.collision = "a";
			}
			if (Player2.playerrect.getMaxY() < temprect.getMaxY() && Player2.playerrect.getMaxX() > temprect.getMinX() && Player2.playerrect.getMinX() < temprect.getMaxX()) {
				//Sta op blok
				Player2.collisiondown = true;
			}
			else if (Player2.playerrect.getMinY() + 4 > temprect.getMaxY()) {
				//Stoot je hoofd tegen blok
				Player2.collisiondown = false;
				//Player2.player_y += 4;
				Player2.i = 102;
				Player2.momentum = 0.1f;
			}
		}
		else if (World2.blocks.length-1 == i && Player2.isFalling == false && Player2.isJumping == false && acol == false) {
			Player2.collisiondown = false;
			Player2.isFalling = true;
			Player2.i = 102;
			Player2.momentum = 0.1f;
		}
		else if (World2.blocks.length-1 == i && acol == true) {
			acol = false;
		}
		stoptime = System.currentTimeMillis();
	}
	
	//Returns the other block number if there is a collision (blockId)
	public static int testblockcol (int i) {
		starttime = System.currentTimeMillis();
		Rectangle temprect = new Rectangle();
		Rectangle temprect2 = new Rectangle();
		temprect = (Rectangle) World2.blockcollisions[i].clone();
		//temprect.x -= (int) World2.camera_x;
		//temprect.y -= (int) World2.camera_y;
		
		for (int j = 0; j < World2.blockcollisions.length; j++) {
			if (j != i) {
				if (World2.blockbackground[j] == false) {
					if (Math.abs(temprect.x - World2.blockcollisions[j].x) < World2.blockcollisions[j].width && Math.abs(temprect.y - World2.blockcollisions[j].y) < World2.blockcollisions[j].height) {
						temprect2 = (Rectangle) World2.blockcollisions[j].clone();
						//temprect2.x -= (int) World2.camera_x;
						//temprect2.y -= (int) World2.camera_y;
						if (temprect.intersects(temprect2)) {
							stoptime = System.currentTimeMillis();
							return j;
						}
					}
				}
			}
		}
		stoptime = System.currentTimeMillis();
		
		return -1;
	}
	
	//Returns if collision on side has been detected (boolean)
	public static boolean testblockcolside (int i, String side, boolean forcesameheight) {
		starttime = System.currentTimeMillis();
		Rectangle temprect = new Rectangle();
		Rectangle temprect2 = new Rectangle();
		temprect = (Rectangle) World2.blockcollisions[i].clone();
		temprect.x -= (int) World2.camera_x;
		
		for (int j = 0; j < World2.blockcollisions.length; j++) {
			if (j != i && World2.blockbackground[j] == false) {
				temprect = (Rectangle) World2.blockcollisions[i].clone();
				temprect.x -= (int) World2.camera_x;
				temprect2 = (Rectangle) World2.blockcollisions[j].clone();
				temprect2.x -= (int) World2.camera_x;
				if (forcesameheight && temprect.y+25 > temprect2.y && temprect.y-25 < temprect2.y || !forcesameheight) {
					if (side == "Left") {
						temprect.x = temprect.x - 25;
						temprect.y = temprect.y - 1;
						if (temprect2.intersects(temprect)) {
							stoptime = System.currentTimeMillis();
							return true;
						}
					}
					else if (side == "Right") {
						temprect.x = temprect.x + 25;
						temprect.y = temprect.y - 1;
						if (temprect2.intersects(temprect)) {
							stoptime = System.currentTimeMillis();
							return true;
						}
					}
				}
			}
		}
		stoptime = System.currentTimeMillis();
		
		return false;
	}
	
	//Returns if the rain particle has collision with a block (boolean)
	public static boolean testraincol (int x, int y) {
		starttime = System.currentTimeMillis();
		Rectangle temprect = new Rectangle();
		Rectangle temprect2 = new Rectangle();
		temprect = new Rectangle(x,y,25,25);
		/*temprect.x -= (int) World2.camera_x;*/
		/*temprect.y -= (int) World2.camera_y;*/
		
		for (int j = 0; j < World2.blockcollisions.length; j++) {
			if (World2.blockbackground[j] == false) {
				if (Math.abs(temprect.x - (World2.blockcollisions[j].x - (int) World2.camera_x)) < World2.blockcollisions[j].width && Math.abs(temprect.y - (World2.blockcollisions[j].y - (int) World2.camera_y)) < World2.blockcollisions[j].height) {
					temprect2 = (Rectangle) World2.blockcollisions[j].clone();
					temprect2.x -= (int) World2.camera_x;
					temprect2.y -= (int) World2.camera_y;
					if (temprect.intersects(temprect2)) {
						stoptime = System.currentTimeMillis();
						return true;
					}
				}
			}
		}
		stoptime = System.currentTimeMillis();
		
		return false;
	}
	
}
