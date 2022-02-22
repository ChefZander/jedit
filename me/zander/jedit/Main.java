package me.zander.jedit;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
	
	public static String currentFile = "random_file.txt";
	public static String originalFileContents = "";
	
	public static void log(String msg) {
		System.out.println("-> " + msg);
	}
	
	public static void main(String[] args) {
		log("JVM Started Execution");
		currentFile = args[0];
		log("Initializing...");
		try {
			UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JFrame f = Gui.get();
		log("Done!");
		f.setVisible(true);
	}

}
