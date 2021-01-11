package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Represents a single Event in the WolfScheduler system. Event is an extension
 * of the Activity class. In addition to the functionality of Activity, Event
 * has getters and setters for the weekly repeat and event details. Also has
 * methods to provide information to the GUI and to check whether or not an
 * Event is a duplicate.
 * 
 * @author Selena Chen
 */
public class Event extends Activity {

	/** Event's weekly repeat */
	private int weeklyRepeat;
	/** Event's details */
	private String eventDetails;

	/**
	 * Constructs an Event object with values for all fields.
	 * 
	 * @param title        title of Event
	 * @param meetingDays  meeting days for Event as series of chars
	 * @param startTime    start time for Event
	 * @param endTime      end time for Event
	 * @param weeklyRepeat weekly repeat of Event
	 * @param eventDetails event details of Event
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, int weeklyRepeat, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setWeeklyRepeat(weeklyRepeat);
		setEventDetails(eventDetails);
	}

	/**
	 * Returns the Event's weekly repeat.
	 * 
	 * @return Event's weekly repeat
	 */
	public int getWeeklyRepeat() {
		return weeklyRepeat;
	}

	/**
	 * Sets the Event's weekly repeat. Throws an IllegalArgumentException if the
	 * weeklyRepeat parameter is less than one or greater than 4.
	 * 
	 * @param weeklyRepeat the weeklyRepeat to set
	 * @throws IllegalArgumentException if the weeklyRepeat parameter is less than
	 *                                  one or greater than 4
	 */
	public void setWeeklyRepeat(int weeklyRepeat) {
		if (weeklyRepeat < 1 || weeklyRepeat > 4) {
			throw new IllegalArgumentException("Invalid weekly repeat");
		}
		this.weeklyRepeat = weeklyRepeat;
	}

	/**
	 * Returns the Event's details.
	 * 
	 * @return the Event's details
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/**
	 * Sets the Event's details. Throws an IllegalArgumentException if the
	 * eventDetails parameter is null.
	 * 
	 * @param eventDetails the eventDetails to set
	 * @throws IllegalArgumentException if the eventDetails parameter is null
	 */
	public void setEventDetails(String eventDetails) {
		if (eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details");
		}
		this.eventDetails = eventDetails;
	}

	/**
	 * Sets the Event's meeting days.
	 * 
	 * @param meetingDays meeting days for Event as series of chars
	 * @throws IllegalArgumentException if meetingDays string is null or an empty
	 *                                  string, or if any character other than 'U',
	 *                                  'M', 'T', 'W', 'H', 'F', or 'S' is present
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.equals("")) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < meetingDays.length(); i++) {
			if (meetingDays.charAt(i) != 'U' && meetingDays.charAt(i) != 'M' && meetingDays.charAt(i) != 'T'
					&& meetingDays.charAt(i) != 'W' && meetingDays.charAt(i) != 'H' && meetingDays.charAt(i) != 'F'
					&& meetingDays.charAt(i) != 'S') {
				throw new IllegalArgumentException();
			}
		}
		super.setMeetingDays(meetingDays);
	}

	/**
	 * Returns an array of length 4. The first two values are empty strings since
	 * Event doesn’t have a name or section. The last two values are the title and
	 * the meeting string.
	 * 
	 * @return array containing empty strings along with the Event title and meeting
	 *         days string
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] display = { "", "", getTitle(), getMeetingString() };
		return display;
	}

	/**
	 * Returns a String array of length seven. The first two values are empty
	 * strings since Event doesn’t have a name or section. The third value is the
	 * title followed by two values with empty strings. The last two are the meeting
	 * string and eventDetails.
	 * 
	 * @return array containing empty strings along with the Event title, meeting
	 *         days string, and event details
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] display = { "", "", getTitle(), "", "", getMeetingString(), getEventDetails() };
		return display;
	}

	/**
	 * Returns meeting information as a string in standard time format rather than
	 * in military format.
	 * 
	 * @return String representation of meeting information
	 */
	@Override
	public String getMeetingString() {
		return super.getMeetingString() + " (every " + weeklyRepeat + " weeks)";
	}

	/**
	 * Returns a comma separated value String of all Event fields.
	 * 
	 * @return String representation of Event
	 */
	@Override
	public String toString() {
		return getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime() + "," + weeklyRepeat
				+ "," + eventDetails;
	}

	/**
	 * Checks to see if the Event to be added is a duplicate of a Event already in
	 * the schedule.
	 * 
	 * @param activity Activity being checked
	 * @return true if the Event to be added is a duplicate of a Event already in
	 *         the schedule
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Event) {
			Event e = (Event) activity;
			return e.getTitle().equals(getTitle());
		} else {
			return false;
		}
	}
}