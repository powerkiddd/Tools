package Main;

import java.awt.Rectangle;

import Game.Player2;
import Game.World2;

public class Collision {
	
	static boolean acol = false;
	
	//Returns nothing, tests player collision with blocks
	public static void testplayercol (int i) {
		
		Rectangle temprect = new Rectangle();
		temprect = (Rectangle) World2.blockcollisions[i].clone();
		temprect.x -= (int) World2.camera_x;
		temprect.y -= (int) World2.camera_y;
		
		if (World2.blocks[i].equals("Water")) {
			if (Player2.playerrect.intersects(temprect)) {
				acol = true;
				Player2.overridespeed = true;
				Player2.playerspeed = 1;
				World2.NextFrame_Water = true;
				Player2.isInWater = true;
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
	}
	
	//Returns the other block number if there is a collision
	public static int testblockcol (int i) {
		Rectangle temprect = new Rectangle();
		Rectangle temprect2 = new Rectangle();
		temprect = (Rectangle) World2.blockcollisions[i].clone();
		temprect.x -= (int) World2.camera_x;
		
		for (int j = 0; j < World2.blockcollisions.length; j++) {
			if (j == i) {
				break;
			}
			temprect2 = (Rectangle) World2.blockcollisions[j].clone();
			temprect2.x -= (int) World2.camera_x;
			if (temprect.intersects(temprect2)) {
				return j;
			}
		}
		
		return -1;
	}
	
	//Returns if collision on side has been detected (boolean)
	public static boolean testblockcolside (int i, String side) {
		Rectangle temprect = new Rectangle();
		Rectangle temprect2 = new Rectangle();
		temprect = (Rectangle) World2.blockcollisions[i].clone();
		temprect.x -= (int) World2.camera_x;
		
		for (int j = 0; j < World2.blockcollisions.length; j++) {
			if (j != i) {
				temprect = (Rectangle) World2.blockcollisions[i].clone();
				temprect.x -= (int) World2.camera_x;
				temprect2 = (Rectangle) World2.blockcollisions[j].clone();
				temprect2.x -= (int) World2.camera_x;
				if (side == "Left") {
					temprect.x = temprect.x - 25;
					temprect.y = temprect.y - 1;
					if (temprect2.intersects(temprect)) {
						return true;
					}
				}
				else if (side == "Right") {
					temprect.x = temprect.x + 25;
					temprect.y = temprect.y - 1;
					if (temprect2.intersects(temprect)) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
}
