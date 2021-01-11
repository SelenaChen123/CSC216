package edu.ncsu.csc216.travel.model.vacation;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests CapacityException.
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public class CapacityExceptionTest {

	/**
	 * Tests the CapacityException constructor.
	 */
	@Test
	public void testCapacityException() {
		CapacityException e = new CapacityException();
		assertEquals("Capacity exception", e.getMessage());
	}

	/**
	 * Tests the CapacityException constructor with a given message.
	 */
	@Test
	public void testCapacityExceptionString() {
		CapacityException e = new CapacityException("This is an exception");
		assertEquals("This is an exception", e.getMessage());
	}
}