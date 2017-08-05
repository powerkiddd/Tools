package Main;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.Build;
import Game.Inventory;
import Game.World2;

public class Mouse {
	
	public static BufferedImage gamecursor;
	public static boolean right = false;
	public static boolean left = false;
	public static boolean leftonce = false;
	public static boolean rightonce = false;
	public static Rectangle gamecursorrect;
	public static int mouseposonscreenx;
	public static int mouseposonscreeny;
	public static int mouseposinworldx;
	public static int mouseposinworldy;
	public static Point mousePos;
	
	public static void main(String [] args) {
		World2.f.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == 1) {
					//left
					gamecursorrect = Recalculate_Rect();
					left = true;
				}
				if (e.getButton() == 3) {
					//right
					gamecursorrect = Recalculate_Rect();
					right = true;
					if (Build.selected-1 >= 0) {
						if (Inventory.items[Build.selected-1] != "Empty") {
							Build.build();
						}
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == 1) {
					//left
					left = false;
					leftonce = false;
				}
				if (e.getButton() == 3) {
					//right
					right = false;
					rightonce = false;
				}
			}
			
		});
		try{
			File file = new File("images\\gamecursor.png");
			gamecursor = ImageIO.read(file);
		} catch (IOException ex) {
			//You Errored
		}
	}
	
	public static Rectangle Recalculate_Rect () {
		if (Input.snap) {
			mousePos = World2.f.getMousePosition();
			if (mousePos != null) {
				int temp_x = (int) (mousePos.x + World2.camera_x);
				World2.final_x = Math.round(temp_x/25)*25;
				
				int temp_y = (int) (mousePos.y + World2.camera_y);
				World2.final_y = Math.round((temp_y-50)/25)*25;
				
				return new Rectangle(World2.final_x, World2.final_y, 25, 25);
			}
			else {
				return null;
			}
		}
		else {
			return new Rectangle(MouseInfo.getPointerInfo().getLocation().x - 13 - World2.f.getLocationOnScreen().x,MouseInfo.getPointerInfo().getLocation().y - 37 - World2.f.getLocationOnScreen().y,25,25);
		}
	}
}
