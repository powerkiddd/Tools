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
			String message;
			try {
				message = in.readUTF();
				System.out.println(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
