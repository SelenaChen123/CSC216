package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Class that represents an array list data structure that retrieves elements in
 * the same order they were added. Unlike ArrayList, this list has a capacity,
 * which will be set in this class (rather than ArrayList). Implements the Queue
 * interface and delegates to LinkedAbstractList. Has methods to add elements,
 * remove elements, check whether the Queue is empty, get the size of the Queue,
 * and set the capacity of the Queue.
 * 
 * @param <E> generic type parameter
 * 
 * @author Selena Chen
 * @author Atharva Mahajan
 * @author Sarah Morton
 */
public class ArrayQueue<E> implements Queue<E> {

	/** List of items in the Queue */
	private ArrayList<E> list;
	/** Capacity of the Queue */
	private int capacity;

	/**
	 * Constructs an ArrayQueue object with a given capacity.
	 * 
	 * @param capacity ArrayQueue's capacity
	 */
	public ArrayQueue(int capacity) {
		list = new ArrayList<E>();
		setCapacity(capacity);
	}

	/**
	 * Adds the element to the back of the Queue.
	 * 
	 * @param element element to be added to the back of the Queue
	 * @throws IllegalArgumentException if there is no room (capacity has been
	 *                                  reached)
	 */
	public void enqueue(E element) {
		if (size() == capacity) {
			throw new IllegalArgumentException();
		} else {
			list.add(size(), element);
		}
	}

	/**
	 * Removes and returns the element at the front of the Queue.
	 * 
	 * @return element that was at the front of the Queue
	 * @throws NoSuchElementException if the Queue is empty
	 */
	public E dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		} else {
			E ret = list.remove(0);
			return ret;
		}
	}

	/**
	 * Returns whether or not the Queue is empty.
	 * 
	 * @return true if the Queue is empty, false otherwise
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns the number of elements in the Queue.
	 * 
	 * @return number of elements in the Queue
	 */
	public int size() {
		return list.size();
	}

	/**
	 * Sets the Queue’s capacity
	 * 
	 * @param capacity Queue's capacity
	 * @throws IllegalArgumentException if the actual parameter is negative or if it
	 *                                  is less than the number of elements in the
	 *                                  Queue
	 */
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}
}