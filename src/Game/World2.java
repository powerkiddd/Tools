package Game;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Main.Collision;
import Main.Crash;
import Main.Directory;
import Main.Input;
import Main.Mouse;
import Main.Version;
import Mobs.Chi;
import Settings.Settings;
import Settings.Video_Settings;

public class World2 extends JPanel {
	
	private static BufferedImage image;
	private static BufferedImage background_land;
	public static int backgroundx = 640;
	public static int backgroundy = 400;
	private static BufferedImage playerimage;
	public static BufferedImage[] allblocks;
	public static String[] blockidentifiers; //Names of blocks must align to pictures
	private static BufferedImage grassblock;
	private static BufferedImage dirtblock;
	private static BufferedImage tree;
	private static BufferedImage invslot;
	private static BufferedImage collider;
	private static BufferedImage collider2;
	private static BufferedImage collider3;
	private static BufferedImage blockholder;
	private static BufferedImage grid;
	private static int world_x = 2400;
	public static boolean debug = false;
	public static boolean debuggrid = false;
	public static float camera_x = 0;
	public static String[] blocks = new String[0];
	public static String[] blockposses = new String[0];
	public static Rectangle[] blockcollisions = new Rectangle[0];
	public static String[] blockinfo = new String[0];
	public static JFrame f = new JFrame();
	public static JPanel p = new JPanel();
	public static Timer frametimer = new Timer();
	public static Timer updatetimer = new Timer();
	public static int FPS = 0;
	public static int lastFPS = 0;
	public static boolean once = false;
	public static Runtime runtime = Runtime.getRuntime();
	public static long maxmemory = runtime.maxMemory();
	public static long allocatedmemory = runtime.totalMemory();
	public static long freememory = runtime.freeMemory();
	public static byte processors = (byte) runtime.availableProcessors();
	public static byte final_x;
	public static boolean NextFrame_Water = false;
	public static byte hasitcrashed = 0;
	
