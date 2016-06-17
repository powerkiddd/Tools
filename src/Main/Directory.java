package Main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class Directory { 
	
	public static int i = 0;
	public static String[] allfiles = new String[0];
	public static BufferedImage[] allimages = new BufferedImage[0];
	public static String[] identifiers = new String[0];
	public static String[] alldirs;
	
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
	
	public static String[] GetAllDirectoriesFromDirectory (String dir) {
		i = 0;
		try {
			alldirs = new String[(int) Files.walk(Paths.get(dir)).count()];
			Files.walk(Paths.get(dir)).forEach(filePath -> {
			    if (Files.isReadable(filePath) && Files.isDirectory(filePath)) {
					alldirs[i] = filePath.toString();
			        i = i + 1;
			    }
			});			
			String[] olddirs = alldirs;
			alldirs = new String[i];
			i = 0;
			for (int j = 0; j < alldirs.length; j++) {
				String[] split = olddirs[j].split("\\\\");
				if (olddirs[j] != null && !olddirs[j].isEmpty() && split.length > 1 && !split[1].isEmpty()) {
					alldirs[i] = split[1];
					i = i + 1;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return alldirs;
	}
	
	public static BufferedImage[] GetAllImagesFromDirectory (String dir) {
		i = 0;
		try {
			allimages = new BufferedImage[(int) Files.walk(Paths.get(dir)).count()];
			identifiers = new String[(int) Files.walk(Paths.get(dir)).count()];
			Files.walk(Paths.get(dir)).forEach(filePath -> {
			    if (Files.isReadable(filePath)) {
			    	String splitthis = filePath.getFileName().toString();
			    	if (splitthis.contains(".")) {
				    	String[] derp = splitthis.split("\\.");
				    	if (derp[1].equals("png") || derp[1].equals("jpg")) {
					    	File file = new File(filePath.toString());
							try {
								allimages[i] = ImageIO.read(file);
								identifiers[i] = derp[0];
							} catch (Exception e) {
								e.printStackTrace();
							}
					        i = i + 1;
				    	}
			    	}
			    }
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return allimages;
	}
	
	public static void CreateNewDirectory (String name) {
		new File(name+"\\").mkdirs();
	}
	
	public static boolean Exists (String name) {
		return new File(name).exists();
	}
	
}
