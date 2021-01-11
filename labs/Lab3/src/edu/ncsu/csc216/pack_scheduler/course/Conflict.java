package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Interface that checks for conflicting activities. Defines an abstract method
 * for checking conflicts.
 * 
 * @author Selena Chen
 * @author Travis Walter
 * @author Tyler Mathis
 */
public interface Conflict {

	/**
	 * Checks whether or not there is a conflict with an Activity and throws a
	 * custom ConflictException if the activities conflict.
	 * 
	 * @param possibleConflictingActivity Activity being checked
	 * @throws ConflictException if there is a conflict
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
}