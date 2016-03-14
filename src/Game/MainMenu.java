package Game;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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
	static JButton b1 = new JButton("Settings");
	static JButton b2 = new JButton("Apply");
	static JButton b3 = new JButton("Fullscreen: " + Settings.fullscreen);
	static JButton b4 = new JButton("Play Game");
	static JButton b5 = new JButton("Chi: " + Settings.chi);
	static JButton exit = new JButton("Exit Game");
	static JButton invisible = new JButton("");
	static JButton music = new JButton("Music: " + Settings.music);
	static JTextField mapsize = new JTextField("INPUT MAPSIZE HERE");
	static JLabel mapsizelabel = new JLabel("Mapsize: ");
	static JLabel derp;
	static JLabel maintext = new JLabel("As suggested by Mees... MEES...");
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
		switch (rand.nextInt(3)) {
		case 0:
			maintext.setText("As suggested by Mees... MEES...");
			break;
		case 1:
			maintext.setText("Programmed by Kevin Wouda!");
			break;
		case 2:
			maintext.setText("PHYSICS!!!!");
			break;
		}
		b3.setText("Fullscreen: " + Settings.fullscreen);
		b5.setText("Chi: " + Settings.chi);
		music.setText("Music: " + Settings.music);
		f.setLayout(null);
		b1.setBounds(Video_Settings.window_size_x/4, 150, Video_Settings.window_size_x/2, 50);
		b2.setBounds(Video_Settings.window_size_x/4, Video_Settings.window_size_y-100, Video_Settings.window_size_x/2, 50);
		b3.setBounds(Video_Settings.window_size_x/4, 50, Video_Settings.window_size_x/2, 50);
		b4.setBounds(Video_Settings.window_size_x/4, 50, Video_Settings.window_size_x/2, 50);
		b5.setBounds(Video_Settings.window_size_x/4, 150, Video_Settings.window_size_x/2, 50);
		b5.setToolTipText("(Chi is the cat, (Not a goat))");
		exit.setBounds(Video_Settings.window_size_x/4, Video_Settings.window_size_y-100, Video_Settings.window_size_x/2, 50);
		mapsize.setBounds(Video_Settings.window_size_x/4, 250, Video_Settings.window_size_x/2, 50);
		mapsize.setText(Integer.toString(Video_Settings.mapsize));
		mapsizelabel.setBounds(Video_Settings.window_size_x/4-50, 250, Video_Settings.window_size_x/2, 50);
		music.setBounds(Video_Settings.window_size_x/4, 350, Video_Settings.window_size_x/2, 50);
		music.setToolTipText("Toggles music on and off.");
		maintext.setBounds(Video_Settings.window_size_x - Video_Settings.window_size_x/4,100, 200, 50);
		f.add(new MainMenu());
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Options menu
				System.out.println("You clikced button1!");
				f.add(b3, this);
				f.add(b2, this);
				f.add(b5, this);
				f.add(music, this);
				f.add(mapsize);
				f.add(mapsizelabel);
				f.remove(derp);
				f.add(derp);
				f.remove(exit);
				f.remove(b4);
				f.remove(b1);
				f.remove(maintext);
				f.repaint();
				System.out.println("repainted for buttons");
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//MainMenu
				System.out.println("You clikced button2!");
				f.remove(b3);
				f.remove(b2);
				f.remove(b5);
				f.remove(music);
				f.remove(mapsize);
				f.remove(mapsizelabel);
				Video_Settings.SaveConfig();
				f.add(b1);
				f.add(b4);
				f.add(exit);
				f.remove(derp);
				f.add(derp);
				f.add(maintext);
				f.repaint();
				System.out.println("repainted for buttons");
			}
		});
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Fullscreen
				Settings.fullscreen = !Settings.fullscreen;
				Settings.Changed();
				b3.setText("Fullscreen: " + Settings.fullscreen);
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
				b1.setBounds(f.getSize().width/4, 150, f.getSize().width/2, 50);
				b2.setBounds(f.getSize().width/4, f.getSize().height-100, f.getSize().width/2, 50);
				b3.setBounds(f.getSize().width/4, 50, f.getSize().width/2, 50);
				b4.setBounds(f.getSize().width/4, 50, f.getSize().width/2, 50);
				b5.setBounds(f.getSize().width/4, 150, f.getSize().width/2, 50);
				b5.setToolTipText("(Chi is the cat, (Not a goat))");
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
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Play Game
				System.out.println("You clicked button4!");
				error = true;
				f.repaint();
				World2.main(null);
				//new World().main(null);
				error = false;
				f.dispose();
			}
		});
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Chi
				System.out.println("You clicked button5!");
				f.repaint();
				Settings.chi =! Settings.chi;
				b5.setText("Chi: " + Settings.chi);
				//new World().main(null);
			}
		});
		music.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Music
				System.out.println("You clicked the music button!");
				f.repaint();
				Settings.music =! Settings.music;
				music.setText("Music: " + Settings.music);
			}
		});
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Exit Game
				System.exit(0);
			}
		});
		mapsize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//MapSize input
				System.out.println("mapsize changed!");
				try {
					Video_Settings.mapsize = Integer.parseInt(mapsize.getText());
				}
				catch (NumberFormatException ex) {
					Video_Settings.mapsize = 2400;
				}
			}
		});
		f.add(b1);
		f.add(b4);
		f.add(exit);
		f.add(maintext);
		f.setSize(Video_Settings.window_size_x, Video_Settings.window_size_y);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		try {
			File file = new File("images\\mainmenu2.png");
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
		TimerTask update = new TimerTask () {
			public void run () {
				
			}
		};
		update_.scheduleAtFixedRate(update, 10, 10);
		f.repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bgr, 0, 0, Video_Settings.window_size_x, Video_Settings.window_size_y, null);
		g.drawString(Version.version, 0, 10);
		if (bgr == null) {
			f.repaint();
			System.out.println("Repainted in mainmenu");
		}
		if (Settings.fullscreen == true) {
			g.drawString("FULLSCREEN NOT YET SUPPORTED", 100, 10);
			f.repaint();
		}
		if (error == true) {
			g.drawString("An unexpected error has occured, please report this to the developer.", 0, 100);
		}
	}

}
