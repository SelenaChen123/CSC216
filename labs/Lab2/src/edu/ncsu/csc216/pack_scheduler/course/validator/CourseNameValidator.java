package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Class responsible for testing the validity of a course name. Implements an
 * inner class state pattern. Has inner classes for State, InitialState,
 * LetterState, NumberState, and SuffixState.
 * 
 * @author Justin Wald
 * @author Selena Chen
 */
public class CourseNameValidator {

	/** Boolean to represent whether a certain state is valid */
	private boolean validEndState;

	/** Keeps track of the number of letters in the Course name being tested for validity */
	private int letterCount;

	/** Keeps track of the number of digits in the Course name being tested for validity */
	private int digitCount;

	/** Starting state of the FSM */
	private State stateInitial;

	/** State in which the FSM is currently on a number */
	private State stateNumber;

	/** State in which the FSM is currently on a letter */
	private State stateLetter;

	/** State in which the FSM is currently on a letter suffix */
	private State stateSuffix;

	/** Current state of the FSM */
	private State currentState;

	/**
	 * Constructs a CourseNameValidator object by constructing all of the states.
	 */
	public CourseNameValidator() {
		stateInitial = new InitialState();
		stateNumber = new NumberState();
		stateLetter = new LetterState();
		stateSuffix = new SuffixState();
	}

	/**
	 * Tests whether the passed String represents a valid Course name using an FSM
	 * design.
	 * 
	 * @param course String that may represent a Course name
	 * @return True if text represents a valid Course name, false otherwise
	 * @throws InvalidTransitionException if a transition is not valid in the
	 *                                    context of the current state
	 */
	public boolean isValid(String course) throws InvalidTransitionException {
		currentState = stateInitial;
		letterCount = 0;
		digitCount = 0;
		validEndState = false;
		if (course == null || course.isEmpty()) {
			return false;
		}
		for (int i = 0; i < course.length(); i++) {
			char character = course.charAt(i);
			if (Character.isLetter(character)) {
				currentState.onLetter();
			} else if (Character.isDigit(character)) {
				currentState.onDigit();
			} else {
				currentState.onOther();
			}
		}
		return validEndState;
	}

	/**
	 * Abstract class that represents the state of the FSM.
	 * 
	 * @author Justin Wald
	 * @author Selena Chen
	 */
	public abstract class State {

		/**
		 * Constructs a State object.
		 */
		public State() {
			validEndState = false;
		}

		/**
		 * Method to be called when the next character in the String is a letter
		 * 
		 * @throws InvalidTransitionException if the letter is an invalid transition
		 */
		public abstract void onLetter() throws InvalidTransitionException;

		/**
		 * Method to be called when the next character in the String is a digit
		 * 
		 * @throws InvalidTransitionException if the digit is an invalid transition
		 */
		public abstract void onDigit() throws InvalidTransitionException;

		/**
		 * Method to be called when the next character in the String is not a letter or
		 * a digit.
		 * 
		 * @throws InvalidTransitionException if called
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}

	/**
	 * Initial state of the FSM.
	 * 
	 * @author Justin Wald
	 * @author Selena Chen
	 */
	public class InitialState extends State {

		/**
		 * Constructs an InitialState object.
		 */
		private InitialState() {
			validEndState = false;
		}

		/**
		 * {@inheritDoc} Increments the letter counter and transitions to the letter
		 * state.
		 */
		@Override
		public void onLetter() {
			letterCount++;
			currentState = stateLetter;
		}

		/**
		 * {@inheritDoc} Throws an error since Course names can't start with a digit.
		 * 
		 * @throws InvalidTransitionException if called
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
	}

	/**
	 * State of the FSM when on a letter.
	 * 
	 * @author Justin Wald
	 * @author Selena Chen
	 */
	public class LetterState extends State {

		/** Max number of prefix letters in a course name */
		private static final int MAX_PREFIX_LETTERS = 4;

		/**
		 * Constructs a LetterState object.
		 */
		private LetterState() {
			validEndState = false;
		}

		/**
		 * {@inheritDoc} Increments the letter counter and ensures that number of
		 * letters doesn't exceed 4.
		 * 
		 * @throws InvalidTransitionException if the number of letters exceeds 4
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			letterCount++;
			if (letterCount <= MAX_PREFIX_LETTERS) {
				currentState = stateLetter;
			} else {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
		}

		/**
		 * {@inheritDoc} Increments the digit counter and transitions to the number
		 * state.
		 */
		@Override
		public void onDigit() {
			digitCount++;
			currentState = stateNumber;
		}
	}

	/**
	 * State of the FSM when on a digit.
	 * 
	 * @author Justin Wald
	 * @author Selena Chen
	 */
	public class NumberState extends State {

		/** Number of digits in a course name */
		private static final int COURSE_NUMBER_LENGTH = 3;

		/**
		 * Constructs a NumberState object.
		 */
		private NumberState() {
			validEndState = false;
		}

		/**
		 * {@inheritDoc} Increments the letter counter and ensures that there are
		 * already 3 digits. Transitions to suffix state and it is now a valid end
		 * state.
		 * 
		 * @throws InvalidTransitionException if the digit counter is not 3, since all
		 *                                    course names must have 3 digits
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			letterCount++;
			if (digitCount == COURSE_NUMBER_LENGTH) {
				validEndState = true;
				currentState = stateSuffix;
			} else {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
		}

		/**
		 * {@inheritDoc} Increments the digit counter and ensures that the digit counter
		 * will not exceed 3. If the digit counter is 3, it is now a valid end state.
		 * 
		 * @throws InvalidTransitionException if a fourth digit is found
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			digitCount++;
			if (digitCount == COURSE_NUMBER_LENGTH) {
				validEndState = true;
			} else if (digitCount > COURSE_NUMBER_LENGTH) {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
		}
	}

	/**
	 * State of the FSM when on a letter suffix.
	 * 
	 * @author Justin Wald
	 * @author Selena Chen
	 */
	public class SuffixState extends State {

		/**
		 * Constructs a SuffixState object.
		 */
		private SuffixState() {
			validEndState = true;
		}

		/**
		 * {@inheritDoc} Throws an error since the suffix can only be a single letter.
		 * 
		 * @throws InvalidTransitionException if called
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			validEndState = false;
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
		}

		/**
		 * {@inheritDoc} Throws an error since the suffix can only be a single letter.
		 * 
		 * @throws InvalidTransitionException if called
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			validEndState = false;
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}
	}
}