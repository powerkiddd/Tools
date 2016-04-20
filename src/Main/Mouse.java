package Main;

import java.awt.MouseInfo;
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
			if (World2.camera_x/25==Math.floor(World2.camera_x/25) && World2.camera_y/25==Math.floor(World2.camera_y/25)) {
				return new Rectangle((int) ((Math.floor(MouseInfo.getPointerInfo().getLocation().x/25)*25) - World2.f.getLocationOnScreen().x), (int) ((Math.floor(MouseInfo.getPointerInfo().getLocation().y/25))*25)-25 - World2.f.getLocationOnScreen().y, 25,25);
			}
			else {
				byte temp_x = (byte)Math.round((World2.camera_x/25));
				World2.final_x = (byte)(World2.camera_x-(25*temp_x));
				byte temp_y = (byte)Math.round((World2.camera_y/25));
				World2.final_y = (byte)(World2.camera_y-(25*temp_y));
				World2.final_y = (byte) (World2.final_y + 25);
				return new Rectangle((int) ((Math.floor(MouseInfo.getPointerInfo().getLocation().x/25)*25)-(int) World2.final_x) - World2.f.getLocationOnScreen().x, (int) ((Math.floor(MouseInfo.getPointerInfo().getLocation().y/25)*25)-(int) World2.final_y) - World2.f.getLocationOnScreen().y, 25,25);
			}
		}
		else {
			return new Rectangle(MouseInfo.getPointerInfo().getLocation().x - 13 - World2.f.getLocationOnScreen().x,MouseInfo.getPointerInfo().getLocation().y - 37 - World2.f.getLocationOnScreen().y,25,25);
		}
	}
}
