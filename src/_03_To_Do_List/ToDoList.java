package _03_To_Do_List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ToDoList implements ActionListener {
	/*
	 * Create a program with five buttons, add task, view tasks, remove task, save list, and load list. 
	 *
	 * When add task is clicked:
	 * 		Create a JOptionPane to ask the user for a task and add it to an ArrayList
	 * 
	 * When the view tasks button is clicked:
	 *		show all the tasks in the list
	 * 
	 * When the remove task button is clicked:
	 * 		Create a JOptionPane to prompt the user for which task to remove and take it off of the list.
	 * 
	 * When the save list button is clicked:
	 * 		Save the list to a file
	 * 
	 * When the load list button is clicked:
	 * 		Create a JOptionPane to Prompt the user for the location of the file and load the list from that file
	 */
	
	JFrame frame;
	
	JPanel panel;
	
	JButton addTask;
	JButton viewTasks;
	JButton removeTask;
	JButton saveList;
	JButton loadList;
	
	ArrayList<String> list = new ArrayList<String>();
	
	public ToDoList() {
		start();
	}
	
	public static void main(String[] args) {
		ToDoList tdl = new ToDoList();
	}
	
	void start() {
		frame = new JFrame();
		panel = new JPanel();
		addTask = new JButton();
		viewTasks = new JButton();
		removeTask = new JButton();
		saveList = new JButton();
		loadList = new JButton();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.add(addTask);
		panel.add(viewTasks);
		panel.add(removeTask);
		panel.add(saveList);
		panel.add(loadList);
		addTask.setText("Add Task");
		viewTasks.setText("View Tasks");
		removeTask.setText("Remove Task");
		saveList.setText("Save List");
		loadList.setText("Load List");
		addTask.addActionListener(this);
		viewTasks.addActionListener(this);
		removeTask.addActionListener(this);
		saveList.addActionListener(this);
		loadList.addActionListener(this);
		frame.pack();
	}
	
	void load(String file) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			list.clear();
			while(line != null){
				list.add(line);
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(addTask)) {
			String input = JOptionPane.showInputDialog(null, "Please enter the task.");
			list.add(input);
		}else if (e.getSource().equals(viewTasks)) {
			for (int i = 0; i < list.size(); i++) {
				JOptionPane.showMessageDialog(null, "Task " + (i + 1) + ": " + list.get(i));
			}
		}else if (e.getSource().equals(removeTask)) {
			String input = JOptionPane.showInputDialog(null, "Please enter the task.");
			for (int i = 0; i < list.size(); i++) {
				if (input.toLowerCase().equals(list.get(i).toLowerCase())) {
					list.remove(i);
				}
			}
		}else if (e.getSource().equals(saveList)) {
			try {
				String input = JOptionPane.showInputDialog(null, "Please enter the desired save location.");
				FileWriter fw = new FileWriter(input);
				String listI = "";
				for (int i = 0; i < list.size(); i++) {
					listI+=list.get(i)+"\n";
				}
				fw.write(listI);
				fw.close();
				JOptionPane.showMessageDialog(null, "List saved.");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if (e.getSource().equals(loadList)) {
			String input = JOptionPane.showInputDialog(null, "Please enter the file name.");
			load(input);
		}
	}
}
