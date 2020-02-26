package utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class FileHandling {

	
	public static void createFile(String pathstr) {
		
		Path path = Paths.get(pathstr);
	    //if directory exists?
	    if (!Files.exists(path)) {
	        try {
	            Files.createDirectories(path);
	        } catch (IOException e) {
	            //fail to create directory
	            e.printStackTrace();
	        }
	    }
	}
	
}
