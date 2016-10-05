package Main;

import javax.swing.JOptionPane;

public class Crash {
	
	public static String cause = "Undefined, run the debugger (starter_debugger.bat) to be able to see all the errors!";
	
	public static void main(String[] args) {
		SaveLoad.worldname = "CrashSaveBackup";
		boolean hasSaved = false;
		try {
			SaveLoad.SaveGame();
			hasSaved = true;
		}catch (Exception ex) {
			cause = cause + "\n\nAlso, we couldn't backup your save, reason: " + ex.getMessage();
		}
		String crashdialogue;
		if (hasSaved) {
			crashdialogue = "Well done, old chap! You crashed the game! \nDon't worry, your game has been automaticly saved and can be found in: Saves/CrashSaveBackup."
					+ "\nBut for now, you crashed, the game.\n\nIf you would like to report this error, please send the following information to the creator of this game:"
					+ "\nCause: " + cause;
		}
		else {
			crashdialogue = "Welp, it crashed... \nWe're terribly sorry we couldn't create a backup before it crashed."
					+ "\n\nIf you would like to report this error, please send the following information to the creator of this game:"
					+ "\nCause: " + cause;
		}
		JOptionPane.showMessageDialog(null, crashdialogue,"UH-OH", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
	
}
