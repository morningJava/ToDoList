
package ToDo.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import ToDo.Item;
import ToDo.model.ToDoList;

public class ToDoApplication extends JFrame implements ActionListener {

	/** Version ID */
	private static final long serialVersionUID = 1L;
	/** Access to Item class */
	private Item item;
	/** Access to ToDoList class */
	private ToDoList list;
	/** Visual list of Items for the frame */
	private JList<Item> tasks;
	/** Dialog box for adding a task to the list */
	 private AddDialog addDialog;
	/** Allows user to specify read & write location */
	private JFileChooser chooser;
	/** Writes the list object to user specified location */
	private JMenuItem saveMenu;
	/** Gets list from user specified location */
	private JMenuItem openMenu;
	/** Exits the Application */
	private JMenuItem exitMenu;
	/** Adds item to the list */
	private JMenuItem addMenu;
	/** Removes items from list */
	private JMenuItem removeMenu;

	/**
	 * Builds the frame.
	 */
	public ToDoApplication() {
		list = new ToDoList();
		tasks = new JList<Item>(list);
		
		setJMenuBar(setupMenubar());
		add(tasks);
		setTitle("To Do List");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setSize(600,500);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}

	/**
	 * Builds the Menu Bar for the frame.
	 * 
	 * @return The menu bar for the frame.
	 */
	private JMenuBar setupMenubar() {

		JMenuBar menuBar = new JMenuBar();

		// build and add all things for the "File" menu
		JMenu fileMenu = new JMenu("File");

		saveMenu = new JMenuItem("Save");
		saveMenu.addActionListener(this);
		openMenu = new JMenuItem("Open");
		openMenu.addActionListener(this);
		exitMenu = new JMenuItem("Exit");
		exitMenu.addActionListener(this);

		fileMenu.add(saveMenu);
		fileMenu.add(openMenu);
		fileMenu.addSeparator();
		fileMenu.add(exitMenu);

		// build and add all things for the "Action" menu
		JMenu actionMenu = new JMenu("Action");

		addMenu = new JMenuItem("Add a Task");
		addMenu.addActionListener(this);
		removeMenu = new JMenuItem("Remove a Task");
		removeMenu.addActionListener(this);

		actionMenu.add(addMenu);
		actionMenu.add(removeMenu);
		
		menuBar.add(fileMenu);
		menuBar.add(actionMenu);

		return menuBar;

	}

	/**
	 * Handles events.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		JComponent component = (JComponent) e.getSource();

		if (component == exitMenu) {
			int quit = JOptionPane.showConfirmDialog(null,
					" Are you sure you want to quit?", "Quit?",
					JOptionPane.YES_NO_OPTION);
			if (quit == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
			return;
		}

		if (component == saveMenu || component == openMenu) {
			fileMenuEvent(component);
			return;

		}
		if (component == addMenu || component == removeMenu) {
			actionMenuEvent(component);
			return;
		}
	}

	/**
	 * Listener helper for I/O menu events.
	 */
	@SuppressWarnings("unchecked")
	private void fileMenuEvent(JComponent comp) {

		if (comp == saveMenu) {
			chooser = new JFileChooser();

			int save = chooser.showSaveDialog(this);
			if (save == JFileChooser.APPROVE_OPTION) {
				try {
					FileOutputStream out = new FileOutputStream(
							chooser.getSelectedFile());
					ObjectOutputStream output = new ObjectOutputStream(
							out);
					output.writeObject(list.getItems());

					output.close();

				}
				catch (IOException e) {
					JOptionPane.showMessageDialog
						                (null,"There was a problem saving the file",
						                "Saving Error",JOptionPane.ERROR_MESSAGE);
				}
			}

			return;
		}
		if (comp == openMenu) {
			chooser = new JFileChooser();
			int open = chooser.showOpenDialog(this);
			if (open == JFileChooser.APPROVE_OPTION) {
				try {
					FileInputStream in = new FileInputStream(
							chooser.getSelectedFile());
					ObjectInputStream input = new ObjectInputStream(
							in);
					list.setItems((LinkedList<Item>) input
							.readObject());

					input.close();
				}
				catch (IOException e2) {
					e2.printStackTrace();

				}
				catch (ClassNotFoundException e1) {

					e1.printStackTrace();
				}

			}
			return;

		}

	}

	/**
	 * Listener Helper for action menu events.
	 */
	private void actionMenuEvent(JComponent comp) {

		if (comp == addMenu) {
			item = new Item();
			addDialog = new AddDialog(this, item);
			if(addDialog.isReady()){
				list.add(item);
				addDialog.dispose();
			}

			return;

		}
		if (comp == removeMenu) {
			int taskIndex = tasks.getSelectedIndex();
			
			list.remove(taskIndex);

			return;

		}

	}

	/**
	 * Entry point for the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		new ToDoApplication();

	}

}
