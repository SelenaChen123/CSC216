package edu.ncsu.csc216.travel.model.vacation;

import java.time.LocalDate;

import edu.ncsu.csc216.travel.list_utils.SimpleArrayList;
import edu.ncsu.csc216.travel.model.participants.Client;

/**
 * An abstract class representing a Wolf Travel tour. Every tour maintains a
 * list of reservations for that tour. Has getters for each of its name, start
 * date, duration, capacity, whether or not capacity is fixed, base price, and
 * list of reservations fields. fields. Also has methods to compare tours, set
 * the capacity, get the number of client reservations, get the number of spaces
 * left for the tour, fix the capacity of the tour, get the end date of the
 * tour, get a specific reservation, get a summary of the tour information,
 * create reservations, add reservations, and cancel reservations.
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public abstract class Tour implements Comparable<Tour> {

	/** Name of the Tour */
	private String name;
	/** Starting date of the Tour */
	private LocalDate start;
	/** Duration of the Tour */
	private int duration;
	/** Capacity of the Tour */
	private int capacity;
	/** Whether the capacity of the Tour is fixed or not */
	private boolean capacityFixed;
	/** Base price of the Tour */
	private int basePrice;
	/** Number of participants in the Tour */
	private int numParticipants;
	/** List of the Tour's Reservations */
	private SimpleArrayList<Reservation> res;

	/**
	 * Constructs a Tour object with a given name, start date, duration, base price,
	 * and capacity.
	 * 
	 * @param name      Name of the Tour
	 * @param start     Start date of the Tour
	 * @param duration  Duration of the Tour
	 * @param basePrice Base price of the Tour
	 * @param capacity  Capacity of the Tour
	 * @throws IllegalArgumentException If any parameters are not valid
	 */
	public Tour(String name, LocalDate start, int duration, int basePrice, int capacity) {
		if (name == null || name.trim().equals("") || name.contains(":")) {
			throw new IllegalArgumentException("Invalid name");
		} else if (start == null) {
			throw new IllegalArgumentException("Invalid start date");
		} else if (duration < 1) {
			throw new IllegalArgumentException("Invalid duration");
		} else if (basePrice < 1) {
			throw new IllegalArgumentException("Invalid base price");
		} else if (capacity < 1) {
			throw new IllegalArgumentException("Invalid capacity");
		}
		this.name = name.trim();
		this.start = start;
		this.duration = duration;
		this.basePrice = basePrice;
		this.capacity = capacity;
		numParticipants = 0;
		res = new SimpleArrayList<Reservation>();
	}

	/**
	 * Returns the number of Client Reservations the Tour currently has.
	 * 
	 * @return Number of Client Reservations the Tour currently has
	 */
	public int numberOfClientReservations() {
		return res.size();
	}

	/**
	 * Returns the number of spaces left in the Tour.
	 * 
	 * @return Number of spaces left in the Tour
	 */
	public int spacesLeft() {
		return capacity - numParticipants;
	}

	/**
	 * Returns the start date of the Tour.
	 * 
	 * @return Start date of the Tour
	 */
	public LocalDate getStartDate() {
		return start;
	}

	/**
	 * Returns the end date of the Tour.
	 * 
	 * @return End date of the Tour
	 */
	public LocalDate getEndDate() {
		return start.plusDays(duration - 1);
	}

	/**
	 * Returns the duration of the Tour.
	 * 
	 * @return Duration of the Tour
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Returns the base price of the Tour.
	 * 
	 * @return Base price of the Tour
	 */
	public int getBasePrice() {
		return basePrice;
	}

	/**
	 * Returns the name of the Tour.
	 * 
	 * @return Name of the Tour
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the capacity of the Tour.
	 * 
	 * @return Capacity of the Tour
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Fixes the capacity of the Tour.
	 */
	public void fixCapacity() {
		capacityFixed = true;
	}

	/**
	 * Returns whether or not the capacity of the Tour is fixed.
	 * 
	 * @return True if the capacity of the Tour is fixed, false otherwise
	 */
	public boolean isCapacityFixed() {
		return capacityFixed;
	}

	/**
	 * Sets the capacity of the Tour.
	 * 
	 * @param capacity Capacity of the Tour
	 * @throws CapacityException If the capacity cannot be set
	 */
	protected void setCapacity(int capacity) throws CapacityException {
		if (capacity < numParticipants || capacityFixed) {
			throw new CapacityException();
		}
		this.capacity = capacity;
		fixCapacity();
	}

	/**
	 * Returns the Reservation from the list with the given index.
	 * 
	 * @param index Index of the Reservation
	 * @return Reservation with the given code
	 * @throws IllegalArgumentException If the list of Reservations is empty
	 */
	public Reservation getReservation(int index) {
		if (index < 0) {
			return null;
		} else if (res.isEmpty()) {
			throw new IllegalArgumentException();
		}
		return res.get(index);
	}

	/**
	 * Returns an array of all of the data.
	 * 
	 * @return Array of all of the data
	 */
	public Object[] getAllData() {
		Object[] arr = new Object[5];
		arr[0] = getName();
		int month = getStartDate().getMonthValue();
		int day = getStartDate().getDayOfMonth();
		int year = getStartDate().getYear() % 100;
		String startDate = "" + (month < 10 ? "0" + month : month) + "/" + (day < 10 ? "0" + day : day) + "/"
				+ (year < 10 ? "0" + year : year);
		arr[1] = startDate;
		arr[2] = "" + getDuration();
		arr[3] = "$" + getBasePrice();
		arr[4] = "" + getCapacity() + (isCapacityFixed() ? "" : "*");
		return arr;
	}

	/**
	 * Returns an array of String representations of the Reservations.
	 * 
	 * @return Array of String representations of the Reservations
	 */
	public String[] listOfReservations() {
		String[] reservations = null;
		if (res.size() == 0) {
			reservations = new String[0];
		} else {
			reservations = new String[res.size()];
			for (int i = 0; i < res.size(); i++) {
				reservations[i] = res.get(i).displayReservationClient();
			}
		}
		return reservations;
	}

	/**
	 * Returns a String representation of the Tour information.
	 * 
	 * @return String representation of the Tour information
	 */
	public String summaryInfo() {
		int month = getStartDate().getMonthValue();
		int day = getStartDate().getDayOfMonth();
		int year = getStartDate().getYear() % 100;
		return getName() + ": " + (month < 10 ? "0" + month : month) + "/" + (day < 10 ? "0" + day : day) + "/"
				+ (year < 10 ? "0" + year : year) + " " + getDuration() + " days";
	}

	/**
	 * Compares two Tours and determines where they go in terms of starting dates.
	 * 
	 * @param tour Tour to be compared with
	 * @return 1 if this Tour comes after the given Tour, -1 if this Tour comes
	 *         before the given Tour, and 0 if both Tours are the same
	 */
	public int compareTo(Tour tour) {
		int dateComp = getStartDate().compareTo(tour.getStartDate());
		if (dateComp == 0) {
			int nameComp = getName().substring(3).compareTo(tour.getName().substring(3));
			if (nameComp == 0) {
				return getDuration() - tour.getDuration();
			}
			return nameComp;
		}
		return dateComp;
	}

	/**
	 * Creates a Reservation of the given party size for the given Client.
	 * 
	 * @param client    Given Client to create the Reservation for
	 * @param partySize Given party size of the Reservation
	 * @return Reservation of the given party size for the given Client
	 * @throws CapacityException        If the Tour cannot accommodate all people in
	 *                                  the Reservation
	 * @throws IllegalArgumentException If the client is null or the party size is
	 *                                  less than 1
	 */
	public Reservation createReservationFor(Client client, int partySize) throws CapacityException {
		if (client == null) {
			throw new IllegalArgumentException();
		} else if (partySize < 1) {
			throw new IllegalArgumentException();
		} else if (partySize > spacesLeft()) {
			throw new CapacityException("Not enough space in selected tour for this party");
		}
		Reservation newRes = new Reservation(this, client, partySize);
		res.add(newRes);
		client.addReservation(newRes);
		numParticipants += partySize;
		return newRes;
	}

	/**
	 * Adds an old Reservation back to the list of Reservations.
	 * 
	 * @param reservation Old Reservation to be added
	 * @return old Reservation that was added
	 * @throws CapacityException        If the Tour cannot accommodate all people in
	 *                                  the Reservation
	 * @throws IllegalArgumentException If the Tour associated with the Reservation
	 *                                  is not this Tour
	 */
	public Reservation addOldReservation(Reservation reservation) throws CapacityException {
		if (reservation.getNumInParty() > spacesLeft()) {
			throw new CapacityException();
		}
		if (!reservation.getTour().getName().equals(getName())) {
			throw new IllegalArgumentException();
		}
		res.add(reservation);
		reservation.getClient().addReservation(reservation);
		numParticipants += reservation.getNumInParty();
		return reservation;
	}

	/**
	 * Cancels the given Reservation.
	 * 
	 * @param reservation Given Reservation to be canceled
	 */
	public void cancelReservation(Reservation reservation) {
		for (int i = 0; i < res.size(); i++) {
			if (res.get(i).equals(reservation)) {
				numParticipants -= res.get(i).getNumInParty();
				res.remove(i);
			}
		}
	}

	/**
	 * Generates a hashCode for Tour using all fields.
	 * 
	 * @return HashCode for the Tour
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + duration;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this object for equality on all fields.
	 * 
	 * @param obj Object to compare
	 * @return True if all objects are the same on all fields, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tour other = (Tour) obj;
		if (duration != other.duration)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!getName().toUpperCase().equals(other.getName().toUpperCase()))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}

	/**
	 * Calculates the cost of a given Reservation.
	 * 
	 * @param index Index of the given Reservation
	 * @return Cost of the given Reservation
	 */
	public abstract double costFor(int index);
}