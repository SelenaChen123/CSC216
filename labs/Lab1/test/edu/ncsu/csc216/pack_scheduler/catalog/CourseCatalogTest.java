package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests CourseCatalog.
 * 
 * @author Selena Chen
 * @author Travis Walter
 * @author Tyler Mathis
 */
public class CourseCatalogTest {

	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_course_records.txt";
	/** Second invalid course records */
	private final String invalidTestFile2 = "test-files/invalid2_course_records.txt";

	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Programming Concepts - Java";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 4;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;

	/**
	 * Resets course_records.txt for use in other tests.
	 * 
	 * @throws Exception if unable to reset files
	 */
	@Before
	public void setUp() throws Exception {
		// Reset course_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "starter_course_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "course_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests CourseCatalog().
	 */
	@Test
	public void testCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		assertEquals(0, cc.getCourseCatalog().length);
	}

	/**
	 * Tests newCourseCatalog().
	 */
	@Test
	public void testNewCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		assertEquals(0, cc.getCourseCatalog().length);
		cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(1, cc.getCourseCatalog().length);
		cc.newCourseCatalog();
		assertEquals(0, cc.getCourseCatalog().length);
	}

	/**
	 * Tests loadCoursesFromFile().
	 * 
	 * @throws IllegalArgumentException if unable to read file
	 */
	@Test
	public void testLoadCoursesFromFile() {
		CourseCatalog cc = new CourseCatalog();

		// Test valid file
		cc.loadCoursesFromFile(validTestFile);
		assertEquals(8, cc.getCourseCatalog().length);

		// Test invalid file
		try {
			cc.loadCoursesFromFile(invalidTestFile);
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to read file ", e.getMessage());
		}
		
		try {
			cc.loadCoursesFromFile(invalidTestFile2);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to read file test-files/invalid2_course_records.txt", e.getMessage());
		}
	}

	/**
	 * Tests addCourseToCatalog().
	 * 
	 * @throws IllegalArgumentException if course cannot be added due to a conflict
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog cc = new CourseCatalog();

		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
		assertTrue(cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME,
				END_TIME));
		assertEquals(1, cc.getCourseCatalog().length);

		String[] course = cc.getCourseCatalog()[0];
		assertEquals(NAME, course[0]);
		assertEquals(SECTION, course[1]);
		assertEquals(TITLE, course[2]);
		assertEquals(c.getMeetingString(), course[3]);
		
		// Test that a duplicate course cannot be added
//		try {
//			cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME,
//					END_TIME);
//			fail();
//		} catch (IllegalArgumentException e) {
//			assertEquals(NAME, course[0]);
//			assertEquals(SECTION, course[1]);
//			assertEquals(TITLE, course[2]);
//			assertEquals(c.getMeetingString(), course[3]);
//		}
		assertFalse(cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME,
				END_TIME));
	}

	/**
	 * Tests removeCourseFromCatalog().
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();

		assertFalse(cc.removeCourseFromCatalog(NAME, SECTION));

		assertTrue(cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME,
				END_TIME));
		assertEquals(1, cc.getCourseCatalog().length);

		assertTrue(cc.removeCourseFromCatalog(NAME, SECTION));
		assertEquals(0, cc.getCourseCatalog().length);

		assertTrue(cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME,
				END_TIME));
		assertEquals(1, cc.getCourseCatalog().length);
	}

	/**
	 * Tests getCourseFromCatalog().
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();

		assertNull(cc.getCourseFromCatalog("CSC492", "001"));

		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
		cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(c, cc.getCourseFromCatalog("CSC216", "001"));
	}

	/**
	 * Tests getCourseCatalog().
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();

		cc.addCourseToCatalog("CSC116", "Intro to Programming - Java", "001", 3, "jdyoung2", "MW", 910, 1100);
		cc.addCourseToCatalog("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "TH", 1330, 1445);
		cc.addCourseToCatalog("CSC230", "C and Software Tools", "001", 3, "dbsturgi", "MW", 1145, 1300);

		// Get the catalog and make sure contents are correct
		// Name, section, title
		String[][] catalog = cc.getCourseCatalog();
		// Row 0
		assertEquals("CSC116", catalog[0][0]);
		assertEquals("001", catalog[0][1]);
		assertEquals("Intro to Programming - Java", catalog[0][2]);
		assertEquals("MW 9:10AM-11:00AM", catalog[0][3]);
		// Row 1
		assertEquals("CSC216", catalog[1][0]);
		assertEquals("001", catalog[1][1]);
		assertEquals("Programming Concepts - Java", catalog[1][2]);
		assertEquals("TH 1:30PM-2:45PM", catalog[1][3]);
		// Row 2
		assertEquals("CSC230", catalog[2][0]);
		assertEquals("001", catalog[2][1]);
		assertEquals("C and Software Tools", catalog[2][2]);
		assertEquals("MW 11:45AM-1:00PM", catalog[2][3]);
	}

	/**
	 * Tests saveCourseCatalog().
	 */
	@Test
	public void testSaveCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.addCourseToCatalog("CSC116", "Intro to Programming - Java", "003", 3, "spbalik", "MW", 1250, 1440);
		cc.addCourseToCatalog("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1330, 1445);

		cc.saveCourseCatalog("test-files/actual_course_records.txt");
		
		try {
			Scanner expScanner = new Scanner(new File("test-files/expected_course_records.txt"));
			Scanner actScanner = new Scanner(new File("test-files/actual_course_records.txt"));
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}