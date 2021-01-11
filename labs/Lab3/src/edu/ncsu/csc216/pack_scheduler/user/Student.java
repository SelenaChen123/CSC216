package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * The Student class represents an individual student record. The Student class
 * is a plain old java object (POJO) consisting mostly of getters and setters
 * for first name, last time, id, email, password, and max credit hours. The
 * setters do have some complexity. Also has methods to provide information to
 * the GUI and to compare two students.
 * 
 * @author Sarah Heckman
 * @author Selena Chen
 * @author Travis Walter
 * @author Tyler Mathis
 * @author Justin Wald
 */
public class Student extends User implements Comparable<Student> {

	/** Maximum possible credits any student can have */
	public static final int MAX_CREDITS = 18;

	/** Student's max credit hours */
	private int maxCredits;

	/** Schedule holding student's courses */
	private Schedule schedule;

	/**
	 * Constructs Student using all fields.
	 *
	 * @param firstName  Student's first name
	 * @param lastName   Student's last name
	 * @param id         Student's unity id
	 * @param email      Student's email
	 * @param password   Student's password
	 * @param maxCredits Student's max credit hours
	 */
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
		super(firstName, lastName, id, email, password);
		schedule = new Schedule();
		setMaxCredits(maxCredits);
	}

	/**
	 * Constructs Student using all but the maxCredits field, with default max
	 * credits value of 18.
	 *
	 * @param firstName Student's first name
	 * @param lastName  Student's last name
	 * @param id        Student's unity id
	 * @param email     Student's email
	 * @param password  Student's password
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName, lastName, id, email, password, MAX_CREDITS);
	}

	/**
	 * Returns the Student's max credit hours.
	 * 
	 * @return Student's max credit hours
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the Student's max credit hours.
	 * 
	 * @param maxCredits Student's max credit hours
	 * @throws IllegalArgumentException if the parameter is less than 3 or greater
	 *                                  than 18
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 3 || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}

	/**
	 * Returns a String containing the fields as a comma separated line.
	 * 
	 * @return String containing the fields as a comma separated line
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + ","
				+ getMaxCredits();
	}

	/**
	 * Compares two students and determines where they go alphabetically.
	 * 
	 * @param s Student being compared
	 * @return 1 if Student one comes after Student two, -1 if Student one comes
	 *         before Student two, and 0 if Student one and two are the same
	 */
	@Override
	public int compareTo(Student s) {
		if (getLastName().compareTo(s.getLastName()) > 0) {
			return 1;
		} else if (getLastName().compareTo(s.getLastName()) < 0) {
			return -1;
		} else {
			if (getFirstName().compareTo(s.getFirstName()) > 0) {
				return 1;
			} else if (getFirstName().compareTo(s.getFirstName()) < 0) {
				return -1;
			} else {
				if (getId().compareTo(s.getId()) > 0) {
					return 1;
				} else if (getId().compareTo(s.getId()) < 0) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	/**
	 * Generates a hashCode for Student using all fields.
	 * 
	 * @return hashCode for Student
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	/**
	 * Compares a given object to this object for equality on all fields.
	 * 
	 * @param obj the Object to compare
	 * @return true if all objects are the same on all fields, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (!super.equals(obj))
			return false;
		Student other = (Student) obj;
		if (maxCredits != other.maxCredits)
			return false;
		if (!super.getPassword().equals(other.getPassword()))
			return false;
		return true;
	}

	/**
	 * Provides the student's current schedule for modification.
	 * 
	 * @return Schedule object holding the student's courses
	 */
	public Schedule getSchedule() {
		return schedule;
	}

	/**
	 * Checks whether a Student can add a course to their Schedule based on Schedule
	 * availability and credit hours.
	 * 
	 * @param course Course to check eligibility.
	 * @return true if a student can add the Course to their schedule, false
	 *         otherwise
	 */
	public boolean canAdd(Course course) {
		if (!schedule.canAdd(course)) {
			return false;
		} else if (schedule.getScheduleCredits() + course.getCredits() > maxCredits) {
			return false;
		}
		return true;
	}
}