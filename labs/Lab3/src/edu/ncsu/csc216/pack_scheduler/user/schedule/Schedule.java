package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Holds a custom ArrayList representing a student's schedule. This ArrayList
 * holds Course objects and has a title associated with it. Has methods to add
 * courses to the schedule, remove courses from the schedule, reset the
 * schedule, get the scheduled courses, set the title, and get the title.
 * 
 * @author Justin Wald
 * @author Selena Chen
 */
public class Schedule {

	/** Custom ArrayList holding the Courses that make up a student's schedule */
	private ArrayList<Course> schedule;
	/** Title of the schedule */
	private String title;

	/**
	 * Constructs a Schedule object.
	 */
	public Schedule() {
		title = "My Schedule";
		resetSchedule();
	}

	/**
	 * Adds a course to the Schedule.
	 * 
	 * @param course Course to add to the student's schedule
	 * @return true if the Course was successfully added, false if Course was null,
	 *         a duplicate, or conflicts with an existing course
	 * @throws IllegalArgumentException if Course is null or already exists within
	 *                                  the schedule, or if there is a conflict with
	 *                                  an existing course
	 */
	public boolean addCourseToSchedule(Course course) throws IllegalArgumentException {
		for (int i = 0; i < schedule.size(); i++) {
			Course tempCourse = schedule.get(i);
			try {
				tempCourse.checkConflict(course);
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
			if (tempCourse.isDuplicate(course)) {
				throw new IllegalArgumentException("You are already enrolled in " + course.getName());
			}
		}
		try {
			schedule.add(course);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("You are already enrolled in " + course.getName());
		}
		return true;
	}

	/**
	 * Removes the passed Course from the student's schedule if able to. Returns
	 * false if a Course matching the passed Course wasn't found in the student's
	 * schedule or the passed Course was null.
	 * 
	 * @param course Course you want to remove from the student's Schedule
	 * @return true if Course was removed, otherwise false
	 */
	public boolean removeCourseFromSchedule(Course course) {
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).equals(course)) {
				schedule.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Resets the schedule to an empty schedule.
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Course>();
	}

	/**
	 * Gets the current schedule as a 2d array of Strings. Generally used for output
	 * to GUI. Each element of the first index represents a different Course, the
	 * second index contains Course's name at [0], section at [1], title at [2], and
	 * meetingString at [3].
	 * 
	 * @return 2D representation of the current schedule
	 */
	public String[][] getScheduledCourses() {
		String[][] rtn = new String[schedule.size()][4];
		for (int i = 0; i < schedule.size(); i++) {
			Course temp = schedule.get(i);
			rtn[i] = temp.getShortDisplayArray();
		}
		return rtn;
	}

	/**
	 * Sets the title of the schedule.
	 * 
	 * @param title String to set schedule's title to
	 * @throws IllegalArgumentException if title is null or empty
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}

	/**
	 * Gets the title of the schedule.
	 * 
	 * @return title of the schedule
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the total number of credits in a schedule.
	 * 
	 * @return number of credits in the schedule
	 */
	public int getScheduleCredits() {
		int sum = 0;
		for (int i = 0; i < schedule.size(); i++) {
			sum += schedule.get(i).getCredits();
		}
		return sum;
	}

	/**
	 * Determines if a Course can be added to schedule.
	 * 
	 * @param course Course to determine eligibility.
	 * @return true if a course can be added to schedule, false otherwise
	 */
	public boolean canAdd(Course course) {
		if (course == null) {
			return false;
		}
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).isDuplicate(course)) {
				return false;
			}
			try {
				schedule.get(i).checkConflict(course);
			} catch (ConflictException e) {
				return false;
			}
		}
		return true;
	}
}