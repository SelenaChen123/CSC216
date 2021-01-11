package edu.ncsu.csc216.travel.list_utils;

/**
 * Custom implementation of an array data structure that implements the 8
 * methods of the SimpleList interface. Has a field which is an array of
 * Objects, a size to indicate how many array elements belong to the list, and a
 * RESIZE constant with the value 12. The initial length of the array is 12. The
 * length of the array should be increased by 12 whenever needed to accommodate
 * new list items.
 * 
 * @param <E> Generic type parameter
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public class SimpleArrayList<E> implements SimpleList<E> {

	/** Initial length of the list and resize constant */
	private static final int RESIZE = 12;
	/** List of objects */
	private Object[] list;
	/** Size of the list */
	private int size;

	/**
	 * Constructs a SimpleArrayList object.
	 */
	public SimpleArrayList() {
		this(RESIZE);
	}

	/**
	 * Constructs a SimpleArrayList object with a given size.
	 * 
	 * @param size Given size of the SimpleArrayList
	 */
	public SimpleArrayList(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException();
		}
		list = new Object[size];
		this.size = 0;
	}

	/**
	 * Returns the number of elements in this list or Integer.MAX_VALUE if the list
	 * has more than Integer.MAX_VALUE elements.
	 *
	 * @return Number of elements in this list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Does this list contain elements?
	 *
	 * @return True if and only if this list contains no elements, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Does this list contains the specified element?
	 *
	 * @param e Element whose presence in this list is to be tested
	 * @return True if this list contains the specified element e, false otherwise
	 */
	@Override
	public boolean contains(E e) {
		for (int i = 0; i < size; i++) {
			if (list[i].equals(e)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds the specified element to the end of the list.
	 *
	 * @param e Element to be appended to this list
	 * @return True if element is added, false otherwise
	 * @throws IllegalArgumentException If the list already contains e
	 * @throws NullPointerException     If the specified element is null
	 */
	@Override
	public boolean add(E e) {
		if (contains(e)) {
			throw new IllegalArgumentException();
		} else if (e == null) {
			throw new NullPointerException();
		}
		try {
			add(size, e);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	/**
	 * Inserts the specified element at the specified position in this list. Shifts
	 * any element currently at that position plus all subsequent elements to the
	 * right (adds one to their indexes).
	 *
	 * @param pos Index at which the specified element is to be inserted
	 * @param e   Element to be inserted
	 * @throws NullPointerException      If the specified element is null and this
	 *                                   list does not permit null elements
	 * @throws IllegalArgumentException  If some property of the specified element
	 *                                   prevents it from being added to this list
	 * @throws IndexOutOfBoundsException If the index is out of range (pos is less
	 *                                   than 0 or greater than size)
	 */
	@Override
	public void add(int pos, E e) {
		if (e == null) {
			throw new NullPointerException();
		} else if (pos < 0 || pos > size) {
			throw new IndexOutOfBoundsException();
		} else if (contains(e)) {
			throw new IllegalArgumentException();
		}
		if (size == list.length) {
			Object[] newList = new Object[list.length + RESIZE];
			for (int i = 0; i < size; i++) {
				newList[i] = list[i];
			}
			list = newList;
		}
		for (int i = size; i > pos; i--) {
			list[i] = list[i - 1];
		}
		list[pos] = e;
		size++;
	}

	/**
	 * Removes the element at the specified position in this list, shifting any
	 * subsequent elements to the left (subtracts one from their indexes). Returns
	 * the element that was removed from the list.
	 *
	 * @param index Index of the element to be removed
	 * @return Element previously at the specified position
	 * @throws IndexOutOfBoundsException If the index is out of range (index is less
	 *                                   than 0 or index is greater than or equal to
	 *                                   size
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		E e = (E) list[index];
		for (int i = index; i < size; i++) {
			list[i] = list[i + 1];
		}
		size--;
		return e;
	}

	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param pos Index of the element to return
	 * @return Element at the specified position in this list
	 * @throws IndexOutOfBoundsException If the index is out of range (pos is less
	 *                                   than 0 or pos is greater than or equal to
	 *                                   size)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E get(int pos) {
		if (pos < 0 || pos >= size) {
			throw new IndexOutOfBoundsException();
		}
		return (E) list[pos];
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element.
	 *
	 * @param e Element to search for
	 * @return Index of the first occurrence of the specified element in this list,
	 *         or -1 if this list does not contain the element
	 */
	@Override
	public int indexOf(E e) {
		for (int i = 0; i < size; i++) {
			if (list[i].equals(e)) {
				return i;
			}
		}
		return -1;
	}
}