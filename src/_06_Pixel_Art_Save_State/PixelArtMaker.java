package _06_Pixel_Art_Save_State;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;

public class PixelArtMaker implements MouseListener{
	private JFrame window;
	private GridInputPanel gip;
	private GridPanel gp;
	ColorSelectionPanel csp;
	
	public void start() {
		gip = new GridInputPanel(this);	
		window = new JFrame("Pixel Art");
		window.setLayout(new FlowLayout());
		window.setResizable(false);
		
		window.add(gip);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public void submitGridData(int w, int h, int r, int c) {
		gp = new GridPanel(w, h, r, c);
		csp = new ColorSelectionPanel();
		window.remove(gip);
		window.add(gp);
		window.add(csp);
		gp.repaint();
		gp.addMouseListener(this);
		window.pack();
	}
	
	public void encrypt(Pixel[][] p) {
		try {
			FileWriter fw = new FileWriter("src/_06_Pixel_Art_Save_State/save.txt");
			String line = "";
			for (int i = 0; i < p.length; i++) {
				for (int j = 0; j < p[i].length; j++) {
					line += (p[i][j].toString());
				}
				line += "\n";
			}
			fw.write(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void downlink() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/_06_Pixel_Art_Save_State/save.txt"));
			String line = "";
			int lineN = 0;
			while(line != null){
				line = br.readLine();
				decrypt(line, gp.pixels[lineN]);
				lineN++;
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void decrypt(String s, Pixel[] p) {
		for (int i = 0; i + 9 < s.length(); i+=9) {
			p[i].color = Color.decode(s.substring(i, i+9));
		}
	}
	
	public static void main(String[] args) {
		new PixelArtMaker().start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource().equals(csp.sButton)) {
			
		}else {
			gp.setColor(csp.getSelectedColor());
			gp.clickPixel(e.getX(), e.getY());
			gp.repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}

 //Copyright © 2020 League of Amazing Programmers and Jonas Brown
