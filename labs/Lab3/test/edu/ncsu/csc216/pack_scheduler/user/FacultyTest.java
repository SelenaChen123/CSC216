package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests Faculty.
 * 
 * @author Selena Chen
 * @author Atharva Mahajan
 * @author Sarah Morton
 */
public class FacultyTest {

	/** Course name */
	private static final String FIRST = "Mark";
	/** Course title */
	private static final String LAST = "Smith";
	/** Course section */
	private static final String ID = "msmith";
	/** Course section */
	private static final String EMAIL = "msmith@ncsu.edu";
	/** Course section */
	private static final String PASSWORD = "Msmith2019!";
	/** Course credits */
	private static final int MAXCOURSES = 3;
	/** Course registered credits */
	private static final int REGCOURSES = 2;
	/** Course invalid too high credits */
	private static final int INVALIDCOURSESHIGH = 4;
	/** Course invalid too low credits */
	private static final int INVALIDCOURSESLOW = 0;

	/**
	 * Tests hashCode().
	 */
	@Test
	public void testHashCode() {
		User s1 = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		User s2 = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		User s3 = new Faculty("Leon", LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		User s4 = new Faculty(FIRST, "John", ID, EMAIL, PASSWORD, MAXCOURSES);
		User s5 = new Faculty(FIRST, LAST, "ljohn", EMAIL, PASSWORD, MAXCOURSES);
		User s6 = new Faculty(FIRST, LAST, ID, "ljohn@ncsu.edu", PASSWORD, MAXCOURSES);
		User s7 = new Faculty(FIRST, LAST, ID, EMAIL, "Ljohn2019!", MAXCOURSES);
		User s8 = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, REGCOURSES);

		// Test for the same hash code for the same values
		assertEquals(s1.hashCode(), s2.hashCode());

		// Test for each of the fields
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());
	}

	/**
	 * Tests Faculty(String, String, String, String, String, Int) constructor.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testFacultyStringStringStringStringStringInt() {
		Faculty s = null; // Initialize a Faculty reference to null
		try {
			s = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAXCOURSES, s.getMaxCourses());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests Faculty(String, String, String, String, String, Int) constructor with a
	 * null first name.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testFacultyStringStringStringStringStringIntNullFirstName() {
		User s = null; // Initialize a Faculty reference to null
		try {
			s = new Faculty(null, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Faculty(String, String, String, String, String, Int) constructor with a
	 * null last name.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testFacultyStringStringStringStringStringIntNullLastName() {
		User s = null; // Initialize a Faculty reference to null
		try {
			s = new Faculty(FIRST, null, ID, EMAIL, PASSWORD, MAXCOURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Faculty(String, String, String, String, String, Int) constructor with a
	 * null id.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testFacultyStringStringStringStringStringIntNullId() {
		User s = null; // Initialize a Faculty reference to null
		try {
			s = new Faculty(FIRST, LAST, null, EMAIL, PASSWORD, MAXCOURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Faculty(String, String, String, String, String, Int) constructor with a
	 * null email.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testFacultyStringStringStringStringStringIntNullEmail() {
		User s = null; // Initialize a Faculty reference to null
		try {
			s = new Faculty(FIRST, LAST, ID, null, PASSWORD, MAXCOURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Faculty(String, String, String, String, String, Int) constructor with a
	 * null password.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testFacultyStringStringStringStringStringIntNullPassword() {
		User s = null; // Initialize a Faculty reference to null
		try {
			s = new Faculty(FIRST, LAST, ID, EMAIL, null, MAXCOURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Faculty(String, String, String, String, String, Int) constructor with
	 * invalid too low credits.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testFacultyStringStringStringStringStringIntOutOfBoundCourseLow() {
		User s = null; // Initialize a Faculty reference to null
		try {
			s = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, INVALIDCOURSESLOW);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Faculty(String, String, String, String, String, Int) constructor with
	 * invalid too high credits.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testFacultyStringStringStringStringStringIntOutOfBoundCourseHigh() {
		User s = null; // Initialize a Faculty reference to null
		try {
			s = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, INVALIDCOURSESHIGH);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Faculty(String, String, String, String, String, Int) constructor with
	 * an empty first name.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testFacultyStringStringStringStringStringIntEmptyFirstName() {
		User s = null; // Initialize a Faculty reference to null
		try {
			s = new Faculty("", LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Faculty(String, String, String, String, String, Int) constructor with
	 * an empty last name.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testFacultyStringStringStringStringStringIntEmptyLastName() {
		User s = null; // Initialize a Faculty reference to null
		try {
			s = new Faculty(FIRST, "", ID, EMAIL, PASSWORD, MAXCOURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Faculty(String, String, String, String, String, Int) constructor with
	 * an empty id.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testFacultyStringStringStringStringStringIntEmptyId() {
		User s = null; // Initialize a Faculty reference to null
		try {
			s = new Faculty(FIRST, LAST, "", EMAIL, PASSWORD, MAXCOURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Faculty(String, String, String, String, String, Int) constructor with
	 * an empty email.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testFacultyStringStringStringStringStringIntEmptyEmail() {
		User s = null; // Initialize a Faculty reference to null
		try {
			s = new Faculty(FIRST, LAST, ID, "", PASSWORD, MAXCOURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Faculty(String, String, String, String, String, Int) constructor with
	 * an empty password.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testFacultyStringStringStringStringStringIntEmptyPassword() {
		User s = null; // Initialize a Faculty reference to null
		try {
			s = new Faculty(FIRST, LAST, ID, EMAIL, "", MAXCOURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests setFirstName().
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testSetFirstName() {
		// Construct a valid Faculty
		User s = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		try {
			s.setFirstName("Mark");
			assertEquals("Mark", s.getFirstName());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests setFirstName() with a null parameter.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testSetNullFirstName() {
		// Construct a valid Faculty
		User s = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		try {
			s.setFirstName(null);
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(FIRST, s.getFirstName());
		}
	}

	/**
	 * Tests setFirstName() with an empty string.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testSetEmptyFirstName() {
		// Construct a valid Faculty
		User s = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		try {
			s.setFirstName("");
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(FIRST, s.getFirstName());
		}
	}

	/**
	 * Tests setLastName().
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testSetLastName() {
		// Construct a valid Faculty
		User s = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		try {
			s.setLastName("Smith");
			assertEquals("Smith", s.getLastName());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests setLastName() with a null parameter.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testSetNullLastName() {
		// Construct a valid Faculty
		User s = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		try {
			s.setLastName(null);
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(LAST, s.getLastName());
		}
	}

	/**
	 * Tests setLastName() with an empty string.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testSetEmptyLastName() {
		// Construct a valid Faculty
		User s = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		try {
			s.setLastName("");
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(LAST, s.getLastName());
		}
	}

	/**
	 * Tests setEmail().
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testSetEmail() {
		// Construct a valid Faculty
		User s = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		try {
			s.setEmail("msmith@ncsu.edu");
			assertEquals("msmith@ncsu.edu", s.getEmail());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests setEmail() with a null parameter.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testSetNullEmail() {
		// Construct a valid Faculty
		User s = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		try {
			s.setEmail(null);
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(EMAIL, s.getEmail());
		}
	}

	/**
	 * Tests setEmail() with an empty string.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testSetEmptyEmail() {
		// Construct a valid Faculty
		User s = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		try {
			s.setEmail("");
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(EMAIL, s.getEmail());
		}
	}

	/**
	 * Tests setEmail() with a missing '@' symbol.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testSetInvalidEmailWithMissingAt() {
		// Construct a valid Faculty
		User s = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		try {
			s.setEmail("msmithncsu.edu");
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(EMAIL, s.getEmail());
		}
	}

	/**
	 * Tests setEmail() with a missing '.' symbol.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testSetInvalidEmailWithMissingDot() {
		// Construct a valid Faculty
		User s = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		try {
			s.setEmail("msmith@ncsuedu");
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(EMAIL, s.getEmail());
		}
	}

	/**
	 * Tests setEmail() with invalid input.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testSetInvalidEmail() {
		// Construct a valid Faculty
		User s = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		try {
			s.setEmail("msmith.ncsu@edu");
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(EMAIL, s.getEmail());
		}
	}

	/**
	 * Tests setPassword().
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testSetPassword() {
		// Construct a valid Faculty
		User s = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		try {
			s.setPassword("Msmith2019!");
			assertEquals("Msmith2019!", s.getPassword());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests setPassword() with a null parameter.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testSetNullPassword() {
		// Construct a valid Faculty
		User s = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		try {
			s.setPassword(null);
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(PASSWORD, s.getPassword());
		}
	}

	/**
	 * Tests setPassword() with an empty string.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testSetEmptyPassword() {
		// Construct a valid Faculty
		User s = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		try {
			s.setPassword("");
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(PASSWORD, s.getPassword());
		}
	}

	/**
	 * Tests setMaxCourses().
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testsetMaxCourses() {
		// Construct a valid Faculty
		Faculty s = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		try {
			s.setMaxCourses(REGCOURSES);
			assertEquals(REGCOURSES, s.getMaxCourses());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests setMaxCourses() with invalid too low max credits.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testSetInvalidLowMaxCourses() {
		// Construct a valid Faculty
		Faculty s = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		try {
			s.setMaxCourses(0);
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(MAXCOURSES, s.getMaxCourses());
		}
	}

	/**
	 * Tests setMaxCourses() with invalid too high max credits.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testSetInvalidHighMaxCourses() {
		// Construct a valid Faculty
		Faculty s = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		try {
			s.setMaxCourses(INVALIDCOURSESHIGH);
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(MAXCOURSES, s.getMaxCourses());
		}
	}

	/**
	 * Tests equals().
	 */
	@Test
	public void testEqualsObject() {
		User s1 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		User s2 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		User s3 = new Faculty("firstName", "lastName", "unityid", "FacultyEmail@ncsu.edu", "password", 2);
		User s4 = new Faculty("Mark", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		User s5 = new Faculty("first", "Smith", "id", "email@ncsu.edu", "hashedpassword", 2);
		User s6 = new Faculty("first", "last", "msmith", "email@ncsu.edu", "hashedpassword", 2);
		User s7 = new Faculty("first", "last", "id", "msmith@ncsu.edu", "hashedpassword", 2);
		User s8 = new Faculty("first", "last", "id", "email@ncsu.edu", "Msmith2019!", 2);
		User s9 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 3);

		assertTrue(s1.equals(s2));

		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));
		assertFalse(s1.equals(s9));
	}

	/**
	 * Tests toString().
	 */
	@Test
	public void testToString() {
		User s1 = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		String string1 = "Mark,Smith,msmith,msmith@ncsu.edu,Msmith2019!,3";
		assertEquals(string1, s1.toString());
	}
}