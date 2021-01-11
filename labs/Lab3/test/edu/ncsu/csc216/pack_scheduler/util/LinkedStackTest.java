package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;

/**
 * Tests LinkedStack.
 * 
 * @author Selena Chen
 * @author Atharva Mahajan
 * @author Sarah Morton
 */
public class LinkedStackTest {

	/** Instance variable for testing */
	LinkedStack<String> stack;

	/**
	 * Tests the LinkedStack() constructor.
	 */
	@Test
	public void testLinkedStack() {
		// Constructs a LinkedStack with a valid capacity
		stack = new LinkedStack<String>(5);

		// Construct a LinkedStack with an invalid capacity
		try {
			stack = new LinkedStack<String>(-1);
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
		stack = new LinkedStack<String>(5);
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
		stack = new LinkedStack<String>(5);
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
		stack = new LinkedStack<String>(5);
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
		stack = new LinkedStack<String>(5);
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
		stack = new LinkedStack<String>(5);

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