package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.*;
import java.util.*;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * Reads in and stores as a list all of the Activity records stored in a file
 * and creates a schedule for the current user (a student) and provides
 * functionality for naming the schedule, adding Courses and Events to the
 * schedule, removing Activities from the schedule, and resetting the schedule.
 * 
 * @author Selena Chen
 */
public class WolfScheduler {

	/** Course catalog */
	private ArrayList<Course> catalog;
	/** Schedule */
	private ArrayList<Activity> schedule;
	/** Schedule title */
	private String title;

	/**
	 * Initializes schedule to an empty ArrayList and sets title to My Schedule.
	 * Initializes the catalog ArrayList and populates the Courses in the catalog
	 * from the information in the courseRecordsFileName. Throws an
	 * IllegalArgumentException if the file cannot be found.
	 * 
	 * @param courseRecordsFileName file where courses are read from
	 * @throws IllegalArgumentException if file cannot be read
	 */
	public WolfScheduler(String courseRecordsFileName) {
		catalog = new ArrayList<Course>();
		schedule = new ArrayList<Activity>();
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
			Course currentCourse = (Course) catalog.get(i);
			if (currentCourse.getName().equals(name) && currentCourse.getSection().equals(section)) {
				course = currentCourse;
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
	 * @throws IllegalArgumentException if there is a Course with the same name
	 *                                  already in the schedule
	 */
	public boolean addCourse(String name, String section) {
		Course course = getCourseFromCatalog(name, section);
		if (course == null) {
			return false;
		}
		for (int i = 0; i < schedule.size(); i++) {
			if (course.isDuplicate(schedule.get(i))) {
				throw new IllegalArgumentException("You are already enrolled in " + name);
			}
		}
		schedule.add(course);
		return true;
	}

	/**
	 * Returns true if the Event represented by the name and section are in the
	 * catalog and can be added to the schedule and false if the Event is not in the
	 * catalog. If there is a Event with the same name already in the schedule an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param title        Event's title
	 * @param meetingDays  Event's meeting days
	 * @param startTime    Event's start time
	 * @param endTime      Event's end time
	 * @param weeklyRepeat Event's weekly repeat
	 * @param eventDetails Event's details
	 * @return true if the Event represented by the name and section are in the
	 *         catalog and can be added to the schedule or false if the Event is not
	 *         in the catalog
	 * @throws IllegalArgumentException if there is an Event with the same name
	 *                                  already in the schedule
	 */
	public boolean addEvent(String title, String meetingDays, int startTime, int endTime, int weeklyRepeat,
			String eventDetails) {
		Event event = new Event(title, meetingDays, startTime, endTime, weeklyRepeat, eventDetails);
		for (int i = 0; i < schedule.size(); i++) {
			if (event.isDuplicate(schedule.get(i))) {
				throw new IllegalArgumentException("You have already created an event called " + title);
			}
		}
		schedule.add(event);
		return true;
	}

	/**
	 * Returns true if the Activity is removed from the schedule and false
	 * otherwise.
	 * 
	 * @param idx index of the Activity to be removed
	 * @return true if the Activity is removed from the schedule or false otherwise
	 */
	public boolean removeActivity(int idx) {
		if (idx < schedule.size()) {
			for (int i = 0; i < schedule.size(); i++) {
				if (schedule.get(i).equals(schedule.get(idx))) {
					schedule.remove(i);
					return true;
				}
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
		String[][] courseCatalog = new String[catalog.size()][4];
		for (int row = 0; row < catalog.size(); row++) {
			courseCatalog[row] = catalog.get(row).getShortDisplayArray();
		}
		return courseCatalog;
	}

	/**
	 * Returns a String[][] (2D array) that contains a schedule item on each row.
	 * The first column is the Activity name, the second is the section, the third
	 * is the title, and the fourth is the meeting string.
	 * 
	 * @return 2D array of partial String representations of the Activities in the
	 *         schedule
	 */
	public String[][] getScheduledActivities() {
		String[][] scheduledCourses = new String[schedule.size()][4];
		for (int row = 0; row < schedule.size(); row++) {
			scheduledCourses[row] = schedule.get(row).getShortDisplayArray();
		}
		return scheduledCourses;
	}

	/**
	 * Returns a String[][] (2D array) that contains a schedule item on each row.
	 * The first column is the Activity name, the second is the section, the third
	 * is the title, the fourth is the credits, the fifth is the instructorId, sixth
	 * is the meeting time String, and seventh is the event details.
	 * 
	 * @return 2D array of full String representations of the Activities in the
	 *         schedule
	 */
	public String[][] getFullScheduledActivities() {
		String[][] fullScheduledCourses = new String[schedule.size()][7];
		for (int row = 0; row < schedule.size(); row++) {
			fullScheduledCourses[row] = schedule.get(row).getLongDisplayArray();
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
	 * @throws IllegalArgumentException if the title is null
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
	 * @throws IllegalArgumentException if file cannot be saved
	 */
	public void exportSchedule(String fileName) {
		try {
			ActivityRecordIO.writeActivityRecords(fileName, schedule);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}
}