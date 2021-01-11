package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests Student.
 * 
 * @author Selena Chen
 * @author Travis Walter
 * @author Tyler Mathis
 * @author Justin Wald
 */
public class StudentTest {

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
	private static final int MAXCREDITS = 18;
	/** Course registered credits */
	private static final int REGCREDITS = 10;
	/** Course invalid too high credits */
	private static final int INVALIDCREDITSHIGH = 19;
	/** Course invalid too low credits */
	private static final int INVALIDCREDITSLOW = 0;

	/**
	 * Tests hashCode().
	 */
	@Test
	public void testHashCode() {
		User s1 = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCREDITS);
		User s2 = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCREDITS);
		User s3 = new Student("Leon", LAST, ID, EMAIL, PASSWORD, MAXCREDITS);
		User s4 = new Student(FIRST, "John", ID, EMAIL, PASSWORD, MAXCREDITS);
		User s5 = new Student(FIRST, LAST, "ljohn", EMAIL, PASSWORD, MAXCREDITS);
		User s6 = new Student(FIRST, LAST, ID, "ljohn@ncsu.edu", PASSWORD, MAXCREDITS);
		User s7 = new Student(FIRST, LAST, ID, EMAIL, "Ljohn2019!", MAXCREDITS);
		User s8 = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, REGCREDITS);

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
	 * Tests Student(String, String, String, String, String, Int) constructor.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testStudentStringStringStringStringStringInt() {
		Student s = null; // Initialize a student reference to null
		try {
			s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCREDITS);
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAXCREDITS, s.getMaxCredits());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests Student(String, String, String, String, String, Int) constructor with a
	 * null first name.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testStudentStringStringStringStringStringIntNullFirstName() {
		User s = null; // Initialize a student reference to null
		try {
			s = new Student(null, LAST, ID, EMAIL, PASSWORD, MAXCREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Student(String, String, String, String, String, Int) constructor with a
	 * null last name.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testStudentStringStringStringStringStringIntNullLastName() {
		User s = null; // Initialize a student reference to null
		try {
			s = new Student(FIRST, null, ID, EMAIL, PASSWORD, MAXCREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Student(String, String, String, String, String, Int) constructor with a
	 * null id.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testStudentStringStringStringStringStringIntNullId() {
		User s = null; // Initialize a student reference to null
		try {
			s = new Student(FIRST, LAST, null, EMAIL, PASSWORD, MAXCREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Student(String, String, String, String, String, Int) constructor with a
	 * null email.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testStudentStringStringStringStringStringIntNullEmail() {
		User s = null; // Initialize a student reference to null
		try {
			s = new Student(FIRST, LAST, ID, null, PASSWORD, MAXCREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Student(String, String, String, String, String, Int) constructor with a
	 * null password.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testStudentStringStringStringStringStringIntNullPassword() {
		User s = null; // Initialize a student reference to null
		try {
			s = new Student(FIRST, LAST, ID, EMAIL, null, MAXCREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Student(String, String, String, String, String, Int) constructor with
	 * invalid too low credits.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testStudentStringStringStringStringStringIntOutOfBoundCreditLow() {
		User s = null; // Initialize a student reference to null
		try {
			s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, INVALIDCREDITSLOW);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Student(String, String, String, String, String, Int) constructor with
	 * invalid too high credits.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testStudentStringStringStringStringStringIntOutOfBoundCreditHigh() {
		User s = null; // Initialize a student reference to null
		try {
			s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, INVALIDCREDITSHIGH);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Student(String, String, String, String, String, Int) constructor with
	 * an empty first name.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testStudentStringStringStringStringStringIntEmptyFirstName() {
		User s = null; // Initialize a student reference to null
		try {
			s = new Student("", LAST, ID, EMAIL, PASSWORD, MAXCREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Student(String, String, String, String, String, Int) constructor with
	 * an empty last name.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testStudentStringStringStringStringStringIntEmptyLastName() {
		User s = null; // Initialize a student reference to null
		try {
			s = new Student(FIRST, "", ID, EMAIL, PASSWORD, MAXCREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Student(String, String, String, String, String, Int) constructor with
	 * an empty id.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testStudentStringStringStringStringStringIntEmptyId() {
		User s = null; // Initialize a student reference to null
		try {
			s = new Student(FIRST, LAST, "", EMAIL, PASSWORD, MAXCREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Student(String, String, String, String, String, Int) constructor with
	 * an empty email.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testStudentStringStringStringStringStringIntEmptyEmail() {
		User s = null; // Initialize a student reference to null
		try {
			s = new Student(FIRST, LAST, ID, "", PASSWORD, MAXCREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Student(String, String, String, String, String, Int) constructor with
	 * an empty password.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testStudentStringStringStringStringStringIntEmptyPassword() {
		User s = null; // Initialize a student reference to null
		try {
			s = new Student(FIRST, LAST, ID, EMAIL, "", MAXCREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Student(String, String, String, String, String) constructor.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testStudentStringStringStringStringString() {

		User s = null; // Initialize a student reference to null

		// Tests valid student constructor
		try {
			s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD);
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests Student(String, String, String, String, String) constructor with a null
	 * first name.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testNullStudent2First() {
		User s = null;
		// Testing invalid first name
		try {
			s = new Student(null, LAST, ID, EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Student(String, String, String, String, String) constructor with a null
	 * last name.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testNullStudent2Last() {
		User s = null;
		// Testing invalid last name
		try {
			s = new Student(FIRST, null, ID, EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Student(String, String, String, String, String) constructor with a null
	 * id.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testNullStudent2Id() {
		User s = null;
		// Testing invalid id
		try {
			s = new Student(FIRST, LAST, null, EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Student(String, String, String, String, String) constructor with a null
	 * email.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testNullStudent2Email() {
		User s = null;
		// Testing invalid email
		try {
			s = new Student(FIRST, LAST, ID, null, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Student(String, String, String, String, String) constructor with a null
	 * password.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testNullStudent2Password() {
		User s = null;
		// Testing invalid password
		try {
			s = new Student(FIRST, LAST, ID, EMAIL, null);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Student(String, String, String, String, String) constructor with an
	 * empty first name.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testEmptyStudent2First() {
		User s = null;
		// Testing invalid first name
		try {
			s = new Student("", LAST, ID, EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Student(String, String, String, String, String) constructor with an
	 * empty last name.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testEmptyStudent2Last() {
		User s = null;
		// Testing invalid last name
		try {
			s = new Student(FIRST, "", ID, EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Student(String, String, String, String, String) constructor with an
	 * empty id.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testEmptyStudent2Id() {
		User s = null;
		// Testing invalid id
		try {
			s = new Student(FIRST, LAST, "", EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Student(String, String, String, String, String) constructor with an
	 * empty email.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testEmptyStudent2Email() {
		User s = null;
		// Testing invalid email
		try {
			s = new Student(FIRST, LAST, ID, "", PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests Student(String, String, String, String, String) constructor with an
	 * empty password.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testEmptyStudent2Password() {
		User s = null;
		// Testing invalid password
		try {
			s = new Student(FIRST, LAST, ID, EMAIL, "");
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
		// Construct a valid Student
		User s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD);
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
		// Construct a valid Student
		User s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD);
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
		// Construct a valid Student
		User s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD);
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
		// Construct a valid Student
		User s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD);
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
		// Construct a valid Student
		User s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD);
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
		// Construct a valid Student
		User s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD);
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
		// Construct a valid Student
		User s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD);
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
		// Construct a valid Student
		User s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD);
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
		// Construct a valid Student
		User s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD);
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
		// Construct a valid Student
		User s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD);
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
		// Construct a valid Student
		User s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD);
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
		// Construct a valid Student
		User s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD);
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
		// Construct a valid Student
		User s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD);
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
		// Construct a valid Student
		User s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD);
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
		// Construct a valid Student
		User s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD);
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
	 * Tests setMaxCredits().
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testSetMaxCredits() {
		// Construct a valid Student
		Student s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCREDITS);
		try {
			s.setMaxCredits(REGCREDITS);
			assertEquals(REGCREDITS, s.getMaxCredits());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests setMaxCredits() with invalid too low max credits.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testSetInvalidLowMaxCredits() {
		// Construct a valid Student
		Student s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCREDITS);
		try {
			s.setMaxCredits(2);
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(MAXCREDITS, s.getMaxCredits());
		}
	}

	/**
	 * Tests setMaxCredits() with invalid too high max credits.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testSetInvalidHighMaxCredits() {
		// Construct a valid Student
		Student s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCREDITS);
		try {
			s.setMaxCredits(INVALIDCREDITSHIGH);
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(MAXCREDITS, s.getMaxCredits());
		}
	}

	/**
	 * Tests equals().
	 */
	@Test
	public void testEqualsObject() {
		User s1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		User s2 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		User s3 = new Student("firstName", "lastName", "unityid", "studentEmail@ncsu.edu", "password");
		User s4 = new Student("Mark", "last", "id", "email@ncsu.edu", "hashedpassword");
		User s5 = new Student("first", "Smith", "id", "email@ncsu.edu", "hashedpassword");
		User s6 = new Student("first", "last", "msmith", "email@ncsu.edu", "hashedpassword");
		User s7 = new Student("first", "last", "id", "msmith@ncsu.edu", "hashedpassword");
		User s8 = new Student("first", "last", "id", "email@ncsu.edu", "Msmith2019!");

		assertTrue(s1.equals(s2));

		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));
	}

	/**
	 * Tests toString().
	 */
	@Test
	public void testToString() {
		User s1 = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCREDITS);
		String string1 = "Mark,Smith,msmith,msmith@ncsu.edu,Msmith2019!,18";
		assertEquals(string1, s1.toString());
	}

	/**
	 * Tests compareTo().
	 */
	@Test
	public void testCompareTo() {
		Student s1 = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCREDITS);
		Student s2 = new Student("Leon", "John", "ljohn", "ljohn@ncsu.edu", "Ljohn2019!", MAXCREDITS);
		Student s3 = new Student("Nemo", "Fish", "nfish", "nfish@ncsu.edu", "Nfish2019!", MAXCREDITS);
		Student s4 = new Student("James", "Fish", "jfish", "jfish@ncsu.edu", "jfish2019!", MAXCREDITS);

		assertEquals(0, s1.compareTo(s1));
		assertEquals(1, s1.compareTo(s2));
		assertEquals(-1, s2.compareTo(s1));
		assertEquals(1, s1.compareTo(s3));
		assertEquals(-1, s3.compareTo(s1));
		assertEquals(1, s2.compareTo(s3));
		assertEquals(-1, s3.compareTo(s2));
		assertEquals(1, s3.compareTo(s4));
	}

	/**
	 * Tests the canAdd method.
	 */
	public void testCanAdd() {
		Student s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCREDITS);
		s.getSchedule().addCourseToSchedule(
				new Course("CSC216", "Intro to Computer Programming 2", "001", 3, "jtwald", 10, "MW", 1000, 1130));
		s.getSchedule()
				.addCourseToSchedule(new Course("CSC226", "Discrete Math", "001", 3, "jtwald", 10, "TH", 930, 1100));
		s.getSchedule().addCourseToSchedule(new Course("CSC230", "C Tools", "001", 3, "jtwald", 10, "MW", 1200, 1330));
		s.getSchedule().addCourseToSchedule(new Course("CSC231", "C Tools", "001", 3, "jtwald", 10, "MW", 1400, 1500));
		s.getSchedule().addCourseToSchedule(new Course("CSC232", "C Tools", "001", 3, "jtwald", 10, "MW", 1530, 1630));
		assertTrue(s.canAdd(new Course("CSC233", "C Tools", "001", 3, "jtwald", 10, "MW", 1700, 1800)));
		s.getSchedule().addCourseToSchedule(new Course("CSC234", "C Tools", "001", 3, "jtwald", 10, "MW", 1700, 1800));
		assertFalse(s.canAdd(new Course("CSC234", "C Tools", "001", 3, "jtwald", 10, "MW", 1830, 1930)));
		assertFalse(s.canAdd(null));
		assertFalse(s.canAdd(new Course("CSC233", "C Tools", "001", 3, "jtwald", 10, "MW", 1700, 1800)));
	}
}