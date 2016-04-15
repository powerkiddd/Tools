package Game;

import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import Main.Crash;
import Main.Input;
import Main.Mouse;

public class Build {
	
	public static String[] type;
	public static Timer buildtimer = new Timer();
	public static TimerTask update;
	public static boolean once = false;
	public static byte selected = 0;
	
	public static void Main(String[] args) {
		
	}
	
	public static void build () {
		if (selected != 0) {
			Rectangle CurrentMousePos = Mouse.Recalculate_Rect();
			CurrentMousePos.x += World2.camera_x;
			CurrentMousePos.y += World2.camera_y;
			for (int i = 0; i < World2.blockcollisions.length; i++) {
				if (CurrentMousePos.intersects(World2.blockcollisions[i])) {
					return;
				}
			}
			String pos = "";
			if (Input.snap) {
				if (World2.camera_x/25==Math.floor(World2.camera_x/25) && World2.camera_y/25==Math.floor(World2.camera_y/25)) {
					pos = "" + ((int) ((Math.floor(MouseInfo.getPointerInfo().getLocation().x/25)*25) - World2.f.getLocationOnScreen().x + World2.camera_x)) + "," + ((int) ((Math.floor(MouseInfo.getPointerInfo().getLocation().y/25))*25)-25 - World2.f.getLocationOnScreen().y + (int) World2.camera_y);
				}
				else {
					byte temp_x = (byte)Math.round((World2.camera_x/25));
					World2.final_x = (byte)(World2.camera_x-(25*temp_x));
					byte temp_y = (byte)Math.round((World2.camera_y/25));
					World2.final_y = (byte)(World2.camera_y-(25*temp_y));
					pos = "" + ((int) ((Math.floor(MouseInfo.getPointerInfo().getLocation().x/25)*25)-(int) World2.final_x) - World2.f.getLocationOnScreen().x + World2.camera_x) + "," + ((int) ((Math.floor(MouseInfo.getPointerInfo().getLocation().y/25)*25)-(int)World2.final_y)-25 - World2.f.getLocationOnScreen().y + (int) World2.camera_y);
				}
			}
			else {
				pos = "" + (short) (CurrentMousePos.x) + "," + "" + (short) (CurrentMousePos.y);
			}
			CurrentMousePos.x -= World2.camera_x;
			CurrentMousePos.y -= World2.camera_y;
			UpdateBlocks(Inventory.items[selected-1], pos, CurrentMousePos, false);
			Inventory.count[selected-1]--;
			if (Inventory.count[selected-1] == 0) {
				Inventory.items[selected-1] = "Empty";
				Inventory.slots[selected-1] = null;
			}
		}
	}
	
	public static void Place (String type, Rectangle location, boolean background) {
		String pos = "";
		if (Input.snap) {
			pos = "" + (short) (location.x) + "," + "" + location.y;
		}
		else {
			pos = "" + (short) (location.x) + "," + "" + location.y;
		}
		UpdateBlocks(type, pos, location, background);
	}
	
	public static void Interact (int number, boolean giveplayerblock, boolean background) {
		if (background) {
			if (World2.blocks[number].equals("rock") || World2.blocks[number].equals("rocks")) {
				Inventory.AddBlock("rock", (byte) 1);
				Build.Mine(number,false,background);
			}
			if (World2.blocks[number].equals("Tree")) {
				Inventory.AddBlock("wood", (byte) 4);
				Build.Mine(number,false,background);
			}
		}
	}
	
	public static void Mine (int number, boolean giveplayerblock, boolean background) {
		String[] blocks_;
		String[] blockposses_;
		Rectangle[] blockcollisions_;
		Boolean[] blockbackground_;
		blocks_ = World2.blocks.clone();
		blockposses_ = World2.blockposses.clone();
		blockcollisions_ = World2.blockcollisions.clone();
		blockbackground_ = World2.blockbackground.clone();
		if (giveplayerblock) {
			Inventory.AddBlock(blocks_[number], (byte) 1);
		}
		boolean foundnumber = false;
		//Reset Blocks, blockposses and blockcollisions
		World2.blocks = new String[World2.blocks.length-1];
		World2.blockposses = new String[World2.blockposses.length-1];
		World2.blockcollisions = new Rectangle[World2.blockcollisions.length-1];
		World2.blockbackground = new Boolean[World2.blockbackground.length-1];
		for (int i=0; i < blocks_.length; i++) {
			if (i != number) {
				if (foundnumber == false) {
					World2.blocks[i] = blocks_[i];
					World2.blockposses[i] = blockposses_[i];
					World2.blockcollisions[i] = blockcollisions_[i];
					World2.blockbackground[i] = blockbackground_[i];
				}
				else {
					World2.blocks[i-1] = blocks_[i];
					World2.blockposses[i-1] = blockposses_[i];
					World2.blockcollisions[i-1] = blockcollisions_[i];
					World2.blockbackground[i-1] = blockbackground_[i];
				}
			}
			else {
				foundnumber = true;
			}
		}
		foundnumber = false;
		Mouse.left = false;
	}
	
	public static void UpdateBlocks (String type, String pos, Rectangle MousePos, Boolean background) {
		//Clone current data
		String[] blocks_ = World2.blocks.clone();
		String[] blockposses_ = World2.blockposses.clone();
		Rectangle[] blockcollisions_ = World2.blockcollisions.clone();
		Boolean[] blockbackground_ = World2.blockbackground.clone();
		//Reassign block types & put in new one
		//Reassign block positions & put in new one
		//Reassign block collisions & put in new one
		World2.blocks = new String[World2.blocks.length+1];
		World2.blockposses = new String[World2.blockposses.length+1];
		World2.blockcollisions = new Rectangle[World2.blockcollisions.length+1];
		World2.blockbackground = new Boolean[World2.blockbackground.length+1];
		for (int i=0; i < blocks_.length; i++) {
			World2.blocks[i] = blocks_[i];
			World2.blockposses[i] = blockposses_[i];
			World2.blockcollisions[i] = blockcollisions_[i];
			World2.blockbackground[i] = blockbackground_[i];
		}
		World2.blocks[World2.blocks.length-1] = type;
		World2.blockposses[World2.blockposses.length-1] = pos;
		World2.blockbackground[World2.blockbackground.length-1] = background;
		if (selected != 0 && Inventory.slots[selected-1] != null) {
			//Get index from inventory
			World2.blockcollisions[World2.blockcollisions.length-1] = new Rectangle(MousePos.x+(int)Math.ceil(World2.camera_x),MousePos.y+(int)Math.ceil(World2.camera_y),Inventory.slots[selected-1].getWidth(),Inventory.slots[selected-1].getHeight());
		}
		else {
			//Get index from world block collection
			boolean found = false;
			for (int j = 0; j < World2.allblocks.length; j++) {
				if (type.equalsIgnoreCase(World2.blockidentifiers[j])) {
					World2.blockcollisions[World2.blockcollisions.length-1] = new Rectangle(MousePos.x+(int)Math.ceil(World2.camera_x),MousePos.y+(int)Math.ceil(World2.camera_y),World2.allblocks[j].getWidth(),World2.allblocks[j].getHeight());
					found = true;
					break;
				}
			}
			if (found == false) {
				Crash.cause = "A block was trying to load his image wich couldn't be found.\nIt might be missing in your images directory or read protected.\nOr the developer fucked up.\nBlock name: " + type;
			}
		}
	}
}
