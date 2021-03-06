package Main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class Server {
	
	public static ServerSocket serversocket;
	public static Socket socket;
	public static DataOutputStream out;
	public static DataInputStream in;
	public static int port = 8888;
	public static Players[] player = new Players[10];
	public static byte playersinserver = 0;
	
	public static void main(String[] args) {
		try {
			serversocket = new ServerSocket(port);
			System.out.println("Server started!");
			while(true) {
				System.out.println("Waiting for connections...");
				socket = serversocket.accept();
				playersinserver = 0;
				for (byte i = 0; i < 10; i++) {
					playersinserver++;
					out = new DataOutputStream(socket.getOutputStream());
					in = new DataInputStream(socket.getInputStream());
					if (player[i] == null) {
						player[i] = new Players(out,in,player);
						Thread thread = new Thread(player[i]);
						thread.start();
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}