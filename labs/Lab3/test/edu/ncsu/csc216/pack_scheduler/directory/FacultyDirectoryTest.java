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

import edu.ncsu.csc216.pack_scheduler.user.Faculty;

/**
 * Tests FacultyDirectory.
 * 
 * @author Sarah Heckman
 * @author Atharva Mahajan
 * @author Selena Chen
 * @author Sarah Morton
 */
public class FacultyDirectoryTest {

	/** Valid course records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "";
	/** Test first name */
	private static final String FIRST_NAME = "faculty";
	/** Test last name */
	private static final String LAST_NAME = "member";
	/** Test id */
	private static final String ID = "fmemb";
	/** Test email */
	private static final String EMAIL = "fmemb@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_COURSES = 2;

	/**
	 * Resets course_records.txt for use in other tests.
	 * 
	 * @throws Exception if something fails during setup
	 */
	@Before
	public void setUp() throws Exception {
		// Reset Faculty_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_faculty_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "faculty_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests FacultyDirectory().
	 */
	@Test
	public void testFacultyDirectory() {
		// Test that the FacultyDirectory is initialized to an empty list
		FacultyDirectory sd = new FacultyDirectory();
		assertFalse(sd.removeFaculty("sesmith5"));
		assertEquals(0, sd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.testNewFacultyDirectory().
	 */
	@Test
	public void testNewFacultyDirectory() {
		// Test that if there are Faculty in the directory, they
		// are removed after calling newFacultyDirectory().
		FacultyDirectory sd = new FacultyDirectory();

		sd.loadFacultyFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);

		sd.newFacultyDirectory();
		assertEquals(0, sd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.loadFacultysFromFile().
	 */
	@Test
	public void testLoadFacultysFromFile() {
		FacultyDirectory sd = new FacultyDirectory();

		// Test valid file
		sd.loadFacultyFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.loadFacultysFromFile() with invalid filename.
	 * 
	 * @throws IllegalArgumentException if unable to read from file
	 */
	@Test
	public void testLoadFacultyFromInvalidFile() {
		FacultyDirectory sd = new FacultyDirectory();

		try {
			// Test invalid file
			sd.loadFacultyFromFile(invalidTestFile);
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to read file ", e.getMessage());
		}
	}

	/**
	 * Tests FacultyDirectory.addFaculty().
	 */
	@Test
	public void testAddFaculty() {
		FacultyDirectory sd = new FacultyDirectory();

		// Test valid Faculty
		sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		String[][] facultyDirectory = sd.getFacultyDirectory();
		assertEquals(1, facultyDirectory.length);
		assertEquals(FIRST_NAME, facultyDirectory[0][0]);
		assertEquals(LAST_NAME, facultyDirectory[0][1]);
		assertEquals(ID, facultyDirectory[0][2]);
	}

	/**
	 * Tests FacultyDirectory.addFaculty() when the first password is null.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testAddFacultyPasswordNull() {
		FacultyDirectory sd = new FacultyDirectory();
		try {
			// Test invalid password for Faculty
			sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, null, PASSWORD, MAX_COURSES);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
	}

	/**
	 * Tests FacultyDirectory.addFaculty() when the first password entered is empty.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testAddFacultyPasswordEmpty() {
		FacultyDirectory sd = new FacultyDirectory();
		try {
			// Test empty password for Faculty
			sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "", PASSWORD, MAX_COURSES);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
	}

	/**
	 * Tests FacultyDirectory.addFaculty() when repeated password is null.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testAddFacultyRepeatPasswordNull() {
		FacultyDirectory sd = new FacultyDirectory();
		try {
			// Test null repeat password for Faculty
			sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, null, MAX_COURSES);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
	}

	/**
	 * Tests FacultyDirectory.addFaculty() when repeated password is empty.
	 * 
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testAddFacultyRepeatPasswordEmpty() {
		FacultyDirectory sd = new FacultyDirectory();
		try {
			// Test empty repeat password for Faculty
			sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "", MAX_COURSES);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
	}

	/**
	 * Tests FacultyDirectory.removeFaculty().
	 */
	@Test
	public void testRemoveFaculty() {
		FacultyDirectory sd = new FacultyDirectory();

		// Add Faculty and remove
		sd.loadFacultyFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);
		assertTrue(sd.removeFaculty("lwalls"));
		String[][] facultyDirectory = sd.getFacultyDirectory();
		assertEquals(7, facultyDirectory.length);
		assertEquals("Elton", facultyDirectory[5][0]);
		assertEquals("Briggs", facultyDirectory[5][1]);
		assertEquals("ebriggs", facultyDirectory[5][2]);
	}

	/**
	 * Tests FacultyDirectory.saveFacultyDirectory().
	 */
	@Test
	public void testSaveFacultyDirectory() {
		FacultyDirectory sd = new FacultyDirectory();

		// Add a Faculty
		sd.addFaculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", "pw", "pw", 2);
		sd.addFaculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", "pw", "pw", 3);
		sd.addFaculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", "pw", "pw", 1);
		assertEquals(3, sd.getFacultyDirectory().length);
		sd.saveFacultyDirectory("test-files/actual_faculty_records.txt");
		checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_faculty_records.txt");
	}

	/**
	 * Tests FacultyDirectory.saveFacultyDirectory() with invalid filename.
	 * 
	 * @throws IllegalArgumentException if unable to write to file
	 */
	@Test
	public void testSaveFacultyDirectoryInvalidFile() {
		FacultyDirectory sd = new FacultyDirectory();

		try {
			// Add a Faculty and save to invalid file
			sd.addFaculty("Ashley", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", "pw", "pw", 2);
			assertEquals(1, sd.getFacultyDirectory().length);
			sd.saveFacultyDirectory(invalidTestFile);
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
	 * Tests getFacultyById().
	 */
	@Test
	public void testGetFacultyById() {
		FacultyDirectory sd = new FacultyDirectory();
		sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		Faculty faculty = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		assertEquals(faculty.getFirstName(), sd.getFacultyById(ID).getFirstName());
	}
}