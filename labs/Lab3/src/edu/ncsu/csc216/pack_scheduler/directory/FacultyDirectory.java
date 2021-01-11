package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ListIterator;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Maintains a directory of all Faculty enrolled at NC State. All Faculty have a
 * unique id. Has methods to create a new Faculty directory, load Faculty from a
 * file, add Faculty, remove Faculty, get the Faculty directory, and save the
 * Faculty directory to a file.
 * 
 * @author Sarah Heckman
 * @author Selena Chen
 * @author Travis Walter
 * @author Tyler Mathis
 * @author Atharva Mahajan
 * @author Sarah Morton
 */
public class FacultyDirectory {

	/** List of Faculty in the directory */
	private LinkedList<Faculty> facultyDirectory;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * Creates an empty Faculty directory.
	 */
	public FacultyDirectory() {
		newFacultyDirectory();
	}

	/**
	 * Creates an empty Faculty directory. All Faculty in the previous list are list
	 * unless saved by the user.
	 */
	public void newFacultyDirectory() {
		facultyDirectory = new LinkedList<Faculty>();
	}

	/**
	 * Constructs the Faculty directory by reading in Faculty information from the
	 * given file. Throws an IllegalArgumentException if the file cannot be found.
	 * 
	 * @param fileName file containing list of Faculty
	 * @throws IllegalArgumentException if the file cannot be found
	 */
	public void loadFacultyFromFile(String fileName) {
		try {
			facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}

	/**
	 * Adds a Faculty to the directory. Returns true if the Faculty is added and
	 * false if the Faculty is unable to be added because their id matches another
	 * Faculty's id. This method also hashes the Faculty's password for internal
	 * storage.
	 * 
	 * @param firstName      Faculty's first name
	 * @param lastName       Faculty's last name
	 * @param id             Faculty's id
	 * @param email          Faculty's email
	 * @param password       Faculty's password
	 * @param repeatPassword Faculty's repeated password
	 * @param maxCourses     Faculty's max Courses.
	 * @return true if added, false otherwise
	 * @throws IllegalArgumentException if any parameter is invalid
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password,
			String repeatPassword, int maxCourses) {
		String hashPW = "";
		String repeatHashPW = "";
		if (password == null || repeatPassword == null || password.equals("") || repeatPassword.equals("")) {
			throw new IllegalArgumentException("Invalid password");
		}
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(password.getBytes());
			hashPW = new String(digest1.digest());
			MessageDigest digest2 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest2.update(repeatPassword.getBytes());
			repeatHashPW = new String(digest2.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
		if (!hashPW.equals(repeatHashPW)) {
			throw new IllegalArgumentException("Passwords do not match");
		}
		// If an IllegalArgumentException is thrown, it's passed up from Faculty
		// to the GUI
		Faculty faculty = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		ListIterator<Faculty> iter = facultyDirectory.listIterator(0);
		while (iter.hasNext()) {
			User s = iter.next();
			if (s.getId().equals(faculty.getId())) {
				return false;
			}
		}
		return facultyDirectory.add(faculty);
	}

	/**
	 * Removes the Faculty with the given id from the list of Faculty with the given
	 * id. Returns true if the Faculty is removed and false if the Faculty is not in
	 * the list.
	 * 
	 * @param facultyId Faculty's id
	 * @return true if removed, false otherwise
	 */
	public boolean removeFaculty(String facultyId) {
		ListIterator<Faculty> iter = facultyDirectory.listIterator(0);
		while (iter.hasNext()) {
			User s = iter.next();
			if (s.getId().equals(facultyId)) {
				iter.remove();
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns all Faculty in the directory with a column for first name, last name,
	 * and id.
	 * 
	 * @return String array containing Faculty first name, last name, and id
	 */
	public String[][] getFacultyDirectory() {
		String[][] directory = new String[facultyDirectory.size()][3];
		ListIterator<Faculty> iter = facultyDirectory.listIterator(0);
		int i = 0;
		while (iter.hasNext()) {
			User s = iter.next();
			directory[i][0] = s.getFirstName();
			directory[i][1] = s.getLastName();
			directory[i][2] = s.getId();
			i++;
		}
		return directory;
	}

	/**
	 * Saves all Faculty in the directory to a file.
	 * 
	 * @param fileName name of file to save Faculty to
	 * @throws IllegalArgumentException if file is unable to be written to
	 */
	public void saveFacultyDirectory(String fileName) {
		try {
			FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}

	/**
	 * Searches the Faculty in the FacultyDirectory and returns the Faculty with the
	 * matching Id. If no matching Faculty is found, null is returned.
	 * 
	 * @param id id of the desired Faculty
	 * @return Faculty object with matching id
	 */
	public Faculty getFacultyById(String id) {
		ListIterator<Faculty> iter = facultyDirectory.listIterator(0);
		while (iter.hasNext()) {
			Faculty f = iter.next();
			if (f.getId().equals(id)) {
				return f;
			}
		}
		return null;
	}
}