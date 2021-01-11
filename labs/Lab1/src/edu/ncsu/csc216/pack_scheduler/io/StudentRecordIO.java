package edu.ncsu.csc216.pack_scheduler.io;

import java.io.*;
import java.util.*;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Handles the the import and export of students from a file. Reads student
 * records from files by processing students line-by-line and writes a set of
 * student records to a file.
 * 
 * @author Selena Chen
 * @author Travis Walter
 * @author Tyler Mathis
 */
public class StudentRecordIO {

	/**
	 * Reads Student records from a file and generates a list of valid Student
	 * records. Any invalid Students are ignored. If the file to read cannot be
	 * found or the permissions are incorrect a FileNotFoundException is thrown.
	 * 
	 * @param fileName file to read Student records from
	 * @return a list of valid Students
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Student> students = new SortedList<Student>();
		while (fileReader.hasNextLine()) {
			try {
				Student student = processStudent(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < students.size(); i++) {
					Student s = students.get(i);
					if (student.getFirstName().equals(s.getFirstName())
							&& student.getLastName().equals(s.getLastName())) {
						// it's a duplicate
						duplicate = true;
					}
				}
				if (!duplicate) {
					students.add(student);
				}
			} catch (IllegalArgumentException e) {
				// skip the line
			}
		}
		fileReader.close();
		return students;
	}

	/**
	 * Writes Student records to a file.
	 * 
	 * @param fileName         file to output to
	 * @param studentDirectory Sorted list of students
	 * @throws IOException if file is unable to be written to
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for (int i = 0; i < studentDirectory.size(); i++) {
			fileWriter.println(studentDirectory.get(i).toString());
		}
		fileWriter.close();
	}

	/**
	 * Process the Student object read through the line of text.
	 * 
	 * @param line line to be read
	 * @return student that was processed
	 * @throws IllegalArgumentException if parameters can't be read in
	 */
	private static Student processStudent(String line) {
		Scanner lineScanner = new Scanner(line);
		lineScanner.useDelimiter(",");
		String firstName = null;
		String lastName = null;
		String id = null;
		String email = null;
		String password = null;
		int maxCredits = 0;
		Student student = null;
		int count = 0;
		while (lineScanner.hasNext()) {
			count++;
			lineScanner.next();
		}
		lineScanner.close();
		if (count != 6) {
			try {
				student = new Student(firstName, lastName, id, email, password, maxCredits);
			} catch (NullPointerException n) {
				// Can't create student
			}
			return student;
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
									maxCredits = lineScanner.nextInt();
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
			student = new Student(firstName, lastName, id, email, password, maxCredits);
		} catch (NullPointerException n) {
			// Can't create student
		}
		return student;
	}
}