package Main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Directory { 
	
	public static int i = 0;
	public static String[] allfiles = new String[0];
	
	public static String[] GetAllFilesFromDirectory (String dir) {
		//allfiles = new String[0];
		i = 0;
		try {
			allfiles = new String[(int) Files.walk(Paths.get(dir)).count()];
			Files.walk(Paths.get(dir)).forEach(filePath -> {
			    if (Files.isReadable(filePath)) {
					allfiles[i] = filePath.toString();
			        i = i + 1;
			    }
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return allfiles;
	}
	
}
