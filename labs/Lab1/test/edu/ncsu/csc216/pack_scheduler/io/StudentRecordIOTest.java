package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.*;
import java.security.*;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.io.StudentRecordIO;

/**
 * Tests StudentRecordIO.
 * 
 * @author Selena Chen
 * @author Travis Walter
 * @author Tyler Mathis
 */
public class StudentRecordIOTest {

	/** Valid course records */
	private final String validTestFile = "test-files/student_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_student_records.txt";

	/** String representations of valid students */
	private String validStudent0 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	private String validStudent1 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	private String validStudent2 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	private String validStudent3 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	private String validStudent4 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	private String validStudent5 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	private String validStudent6 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	private String validStudent7 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
	private String validStudent8 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	private String validStudent9 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";
	/** String array containing valid students */
	private String[] validStudents = { validStudent0, validStudent1, validStudent2, validStudent3, validStudent4,
			validStudent5, validStudent6, validStudent7, validStudent8, validStudent9 };
	/** Hashed password */
	private String hashPW;
	/** Constant used during hashing of passwords */
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * Sets up the files for testing before calling each method.
	 * 
	 * @throws NoSuchAlgorithmException if unable to create hash during setup
	 */
	@Before
	public void setUp() throws Exception {
		try {
			String password = "pw";
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			hashPW = new String(digest.digest());

			for (int i = 0; i < validStudents.length; i++) {
				validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
			}
		} catch (NoSuchAlgorithmException e) {
			fail("Unable to create hash during setup");
		}
	}

	/**
	 * Test method for readStudentRecords() with valid test file.
	 */
	@Test
	public void testReadValidStudentRecords() {
		try {
			SortedList<Student> students = StudentRecordIO.readStudentRecords(validTestFile);
			assertEquals(10, students.size());

			SortedList<String> studentsList = new SortedList<String>();
			for (int i = 0; i < students.size(); i++) {
				studentsList.add(students.get(i).toString());
			}

			SortedList<String> validStudentsList = new SortedList<String>();
			for (int i = 0; i < validStudents.length; i++) {
				validStudentsList.add(validStudents[i]);
			}

			for (int i = 0; i < students.size(); i++) {
				assertEquals(validStudentsList.get(i), studentsList.get(i));
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + validTestFile);
		}
	}

	/**
	 * Test method for readStudentRecords() with invalid test file.
	 */
	@Test
	public void testReadInvalidStudentRecords() {
		SortedList<Student> students;
		try {
			students = StudentRecordIO.readStudentRecords(invalidTestFile);
			assertEquals(0, students.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		}
	}

	/**
	 * Test method for writeStudentRecords().
	 */
	@Test
	public void testWriteValidStudentRecords() {
		SortedList<Student> students = new SortedList<Student>();
		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
		try {
			StudentRecordIO.writeStudentRecords("test-files/actual_student_records.txt", students);
		} catch (IOException e) {
			fail("Cannot write to student records file");
		}
		checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
		
	}

	/**
	 * Test method for writeStudentRecords() without permissions.
	 */
	@Test
	public void testWriteStudentRecordsNoPermissions() {
		SortedList<Student> students = new SortedList<Student>();
		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
		// Assumption that you are using a hash of "pw" stored in hashPW
		try {
			StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students);
			fail("Attempted to write to a directory location that doesn't exist or without the appropriate permissions and the write happened.");
		} catch (IOException e) {
			assertEquals("/home/sesmith5/actual_student_records.txt (Permission denied)", e.getMessage());
			// The actual error message on Jenkins!
		}
	}

	/**
	 * Helper method to compare two files for the same contents.
	 * 
	 * @param expFile expected output
	 * @param actFile actual output
	 * @throws IOException if error reading file
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			while (expScanner.hasNextLine() && actScanner.hasNextLine()) {
				String exp = expScanner.nextLine();
				String act = actScanner.nextLine();
				assertEquals("Expected: " + exp + " Actual: " + act, exp, act);
			}
			if (expScanner.hasNextLine()) {
				fail("The expected results expect another line " + expScanner.nextLine());
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
			}
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}