package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests CourseRoll.
 * 
 * @author Justin Wald
 * @author Selena Chen
 */
public class CourseRollTest {

	/**
	 * Tests the CourseRoll constructor.
	 */
	@Test
	public void testCourseRoll() {
		CourseRoll courseRoll = new CourseRoll(15);
		assertEquals(15, courseRoll.getEnrollmentCap());
		assertEquals(15, courseRoll.getOpenSeats());

		try {
			courseRoll = new CourseRoll(-1);
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(15, courseRoll.getEnrollmentCap());
			assertEquals(15, courseRoll.getOpenSeats());
		}

		try {
			courseRoll = new CourseRoll(9);
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(15, courseRoll.getEnrollmentCap());
			assertEquals(15, courseRoll.getOpenSeats());
		}

		try {
			courseRoll = new CourseRoll(251);
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(15, courseRoll.getEnrollmentCap());
			assertEquals(15, courseRoll.getOpenSeats());
		}
	}

	/**
	 * Tests getEnrollmentCap().
	 */
	@Test
	public void testGetEnrollmentCap() {
		CourseRoll courseRoll = new CourseRoll(15);
		assertEquals(15, courseRoll.getEnrollmentCap());

		courseRoll.setEnrollmentCap(10);
		assertEquals(10, courseRoll.getEnrollmentCap());

		courseRoll.setEnrollmentCap(250);
		assertEquals(250, courseRoll.getEnrollmentCap());

		try {
			courseRoll.setEnrollmentCap(-1);
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(250, courseRoll.getEnrollmentCap());
		}
	}

	/**
	 * Tests setEnrollmentCap().
	 */
	@Test
	public void testSetEnrollmentCap() {
		CourseRoll courseRoll = new CourseRoll(10);
		assertEquals(10, courseRoll.getEnrollmentCap());

		courseRoll.setEnrollmentCap(250);
		assertEquals(250, courseRoll.getEnrollmentCap());

		try {
			courseRoll.setEnrollmentCap(-1);
		} catch (IllegalArgumentException iae) {
			assertEquals(250, courseRoll.getEnrollmentCap());
		}

		try {
			courseRoll.setEnrollmentCap(9);
		} catch (IllegalArgumentException iae) {
			assertEquals(250, courseRoll.getEnrollmentCap());
		}

		try {
			courseRoll.setEnrollmentCap(251);
		} catch (IllegalArgumentException iae) {
			assertEquals(250, courseRoll.getEnrollmentCap());
		}

		courseRoll.setEnrollmentCap(15);
		assertEquals(15, courseRoll.getEnrollmentCap());

		Student student = new Student("first1", "last1", "id1", "email1@ncsu.edu", "pw");
		courseRoll.enroll(student);
		student = new Student("first2", "last2", "id2", "email2@ncsu.edu", "pw");
		courseRoll.enroll(student);
		student = new Student("first3", "last3", "id3", "email3@ncsu.edu", "pw");
		courseRoll.enroll(student);
		student = new Student("first4", "last4", "id4", "email4@ncsu.edu", "pw");
		courseRoll.enroll(student);
		student = new Student("first5", "last5", "id5", "email5@ncsu.edu", "pw");
		courseRoll.enroll(student);
		student = new Student("first6", "last6", "id6", "email6@ncsu.edu", "pw");
		courseRoll.enroll(student);
		student = new Student("first7", "last7", "id7", "email7@ncsu.edu", "pw");
		courseRoll.enroll(student);
		student = new Student("first8", "last8", "id8", "email8@ncsu.edu", "pw");
		courseRoll.enroll(student);
		student = new Student("first9", "last9", "id9", "email9@ncsu.edu", "pw");
		courseRoll.enroll(student);
		student = new Student("first10", "last10", "id10", "email10@ncsu.edu", "pw");
		courseRoll.enroll(student);
		student = new Student("first11", "last11", "id11", "email11@ncsu.edu", "pw");
		courseRoll.enroll(student);
		assertEquals(4, courseRoll.getOpenSeats());

		courseRoll.setEnrollmentCap(11);
		assertEquals(11, courseRoll.getEnrollmentCap());

		try {
			courseRoll.setEnrollmentCap(10);
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(11, courseRoll.getEnrollmentCap());
		}
	}

