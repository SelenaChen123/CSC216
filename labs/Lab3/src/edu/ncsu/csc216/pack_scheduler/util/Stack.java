package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Class that represents a data structure that retrieves elements in the
 * opposite order of when they were added. Has methods to add elements, remove
 * elements, check whether the Stack is empty, get the size of the Stack, and
 * set the capacity of the Stack.
 * 
 * @param <E> generic type parameter
 * 
 * @author Selena Chen
 * @author Atharva Mahajan
 * @author Sarah Morton
 */
public interface Stack<E> {

	/**
	 * Adds the element to the top of the Stack.
	 * 
	 * @param element element to be added to the Stack
	 * @throws IllegalArgumentException if there is no room (capacity has been
	 *                                  reached)
	 */
	void push(E element);

	/**
	 * Removes and returns the element at the top of the Stack.
	 * 
	 * @return element at the top of the Stack
	 * @throws EmptyStackException if the Stack is empty
	 */
	E pop();

	/**
	 * Returns true if the Stack is empty.
	 * 
	 * @return true if the Stack is empty, false otherwise
	 */
	boolean isEmpty();

	/**
	 * Returns the number of elements in the Stack.
	 * 
	 * @return number of elements in the Stack
	 */
	int size();

	/**
	 * Sets the stack's capacity.
	 * 
	 * @param capacity Capacity to set the stack to
	 * @throws IllegalArgumentException if the actual parameter is negative or if it
	 *                                  is less than the number of elements in the
	 *                                  Stack
	 */
	void setCapacity(int capacity);
}