package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Tests LinkedList.
 * 
 * @author Selena Chen
 * @author Atharva Mahajan
 * @author Sarah Morton
 */
public class LinkedListTest {

	/** Instance variable for testing */
	LinkedList<String> test;
	
	/**
	 * Tests the LinkedList() constructor.
	 */
	@Test
	public void testLinkedList() {
		test = new LinkedList<String>();
		assertEquals(0, test.size());
		try {
			test.get(2);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, test.size());
		}
	}
	
	/**
	 * Tests listIterator().
	 */
	@Test
	public void testListIterator() {
		test = new LinkedList<String>();
		
		try {
			test.listIterator(-1);
		} catch (IndexOutOfBoundsException ioobe) {
			assertEquals(0, test.size());
		}
		
		try {
			test.listIterator(1);
		} catch (IndexOutOfBoundsException ioobe) {
			assertEquals(0, test.size());
		}
	}

	/**
	 * Tests size().
	 */
	@Test
	public void testSize() {
		test = new LinkedList<String>();
		test.add(0, "Element 1");
		assertEquals(1, test.size());
		test.add(0, "Element 2");
		assertEquals(2, test.size());
		test.add(0, "Element 3");
		assertEquals(3, test.size());
	}

	/**
	 * Tests LinkedLists's add().
	 */
	@Test
	public void testAddIntE() {
		test = new LinkedList<String>();
		test.add(0, "Element 1");
		assertEquals(1, test.size());
		assertEquals("Element 1", test.get(0));
		test.add(1, "Element 2");
		assertEquals(2, test.size());
		assertEquals("Element 2", test.get(1));
		test.add(2, "Element 3");
		assertEquals(3, test.size());
		assertEquals("Element 3", test.get(2));
		try {
			test.add(4, "Fail");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, test.size());
		}
		try {
			test.add(2, "Element 1");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Element 3", test.get(2));
		}
		try {
			test.add(null);
		} catch (NullPointerException e) {
			assertEquals(3, test.size());
		}

		LinkedList<String> tsTest = new LinkedList<String>();
		tsTest.add(0, "Apple");
		tsTest.add(0, "Orange");
		assertEquals("Apple", tsTest.get(1));
		assertEquals("Orange", tsTest.get(0));
	}

	/**
	 * Tests remove().
	 */
	@Test
	public void testRemoveInt() {
		test = new LinkedList<String>();
		test.add(0, "Element 1");
		test.add(1, "Element 2");
		test.add(2, "Element 3");
		test.add(3, "Element 4");

		assertEquals(4, test.size());
		assertEquals("Element 4", test.remove(3));
		assertEquals(3, test.size());

		assertEquals("Element 2", test.remove(1));
		assertEquals(2, test.size());

		assertEquals("Element 1", test.remove(0));
		assertEquals(1, test.size());

		try {
			test.remove(1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, test.size());
		}
		
		assertEquals("Element 3", test.remove(0));
		assertEquals(0, test.size());
	}

	/**
	 * Tests set().
	 */
	@Test
	public void testSetIntE() {
		test = new LinkedList<String>();
		test.add(0, "Element 1");
		test.set(0, "New Element 1");
		assertEquals(1, test.size());
		assertEquals("New Element 1", test.get(0));

		try {
			test.set(1, "Set shouldn't be able to add an element.");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, test.size());
		}

		try {
			test.set(0, "New Element 1");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1, test.size());
		}

		try {
			test.set(0, null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(1, test.size());
		}
	}

	/**
	 * Tests get().
	 */
	@Test
	public void testGetInt() {
		test = new LinkedList<String>();
		test.add(0, "Element 1");
		test.add(1, "Element 2");

		assertEquals("Element 1", test.get(0));
		try {
			test.get(2);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, test.size());
		}
	}
	
	/**
	 * Tests LinkedListIterator.hasNext().
	 */
	@Test
	public void testHasNext() {
		test = new LinkedList<String>();
		assertFalse(test.listIterator(0).hasNext());
	}
	
	/**
	 * Tests LinkedListIterator.hasPrevious().
	 */
	@Test
	public void testHasPrevious() {
		test = new LinkedList<String>();
		assertFalse(test.listIterator(0).hasPrevious());
	}
	
	/**
	 * Tests LinkedListIterator.nextIndex().
	 */
	@Test
	public void testNextIndex() {
		test = new LinkedList<String>();
		assertEquals(0, test.listIterator(0).nextIndex());
	}
	
	/**
	 * Tests LinkedListIterator.previousIndex().
	 */
	@Test
	public void testPreviousIndex() {
		test = new LinkedList<String>();
		assertEquals(-1, test.listIterator(0).previousIndex());
	}
	
	/**
	 * Tests LinkedListIterator.next().
	 */
	@Test
	public void testNext() {
		test = new LinkedList<String>();
		try {
			test.listIterator(0).next();
		} catch (NoSuchElementException nsee) {
			assertEquals(0, test.size());
		}
	}
	
	/**
	 * Tests LinkedListIterator.previous().
	 */
	@Test
	public void testPrevious() {
		test = new LinkedList<String>();
		try {
			test.listIterator(0).previous();
		} catch (NoSuchElementException nsee) {
			assertEquals(0, test.size());
		}
	}
}