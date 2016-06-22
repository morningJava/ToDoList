/*
Builds the dialog box when adding or removing items.
@author: Bryon


*/
package ToDo.GUI;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ToDo.Item;
import ToDo.ItemPriority;

public class AddDialog extends JDialog implements ActionListener {

	/**Version ID*/
	private static final long serialVersionUID = 1L;
	/** Text field for task description */
	private JTextField txtDescription;
	/** Text field for Due Date */
	private JTextField txtDueDate;
	/** Button group for priority options */
	private ButtonGroup grpPriority;
	/** Low priority */
	private JRadioButton rdoLow;
	/** Normal priority */
	private JRadioButton rdoMed;
	/** High priority */
	private JRadioButton rdoHigh;
	/** Panel for RadioButtons */
	private JPanel buttonPanel;
	/** Adds item when clicked */
	private JButton btnOK;
	/** Cancels dialog */
	private JButton btnCancel;
	/** Calendar for due date */
	private GregorianCalendar due;
	/** Date for changing to GregorianCalendar */
	private Date date;
	/** Formats date to/ from GregoianCalendar */
	private SimpleDateFormat format;
	/** Checks if dialog info is ready */
	private boolean ready;
	/** Item to be added */
	Item task;

	/**
	 * Builds the dialog box.
	 */
	public AddDialog(JFrame parent, Item item) {

		super(parent, true);
		task = item;
		due = (GregorianCalendar) GregorianCalendar.getInstance();
		date = due.getTime();
		ready = false;

		setTitle("New Task");

		setupDialog();
		setSize(400,300);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}

	/**
	 * Helps build the dialog.
	 */
	private void setupDialog() {

		txtDescription = new JTextField(30);
		txtDueDate = new JTextField(8);

		btnOK = new JButton("Ok");
		btnOK.addActionListener(this);
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);

		setTextDefault();

		// GridLayout(#of panels , 1 column)
		setLayout(new GridLayout(4, 1));

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(new JLabel("Description:"));
		panel.add(txtDescription);
		add(panel);

		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(new JLabel("Due by:"));
		panel.add(txtDueDate);
		add(panel);

		add(setupRadioButtons());

		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(btnOK);
		panel.add(btnCancel);
		add(panel);

	}

	/**
	 * Sets the text fields to default text.
	 */
	private void setTextDefault() {

		format = new SimpleDateFormat("MM/dd/yy");
		txtDescription.setText("Pay Water bill");

		// always today's date by default
		txtDueDate.setText(format.format(date));
	}

	/**
	 * Sets up radio buttons, group and panel.
	 * 
	 * @return JPanel buttonPanel
	 */
	private JPanel setupRadioButtons() {

		buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		rdoLow = new JRadioButton("Low");
		rdoMed = new JRadioButton("Normal");
		rdoHigh = new JRadioButton("High");

		grpPriority = new ButtonGroup();

		grpPriority.add(rdoLow);
		grpPriority.add(rdoMed);
		grpPriority.add(rdoHigh);

		rdoMed.setSelected(true);

		buttonPanel.add(rdoLow);
		buttonPanel.add(rdoMed);
		buttonPanel.add(rdoHigh);
		buttonPanel.setBorder(BorderFactory
				.createTitledBorder(" Task Priority"));

		return buttonPanel;

	}

	/**
	 * Pairs radio buttons with ItemPriority enums.
	 * 
	 * @return ItemPriority
	 */
	private ItemPriority returnPriotity() {

		if (rdoLow.isSelected()) {
			return ItemPriority.LOW;

		} else if (rdoMed.isSelected()) {
			return ItemPriority.NORMAL;

		} else {

			return ItemPriority.HIGH;
		}

	}

	/**
	 * Event handler for the dialog.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnOK) {
			task.setDescription(txtDescription.getText());
			task.setPriority(returnPriotity());
			task.setDateAdded(due);
			try {
				date = format.parse(txtDueDate.getText());
				due.setTime(date);
				task.setDateDue(due);
			}
			catch (ParseException e1) {
				JOptionPane.showMessageDialog(null,
						"Your  Due By Field is improperly formatted",
						"ERROR", JOptionPane.ERROR_MESSAGE);
				setTextDefault();
				setReady(false);
				return;
			}
			setVisible(false);
			setReady(true);

		}
		if (e.getSource() == btnCancel) {
			setReady(false);
			dispose();
		}

	}

	/**
	 * Returns state of ready. Used by Application class to determine
	 * whether to add the new task to the list or not.
	 */
	public boolean isReady() {

		return ready;
	}

	/**
	 * Sets ready to boolean value.
	 * 
	 * @param ready
	 */
	public void setReady(boolean ready) {

		this.ready = ready;
	}

	/**
	 * Sets Task to Item object.
	 * 
	 * @return
	 */
	public Item getTask() {

		return task;
	}

	/**
	 * Return Item object;
	 * 
	 * @param task
	 */
	public void setTask(Item task) {

		this.task = task;
	}

}
