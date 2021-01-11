package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Custom implementation that doesn't allow for null elements or duplicate
 * elements as defined by the equals() method, and can push elements onto or pop
 * elements from the Stack. This class implements the Stack interface and
 * creates a Stack object that uses ArrayList to hold and manipulate its
 * information. Unlike ArrayList, this list has a capacity, which will be set in
 * this class (rather than ArrayList).
 * 
 * @param <E> generic type parameter
 * 
 * @author Selena Chen
 * @author Atharva Mahajan
 * @author Sarah Morton
 */
public class ArrayStack<E> implements Stack<E> {

	/** List of items in the Stack */
	private ArrayList<E> stack;
	/** Capacity of the Stack */
	private int capacity;

	/**
	 * Creates an ArrayStack object with a given capacity.
	 * 
	 * @param cap ArrayStack's capacity
	 */
	public ArrayStack(int cap) {
		stack = new ArrayList<E>();
		setCapacity(cap);
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
		if (stack.size() == capacity) {
			throw new IllegalArgumentException("Stack is full.");
		}
		stack.add(stack.size(), element);
	}

	/**
	 * Removes and returns the element at the top of the Stack.
	 * 
	 * @return element at the top of the Stack
	 * @throws EmptyStackException if the Stack is empty
	 */
	@Override
	public E pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return stack.remove(stack.size() - 1);
	}

	/**
	 * Returns true if the Stack is empty.
	 * 
	 * @return true if the Stack is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		if (stack.size() == 0) {
			return true;
		}
		return false;
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
		if (capacity < 0 || capacity < stack.size()) {
			throw new IllegalArgumentException("Invalid Capacity");
		}
		this.capacity = capacity;
	}
}