package _02_File_Encrypt_Decrypt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileDecryptor {
	/*
	 * Decryption is the process of taking encoded or encrypted text or other data
	 * and converting it back into text that you or the computer can read and understand.
	 *
	 * The shift cipher is decrypted by using using the key and shifting back up,
	 * at the end of our encryption example we had our alphabet as:
	 *
	 * e f g h i j k l m n o p q r s t u v w x y z a b c d
	 *
	 * If we shift it back up by 4 based on the key we used the alphabet is decrypted:
	 *
	 * a b c d e f g h i j k l m n o p q r s t u v w x y z
	 *
	 * "Lipps Asvph" returns to "Hello World"
	 * 
	 * Create a program that opens the file created by FileEncryptor and decrypts
	 * the message, then display it to the user in a JOptionPane.
	 */
	
	public static void main(String[] args) {
		FileDecryptor fd = new FileDecryptor();
		fd.decrypt();
	}
	
	public void decrypt() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/_02_File_Encrypt_Decrypt/information.txt"));
			String line = br.readLine();
			String input = JOptionPane.showInputDialog(null, "Please enter the encryption key.");
			int key = Integer.parseInt(input);
			key = key % 26;
			String message = "";
			while (line != null) {
				for (int i = 0; i < line.length(); i++) {
					message += backShift(line.charAt(i), key);
				}
				JOptionPane.showMessageDialog(null, message);
				message = "";
				line = br.readLine();
			}
			br.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public char backShift(char c, int key) {
		if (c != ' ') {
			c -= key;
		}
		return c;
	}
}