	public static void main(String[] args) {
		Player2.playerspeed = 3;
		try {
			Video_Settings.main(null);
			Settings.main(null);
			Music.main(null);
			Chi.main(null);
			Input.main(null);
			Player2.main(null);
			Build.Main(null);
			Inventory.main(null);
			//pausemenu.main(null);
			Mouse.main(null);
		} catch (IOException e) {
			e.printStackTrace();
			Crash.cause = "Could not start one of the main classes. Have you tempered with the jar file?";
		}
		world_x = Video_Settings.mapsize;
		try {
			String[] allfiles = Directory.GetAllFilesFromDirectory("images\\blocks\\");
			allblocks = new BufferedImage[allfiles.length-1];
			blockidentifiers = new String[allfiles.length-1];
			for (int i = 1; i < allfiles.length; i++) {
				File newfile = new File("images\\blocks\\"+allfiles[i].substring(14));
				allblocks[i-1] = ImageIO.read(newfile);
				String[] split = allfiles[i].substring(14).split("\\.");
				blockidentifiers[i-1] = split[0];
			}
			File file = new File("images\\skybox.jpg");
			File file2 = new File("images\\player.png");
			File file3 = new File("images\\blocks\\grass.png");
			File file4 = new File("images\\blocks\\dirt.png");
			File file5 = new File("images\\inventory_slot.png");
			File file6 = new File("images\\Collider.png");
			File file7 = new File("images\\Collider2.png");
			File file8 = new File("images\\BlockHolder.png");
			File file9 = new File("images\\Grid.png");
			File file10 = new File("images\\Collider3.png");
			File file11 = new File("images\\blocks\\Tree.png");
			File file12 = new File("images\\background_land.png");
			image = ImageIO.read(file);
			playerimage = ImageIO.read(file2);
			grassblock = ImageIO.read(file3);
			dirtblock = ImageIO.read(file4);
			invslot = ImageIO.read(file5);
			collider = ImageIO.read(file6);
			collider2 = ImageIO.read(file7);
			blockholder = ImageIO.read(file8);
			grid = ImageIO.read(file9);
			collider3 = ImageIO.read(file10);
			tree = ImageIO.read(file11);
			background_land = ImageIO.read(file12);
		}
		catch (IOException ex) {
			ex.printStackTrace();
			Crash.cause = "Could not find one of the images. Are they read protected or are there files missing?";
		}
		f.setSize(Video_Settings.window_size_x, Video_Settings.window_size_y);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.add(new World2());
		f.setTitle("Tools " + Version.version + " [FPS: " + lastFPS + "]");
		Runtime.getRuntime().gc();
		if (Settings.fullscreen == true) {
			f.setExtendedState(Frame.MAXIMIZED_BOTH);
			f.setVisible(true);
			//Video_Settings.gs.setFullScreenWindow(f);
		}
		Chi.chiy = f.getSize().height-f.getSize().height/4-67;
		Player2.player_y = f.getSize().height-(f.getSize().height/4)-67;
		System.out.println("This is currently beta " + Version.version);
		TimerTask updateFPS = new TimerTask() {
			public void run () {
				maxmemory = runtime.maxMemory();
				allocatedmemory = runtime.totalMemory();
				freememory = runtime.freeMemory();
				lastFPS = FPS;
				if (lastFPS == 0) {
					hasitcrashed++;
					if (hasitcrashed == 5) {
						//Close game, show crash warning
						Crash.main(null);
						System.exit(-1);
					}
				}
				else {
					hasitcrashed = 0;
				}
				f.setTitle("Tools " + Version.version + " [FPS: " + lastFPS + "]");
				FPS = 0;
			}
		};
		TimerTask update = new TimerTask () {
			public void run () {
				Player2.playerrect = new Rectangle((int) Player2.player_x,(short) Player2.player_y,19,67);
				//Sprint if left shift is pressed
				if (Input.GetInput("shift")) {
					if (Player2.overridespeed == false) {
						Player2.playerspeed = 5;
					}
				}
				else {
					if (Player2.overridespeed == false) {
						Player2.playerspeed = 3;
					}
				}
				//Move left if a is pressed
				if (Input.GetInput("a") && Player2.collision != "a") {
					if (once == false) {
						playerimage = Player2.changeimage((byte) 1);
						once = true;
					}
					if (camera_x > 0 && Player2.player_x < (0+Video_Settings.window_size_x/3)) {
						for (byte i = 0; i < 10; i++) {
							camera_x -= (float)Player2.playerspeed/10;
						}
						Player2.atborder = true;
						if (camera_x < 0) {
							camera_x = 0;
						}
						//System.out.println("GO LEFT");
					}
					else {
						Player2.atborder = false;
						for (byte i = 0; i < 10; i++) {
							Player2.player_x -= (float)Player2.playerspeed/10;
						}
					}
				}
				else {
					once = false;
				}
				//move right if d is pressed
				if (Input.GetInput("d") && Player2.collision != "d") {
					if (once == false) {
						playerimage = Player2.changeimage((byte) 0);
						once = true;
					}
					if (Player2.player_x > Video_Settings.window_size_x-Video_Settings.window_size_x/3 && camera_x < world_x) {
						for (byte i = 0; i < 10; i++) {
							camera_x += (float)Player2.playerspeed/10;
						}
						Player2.atborder = true;
						if (camera_x > world_x) {
							camera_x = world_x;
						}
						//System.out.println("GO RIGHT");
					}
					else {
						Player2.atborder = false;
						for (byte i = 0; i < 10; i++) {
							Player2.player_x += (float)Player2.playerspeed/10;
						}
					}
				}
				else {
					once = false;
				}
				if (Input.GetInput("space")) {
					if (Player2.isJumping == false && Player2.isFalling == false) {
						Player2.isJumping = true;
					}
				}
			}
		};
		frametimer.scheduleAtFixedRate(updateFPS, 1000, 1000);
		updatetimer.scheduleAtFixedRate(update, 10, 10);
		for (int i = 75; i < world_x; i++) {
			Random rnd = new Random();
			int kaas = rnd.nextInt(400);
			if (kaas == 200) {
				Build.Place("Tree", new Rectangle(i,f.getSize().height-f.getSize().height/4-175,75,175));
			}
		}
		for (int i = 0; i < world_x; i += 25) {
			Build.Place("Grass", new Rectangle(i,f.getSize().height-f.getSize().height/4, 25 , 25));
			for (int j = f.getSize().height-f.getSize().height/4+25; j < f.getSize().height-25; j += 25) {
				Build.Place("Dirt", new Rectangle(i,j, 25 , 25));
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		NextFrame_Water = false;
		FPS++;
		super.paintComponent(g);
		//Draw Background
		for (int i=0; i < f.getSize().height; i += backgroundy) {
			for (int j=0; j < world_x+f.getSize().width; j += backgroundx) {
				g.drawImage(image, (int) ((int)j-camera_x), i, backgroundx, backgroundy,null);
			}
		}
		for (int i = 0; i < world_x+f.getSize().width; i += 640) {
			g.drawImage(background_land, (int) ((int)i-camera_x), f.getSize().height-f.getSize().height/4-380, 640, 400,null);
		}
		//Draw world
		//g.drawImage(grassblock, 0,f.getSize().height-f.getSize().height/4, f.getSize().width , 25, null);
		//g.drawImage(dirtblock, 0,f.getSize().height-f.getSize().height/4+25, f.getSize().width , f.getSize().height/4-25, null);
		//Draw Blocks
		for (int i=0; i < blocks.length; i++) {
			String pos = blockposses[i];
			String[] xy = pos.split(",");
			short x = Short.parseShort(xy[0]);
			int y = Integer.parseInt(xy[1]);
			boolean removeblock = false;
			for (int j = 0; j < allblocks.length; j++) {
				if (blockidentifiers[j].equalsIgnoreCase(blocks[i])) {
					g.drawImage(allblocks[j], (int) ((int) x-camera_x), y, blockcollisions[i].width, blockcollisions[i].height, null);
					break;
				}
			}
			if (blocks[i] == "Dirt") {
				//g.drawImage(dirtblock, (int) ((int) x-camera_x), y, 25, 25, null);
			}
			else if (blocks[i] == "Grass") {
				//g.drawImage(grassblock, (int) ((int) x-camera_x), y, 25, 25, null);
			}
			else if (blocks[i] == "Water") {
				//g.drawImage(waterblock, (int) ((int) x-camera_x), y, blockcollisions[i].width, blockcollisions[i].height, null);
				if (y < World2.f.getSize().height - World2.f.getSize().height/4 - 25) {
					int hascolwith = Collision.testblockcol(i);
					if (hascolwith == -1) {
						blockposses[i] = "" + x + "," + (y+1);
						blockcollisions[i] = new Rectangle(x,y+1,25,25);
					}
					else if (blocks[hascolwith] != "Water") {
						if (blocks[hascolwith] == "Grass" || blocks[hascolwith] == "Dirt") {
							if (blockcollisions[i].height > 1) {
								blockposses[i] = "" + (x-1) + "," + (y+1);
								blockcollisions[i] = new Rectangle(x,y,blockcollisions[i].width+2,blockcollisions[i].height-1);
							}
							else {
								blockcollisions[i] = new Rectangle(x+25,y,25,25);
								if (!Collision.testblockcolside(i, "Left")) {
									//spawn water block left
									Build.Place("Water", new Rectangle(blockcollisions[i].x-25,blockcollisions[i].y,25,25));
								}
								if (!Collision.testblockcolside(i, "Right")) {
									//spawn water block right
									Build.Place("Water", new Rectangle(blockcollisions[i].x+25,blockcollisions[i].y,25,25));
								}
								removeblock = true;
							}
						}
						else {
							/*if (BlockInfo.GetBlockInfo(i) == null) {
								BlockInfo.SetBlockInfo(i, new String[]{"false"});
							}*/
							Random rand = new Random();
							byte finalside = (byte) rand.nextInt(2);
							boolean left = false;
							boolean right = false;
							if (!Collision.testblockcolside(i, "Left")) {
								//spawn water block left
								//Build.Place("Water", new Rectangle(blockcollisions[i].x-25,blockcollisions[i].y,25,25));
								removeblock = true;
								left = true;
							}
							if (!Collision.testblockcolside(i, "Right")) {
								//spawn water block right
								//Build.Place("Water", new Rectangle(blockcollisions[i].x+25,blockcollisions[i].y,25,25));
								removeblock = true;
								right = true;
							}
							if (left == true) {
								if (right == true) {
									if (finalside == 0) {
										//if (BlockInfo.GetBlockInfo(i)[1].equals("false")) {
											Build.Place("Water", new Rectangle(blockcollisions[i].x-25,blockcollisions[i].y,25,25));
											//BlockInfo.ReplaceBlockInfo(i, "false", "true");
										//}
									}
									else {
										//if (BlockInfo.GetBlockInfo(i)[1].equals("false")) {
											Build.Place("Water", new Rectangle(blockcollisions[i].x+25,blockcollisions[i].y,25,25));
											//BlockInfo.ReplaceBlockInfo(i, "false", "true");
										//}
									}
								}
								else {
									//if (BlockInfo.GetBlockInfo(i)[1].equals("false")) {
										Build.Place("Water", new Rectangle(blockcollisions[i].x-25,blockcollisions[i].y,25,25));
										//BlockInfo.ReplaceBlockInfo(i, "false", "true");
									//}
								}
							}
							else if (right == true) {
								//if (BlockInfo.GetBlockInfo(i)[1].equals("false")) {
									Build.Place("Water", new Rectangle(blockcollisions[i].x+25,blockcollisions[i].y,25,25));
									//BlockInfo.ReplaceBlockInfo(i, "false", "true");
								//}
							}
						}
					}
				}
				else {
					if (blockcollisions[i].height > 1) {
						blockposses[i] = "" + (x-1) + "," + (y+1);
						blockcollisions[i] = new Rectangle(x,y,blockcollisions[i].width+2,blockcollisions[i].height-1);
					}
					else {
						removeblock = true;
					}
				}
			}
			else if (blocks[i] == "Stone") {
				//g.drawImage(stoneblock, (int) ((int) x-camera_x), y, 25, 25, null);
			}
			Collision.testplayercol(i);
			Rectangle temprect = new Rectangle();
			temprect = (Rectangle) blockcollisions[i].clone();
			if (temprect.intersects(Player2.playerrect)) {
				Collision.testplayercol(i);
			}
			else {
				if (NextFrame_Water == false) {
					Player2.overridespeed = false;
					Player2.isInWater = false;
				}
			}
			temprect.x -= (int) camera_x;
			if (debug == true) {
				g.drawImage(collider, temprect.x, temprect.y, temprect.width, temprect.height, null);
				if (Mouse.gamecursorrect != null) {
					g.drawImage(collider2, Mouse.gamecursorrect.x, Mouse.gamecursorrect.y, Mouse.gamecursorrect.width, Mouse.gamecursorrect.height, null);
				}
				//g.drawImage(collider2, blockcollisions[i].x, blockcollisions[i].y, blockcollisions[i].width, blockcollisions[i].height, null);
			}
			if (Mouse.left == true) {
				if (Mouse.gamecursorrect.intersects(temprect)) {
					System.out.println("You clicked an block!");
					Build.Mine(i, true);
				}
			}
			if (Player2.playerrect.intersects(temprect)) {
				/*Player2.collisionpos = temprect;
				if (Input.GetInput("a") && Player2.collision != "d" && Player2.collisiondown == false) {
					Player2.collision = "a";
				}
				if (Input.GetInput("d") && Player2.collision != "a" && Player2.collisiondown == false) {
					Player2.collision = "d";
				}*/
				/*if (Player2.isFalling == true) {
					System.out.println(Player2.player_y + "," + temprect.y);
					if (Player2.player_y+64 > temprect.y) {
						//Player2.player_y--;
						Player2.collisiondown = true;
					}
				}*/
				//System.out.println(Player2.i + "," + Player2.isFalling + "," + Player2.isJumping);
				/*if (Player2.isJumping == true) {
					Player2.player_y -= 0.5;
					//Player2.i = 101;
				}
				if (Player2.collision == "a") {
					Player2.player_x += 0.02;
				}
				if (Player2.collision == "d") {
					Player2.player_x -= 0.02;
				}*/
			}
			else if (Player2.collisionpos != null) {
				if (!Player2.playerrect.intersects(Player2.collisionpos)) {
					Player2.collision = "";
					if (Player2.collisiondown == true) {
						Player2.isFalling = true;
						Player2.collisiondown = false;
					}
				}
			}
			if (removeblock) {
				Build.Mine(i, false);
				//BlockInfo.RemoveAllInfo(i);
			}
		}
		//Draw Player
		g.drawImage(playerimage, (int) (Player2.player_x), (int) Player2.player_y, null);
		//Draw Chi
		if (Settings.chi == true) {
			g.drawImage(Chi.image, (int) Chi.chix, (int) Chi.chiy,28,67, null);
		}
		//Draw Hotbar
		g.drawImage(invslot, f.getSize().width/2-116, 0, 29, 29, null);
		g.drawImage(invslot, f.getSize().width/2-87, 0, 29, 29, null);
		g.drawImage(invslot, f.getSize().width/2-58, 0, 29, 29, null);
		g.drawImage(invslot, f.getSize().width/2-29, 0, 29, 29, null);
		g.drawImage(invslot, f.getSize().width/2, 0, 29, 29, null);
		g.drawImage(invslot, f.getSize().width/2+29, 0, 29, 29, null);
		g.drawImage(invslot, f.getSize().width/2+58, 0, 29, 29, null);
		g.drawImage(invslot, f.getSize().width/2+87, 0, 29, 29, null);
		g.drawImage(invslot, f.getSize().width/2+116, 0, 29, 29, null);
		g.drawImage(Inventory.slots[0], f.getSize().width/2-114, 2, 25, 25, null);
		g.drawImage(Inventory.slots[1], f.getSize().width/2-85, 2, 25, 25, null);
		g.drawImage(Inventory.slots[2], f.getSize().width/2-56, 2, 25, 25, null);
		g.drawImage(Inventory.slots[3], f.getSize().width/2-27, 2, 25, 25, null);
		g.drawImage(Inventory.slots[4], f.getSize().width/2+2, 2, 25, 25, null);
		g.drawImage(Inventory.slots[5], f.getSize().width/2+31, 2, 25, 25, null);
		g.drawImage(Inventory.slots[6], f.getSize().width/2+60, 2, 25, 25, null);
		g.drawImage(Inventory.slots[7], f.getSize().width/2+89, 2, 25, 25, null);
		g.drawImage(Inventory.slots[8], f.getSize().width/2+118, 2, 25, 25, null);
		g.setColor(Color.WHITE);
		g.drawString("" + Inventory.count[0], f.getSize().width/2-118, 25);
		g.drawString("" + Inventory.count[1], f.getSize().width/2-88, 25);
		g.drawString("" + Inventory.count[2], f.getSize().width/2-58, 25);
		g.drawString("" + Inventory.count[3], f.getSize().width/2-28, 25);
		g.drawString("" + Inventory.count[4], f.getSize().width/2+2, 25);
		g.drawString("" + Inventory.count[5], f.getSize().width/2+32, 25);
		g.drawString("" + Inventory.count[6], f.getSize().width/2+62, 25);
		g.drawString("" + Inventory.count[7], f.getSize().width/2+92, 25);
		g.drawString("" + Inventory.count[8], f.getSize().width/2+122, 25);
		g.setColor(Color.BLACK);
		//Draw placeholder block
		if (Build.selected != 0) {
			if (Inventory.items[Build.selected-1] != "Empty") {
				if (Input.snap) {
					if (camera_x/25==Math.floor(camera_x/25)) {
						g.drawImage(blockholder, (int) ((Math.floor(MouseInfo.getPointerInfo().getLocation().x/25)*25) - f.getLocationOnScreen().x), (int) ((Math.floor(MouseInfo.getPointerInfo().getLocation().y/25))*25)-25 - f.getLocationOnScreen().y, 25,25,null);
					}
					else {
						byte temp_x = (byte)Math.round((camera_x/25));
						final_x = (byte)(camera_x-(25*temp_x));
						g.drawImage(blockholder, (int) ((Math.floor(MouseInfo.getPointerInfo().getLocation().x/25)*25)-(int) final_x) - f.getLocationOnScreen().x, (int) ((Math.floor(MouseInfo.getPointerInfo().getLocation().y/25))*25)-25 - f.getLocationOnScreen().y, 25,25,null);
					}
				}
				else {
					g.drawImage(blockholder, MouseInfo.getPointerInfo().getLocation().x-13-f.getLocationOnScreen().x, MouseInfo.getPointerInfo().getLocation().y-37-f.getLocationOnScreen().y, 25,25,null);
				}
			}
		}
		//Start Debugging Information
		if (debug == true) {
			g.setColor(Color.BLACK);
			g.drawString("This is currently " + Version.version + "!", 0, 10);
			g.drawString("DEBUGGING INFORMATION:",0,25);
			g.drawString("Camera_x = " + camera_x,0,40);
			g.drawString("Player_x = " + Player2.player_x + " | Player_y = " + Player2.player_y,0,55);
			g.drawString("Playerspeed = " + Player2.playerspeed,0,70);
			g.drawString("Mapsize = " + world_x,0,85);
			g.drawString("Max Memory = " + (maxmemory/1024) + " KiloBytes | " + (maxmemory/1024/1024) + " MegaBytes",0,100);
			g.drawString("Allocated Memory = " + (allocatedmemory/1024) + " KiloBytes | " + (allocatedmemory/1024/1024) + " MegaBytes",0,115);
			g.drawString("Free Memory = " + (freememory/1024) + " KiloBytes | " + (freememory/1024/1024) + " MegaBytes",0,130);
			g.drawString("Total Free Memory = " + ((freememory + (maxmemory - allocatedmemory))/1024/1024) + " MegaBytes", 0, 145);
			g.drawString("FPS: " + lastFPS, 0, 160);
			g.drawImage(collider, (int) Player2.playerrect.x, (int) Player2.playerrect.y, Player2.playerrect.width, Player2.playerrect.height, null);
			//g.drawImage(collider3, (int) Player2.playerrect.x, (int) Player2.playerrect.getMaxY()-70, Player2.playerrect.width, 69, null);
			g.drawString("Graphics Device: " + Video_Settings.gs, 0, 175);
			g.drawString("Total Graphics Devices: " + (Video_Settings.ge.getScreenDevices().length), 0, 190);
			g.drawString("Using Graphics Device:" + Video_Settings.gd[0].getIDstring(), 0, 205);
			g.drawString("IsJumping: " + Player2.isJumping + " , IsFalling: " + Player2.isFalling,0, 220);
			g.drawString("Total CPU Cores available: " + processors, 0, 235);
		}
		//DRAW GRID
		if (debuggrid == true) {
			for (int gridx = 0; gridx < world_x; gridx += 25) {
				for (int gridy = 0; gridy < f.getHeight(); gridy += 25) {
					g.drawImage(grid, gridx - (int) camera_x, gridy, 25, 25, null);
				}
			}
		}
		//End Debugging Information
		g.dispose();
		f.repaint();
	}
}
