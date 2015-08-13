/**
 * Item.java describes the individual item to appear on the list.
 * 
 * @author Bryon Fields
 * @version 0.1
 */

package ToDo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Item implements Serializable {

	/** Version ID */
	private static final long serialVersionUID = 1L;
	/** The description of the item */
	private String description;
	/** The date item was added */
	private GregorianCalendar dateAdded;
	/** The date item needs to be completed by */
	private GregorianCalendar dateDue;
	/** Tracks Item priority */
	private ItemPriority priority;
	/** Whether the task is completed or active */
	private boolean completed;

	/**
	 * Default constructor. Sets all variables to null.
	 */
	public Item() {

		this(null, null, null, ItemPriority.LOW);
	}

	/**
	 * Creates a new Item.
	 * 
	 * @param discription
	 *            the task to be completed
	 * @param added
	 *            the Date task was added
	 * @param due
	 *            the Date the task is due.
	 */
	public Item(String description, GregorianCalendar added,
			GregorianCalendar due, ItemPriority priority) {

		this.description = description;
		this.dateAdded = added;
		this.dateDue = due;
		this.priority = priority;
		completed = false;
	}

	/**
	 * makes the current item inactive
	 */
	public void itemCompleted() {

		setCompleted(true);
	}

	

	/**
	 * Accesses the description.
	 * 
	 * @return description class variable containing the Item
	 *         description.
	 */
	public String getDescription() {

		return description;
	}

	/**
	 * Sets the class variable description.
	 * 
	 * @param description
	 *            The task to be completed.
	 */
	public void setDescription(String description) {

		this.description = description;
	}

	/**
	 * Returns the date of item creation.
	 * 
	 * @return dateAdded The Date of Item creation.
	 */
	public GregorianCalendar getDateAdded() {

		return dateAdded;
	}

	/**
	 * Sets the date the Item was created.
	 * 
	 * @param dateAdded
	 *            The date the Item was created.
	 */
	public void setDateAdded(GregorianCalendar dateAdded) {

		this.dateAdded = dateAdded;
	}

	/**
	 * Returns the date Item should be completed.
	 * 
	 * @return dueDate The date the Item should be completed.
	 */
	public GregorianCalendar getDateDue() {

		return dateDue;
	}

	/**
	 * Sets the date should be completed by.
	 * 
	 * @param dateDue
	 */
	public void setDateDue(GregorianCalendar dateDue) {

		this.dateDue = dateDue;
	}

	/**
	 * Returns false until Item is completed.
	 * 
	 * @return false
	 */
	public boolean isCompleted() {

		return completed;
	}

	/**
	 * Sets the variable completed. Defaulted to false.
	 * 
	 * @param completed
	 *            boolean value.
	 */
	public void setCompleted(boolean completed) {

		this.completed = completed;
	}

	public ItemPriority getPriority() {

		return priority;
	}

	public void setPriority(ItemPriority priority) {

		this.priority = priority;
	}
	@Override
	public String toString(){
	final	StringBuilder message = new StringBuilder();
	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
		message.append("Task: "+getDescription()+" ");
	    message.append("Added on: "+format.format(getDateAdded().getTime())+" ");
	    message.append("Finish by: "+ format.format(getDateDue().getTime())+" ");
	    message.append("Priority: "+getPriority());
		
		return message.toString();
	}

}
