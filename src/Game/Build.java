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
	
	public static void setSelected (byte select) {
		selected = select;
		for (int i = 0; i < World2.allblocks.length; i++) {
			if (selected != 0) {
				if (World2.blockidentifiers[i].equalsIgnoreCase(Inventory.items[selected-1])) {
					World2.theblock = i;
				}
			}
		}
	}
	
	public static void build () {
		if (selected != 0) {
			Rectangle CurrentMousePos = Mouse.Recalculate_Rect();
			if (CurrentMousePos != null) {
				
				//Make sure we are not building on another block
				for (int i = 0; i < World2.blockcollisions.length; i++) {
					if (CurrentMousePos.intersects(World2.blockcollisions[i])) {
						return;
					}
				}
				
				String pos = "" + (short) CurrentMousePos.x + "," + (short) CurrentMousePos.y;
				CurrentMousePos.width = 0;
				CurrentMousePos.height = 0;
				UpdateBlocks(Inventory.items[selected-1], pos, CurrentMousePos, false);
				Inventory.count[selected-1]--;
				if (Inventory.count[selected-1] == 0) {
					Inventory.items[selected-1] = "Empty";
					Inventory.slots[selected-1] = null;
				}
			}
			else {
				System.out.println("CurrentMousePos = null in Build.java");
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
			if (Build.selected-1 < 0) {
				if (Recipes.CheckRecipe(World2.blocks[number], "Empty")) {
					Build.Mine(number, false, background);
				}
			}
			else {
				if (Recipes.CheckRecipe(World2.blocks[number], Inventory.items[Build.selected-1])) {
					Build.Mine(number, false, background);
				}
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
			Inventory.AddItem(blocks_[number], (byte) 1, (byte) 1);
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
		if (MousePos.width == 0 || MousePos.height == 0) {
			if (selected != 0 && Inventory.slots[selected-1] != null) {
				//Get index from inventory
				World2.blockcollisions[World2.blockcollisions.length-1] = new Rectangle(MousePos.x,MousePos.y,Inventory.slots[selected-1].getWidth(),Inventory.slots[selected-1].getHeight());
			}
			else {
				//Get index from world block collection
				boolean found = false;
				for (int j = 0; j < World2.allblocks.length; j++) {
					if (type.equalsIgnoreCase(World2.blockidentifiers[j])) {
						World2.blockcollisions[World2.blockcollisions.length-1] = new Rectangle(MousePos.x,MousePos.y,World2.allblocks[j].getWidth(),World2.allblocks[j].getHeight());
						found = true;
						break;
					}
				}
				if (found == false) {
					Crash.cause = "A block was trying to load his image wich couldn't be found.\nIt might be missing in your images directory or read protected.\nOr the developer fucked up.\nBlock name: " + type;
				}
			}
		}
		else {
			World2.blockcollisions[World2.blockcollisions.length-1] = new Rectangle(MousePos.x,MousePos.y,MousePos.width,MousePos.height);
		}
	}
}
