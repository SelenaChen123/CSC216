package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Checked exception to be thrown when an invalid transition is attempted within
 * an FSM.
 * 
 * @author Justin Wald
 * @author Selena Chen
 */
public class InvalidTransitionException extends Exception {

	/** ID used for serialization */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an InvalidTransitionException with a default message.
	 */
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}

	/**
	 * Constructs an InvalidTransitionException with a given message.
	 * 
	 * @param m Message to be displayed
	 */
	public InvalidTransitionException(String m) {
		super(m);
	}
}