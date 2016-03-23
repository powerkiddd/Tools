package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Game.Build;
import Game.World2;

public class Input {
	
	public static boolean a = false;
	public static boolean d = false;
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
					World2.debug = f3;
				}
				if (e.getKeyCode() == 115) {
					f4 =! f4;
					World2.debuggrid = f4;
				}
				if (e.getKeyCode() == 27) {
					escape =! escape;
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
		return false;
	}
}
