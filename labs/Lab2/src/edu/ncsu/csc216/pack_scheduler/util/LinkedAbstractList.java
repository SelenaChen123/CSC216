package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Utility class that extends AbstractList and is a a custom implementation of
 * an array list that doesn’t allow for null elements or duplicate elements as
 * defined by the equals() method, and has a capacity.
 * 
 * @param <E> generic type parameter
 * 
 * @author Selena Chen
 * @author Justin Wald
 */
public class LinkedAbstractList<E> extends AbstractList<E> {

	/** ListNode at the front of the LinkedAbstractList */
	private ListNode front;
	/** Size of the LinkedAbstractList */
	private int size;
	/** Capacity of the LinkedAbstractList */
	private int capacity;

	/**
	 * Constructs a LinkedAbstractList object with a given capacity.
	 * 
	 * @param capacity capacity of the LinkedAbstractList
	 * @throws IllegalArgumentException if the parameter is less than 0 or the
	 *                                  capacity is less than the current list’s
	 *                                  size
	 */
	public LinkedAbstractList(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException();
		}
		front = null;
		size = 0;
		setCapacity(capacity);
	}

	/**
	 * Sets the capacity of the LinkedAbstractList.
	 * 
	 * @param capacity capacity of the LinkedAbstractList
	 * @throws IllegalArgumentException if capacity is less than 0 or size
	 */
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}

	/**
	 * Adds an element to the given index in the LinkedAbstractList.
	 * 
	 * @param index   index of the element to be added
	 * @param element element to be added in the LinkedAbstractList
	 * @throws IndexOutOfBoundsException if index is less than 0 or larger than size
	 *                                   of LinkedAbstractList
	 * @throws IllegalArgumentException  if element is a duplicate or the capacity
	 *                                   has already been reached
	 * @throws NullPointerException      if element is null
	 */
	@Override
	public void add(int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		} else if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		} else if (size == capacity) {
			throw new IllegalArgumentException();
		}
		ListNode current = front;
		while (current != null) {
			if (element.equals(current.data)) {
				throw new IllegalArgumentException();
			}
			current = current.next;
		}
		if (front == null) {
			front = new ListNode(element);
		} else if (index == 0) {
			front = new ListNode(element, front);
		} else {
			current = front;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			current.next = new ListNode(element, current.next);
		}
		size++;
	}

	/**
	 * Removes an element at the given index.
	 * 
	 * @param index index of element to be removed
	 * @return element that was removed
	 * @throws IndexOutOfBoundsException if index is less than 0 or larger than size
	 *                                   of LinkedAbstractList
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		E rtn = null;
		if (size == 1) {
			rtn = front.data;
			front = null;
		} else {
			ListNode current = front;
			if (index == 0) {
				rtn = current.data;
				front = current.next;
			} else {
				current = front;
				for (int i = 0; i < index - 1; i++) {
					rtn = current.data;
					current = current.next;
				}
				rtn = current.next.data;
				current.next = current.next.next;
			}
		}
		size--;
		return rtn;
	}

	/**
	 * Replaces the element at the given index with the given element.
	 * 
	 * @param index   index of element to be replaced
	 * @param element element that is replacing the previous element
	 * @return element that was replaced
	 * @throws IndexOutOfBoundsException if index is less than 0 or larger than size
	 *                                   of LinkedAbstractList
	 * @throws IllegalArgumentException  if element is a duplicate
	 * @throws NullPointerException      if element is null
	 */
	@Override
	public E set(int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		} else if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		ListNode current = front;
		while (current != null) {
			if (element.equals(current.data)) {
				throw new IllegalArgumentException();
			}
			current = current.next;
		}
		E rtn = null;
		current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		rtn = current.data;
		current.data = element;
		return rtn;
	}

	/**
	 * Returns the element at the given index.
	 * 
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if index is less than 0 or larger than size
	 *                                   of LinkedAbstractList
	 * @throws NullPointerException      if LinkedAbstractList is empty
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		} else if (front == null) {
			throw new NullPointerException();
		}
		ListNode current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.data;
	}

	/**
	 * Returns the size of the LinkedAbstractList.
	 * 
	 * @return size of the LinkedAbstractList
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Class that represents a single node of a LinkedAbstractList object,
	 * containing data and a reference to the next node in the list.
	 * 
	 * @author Selena Chen
	 * @author Justin Wald
	 */
	private class ListNode {

		/** Data in the node */
		private E data;
		/** Next node in the list */
		private ListNode next;

		/**
		 * Constructs a ListNode object with the given data.
		 * 
		 * @param data data in the node
		 */
		public ListNode(E data) {
			this.data = data;
			next = null;
		}

		/**
		 * Constructs a ListNode object with the given data and a next node.
		 * 
		 * @param data data in the node
		 * @param next next node in the list
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
}