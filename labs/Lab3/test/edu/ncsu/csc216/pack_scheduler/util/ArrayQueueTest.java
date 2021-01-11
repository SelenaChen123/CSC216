package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests ArrayQueue.
 * 
 * @author Selena Chen
 * @author Atharva Mahajan
 * @author Sarah Morton
 */
public class ArrayQueueTest {

	/** Instance variable for testing */
	ArrayQueue<String> test;

	/** Constant to hold the LinkedAbstractList's capacity */
	private static final int CAPACITY = 5;

	/**
	 * Tests the ArrayQueue() constructor.
	 */
	@Test
	public void testArrayQueue() {
		test = new ArrayQueue<String>(CAPACITY);
		assertEquals(0, test.size());

		try {
			test = new ArrayQueue<String>(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, test.size());
		}
	}

	/**
	 * Tests size().
	 */
	@Test
	public void testSize() {
		test = new ArrayQueue<String>(CAPACITY);
		test.enqueue("Element 1");
		assertEquals(1, test.size());
		test.enqueue("Element 2");
		assertEquals(2, test.size());
		test.enqueue("Element 3");
		assertEquals(3, test.size());
	}

	/**
	 * Tests isEmpty().
	 */
	@Test
	public void testIsEmpty() {
		test = new ArrayQueue<String>(CAPACITY);
		assertTrue(test.isEmpty());
		test.enqueue("Element 1");
		assertFalse(test.isEmpty());
	}

	/**
	 * Tests setCapacity().
	 */
	@Test
	public void testSetCapacity() {
		try {
			test = new ArrayQueue<String>(-1);
			fail();
		} catch (IllegalArgumentException iae) {
			assertNull(test);
		}

		test = new ArrayQueue<String>(CAPACITY);
		test.enqueue("Element 1");
		test.enqueue("Element 2");
		test.enqueue("Element 3");
		test.enqueue("Element 4");
		test.enqueue("Element 5");
		assertEquals(5, test.size());

		try {
			test.enqueue("Element 6");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(5, test.size());
		}

		test.setCapacity(6);
		test.enqueue("Element 6");
		assertEquals(6, test.size());
	}

	/**
	 * Tests enqueue().
	 */
	@Test
	public void testEnqueue() {
		test = new ArrayQueue<String>(CAPACITY);
		test.enqueue("Element 1");
		assertEquals(1, test.size());
		test.enqueue("Element 2");
		assertEquals(2, test.size());
		test.enqueue("Element 3");
		assertEquals(3, test.size());
		test.enqueue("Element 4");
		assertEquals(4, test.size());
		test.enqueue("Element 5");
		assertEquals(5, test.size());

		try {
			test.enqueue("Element 6");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(5, test.size());
		}
	}

	/**
	 * Tests dequeue().
	 */
	@Test
	public void testDequeue() {
		test = new ArrayQueue<String>(CAPACITY);
		test.enqueue("Element 1");
		assertEquals(1, test.size());
		test.enqueue("Element 2");
		assertEquals(2, test.size());
		test.enqueue("Element 3");
		assertEquals(3, test.size());
		test.enqueue("Element 4");
		assertEquals(4, test.size());
		test.enqueue("Element 5");
		assertEquals(5, test.size());

		try {
			test.enqueue("Element 6");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(5, test.size());
		}

		String removed = test.dequeue();
		assertEquals("Element 1", removed);
		removed = test.dequeue();
		assertEquals("Element 2", removed);
		removed = test.dequeue();
		assertEquals("Element 3", removed);
		removed = test.dequeue();
		assertEquals("Element 4", removed);
		removed = test.dequeue();
		assertEquals("Element 5", removed);
	}
}