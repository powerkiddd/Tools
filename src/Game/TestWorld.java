package Game;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Game.TestPlayer;
import Main.Version;

public class TestWorld extends JFrame{

	private static BufferedImage image;
	JFrame f = new JFrame();
	JPanel p = new JPanel();
	TestWorld2 test = new TestWorld2();
	
	public void TestWorld () {
		f.getContentPane().add(test, BorderLayout.CENTER);
		f.setVisible(true);
		f.resize(640, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		try {
			File file = new File("images\\skybox.jpg");
			image = ImageIO.read(file);
			}
			catch (IOException ex) {
				//je weet zeluf
		}
		System.out.println("Derp");
	}
	
	public void main (String [] args) {
		System.out.println("herp");
		//new TestWorld();
	}
	
	public class TestWorld2 extends JPanel{
		TestWorld2() {
			p.add(new TestPlayer());
			System.out.println("doos");
		}
	}
	
}