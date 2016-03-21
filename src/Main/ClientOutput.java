package Main;

import java.io.DataOutputStream;
import java.io.IOException;

import Game.Player;
import Game.Player2;
import Game.World2;

public class ClientOutput implements Runnable {
	
	DataOutputStream out;
	int ms = 0;
	
	public ClientOutput (DataOutputStream out) {
		this.out = out;
	}
	
	public void run () {
		while(true) {
			try {
				
			} catch (Exception ex) {
				ex.printStackTrace();
				System.exit(0);
			}
		}
	}
	
}
