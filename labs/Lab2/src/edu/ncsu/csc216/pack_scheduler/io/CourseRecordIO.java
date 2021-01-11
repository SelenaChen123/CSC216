package edu.ncsu.csc216.pack_scheduler.io;

import java.io.*;
import java.util.*;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Handles the the import and export of courses from a file. Reads course
 * records from files by reading courses in line-by-line and writes a set of
 * course records to a file.
 * 
 * @author Sarah Heckman
 * @author Selena Chen
 * @author Travis Walter
 * @author Tyler Mathis
 * @author Justin Wald
 */
public class CourseRecordIO {

	/**
	 * Reads course records from a file and generates a list of valid Courses. Any
	 * invalid Courses are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName file to read Course records from
	 * @return a list of valid Courses
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Course> courses = new SortedList<Course>();
		while (fileReader.hasNextLine()) {
			try {
				Course course = readCourse(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < courses.size(); i++) {
					Course c = courses.get(i);
					if (course.getName().equals(c.getName()) && course.getSection().equals(c.getSection())) {
						// it's a duplicate
						duplicate = true;
					}
				}
				if (!duplicate) {
					courses.add(course);
				}
			} catch (IllegalArgumentException e) {
				// skip the line
			}
		}
		fileReader.close();
		return courses;
	}

	/**
	 * Parses a single course's line for info and returns Course object if
	 * successful.
	 * 
	 * @param nextLine line to be read
	 * @return Course generated from line info
	 * @throws IllegalArgumentException if a time is listed on a course record with
	 *                                  meeting days "A"
	 */
	private static Course readCourse(String nextLine) {
		Scanner s = new Scanner(nextLine);
		s.useDelimiter(",");

		try {
			String name = s.next();
			String title = s.next();
			String section = s.next();
			int credits = s.nextInt();
			String instructorId = s.next();
			int enrollmentCap = s.nextInt();
			String meetingDays = s.next();
			if (meetingDays.equals("A")) {
				if (s.hasNextInt()) {
					s.close();
					throw new IllegalArgumentException();
				}
				s.close();
				return new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays);
			} else {
				int startTime = s.nextInt();
				int endTime = s.nextInt();
				s.close();
				return new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime,
						endTime);
			}
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Writes the given list of Courses to an output file
	 * 
	 * @param fileName file to output to
	 * @param courses  SortedList of courses
	 * @throws IOException if file unable to write to the file
	 */
	public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for (int i = 0; i < courses.size(); i++) {
			fileWriter.println(courses.get(i).toString());
		}
		fileWriter.close();
	}
}