package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests Schedule.
 * 
 * @author Justin Wald
 * @author Selena Chen
 */
public class ScheduleTest {

	/** Schedule to be used for testing */
	Schedule schedule;

	/** Courses to be used in testing */
	Course course1;
	Course course2;
	Course course3;
	Course course4;
	Course course5;

	/**
	 * Sets up the test environment.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() {
		schedule = new Schedule();
		course1 = new Course("CSC116", "Intro to Computer Programming", "001", 3, "jtwald", 10, "MW", 930, 1100);
		course2 = new Course("CSC216", "Intro to Computer Programming 2", "001", 3, "jtwald", 10,  "MW", 1000, 1130);
		course3 = new Course("CSC226", "Discrete Math", "001", 3, "jtwald", 10,  "TH", 930, 1100);
		course4 = new Course("CSC230", "C Tools", "001", 3, "jtwald", 10,  "MW", 1200, 1330);
		course5 = new Course("CSC116", "Intro to Computer Programming", "002", 3, "jtwald", 10,  "MW", 1230, 1400);
	}

	/**
	 * Tests the Schedule() constructor.
	 */
	@Test
	public void testSchedule() {
		schedule = new Schedule();
		assertTrue(schedule != null);
		assertEquals(0, schedule.getScheduledCourses().length);
	}

	/**
	 * Tests addCourseToSchedule().
	 */
	@Test
	public void testAddCourseToSchedule() {
		schedule = new Schedule();
		try {
			schedule.addCourseToSchedule(course1);
		} catch (IllegalArgumentException e) {
			fail("Unable to add to schedule.");
		}
		assertEquals(1, schedule.getScheduledCourses().length);

		try {
			schedule.addCourseToSchedule(course2);
			fail("Conflicting Course allowed to be added.");
		} catch (IllegalArgumentException e) {
			assertEquals(1, schedule.getScheduledCourses().length);
			assertEquals("The course cannot be added due to a conflict.", e.getMessage());
		}
		try {
			schedule.addCourseToSchedule(course5);
			fail("Already enrolled in course of same name");
		} catch (IllegalArgumentException e) {
			assertEquals(1, schedule.getScheduledCourses().length);
			assertEquals("You are already enrolled in " + course5.getName(), e.getMessage());
		}
		schedule.addCourseToSchedule(course3);
		schedule.addCourseToSchedule(course4);
		assertEquals(3, schedule.getScheduledCourses().length);
	}

	/**
	 * Tests removeCourseFromSchedule().
	 */
	@Test
	public void testRemoveCourseFromSchedule() {
		schedule.addCourseToSchedule(course1);
		schedule.addCourseToSchedule(course3);
		schedule.addCourseToSchedule(course4);
		assertFalse(schedule.removeCourseFromSchedule(course5));
		assertTrue(schedule.removeCourseFromSchedule(course3));
		assertEquals(2, schedule.getScheduledCourses().length);
		assertEquals(course1.getName(), schedule.getScheduledCourses()[0][0]);
		assertEquals(course4.getName(), schedule.getScheduledCourses()[1][0]);
		assertTrue(schedule.removeCourseFromSchedule(course4));
		assertEquals(1, schedule.getScheduledCourses().length);
		assertEquals(course1.getName(), schedule.getScheduledCourses()[0][0]);
	}

	/**
	 * Tests resetSchedule().
	 */
	@Test
	public void testResetSchedule() {
		schedule.addCourseToSchedule(course1);
		schedule.addCourseToSchedule(course3);
		assertEquals(2, schedule.getScheduledCourses().length);
		schedule.resetSchedule();
		assertEquals(0, schedule.getScheduledCourses().length);
	}

	/**
	 * Tests getScheduledCourses().
	 */
	@Test
	public void testGetScheduledCourses() {
		schedule.addCourseToSchedule(course1);
		assertEquals("CSC116", schedule.getScheduledCourses()[0][0]);
		assertEquals("001", schedule.getScheduledCourses()[0][1]);
		assertEquals("Intro to Computer Programming", schedule.getScheduledCourses()[0][2]);
		assertEquals("MW 9:30AM-11:00AM", schedule.getScheduledCourses()[0][3]);
		assertEquals(Integer.toString(10), schedule.getScheduledCourses()[0][4]);
	}

	/**
	 * Tests setTitle().
	 */
	@Test
	public void testSetTitle() {
		assertEquals("My Schedule", schedule.getTitle());
		schedule.setTitle("Cool Schedule");
		assertEquals("Cool Schedule", schedule.getTitle());
		try {
			schedule.setTitle(null);
			fail("Should throw Exception.");
		} catch (IllegalArgumentException e) {
			assertEquals("Cool Schedule", schedule.getTitle());
		}
	}

	/**
	 * Tests getTitle().
	 */
	@Test
	public void testGetTitle() {
		assertEquals("My Schedule", schedule.getTitle());
		schedule.setTitle("Cool Schedule");
		assertEquals("Cool Schedule", schedule.getTitle());
	}
	
	/**
	 * Tests getScheduleCredits().
	 */
	@Test
	public void testGetScheduleCredits() {
		schedule.addCourseToSchedule(course2);
		schedule.addCourseToSchedule(course3);
		schedule.addCourseToSchedule(course4);
		assertEquals(9, schedule.getScheduleCredits());
	}
	
	/**
	 * Tests canAdd().
	 */
	@Test
	public void testCanAdd() {
		schedule.addCourseToSchedule(course1);
		assertFalse(schedule.canAdd(course1));
		assertFalse(schedule.canAdd(null));
		assertTrue(schedule.canAdd(course3));
	}
}