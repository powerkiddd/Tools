package Main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteToFile {
	
	public static void writestuff (String path,String filename, String fileextension, String text) {
		try {
			File file = new File(path + filename + fileextension);
			File file2 = new File(path);
			if (file2.exists()) {
				if (file.createNewFile()) {
					PrintWriter writer = new PrintWriter(path + filename + fileextension, "UTF-8");
					writer.println(text);
					writer.close();
				}
				else {
					file.delete();
					file.createNewFile();
					PrintWriter writer = new PrintWriter(path + filename + fileextension, "UTF-8");
					writer.println(text);
					writer.close();
				}
			}
			else{
				file2.mkdir();
				writestuff(path, filename, fileextension, text);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}