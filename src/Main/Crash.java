package Main;

import javax.swing.JOptionPane;

public class Crash {
	
	public static String cause = "Undefined, run the debugger (starter_debugger.bat) to be able to see all the errors!";
	
	public static void main(String[] args) {
		if (SaveLoad.worldname == null || SaveLoad.worldname == "") {
			SaveLoad.worldname = "CrashSaveBackup";
		}
		SaveLoad.SaveGame();
		String crashdialogue = "Well done, old chap! You crashed the game! \nDon't worry, your game has been automaticly saved and you can continue playing your game at restart."
				+ "\nBut for now, you crashed, the game.\n\nIf you would like to report this error, please send the following information to the creator of this game:"
				+ "\nCause: " + cause;
		JOptionPane.showMessageDialog(null, crashdialogue,"UH-OH", JOptionPane.ERROR_MESSAGE);
	}
	
}
