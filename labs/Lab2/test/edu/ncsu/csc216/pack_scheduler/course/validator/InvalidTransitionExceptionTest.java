package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests InvalidTransitionException.
 * 
 * @author Justin Wald
 * @author Selena Chen
 */
public class InvalidTransitionExceptionTest {

	/** Tests the InvalidTransitionException constructor when not passed a message. */
	@Test
	public void testDefaultInvalidTransitionException() {
		try {
			throw new InvalidTransitionException();
		} catch (InvalidTransitionException e) {
			assertEquals("Invalid FSM Transition.", e.getMessage());
		}
	}

	/**
	 * Tests the InvalidTransitionException constructor when passed a message.
	 */
	@Test
	public void testInvalidTransitionExceptionWithMessage() {
		try {
			throw new InvalidTransitionException("Testing testing 123");
		} catch (InvalidTransitionException e) {
			assertEquals("Testing testing 123", e.getMessage());
		}
	}
}