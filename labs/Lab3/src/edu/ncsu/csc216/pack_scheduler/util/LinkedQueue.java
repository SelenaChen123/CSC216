package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Class that represents a linked list data structure that retrieves elements in
 * the same order they were added. Implements the Queue interface and delegates
 * to LinkedAbstractList. Has methods to add elements, remove elements, check
 * whether the Queue is empty, get the size of the Queue, and set the capacity
 * of the Queue.
 * 
 * @param <E> generic type parameter
 * 
 * @author Selena Chen
 * @author Atharva Mahajan
 * @author Sarah Morton
 */
public class LinkedQueue<E> implements Queue<E> {

	/** LinkedAbstractList that keeps track of the elements in the LinkedQueue */
	private LinkedAbstractList<E> queue;

	/**
	 * Constructs a LinkedQueue object with a given capacity.
	 * 
	 * @param capacity LinkedQueue's capacity
	 */
	public LinkedQueue(int capacity) {
		queue = new LinkedAbstractList<E>(capacity);
	}

	/**
	 * Adds the element to the back of the Queue.
	 * 
	 * @param element element to be added to the back of the Queue
	 * @throws IllegalArgumentException if there is no room (capacity has been
	 *                                  reached)
	 */
	@Override
	public void enqueue(E element) {
		try {
			queue.add(queue.size(), element);
		} catch (IllegalArgumentException iae) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Removes and returns the element at the front of the Queue.
	 * 
	 * @return element that was at the front of the Queue
	 * @throws NoSuchElementException if the Queue is empty
	 */
	@Override
	public E dequeue() {
		if (queue.isEmpty()) {
			throw new NoSuchElementException();
		}
		return queue.remove(0);
	}

	/**
	 * Returns whether or not the Queue is empty.
	 * 
	 * @return true if the Queue is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return queue.size() == 0;
	}

	/**
	 * Returns the number of elements in the Queue.
	 * 
	 * @return number of elements in the Queue
	 */
	public int size() {
		return queue.size();
	}

	/**
	 * Sets the Queue's capacity
	 * 
	 * @param capacity Queue's capacity
	 * @throws IllegalArgumentException if the actual parameter is negative or if it
	 *                                  is less than the number of elements in the
	 *                                  Queue
	 */
	@Override
	public void setCapacity(int capacity) {
		queue.setCapacity(capacity);
	}

	/**
	 * Returns whether or not the given element is already in the LinkedQueue.
	 * 
	 * @param element Element to be searched for
	 * @return True if the given element is already in the LinkedQueue, false
	 *         otherwise
	 */
	public boolean contains(E element) {
		for (int i = 0; i < queue.size(); i++) {
			if (queue.get(i).equals(element)) {
				return true;
			}
		}
		return false;
	}
}