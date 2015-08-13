/**
 * ToDoList handles the Item list for the GUI.
 * 
 * @author Bryon Fields
 * @version 0.1
 */

package ToDo.model;

import java.util.LinkedList;

import javax.swing.AbstractListModel;

import ToDo.Item;

public class ToDoList extends AbstractListModel<Item> {

	/** Version ID */
	private static final long serialVersionUID = 1L;
	/** Linked List of Item Objects */
	private LinkedList<Item> items;

	/**
	 * Default Constructor. Instantiates empty LinkedList of Item
	 * objects.
	 */
	public ToDoList() {

		this.items = new LinkedList<Item>();
	}

	/**
	 * Returns the number of Items in the List.
	 */
	@Override
	public int getSize() {

		return items.size();
	}

	/**
	 * Returns Item at specified index.
	 * 
	 * @param index
	 *            The index of the desired item.
	 */
	@Override
	public Item getElementAt(int index) {

		return items.get(index);
	}

	/**
	 * returns index of Item object. Updates caller.
	 * 
	 * @param item
	 *            Item being searched for in the list.
	 * @return index of Item
	 */
	public int indexOf(Item item) {

		return items.indexOf(item);
	}

	/**
	 * Add Item object to list. Updates caller.
	 * 
	 * @param item
	 *            The Item object to be added to list
	 */
	public void add(Item item) {

		if (item != null) {
			items.add(item);
			fireIntervalAdded(this, items.size() - 1,
					items.size() - 1);
		}
	}

	/**
	 * Add Item object to specific index. Updates caller.
	 * 
	 * @param index
	 *            The desired position of new Item object.
	 * @param item
	 *            The item object to be added.
	 */
	public void add(int index, Item item) {

		if (item != null) {
			items.add(index, item);
			fireIntervalAdded(this, index, index);
		}
	}

	/**
	 * Removes item at specified index. Updates caller.
	 * 
	 * @param index
	 *            Index of item to be removed.
	 */
	public void remove(int index) {

		items.remove(index);
		fireIntervalRemoved(this, 0, items.size());
	}

	/**
	 * Removes item from index.
	 * 
	 * @param item
	 *            The Item to be removed.
	 */
	public void remove(Item item) {

		remove(indexOf(item));
	}

	/**
	 * Clears all Items from the list. Updates caller.
	 */
	public void clear() {

		if (items.size() > 0) {
			int size = items.size();
			items.clear();
			fireIntervalRemoved(this, 0, size - 1);
		}
	}

	/**
	 * Returns the list of items.
	 * 
	 * @return items The List of Items.
	 */
	public LinkedList<Item> getItems() {

		return items;
	}
/**
 * Sets a new list of Item objects. Updates caller.
 * @param items
 */
	public void setItems(LinkedList<Item> items) {

		this.items = items;
		fireContentsChanged(this, 0, items.size());
	}

}
