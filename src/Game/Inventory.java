package Game;

import java.awt.image.BufferedImage;

import Main.Directory;
import Main.Input;

public class Inventory {
	
	public static byte selected = -1;
	public static short craftingselected = -1;
	public static BufferedImage[] slots = new BufferedImage[9];
	public static String[] items = {"Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty"};
	public static byte[] count = {0, 0, 0, 0, 0, 0, 0, 0, 0};
	public static byte[] itemtype = {0, 0, 0, 0, 0, 0, 0, 0, 0}; //None, Block, Tool, Material
	public static BufferedImage[] blocks;
	public static BufferedImage[] tools;
	public static BufferedImage[] items2;
	public static String[] identifier;
	public static String[] toolidentifier;
	public static String[] items2identifier;
	
	public static void main(String[] args) {
		slots = new BufferedImage[45];
		items = new String[45];
		count = new byte[45];
		itemtype = new byte[45];
		for (byte i = 0; i < 45; i++) {
			items[i] = "Empty";
			count[i] = 0;
			itemtype[i] = 0;
		}
		tools = Directory.GetAllImagesFromDirectory("images\\tools\\");
		toolidentifier = Directory.identifiers;
		blocks = Directory.GetAllImagesFromDirectory("images\\blocks\\");
		identifier = Directory.identifiers;
		items2 = Directory.GetAllImagesFromDirectory("images\\items\\");
		items2identifier = Directory.identifiers;
		
		AddItem("Dirt", (byte) 127, (byte) 1);
		AddItem("Grass", (byte) 127, (byte) 1);
		AddItem("Water", (byte) 127, (byte) 1);
		AddItem("Stone", (byte) 127, (byte) 1);
	}
	
	public static boolean AddItem (String name, byte amount, byte type) {
		if (type == 0) {
			//System.out.println("INVALID TYPE");
			return false;
		}
		for (int i = 0; i < 45; i++) {
			if (items[i].equals("Empty")) {
				if (type == 1) {
					for (int j = 0; j < identifier.length; j++) {
						if (identifier[j] != null) {
							if (identifier[j].equalsIgnoreCase(name)) {
								slots[i] = blocks[j];
								items[i] = name;
								count[i] = amount;
								itemtype[i] = 1;
								return true;
							}
						}
					}
				}
				else if (type == 2) {
					for (int j = 0; j < toolidentifier.length; j++) {
						if (toolidentifier[j] != null) {
							if (toolidentifier[j].equalsIgnoreCase(name)) {
								slots[i] = tools[j];
								items[i] = name;
								count[i] = 1;
								itemtype[i] = 2;
								return true;
							}
						}
					}
				}
				else if (type == 3) {
					for (int j = 0; j < items2identifier.length; j++) {
						if (items2identifier[j] != null) {
							if (items2identifier[j].equalsIgnoreCase(name)) {
								slots[i] = items2[j];
								items[i] = name;
								count[i] = amount;
								itemtype[i] = 3;
								return true;
							}
						}
					}
				}
			}
			else {
				if (type == 1) {
					if (items[i].equalsIgnoreCase(name)) {
						if (count[i] < 127) {
							int total = count[i] + amount;
							if (total <= 127) {
								count[i] += amount;
								return true;
							}
							else {
								amount = (byte) (amount-(amount-count[i]));
								count[i] = 127;
							}
						}
					}
				}
				else if (type == 2) {
					if (items[i].equalsIgnoreCase(name)) {
						if (name.equalsIgnoreCase("Stick")) {
							if (count[i] < 127) {
								int total = count[i] + amount;
								if (total <= 127) {
									count[i] += amount;
									return true;
								}
								else {
									amount = (byte) (amount-(amount-count[i]));
									count[i] = 127;
								}
							}
						}
					}
				}
			}
		}
		//Block/Item/Tool has not been picked up
		System.out.println("Item: " + name + ", not picked up!");
		return false;
	}
	
	public static int GetItemID (String name, byte type) {
		if (type == 0) {
			return 0;
		}
		else if (type == 1) {
			for (int i = 0; i < identifier.length-1; i++) {
				if (identifier[i].equalsIgnoreCase(name)) {
					return i;
				}
			}
		}
		else if (type == 2) {
			for (int i = 0; i < toolidentifier.length-1; i++) {
				if (toolidentifier[i].equalsIgnoreCase(name)) {
					return i;
				}
			}
		}
		else if (type == 3) {
			for (int i = 0; i < items2identifier.length-1; i++) {
				if (items2identifier[i].equalsIgnoreCase(name)) {
					return i;
				}
			}
		}
		return 0;
	}
	
	public static boolean IsInventoryFull (boolean DoesCountApply) {
		for (int i = 0; i < items.length; i++) {
			if (DoesCountApply) {
				if (count[i] < 127) {
					return false;
				}
			}
			if (items[i].equalsIgnoreCase("Empty")) {
				return false;
			}
		}
		return true;
	}
}
