package edu.ncsu.csc216.pack_scheduler.user;

/**
 * This class represents a user of the PackScheduler system. A user has a first
 * name, last name, id, email, and password.
 * 
 * @author Selena Chen
 * @author Justin Wald
 */
public abstract class User {

	/** User's first name */
	private String firstName;
	/** User's last name */
	private String lastName;
	/** User's unity id */
	private String id;
	/** User's email */
	private String email;
	/** User's password */
	private String password;

	/**
	 * Constructs a new user when passed the new user's valid information. Sets
	 * fields by calling associated setters, which also handle data validation.
	 * 
	 * @param firstName User's first name
	 * @param lastName  User's last name
	 * @param id        User's id
	 * @param email     User's email
	 * @param password  User's password, in plain text
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
	}

	/**
	 * Returns the User's first name.
	 * 
	 * @return User's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the User's first name.
	 * 
	 * @param firstName User's first name
	 * @throws IllegalArgumentException if the parameter is null or an empty string
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || firstName.length() == 0) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Returns the User's last name.
	 * 
	 * @return User's last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the User's last name.
	 * 
	 * @param lastName User's last name
	 * @throws IllegalArgumentException if the parameter is null or an empty string
	 */
	public void setLastName(String lastName) {
		if (lastName == null || lastName.length() == 0) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * Returns the User's unity id.
	 * 
	 * @return User's unity id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the User's unity id.
	 * 
	 * @param id User's unity id
	 * @throws IllegalArgumentException if the parameter is null or an empty string
	 */
	protected void setId(String id) {
		if (id == null || id.length() == 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * Returns the User's email.
	 * 
	 * @return User's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the User's email.
	 * 
	 * @param email User's email
	 * @throws IllegalArgumentException if the parameter is null or an empty string,
	 *                                  email doesn't contain an @ character, email
	 *                                  doesn't contain a . character, or the index
	 *                                  of the last . character in the email string
	 *                                  is earlier than the index of the first @
	 *                                  character
	 */
	public void setEmail(String email) {
		if (email == null || email.length() == 0 || !email.contains("@") || !email.contains(".")
				|| email.lastIndexOf('.') < email.lastIndexOf('@')) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * Returns the User's password.
	 * 
	 * @return User's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the User's password.
	 * 
	 * @param password User's password
	 * @throws IllegalArgumentException if the parameter is null or an empty string
	 */
	public void setPassword(String password) {
		if (password == null || password.length() == 0) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = password;
	}

	/**
	 * Generates a hashCode for User using all fields.
	 * 
	 * @return hashCode for User
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this object for equality on all fields.
	 * 
	 * @param obj the Object to compare
	 * @return true if all objects are the same on all fields
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
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
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
}