package Game;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Blocks.GrassBlock;
import Main.Mouse;
import Main.Version;
import Settings.Video_Settings;

public class World extends JPanel implements ActionListener {
	
	private static BufferedImage image;
	private static BufferedImage playerimage;
	private static BufferedImage grassblock;
	public static JFrame f = new JFrame();
	public static JPanel p = new JPanel();
	private static boolean once = false;
	public static boolean cangenerategrass;
	
	public static void main(String [] args) {	
		try {
			Video_Settings.main(null);
			PauseMenu.main(null);
			Mouse.main(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		GrassBlock.test();
		System.out.println("This is currently beta " + Version.version);
		f.add(new World());
		f.setSize(Video_Settings.window_size_x, Video_Settings.window_size_y);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		try {
			File file = new File("images\\skybox.jpg");
			File file2 = new File("images\\player.png");
			File file3 = new File("images\\grass.png");
			image = ImageIO.read(file);
			playerimage = ImageIO.read(file2);
			grassblock = ImageIO.read(file3);
			}
			catch (IOException ex) {
				ex.getStackTrace();
			}
	    f.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	if (PauseMenu.paused == true) {
	        		if (Mouse.gamecursorrect.intersects(PauseMenu.mainmenubuttonrect)) {
	        			if (e.getButton() == 1) {
	        				System.out.println("Exiting to mainmenu");
	        				Player.pause = false;
	        				PauseMenu.paused = false;
	        				GrassBlock.totalblockzcount = 0;
	        				GrassBlock.shouldcreate = 0;
	        				GrassBlock.totalblockzcountcount = 1;
	        				GrassBlock.count = 0;
	        				BlockStorage.totalgrassblocks = 0;
	        				BlockStorage.totalblocks = 0;
	        				BlockStorage.grassblocks = new Rectangle[0];
	        				GrassBlock.everyblock = new Rectangle[0];
	        				GrassBlock.xinrange = true;
	        				GrassBlock.anyinrange = false;
	        				GrassBlock.anyinyrange = false;
	        				GrassBlock.anyinrange = false;
	        				GrassBlock.x = new int[0];
	        				GrassBlock.y = new int[0];
	        				GrassBlock.x2 = new int[0];
	        				GrassBlock.y2 = new int[0];
	        				GrassBlock.totalblockz = new int[0];
	        				GrassBlock.busy = false;
	        				once = false;
	        				f.remove(f);
	        				MainMenu.main(null);
	        				f.dispose();
	        			}
	        		}
	        	}
	        }
	    });
	}
	
	protected void paintComponent(Graphics g) {
		if (once == false) {
		dingest();
		once = true;
		}
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
		for (int i = 0;i < GrassBlock.totalblockzcount;i++) {
		int grassx = GrassBlock.x[i];
		int grassy = GrassBlock.y[i];
		g.drawImage(grassblock, grassx, grassy, null);
		}
		g.drawString("This is currently " + Version.version + "!", 0, 10);
		if (PauseMenu.paused == true) {
			g.drawImage(PauseMenu.mainmenubutton, Video_Settings.window_size_x / 2 - 110, 0, null);
			g.drawImage(Mouse.gamecursor, MouseInfo.getPointerInfo().getLocation().x - 2, MouseInfo.getPointerInfo().getLocation().y - 25, null);
			Player.x = MouseInfo.getPointerInfo().getLocation().x;
			Player.y = MouseInfo.getPointerInfo().getLocation().y;
		}
		g.drawImage(playerimage, Player.x, (int) Player.y, null);
		GrassBlock.Update();
		BlockStorage.Store();
		Player.Update();
		PauseMenu.Update();
		Mouse.Recalculate_Rect();
		if (cangenerategrass == true) {
			GrassBlock.Generate();
		}
		f.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Player.move(null);
	}
	
	public void dingest() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		System.out.println("yay3");
	}
	
    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == 65 || e.getKeyCode() == 68) {
            	Player.xspeed = 0;
            }
        }

        public void keyPressed(KeyEvent e) {
            Player.move(e);
            //System.out.println(e);
            
            int key = e.getKeyCode();
            //System.out.println(key);
        }
    }
    
}
