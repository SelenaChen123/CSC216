package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;
import java.util.Arrays;

/**
 * Custom implementation of an array list that doesn't allow for null elements
 * or duplicate elements as defined by the equals() method.
 * 
 * @param <E> generic type parameter
 * 
 * @author Selena Chen
 * @author Justin Wald
 */
public class ArrayList<E> extends AbstractList<E> {

	/** Constant value for initializing the list size */
	private static final int INIT_SIZE = 10;
	/** Array of type E */
	private E[] list;
	/** Size of the list */
	private int size;

	/**
	 * Constructs an ArrayList object.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		list = (E[]) new Object[INIT_SIZE];
		size = 0;
	}

	/**
	 * Adds an element to the given index.
	 * 
	 * @param index   index where the element should be added
	 * @param element element to be added to the ArrayList
	 * @throws IndexOutOfBoundsException if index is less than 0 or larger than size
	 *                                   of ArrayList
	 * @throws IllegalArgumentException  if element is a duplicate
	 * @throws NullPointerException      if element is null
	 */
	@Override
	public void add(int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		} else if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		for (E e : list) {
			if (element.equals(e)) {
				throw new IllegalArgumentException();
			}
		}
		size++;
		if (size == list.length) {
			growArray();
		}
		for (int i = size - 1; i >= index; i--) {
			list[i + 1] = list[i];
		}
		set(index, element);
	}

	/**
	 * Doubles the capacity of the array.
	 */
	private void growArray() {
		list = Arrays.copyOf(list, 2 * size);
	}

	/**
	 * Removes the element at the given index.
	 * 
	 * @param index index of element to be removed from the ArrayList
	 * @return element that was removed
	 * @throws IndexOutOfBoundsException if index is less than 0 or larger than size
	 *                                   of ArrayList
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		E element = list[index];
		for (int i = index + 1; i <= size; i++) {
			list[i - 1] = list[i];
		}
		list[size - 1] = null;
		size--;
		return element;
	}

	/**
	 * Replaces the element at the specified index with the given element.
	 * 
	 * @param index   index of the element to be replaced
	 * @param element given element that is replacing the element at the given index
	 * @return original element at the given index
	 * @throws IndexOutOfBoundsException if index is less than 0 or larger than size
	 *                                   of ArrayList
	 * @throws IllegalArgumentException  if element is a duplicate
	 * @throws NullPointerException      if element is null
	 */
	public E set(int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		} else if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		for (E e : list) {
			if (element.equals(e)) {
				throw new IllegalArgumentException();
			}
		}
		E replaced = list[index];
		list[index] = element;
		return replaced;
	}

	/**
	 * Returns the element at the given index.
	 * 
	 * @param index index of the element to be returned
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if index is less than 0 or larger than size
	 *                                   of ArrayList
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		E element = list[index];
		return element;
	}

	/**
	 * Returns the size of the ArrayList.
	 * 
	 * @return size of the ArrayList
	 */
	@Override
	public int size() {
		return size;
	}
}