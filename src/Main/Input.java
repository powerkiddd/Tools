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
	public static String keytext = "";
	
	public static void main (String[] args) {
		World2.f.addKeyListener(new KeyListener () {

			@Override
			public void keyPressed(KeyEvent e) {
				keytext = KeyEvent.getKeyText(e.getKeyCode());
				if (keytext.equalsIgnoreCase("Ctrl")) {
					snap = !snap;
				}
				if (e.getKeyChar() == 'a') {
					a = true;
				}
				if (e.getKeyChar() == 'd') {
					d = true;
				}
				if (e.getKeyChar() == 'i') {
					i = true;
				}
				if (keytext.equalsIgnoreCase("Shift")) {
					shift = true;
				}
				if (keytext.equalsIgnoreCase("Space")) {
					space = true;
				}
				if (e.getKeyChar() == '0') {
					key0 = true;
					Build.selected = 0;
				}
				if (e.getKeyChar() == '1') {
					key1 = true;
					Build.selected = 1;
				}
				if (e.getKeyChar() == '2') {
					key2 = true;
					Build.selected = 2;
				}
				if (e.getKeyChar() == '3') {
					key3 = true;
					Build.selected = 3;
				}
				if (e.getKeyChar() == '4') {
					key4 = true;
					Build.selected = 4;
				}
				if (e.getKeyChar() == '5') {
					key5 = true;
					Build.selected = 5;
				}
				if (e.getKeyChar() == '6') {
					key6 = true;
					Build.selected = 6;
				}
				if (e.getKeyChar() == '7') {
					key7 = true;
					Build.selected = 7;
				}
				if (e.getKeyChar() == '8') {
					key8 = true;
					Build.selected = 8;
				}
				if (e.getKeyChar() == '9') {
					key9 = true;
					Build.selected = 9;
				}
				if (keytext.equalsIgnoreCase("F3")) {
					f3 =! f3;
					if (shift == false) {
						World2.debug = f3;
					}
					else {
						World2.debugpie = f3;
					}
				}
				if (keytext.equalsIgnoreCase("F4")) {
					f4 =! f4;
					World2.debuggrid = f4;
				}
				if (keytext.equalsIgnoreCase("Escape")) {
					escape =! escape;
				}
				if (e.getKeyChar() == '`') {
					console =! console;
					World2.consoleinput = "";
				}
				if (console) {
					if (keytext.equalsIgnoreCase("Enter")) {
						RegisterInConsole(World2.consoleinput);
						CheckConsoleCommand(World2.consoleinput);
						lastconsoleinput = World2.consoleinput;
						World2.consoleinput = "";
					}
					else if (keytext.equalsIgnoreCase("Backspace")) {
						World2.consoleinput = World2.consoleinput.substring(0, World2.consoleinput.length()-1);
					}
					else if (keytext.equalsIgnoreCase("Up")) {
						//arrow up
						World2.consoleinput = lastconsoleinput;
					}
					else if (keytext.equalsIgnoreCase("Down")) {
						//arrow down
						World2.consoleinput = lastconsoleinput;
					}
					else if (!e.isActionKey() && !keytext.equalsIgnoreCase("Ctrl") && !keytext.equalsIgnoreCase("Shift")) {
						World2.consoleinput = World2.consoleinput + e.getKeyChar();
					}
				}
				//System.out.println(e.getKeyChar());
				//System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
			}

			@Override
			public void keyReleased(KeyEvent e) {
				keytext = KeyEvent.getKeyText(e.getKeyCode());
				if (keytext.equalsIgnoreCase("Shift")) {
					shift = false;
				}
				if (e.getKeyChar() == 'a') {
					a = false;
				}
				if (e.getKeyChar() == 'd') {
					d = false;
				}
				if (e.getKeyCode() == 'i') {
					i = false;
				}
				if (keytext.equalsIgnoreCase("Space")) {
					space = false;
				}
				if (e.getKeyChar() == '1') {
					key1 = false;
				}
				if (e.getKeyChar() == '2') {
					key2 = false;
				}
				if (e.getKeyChar() == '3') {
					key3 = false;
				}
				if (e.getKeyChar() == '4') {
					key4 = false;
				}
				if (e.getKeyChar() == '5') {
					key5 = false;
				}
				if (e.getKeyChar() == '6') {
					key6 = false;
				}
				if (e.getKeyChar() == '7') {
					key7 = false;
				}
				if (e.getKeyChar() == '8') {
					key8 = false;
				}
				if (e.getKeyChar() == '9') {
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
			if (splitinput.length < 3) {
				result = "Not enough arguments, execute command as: give *item* *amount*";
			}
			else {
				try {
					if (Inventory.AddItem(splitinput[1], Byte.parseByte(splitinput[2]), (byte) 1)) {
						result = "Giving " + splitinput[2] + " of " + splitinput[1];
					}
					else if (Inventory.AddItem(splitinput[1], Byte.parseByte(splitinput[2]), (byte) 2)) {
						result = "Giving " + splitinput[2] + " of " + splitinput[1];
					}
					else {
						result = "Failed to execute command, inventory is full or block not found!";
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
					if (ex.getMessage().contains("Value out of range.")) {
						result = "Failed to execute command, Argument 2 (" + splitinput[2] + ") Is to high or to low. (Range 0-127)";
					}
					else {
						result = "Failed to execute command, Argument 2 (" + splitinput[2] + ") Is not a number!";
					}
				}
			}
		}
		else if (input.equalsIgnoreCase("ToggleJetpack")) {
			Player2.hasJetpack = !Player2.hasJetpack;
			result = "Jetpack enabled!";
		}
		else if (input.equalsIgnoreCase("Help") || input.equalsIgnoreCase("Commands")) {
			result = "Prepare your anus for a list of commands!";
			ShowAllCommands();
		}
		else if (input.equalsIgnoreCase("ListAllBlocks")) {
			result = "Prepare your anus for a list of all blocks!";
			ShowAllBlocks();
		}
		else if (splitinput[0].equalsIgnoreCase("ToggleWorldState")) {
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
		else if (splitinput[0].equalsIgnoreCase("Clear")) {
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
