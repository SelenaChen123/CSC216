package edu.ncsu.csc216.pack_scheduler.io;

import java.io.*;
import java.util.*;

import edu.ncsu.csc216.pack_scheduler.util.LinkedList;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * Handles the the import and export of faculty from a file. Reads faculty
 * records from files by processing faculty line-by-line and writes a set of
 * faculty records to a file.
 * 
 * @author Selena Chen
 * @author Atharva Mahajan
 * @author Sarah Morton
 */
public class FacultyRecordIO {

	/**
	 * Reads Faculty records from a file and generates a list of valid Faculty
	 * records. Any invalid Faculty are ignored. If the file to read cannot be found
	 * or the permissions are incorrect a FileNotFoundException is thrown.
	 * 
	 * @param fileName file to read Faculty records from
	 * @return a list of valid Faculty
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		LinkedList<Faculty> facultyList = new LinkedList<Faculty>();
		while (fileReader.hasNextLine()) {
			try {
				Faculty faculty = processFaculty(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < facultyList.size(); i++) {
					User f = facultyList.get(i);
					if (faculty.getFirstName().equals(f.getFirstName())
							&& faculty.getLastName().equals(f.getLastName())) {
						// it's a duplicate
						duplicate = true;
					}
				}
				if (!duplicate) {
					facultyList.add(faculty);
				}
			} catch (IllegalArgumentException e) {
				// skip the line
			}
		}
		fileReader.close();
		return facultyList;
	}

	/**
	 * Writes Faculty records to a file.
	 * 
	 * @param fileName         file to output to
	 * @param facultyDirectory linked list of faculty
	 * @throws IOException if file is unable to be written to
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for (int i = 0; i < facultyDirectory.size(); i++) {
			fileWriter.println(facultyDirectory.get(i).toString());
		}
		fileWriter.close();
	}

	/**
	 * Process the Faculty object read through the line of text.
	 * 
	 * @param line line to be read
	 * @return Faculty that was processed
	 * @throws IllegalArgumentException if parameters can't be read in
	 */
	private static Faculty processFaculty(String line) {
		Scanner lineScanner = new Scanner(line);
		lineScanner.useDelimiter(",");
		String firstName = null;
		String lastName = null;
		String id = null;
		String email = null;
		String password = null;
		int maxCourses = 0;
		Faculty faculty = null;
		int count = 0;
		while (lineScanner.hasNext()) {
			count++;
			lineScanner.next();
		}
		lineScanner.close();
		if (count != 6) {
			try {
				faculty = new Faculty(firstName, lastName, id, email, password, maxCourses);
			} catch (NullPointerException n) {
				// Can't create faculty
			}
			return faculty;
		}
		lineScanner = new Scanner(line);
		lineScanner.useDelimiter(",");
		try {
			if (lineScanner.hasNext()) {
				firstName = lineScanner.next();
				if (firstName.length() != 0 && lineScanner.hasNext()) {
					lastName = lineScanner.next();
					if (lastName.length() != 0 && lineScanner.hasNext()) {
						id = lineScanner.next();
						if (id.length() != 0 && lineScanner.hasNext()) {
							email = lineScanner.next();
							if (email.length() != 0 && email.contains(".") && email.contains("@")
									&& email.lastIndexOf('.') > email.lastIndexOf('@') && lineScanner.hasNext()) {
								password = lineScanner.next();
								if (password.length() != 0 && lineScanner.hasNextInt()) {
									maxCourses = lineScanner.nextInt();
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
			faculty = new Faculty(firstName, lastName, id, email, password, maxCourses);
		} catch (NullPointerException n) {
			// Can't create faculty
		}
		return faculty;
	}
}