package me.zander.jedit;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class Gui {
	
	public static JFrame get() {
		JFrame f = new JFrame("JEdit Text Editor - " + Main.currentFile);
		
		// settings
		f.setSize(1600, 1000);
		f.setDefaultCloseOperation(3);
		f.setAlwaysOnTop(true);
		f.getContentPane().setBackground(Color.BLACK);
		f.setLocationRelativeTo(null);
		
		//close on ESC
		KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
		Action escapeAction = new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
				Main.log("Closing!");
				f.dispose();
				System.exit(-1);
			}
		};
		f.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
		f.getRootPane().getActionMap().put("ESCAPE", escapeAction);
		
		
		//load data from file
		StringBuilder builder = new StringBuilder();
		try (BufferedReader buffer = new BufferedReader(new FileReader(Main.currentFile))) {
			String str;
			while ((str = buffer.readLine()) != null) {
				builder.append(str).append("\n");
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		Main.originalFileContents = builder.toString();
		
		//input field
		JTextArea textfield = new JTextArea(Main.originalFileContents);
		f.add(textfield);
		
		
		//save on ctrl s
		(new Thread() {
			public void run() {
				while(true) {
					if(Keyboard.isKeyPressed(KeyEvent.VK_CONTROL) && Keyboard.isKeyPressed(KeyEvent.VK_S)) {
						Main.log("Saving!");
						String toSave = textfield.getText();
						FileSaver.save(Main.currentFile, toSave);
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
		
		//scrolling
		JScrollPane scroll = new JScrollPane(textfield, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		f.add(scroll);
		
		return f;
	}
}
