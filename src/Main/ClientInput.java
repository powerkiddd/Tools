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
				String[] splitmsg = message.split(":");
				if (splitmsg[0].equals("PIS")) {
					Players.playersinserver = Byte.parseByte(splitmsg[1]);
				}
				if (splitmsg[0].equals("PPOS")) {
					String[] ppos = splitmsg[1].split(",");
					System.out.println(message);
					if (ppos.length > 2) {
						if (!ppos[2].equals(Client.id)) {
							Players.playerx[Byte.parseByte(ppos[2])] = (int) Double.parseDouble(ppos[0]);
							Players.playery[Byte.parseByte(ppos[2])] = (int) Double.parseDouble(ppos[1]);
						}
					}
				}
				if (splitmsg[0].equals("AID")) {
					Client.id = Byte.parseByte(splitmsg[1]);
				}
				//System.out.println(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
