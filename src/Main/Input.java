package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.imageio.ImageIO;

import Game.Build;
import Game.Inventory;
import Game.Player2;
import Game.World2;

public class Input {
	
	public static boolean a = false;
	public static boolean d = false;
	public static boolean i = false;
	public static boolean shift = false;
	public static boolean space = false;
	public static boolean key0 = false;
	public static boolean key1 = false;
	public static boolean key2 = false;
	public static boolean key3 = false;
	public static boolean key4 = false;
	public static boolean key5 = false;
	public static boolean key6 = false;
	public static boolean key7 = false;
	public static boolean key8 = false;
	public static boolean key9 = false;
	public static boolean f3 = false;
	public static boolean f4 = false;
	public static boolean snap = true;
	public static boolean escape = false;
	public static boolean console = false;
	public static String[] onces = new String[10];
	public static String lastconsoleinput = "";
	public static String[] itemtypes = {"Empty (not valid)", "Block", "Tool"};
	
	public static void main (String[] args) {
		World2.f.addKeyListener(new KeyListener () {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 17) {
					snap = !snap;
				}
				if (e.getKeyCode() == 65) {
					a = true;
				}
				if (e.getKeyCode() == 68) {
					d = true;
				}
				if (e.getKeyCode() == 73) {
					i = true;
				}
				if (e.getKeyCode() == 16) {
					shift = true;
				}
				if (e.getKeyCode() == 32) {
					space = true;
				}
				if (e.getKeyCode() == 48) {
					key0 = true;
					Build.selected = 0;
				}
				if (e.getKeyCode() == 49) {
					key1 = true;
					Build.selected = 1;
				}
				if (e.getKeyCode() == 50) {
					key2 = true;
					Build.selected = 2;
				}
				if (e.getKeyCode() == 51) {
					key3 = true;
					Build.selected = 3;
				}
				if (e.getKeyCode() == 52) {
					key4 = true;
					Build.selected = 4;
				}
				if (e.getKeyCode() == 53) {
					key5 = true;
					Build.selected = 5;
				}
				if (e.getKeyCode() == 54) {
					key6 = true;
					Build.selected = 6;
				}
				if (e.getKeyCode() == 55) {
					key7 = true;
					Build.selected = 7;
				}
				if (e.getKeyCode() == 56) {
					key8 = true;
					Build.selected = 8;
				}
				if (e.getKeyCode() == 57) {
					key9 = true;
					Build.selected = 9;
				}
				if (e.getKeyCode() == 114) {
					f3 =! f3;
					if (shift == false) {
						World2.debug = f3;
					}
					else {
						World2.debugpie = f3;
					}
				}
				if (e.getKeyCode() == 115) {
					f4 =! f4;
					World2.debuggrid = f4;
				}
				if (e.getKeyCode() == 27) {
					escape =! escape;
				}
				if (e.getKeyCode() == 192) {
					console =! console;
					World2.consoleinput = "";
				}
				if (console) {
					if (e.getKeyCode() == 10) {
						RegisterInConsole(World2.consoleinput);
						CheckConsoleCommand(World2.consoleinput);
						lastconsoleinput = World2.consoleinput;
						World2.consoleinput = "";
					}
					else if (e.getKeyCode() == 8) {
						World2.consoleinput = World2.consoleinput.substring(0, World2.consoleinput.length()-1);
					}
					else if (e.getKeyCode() == 38) {
						//arrow up
						World2.consoleinput = lastconsoleinput;
					}
					else if (e.getKeyCode() == 40) {
						//arrow down
						World2.consoleinput = lastconsoleinput;
					}
					else if (e.getKeyCode() != 192 && e.getKeyCode() != 10 && e.getKeyCode() != 16 && e.getKeyCode() != 20 && e.getKeyCode() != 114 && e.getKeyCode() != 115 && e.getKeyCode() != 17 && e.getKeyCode() != 18 && e.getKeyCode() != 524 && e.getKeyCode() != 525 && e.getKeyCode() != 144 && e.getKeyCode() != 37 && e.getKeyCode() != 39) {
						World2.consoleinput = World2.consoleinput + e.getKeyChar();
					}
				}
				//System.out.println(e.getKeyCode());				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 16) {
					shift = false;
				}
				if (e.getKeyCode() == 65) {
					a = false;
				}
				if (e.getKeyCode() == 68) {
					d = false;
				}
				if (e.getKeyCode() == 73) {
					i = false;
				}
				if (e.getKeyCode() == 32) {
					space = false;
				}
				if (e.getKeyCode() == 49) {
					key1 = false;
				}
				if (e.getKeyCode() == 50) {
					key2 = false;
				}
				if (e.getKeyCode() == 51) {
					key3 = false;
				}
				if (e.getKeyCode() == 52) {
					key4 = false;
				}
				if (e.getKeyCode() == 53) {
					key5 = false;
				}
				if (e.getKeyCode() == 54) {
					key6 = false;
				}
				if (e.getKeyCode() == 55) {
					key7 = false;
				}
				if (e.getKeyCode() == 56) {
					key8 = false;
				}
				if (e.getKeyCode() == 57) {
					key9 = false;
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	public static boolean GetInput (String Key) {
		if (Key == "a") {
			return a;
		}
		if (Key == "d") {
			return d;
		}
		if (Key == "shift") {
			return shift;
		}
		if (Key == "space") {
			return space;
		}
		if (Key == "i") {
			return i;
		}
		return false;
	}
	
	public static boolean OnGetInput (String Key) {
		if (Key.equals("i")) {
			for (byte bi = 0; bi < onces.length; bi++) {
				if (onces[bi] == "i") {
					if (i == false) {
						onces[bi] = null;
						return false;
					}
					else {
						return false;
					}
				}
			}
			for (byte bi = 0; bi < onces.length; bi++) {
				if (onces[bi] == null && i == true) {
					onces[bi] = "i";
					return true;
				}
			}
		}
		return false;
	}
	
	public static void CheckConsoleCommand (String input) {
		String result = "Command not found.";
		String[] splitinput = input.split(" ");
		if (splitinput[0].equalsIgnoreCase("Give")) {
			if (splitinput.length < 4) {
				result = "Not enough arguments, execute command as: give *item* *amount* *type*";
			}
			else {
				try {
					if (Inventory.AddItem(splitinput[1], Byte.parseByte(splitinput[2]), Byte.parseByte(splitinput[3]))) {
						result = "Giving " + splitinput[2] + " of " + splitinput[1] + " type: " + splitinput[3];
					}
					else {
						result = "Failed to execute command, inventory is full or block not found!";
					}
				}
				catch (Exception ex) {
					if (ex.getMessage().contains("Value out of range.")) {
						result = "Failed to execute command, Argument 2 (" + splitinput[2] + ") Is to high or to low. (Range 0-127)";
					}
					else {
						result = "Failed to execute command, Argument 2 (" + splitinput[2] + ") Is not a number!";
					}
				}
			}
		}
		if (input.equalsIgnoreCase("ToggleJetpack")) {
			Player2.hasJetpack = !Player2.hasJetpack;
			result = "Jetpack enabled!";
		}
		if (input.equalsIgnoreCase("Help") || input.equalsIgnoreCase("Commands")) {
			result = "Prepare your anus for a list of commands!";
			ShowAllCommands();
		}
		if (input.equalsIgnoreCase("ListAllBlocks")) {
			result = "Prepare your anus for a list of all blocks!";
			ShowAllBlocks();
		}
		if (splitinput[0].equalsIgnoreCase("ToggleWorldState")) {
			try {
				if (splitinput.length > 1) {
					if (splitinput[1].equalsIgnoreCase("Normal")) {
						result = "World is now normal.";
						File file = new File("images\\background_land.png");
						World2.background_land = ImageIO.read(file);
						File file2 = new File("images\\skybox.jpg");
						World2.image = ImageIO.read(file2);
						File file3 = new File("images\\blocks\\grass.png");
						File file4 = new File("images\\blocks\\Tree.png");
						for (int i = 0; i < World2.blockidentifiers.length; i++) {
							if (World2.blockidentifiers[i].equalsIgnoreCase("Grass")) {
								World2.allblocks[i] = ImageIO.read(file3);
							}
							else if (World2.blockidentifiers[i].equalsIgnoreCase("Tree")) {
								World2.allblocks[i] = ImageIO.read(file4);
							}
						}
					}
					else if (splitinput[1].equalsIgnoreCase("Industrialized")) {
						result = "World is now industrialized.";
						File file = new File("images\\background_land_industrialized.png");
						World2.background_land = ImageIO.read(file);
						File file2 = new File("images\\skybox_industrialized.jpg");
						World2.image = ImageIO.read(file2);
						File file3 = new File("images\\blocks\\grass_industrialized.png");
						File file4 = new File("images\\blocks\\Tree_industrialized.png");
						for (int i = 0; i < World2.blockidentifiers.length; i++) {
							if (World2.blockidentifiers[i].equalsIgnoreCase("Grass")) {
								World2.allblocks[i] = ImageIO.read(file3);
							}
							else if (World2.blockidentifiers[i].equalsIgnoreCase("Tree")) {
								World2.allblocks[i] = ImageIO.read(file4);
							}
						}
					}
					else if (splitinput[1].equalsIgnoreCase("Space")) {
						result = "You cheeky easter egg finding bastard.";
						File file = new File("images\\skybox_space.jpg");
						World2.image = ImageIO.read(file);
						World2.background_land = null;
					}
					else {
						result = "WorldState not found, available states: Normal,Industrialized";
					}
				}
				else {
					result = "Not enough arguments, execute command as: ToggleWorldState *state*";
				}
			} catch (Exception ex) {
				result = "This trew an error...";
			}
		}
		if (splitinput[0].equalsIgnoreCase("Clear")) {
			if (splitinput.length > 1) {
				if (splitinput[1].equalsIgnoreCase("Console")) {
					result = "NYI, i'm lazy.";
				}
				else if (splitinput[1].equalsIgnoreCase("Inventory")) {
					for (byte i = 0; i < 45; i++) {
						Inventory.slots[i] = null;
						Inventory.items[i] = "Empty";
						Inventory.count[i] = 0;
						Inventory.itemtype[i] = 0;
					}
					result = "All items in Inventory cleared.";
				}
				else {
					result = "Invalid argument: '" + splitinput[1] + "', valid arguments: Console,Inventory";
				}
			}
			else {
				result = "Not enough arguments, execute command as: Clear Console/Inventory";
			}
		}
		RegisterInConsole(result);
	}
	
	public static void ShowAllCommands() {
		RegisterInConsole("Help/Commands - Shows all commands");
		RegisterInConsole("Give *block* *amount* *type* - Gives the amount of the specified block to the player. (Types: 1-block 2-tool)");
		RegisterInConsole("ListAllBlocks - Shows a list of all blocks in the game.");
		RegisterInConsole("ToggleJetpack - Toggles jetpack on/off.");
		RegisterInConsole("ToggleWorldState - Toggles the state of the world.");
	}
	
	public static void ShowAllBlocks() {
		for (int i = 0; i < Inventory.identifier.length; i++) {
			if (Inventory.identifier[i] != null) {
				RegisterInConsole(Inventory.identifier[i]);
			}
			else {
				return;
			}
		}
	}
	
	public static void RegisterInConsole (String register) {
		for (byte i = 0; i < World2.consoleoutput.length; i++) {
			if (World2.consoleoutput[i] == null) {
				World2.consoleoutput[i] = register;
				return;
			}
		}
		//Consoleoutput is full!
		for (int i = 0; i < World2.consoleoutput.length-1; i++) {
			World2.consoleoutput[i] = World2.consoleoutput[i+1];
		}
		World2.consoleoutput[World2.consoleoutput.length-1] = register;
	}
}
