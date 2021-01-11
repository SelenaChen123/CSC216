package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Represents a single Course in the WolfScheduler system. Course is an
 * extension of the Activity class. In addition to the functionality of
 * Activity, Course has added getters and setters for the Course name, section,
 * credits, and instructor id. Also has methods to provide information to the
 * GUI and to check whether or not a Course is a duplicate.
 * 
 * @author Selena Chen
 * @author Travis Walter
 * @author Tyler Mathis
 * @author Justin Wald
 */
public class Course extends Activity implements Comparable<Course> {

	/** Constant containing the length of the section String */
	private static final int SECTION_LENGTH = 3;
	/** Constant containing the maximum number of credits for a course */
	private static final int MAX_CREDITS = 5;
	/** Constant containing the minimum number of credits for a course */
	private static final int MIN_CREDITS = 1;
	/** Course's name */
	private String name;
	/** Course's section */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Tests the validity of a Course name */
	private CourseNameValidator c = new CourseNameValidator();
	/** Roll of students taking Course */
	private CourseRoll roll;

	/**
	 * Constructs a Course object with values for all fields.
	 * 
	 * @param name          name of Course
	 * @param title         title of Course
	 * @param section       section of Course
	 * @param credits       credit hours for Course
	 * @param instructorId  instructor's unity id
	 * @param enrollmentCap capacity of the Course
	 * @param meetingDays   meeting days for Course as series of chars
	 * @param startTime     start time for Course
	 * @param endTime       end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays, int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		roll = new CourseRoll(enrollmentCap);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged.
	 * 
	 * @param name          name of Course
	 * @param title         title of Course
	 * @param section       section of Course
	 * @param credits       credit hours for Course
	 * @param instructorId  instructor's unity id
	 * @param enrollmentCap capacity of the course
	 * @param meetingDays   meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name.
	 * 
	 * @return name of Course
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. If the name is null, has a length less than 4 or
	 * greater than 6, an IllegalArgumentException is thrown.
	 * 
	 * @param name the name to set
	 * @throws IllegalArgumentException if name is null or length is less than 4 or
	 *                                  greater than 8
	 */
	private void setName(String name) {
		try {
			if (c.isValid(name)) {
				this.name = name;
			} else {
				throw new IllegalArgumentException();
			}
		} catch (InvalidTransitionException ite) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Returns the Course's section.
	 * 
	 * @return section of Course
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section.
	 * 
	 * @param section the section to set
	 * @throws IllegalArgumentException if section if null or an empty string, or if
	 *                                  the section is not a series of numbers of
	 *                                  length 3
	 */
	public void setSection(String section) {
		if (section == null || section.equals("")) {
			throw new IllegalArgumentException();
		}
		if (section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < section.length(); i++) {
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException();
			}
		}
		this.section = section;
	}

	/**
	 * Returns the Course's credit hours.
	 * 
	 * @return credit hours for Course
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's credit hours.
	 * 
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if credits is less than 1 or greater than 5
	 */
	public void setCredits(int credits) {
		try {
			if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
				throw new IllegalArgumentException();
			}
		} catch (NumberFormatException e) {
			// Not an int
		}
		this.credits = credits;
	}

	/**
	 * Returns the Course's instructor.
	 * 
	 * @return instructor's unity id
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's instructor.
	 * 
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if the instructorId is null or an empty
	 *                                  string
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || instructorId.equals("")) {
			throw new IllegalArgumentException();
		}
		this.instructorId = instructorId;
	}

	/**
	 * Sets the Course's meeting days.
	 * 
	 * @param meetingDays meeting days for Course as series of chars
	 * @throws IllegalArgumentException if meetingDays string is null or an empty
	 *                                  string, it contains 'A' but 'A' isn't the
	 *                                  only character, or if any character other
	 *                                  than 'M', 'T', 'W', 'H', 'F', or 'A' is
	 *                                  present
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.equals("")) {
			throw new IllegalArgumentException();
		}
		if (meetingDays.contains("A") && meetingDays.length() != 1) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < meetingDays.length(); i++) {
			if (meetingDays.charAt(i) != 'M' && meetingDays.charAt(i) != 'T' && meetingDays.charAt(i) != 'W'
					&& meetingDays.charAt(i) != 'H' && meetingDays.charAt(i) != 'F' && meetingDays.charAt(i) != 'A') {
				throw new IllegalArgumentException();
			}
		}
		super.setMeetingDays(meetingDays);
	}

	/**
	 * Returns the roll of students taking Course.
	 * 
	 * @return CourseRoll containing all student enrolled in Course
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if (getMeetingDays().equals("A")) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
					+ roll.getEnrollmentCap() + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
				+ roll.getEnrollmentCap() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime();
	}

	/**
	 * Generates a hashCode for Course using all fields.
	 * 
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Returns an array of length 4 containing the Course name, section, title, and
	 * meeting days string.
	 * 
	 * @return array containing the Course name, section, title, and meeting days
	 *         string
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] display = { name, section, getTitle(), getMeetingString(), Integer.toString(roll.getOpenSeats()) };
		return display;
	}

	/**
	 * Returns an array of length 7 containing the Course name, section, title,
	 * credits, instructorId, meeting days string, empty string (for a field that
	 * Event will have that Course does not).
	 * 
	 * @return array containing the Course name, section, title, credits,
	 *         instructorId, meeting days string, empty string
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] display = { name, section, getTitle(), "" + getCredits(), getInstructorId(), getMeetingString(), "" };
		return display;
	}

	/**
	 * Checks to see if the Course to be added is a duplicate of a Course already in
	 * the schedule.
	 * 
	 * @param activity Activity being checked
	 * @return true if the Course to be added is a duplicate of a Course already in
	 *         the schedule, false otherwise
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course course = (Course) activity;
			return course.getTitle().equals(getTitle());
		} else {
			return false;
		}
	}

	/**
	 * Compares two courses and determines where they go alphabetically.
	 * 
	 * @param o The course being compared
	 * @return 1 if course one comes after course two, -1 if course one comes before
	 *         course two, and 0 if course one and two are the same
	 */
	@Override
	public int compareTo(Course o) {
		if (name.compareTo(o.getName()) > 0) {
			return 1;
		} else if (name.compareTo(o.getName()) < 0) {
			return -1;
		} else {
			if (section.compareTo(o.getSection()) > 0) {
				return 1;
			} else if (section.compareTo(o.getSection()) < 0) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}