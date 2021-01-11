package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;

/**
 * Represents the course catalog in the PackScheduler system. Has methods to
 * create a new course catalog, load courses from a file, add courses to the
 * catalog, remove courses from the catalog, get courses from the catalog, get
 * the entire course catalog, and save the course catalog to a file.
 * 
 * @author Selena Chen
 * @author Travis Walter
 * @author Tyler Mathis
 */
public class CourseCatalog {

	/** Courses that make up the catalog */
	private SortedList<Course> catalog;

	/**
	 * Constructs an empty catalog.
	 */
	public CourseCatalog() {
		catalog = new SortedList<Course>();
	}

	/**
	 * Constructs an empty catalog.
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}

	/**
	 * Loads course records into the catalog. Any FileNotFoundExceptions are caught
	 * and an IllegalArgumentException is thrown to the client.
	 * 
	 * @param file file where courses are read from
	 * @throws IllegalArgumentException if unable to read file
	 */
	public void loadCoursesFromFile(String file) {
		try {
			catalog = CourseRecordIO.readCourseRecords(file);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + file);
		}
	}

	/**
	 * Adds a Course with the following fields to the catalog and returns true if
	 * the Course is added and false if the Course already exists in the catalog. If
	 * there is an error constructing the Course, the IllegalArgumentException is
	 * allowed to propagate to the client.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays  meeting days for Course as series of chars
	 * @param startTime    start time for Course
	 * @param endTime      end time for Course
	 * @return true if the Course is added and false if the Course already exists in
	 *         the catalog
	 * @throws IllegalArgumentException if there is an error constructing the Course
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId,
			String meetingDays, int startTime, int endTime) {
		Course course = new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);
		for (int i = 0; i < catalog.size(); i++) {
			if (course.isDuplicate(catalog.get(i)) && catalog.get(i).getSection().equals(section)) {
				return false;
//				throw new IllegalArgumentException("You are already enrolled in " + name);
			}
		}
		catalog.add(course);
		return true;
	}

	/**
	 * Removes the Course with the given name and section from the catalog.
	 * 
	 * @param name    name of Course
	 * @param section section of Course
	 * @return true if the Course is removed from the catalog and false if the
	 *         Course is not in the catalog
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		Course course = getCourseFromCatalog(name, section);
		if (catalog.contains(course)) {
			catalog.remove(catalog.indexOf(course));
			return true;
		}
		return false;
	}

	/**
	 * Returns the Course from the catalog with the given name and section. Returns
	 * null if the Course isn’t in the catalog.
	 * 
	 * @param name    name of Course
	 * @param section section of Course
	 * @return course from the catalog with the given name and section
	 */
	public Course getCourseFromCatalog(String name, String section) {
		Course course = null;
		for (int i = 0; i < catalog.size(); i++) {
			Course currentCourse = (Course) catalog.get(i);
			if (currentCourse.getName().equals(name) && currentCourse.getSection().equals(section)) {
				course = currentCourse;
			}
		}
		return course;
	}

	/**
	 * Returns the name, section, title, and meeting information for Courses in the
	 * catalog.
	 * 
	 * @return String array containing the name, section, title, and meeting
	 *         information for Courses in the catalog
	 */
	public String[][] getCourseCatalog() {
		String[][] courseCatalog = new String[catalog.size()][4];
		for (int row = 0; row < catalog.size(); row++) {
			courseCatalog[row] = catalog.get(row).getShortDisplayArray();
		}
		return courseCatalog;
	}

	/**
	 * Saves the catalog course records to the given file. Any IOExceptions are
	 * caught and an IllegalArgumentException is thrown to the client.
	 * 
	 * @param file file to be written to
	 * @throws IllegalArgumentException if file cannot be saved
	 */
	public void saveCourseCatalog(String file) {
		try {
			CourseRecordIO.writeCourseRecords(file, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}
}