package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Utility class that extends AbstractSequentialList and is a a custom
 * implementation of an linked list that doesn't allow for null elements or
 * duplicate elements as defined by the equals() method.
 * 
 * @param <E> generic type parameter
 * 
 * @author Selena Chen
 * @author Atharva Mahajan
 * @author Sarah Morton
 */
public class LinkedList<E> extends AbstractSequentialList<E> {

	/** ListNode at the front of the LinkedList */
	private ListNode front;
	/** ListNode at the back of the LinkedList */
	private ListNode back;
	/** Size of the LinkedList */
	private int size;

	/**
	 * Constructs a LinkedList object.
	 */
	public LinkedList() {
		front = new ListNode(null);
		back = new ListNode(null);
		front.next = back;
		back.prev = front;
		size = 0;
	}

	/**
	 * Returns a ListIterator that is positioned such that a call to
	 * ListIterator.next() will return the element at given index.
	 * 
	 * @param index Index to position the LinkedListIterator at
	 * @return ListIterator that is positioned such that a call to
	 *         ListIterator.next() will return the element at given index
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		return new LinkedListIterator(index);
	}

	/**
	 * Adds an element to the given index in the LinkedList.
	 * 
	 * @param index   index of the element to be added
	 * @param element element to be added in the LinkedList
	 * @throws IllegalArgumentException if the element to add is a duplicate of an
	 *                                  element already in the list as determined by
	 *                                  the LinkedList.contains() method
	 */
	@Override
	public void add(int index, E element) {
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		super.add(index, element);
	}

	/**
	 * Replaces the element at the given index with the given element.
	 * 
	 * @param index   index of element to be replaced
	 * @param element element that is replacing the previous element
	 * @return element that was replaced
	 * @throws IllegalArgumentException if the element to set is a duplicate of an
	 *                                  element already in the list as determined by
	 *                                  the LinkedList.contains() method
	 */
	@Override
	public E set(int index, E element) {
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		return super.set(index, element);
	}

	/**
	 * Returns the size of the LinkedList.
	 * 
	 * @return Size of the LinkedList
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Class that represents a single node of a LinkedList object, containing data
	 * and references to the next and previous nodes in the list.
	 * 
	 * @author Selena Chen
	 * @author Atharva Mahajan
	 * @author Sarah Morton
	 */
	private class ListNode {

		/** Data in the node */
		public E data;
		/** Next node in the list */
		public ListNode next;
		/** Previous node in the list */
		public ListNode prev;

		/**
		 * Constructs a ListNode object with the given data.
		 * 
		 * @param data data in the node
		 */
		public ListNode(E data) {
			this.data = data;
			next = null;
			prev = null;
		}

		/**
		 * Constructs a ListNode object with the given data, a next node, and a previous
		 * node.
		 * 
		 * @param data data in the node
		 * @param prev previous node in the list
		 * @param next next node in the list
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}

	/**
	 * An iterator for linked lists that allows the programmer to traverse the list
	 * in either direction, modify the list during iteration, and obtain the
	 * iterator's current position in the list. A ListIterator has no current
	 * element; its cursor position always lies between the element that would be
	 * returned by a call to previous() and the element that would be returned by a
	 * call to next().
	 * 
	 * @author Selena Chen
	 * @author Atharva Mahajan
	 * @author Sarah Morton
	 */
	private class LinkedListIterator implements ListIterator<E> {

		/** ListNode that would be returned on a call to previous() */
		public ListNode previous;
		/** ListNode that would be returned on a call to next() */
		public ListNode next;
		/** Index that would be returned on a call to previousIndex() */
		public int previousIndex;
		/** Index that would be returned on a call to nextIndex() */
		public int nextIndex;
		/** ListNode that was returned on the last call to either previous() or next() */
		private ListNode lastRetrieved;

		/**
		 * Constructs a LinkedListIterator positioned at the given index.
		 * 
		 * @param index Index to position the LinkedListIterator at
		 * @throws IndexOutOfBoundsException if index is less than 0 or greater than or
		 *                                   equal to size
		 */
		public LinkedListIterator(int index) {
			if (index < 0 || index > size) {
				throw new IndexOutOfBoundsException();
			}
			previous = front;
			next = previous.next;
			for (int i = 0; i < index; i++) {
				previous = next;
				next = next.next;
			}
			previousIndex = index - 1;
			nextIndex = index;
			lastRetrieved = null;
		}

