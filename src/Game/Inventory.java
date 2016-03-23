package Game;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import Main.Crash;
import Main.Directory;

public class Inventory {
	
	public static BufferedImage[] slots = new BufferedImage[9];
	public static String[] items = {"Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty"};
	public static byte[] count = {0, 0, 0, 0, 0, 0, 0, 0, 0};
	public static BufferedImage[] blocks;
	public static String[] identifier;
	
	public static void main(String[] args) {
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
		AddBlock("Water", (byte) 127);
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
			}
			else {
				if (new String(items[i].toLowerCase().toString()).equals(block.toLowerCase().toString())) {
					if (count[i] < 127) {
						count[i] += amount;
						return;
					}
				}
			}
		}
	}
	
}
