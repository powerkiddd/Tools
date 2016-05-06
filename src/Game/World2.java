package Game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import Main.Lighting;
import Main.Mouse;
import Main.Players;
import Main.SaveLoad;
import Main.Version;
import Mobs.Chi;
import Settings.KeyBindings;
import Settings.Settings;
import Settings.Video_Settings;

public class World2 extends JPanel {
	
	public static BufferedImage image;
	private static BufferedImage space;
	public static BufferedImage background_land;
	public static int backgroundx = 640;
	public static int backgroundy = 400;
	public static BufferedImage playerimage;
	public static BufferedImage[] allblocks;
	public static String[] blockidentifiers; //Names of blocks must align to pictures
	private static BufferedImage invslot;
	private static BufferedImage collider;
	private static BufferedImage collider2;
	private static BufferedImage collider3;
	private static BufferedImage blockholder;
	private static BufferedImage grid;
	private static BufferedImage loading;
	private static BufferedImage loadingicon;
	private static BufferedImage console;
	private static BufferedImage spacemen;
	private static BufferedImage highlight;
	private static BufferedImage heart;
	private static BufferedImage hunger;
	private static BufferedImage thirst;
	private static int world_x = 2400;
	private static int world_y = 1200;
	public static boolean debug = false;
	public static boolean debuggrid = false;
	public static boolean debugpie = false;
	public static float camera_x = 0;
	public static float camera_y = 0;
	public static boolean canplayermovey = true;
	public static String[] blocks = new String[0];
	public static String[] blockposses = new String[0];
	public static Rectangle[] blockcollisions = new Rectangle[0];
	public static Boolean[] blockbackground = new Boolean[0];
	public static String[] blockinfo = new String[0];
	public static JFrame f = new JFrame();
	public static JPanel p = new JPanel();
	public static Timer frametimer = new Timer();
	public static Timer updatetimer = new Timer();
	public static Timer vsynctimer = new Timer();
	public static int FPS = 0;
	public static int lastFPS = 0;
	public static String special = "";
	public static boolean once = false;
	public static Runtime runtime = Runtime.getRuntime();
	public static long maxmemory = runtime.maxMemory();
	public static long allocatedmemory = runtime.totalMemory();
	public static long freememory = runtime.freeMemory();
	public static byte processors = (byte) runtime.availableProcessors();
	public static byte final_x;
	public static byte final_y;
	public static boolean NextFrame_Water = false;
	public static byte hasitcrashed = 0;
	public static boolean buildingworld = true;
	public static boolean saveonce = false;
	public static boolean showinventory = false;
	public static String[] consoleoutput = new String[100];
	public static String consoleinput = "";
	private static float rot = 0; //Rotation for loading icon.
	public static long[] milliseconds = new long[6]; //Background,Blocks,Player,GUI,Debug Blocks,Collision Calculation
	public static long[] lastmilliseconds = new long[6]; //Background,Blocks,Player,GUI,Debug Blocks,Collision Calculation
	public static int holdingtool = 0;
	
