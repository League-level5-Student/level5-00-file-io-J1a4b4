package _04_Directory_Iteration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DirectoryIterator {
	public static void main(String[] args) {
		/*
		 * The following is an example of how to list all of the files in a directory.
		 * Once the program is running, the directory is chosen using the JFileChooser.
		 */
		// JFileChooser jfc = new JFileChooser();
		// jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		// int returnVal = jfc.showOpenDialog(null);
		// if (returnVal == JFileChooser.APPROVE_OPTION) {
		// File directory = jfc.getSelectedFile();
		// File[] files = directory.listFiles();
		// if(files != null) {
		// for(File f : files) {
		// System.out.println(f.getAbsolutePath());
		// }
		// }
		// }

		/*
		 * Your task is to write a program that iterates through the src folder of this
		 * current Java Project. For every .java file it finds, the program will add a
		 * (non-legally binding) copyright statement at the bottom. Be aware of possible
		 * directories inside of directories. (e.g //Copyright © 2019 FirstName
		 * LastName)
		 */
		DirectoryIterator dI = new DirectoryIterator();
		dI.iterate();
	}

	void iterate() {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Java Files", "java");
		jfc.setFileFilter(filter);
		int returnVal = jfc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File directory = jfc.getSelectedFile();
			File[] files = directory.listFiles();
			FileWriter fw;
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					File[] filesI = files[i].listFiles();
					for (File f : filesI) {
						try {
							fw = new FileWriter(f.getAbsolutePath(), true);
							fw.write("\n \n //Copyright © 2020 League of Amazing Programmers and Jonas Brown");
							fw.close();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}

 
 //Copyright © 2020 League of Amazing Programmers and Jonas Brown