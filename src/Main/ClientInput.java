package Main;

import java.io.DataInputStream;
import java.io.IOException;

public class ClientInput implements Runnable {
	
	DataInputStream in;
	
	public ClientInput (DataInputStream in) {
		this.in = in;
	}

	public void run() {
		while(true) {
			try {
				
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

}
