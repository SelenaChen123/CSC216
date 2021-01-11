package edu.ncsu.csc216.travel.model.office;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests DuplicateTourException.
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public class DuplicateTourExceptionTest {

	/**
	 * Tests the DuplicateTourException constructor.
	 */
	@Test
	public void testDuplicateTourException() {
		DuplicateTourException e = new DuplicateTourException();
		assertEquals("Duplicate tour", e.getMessage());
	}

	/**
	 * Tests the DuplicateTourException constructor with a given message.
	 */
	@Test
	public void testDuplicateTourExceptionString() {
		DuplicateTourException e = new DuplicateTourException("Oops");
		assertEquals("Oops", e.getMessage());
	}
}