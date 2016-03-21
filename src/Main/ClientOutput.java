package Main;

import java.io.DataOutputStream;
import java.io.IOException;

import Game.Player;
import Game.Player2;

public class ClientOutput implements Runnable {
	
	DataOutputStream out;
	public static Boolean playermoved = true;
	
	public ClientOutput (DataOutputStream out) {
		this.out = out;
	}
	
	public void run () {
		while(true) {
			try {
				if (playermoved) {
					out.writeUTF("PPOS:" + Player2.player_x + "," + Player2.player_y + "," + Client.id);
					playermoved = false;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
