package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the ConflictException class.
 * 
 * @author Selena Chen
 */
public class ConflictExceptionTest {

	/**
	 * Tests the ConflictException constructor with a String parameter.
	 */
	@Test
	public void testConflictExceptionString() {
		ConflictException ce = new ConflictException("Custom exception message");
		assertEquals("Custom exception message", ce.getMessage());
	}

	/**
	 * Tests the ConflictException constructor without parameters.
	 */
	@Test
	public void testConflictException() {
		ConflictException ce = new ConflictException();
		assertEquals("Schedule conflict.", ce.getMessage());
	}
}