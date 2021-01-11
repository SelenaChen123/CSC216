package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;

/**
 * Tests ArrayStack.
 * 
 * @author Selena Chen
 * @author Atharva Mahajan
 * @author Sarah Morton
 */
public class ArrayStackTest {

	/** Instance variable for testing */
	ArrayStack<String> stack;

	/**
	 * Tests the ArrayStack() constructor.
	 */
	@Test
	public void testArrayStack() {
		// Constructs an ArrayStack with a valid capacity
		stack = new ArrayStack<String>(5);

		// Construct an ArrayStack with an invalid capacity
		try {
			stack = new ArrayStack<String>(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, stack.size());
		}
	}

	/**
	 * Tests size().
	 */
	@Test
	public void testSize() {
		stack = new ArrayStack<String>(5);
		assertEquals(0, stack.size());
		assertTrue(stack.isEmpty());
		stack.push("Mango");
		stack.push("Watermelon");
		assertFalse(stack.isEmpty());
		assertEquals(2, stack.size());
	}

	/**
	 * Tests isEmpty().
	 */
	@Test
	public void testIsEmpty() {
		stack = new ArrayStack<String>(5);
		assertTrue(stack.isEmpty());
		stack.push("Mango");
		stack.push("watermelon");
		assertFalse(stack.isEmpty());
		assertEquals(2, stack.size());
	}

	/**
	 * Tests push().
	 */
	@Test
	public void testPush() {
		stack = new ArrayStack<String>(5);
		assertTrue(stack.isEmpty());
		stack.push("Mango");
		stack.push("watermelon");
		stack.push("Peach");
		stack.push("Papaya");
		stack.push("Avocado");
		assertEquals(5, stack.size());
		try {
			stack.push("Apple");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(5, stack.size());
		}
	}

	/**
	 * Tests pop().
	 */
	@Test
	public void testPop() {
		stack = new ArrayStack<String>(5);
		assertTrue(stack.isEmpty());
		try {
			stack.pop();
			fail();
		} catch (EmptyStackException e) {
			assertEquals(0, stack.size());
		}

		stack.push("Mango");
		stack.push("watermelon");
		stack.push("Peach");
		stack.push("Papaya");
		stack.push("Avocado");
		assertEquals(5, stack.size());
		assertEquals("Avocado", stack.pop());
		assertEquals(4, stack.size());
	}

	/**
	 * Tests setCapacity().
	 */
	@Test
	public void testSetCapacity() {
		stack = new ArrayStack<String>(5);

		try {
			stack.setCapacity(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, stack.size());
		}
		stack.push("Mango");
		stack.push("watermelon");
		stack.push("Peach");
		stack.push("Papaya");
		stack.push("Avocado");
		assertEquals(5, stack.size());
		try {
			stack.setCapacity(4);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(5, stack.size());
		}
		stack.setCapacity(7);
		assertEquals("Avocado", stack.pop());
		assertEquals("Papaya", stack.pop());
		assertEquals(3, stack.size());
		stack.push("Cantaloupe");
		stack.push("Tomato");
		assertEquals("Tomato", stack.pop());
		try {
			stack.setCapacity(-1);
			fail();
		} catch (IllegalArgumentException e1) {
			assertEquals(4, stack.size());
		}
	}
}