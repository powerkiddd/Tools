package Game;

import java.awt.image.BufferedImage;

import Main.Directory;

public class Inventory {
	
	public static BufferedImage[] slots = new BufferedImage[9];
	public static String[] items = {"Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty"};
	public static byte[] count = {0, 0, 0, 0, 0, 0, 0, 0, 0};
	public static BufferedImage[] blocks;
	public static BufferedImage[] tools;
	public static String[] identifier;
	public static String[] toolidentifier;
	
	public static void main(String[] args) {
		slots = new BufferedImage[45];
		items = new String[45];
		count = new byte[45];
		for (byte i = 0; i < 45; i++) {
			items[i] = "Empty";
			count[i] = 0;
		}
		tools = Directory.GetAllImagesFromDirectory("images\\tools\\");
		toolidentifier = Directory.identifiers;
		blocks = Directory.GetAllImagesFromDirectory("images\\blocks\\");
		identifier = Directory.identifiers;
		
		AddBlock("Dirt", (byte) 127);
		AddBlock("Grass", (byte) 127);
		AddBlock("Water", (byte) 127);
		AddBlock("Stone", (byte) 127);
	}
	
	public static void AddBlock (String block, byte amount) {
		for (int i = 0; i < 45; i++) {
			if (items[i] == "Empty") {
				for (int j = 0; j < identifier.length; j++) {
					if (identifier[j].equalsIgnoreCase(block)) {
						slots[i] = blocks[j];
						items[i] = block;
						count[i] = amount;
						return;
					}
				}
			}
			else {
				if (new String(items[i].toLowerCase().toString()).equals(block.toLowerCase().toString())) {
					if (count[i] < 127) {
						int total = count[i] + amount;
						if (total <= 127) {
							count[i] += amount;
							return;
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
