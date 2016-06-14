package Game;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;

import Main.Version;
import Settings.Settings;
import Settings.Video_Settings;

public class MainMenu extends JPanel{

	private static BufferedImage bgr = new BufferedImage(Video_Settings.window_size_x, Video_Settings.window_size_y, BufferedImage.TYPE_INT_ARGB);
	private static JFrame f = new JFrame("Tools " + Version.version);
	static JButton settings = new JButton("Settings");
	static JButton applysettings = new JButton("Apply");
	static JButton fullscreen = new JButton("Fullscreen: " + Settings.fullscreen);
	static JButton play = new JButton("Play Game");
	static JButton chi = new JButton("Chi: " + Settings.chi);
	static JButton exit = new JButton("Exit Game");
	static JButton invisible = new JButton("");
	static JButton music = new JButton("Music: " + Settings.music);
	static JButton vsync = new JButton("Framelimit: " + Video_Settings.VSync);
	static JTextField mapsize = new JTextField("INPUT MAPSIZE HERE");
	static JTextField framelimit = new JTextField("INPUT FRAMELIMIT HERE");
	static JLabel mapsizelabel = new JLabel("Mapsize: ");
	static JLabel derp;
	static JLabel maintext = new JLabel("As suggested by Mees... MEES...");
	static JLabel framelimitlabel = new JLabel("Framelimit: ");
	static boolean error = false;
	static Timer update_ = new Timer();
	static Random rand = new Random();
	
