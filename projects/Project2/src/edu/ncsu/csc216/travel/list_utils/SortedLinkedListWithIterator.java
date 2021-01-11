package edu.ncsu.csc216.travel.list_utils;

import java.util.NoSuchElementException;

/**
 * Custom implementation of a singly linked list that implements the 7 methods
 * of the SortedList interface. Has a Node field which represents the head of
 * the linked list. Also contains the inner classes Node and Cursor.
 * 
 * @param <E> Generic type parameter
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public class SortedLinkedListWithIterator<E extends Comparable<E>> implements SortedList<E> {

	/** Head of the linked list */
	private Node<E> head;

	/**
	 * Constructs a SortedLinkedListWithIterator object.
	 */
	public SortedLinkedListWithIterator() {
		head = null;
	}

	/**
	 * Returns the number of elements in this list. If this list contains more than
	 * Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
	 *
	 * @return Number of elements in this list
	 */
	@Override
	public int size() {
		int size = 0;
		Node<E> current = head;
		while (current != null) {
			current = current.next;
			size++;
		}
		return size;
	}

	/**
	 * Returns true if this list contains no elements.
	 *
	 * @return True if this list contains no elements, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns true if this list contains the specified element. More formally,
	 * returns true if and only if this list contains at least one element a such
	 * that (o==null ? a==null : o.equals(a)).
	 *
	 * @param e Element whose presence in this list is to be tested
	 * @return True if this list contains the specified element, false otherwise
	 */
	@Override
	public boolean contains(E e) {
		Node<E> current = head;
		while (current != null) {
			if (current.value.equals(e)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	/**
	 * Adds the specified element to list in sorted order.
	 *
	 * @param e Element to be appended to this list
	 * @return True if element is added, false otherwise
	 * @throws NullPointerException     If e is null
	 * @throws IllegalArgumentException If list already contains e
	 */
	@Override
	public boolean add(E e) {
		if (e == null) {
			throw new NullPointerException();
		} else if (contains(e)) {
			throw new IllegalArgumentException();
		}
		Node<E> previous = null;
		Node<E> current = head;
		while (current != null && current.value.compareTo(e) < 0) {
			previous = current;
			current = current.next;
		}
		Node<E> newNode = new Node<E>(e, current);
		if (previous != null) {
			previous.next = newNode;
		} else {
			head = newNode;
		}
		return true;
	}

	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param index Index of the element to return
	 * @return Element at the specified position in this list
	 * @throws IndexOutOfBoundsException If the index is out of range (index is less
	 *                                   than 0 or index is greater than or equal to
	 *                                   size)
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.value;
	}

	/**
	 * Removes the element at the specified position in this list. Shifts any
	 * subsequent elements to the left (subtracts one from their indices). Returns
	 * the element that was removed from the list.
	 *
	 * @param index Index of the element to be removed
	 * @return Element previously at the specified position
	 * @throws IndexOutOfBoundsException If the index is out of range (index is less
	 *                                   than 0 or index is greater than or equal to
	 *                                   size)
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> prev = null;
		Node<E> current = head;
		for (int i = 0; i < index; i++) {
			prev = current;
			current = current.next;
		}
		E val = current.value;
		if (prev != null) {
			prev.next = current.next;
		} else {
			head = current.next;
		}
		return val;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. More formally, returns
	 * the lowest index i such that (o==null ? get(i)==null : o.equals(get(i))), or
	 * -1 if there is no such index.
	 *
	 * @param e Element to search for
	 * @return Index of the first occurrence of the specified element in this list,
	 *         or -1 if this list does not contain the element
	 */
	@Override
	public int indexOf(E e) {
		int index = 0;
		Node<E> current = head;
		while (current != null) {
			if (current.value.equals(e)) {
				return index;
			}
			current = current.next;
			index++;
		}
		return -1;
	}

	/**
	 * Returns an instance of Cursor, since the inner class Cursor implements
	 * SimpleListIterator.
	 * 
	 * @return Instance of Cursor
	 */
	public SimpleListIterator<E> iterator() {
		return new Cursor();
	}

	/**
	 * Returns a string representation of the list in the format [A,B,...,X] where A
	 * is the first list item, B is the second, ..., and X is the last.
	 * 
	 * @return String representation of the list
	 */
	public String toString() {
		String s = "[";
		Node<E> current = head;
		while (current != null) {
			s += current.value + (current.next != null ? ", " : "");
			current = current.next;
		}
		s += "]";
		return s;
	}

	/**
	 * Class that represents a single node of a SortedLinkedListWithIterator object,
	 * containing a value and a reference to the next node in the list.
	 * 
	 * @param <E> Generic type parameter
	 * 
	 * @author Selena Chen
	 * @author Alex Snezhko
	 */
	private static class Node<E> {

		/** Value of the node */
		E value;
		/** Next node in the list */
		private Node<E> next;

		/**
		 * Constructs a Node object with the given data and a next node.
		 * 
		 * @param value Value of the node
		 * @param next  Next node in the list
		 */
		public Node(E value, Node<E> next) {
			this.value = value;
			this.next = next;
		}
	}

	/**
	 * Class that represents an iterator that can travel through the list, one item
	 * at a time.
	 * 
	 * @author Selena Chen
	 * @author Alex Snezhko
	 */
	private class Cursor implements SimpleListIterator<E> {

		/** Node that travels through the list */
		private Node<E> traveler;

		/**
		 * Constructs a Cursor object.
		 */
		public Cursor() {
			traveler = head;
		}

		/**
		 * Are there elements in the collection that have not been visited?
		 *
		 * @return True if yes, false if all elements have been visited
		 */
		@Override
		public boolean hasNext() {
			return traveler != null;
		}

		/**
		 * Answers the question: "What is the next element in the collection to be
		 * visited?" This method also advances the iterator to the following element, or
		 * throws NoSuchElementException if the list has already been traversed.
		 *
		 * @return Next element in the collection to be visited
		 */
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			E val = traveler.value;
			traveler = traveler.next;
			return val;
		}
	}
}