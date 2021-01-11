package edu.ncsu.csc216.pack_scheduler.util;

/**
 * A custom recursive implementation of LinkedList with functions to add,
 * remove, set and get. Implements functions recursively, with public/private
 * pairs of methods where the public LinkedListRecursive methods handles error
 * checking and special cases before transferring the flow of control to the
 * private ListNode methods to complete the recursion.
 * 
 * @param <E> Generic type parameter
 * 
 * @author Selena Chen
 * @author Atharva Mahajan
 * @author Sarah Morton
 */
public class LinkedListRecursive<E> {

	/** Size of the list */
	private int size;

	/** Front of the list */
	private ListNode front;

	/**
	 * Constructs a LinkedListRecursive object
	 */
	public LinkedListRecursive() {
		front = null;
		size = 0;
	}

	/**
	 * Returns true if the list is empty.
	 * 
	 * @return True if the list is empty, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the size of the list.
	 * 
	 * @return Size of the list
	 */
	public int size() {
		return size;
	}

	/**
	 * Adds an element to the end of the list. If the element is to be added to an
	 * empty list, the front is set to a LinkedNode. Otherwise the flow of control
	 * is transferred to ListNode(E).
	 * 
	 * @param element Element to be added
	 * @return True if successfully added, false otherwise.
	 * @throws IllegalArgumentException If the element already exists in the list
	 */
	public boolean add(E element) {
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		if (front == null) {
			front = new ListNode(element, null);
			size++;
			return true;
		}
		return front.add(element);
	}

