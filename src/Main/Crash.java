package Main;

import javax.swing.JOptionPane;

public class Crash {
	
	public static String cause = "Undefined, run the debugger (starter_debugger.bat) to be able to see all the errors!";
	
	public static void main(String[] args) {
		/*JFrame f = new JFrame();
		f.setSize(Video_Settings.window_size_x, Video_Settings.window_size_y);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setTitle("UH-OH");*/
		String crashdialogue = "Well done, old chap! You crashed the game! \nDon't worry, whenever I implement a save game feature, your world will be automaticly saved and you can continue playing your game, afterward."
				+ "\nBut for now, you crashed, the game.\n\nIf you would like to report this error, please send the following information to the creator of this game:"
				+ "\nCause: " + cause;
		JOptionPane.showMessageDialog(null, crashdialogue,"UH-OH", JOptionPane.ERROR_MESSAGE);
	}
	
}
