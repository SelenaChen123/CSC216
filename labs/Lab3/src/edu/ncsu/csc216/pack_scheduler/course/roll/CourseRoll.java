package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * CourseRoll keeps track of the students taking a class. Roll keeps track of
 * the students with a custom linked list class. Has methods to get/set the
 * enrollment cap, enroll a Student, drop a Student, get the number of open
 * seats left, check whether a Student can be enrolled or not, and get the
 * number of students on the waitlist.
 * 
 * @author Justin Wald
 * @author Selena Chen
 * @author Atharva Matharva
 * @author Sarah Morton
 */
public class CourseRoll {

	/** Enrollment cap for class */
	private int enrollmentCap;

	/** Minimum number of students in a class */
	private static final int MIN_ENROLLMENT = 10;

	/** Maximum number of students in a class */
	private static final int MAX_ENROLLMENT = 250;

	/** Custom LinkedAbstractList to hold students in roll */
	private LinkedAbstractList<Student> roll;

	/** LinkedQueue to hold students on the waitlist */
	private LinkedQueue<Student> waitlist;

	/** Specific Course associated with the CourseRoll */
	private Course course;

	/**
	 * Constructs a CourseRoll object for the given Course with a passed capacity.
	 * 
	 * @param capacity capacity of the Course
	 * @param course   specific Course associated with the CourseRoll
	 * @throws IllegalArgumentException if course is null
	 */
	public CourseRoll(Course course, int capacity) {
		if (course == null) {
			throw new IllegalArgumentException();
		}
		roll = new LinkedAbstractList<Student>(capacity);
		setEnrollmentCap(capacity);
		waitlist = new LinkedQueue<Student>(10);
		this.course = course;
	}

	/**
	 * Returns the enrollment cap (capacity) of the CourseRoll.
	 * 
	 * @return enrollment cap of the CourseRoll
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}

	/**
	 * Sets the enrollment cap of a Course.
	 * 
	 * @param capacity capacity to set enrollment cap to
	 * @throws IllegalArgumentException if capacity is not within bounds or if
	 *                                  enrollmentCap is attempted to be changed to
	 *                                  below current roll size
	 */
	public void setEnrollmentCap(int capacity) {
		if (capacity > MAX_ENROLLMENT || capacity < MIN_ENROLLMENT) {
			throw new IllegalArgumentException("Capacity not within allowed bounds.");
		}
		if (roll.size() > capacity) {
			throw new IllegalArgumentException("Cannot change cap to lower than roll's current length.");
		}
		roll.setCapacity(capacity);
		enrollmentCap = capacity;
	}

	/**
	 * Enrolls the passed student in the CourseRoll.
	 * 
	 * @param student Student to add to the CourseRoll
	 * @throws IllegalArgumentException if Student is null, roll and waitlist are
	 *                                  both full, Student is already enrolled, or
	 *                                  an error was encountered when adding
	 */
	public void enroll(Student student) {
		if (student == null) {
			throw new IllegalArgumentException("Student cannot be null.");
		}
		for (int i = 0; i < roll.size(); i++) {
			if (roll.get(i).equals(student)) {
				throw new IllegalArgumentException("Student is already enrolled.");
			}
		}
		if (canEnroll(student)) {
			try {
				roll.add(roll.size(), student);
				student.getSchedule().addCourseToSchedule(course);
			} catch (Exception e) {
				if (roll.size() == enrollmentCap) {
					try {
						waitlist.enqueue(student);
					} catch (IllegalArgumentException iae) {
						throw new IllegalArgumentException();
					}
				}
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Drops the passed Student from the CourseRoll or waitlist.
	 * 
	 * @param student Student to drop from the CourseRoll or waitlist
	 * @throws IllegalArgumentException if Student to drop is null or removing
	 *                                  Student generated an exception from
	 *                                  LinkedAbstractList
	 */
	public void drop(Student student) {
		if (student == null) {
			throw new IllegalArgumentException("Student cannot be null.");
		}
		try {
			for (int i = 0; i < roll.size(); i++) {
				if (roll.get(i).equals(student)) {
					roll.remove(i);
					if (waitlist.size() > 0) {
						Student dqStudent = waitlist.dequeue();
						roll.add(roll.size(), dqStudent);
						dqStudent.getSchedule().addCourseToSchedule(course);
					}
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		try {
			for (int i = 0; i < waitlist.size(); i++) {
				Student s = waitlist.dequeue();
				if (!student.equals(s)) {
					waitlist.enqueue(s);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * Returns the number of open seats in the class.
	 * 
	 * @return number of open seats left in CourseRoll
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}

	/**
	 * Tests whether the passed student can enroll in the CourseRoll or be added to
	 * the waitlist.
	 * 
	 * @param student Student to be checked
	 * @return true if a student can enroll in the CourseRoll or be added to the
	 *         waitlist, false otherwise
	 */
	public boolean canEnroll(Student student) {
		if (getOpenSeats() == 0 && waitlist.size() == 10) {
			return false;
		}
		for (int i = 0; i < roll.size(); i++) {
			if (roll.get(i).equals(student) || waitlist.contains(student)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns the number of Students on the waitlist.
	 * 
	 * @return Number of Students on the waitlist
	 */
	public int getNumberOnWaitlist() {
		return waitlist.size();
	}
}