	/**
	 * Adds an element to the given index in the list. This public method checks
	 * that the element isn’t already in the list (an IllegalArgumentException is
	 * thrown if the element already exists), handles bounds checking on the index
	 * (an IndexOutOfBoundsException is thrown for an invalid index), checks that
	 * the element isn’t null (a NullPointerException is thrown if element is null),
	 * and the special case of adding a node to the front of the list. If the
	 * element is added to the middle or end of the list, then the public method
	 * transfers the flow of control to the private ListNode.add(int idx, E element)
	 * method, which completes the recursion to add the element at the appropriate
	 * location.
	 * 
	 * @param index   Index of the element to be added
	 * @param element Element to be added to the list
	 * @throws IllegalArgumentException  If the element to add is a duplicate of an
	 *                                   element already in the list as determined
	 *                                   by the contains() method
	 * @throws IndexOutOfBoundsException If index is less than 0 or index is greater
	 *                                   than size
	 * @throws NullPointerException      If the element is null
	 */
	public void add(int index, E element) {
		if (contains(element)) {
			throw new IllegalArgumentException();
		} else if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		} else if (element == null) {
			throw new NullPointerException();
		}
		if (index == 0) {
			front = new ListNode(element, front);
			size++;
		} else {
			front.add(index, element);
		}
	}

	/**
	 * Returns the element at the given index. This public method handles bounds
	 * checking on the index. Then the public method transfers the flow of control
	 * to the private ListNode.get(int idx) method, which completes the recursion to
	 * get to element at the appropriate location.
	 * 
	 * @param index Index to return the value from
	 * @return Element at the index
	 * @throws IndexOutOfBoundsException If index is less than 0 or index is greater
	 *                                   than or equal to size
	 */
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return front.get(index);
	}

	/**
	 * Removes a given element from the list. The public method checks if the
	 * element is null, if the list is empty, and handles the special case of
	 * removing the first node in the list. Then the public method transfers the
	 * flow of control to the private ListNode.remove(E element) method, which
	 * completes the recursion to remove the element at the appropriate location.
	 * 
	 * @param element Element to be removed
	 * @return Whether or not the element was removed
	 */
	public boolean remove(E element) {
		if (element == null || isEmpty()) {
			return false;
		}
		if (front.data.equals(element)) {
			front = front.next;
			size--;
			return true;
		}
		return front.remove(element);
	}

	/**
	 * Removes an element at the given index. This public method handles bounds
	 * checking on the index and the special case of removing the first node in the
	 * list. Then the public method transfers the flow of control to the private
	 * ListNode.remove(int idx) method, which completes the recursion to remove to
	 * element at the appropriate location.
	 * 
	 * @param index Index of the element to be removed
	 * @return Element that was removed
	 * @throws IndexOutOfBoundsException If index is less than 0 or index is greater
	 *                                   than or equal to size
	 */
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			E element = front.data;
			front = front.next;
			size--;
			return element;
		}
		return front.remove(index);
	}

	/**
	 * Replaces the element at the given index with the given element. This public
	 * method handles bounds checking on the index. Then the public method transfers
	 * the flow of control to the private ListNode.set(int idx, E element) method,
	 * which completes the recursion to set the element at the appropriate location.
	 * 
	 * @param index   Index of element to be replaced
	 * @param element Element that is replacing the previous element
	 * @return Element that was replaced
	 * @throws IndexOutOfBoundsException If index is less than 0 or index is greater
	 *                                   than or equal to size
	 */
	public E set(int index, E element) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return front.set(index, element);
	}

	/**
	 * Returns whether or not the given element is already in the list. This public
	 * method handles the special case of an empty list. If the list is not empty,
	 * then the public method transfers the flow of control to the private
	 * ListNode.contains(E element) method, which completes the recursion to check
	 * the elements in the list.
	 * 
	 * @param element Element to be searched for
	 * @return True if the given element is already in the list, false otherwise
	 */
	public boolean contains(E element) {
		if (front == null) {
			return false;
		}
		return front.contains(element);
	}

	/**
	 * Class that represents a single node of a LinkedListRecursive object,
	 * containing data and a reference to the next node in the list. Handles the
	 * recursion from the LinkedListRecursive methods.
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

		/**
		 * Adds an element to the end of the list. This public method checks that the
		 * element isn’t already in the list (an IllegalArgumentException is thrown if
		 * the element already exists) and handles the special case of adding a node to
		 * an empty list. If the list is not empty, then the public method transfers the
		 * flow of control to the private ListNode.add(E element) method, which
		 * completes the recursion to add the element to the end of the list.
		 * 
		 * @param element Element to be added
		 * @return Whether or not the element was added
		 */
		public boolean add(E element) {
			if (next == null) {
				next = new ListNode(element, null);
				size++;
				return true;
			}
			return next.add(element);
		}

		/**
		 * Adds an element to the given index in the list. This method completes the
		 * recursion to add the element at the appropriate location from the public
		 * LinkedListRecursive.add(int idx, E element) method.
		 * 
		 * @param index   Index of the element to be added
		 * @param element Element to be added to the list
		 */
		public void add(int index, E element) {
			if (index == 1) {
				next = new ListNode(element, next);
				size++;
			} else {
				next.add(index - 1, element);
			}
		}

		/**
		 * Returns the element at the given index. This method handles completes the
		 * recursion to get to element at the appropriate location from the private
		 * LinkedListRecursive.get(int idx) method.
		 * 
		 * @param index Index to return the value from
		 * @return Element at the index
		 */
		public E get(int index) {
			if (index == 0) {
				return data;
			} else {
				return next.get(index - 1);
			}
		}

		/**
		 * Removes an element at the given index. This method completes the recursion to
		 * remove the element at the appropriate location from the public
		 * LinkedListRecursive.remove(int idx) method.
		 * 
		 * @param index Index of the element to be removed
		 * @return Element that was removed
		 */
		public E remove(int index) {
			if (index == 1) {
				E element = next.data;
				next = next.next;
				size--;
				return element;
			} else {
				return next.remove(index - 1);
			}
		}

		/**
		 * Removes a given element from the list. This method completes the recursion to
		 * remove the element at the appropriate location from the public
		 * LinkedListRecursive.remove(E element) method.
		 * 
		 * @param element Element to be removed
		 * @return Whether or not the element was removed
		 */
		public boolean remove(E element) {
			if (!contains(element)) {
				return false;
			} else if (next.data.equals(element)) {
				next = next.next;
				size--;
				return true;
			} else {
				return next.remove(element);
			}
		}

		/**
		 * Replaces the element at the given index with the given element. This method
		 * completes the recursion to set the element at the appropriate location from
		 * the public LinkedListRecursive.set(int idx, E element) method.
		 * 
		 * @param index   Index of element to be replaced
		 * @param element Element that is replacing the previous element
		 * @return Element that was replaced
		 * @throws IllegalArgumentException If the list already contains the element
		 * @throws NullPointerException     If the element is null
		 */
		public E set(int index, E element) {
			if (contains(element)) {
				throw new IllegalArgumentException();
			} else if (element == null) {
				throw new NullPointerException();
			}
			if (index == 0) {
				E elem = data;
				data = element;
				return elem;
			} else {
				return next.set(index - 1, element);
			}

		}

		/**
		 * Returns whether or not the given element is already in the list. This method
		 * completes the recursion from the public LinkedListRecursive.contains(E
		 * element) method to check the elements in the list.
		 * 
		 * @param element Element to be searched for
		 * @return True if the given element is already in the list, false otherwise
		 */
		public boolean contains(E element) {
			if (data.equals(element)) {
				return true;
			} else if (next == null) {
				return false;
			}
			return next.contains(element);
		}
	}
}