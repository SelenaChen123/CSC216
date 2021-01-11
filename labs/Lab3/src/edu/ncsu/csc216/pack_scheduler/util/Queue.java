package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Class that represents a data structure that retrieves elements in the same
 * order they were added. Has methods to add elements, remove elements, check
 * whether the Queue is empty, get the size of the Queue, and set the capacity
 * of the Queue.
 * 
 * @author Selena Chen
 * @author Atharva Mahajan
 * @author Sarah Morton
 * 
 * @param <E> generic type parameter
 */
public interface Queue<E> {

	/**
	 * Adds the element to the back of the Queue.
	 * 
	 * @param element element to be added to the back of the Queue
	 * @throws IllegalArgumentException if there is no room (capacity has been
	 *                                  reached)
	 */
	void enqueue(E element);

	/**
	 * Removes and returns the element at the front of the Queue.
	 * 
	 * @return element that was at the front of the Queue
	 * @throws NoSuchElementException if the Queue is empty
	 */
	E dequeue();

	/**
	 * Returns whether or not the Queue is empty.
	 * 
	 * @return true if the Queue is empty, false otherwise
	 */
	boolean isEmpty();

	/**
	 * Returns the number of elements in the Queue.
	 * 
	 * @return number of elements in the Queue
	 */
	int size();

	/**
	 * Sets the Queue’s capacity
	 * 
	 * @param capacity Queue's capacity
	 * @throws IllegalArgumentException if the actual parameter is negative or if it
	 *                                  is less than the number of elements in the
	 *                                  Queue
	 */
	void setCapacity(int capacity);
}