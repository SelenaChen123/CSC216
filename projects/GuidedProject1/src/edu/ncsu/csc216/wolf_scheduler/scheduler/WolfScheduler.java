package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.*;
import java.util.*;

import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * Reads in and stores as a list all of the Course records stored in a file and
 * creates a schedule for the current user (a student) and provides
 * functionality for naming the schedule, adding a Course to the schedule,
 * removing a Course from the schedule, and resetting the schedule.
 * 
 * @author Selena Chen
 */
public class WolfScheduler {

	/** Course catalog */
	private ArrayList<Course> catalog;
	/** Schedule */
	private ArrayList<Course> schedule;
	/** Schedule title */
	private String title;

	/**
	 * Initializes schedule to an empty ArrayList and sets title to My Schedule.
	 * Initializes the catalog ArrayList and populates the Courses in the catalog
	 * from the information in the courseRecordsFileName. Throws an
	 * IllegalArgumentException if the file cannot be found.
	 * 
	 * @param courseRecordsFileName file where courses are read from
	 */
	public WolfScheduler(String courseRecordsFileName) {
		catalog = new ArrayList<Course>();
		schedule = new ArrayList<Course>();
		title = "My Schedule";
		try {
			catalog = CourseRecordIO.readCourseRecords(courseRecordsFileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * Uses the name and section to find a Course in the catalog. Returns null if
	 * the Course cannot be found.
	 * 
	 * @param name    Course's name
	 * @param section Course's section
	 * @return course being searched for
	 */
	public Course getCourseFromCatalog(String name, String section) {
		Course course = null;
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				course = catalog.get(i);
			}
		}
		return course;
	}

	/**
	 * Returns true if the Course represented by the name and section are in the
	 * catalog and can be added to the schedule and false if the Course is not in
	 * the catalog. If there is a Course with the same name already in the schedule
	 * an IllegalArgumentException is thrown.
	 * 
	 * @param name    Course's name
	 * @param section Course's section
	 * @return true if the Course represented by the name and section are in the
	 *         catalog and can be added to the schedule or false if the Course is
	 *         not in the catalog
	 */
	public boolean addCourse(String name, String section) {
		Course course = getCourseFromCatalog(name, section);
		if (course == null) {
			return false;
		}
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).getName().equals(name)) {
				throw new IllegalArgumentException("You are already enrolled in " + name);
			}
		}
		schedule.add(course);
		return true;
	}

	/**
	 * Returns true if the Course is removed from the schedule and false otherwise.
	 * 
	 * @param name    Course's name
	 * @param section Course's section
	 * @return true if the Course is removed from the schedule or false if otherwise
	 */
	public boolean removeCourse(String name, String section) {
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).getName().equals(name)) {
				schedule.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Resets the schedule to an empty ArrayList.
	 */
	public void resetSchedule() {
		schedule.clear();
	}

	/**
	 * Returns a String[][] (2D array) that contains a catalog item on each row. The
	 * first column is the Course name, the second is the section, and the third is
	 * the title.
	 * 
	 * @return 2D array of partial String representations of the courses in the
	 *         course catalog
	 */
	public String[][] getCourseCatalog() {
		String[][] courseCatalog = new String[catalog.size()][3];
		for (int row = 0; row < catalog.size(); row++) {
			for (int column = 0; column < 3; column++) {
				if (column == 0) {
					courseCatalog[row][column] = catalog.get(row).getName();
				} else if (column == 1) {
					courseCatalog[row][column] = catalog.get(row).getSection();
				} else {
					courseCatalog[row][column] = catalog.get(row).getTitle();
				}
			}
		}
		return courseCatalog;
	}

	/**
	 * Returns a String[][] (2D array) that contains a schedule item on each row.
	 * The first column is the Course name, the second is the section, and the third
	 * is the title.
	 * 
	 * @return 2D array of partial String representations of the courses in the
	 *         schedule
	 */
	public String[][] getScheduledCourses() {
		String[][] scheduledCourses = new String[schedule.size()][3];
		for (int row = 0; row < schedule.size(); row++) {
			for (int column = 0; column < 3; column++) {
				if (column == 0) {
					scheduledCourses[row][column] = schedule.get(row).getName();
				} else if (column == 1) {
					scheduledCourses[row][column] = schedule.get(row).getSection();
				} else {
					scheduledCourses[row][column] = schedule.get(row).getTitle();
				}
			}
		}
		return scheduledCourses;
	}

	/**
	 * Returns a String[][] (2D array) that contains a schedule item on each row.
	 * The first column is the Course name, the second is the section, the third is
	 * the title, the fourth is the credits, the fifth is the instructorId, and the
	 * sixth is the meeting time String.
	 * 
	 * @return 2D array of full String representations of the courses in the
	 *         schedule
	 */
	public String[][] getFullScheduledCourses() {
		String[][] fullScheduledCourses = new String[schedule.size()][6];
		for (int row = 0; row < schedule.size(); row++) {
			for (int column = 0; column < 6; column++) {
				if (column == 0) {
					fullScheduledCourses[row][column] = schedule.get(row).getName();
				} else if (column == 1) {
					fullScheduledCourses[row][column] = schedule.get(row).getSection();
				} else if (column == 2) {
					fullScheduledCourses[row][column] = schedule.get(row).getTitle();
				} else if (column == 3) {
					fullScheduledCourses[row][column] = "" + schedule.get(row).getCredits();
				} else if (column == 4) {
					fullScheduledCourses[row][column] = schedule.get(row).getInstructorId();
				} else {
					fullScheduledCourses[row][column] = schedule.get(row).getMeetingString();
				}
			}
		}
		return fullScheduledCourses;
	}

	/**
	 * Returns the schedule title.
	 * 
	 * @return schedule title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the schedule title Throws an IllegalArgumentException if the schedule
	 * title is null.
	 * 
	 * @param title schedule title
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException();
		}
		this.title = title;
	}

	/**
	 * Writes the schedule to the given file.
	 * 
	 * @param fileName given file name
	 */
	public void exportSchedule(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, schedule);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}
}