package Main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Players implements Runnable {

	DataOutputStream out;
	DataInputStream in;
	Players[] player = new Players[10];
	public Players(DataOutputStream out, DataInputStream in, Players[] player) {
		this.in = in;
		this.out = out;
		this.player = player;
	}
	public void run() {
		while(true) {
			try {
				String message = in.readUTF();
				for (byte i = 0; i < 10; i++) {
					if (player[i] != null) {
						player[i].out.writeUTF(message);
					}
				}
			} catch (IOException e) {
				this.out = null;
				this.in = null;
				e.printStackTrace();
			}
		}
	}
	
}