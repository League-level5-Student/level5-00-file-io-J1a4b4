package _06_Pixel_Art_Save_State;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PixelArtMaker implements MouseListener, ActionListener{
	private JFrame window;
	private GridInputPanel gip;
	private GridPanel gp;
	ColorSelectionPanel csp;
	
	JButton save;
	JButton load;
	
	private final String SAVEFILELOCATION = "src/_06_Pixel_Art_Save_State/SaveFile.dat";
	
	public void start() {
		gip = new GridInputPanel(this);
		window = new JFrame("Pixel Art");
		window.setLayout(new FlowLayout());
		window.setResizable(false);
		save = new JButton();
		load = new JButton();
		save.setText("Save");
		load.setText("Load");
		save.addActionListener(this);
		load.addActionListener(this);
		
		window.add(gip);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public void loadGridData() {
		if (new File(SAVEFILELOCATION).exists()) {
			gp = loadGrid();
			setUpGrid();
		}else {
			JOptionPane.showMessageDialog(null, "No file exists at " + SAVEFILELOCATION + ".");
		}
	}
	
	public void submitGridData(int w, int h, int r, int c) {
		gp = new GridPanel(w, h, r, c);
		setUpGrid();
	}
	
	public void setUpGrid() {
		csp = new ColorSelectionPanel();
		window.remove(gip);
		window.add(gp);
		window.add(csp);
		gp.repaint();
		gp.addMouseListener(this);
		window.add(save);
		window.add(load);
		window.pack();
	}
	
	public static void main(String[] args) {
		new PixelArtMaker().start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		gp.setColor(csp.getSelectedColor());
		gp.clickPixel(e.getX(), e.getY());
		gp.repaint();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.save)) {
			saveState(gp);
		}else if (e.getSource().equals(this.load)) {
			window.getContentPane().removeAll();
			loadGridData();
		}
	}
	
	void saveState(GridPanel grid) {
		try {
			FileOutputStream f = new FileOutputStream(new File(SAVEFILELOCATION));
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(grid);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	GridPanel loadGrid() {
		GridPanel saveGrid = null;
		try {
			FileInputStream f = new FileInputStream(new File(SAVEFILELOCATION));
			ObjectInputStream o = new ObjectInputStream(f);
			saveGrid = (GridPanel) o.readObject();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Previous save file not found.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return saveGrid;
	}
}

 //Copyright © 2020 League of Amazing Programmers and Jonas Brown