	/**
	 * Tests enroll().
	 */
	@Test
	public void testEnroll() {
		CourseRoll courseRoll = new CourseRoll(15);
		assertEquals(15, courseRoll.getOpenSeats());

		Student student = new Student("Justin", "Wald", "JTWald", "JTWald@ncsu.edu", "pw");
		courseRoll.enroll(student);
		assertEquals(14, courseRoll.getOpenSeats());

		try {
			courseRoll.enroll(student);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(14, courseRoll.getOpenSeats());
		}

		try {
			courseRoll.enroll(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(14, courseRoll.getOpenSeats());
		}

		student = new Student("Selena", "Chen", "schen53", "schen53@ncsu.edu", "pw");
		courseRoll.enroll(student);
		assertEquals(13, courseRoll.getOpenSeats());

		CourseRoll roll = new CourseRoll(10);
		Student s = new Student("first1", "last1", "id1", "email1@ncsu.edu", "pw");
		roll.enroll(s);
		s = new Student("first2", "last2", "id2", "email2@ncsu.edu", "pw");
		roll.enroll(s);
		s = new Student("first3", "last3", "id3", "email3@ncsu.edu", "pw");
		roll.enroll(s);
		s = new Student("first4", "last4", "id4", "email4@ncsu.edu", "pw");
		roll.enroll(s);
		s = new Student("first5", "last5", "id5", "email5@ncsu.edu", "pw");
		roll.enroll(s);
		s = new Student("first6", "last6", "id6", "email6@ncsu.edu", "pw");
		roll.enroll(s);
		s = new Student("first7", "last7", "id7", "email7@ncsu.edu", "pw");
		roll.enroll(s);
		s = new Student("first8", "last8", "id8", "email8@ncsu.edu", "pw");
		roll.enroll(s);
		s = new Student("first9", "last9", "id9", "email9@ncsu.edu", "pw");
		roll.enroll(s);
		s = new Student("first10", "last10", "id10", "email10@ncsu.edu", "pw");
		roll.enroll(s);
		assertEquals(0, roll.getOpenSeats());

		s = new Student("first11", "last11", "id11", "email11@ncsu.edu", "pw");
		try {
			roll.enroll(s);
		} catch (IllegalArgumentException e) {
			assertEquals(0, roll.getOpenSeats());
		}
	}

	/**
	 * Tests drop().
	 */
	@Test
	public void testDrop() {
		CourseRoll courseRoll = new CourseRoll(15);
		assertEquals(15, courseRoll.getOpenSeats());

		Student student = new Student("Justin", "Wald", "JTWald", "JTWald@ncsu.edu", "pw");
		courseRoll.enroll(student);
		assertEquals(14, courseRoll.getOpenSeats());
		courseRoll.drop(student);
		assertEquals(15, courseRoll.getOpenSeats());

		try {
			courseRoll.drop(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(15, courseRoll.getOpenSeats());
		}

		courseRoll.drop(student);
		assertEquals(15, courseRoll.getOpenSeats());
	}

	/**
	 * Tests getOpenSeats().
	 */
	@Test
	public void testGetOpenSeats() {
		CourseRoll courseRoll = new CourseRoll(15);
		assertEquals(15, courseRoll.getOpenSeats());

		Student student = new Student("Justin", "Wald", "JTWald", "JTWald@ncsu.edu", "pw");
		courseRoll.enroll(student);
		assertEquals(14, courseRoll.getOpenSeats());
	}

	/**
	 * Tests canEnroll().
	 */
	@Test
	public void testCanEnroll() {
		CourseRoll courseRoll = new CourseRoll(15);
		assertEquals(15, courseRoll.getOpenSeats());

		Student student = new Student("Justin", "Wald", "JTWald", "JTWald@ncsu.edu", "pw");
		assertTrue(courseRoll.canEnroll(student));
		courseRoll.enroll(student);
		assertEquals(14, courseRoll.getOpenSeats());
		assertFalse(courseRoll.canEnroll(student));

		CourseRoll roll = new CourseRoll(10);
		Student s = new Student("first1", "last1", "id1", "email1@ncsu.edu", "pw");
		roll.enroll(s);
		s = new Student("first2", "last2", "id2", "email2@ncsu.edu", "pw");
		roll.enroll(s);
		s = new Student("first3", "last3", "id3", "email3@ncsu.edu", "pw");
		roll.enroll(s);
		s = new Student("first4", "last4", "id4", "email4@ncsu.edu", "pw");
		roll.enroll(s);
		s = new Student("first5", "last5", "id5", "email5@ncsu.edu", "pw");
		roll.enroll(s);
		s = new Student("first6", "last6", "id6", "email6@ncsu.edu", "pw");
		roll.enroll(s);
		s = new Student("first7", "last7", "id7", "email7@ncsu.edu", "pw");
		roll.enroll(s);
		s = new Student("first8", "last8", "id8", "email8@ncsu.edu", "pw");
		roll.enroll(s);
		s = new Student("first9", "last9", "id9", "email9@ncsu.edu", "pw");
		roll.enroll(s);
		s = new Student("first10", "last10", "id10", "email10@ncsu.edu", "pw");
		roll.enroll(s);
		assertEquals(0, roll.getOpenSeats());

		s = new Student("first11", "last11", "id11", "email11@ncsu.edu", "pw");
		assertFalse(roll.canEnroll(s));
	}
}