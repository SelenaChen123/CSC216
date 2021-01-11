package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests LinkedAbstractList.
 * 
 * @author Selena Chen
 * @author Justin Wald
 * @author Atharva Mahajan
 * @author Sarah Morton
 */
public class LinkedAbstractListTest {

	/** Instance variable for testing */
	LinkedAbstractList<String> test;

	/** Constant to hold the LinkedAbstractList's capacity */
	private static final int CAPACITY = 5;

	/**
	 * Tests size().
	 */
	@Test
	public void testSize() {
		test = new LinkedAbstractList<String>(CAPACITY);
		test.add(0, "Element 1");
		assertEquals(1, test.size());
		test.add(0, "Element 2");
		assertEquals(2, test.size());
		test.add(0, "Element 3");
		assertEquals(3, test.size());
	}

	/**
	 * Tests the LinkedAbstractList() constructor.
	 */
	@Test
	public void testLinkedAbstractList() {
		test = new LinkedAbstractList<String>(CAPACITY);
		assertEquals(0, test.size());
		try {
			test.get(2);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, test.size());
		}
	}

	/**
	 * Tests add().
	 */
	@Test
	public void testAddIntE() {
		test = new LinkedAbstractList<String>(CAPACITY);
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

		LinkedAbstractList<String> tsTest = new LinkedAbstractList<String>(CAPACITY);
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
		test = new LinkedAbstractList<String>(CAPACITY);
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
		test = new LinkedAbstractList<String>(CAPACITY);
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
		test = new LinkedAbstractList<String>(CAPACITY);
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
	 * Tests the capacity of a LinkedAbstractList.
	 */
	@Test
	public void testCapacity() {
		try {
			test = new LinkedAbstractList<String>(-1);
		} catch (IllegalArgumentException iae) {
			assertNull(test);
		}

		test = new LinkedAbstractList<String>(CAPACITY);
		test.add(0, "Element 1");
		test.add(1, "Element 2");
		test.add(2, "Element 3");
		test.add(3, "Element 4");
		test.add(4, "Element 5");
		try {
			test.add(5, "Element 6");
		} catch (IllegalArgumentException iae) {
			assertEquals(5, test.size());
		}
	}
}