		/**
		 * Returns true if this list iterator has more elements when traversing the list
		 * in the forward direction. (In other words, returns true if next() would
		 * return an element rather than throwing an exception.)
		 * 
		 * @return true if the list iterator has more elements when traversing the list
		 *         in the forward direction, false otherwise
		 */
		@Override
		public boolean hasNext() {
			return next.data != null;
		}

		/**
		 * Returns the next element in the list and advances the cursor position. This
		 * method may be called repeatedly to iterate through the list, or intermixed
		 * with calls to previous() to go back and forth. (Note that alternating calls
		 * to next and previous will return the same element repeatedly.)
		 * 
		 * @return next element in the list
		 * @throws NoSuchElementException if the iteration has no next element
		 */
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			lastRetrieved = next;
			E element = next.data;
			previous = next;
			previousIndex++;
			next = next.next;
			nextIndex++;
			return element;
		}

		/**
		 * Returns true if this list iterator has more elements when traversing the list
		 * in the reverse direction. (In other words, returns true if previous() would
		 * return an element rather than throwing an exception.)
		 * 
		 * @return true if the list iterator has more elements when traversing the list
		 *         in the reverse direction, false otherwise
		 */
		@Override
		public boolean hasPrevious() {
			return previous.data != null;
		}

		/**
		 * Returns the previous element in the list and moves the cursor position
		 * backwards. This method may be called repeatedly to iterate through the list
		 * backwards, or intermixed with calls to next() to go back and forth. (Note
		 * that alternating calls to next and previous will return the same element
		 * repeatedly.)
		 * 
		 * @return previous element in the list
		 * @throws NoSuchElementException if the iteration has no previous element
		 */
		@Override
		public E previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			lastRetrieved = previous;
			E element = previous.data;
			next = previous;
			nextIndex--;
			previous = previous.prev;
			previousIndex--;
			return element;
		}

		/**
		 * Returns the index of the element that would be returned by a subsequent call
		 * to next(). (Returns list size if the list iterator is at the end of the
		 * list.)
		 * 
		 * @return index of the element that would be returned by a subsequent call to
		 *         next, or list size if the list iterator is at the end of the list
		 */
		@Override
		public int nextIndex() {
			if (next == null) {
				return size;
			}
			return nextIndex;
		}

		/**
		 * Returns the index of the element that would be returned by a subsequent call
		 * to previous(). (Returns -1 if the list iterator is at the beginning of the
		 * list.)
		 * 
		 * @return index of the element that would be returned by a subsequent call to
		 *         previous, or -1 if the list iterator is at the beginning of the list
		 */
		@Override
		public int previousIndex() {
			if (previous == null) {
				return -1;
			}
			return previousIndex;
		}

		/**
		 * Removes from the list the last element that was returned by next() or
		 * previous() (optional operation). This call can only be made once per call to
		 * next or previous. It can be made only if add(E) has not been called after the
		 * last call to next or previous.
		 * 
		 * @throws IllegalStateException if lastRetrieved is null
		 */
		@Override
		public void remove() {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			lastRetrieved.prev.next = lastRetrieved.next;
			lastRetrieved.next.prev = lastRetrieved.prev;
			size--;
		}

		/**
		 * Replaces the last element returned by next() or previous() with the specified
		 * element (optional operation). This call can be made only if neither remove()
		 * nor add(E) have been called after the last call to next or previous.
		 * 
		 * @param e element with which to replace the last element returned by next or
		 *          previous
		 * @throws NullPointerException     if the element is null
		 * @throws IllegalArgumentException if the last retrieved is null
		 */
		@Override
		public void set(E e) {
			if (e == null) {
				throw new NullPointerException();
			} else if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			lastRetrieved.data = e;
		}

		/**
		 * Inserts the specified element into the list (optional operation). The element
		 * is inserted immediately before the element that would be returned by next(),
		 * if any, and after the element that would be returned by previous(), if any.
		 * (If the list contains no elements, the new element becomes the sole element
		 * on the list.) The new element is inserted before the implicit cursor: a
		 * subsequent call to next would be unaffected, and a subsequent call to
		 * previous would return the new element. (This call increases by one the value
		 * that would be returned by a call to nextIndex or previousIndex.)
		 * 
		 * @param e element to insert
		 * @throws NullPointerException if the element to add is null
		 */
		@Override
		public void add(E e) {
			if (e == null) {
				throw new NullPointerException();
			}
			ListNode node = new ListNode(e, previous, next);
			previous.next = node;
			next.prev = node;
			size++;
			lastRetrieved = null;
		}
	}
}