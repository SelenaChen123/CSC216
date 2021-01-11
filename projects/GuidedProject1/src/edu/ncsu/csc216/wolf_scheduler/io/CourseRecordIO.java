package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.*;
import java.util.*;

import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * Reads Course records from text files. Writes a set of CourseRecords to a
 * file.
 * 
 * @author Sarah Heckman
 * @author Selena Chen
 */
public class CourseRecordIO {
	
	/**
	 * Reads course records from a file and generates a list of valid Courses. Any
	 * invalid Courses are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a FileNotFoundException is thrown.
	 * 
	 * @param fileName file to read Course records from
	 * @return a list of valid Courses
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		ArrayList<Course> courses = new ArrayList<Course>();
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
	 * Returns a Course object created by parsing the line of text.
	 * 
	 * @param nextLine line to be read
	 * @return course that was read
	 */
	private static Course readCourse(String nextLine) {
		final int allParameters = 8;
		final int lessParameters = 6;
		final int minNameLength = 4;
		final int maxNameLength = 6;
		final int sectionLength = 3;
		final int maxCredits = 5;
		Scanner lineScanner = new Scanner(nextLine);
		lineScanner.useDelimiter(",");
		String name = null;
		String title = null;
		String section = null;
		int credits = 0;
		String instructorId = null;
		String meetingDays = null;
		int startTime = 0;
		int endTime = 0;
		Course course = null;
		int count = 0;
		while (lineScanner.hasNext()) {
			count++;
			lineScanner.next();
		}
		lineScanner.close();
		if (count != allParameters && count != lessParameters) {
			try {
				course = new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);
			} catch (NullPointerException n) {
				// Can't create course
			}
			return course;
		}
		lineScanner = new Scanner(nextLine);
		lineScanner.useDelimiter(",");
		try {
			if (lineScanner.hasNext()) {
				name = lineScanner.next();
				if (name.length() >= minNameLength && name.length() <= maxNameLength && lineScanner.hasNext()) {
					title = lineScanner.next();
					if (lineScanner.hasNext()) {
						section = lineScanner.next();
						if (section.length() == sectionLength && lineScanner.hasNextInt()) {
							credits = lineScanner.nextInt();
							if (credits >= 1 && credits <= maxCredits && lineScanner.hasNext()) {
								instructorId = lineScanner.next();
								if (lineScanner.hasNext()) {
									meetingDays = lineScanner.next();
									if (lineScanner.hasNextInt()) {
										startTime = lineScanner.nextInt();
										if (lineScanner.hasNextInt()) {
											endTime = lineScanner.nextInt();
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (InputMismatchException i) {
			// Invalid input
		}
		lineScanner.close();
		try {
			course = new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);
		} catch (NullPointerException n) {
			// Can't create course
		}
		return course;
	}

	/**
	 * Writes the given list of Courses to an output file
	 * 
	 * @param fileName file to output to
	 * @param courses  ArrayList of courses
	 * @throws IOException if file unable to write to the file
	 */
	public static void writeCourseRecords(String fileName, ArrayList<Course> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for (int i = 0; i < courses.size(); i++) {
			fileWriter.println(courses.get(i).toString());
		}
		fileWriter.close();
	}
}