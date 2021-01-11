package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.*;
import java.security.*;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.util.LinkedList;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;

/**
 * Tests FacultyRecordIO.
 * 
 * @author Selena Chen
 * @author Atharva Mahajan
 * @author Sarah Morton
 */
public class FacultyRecordIOTest {

	/** Valid faculty records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Invalid faculty records */
	private final String invalidTestFile = "test-files/invalid_faculty_records.txt";

	/** String representations of valid faculty */
	private String validFaculty0 = "Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,pw,2";
	private String validFaculty1 = "Fiona,Meadows,fmeadow,pharetra.sed@et.org,pw,3";
	private String validFaculty2 = "Brent,Brewer,bbrewer,sem.semper@orcisem.co.uk,pw,1";
	private String validFaculty3 = "Halla,Aguirre,haguirr,Fusce.dolor.quam@amalesuadaid.net,pw,3";
	private String validFaculty4 = "Kevyn,Patel,kpatel,risus@pellentesque.ca,pw,1";
	private String validFaculty5 = "Elton,Briggs,ebriggs,arcu.ac@ipsumsodalespurus.edu,pw,3";
	private String validFaculty6 = "Norman,Brady,nbrady,pede.nonummy@elitfermentum.co.uk,pw,1";
	private String validFaculty7 = "Lacey,Walls,lwalls,nascetur.ridiculus.mus@fermentum.net,pw,2";

	/** String array containing valid faculty */
	private String[] validFacultys = { validFaculty0, validFaculty1, validFaculty2, validFaculty3, validFaculty4,
			validFaculty5, validFaculty6, validFaculty7 };
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

			for (int i = 0; i < validFacultys.length; i++) {
				validFacultys[i] = validFacultys[i].replace(",pw,", "," + hashPW + ",");
			}
		} catch (NoSuchAlgorithmException e) {
			fail("Unable to create hash during setup");
		}
	}

	/**
	 * Test method for readFacultyRecords() with valid test file.
	 */
	@Test
	public void testReadValidFacultyRecords() {
		try {
			LinkedList<Faculty> faculty = FacultyRecordIO.readFacultyRecords(validTestFile);
			assertEquals(8, faculty.size());

			LinkedList<String> facultyList = new LinkedList<String>();
			for (int i = 0; i < faculty.size(); i++) {
				facultyList.add(faculty.get(i).toString());
			}

			LinkedList<String> validFacultysList = new LinkedList<String>();
			for (int i = 0; i < validFacultys.length; i++) {
				validFacultysList.add(validFacultys[i]);
			}

			for (int i = 0; i < faculty.size(); i++) {
				assertEquals(validFacultysList.get(i), facultyList.get(i));
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + validTestFile);
		}
	}

	/**
	 * Test method for readFacultyRecords() with invalid test file.
	 */
	@Test
	public void testReadInvalidFacultyRecords() {
		LinkedList<Faculty> faculty;
		try {
			faculty = FacultyRecordIO.readFacultyRecords(invalidTestFile);
			assertEquals(0, faculty.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		}
	}

	/**
	 * Test method for writeFacultyRecords().
	 */
	@Test
	public void testWriteValidFacultyRecords() {
		LinkedList<Faculty> faculty = new LinkedList<Faculty>();
		faculty.add(new Faculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", hashPW, 2));
		faculty.add(new Faculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", hashPW, 3));
		faculty.add(new Faculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", hashPW, 1));
		try {
			FacultyRecordIO.writeFacultyRecords("test-files/actual_faculty_records.txt", faculty);
		} catch (IOException e) {
			fail("Cannot write to faculty records file");
		}
		checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_faculty_records.txt");
	}

	/**
	 * Test method for writeFacultyRecords() without permissions.
	 */
	@Test
	public void testWriteFacultyRecordsNoPermissions() {
		LinkedList<Faculty> faculty = new LinkedList<Faculty>();
		faculty.add(new Faculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", hashPW, 2));
		// Assumption that you are using a hash of "pw" stored in hashPW
		try {
			FacultyRecordIO.writeFacultyRecords("/home/sesmith5/actual_faculty_records.txt", faculty);
			fail("Attempted to write to a directory location that doesn't exist or without the appropriate permissions and the write happened.");
		} catch (IOException e) {
			assertEquals("/home/sesmith5/actual_faculty_records.txt (Permission denied)", e.getMessage());
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