	public static void main(String [] args) {	
		f.remove(World.f);
		try {
			Video_Settings.main(null);
			Settings.main(null);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		switch (rand.nextInt(4)) {
		case 0:
			maintext.setText("As suggested by Mees... MEES...");
			break;
		case 1:
			maintext.setText("Programmed by Kevin Wouda!");
			break;
		case 2:
			maintext.setText("PHYSICS!!!!");
			break;
		case 3:
			maintext.setText("Under the sea... Wait, what sea?");
			break;
		}
		fullscreen.setText("Fullscreen: " + Settings.fullscreen);
		chi.setText("Chi: " + Settings.chi);
		music.setText("Music: " + Settings.music);
		vsync.setText("Use framelimit: " + Video_Settings.VSync);
		f.setLayout(null);
		settings.setBounds(Video_Settings.window_size_x/4, 150, Video_Settings.window_size_x/2, 50);
		applysettings.setBounds(Video_Settings.window_size_x/4, Video_Settings.window_size_y-100, Video_Settings.window_size_x/2, 50);
		fullscreen.setBounds(Video_Settings.window_size_x/4, 50, Video_Settings.window_size_x/2, 50);
		play.setBounds(Video_Settings.window_size_x/4, 50, Video_Settings.window_size_x/2, 50);
		chi.setBounds(Video_Settings.window_size_x/4, 150, Video_Settings.window_size_x/2, 50);
		chi.setToolTipText("(Chi is the cat, (Not a goat))");
		exit.setBounds(Video_Settings.window_size_x/4, Video_Settings.window_size_y-100, Video_Settings.window_size_x/2, 50);
		mapsize.setBounds(Video_Settings.window_size_x/4, 250, Video_Settings.window_size_x/2, 50);
		mapsize.setText(Integer.toString(Video_Settings.mapsize));
		mapsizelabel.setBounds(Video_Settings.window_size_x/4-50, 250, Video_Settings.window_size_x/2, 50);
		music.setBounds(Video_Settings.window_size_x/4, 350, Video_Settings.window_size_x/2, 50);
		music.setToolTipText("Toggles music on and off.");
		maintext.setBounds(Video_Settings.window_size_x - Video_Settings.window_size_x/4,100, 200, 50);
		vsync.setBounds(Video_Settings.window_size_x/4, 450, Video_Settings.window_size_x/2, 50);
		framelimit.setBounds(Video_Settings.window_size_x/4, 550, Video_Settings.window_size_x/2, 50);
		framelimit.setText(Integer.toString(Video_Settings.framelimit));
		framelimitlabel.setBounds(Video_Settings.window_size_x/4-65, 550, Video_Settings.window_size_x/2, 50);
		framelimitlabel.setForeground(Color.CYAN);
		f.add(new MainMenu());
		settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Options menu
				f.add(fullscreen, this);
				f.add(applysettings, this);
				f.add(chi, this);
				f.add(music, this);
				f.add(mapsize);
				f.add(mapsizelabel);
				f.add(vsync);
				f.add(framelimit);
				f.add(framelimitlabel);
				f.remove(derp);
				f.add(derp);
				f.remove(exit);
				f.remove(play);
				f.remove(settings);
				f.remove(maintext);
				f.repaint();
			}
		});
		applysettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//MainMenu
				f.remove(fullscreen);
				f.remove(applysettings);
				f.remove(chi);
				f.remove(music);
				f.remove(mapsize);
				f.remove(mapsizelabel);
				Video_Settings.SaveConfig();
				Settings.Save();
				f.add(settings);
				f.add(play);
				f.add(exit);
				f.remove(derp);
				f.add(derp);
				f.add(maintext);
				f.repaint();
			}
		});
		fullscreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Fullscreen
				Settings.fullscreen = !Settings.fullscreen;
				Settings.Changed();
				//f.setExtendedState(Frame.MAXIMIZED_BOTH);
				//f.setUndecorated(true);
				if (Settings.fullscreen == true) {
					//Video_Settings.gs.setFullScreenWindow(f);
					f.setExtendedState(Frame.MAXIMIZED_BOTH);
					f.setVisible(true);
				}
				else {
					//Video_Settings.gs.setFullScreenWindow(null);
				}
				f.repaint();
				settings.setBounds(f.getSize().width/4, 150, f.getSize().width/2, 50);
				applysettings.setBounds(f.getSize().width/4, f.getSize().height-100, f.getSize().width/2, 50);
				fullscreen.setBounds(f.getSize().width/4, 50, f.getSize().width/2, 50);
				play.setBounds(f.getSize().width/4, 50, f.getSize().width/2, 50);
				chi.setBounds(f.getSize().width/4, 150, f.getSize().width/2, 50);
				chi.setToolTipText("(Chi is the cat, (Not a goat))");
				exit.setBounds(f.getSize().width/4, f.getSize().height-100, f.getSize().width/2, 50);
				mapsize.setBounds(f.getSize().width/4, 250, f.getSize().width/2, 50);
				mapsize.setText(Integer.toString(Video_Settings.mapsize));
				mapsizelabel.setBounds(f.getSize().width/4-50, 250, f.getSize().width/2, 50);
				music.setBounds(f.getSize().width/4, 350, f.getSize().width/2, 50);
				derp.setBounds(0, 0, f.getSize().width, f.getSize().height);
				ImageIcon dimg = new ImageIcon(bgr.getScaledInstance(f.getSize().width, f.getSize().height, BufferedImage.SCALE_SMOOTH));
				derp.setIcon(dimg);
				f.repaint();
			}
		});
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Play Game
				error = true;
				f.repaint();
				World2.main(null);
				//new World().main(null);
				error = false;
				f.dispose();
			}
		});
		chi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Chi
				f.repaint();
				Settings.chi =! Settings.chi;
				Settings.Changed();
				chi.setText("Chi: " + Settings.chi);
				//new World().main(null);
			}
		});
		music.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Music
				f.repaint();
				Settings.music =! Settings.music;
				Settings.Changed();
				music.setText("Music: " + Settings.music);
			}
		});
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Exit Game
				System.exit(0);
			}
		});
		vsync.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Toggle VSync
				Video_Settings.VSync = !Video_Settings.VSync;
				vsync.setText("Use Framelimit: " + Video_Settings.VSync);
				Video_Settings.SaveConfig();
			}
		});
		mapsize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//MapSize input
				try {
					Video_Settings.mapsize = Integer.parseInt(mapsize.getText());
					Video_Settings.SaveConfig();
				}
				catch (NumberFormatException ex) {
					Video_Settings.mapsize = 2400;
				}
			}
		});
		framelimit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Framelimit input
				try {
					Video_Settings.framelimit = Integer.parseInt(framelimit.getText());
					Video_Settings.SaveConfig();
				} catch (NumberFormatException ex) {
					Video_Settings.framelimit = 120;
				}
			}
		});
		f.add(settings);
		f.add(play);
		f.add(exit);
		f.add(maintext);
		f.setSize(Video_Settings.window_size_x, Video_Settings.window_size_y);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		try {
			File file = new File("images\\mainmenu.png");
			bgr = ImageIO.read(file);
			ImageIcon dimg = new ImageIcon(bgr.getScaledInstance(Video_Settings.window_size_x, Video_Settings.window_size_y, BufferedImage.SCALE_SMOOTH));
			derp = new JLabel();
			derp.setBounds(0, 0, f.getSize().width, f.getSize().height);
			derp.setIcon(dimg);
			f.add(derp);
		}
		catch (IOException ex) {
			//That's the mainmenu texture broken.
			System.out.println("Error in mainmenu texture: " + ex.getCause());
		}
		f.repaint();
	}
}
