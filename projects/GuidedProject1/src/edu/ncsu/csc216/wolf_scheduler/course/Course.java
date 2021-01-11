package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Represents a single Course in the WolfScheduler system
 * 
 * @author Selena Chen
 */
public class Course {

	/** Constant containing the length of the section String */
	private static final int SECTION_LENGTH = 3;
	/** Constant containing the maximum length of the name String */
	private static final int MAX_NAME_LENGTH = 6;
	/** Constant containing the minimum length of the name String */
	private static final int MIN_NAME_LENGTH = 4;
	/** Constant containing the maximum number of credits for a course */
	private static final int MAX_CREDITS = 5;
	/** Constant containing the minimum number of credits for a course */
	private static final int MIN_CREDITS = 1;
	/** Constant containing the upper military time */
	private static final int UPPER_TIME = 2400;
	/** Constant containing the upper value for an hour */
	private static final int UPPER_HOUR = 60;

	/** Course's name. */
	private String name;
	/** Course's title. */
	private String title;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;

	/**
	 * Constructs a Course object with values for all fields.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays  meeting days for Course as series of chars
	 * @param startTime    start time for Course
	 * @param endTime      end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		setName(name);
		setTitle(title);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		setMeetingDays(meetingDays);
		setCourseTime(startTime, endTime);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays  meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
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
	 *                                  greater than 6
	 */
	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException();
		}
		this.name = name;
	}

	/**
	 * Returns the Course's title.
	 * 
	 * @return title of Course
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course's title.
	 * 
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		if (title == null || title.equals("")) {
			throw new IllegalArgumentException();
		}
		this.title = title;
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
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || instructorId.equals("")) {
			throw new IllegalArgumentException();
		}
		this.instructorId = instructorId;
	}

	/**
	 * Returns the Course's meeting days.
	 * 
	 * @return meeting days for Course as series of chars
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets the Course's meeting days.
	 * 
	 * @param meetingDays the meetingDays to set
	 */
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
		this.meetingDays = meetingDays;
	}

	/**
	 * Returns the Course's starting time.
	 * 
	 * @return start time for Course
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Course's ending time.
	 * 
	 * @return end time for Course
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the Course's times.
	 * 
	 * @param startTime the start time for Course to set
	 * @param endTime   the start time for Course to set
	 */
	public void setCourseTime(int startTime, int endTime) {
		if (meetingDays.equals("A") && startTime != 0 && endTime != 0) {
			throw new IllegalArgumentException();
		}
		if (startTime < 0 || startTime >= UPPER_TIME || endTime < 0 || endTime >= UPPER_TIME) {
			throw new IllegalArgumentException();
		}
		if (startTime % 100 >= UPPER_HOUR || endTime % 100 >= UPPER_HOUR) {
			throw new IllegalArgumentException();
		}
		if (endTime < startTime) {
			throw new IllegalArgumentException();
		}
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Returns meeting information as a string in standard time format rather than
	 * in military format
	 * 
	 * @return String representation of meeting information
	 */
	public String getMeetingString() {
		final int notAfter12 = 1259;
		final int militaryNoon = 1200;
		final int hundredsPlace = 100;
		final int noon = 12;
		final int maxDigit = 9;
		final int first2 = 2;
		final int first3 = 3;
		if (meetingDays.equals("A")) {
			return "Arranged";
		}
		String start = "";
		if (startTime > notAfter12) {
			start += (startTime - militaryNoon) / hundredsPlace + ":" + (startTime - militaryNoon) % hundredsPlace
					+ "PM";
		} else {
			start += startTime / hundredsPlace + ":" + startTime % hundredsPlace;
			if (startTime % hundredsPlace == 0) {
				start += "0";
			} else if (startTime % hundredsPlace < 0) {
				if (startTime / hundredsPlace <= maxDigit) {
					start = start.substring(0, first2) + "0" + start.substring(first2);
				} else if (startTime / hundredsPlace > maxDigit) {
					start = start.substring(0, first3) + "0" + start.substring(first3);
				}
			}
			if (startTime / hundredsPlace == noon) {
				start += "PM";
			} else {
				start += "AM";
			}
		}
		String end = "";
		if (endTime > notAfter12) {
			end += (endTime - militaryNoon) / hundredsPlace + ":" + (endTime - militaryNoon) % hundredsPlace + "PM";
		} else {
			end += endTime / hundredsPlace + ":" + endTime % hundredsPlace;
			if (endTime % hundredsPlace == 0) {
				end += "0";
			} else if (endTime % hundredsPlace < 0) {
				if (endTime / hundredsPlace <= maxDigit) {
					end = end.substring(0, first2) + "0" + end.substring(first2);
				} else if (endTime / hundredsPlace > maxDigit) {
					end = end.substring(0, first3) + "0" + end.substring(first3);
				}
			}
			if (endTime / hundredsPlace == noon) {
				end += "PM";
			} else {
				end += "AM";
			}
		}
		return meetingDays + " " + start + "-" + end;
	}

	/**
	 * Generates a hashCode for Course using all fields.
	 * 
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + credits;
		result = prime * result + endTime;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this object for equality on all fields.
	 * 
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (endTime != other.endTime)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
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
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if (meetingDays.equals("A")) {
			return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + meetingDays;
		}
		return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + meetingDays + ","
				+ startTime + "," + endTime;
	}

	/**
	 * Starts the program
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		// Auto-generated
	}
}