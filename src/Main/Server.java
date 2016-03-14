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
	
	public static void main(String[] args) {
		try {
			serversocket = new ServerSocket(port);
			System.out.println("Server started!");
			while(true) {
				System.out.println("Waiting for connections...");
				socket = serversocket.accept();
				for (byte i = 0; i < 10; i++) {
					out = new DataOutputStream(socket.getOutputStream());
					out.writeUTF("Kaasknal");
					in = new DataInputStream(socket.getInputStream());
					if (player[i] == null) {
						player[i] = new Players(out,in,player);
						Thread thread = new Thread();
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