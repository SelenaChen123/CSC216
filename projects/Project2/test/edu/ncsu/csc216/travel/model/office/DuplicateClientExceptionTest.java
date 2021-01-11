package edu.ncsu.csc216.travel.model.office;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests DuplicateClientException.
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public class DuplicateClientExceptionTest {

	/**
	 * Tests the DuplicateClientException constructor.
	 */
	@Test
	public void testDuplicateClientException() {
		DuplicateClientException e = new DuplicateClientException();
		assertEquals("Duplicate client", e.getMessage());
	}

	/**
	 * Tests the DuplicateClientException constructor with a given message.
	 */
	@Test
	public void testDuplicateClientExceptionString() {
		DuplicateClientException e = new DuplicateClientException("Oops");
		assertEquals("Oops", e.getMessage());
	}
}