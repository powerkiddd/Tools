package Main;

import java.io.DataOutputStream;

public class ClientOutput implements Runnable {
	
	DataOutputStream out;
	
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
