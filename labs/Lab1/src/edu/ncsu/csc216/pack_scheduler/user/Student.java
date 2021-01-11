package edu.ncsu.csc216.pack_scheduler.user;

/**
 * The Student class represents an individual student record. The Student class
 * is a plane old java object (POJO) consisting mostly of getters and setters
 * for first name, last time, id, email, password, and max credit hours. The
 * setters do have some complexity. Also has methods to provide information to
 * the GUI and to compare two students.
 * 
 * @author Sarah Heckman
 * @author Selena Chen
 * @author Travis Walter
 * @author Tyler Mathis
 */
public class Student implements Comparable<Student> {

	/** Maximum possible credits any student can have */
	public static final int MAX_CREDITS = 18;

	/** Student's first name */
	private String firstName;
	/** Student's last name */
	private String lastName;
	/** Student's unity id */
	private String id;
	/** Student's email */
	private String email;
	/** Student's password */
	private String password;
	/** Student's max credit hours */
	private int maxCredits;

	/**
	 * Constructs Student using all fields.
	 *
	 * @param firstName  Student's first name
	 * @param lastName   Student's last name
	 * @param id         Student's unity id
	 * @param email      Student's email
	 * @param password   Student's password
	 * @param maxCredits Student's max credit hours
	 */
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
		setMaxCredits(maxCredits);
	}

	/**
	 * Constructs Student using all but the maxCredits field, with default max
	 * credits value of 18.
	 *
	 * @param firstName Student's first name
	 * @param lastName  Student's last name
	 * @param id        Student's unity id
	 * @param email     Student's email
	 * @param password  Student's password
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName, lastName, id, email, password, MAX_CREDITS);
	}

	/**
	 * Returns the Student's first name.
	 * 
	 * @return Student's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the Student's first name.
	 * 
	 * @param firstName Student's first name
	 * @throws IllegalArgumentException if the parameter is null or an empty string
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || firstName.length() == 0) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Returns the Student's last name.
	 * 
	 * @return Student's last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the Student's last name.
	 * 
	 * @param lastName Student's last name
	 * @throws IllegalArgumentException if the parameter is null or an empty string
	 */
	public void setLastName(String lastName) {
		if (lastName == null || lastName.length() == 0) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * Returns the Student's unity id.
	 * 
	 * @return Student's unity id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the Student's unity id.
	 * 
	 * @param id Student's unity id
	 * @throws IllegalArgumentException if the parameter is null or an empty string
	 */
	private void setId(String id) {
		if (id == null || id.length() == 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * Returns the Student's email.
	 * 
	 * @return Student's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the Student's email.
	 * 
	 * @param email Student's email
	 * @throws IllegalArgumentException if the parameter is null or an empty string,
	 *                                  email doesn’t contain an ‘@’ character,
	 *                                  email doesn’t contain a ‘.’ character, or
	 *                                  the index of the last ‘.’ character in the
	 *                                  email string is earlier than the index of
	 *                                  the first ‘@’ character
	 */
	public void setEmail(String email) {
		if (email == null || email.length() == 0 || !email.contains("@") || !email.contains(".")
				|| email.lastIndexOf('.') < email.lastIndexOf('@')) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * Returns the Student's password.
	 * 
	 * @return Student's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the Student's password.
	 * 
	 * @param password Student's password
	 * @throws IllegalArgumentException if the parameter is null or an empty string
	 */
	public void setPassword(String password) {
		if (password == null || password.length() == 0) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = password;
	}

	/**
	 * Returns the Student's max credit hours.
	 * 
	 * @return Student's max credit hours
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the Student's max credit hours.
	 * 
	 * @param maxCredits Student's max credit hours
	 * @throws IllegalArgumentException if the parameter is less than 3 or greater
	 *                                  than 18
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 3 || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}

	/**
	 * Generates a hashCode for the Student using all fields.
	 * 
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + MAX_CREDITS;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + maxCredits;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this object for equality on all fields.
	 * 
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (maxCredits != other.maxCredits)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	/**
	 * Returns a string containing the fields as a comma separated line.
	 * 
	 * @return string containing the fields as a comma separated line
	 */
	@Override
	public String toString() {
		return firstName + "," + lastName + "," + id + "," + email + "," + password + "," + maxCredits;
	}

	/**
	 * Compares two students and determines where they go alphabetically.
	 * 
	 * @param s The the student being compared
	 * @return 1 if student one comes after student two, -1 if student one comes
	 *         before student two, and 0 if student one and two are the same
	 */
	@Override
	public int compareTo(Student s) {
		if (lastName.compareTo(s.getLastName()) > 0) {
			return 1;
		} else if (lastName.compareTo(s.getLastName()) < 0) {
			return -1;
		} else {
			if (firstName.compareTo(s.getFirstName()) > 0) {
				return 1;
			} else if (firstName.compareTo(s.getFirstName()) < 0) {
				return -1;
			} else {
				if (id.compareTo(s.getId()) > 0) {
					return 1;
				} else if (id.compareTo(s.getId()) < 0) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}
}