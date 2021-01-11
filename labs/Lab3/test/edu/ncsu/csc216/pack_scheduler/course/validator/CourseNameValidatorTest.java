package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests CourseNameValidator.
 * 
 * @author Selena Chen
 * @author Justin Wald
 */
public class CourseNameValidatorTest {

	/** CourseNameValidator object used to test the validity of given course names */
	private CourseNameValidator c;

	/**
	 * Sets up the CourseNameValidator object for testing.
	 */
	@Before
	public void setUp() {
		c = new CourseNameValidator();
	}

	/**
	 * Tests isValid() with valid course names.
	 */
	@Test
	public void testIsValid() {
		try {
			assertTrue(c.isValid("M380"));
			assertTrue(c.isValid("M380H"));
			assertTrue(c.isValid("CH101"));
			assertTrue(c.isValid("CH101H"));
			assertTrue(c.isValid("CSC116"));
			assertTrue(c.isValid("CSC116H"));
			assertTrue(c.isValid("CSCS116"));
			assertTrue(c.isValid("CSCS116H"));
			assertTrue(c.isValid("csc116"));
		} catch (InvalidTransitionException e) {
			fail();
		}
	}

	/**
	 * Tests isValid() with invalid course names that don't have invalid
	 * transitions.
	 */
	@Test
	public void testIsNotValidWithoutExceptions() {
		try {
			assertFalse(c.isValid("CSC"));
		} catch (InvalidTransitionException e) {
			fail();
		}

		try {
			assertFalse(c.isValid("CSC1"));
		} catch (InvalidTransitionException e) {
			fail();
		}

		try {
			assertFalse(c.isValid("CSC01"));
		} catch (InvalidTransitionException e) {
			fail();
		}

		try {
			assertFalse(c.isValid("C"));
		} catch (InvalidTransitionException e) {
			fail();
		}

		try {
			assertFalse(c.isValid("CS01"));
		} catch (InvalidTransitionException e) {
			fail();
		}

		try {
			assertFalse(c.isValid("CS1"));
		} catch (InvalidTransitionException e) {
			fail();
		}

		try {
			assertFalse(c.isValid("C01"));
		} catch (InvalidTransitionException e) {
			fail();
		}

		try {
			assertFalse(c.isValid("C1"));
		} catch (InvalidTransitionException e) {
			fail();
		}
	}

	/**
	 * Tests isValid() with invalid course names that have invalid transitions.
	 */
	@Test
	public void testIsNotValidWithExceptions() {

		try {
			assertFalse(c.isValid("C$C"));
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}

		try {
			assertFalse(c.isValid("216csc"));
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must start with a letter.", e.getMessage());
		}

		try {
			assertFalse(c.isValid("CSCHH216"));
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
		}
		
		try {
			assertFalse(c.isValid("csc2h"));
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}

		try {
			assertFalse(c.isValid("csc21h"));
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}

		try {
			assertFalse(c.isValid("csc2016"));
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}

		try {
			assertFalse(c.isValid("CSC216HH"));
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
		}

		try {
			assertFalse(c.isValid("CSC216H1"));
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
		}
	}
}