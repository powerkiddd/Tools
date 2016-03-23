package Main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class Client {
	
	public static Socket socket;
	public static DataInputStream in;
	public static DataOutputStream out;
	
	public static void Connect(String IP, int port) {
		try {
			socket = new Socket(IP, port);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			ClientInput input = new ClientInput(in);
			Thread thread = new Thread(input);
			thread.start();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
