package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests CourseRoll.
 * 
 * @author Justin Wald
 * @author Selena Chen
 * @author Atharva Mahajan
 * @author Sarah Morton
 */
public class CourseRollTest {

	/**
	 * Tests the CourseRoll constructor.
	 */
	@Test
	public void testCourseRoll() {
		Course course = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "A");
		CourseRoll courseRoll = course.getCourseRoll();
		assertEquals(15, courseRoll.getEnrollmentCap());
		assertEquals(15, courseRoll.getOpenSeats());
		assertEquals(0, courseRoll.getNumberOnWaitlist());

		try {
			courseRoll = new CourseRoll(course, -1);
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(15, courseRoll.getEnrollmentCap());
			assertEquals(15, courseRoll.getOpenSeats());
			assertEquals(0, courseRoll.getNumberOnWaitlist());
		}

		try {
			courseRoll = new CourseRoll(course, 9);
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(15, courseRoll.getEnrollmentCap());
			assertEquals(15, courseRoll.getOpenSeats());
			assertEquals(0, courseRoll.getNumberOnWaitlist());
		}

		try {
			courseRoll = new CourseRoll(course, 251);
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(15, courseRoll.getEnrollmentCap());
			assertEquals(15, courseRoll.getOpenSeats());
			assertEquals(0, courseRoll.getNumberOnWaitlist());
		}
	}

	/**
	 * Tests getEnrollmentCap().
	 */
	@Test
	public void testGetEnrollmentCap() {
		Course course = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "A");
		CourseRoll courseRoll = course.getCourseRoll();
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
		Course course = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll courseRoll = course.getCourseRoll();
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
		Course course = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "A");
		CourseRoll courseRoll = course.getCourseRoll();
		assertEquals(15, courseRoll.getOpenSeats());

		Student student = new Student("Justin", "Wald", "JTWald", "JTWald@ncsu.edu", "pw");
		courseRoll.enroll(student);
		assertEquals(4, student.getSchedule().getScheduleCredits());
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

		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll roll = c.getCourseRoll();
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
		roll.enroll(s);
		assertEquals(0, roll.getOpenSeats());
		assertEquals(1, roll.getNumberOnWaitlist());
		s = new Student("first12", "last12", "id12", "email12@ncsu.edu", "pw");
		roll.enroll(s);
		assertEquals(0, roll.getOpenSeats());
		assertEquals(2, roll.getNumberOnWaitlist());
	}

	/**
	 * Tests drop().
	 */
	@Test
	public void testDrop() {
		Course course = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "A");
		CourseRoll courseRoll = course.getCourseRoll();
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
		
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll roll = c.getCourseRoll();
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
		Student sDrop1 = new Student("first9", "last9", "id9", "email9@ncsu.edu", "pw");
		roll.enroll(sDrop1);
		Student sDrop2 = new Student("first10", "last10", "id10", "email10@ncsu.edu", "pw");
		roll.enroll(sDrop2);
		assertEquals(0, roll.getOpenSeats());

		s = new Student("first11", "last11", "id11", "email11@ncsu.edu", "pw");
		roll.enroll(s);
		assertEquals(0, roll.getOpenSeats());
		assertEquals(1, roll.getNumberOnWaitlist());
		s = new Student("first12", "last12", "id12", "email12@ncsu.edu", "pw");
		roll.enroll(s);
		assertEquals(0, roll.getOpenSeats());
		assertEquals(2, roll.getNumberOnWaitlist());
		
		roll.drop(sDrop1);
		assertEquals(0, roll.getOpenSeats());
		assertEquals(1, roll.getNumberOnWaitlist());
		roll.drop(sDrop2);
		assertEquals(0, roll.getOpenSeats());
		assertEquals(0, roll.getNumberOnWaitlist());
	}

	/**
	 * Tests getOpenSeats().
	 */
	@Test
	public void testGetOpenSeats() {
		Course course = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "A");
		CourseRoll courseRoll = course.getCourseRoll();
		assertEquals(15, courseRoll.getOpenSeats());

		Student student = new Student("Justin", "Wald", "JTWald", "JTWald@ncsu.edu", "pw");
		courseRoll.enroll(student);
		assertEquals(14, courseRoll.getOpenSeats());
		
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll roll = c.getCourseRoll();
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
	}

	/**
	 * Tests canEnroll().
	 */
	@Test
	public void testCanEnroll() {
		Course course = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "A");
		CourseRoll courseRoll = course.getCourseRoll();
		assertEquals(15, courseRoll.getOpenSeats());

		Student student = new Student("Justin", "Wald", "JTWald", "JTWald@ncsu.edu", "pw");
		assertTrue(courseRoll.canEnroll(student));
		courseRoll.enroll(student);
		assertEquals(14, courseRoll.getOpenSeats());
		assertFalse(courseRoll.canEnroll(student));

		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll roll = c.getCourseRoll();
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
		assertTrue(roll.canEnroll(s));
		roll.enroll(s);
		assertEquals(0, roll.getOpenSeats());
		assertEquals(1, roll.getNumberOnWaitlist());
		s = new Student("first12", "last12", "id12", "email12@ncsu.edu", "pw");
		assertTrue(roll.canEnroll(s));
		roll.enroll(s);
		assertEquals(0, roll.getOpenSeats());
		assertEquals(2, roll.getNumberOnWaitlist());
		s = new Student("first13", "last13", "id13", "email13@ncsu.edu", "pw");
		assertTrue(roll.canEnroll(s));
		roll.enroll(s);
		assertEquals(0, roll.getOpenSeats());
		assertEquals(3, roll.getNumberOnWaitlist());
		s = new Student("first14", "last14", "id14", "email14@ncsu.edu", "pw");
		assertTrue(roll.canEnroll(s));
		roll.enroll(s);
		assertEquals(0, roll.getOpenSeats());
		assertEquals(4, roll.getNumberOnWaitlist());
		s = new Student("first15", "last15", "id15", "email15@ncsu.edu", "pw");
		assertTrue(roll.canEnroll(s));
		roll.enroll(s);
		assertEquals(0, roll.getOpenSeats());
		assertEquals(5, roll.getNumberOnWaitlist());
		s = new Student("first16", "last16", "id16", "email16@ncsu.edu", "pw");
		assertTrue(roll.canEnroll(s));
		roll.enroll(s);
		assertEquals(0, roll.getOpenSeats());
		assertEquals(6, roll.getNumberOnWaitlist());
		s = new Student("first17", "last17", "id17", "email17@ncsu.edu", "pw");
		assertTrue(roll.canEnroll(s));
		roll.enroll(s);
		assertEquals(0, roll.getOpenSeats());
		assertEquals(7, roll.getNumberOnWaitlist());
		s = new Student("first18", "last18", "id18", "email18@ncsu.edu", "pw");
		assertTrue(roll.canEnroll(s));
		roll.enroll(s);
		assertEquals(0, roll.getOpenSeats());
		assertEquals(8, roll.getNumberOnWaitlist());
		s = new Student("first19", "last19", "id19", "email19@ncsu.edu", "pw");
		assertTrue(roll.canEnroll(s));
		roll.enroll(s);
		assertEquals(0, roll.getOpenSeats());
		assertEquals(9, roll.getNumberOnWaitlist());
		s = new Student("first20", "last20", "id20", "email20@ncsu.edu", "pw");
		assertTrue(roll.canEnroll(s));
		roll.enroll(s);
		assertEquals(0, roll.getOpenSeats());
		assertEquals(10, roll.getNumberOnWaitlist());
		s = new Student("first20", "last20", "id20", "email20@ncsu.edu", "pw");
		assertFalse(roll.canEnroll(s));
		try {
			roll.enroll(s);
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(0, roll.getOpenSeats());
			assertEquals(10, roll.getNumberOnWaitlist());
		}
	}
	
	/**
	 * Tests getNumberOnWaitlist().
	 */
	public void testGetNumberOnWaitlist() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll roll = c.getCourseRoll();
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
		roll.enroll(s);
		assertEquals(0, roll.getOpenSeats());
		assertEquals(1, roll.getNumberOnWaitlist());
		s = new Student("first11", "last11", "id11", "email11@ncsu.edu", "pw");
		roll.enroll(s);
		assertEquals(0, roll.getOpenSeats());
		assertEquals(2, roll.getNumberOnWaitlist());
	}
}