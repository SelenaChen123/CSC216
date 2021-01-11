package edu.ncsu.csc216.travel.model.office;

/**
 * Exception thrown when there is an attempt to add a duplicate client to the
 * list of clients.
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public class DuplicateClientException extends Exception {

	/** ID used for serialization */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a DuplicateClientException object with a default message.
	 */
	public DuplicateClientException() {
		this("Duplicate client");
	}

	/**
	 * Constructs a DuplicateClientException object with a specified message.
	 * 
	 * @param message Specified message
	 */
	public DuplicateClientException(String message) {
		super(message);
	}
}