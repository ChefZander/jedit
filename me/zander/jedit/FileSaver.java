package me.zander.jedit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSaver {
	public static void save(String filename, String str) {
        Path path = Paths.get(filename);
        byte[] arr = str.getBytes();
 
        try {
            Files.write(path, arr);
        }
        catch (IOException ex) {
            System.out.print("Invalid Path");
        }
	}
}
