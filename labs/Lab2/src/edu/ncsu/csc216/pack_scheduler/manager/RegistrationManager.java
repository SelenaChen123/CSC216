package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * RegistrationManager handles administrative tasks for the PackScheduler role.
 * It hold instances of CourseCatalog as well as StudentDirectory for use by the
 * program. RegistrationManager itself is a singleton class so that changes to
 * any data is universal across the program.
 * 
 * @author Selena Chen
 * @author Justin Wald
 */
public class RegistrationManager {

	/** The instance of RegistrationManager held by itself */
	private static RegistrationManager instance;
	/** The catalog of all courses */
	private CourseCatalog courseCatalog;
	/** A list of all students in the system */
	private StudentDirectory studentDirectory;
	/** The user responsible for configuring the program */
	private User registrar;
	/** The current user of the program */
	private User currentUser;

	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	private static final String PROP_FILE = "registrar.properties";

	/**
	 * Creates the RegistrationManager by creating the registrar, loading in a blank
	 * CourseCatalog, and a blank StudentDirectory.
	 */
	private RegistrationManager() {
		createRegistrar();
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
	}

	/**
	 * Creates the registrar by loading in the properties file and creating a
	 * registrar user with the same information.
	 * 
	 * @throws IllegalArgumentException if registrar cannot be created due to an
	 *                                  issue reading the properties file
	 */
	private void createRegistrar() {
		Properties prop = new Properties();
		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);
			String hashPW = hashPW(prop.getProperty("pw"));
			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"),
					prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}

	/**
	 * Hashes a password using an algorithm for secure storage.
	 * 
	 * @param pw password to hash
	 * @return String containing the hashed password
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	/**
	 * Creates the single instance of RegistrationManager. If an instance already
	 * exists, nothing is changed and the instance is returned.
	 * 
	 * @return single instance of RegistrationManager
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}

	/**
	 * Returns the CourseCatalog field for use in other classes.
	 * 
	 * @return CourseCatalog currently held by RegistrationManager
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/**
	 * Returns the StudentDirectory field for use in other classes.
	 * 
	 * @return RegistrationManager's version of StudentDirectory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * Checks to see if the hashed version of the password passed to this method
	 * matches the hashed password of the student whose id is passed. If the passed
	 * id and password match that of the registrar, that user becomes the registrar.
	 * 
	 * @param id       id of the student to check against
	 * @param password password to test
	 * @return true if login is successful, and false if the password doesn't match
	 * @throws IllegalArgumentException if there is an error hashing the passwords
	 */
	public boolean login(String id, String password) throws IllegalArgumentException {
		if (currentUser != null) {
			return false;
		}
		if (registrar.getId().equals(id)) {
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (registrar.getPassword().equals(localHashPW)) {
					currentUser = registrar;
					return true;
				}
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}
		}
		Student s = studentDirectory.getStudentById(id);
		try {
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			String localHashPW = new String(digest.digest());
			if (s.getPassword().equals(localHashPW)) {
				currentUser = s;
				return true;
			}
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException();
		} catch (NullPointerException e) {
			throw new IllegalArgumentException("User doesn't exist.");
		}
		return false;
	}

	/**
	 * Logs the current user off.
	 */
	public void logout() {
		currentUser = null;
	}

	/**
	 * Returns the current user as a User object.
	 * 
	 * @return current user as a User object
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Clears the current catalog and student directory.
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
	}

	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * 
	 * @param c Course to enroll in
	 * @return true if enrolled, false otherwise
	 */
	public boolean enrollStudentInCourse(Course c) {
		if (currentUser == null || !(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			CourseRoll roll = c.getCourseRoll();

			if (s.canAdd(c) && roll.canEnroll(s)) {
				schedule.addCourseToSchedule(c);
				roll.enroll(s);
				return true;
			}

		} catch (IllegalArgumentException e) {
			return false;
		}
		return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * 
	 * @param c Course to drop
	 * @return true if dropped, false otherwise
	 */
	public boolean dropStudentFromCourse(Course c) {
		if (currentUser == null || !(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			c.getCourseRoll().drop(s);
			return s.getSchedule().removeCourseFromSchedule(c);
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	/**
	 * Resets the logged in student's schedule by dropping them from every course
	 * and then resetting the schedule.
	 */
	public void resetSchedule() {
		if (currentUser == null || !(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			String[][] scheduleArray = schedule.getScheduledCourses();
			for (int i = 0; i < scheduleArray.length; i++) {
				Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
				c.getCourseRoll().drop(s);
			}
			schedule.resetSchedule();
		} catch (IllegalArgumentException e) {
			// do nothing
		}
	}

	/**
	 * This class represents the registrar of PackScheduler. This user is
	 * responsible for loading in the CourseCatalog and StudentDirectory, as well as
	 * making any changes to either.
	 * 
	 * @author Selena Chen
	 * @author Justin Wald
	 */
	private static class Registrar extends User {

		/**
		 * Create a registrar user with the user id and password in the
		 * registrar.properties file.
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
}