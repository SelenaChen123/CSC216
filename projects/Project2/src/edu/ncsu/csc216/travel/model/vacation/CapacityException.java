package edu.ncsu.csc216.travel.model.vacation;

/**
 * Exception thrown when there is an attempt to create a reservation that would
 * fill a tour over its capacity.
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public class CapacityException extends Exception {

	/** ID used for serialization */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a CapacityException object with a default message.
	 */
	public CapacityException() {
		this("Capacity exception");
	}

	/**
	 * Constructs a CapacityException object with a specified message.
	 * 
	 * @param message Specified message
	 */
	public CapacityException(String message) {
		super(message);
	}
}