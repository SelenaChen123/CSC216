package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Represents a custom checked exception specifically for conflicts. Has two
 * constructors that specify different messages.
 * 
 * @author Selena Chen
 */
public class ConflictException extends Exception {

	/** ID used for serialization */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a ConflictException object that has a String parameter specifying
	 * a message for the Exception.
	 * 
	 * @param message text to be passed to the parent's constructor
	 */
	public ConflictException(String message) {
		super(message);
	}

	/**
	 * Constructs a ConflictException object without parameters, but calls the other
	 * ConflictException constructor with a default message.
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}
}