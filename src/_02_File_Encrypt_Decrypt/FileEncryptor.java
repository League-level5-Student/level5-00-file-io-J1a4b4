package _02_File_Encrypt_Decrypt;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileEncryptor {
	/*
	 * Encryption is the process of encoding a message or information
	 * in such a way that only authorized parties can access it and
	 * those who are not authorized cannot.
	 *
	 * A simple shift cipher works by shifting the letters of a message
	 * down based on a key. If our key is 4 then:
	 * 
	 * a b c d e f g h i j k l m n o p q r s t u v w x y z
	 * 
	 * becomes:
	 *
	 * e f g h i j k l m n o p q r s t u v w x y z a b c d
	 * 
	 * "Hello World" changes to "Lipps Asvph"
	 *
	 * Create a program that takes a message and a key from the user.
	 * Use the key to shift each letter in the users input and save the final result to a file.
	 */
	
	public static void main(String[] args) {
		FileEncryptor fE = new FileEncryptor();
		fE.encrypt();
	}
	
	public void encrypt() {
		try {
			FileWriter fw = new FileWriter("src/_02_File_Encrypt_Decrypt/information.txt");
			String text = JOptionPane.showInputDialog(null, "Please enter the information you wish to be encrypted.");
			String pNum = JOptionPane.showInputDialog(null, "Please enter the encryption key.");
			int key = Integer.parseInt(pNum);
			if (key > 26) {
				key = key % 26;
			}
			text = text.toLowerCase();
			for (int i = 0; i < text.length(); i++) {
				shift(text.charAt(i), key);
			}
			fw.write(key * 2 + " " + text);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void shift(char c, int key) {
		int code = 0;
		if (c == 'a') {
			code = 1;
		}else if (c == 'b') {
			code = 2;
		}else if (c == 'c') {
			code = 3;
		}else if (c == 'd') {
			code = 4;
		}else if (c == 'e') {
			code = 5;
		}else if (c == 'f') {
			code = 6;
		}else if (c == 'g') {
			code = 7;
		}else if (c == 'h') {
			code = 8;
		}else if (c == 'i') {
			code = 9;
		}else if (c == 'j') {
			code = 10;
		}else if (c == 'k') {
			code = 11;
		}else if (c == 'l') {
			code = 12;
		}else if (c == 'm') {
			code = 13;
		}else if (c == 'n') {
			code = 14;
		}else if (c == 'o') {
			code = 15;
		}else if (c == 'p') {
			code = 16;
		}else if (c == 'q') {
			code = 17;
		}else if (c == 'r') {
			code = 18;
		}else if (c == 's') {
			code = 19;
		}else if (c == 't') {
			code = 20;
		}else if (c == 'u') {
			code = 21;
		}else if (c == 'v') {
			code = 22;
		}else if (c == 'w') {
			code = 23;
		}else if (c == 'x') {
			code = 24;
		}else if (c == 'y') {
			code = 25;
		}else if (c == 'z') {
			code = 26;
		}
		code = code - key;
		if (code < 1) {
			code += 26;
		}
		if (code == 1) {
			c = 'a';
		}else if (code == 2) {
			c = 'b';
		}else if (code == 3) {
			c = 'c';
		}else if (code == 4) {
			c = 'd';
		}else if (code == 5) {
			c = 'e';
		}else if (code == 6) {
			c = 'f';
		}else if (code == 7) {
			c = 'g';
		}else if (code == 8) {
			c = 'h';
		}else if (code == 9) {
			c = 'i';
		}else if (code == 10) {
			c = 'j';
		}else if (code == 11) {
			c = 'k';
		}else if (code == 12) {
			c = 'l';
		}else if (code == 13) {
			c = 'm';
		}else if (code == 14) {
			c = 'n';
		}else if (code == 15) {
			c = 'o';
		}else if (code == 16) {
			c = 'p';
		}else if (code == 17) {
			c = 'q';
		}else if (code == 18) {
			c = 'r';
		}else if (code == 19) {
			c = 's';
		}else if (code == 20) {
			c = 't';
		}else if (code == 21) {
			c = 'u';
		}else if (code == 22) {
			c = 'v';
		}else if (code == 23) {
			c = 'w';
		}else if (code == 24) {
			c = 'x';
		}else if (code == 25) {
			c = 'y';
		}else if (code == 26) {
			c = 'z';
		}
	}
}
