package Blocks;

import java.awt.Rectangle;

import Game.World;
import Settings.Video_Settings;


public class GrassBlock extends World {
	
	public static int[] x;
	public static int[] y;
	public static int[] x2;
	public static int[] y2;
	public static int[] totalblockz;
	public static int totalblockzcount = 0;
	public static int totalblockzcountcount = 1;
	public static int count = 0;
	public static int shouldcreate = 0;
	public static boolean xinrange = true;
	public static boolean anyinxrange = false;
	public static boolean anyinyrange = false;
	public static boolean anyinrange = false;
	public static boolean busy = false;
	public static Rectangle[] everyblock;
	
	public static void test() {
	totalblockzcount++;
	x = new int[totalblockzcount];
	y = new int[totalblockzcount];
	x2 = new int[totalblockzcount];
	y2 = new int[totalblockzcount];
	x[0] = 0;
	y[0] = 250;
	x2[0] = 0;
	y2[0] = 250;
	//x[1] = 325;
	//y[1] = 275;
	System.out.println("public static void test in grassblock");
	World.cangenerategrass = true;
	shouldcreate = Video_Settings.window_size_x / 25;
	}
	
	public static void Update() {
		everyblock = new Rectangle[totalblockzcount];
		everyblock[totalblockzcountcount - 1] = new Rectangle(x[totalblockzcountcount - 1], y[totalblockzcountcount - 1], 25, 25);
		if (totalblockzcountcount != totalblockzcount) {
			totalblockzcountcount++;
		}
		else {
			totalblockzcountcount = 1;
		}
	}
	
	public static void Generate() {
		if (totalblockzcount < shouldcreate + 1) {
			totalblockzcount++;
			x = new int[totalblockzcount];
			y = new int[totalblockzcount];
			for (int x3 : x2) {
				x[count] = x2[count];
				count++;
			}
			count = 0;
			for (int y3 : y2) {
				y[count] = y2[count];
				count++;
			}
			count = 0;
			x[totalblockzcount - 1] = x[totalblockzcount - 2] + 25;
			y[totalblockzcount - 1] = 250;
			x2 = new int[totalblockzcount];
			y2 = new int[totalblockzcount];
			for (int x4 : x) {
				x2[count] = x[count];
				count++;
			}
			count = 0;
			for (int y3 : y2) {
				y2[count] = y[count];
				count++;
			}
			count = 0;
		}
	}
	
}
