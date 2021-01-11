package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * The Faculty class represents an individual faculty record. The Faculty class
 * is a plain old java object (POJO) consisting mostly of getters and setters
 * for first name, last time, id, email, password, and maxCourses. The setters
 * do have some complexity. Also has methods to provide information to the GUI
 * and to compare two faculty.
 * 
 * @author Selena Chen
 * @author Atharva Mahajan
 * @author Sarah Morton
 */
public class Faculty extends User {

	/** Maximum possible courses any faculty can teach */
	public static final int MAX_COURSES = 3;

	/** Minimum possible courses any faculty can teach */
	public static final int MIN_COURSES = 1;

	/** Faculty's max courses */
	private int maxCourses;

	/** Faculty's schedule */
	private FacultySchedule schedule;

	/**
	 * Constructs Faculty using all fields.
	 * 
	 * @param firstName  Faculty's first name
	 * @param lastName   Faculty's last name
	 * @param id         Faculty's unity id
	 * @param email      Faculty's email
	 * @param password   Faculty's password
	 * @param maxCourses Faculty's max courses
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password, int maxCourses) {
		super(firstName, lastName, id, email, password);
		setMaxCourses(maxCourses);
		schedule = new FacultySchedule(id);
	}

	/**
	 * Sets the Faculty's max courses.
	 * 
	 * @param maxCourses Faculty's max courses
	 * @throws IllegalArgumentException if the parameter is less than 1 or greater
	 *                                  than 3
	 */
	public void setMaxCourses(int maxCourses) {
		if (maxCourses < MIN_COURSES || maxCourses > MAX_COURSES) {
			throw new IllegalArgumentException("Invalid max courses");
		}
		this.maxCourses = maxCourses;
	}

	/**
	 * Returns the Faculty's max courses.
	 * 
	 * @return Faculty's max courses
	 */
	public int getMaxCourses() {
		return maxCourses;
	}

	/**
	 * Returns the Faculty's schedule.
	 * 
	 * @return Faculty's schedule
	 */
	public FacultySchedule getSchedule() {
		return schedule;
	}

	/**
	 * Returns true if the number of scheduled courses is greater than the Faculty's
	 * max courses.
	 * 
	 * @return True if the number of scheduled courses is greater than the Faculty's
	 *         max courses, false otherwise
	 */
	public boolean isOverloaded() {
		return schedule.getNumScheduledCourses() > maxCourses;
	}

	/**
	 * Generates a hashCode for Faculty using all fields.
	 * 
	 * @return hashCode for Faculty
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
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
		Faculty other = (Faculty) obj;
		if (maxCourses != other.maxCourses)
			return false;
		if (!super.getPassword().equals(other.getPassword()))
			return false;
		return true;
	}

	/**
	 * Returns a String containing the fields as a comma separated line.
	 * 
	 * @return String containing the fields as a comma separated line
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + ","
				+ getMaxCourses();
	}
}