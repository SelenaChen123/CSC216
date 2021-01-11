package edu.ncsu.csc216.travel.model.office;

/**
 * Exception thrown when there is an attempt to add a duplicate tour to the list
 * of tours.
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public class DuplicateTourException extends Exception {

	/** ID used for serialization */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a DuplicateTourException object with a default message.
	 */
	public DuplicateTourException() {
		super("Duplicate tour");
	}

	/**
	 * Constructs a DuplicateTourException object with a specified message.
	 * 
	 * @param message Specified message
	 */
	public DuplicateTourException(String message) {
		super(message);
	}
}