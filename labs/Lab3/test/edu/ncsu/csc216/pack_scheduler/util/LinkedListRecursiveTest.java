package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.*;

/**
 * Tests LinkedListRecursiveRecursive.
 * 
 * @author Selena Chen
 * @author Atharva Mahajan
 * @author Sarah Morton
 */
public class LinkedListRecursiveTest {
	
	/** Instance variable for testing */
	LinkedListRecursive<String> test;

	/**
	 * Tests the LinkedListRecursiveRecursive constructor.
	 */
	@Test
	public void testLinkedListRecursiveRecursive() {
		test = new LinkedListRecursive<String>();
		assertEquals(0, test.size());
		try {
			test.get(2);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, test.size());
		}
	}

	/**
	 * Tests isEmpty().
	 */
	@Test
	public void testIsEmpty() {
		test = new LinkedListRecursive<String>();
		assertTrue(test.isEmpty());
	}

	/**
	 * Tests size().
	 */
	@Test
	public void testSize() {
		test = new LinkedListRecursive<String>();
		test.add(0, "Element 1");
		assertEquals(1, test.size());
		test.add(0, "Element 2");
		assertEquals(2, test.size());
		test.add(0, "Element 3");
		assertEquals(3, test.size());
	}

	/**
	 * Tests add() with an element parameter.
	 */
	@Test
	public void testAddE() {
		test = new LinkedListRecursive<String>();
		test.add("Element 1");
		assertEquals(1, test.size());
		assertEquals("Element 1", test.get(0));
		test.add("Element 2");
		assertEquals(2, test.size());
		assertEquals("Element 2", test.get(1));
		test.add("Element 3");
		assertEquals(3, test.size());
		assertEquals("Element 3", test.get(2));
	}

	/**
	 * Tests add() with index and element parameters.
	 */
	@Test
	public void testAddIntE() {
		test = new LinkedListRecursive<String>();
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

		LinkedListRecursive<String> tsTest = new LinkedListRecursive<String>();
		tsTest.add(0, "Apple");
		tsTest.add(0, "Orange");
		assertEquals("Apple", tsTest.get(1));
		assertEquals("Orange", tsTest.get(0));
	}

	/**
	 * Tests get().
	 */
	@Test
	public void testGet() {
		test = new LinkedListRecursive<String>();
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
	 * Tests remove() with an element parameter.
	 */
	@Test
	public void testRemoveE() {
		test = new LinkedListRecursive<String>();
		test.add(0, "Element 1");
		test.add(1, "Element 2");
		test.add(2, "Element 3");
		test.add(3, "Element 4");

		assertEquals(4, test.size());
		assertTrue(test.remove("Element 4"));
		assertEquals(3, test.size());

		assertTrue(test.remove("Element 2"));
		assertEquals(2, test.size());

		assertTrue(test.remove("Element 1"));
		assertEquals(1, test.size());
		
		assertTrue(test.remove("Element 3"));
		assertEquals(0, test.size());
	}

	/**
	 * Tests remove() with an index parameter.
	 */
	@Test
	public void testRemoveInt() {
		test = new LinkedListRecursive<String>();
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
	public void testSet() {
		test = new LinkedListRecursive<String>();
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
	 * Tests contains().
	 */
	@Test
	public void testContains() {
		test = new LinkedListRecursive<String>();
		test.add(0, "Element 1");
		test.add(1, "Element 2");
		test.add(2, "Element 3");
		test.add(3, "Element 4");
		
		assertTrue(test.contains("Element 1"));
		assertTrue(test.contains("Element 2"));
		assertTrue(test.contains("Element 3"));
		assertTrue(test.contains("Element 4"));
		assertFalse(test.contains("Element"));
		assertFalse(test.contains("ELEMENT 1"));
	}
}