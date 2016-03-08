package Game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.Crash;
import Main.Directory;

public class Inventory {
	
	public static BufferedImage[] slots = new BufferedImage[9];
	public static String[] items = {"Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty"};
	public static byte[] count = {0, 0, 0, 0, 0, 0, 0, 0, 0};
	public static BufferedImage[] blocks;
	public static String[] identifier;
	
	/*private static BufferedImage dirt;
	private static BufferedImage grass;
	private static BufferedImage water;
	private static BufferedImage stone;*/
	
	public static void main(String[] args) {
		/*try {
			File fdirt = new File("images\\dirt.png");
			File fgrass = new File("images\\grass.png");
			File fwater = new File("images\\water.png");
			File fstone = new File("images\\stone.png");
			grass = ImageIO.read(fgrass);
			dirt = ImageIO.read(fdirt);
			water = ImageIO.read(fwater);
			stone = ImageIO.read(fstone);
		} catch (IOException ex) {
			ex.printStackTrace();
		}*/
		String[] allfiles = Directory.GetAllFilesFromDirectory("images\\blocks\\");
		blocks = new BufferedImage[allfiles.length-1];
		identifier = new String[allfiles.length-1];
		try {
			for (int i = 1; i < allfiles.length; i++) {
				File newfile = new File("images\\blocks\\"+allfiles[i].substring(14));
				blocks[i-1] = ImageIO.read(newfile);
				String[] split = allfiles[i].substring(14).split("\\.");
				identifier[i-1] = split[0];
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			Crash.cause = "Wut... Why did this crash? The only way it couuuuuld crash. Is if someone removed the folder blocks in images, OR all the images inside it.";
		}
		
		AddBlock("Dirt", (byte) 127);
		AddBlock("Grass", (byte) 127);
		AddBlock("Water", (byte) 20);
		AddBlock("Stone", (byte) 127);
	}
	
	public static void AddBlock (String block, byte amount) {
		for (int i = 0; i < 9; i++) {
			if (items[i] == "Empty") {
				for (int j = 0; j < identifier.length; j++) {
					if (identifier[j].equalsIgnoreCase(block)) {
						slots[i] = blocks[j];
						items[i] = block;
						count[i] = amount;
						return;
					}
				}
				/*switch (block) {
					case "Dirt":
						//slots[i] = dirt;
						items[i] = "Dirt";
						count[i] = amount;
						return;
					case "Grass":
						//slots[i] = grass;
						items[i] = "Grass";
						count[i] = amount;
						return;
					case "Water":
						//slots[i] = water;
						items[i] = "Water";
						count[i] = amount;
						return;
					case "Stone":
						//slots[i] = stone;
						items[i] = "Stone";
						count[i] = amount;
					default:
						//default
						return;
				}*/
			}
			else {
				if (new String(items[i].toLowerCase().toString()).equals(block.toLowerCase().toString())) {
					count[i] += amount;
					return;
				}
			}
		}
	}
	
}
