package Main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

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
			Scanner sc = new Scanner(System.in);
			while (true) {
				String sendMessage = sc.nextLine();
				out.writeUTF(sendMessage);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
