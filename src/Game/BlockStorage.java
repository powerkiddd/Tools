package Game;

import java.awt.Rectangle;

import Blocks.GrassBlock;

public class BlockStorage {
	
	public static int totalblocks = 0;
	public static int totalgrassblocks = 0;
	public static Rectangle[] grassblocks;
	
	public static void Store () {
		totalblocks = 0;
		totalblocks += GrassBlock.totalblockzcount;
		totalgrassblocks = GrassBlock.totalblockzcount;
		grassblocks = new Rectangle[totalgrassblocks];
		grassblocks = GrassBlock.everyblock;
	}

}
