package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests StudentDirectory.
 * 
 * @author Sarah Heckman
 */
public class StudentDirectoryTest {

	/** Valid course records */
	private final String validTestFile = "test-files/student_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "";
	/** Test first name */
	private static final String FIRST_NAME = "Stu";
	/** Test last name */
	private static final String LAST_NAME = "Dent";
	/** Test id */
	private static final String ID = "sdent";
	/** Test email */
	private static final String EMAIL = "sdent@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_CREDITS = 15;

	/**
	 * Resets course_records.txt for use in other tests.
	 * 
	 * @throws Exception if something fails during setup
	 */
	@Before
	public void setUp() throws Exception {
		// Reset student_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_student_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "student_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests StudentDirectory().
	 */
	@Test
	public void testStudentDirectory() {
		// Test that the StudentDirectory is initialized to an empty list
		StudentDirectory sd = new StudentDirectory();
		assertFalse(sd.removeStudent("sesmith5"));
		assertEquals(0, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.testNewStudentDirectory().
	 */
	@Test
	public void testNewStudentDirectory() {
		// Test that if there are students in the directory, they
		// are removed after calling newStudentDirectory().
		StudentDirectory sd = new StudentDirectory();

		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);

		sd.newStudentDirectory();
		assertEquals(0, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.loadStudentsFromFile().
	 */
	@Test
	public void testLoadStudentsFromFile() {
		StudentDirectory sd = new StudentDirectory();

		// Test valid file
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.loadStudentsFromFile() with invalid filename.
	 * 
	 * @throws IllegalArgumentException if unable to read from file
	 */
	@Test
	public void testLoadStudentsFromInvalidFile() {
		StudentDirectory sd = new StudentDirectory();

		try {
			// Test invalid file
			sd.loadStudentsFromFile(invalidTestFile);
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to read file ", e.getMessage());
		}
	}

	/**
	 * Tests StudentDirectory.addStudent().
	 */
	@Test
	public void testAddStudent() {
		StudentDirectory sd = new StudentDirectory();

		// Test valid Student
		sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		String[][] studentDirectory = sd.getStudentDirectory();
		assertEquals(1, studentDirectory.length);
		assertEquals(FIRST_NAME, studentDirectory[0][0]);
		assertEquals(LAST_NAME, studentDirectory[0][1]);
		assertEquals(ID, studentDirectory[0][2]);
	}

	/**
	 * Tests StudentDirectory.addStudent() when the first password is null.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testAddStudentPasswordNull() {
		StudentDirectory sd = new StudentDirectory();
		try {
			// Test invalid password for Student
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, null, PASSWORD, MAX_CREDITS);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
	}

	/**
	 * Tests StudentDirectory.addStudent() when the first password entered is empty.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testAddStudentPasswordEmpty() {
		StudentDirectory sd = new StudentDirectory();
		try {
			// Test empty password for Student
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, "", PASSWORD, MAX_CREDITS);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
	}

	/**
	 * Tests StudentDirectory.addStudent() when repeated password is null.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testAddStudentRepeatPasswordNull() {
		StudentDirectory sd = new StudentDirectory();
		try {
			// Test null repeat password for Student
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, null, MAX_CREDITS);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
	}

	/**
	 * Tests StudentDirectory.addStudent() when repeated password is empty.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testAddStudentRepeatPasswordEmpty() {
		StudentDirectory sd = new StudentDirectory();
		try {
			// Test empty repeat password for Student
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "", MAX_CREDITS);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
	}

	/**
	 * Tests StudentDirectory.removeStudent().
	 */
	@Test
	public void testRemoveStudent() {
		StudentDirectory sd = new StudentDirectory();

		// Add students and remove
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
		assertTrue(sd.removeStudent("efrost"));
		String[][] studentDirectory = sd.getStudentDirectory();
		assertEquals(9, studentDirectory.length);
		assertEquals("Zahir", studentDirectory[5][0]);
		assertEquals("King", studentDirectory[5][1]);
		assertEquals("zking", studentDirectory[5][2]);
	}

	/**
	 * Tests StudentDirectory.saveStudentDirectory().
	 */
	@Test
	public void testSaveStudentDirectory() {
		StudentDirectory sd = new StudentDirectory();

		// Add a student
		sd.addStudent("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 15);
		assertEquals(1, sd.getStudentDirectory().length);
		sd.saveStudentDirectory("test-files/actual_student_records.txt");
		checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
	}

	/**
	 * Tests StudentDirectory.saveStudentDirectory() with invalid filename.
	 * 
	 * @throws IllegalArgumentException if unable to write to file
	 */
	@Test
	public void testSaveStudentDirectoryInvalidFile() {
		StudentDirectory sd = new StudentDirectory();

		try {
			// Add a student and save to invalid file
			sd.addStudent("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 15);
			assertEquals(1, sd.getStudentDirectory().length);
			sd.saveStudentDirectory(invalidTestFile);
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to write to file ", e.getMessage());
		}
	}

	/**
	 * Helper method to compare two files for the same contents
	 * 
	 * @param expFile expected output
	 * @param actFile actual output
	 * @throws IOException if error reading files
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));

			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
	
	/**
	 * Tests getStudentById().
	 */
	@Test
	public void testGetStudentById() {
		StudentDirectory sd = new StudentDirectory();
		sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		Student student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		assertEquals(student.getFirstName(), sd.getStudentById(ID).getFirstName());
	}
}