package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Class that represents a linked list data structure that retrieves elements in
 * the reverse order of how they were added. Implements the Stack interface and
 * delegates to LinkedAbstractList. Has methods to push, pop elements, check
 * whether the Stack is empty, get the size of the Stack, and set the capacity
 * of the Stack.
 * 
 * @param <E> generic type parameter
 * 
 * @author Selena Chen
 * @author Atharva Mahajan
 * @author Sarah Morton
 * 
 */
public class LinkedStack<E> implements Stack<E> {

	/** LinkedAbstractList that keeps track of the elements in the LinkedStack */
	private LinkedAbstractList<E> stack;

	/**
	 * Constructs a LinkedStack object with a given capacity.
	 * 
	 * @param capacity LinkedStack's capacity
	 */
	public LinkedStack(int capacity) {
		stack = new LinkedAbstractList<E>(capacity);
	}

	/**
	 * Adds the element to the top of the Stack.
	 * 
	 * @param element element to be added to the Stack
	 * @throws IllegalArgumentException if there is no room (capacity has been
	 *                                  reached)
	 */
	@Override
	public void push(E element) {
		stack.add(0, element);
	}

	/**
	 * Removes and returns the element at the top of the Stack.
	 * 
	 * @return element at the top of the Stack
	 * @throws EmptyStackException if the Stack is empty
	 */
	@Override
	public E pop() {
		if (stack.isEmpty()) {
			throw new EmptyStackException();
		}
		return stack.remove(0);
	}

	/**
	 * Returns true if the Stack is empty.
	 * 
	 * @return true if the Stack is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return stack.size() == 0;
	}

	/**
	 * Returns the number of elements in the Stack.
	 * 
	 * @return number of elements in the Stack
	 */
	@Override
	public int size() {
		return stack.size();
	}

	/**
	 * Sets the stack's capacity.
	 * 
	 * @param capacity Capacity to set the stack to
	 * @throws IllegalArgumentException if the actual parameter is negative or if it
	 *                                  is less than the number of elements in the
	 *                                  Stack
	 */
	@Override
	public void setCapacity(int capacity) {
		stack.setCapacity(capacity);
	}
}