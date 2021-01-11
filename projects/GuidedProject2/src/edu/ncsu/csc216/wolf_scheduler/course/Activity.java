package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Abstract super class that represents a single Activity and has getters and
 * setters for title, meeting days, start time, and end time. Also has abstract
 * methods to provide information to the GUI and to check whether or not an
 * Activity is a duplicate.
 * 
 * @author Selena Chen
 */
public abstract class Activity {

	/** Constant containing the upper military time */
	private static final int UPPER_TIME = 2400;
	/** Constant containing the upper value for an hour */
	private static final int UPPER_HOUR = 60;
	/** Course's title */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;

	/**
	 * Constructs an Activity object with values for all fields.
	 * 
	 * @param title       title of Activity
	 * @param meetingDays meeting days for Activity as series of chars
	 * @param startTime   start time for Activity
	 * @param endTime     end time for Activity
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		setTitle(title);
		setMeetingDays(meetingDays);
		setActivityTime(startTime, endTime);
	}

	/**
	 * Returns the Activity's title.
	 * 
	 * @return title of Activity
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Activity's title.
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException if title is null or an empty string
	 */
	public void setTitle(String title) {
		if (title == null || title.equals("")) {
			throw new IllegalArgumentException();
		}
		this.title = title;
	}

	/**
	 * Returns the Activity's meeting days.
	 * 
	 * @return meeting days for Activity as series of chars
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets the Activity's meeting days.
	 * 
	 * @param meetingDays the meetingDays to set
	 * @throws IllegalArgumentException if meetingDays string is empty or null
	 */
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.equals("")) {
			throw new IllegalArgumentException();
		}
		this.meetingDays = meetingDays;
	}

	/**
	 * Returns the Activity's starting time.
	 * 
	 * @return start time for Activity
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Activity's ending time.
	 * 
	 * @return end time for Activity
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the Activity's times.
	 * 
	 * @param startTime the start time for Activity to set
	 * @param endTime   the start time for Activity to set
	 * @throws IllegalArgumentException if invalid time
	 */
	public void setActivityTime(int startTime, int endTime) {
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
	 * in military format.
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
		final int lengthBefore10 = 5;
		final int lengthAfter9 = 6;
		if (meetingDays.equals("A")) {
			return "Arranged";
		}
		String start = "";
		if (startTime > notAfter12) {
			start += (startTime - militaryNoon) / hundredsPlace + ":" + (startTime - militaryNoon) % hundredsPlace
					+ "PM";
			if (start.length() == lengthBefore10 && start.charAt(first2) == '0') {
				start = start.substring(0, first2) + "00PM";
			} else if (start.length() == lengthAfter9 && start.indexOf(':') == first2 && start.charAt(first3) == '0') {
				start = start.substring(0, first3) + "00PM";
			}
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
			if (end.length() == lengthBefore10 && end.charAt(first2) == '0') {
				end = end.substring(0, first2) + "00PM";
			} else if (end.length() == lengthAfter9 && end.indexOf(':') == first2 && end.charAt(first3) == '0') {
				end = end.substring(0, first3) + "00PM";
			}
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
	 * Generates a hashCode for Activity using all fields.
	 * 
	 * @return hashCode for Activity
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
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
	 * Abstract method to provide a short version of the information to provide to
	 * the GUI.
	 * 
	 * @return short version of the information to provide to the GUI
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * Abstract method to provide a long version of the information to provide to
	 * the GUI.
	 * 
	 * @return long version of the information to provide to the GUI
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Abstract method to check to see if the Activity to be added is a duplicate of
	 * an Activity already in the schedule.
	 * 
	 * @param activity Activity being checked
	 * @return long version of the information to provide to the GUI
	 */
	public abstract boolean isDuplicate(Activity activity);
}