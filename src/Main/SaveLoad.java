package Main;

import java.awt.Rectangle;
import java.io.File;

import Game.World2;

public class SaveLoad {
	
	public static String worldname;
	
	public static void SaveGame () {
		//Save all the information
		Directory.CreateNewDirectory(worldname);
		String blocks = "";
		String blockposses = "";
		String blockcollisions = "";
		String blockbackground = "";
		for (int i = 0; i < World2.blocks.length; i++) {
			blocks = blocks + World2.blocks[i] + ",";
			blockposses = blockposses + World2.blockposses[i] + ":";
			blockcollisions = blockcollisions + World2.blockcollisions[i].x + "," + World2.blockcollisions[i].y + "," + World2.blockcollisions[i].width + "," + World2.blockcollisions[i].height + ":";
			blockbackground = blockbackground + World2.blockbackground[i] + ",";
		}
		WriteToFile.writestuff(worldname+ "\\", "blocks", ".tool", blocks);
		WriteToFile.writestuff(worldname+ "\\", "blockposses", ".tool", blockposses);
		WriteToFile.writestuff(worldname+ "\\", "blockcollisions", ".tool", blockcollisions);
		WriteToFile.writestuff(worldname+ "\\", "blockbackground", ".tool", blockbackground);
	}
	
	public static void LoadGame () {
		//Load all the information
		String blocks = ReadFromFile.readstuff(worldname + "\\", "blocks.tool", "");
		String blockposses = ReadFromFile.readstuff(worldname + "\\", "blockposses.tool", "");
		String blockcollisions = ReadFromFile.readstuff(worldname + "\\", "blockcollisions.tool", "");
		String blockbackground = ReadFromFile.readstuff(worldname + "\\", "blockbackground.tool", "");
		String[] blocks_split = blocks.split(",");
		String[] blockposses_split = blockposses.split(":");
		String[] blockcollisions_split1 = blockcollisions.split(":");
		String[] blockbackground_split = blockbackground.split(",");
		World2.blocks = new String[blocks_split.length];
		World2.blockposses = new String[blocks_split.length];
		World2.blockcollisions = new Rectangle[blocks_split.length];
		World2.blockbackground = new Boolean[blocks_split.length];
		for (int i = 0; i < blocks_split.length; i++) {
			World2.blocks[i] = blocks_split[i];
			World2.blockposses[i] = blockposses_split[i];
			String[] blockcollisions_split = blockcollisions_split1[i].split(",");
			World2.blockcollisions[i] = new Rectangle(Integer.parseInt(blockcollisions_split[0]),Integer.parseInt(blockcollisions_split[1]),Integer.parseInt(blockcollisions_split[2]),Integer.parseInt(blockcollisions_split[3]));
			World2.blockbackground[i] = Boolean.parseBoolean(blockbackground_split[i]);
		}
	}
	
	public static boolean DoesSaveExist () {
		return new File(worldname + "\\").exists();
	}
	
}
