package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadFromFile {
	
	public static String readstuff (String path, String filename, String split) {
		try {
			File file = new File(path + "/" + filename);
			if (!file.exists()) {
				return null;
			}
			BufferedReader reader;
			reader = new BufferedReader(new FileReader(file));
			String derp = "";
			String notderp = "";
			while ((notderp = reader.readLine()) != null) {
				derp = derp + split + notderp;
			}
			reader.close();
			return derp;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
}