	public static void main(String[] args) {
		Player2.playerspeed = 3;
		SaveLoad.worldname = "EarlySaveGameTest";
		try {
			Video_Settings.main(null);
			Settings.main(null);
			Music.main(null);
			Chi.main(null);
			Input.main(null);
			Player2.main(null);
			Build.Main(null);
			Inventory.main(null);
			PauseMenu.main(null);
			Mouse.main(null);
			KeyBindings.main(null);
			//Lighting.main(null);
			//Server.main(null);
			//Client.Connect("127.0.0.1", 8888);
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
			String[] allfiles2 = Directory.GetAllFilesFromDirectory("images\\birthday\\");
			BufferedImage[] allblocks_ = allblocks.clone();
			String[] blockidentifiers_ = blockidentifiers.clone();
			allblocks = new BufferedImage[allblocks_.length + allfiles2.length-1];
			blockidentifiers = new String[blockidentifiers_.length + allfiles2.length-1];
			for (int i = 0; i < allblocks_.length; i++) {
				allblocks[i] = allblocks_[i];
				blockidentifiers[i] = blockidentifiers_[i];
			}
			for (int i = 1; i < allfiles2.length; i++) {
				File newfile = new File("images\\birthday\\"+allfiles2[i].substring(16));
				allblocks[allblocks_.length+i-1] = ImageIO.read(newfile);
				String[] split = allfiles2[i].substring(16).split("\\.");
				blockidentifiers[blockidentifiers_.length+i-1] = split[0];
			}
			File file = new File("images\\skybox.jpg");
			File file2 = new File("images\\player.png");
			File file5 = new File("images\\inventory_slot.png");
			File file6 = new File("images\\Collider.png");
			File file7 = new File("images\\Collider2.png");
			File file8 = new File("images\\BlockHolder.png");
			File file9 = new File("images\\Grid.png");
			File file10 = new File("images\\Collider3.png");
			File file12 = new File("images\\background_land.png");
			File file13 = new File("images\\loading\\loading.png");
			File file14 = new File("images\\loading\\loading_icon.png");
			File file15 = new File("images\\skybox_space.jpg");
			File file16 = new File("images\\console.png");
			File file17 = new File("images\\spaceman.png");
			File file18 = new File("images\\highlight.png");
			File file19 = new File("images\\Heart.png");
			File file20 = new File("images\\Hunger.png");
			File file21 = new File("images\\Thirst.png");
			image = ImageIO.read(file);
			playerimage = ImageIO.read(file2);
			invslot = ImageIO.read(file5);
			collider = ImageIO.read(file6);
			collider2 = ImageIO.read(file7);
			blockholder = ImageIO.read(file8);
			grid = ImageIO.read(file9);
			collider3 = ImageIO.read(file10);
			background_land = ImageIO.read(file12);
			loading = ImageIO.read(file13);
			loadingicon = ImageIO.read(file14);
			space = ImageIO.read(file15);
			console = ImageIO.read(file16);
			spacemen = ImageIO.read(file17);
			highlight = ImageIO.read(file18);
			heart = ImageIO.read(file19);
			hunger = ImageIO.read(file20);
			thirst = ImageIO.read(file21);
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
		f.setTitle("Tools " + Version.version + " [FPS: " + lastFPS + "] " + special);
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
				milliseconds[1] -= milliseconds[4];
				milliseconds[1] -= milliseconds[5];
				for (byte i = 0; i < milliseconds.length; i++) {
					lastmilliseconds[i] = milliseconds[i];
					milliseconds[i] = 0;
				}
				f.setTitle("Tools " + Version.version + " [FPS: " + lastFPS + "] " + special);
				FPS = 0;
			}
		};
		TimerTask update = new TimerTask () {
			public void run () {
				Player2.playerrect = new Rectangle((int) Player2.player_x,(short) Player2.player_y,19,67);
				//Open inventory if I is pressed
				if (Input.console == false) {
					if (Input.OnGetInput("i")) {
						showinventory = !showinventory;
					}
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
							Player2.changeside((byte) 1);
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
							Player2.changeside((byte) 0);
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
				if (Player2.player_y > Video_Settings.window_size_y-Video_Settings.window_size_y/3 && camera_y < world_y) {
					//camera_y = Player2.player_y-Video_Settings.window_size_y+Video_Settings.window_size_y/3;
					canplayermovey = false;
				}
				else {
					canplayermovey = true;
					if (camera_y == world_y && Player2.player_y > Video_Settings.window_size_y-Video_Settings.window_size_y/3) {
						canplayermovey = false;
					}
				}
			}
		};
		TimerTask vsyncnxtframe = new TimerTask () {

			public void run() {
				f.repaint();
			}
			
		};
		frametimer.scheduleAtFixedRate(updateFPS, 1000, 1000);
		updatetimer.scheduleAtFixedRate(update, 10, 10);
		if (Video_Settings.VSync) {
			vsynctimer.scheduleAtFixedRate(vsyncnxtframe, (long) (1000/Video_Settings.framelimit), (long) (1000/Video_Settings.framelimit));
		}
		if (!SaveLoad.DoesSaveExist()) {
			for (int i = 75; i < world_x+Video_Settings.window_size_x-25; i++) {
				Random rnd = new Random();
				int kaas = rnd.nextInt(400);
				if (kaas == 200) {
					Build.Place("Tree", new Rectangle(i,f.getSize().height-f.getSize().height/4-175,75,175), true);
				}
				else if (kaas == 100) {
					Build.Place("rock", new Rectangle(i,f.getSize().height-f.getSize().height/4-25,25,25), true);
				}
				else if (kaas == 101) {
					Build.Place("rocks", new Rectangle(i,f.getSize().height-f.getSize().height/4-25,25,25), true);
				}
			}
			for (int i = 0; i < world_x+Video_Settings.window_size_x-25; i += 25) {
				Build.Place("Grass", new Rectangle(i,f.getSize().height-f.getSize().height/4, 25 , 25), false);
				for (int j = f.getSize().height-f.getSize().height/4+25; j < f.getSize().height-25; j += 25) {
					Build.Place("Dirt", new Rectangle(i,j, 25 , 25),false);
				}
				for (int j = f.getSize().height-25; j < world_y+Video_Settings.window_size_y-25; j += 25) {
					Random rnd = new Random();
					int kaas = rnd.nextInt(15);
					if (kaas >= 0 && kaas < 11) {
						Build.Place("Dirt", new Rectangle(i,j, 25 , 25),false);
					}
					else if (kaas == 11) {
						Build.Place("Coal_ore", new Rectangle(i,j, 25 , 25),false);
					}
					else if (kaas == 12) {
						Build.Place("Copper_ore", new Rectangle(i,j, 25 , 25),false);
					}
					else if (kaas == 13) {
						Build.Place("Gold_ore", new Rectangle(i,j, 25 , 25),false);
					}
					else if (kaas == 14) {
						Build.Place("Iron_ore", new Rectangle(i,j, 25 , 25),false);
					}
				}
			}
			SaveLoad.SaveGame();
		}
		else {
			SaveLoad.LoadGame();
		}
		DateFormat dateformat = new SimpleDateFormat("dd/MM");
		Date date = new Date();
		if (dateformat.format(date).equals("18/03") || dateformat.format(date).equals("10/12")) {
			if (dateformat.format(date).equals("18/03")) {
				special = "Happy birthday powerkiddd!";
			}
			if (dateformat.format(date).equals("10/12")) {
				special = "Happy birthday Menleader!";
			}
			for (int i = 0; i < Video_Settings.window_size_x; i += 25) {
				Random rnd = new Random();
				int kaas = rnd.nextInt(4);
				int j = rnd.nextInt(world_y);
				switch (kaas) {
					case 0:
						Build.Place("Balloon_red", new Rectangle(i,j,47,90), true);
					break;
					case 1:
						Build.Place("Balloon_blue", new Rectangle(i,j,47,90), true);
					break;
					case 2:
						Build.Place("Balloon_green", new Rectangle(i,j,47,90), true);
					break;
					case 3:
						Build.Place("Balloon_yellow", new Rectangle(i,j,47,90), true);
					break;
				}
			}
		}
		Build.Place("steamengine", new Rectangle(500,Video_Settings.window_size_y-Video_Settings.window_size_y/3-118,274,184), true);
		buildingworld = false;
		Input.RegisterInConsole("World" + SaveLoad.worldname + "loaded!");
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		NextFrame_Water = false;
		FPS++;
		if (World2.buildingworld == false) {
			long starttime = System.currentTimeMillis();
			//Draw Background
			for (int i=0; i < f.getSize().height; i += backgroundy) {
				for (int j=0; j < world_x+f.getSize().width; j += backgroundx) {
					if (j > camera_x-backgroundx && j < camera_x+f.getSize().width) {
						if (camera_y > -1500) {
							g.drawImage(image, (int) ((int)j-camera_x), i, backgroundx, backgroundy,null);
						}
						else {
							g.drawImage(space, (int) ((int)j-camera_x), i, backgroundx, backgroundy,null);
						}
					}
				}
			}
			for (int i = 0; i < world_x+f.getSize().width; i += 640) {
				if (i > camera_x-640 && i < camera_x+f.getSize().width && 610 > f.getSize().height-f.getSize().height/4-380-camera_y && -400 < f.getSize().height-f.getSize().height/4-380-camera_y) {
					g.drawImage(background_land, (int) (i-camera_x),(int) (f.getSize().height-f.getSize().height/4-380-camera_y), 640, 400,null);
				}
			}
			long stoptime = System.currentTimeMillis();
			milliseconds[0] = milliseconds[0] + (stoptime-starttime);
			starttime = System.currentTimeMillis();
			//Draw Blocks
			for (int i=0; i < blocks.length; i++) {
				String pos = blockposses[i];
				String[] xy = pos.split(",");
				int x = Math.round(Float.parseFloat(xy[0]));
				//short x = Short.parseShort();
				int y = Integer.parseInt(xy[1]);
				boolean removeblock = false;
				boolean isblockvisible = false;
				Rectangle temprect = new Rectangle();
				temprect = (Rectangle) blockcollisions[i].clone();
				if (x > camera_x-blockcollisions[i].width && x < camera_x+f.getSize().width && y > camera_y-blockcollisions[i].height && y < camera_y+f.getSize().height-25) {
					isblockvisible = true;
				}
				for (int j = 0; j < allblocks.length; j++) {
					if (blockidentifiers[j].equalsIgnoreCase(blocks[i]) && isblockvisible) {
						g.drawImage(allblocks[j], (int) (x-camera_x), (int) (y-camera_y), blockcollisions[i].width, blockcollisions[i].height, null);
						if (blockbackground[i] == false) {
							Collision.testplayercol(i);
							milliseconds[5] = milliseconds[5] + (Collision.stoptime-Collision.starttime);
						}
						break;
					}
					else if (isblockvisible == false) {
						break;
					}
				}
				if (blocks[i].equals("Water")) {
					if (y < -1500) {
						removeblock = true;
						Build.Place("Ice", new Rectangle(blockcollisions[i].x,blockcollisions[i].y,25,25),false);
					}
					if (y < World2.f.getSize().height) {
						int hascolwith = Collision.testblockcol(i);
						milliseconds[5] = milliseconds[5] + (Collision.stoptime-Collision.starttime);
						if (hascolwith == -1 || blockbackground[hascolwith] == true) {
							blockposses[i] = "" + x + "," + (y+1);
							blockcollisions[i] = new Rectangle(x,y+1,25,25);
						}
						else if (!blocks[hascolwith].equals("Water")) {
							if (blocks[hascolwith].equals("Grass") || blocks[hascolwith].equals("Dirt")) {
								if (blockcollisions[i].height > 1) {
									blockposses[i] = "" + (x-1) + "," + (y+1);
									blockcollisions[i] = new Rectangle(x,y,blockcollisions[i].width+2,blockcollisions[i].height-1);
								}
								else {
									blockcollisions[i] = new Rectangle(x+25,y,25,25);
									if (!Collision.testblockcolside(i, "Left", true)) {
										milliseconds[5] = milliseconds[5] + (Collision.stoptime-Collision.starttime);
										//spawn water block left
										Build.Place("Water", new Rectangle(blockcollisions[i].x-25,blockcollisions[i].y,25,25),false);
									}
									if (!Collision.testblockcolside(i, "Right", true)) {
										milliseconds[5] = milliseconds[5] + (Collision.stoptime-Collision.starttime);
										//spawn water block right
										Build.Place("Water", new Rectangle(blockcollisions[i].x+25,blockcollisions[i].y,25,25),false);
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
								if (!Collision.testblockcolside(i, "Left", true)) {
									milliseconds[5] = milliseconds[5] + (Collision.stoptime-Collision.starttime);
									//spawn water block left
									//Build.Place("Water", new Rectangle(blockcollisions[i].x-25,blockcollisions[i].y,25,25));
									removeblock = true;
									left = true;
								}
								if (!Collision.testblockcolside(i, "Right", true)) {
									milliseconds[5] = milliseconds[5] + (Collision.stoptime-Collision.starttime);
									//spawn water block right
									//Build.Place("Water", new Rectangle(blockcollisions[i].x+25,blockcollisions[i].y,25,25));
									removeblock = true;
									right = true;
								}
								if (left == true) {
									if (right == true) {
										if (finalside == 0) {
											//if (BlockInfo.GetBlockInfo(i)[1].equals("false")) {
												Build.Place("Water", new Rectangle(blockcollisions[i].x-25,blockcollisions[i].y,25,25),false);
												//BlockInfo.ReplaceBlockInfo(i, "false", "true");
											//}
										}
										else {
											//if (BlockInfo.GetBlockInfo(i)[1].equals("false")) {
												Build.Place("Water", new Rectangle(blockcollisions[i].x+25,blockcollisions[i].y,25,25),false);
												//BlockInfo.ReplaceBlockInfo(i, "false", "true");
											//}
										}
									}
									else {
										//if (BlockInfo.GetBlockInfo(i)[1].equals("false")) {
											Build.Place("Water", new Rectangle(blockcollisions[i].x-25,blockcollisions[i].y,25,25),false);
											//BlockInfo.ReplaceBlockInfo(i, "false", "true");
										//}
									}
								}
								else if (right == true) {
									//if (BlockInfo.GetBlockInfo(i)[1].equals("false")) {
										Build.Place("Water", new Rectangle(blockcollisions[i].x+25,blockcollisions[i].y,25,25),false);
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
				else if (blocks[i].contains("Balloon")) {
					blockposses[i] = "" + (x) + "," + (y-1);
					blockcollisions[i] = new Rectangle(x,y,blockcollisions[i].width,blockcollisions[i].height);
				}
				
				if (isblockvisible && blockbackground[i] == false) {
					if (temprect.intersects(Player2.playerrect)) {
						Collision.testplayercol(i);
						milliseconds[5] = milliseconds[5] + (Collision.stoptime-Collision.starttime);
					}
					else {
						if (NextFrame_Water == false) {
							Player2.overridespeed = false;
							Player2.isInWater = false;
						}
					}
				}
				temprect.x -= (int) camera_x;
				temprect.y -= (int) camera_y;
				long starttime2 = System.currentTimeMillis();
				if (debug == true) {
					if (blockbackground[i] == false && isblockvisible) {
						g.drawImage(collider, temprect.x, temprect.y, temprect.width, temprect.height, null);
					}
					else if (isblockvisible) {
						g.drawImage(collider3, temprect.x, temprect.y, temprect.width, temprect.height, null);
					}
					if (Mouse.gamecursorrect != null) {
						g.drawImage(collider2, Mouse.gamecursorrect.x, Mouse.gamecursorrect.y, Mouse.gamecursorrect.width, Mouse.gamecursorrect.height, null);
					}
				}
				long stoptime2 = System.currentTimeMillis();
				milliseconds[4] = milliseconds[4] + (stoptime2-starttime2);
				if (Mouse.left == true && blockbackground[i] == false) {
					if (Mouse.gamecursorrect.intersects(temprect)) {
						Collision.testplayercol(i);
						milliseconds[5] = milliseconds[5] + (Collision.stoptime-Collision.starttime);
						Build.Mine(i, true, false);
					}
				}
				if (Mouse.right == true && blockbackground[i] == true) {
					if (Mouse.gamecursorrect.intersects(temprect)) {
						Build.Interact(i, true, true);
					}
				}
				if (Player2.collisionpos != null) {
					if (!Player2.playerrect.intersects(Player2.collisionpos)) {
						Player2.collision = "";
						if (Player2.collisiondown == true) {
							Player2.isFalling = true;
							Player2.collisiondown = false;
						}
					}
				}
				if (removeblock) {
					Build.Mine(i, false, false);
					//BlockInfo.RemoveAllInfo(i);
				}
			}
			stoptime = System.currentTimeMillis();
			milliseconds[1] = milliseconds[1] + (stoptime-starttime);
			starttime = System.currentTimeMillis();
			//Draw Player
			if (Player2.lookingatside) {
				g.drawImage(playerimage, (int) (Player2.player_x), (int) Player2.player_y, playerimage.getWidth(), playerimage.getHeight(), null);
				g.drawImage(Inventory.tools[holdingtool], (int) Player2.player_x+9, (int) Player2.player_y+25,25,25, null);
				if (Player2.hasJetpack) {
					g.drawImage(Player2.jetpack, (int) (Player2.player_x), (int) Player2.player_y,19,67, null);
				}
			}
			else {
				g.drawImage(playerimage, (int) (Player2.player_x)+playerimage.getWidth(), (int) Player2.player_y, -playerimage.getWidth(), playerimage.getHeight(), null);
				g.drawImage(Inventory.tools[holdingtool], (int) Player2.player_x+9, (int) Player2.player_y+25,-25,25, null);
				if (Player2.hasJetpack) {
					g.drawImage(Player2.jetpack, (int) (Player2.player_x+19), (int) Player2.player_y,-19,67, null);
				}
			}
			for (int i = 1; i < Players.playersinserver; i++) {
				g.drawImage(playerimage, Players.playerx[i-1], Players.playery[i-1], null);
			}
			stoptime = System.currentTimeMillis();
			milliseconds[2] = milliseconds[2] + (stoptime-starttime);
			//Draw Chi
			if (Settings.chi == true) {
				g.drawImage(Chi.image, (int) Chi.chix, (int) Chi.chiy,28,67, null);
			}
			/*g.drawImage(spacemen, 0, 520,19,77,null);
			g.setColor(Color.WHITE);
			g.fillRect(0, 505, 400, 20);
			g.setColor(Color.BLACK);
			g.drawString("If you continue down this road, you're gonna have a bad time.", 0, 520);*/
			//Draw Lighting
			/*try {
				Graphics2D g2d = (Graphics2D)g;
				Composite translucent = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) (Lighting.daynight.getAlpha()/255f));
				g2d.setComposite(translucent);
				g2d.drawImage(Lighting.light, 0, 0, Video_Settings.window_size_x, Video_Settings.window_size_y, Lighting.daynight, null);
				if (Lighting.daynight.getRed() > 0) {
					Lighting.daynight = new Color(Lighting.daynight.getRed()-1, Lighting.daynight.getGreen()-1, Lighting.daynight.getBlue()-1, Lighting.daynight.getAlpha());
				}
				else if (Lighting.daynight.getAlpha() < 230) {
					Lighting.daynight = new Color(0,0,0,Lighting.daynight.getAlpha()+1);
				}
				translucent = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) (Lighting.torch.getAlpha()/255f));
				g2d.setComposite(translucent);
				g2d.drawImage(Lighting.light, 360, (int) (360-camera_y), 40, 40, Lighting.torch, null);
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			}
			catch (Exception ex) {
				f.dispose();
				Crash.cause = "Someone forgot to initialize the lighting engine...";
				Crash.main(null);
			}*/
			starttime = System.currentTimeMillis();
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
			short i_hotbar = 0;
			for (int x = f.getSize().width/2-116; x < f.getSize().width/2+135; x += 29) {
				if ((i_hotbar/29) == Inventory.selected) {
					g.drawImage(highlight, x+2,0+2,25,25,null);
				}
				if (Mouse.left && Mouse.leftonce == false) {
					if (MouseInfo.getPointerInfo().getLocation().x > x && MouseInfo.getPointerInfo().getLocation().x < x+29) {
						if (MouseInfo.getPointerInfo().getLocation().y > 0+29 && MouseInfo.getPointerInfo().getLocation().y < 0+58) {
							if (Inventory.selected < 0) {
								Inventory.selected = (byte) (i_hotbar/29);
							}
							else if (Inventory.selected != (i_hotbar/29)) {
								//Switcheroo
								String item = Inventory.items[i_hotbar/29];
								byte count = Inventory.count[i_hotbar/29];
								BufferedImage slot = Inventory.slots[i_hotbar/29];
								byte itemtype = Inventory.itemtype[i_hotbar/29];
								Inventory.items[i_hotbar/29] = Inventory.items[Inventory.selected];
								Inventory.count[i_hotbar/29] = Inventory.count[Inventory.selected];
								Inventory.slots[i_hotbar/29] = Inventory.slots[Inventory.selected];
								Inventory.itemtype[i_hotbar/29] = Inventory.itemtype[Inventory.selected];
								Inventory.items[Inventory.selected] = item;
								Inventory.count[Inventory.selected] = count;
								Inventory.slots[Inventory.selected] = slot;
								Inventory.itemtype[Inventory.selected] = itemtype;
								Inventory.selected = -1;
							}
							Mouse.leftonce = true;
						}
					}
				}
				i_hotbar += 29;
			}
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
			for (int x = 27; x < 19*10; x+= 19) {
				g.drawImage(heart, f.getSize().width-x, 0, 19, 17, null);
				g.drawImage(hunger, f.getSize().width-x, 17, 19, 17, null);
				g.drawImage(thirst, f.getSize().width-x, 34, 19, 17, null);
			}
			stoptime = System.currentTimeMillis();
			milliseconds[3] = milliseconds[3] + (stoptime-starttime);
			//Draw placeholder block
			starttime = System.currentTimeMillis();
			if (Build.selected != 0) {
				if (!Inventory.items[Build.selected-1].equals("Empty") && Inventory.itemtype[Build.selected-1] == 1) {
					if (Input.snap) {
						if (camera_x/25==Math.floor(camera_x/25) && camera_y/25==Math.floor(camera_y/25)) {
							g.drawImage(blockholder, (int) ((Math.floor(MouseInfo.getPointerInfo().getLocation().x/25)*25) - f.getLocationOnScreen().x), (int) ((Math.floor(MouseInfo.getPointerInfo().getLocation().y/25))*25)-25 - f.getLocationOnScreen().y, 25,25,null);
						}
						else {
							/*byte temp_x = (byte)Math.round((camera_x/25));
							final_x = (byte)(camera_x-(25*temp_x));
							byte temp_y = (byte)Math.round((camera_y/25));
							final_y = (byte)(camera_y-(25*temp_y));
							final_y = (byte) (final_y + 25);*/
							Mouse.mouseposonscreenx = MouseInfo.getPointerInfo().getLocation().x;
							Mouse.mouseposonscreeny = MouseInfo.getPointerInfo().getLocation().y;
							Mouse.mouseposonscreenx = (int) (Math.floor(Mouse.mouseposonscreenx/25)*25-Math.floor((camera_x-(25*Math.floor(camera_x/25)))));
							Mouse.mouseposonscreeny = (int) Math.floor(Mouse.mouseposonscreeny/25)*25-25;
							Mouse.mouseposinworldx = (int) ((Mouse.mouseposonscreenx)-camera_x);
							Mouse.mouseposinworldy = Mouse.mouseposonscreeny+Math.round(camera_y);
							g.drawImage(blockholder, Mouse.mouseposonscreenx, Mouse.mouseposonscreeny, 25,25,null);
						}
					}
					else {
						g.drawImage(blockholder, MouseInfo.getPointerInfo().getLocation().x-13-f.getLocationOnScreen().x, MouseInfo.getPointerInfo().getLocation().y-37-f.getLocationOnScreen().y, 25,25,null);
					}
				}
				else if (Inventory.itemtype[Build.selected-1] == 2) {
					holdingtool = Inventory.GetItemID(Inventory.items[Build.selected-1], Inventory.itemtype[Build.selected-1]);
				}
			}
			stoptime = System.currentTimeMillis();
			milliseconds[3] = milliseconds[3] + (stoptime-starttime);
			starttime = System.currentTimeMillis();
			//Draw pausemenu
			if (Input.escape) {
				if (!saveonce) {
					saveonce = true;
					SaveLoad.SaveGame();
				}
				PauseMenu.PauseChanged();
				g.drawImage(PauseMenu.mainmenubutton, PauseMenu.mainmenubuttonrect.x, PauseMenu.mainmenubuttonrect.y, null);
				if (Mouse.left) {
					if (Mouse.gamecursorrect.intersects(PauseMenu.mainmenubuttonrect)) {
						f.removeAll();
						f.dispose();
						MainMenu.main(null);
					}
				}
			}
			else {
				saveonce = false;
			}
			stoptime = System.currentTimeMillis();
			milliseconds[3] = milliseconds[3] + (stoptime-starttime);
			//Draw inventory
			if (showinventory) {
				short i = 0;
				g.setColor(Color.WHITE);
				for (int y = 0; y < 116; y += 29) {
					for (int x = 0; x < 261; x += 29) {
						g.drawImage(invslot,x,y,29,29,null);
						g.drawImage(Inventory.slots[9+i/29],x+2,y+2,25,25,null);
						if ((9+i/29) == Inventory.selected) {
							g.drawImage(highlight, x+2,y+2,25,25,null);
						}
						g.drawString("" + Inventory.count[9+i/29], x+2, y+25);
						if (Mouse.left && Mouse.leftonce == false) {
							if (MouseInfo.getPointerInfo().getLocation().x > x && MouseInfo.getPointerInfo().getLocation().x < x+29) {
								if (MouseInfo.getPointerInfo().getLocation().y > y+29 && MouseInfo.getPointerInfo().getLocation().y < y+58) {
									if (Inventory.selected < 0) {
										Inventory.selected = (byte) (9+i/29);
										/*Inventory.items[9+i/29] = "Empty";
										Inventory.slots[9+i/29] = null;
										Inventory.count[9+i/29] = 0;*/
									}
									else if (Inventory.selected != (9+i/29)) {
										//Switcheroo
										String item = Inventory.items[9+i/29];
										byte count = Inventory.count[9+i/29];
										BufferedImage slot = Inventory.slots[9+i/29];
										byte itemtype = Inventory.itemtype[9+i/29];
										Inventory.items[9+i/29] = Inventory.items[Inventory.selected];
										Inventory.count[9+i/29] = Inventory.count[Inventory.selected];
										Inventory.slots[9+i/29] = Inventory.slots[Inventory.selected];
										Inventory.itemtype[9+i/29] = Inventory.itemtype[Inventory.selected];
										Inventory.items[Inventory.selected] = item;
										Inventory.count[Inventory.selected] = count;
										Inventory.slots[Inventory.selected] = slot;
										Inventory.itemtype[Inventory.selected] = itemtype;
										Inventory.selected = -1;
									}
									Mouse.leftonce = true;
								}
							}
						}
						i += 29;
					}
				}
				g.setColor(Color.BLACK);
			}
			//Start Debugging Information
			if (debug == true) {
				if (camera_y > -1500) {
					g.setColor(Color.BLACK);
				}
				else {
					g.setColor(Color.WHITE);
				}
				g.drawString("This is currently " + Version.version + "!", 0, 10);
				g.drawString("DEBUGGING INFORMATION:",0,25);
				g.drawString("Camera_x = " + camera_x,0,40);
				g.drawString("Camera_y = " + camera_y + " | I = " + Player2.i + " | Collisiondown = " + Player2.collisiondown + " | cpmy = " + canplayermovey,0,55);
				g.drawString("Player_x = " + Player2.player_x + " | Player_y = " + Player2.player_y,0,70);
				g.drawString("Playerspeed = " + Player2.playerspeed,0,85);
				g.drawString("Mapsize = " + world_x,0,100);
				g.drawString("Max Memory = " + (maxmemory/1024) + " KiloBytes | " + (maxmemory/1024/1024) + " MegaBytes",0,115);
				g.drawString("Allocated Memory = " + (allocatedmemory/1024) + " KiloBytes | " + (allocatedmemory/1024/1024) + " MegaBytes",0,130);
				g.drawString("Free Memory = " + (freememory/1024) + " KiloBytes | " + (freememory/1024/1024) + " MegaBytes",0,145);
				g.drawString("Total Free Memory = " + ((freememory + (maxmemory - allocatedmemory))/1024/1024) + " MegaBytes", 0, 160);
				g.drawString("FPS: " + lastFPS, 0, 175);
				g.drawImage(collider, (int) Player2.playerrect.x, (int) Player2.playerrect.y, Player2.playerrect.width, Player2.playerrect.height, null);
				g.drawString("Graphics Device: " + Video_Settings.gs, 0, 190);
				g.drawString("Total Graphics Devices: " + (Video_Settings.ge.getScreenDevices().length), 0, 205);
				g.drawString("Using Graphics Device:" + Video_Settings.gd[0].getIDstring(), 0, 220);
				g.drawString("IsJumping: " + Player2.isJumping + " , IsFalling: " + Player2.isFalling,0, 235);
				g.drawString("Total CPU Cores available: " + processors, 0, 250);
			}
			//DRAW GRID
			if (debuggrid == true) {
				for (int gridx = 0; gridx < world_x; gridx += 25) {
					for (int gridy = 0; gridy < f.getHeight(); gridy += 25) {
						g.drawImage(grid, gridx - (int) camera_x, gridy - (int) camera_y, 25, 25, null);
					}
				}
			}
			//DRAW DEBUG PIE
			if (debug || debugpie) {
				long totalmilliseconds = 1;
				for (byte i = 0; i < lastmilliseconds.length; i++) {
					totalmilliseconds = totalmilliseconds + lastmilliseconds[i];
				}
				g.fillRect(f.getWidth()-250, 215, 200, 100);
				g.setColor(Color.GREEN);
				float derp = (float) ((float) (lastmilliseconds[0])/totalmilliseconds);
				g.fillArc(f.getWidth()-250, 0, 200, 200, 0, (int) Math.ceil(360*derp));
				g.drawString("Background render: " + lastmilliseconds[0] + "MS", f.getWidth()-250, 225);
				g.setColor(Color.BLUE);
				float derp2 = (float) ((float) (lastmilliseconds[1])/totalmilliseconds);
				g.fillArc(f.getWidth()-250, 0, 200, 200, (int) Math.ceil(360*derp), (int) Math.ceil(360*derp2));
				g.drawString("Blocks render: " + lastmilliseconds[1] + "MS", f.getWidth()-250, 240);
				g.setColor(Color.ORANGE);
				float derp3 = (float) ((float) (lastmilliseconds[2])/totalmilliseconds);
				g.fillArc(f.getWidth()-250, 0, 200, 200, (int) Math.ceil(360*(derp+derp2)), (int) Math.ceil((360*derp3)));
				g.drawString("Player render: " + lastmilliseconds[2] + "MS", f.getWidth()-250, 255);
				g.setColor(Color.CYAN);
				float derp4 = (float) ((float) (lastmilliseconds[3])/totalmilliseconds);
				g.fillArc(f.getWidth()-250, 0, 200, 200, (int) Math.ceil(360*(derp+derp2+derp3)), (int) Math.ceil((360*derp4)));
				g.drawString("GUI render: " + lastmilliseconds[3] + "MS", f.getWidth()-250, 270);
				g.setColor(Color.MAGENTA);
				float derp5 = (float) ((float) (lastmilliseconds[4])/totalmilliseconds);
				g.fillArc(f.getWidth()-250, 0, 200, 200, (int) Math.ceil(360*(derp+derp2+derp3+derp4)), (int) Math.ceil(360*derp5));
				g.drawString("Debug Blocks render: " + lastmilliseconds[4] + "MS", f.getWidth()-250, 285);
				g.setColor(Color.PINK);
				float derp6 = (float) ((float) (lastmilliseconds[5])/totalmilliseconds);
				g.fillArc(f.getWidth()-250, 0, 200, 200, (int) Math.ceil(360*(derp+derp2+derp3+derp4+derp5)), (int) Math.ceil(360*derp6));
				g.drawString("Collision Calculation: " + lastmilliseconds[5] + "MS", f.getWidth()-250, 300);
				g.setColor(Color.BLACK);
				g.drawString(String.valueOf(totalmilliseconds), f.getWidth()-160, 100);
			}
			//DRAW CONSOLE
			if (Input.console) {
				g.drawImage(console, 0, 0, f.getWidth(), f.getHeight()/3, null);
				g.setColor(Color.WHITE);
				g.drawString(consoleinput, 0, 25);
				int totalinconsole = 100;
				for (int i = consoleoutput.length-1; i > 0; i--) {
					if (consoleoutput[i] != null) {
						g.drawString(consoleoutput[i], 75, (f.getHeight()/3-20-totalinconsole*20)+i*20);
					}
					else {
						totalinconsole = i-1;
					}
				}
				g.setColor(Color.BLACK);
			}
			//End Debugging Information
		}
		else {
			g.drawImage(loading, 0,0, f.getSize().width, f.getSize().height, null);
			AffineTransform at = new AffineTransform();
	        at.translate(getWidth()-loadingicon.getWidth()*2/2, getHeight()-loadingicon.getHeight()*2/2);
	        at.rotate(rot);
	        rot += 0.01f;
	        at.scale(2, 2);
	        at.translate(-loadingicon.getWidth()/2, -loadingicon.getHeight()/2);
	        // draw the image
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.drawImage(loadingicon, at, null);
		}
		g.dispose();
		if (Video_Settings.VSync == false) {
			f.repaint();
		}
	}
}
