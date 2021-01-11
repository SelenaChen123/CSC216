package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Activity;
import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the Activity class.
 * 
 * @author Selena Chen
 * @author Travis Walter
 * @author Tyler Mathis
 */
public class ActivityTest {

	/**
	 * Tests checkConflict().
	 */
	@Test
	public void testCheckConflict() {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "MW", 1330, 1445);
		Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "TH", 1330, 1445);
		try {
			a1.checkConflict(a2);
			a2.checkConflict(a1);
			assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM",
					a2.getMeetingString());
		} catch (ConflictException e) {
			fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
		}
		a1.setMeetingDays("TH");
		a1.setActivityTime(1445, 1530);
		try {
			a1.checkConflict(a2);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
			assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}
		a1.setMeetingDays("T");
		a1.setActivityTime(1230, 1330);
		try {
			a1.checkConflict(a2);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("T 12:30PM-1:30PM", a1.getMeetingString());
			assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}
		a1.setActivityTime(1400, 1430);
		try {
			a1.checkConflict(a2);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("T 2:00PM-2:30PM", a1.getMeetingString());
			assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}
	}
}