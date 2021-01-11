package edu.ncsu.csc216.travel.model.participants;

import edu.ncsu.csc216.travel.list_utils.SimpleArrayList;
import edu.ncsu.csc216.travel.model.vacation.Reservation;

/**
 * Represents Wolf Travel customers. Each client maintains a list of
 * reservations for that client. Has methods to get its name, contact, number of
 * reservations, reservations, reservation cost, as well as methods to add
 * reservations and cancel reservations.
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public class Client {

	/** Name of the Client */
	private String name;
	/** Contact for the Client */
	private String contact;
	/** List of the Client's Reservations */
	private SimpleArrayList<Reservation> myReservations;

	/**
	 * Constructs a Client object with a given name and contact.
	 * 
	 * @param name    Name of the Client
	 * @param contact Contact for the Client
	 */
	public Client(String name, String contact) {
		if(name == null || name.trim().equals("") || !Character.isLetter(name.trim().charAt(0))) {
			throw new IllegalArgumentException("Client name must start with an alphabetic character.");
		}
		if(contact == null || contact.trim().equals("") || contact.trim().substring(0, 1).equals("*")
				|| !Character.isLetter(contact.trim().charAt(0)) && !Character.isDigit(contact.trim().charAt(0))) {
			throw new IllegalArgumentException("Client contact must start with a letter or digit.");
		}
		this.name = name.trim();
		this.contact = contact.trim();
		myReservations = new SimpleArrayList<Reservation>();
	}

	/**
	 * Returns the contact for the Client.
	 * 
	 * @return Contact for the Client
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * Returns the name of the Client.
	 * 
	 * @return Name of the Client
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the number of Reservations the Client has.
	 * 
	 * @return Number of Reservations the Client has
	 */
	public int getNumberOfReservations() {
		return myReservations.size();
	}

	/**
	 * Returns the Client’s Reservation at the given position, where position
	 * numbering starts at 0.
	 * 
	 * @param pos Position of the Reservation
	 * @return Client's Reservation at the given position
	 */
	public Reservation getReservation(int pos) {
		if (pos < 0 || pos >= myReservations.size()) {
			throw new IllegalArgumentException("Select a client then the client's reservation to cancel.");
		}
		return myReservations.get(pos);
	}

	/**
	 * Returns the total cost of the Client's Reservations.
	 * 
	 * @return Total cost of the Client's Reservations
	 */
	public double totalReservationCost() {
		double cost = 0;
		for (int i = 0; i < getNumberOfReservations(); i++) {
			cost += myReservations.get(i).getCost();
		}
		return cost;
	}

	/**
	 * Adds a Reservation to the Client’s list of Reservations.
	 * 
	 * @param reservation Reservation to be added
	 * @throws IllegalArgumentException If the Client for the Reservation is not
	 *                                  this Client
	 */
	public void addReservation(Reservation reservation) {
		if (!reservation.getClient().equals(this)) {
			throw new IllegalArgumentException();
		}
		myReservations.add(reservation);
	}

	/**
	 * Removes a Reservation from the Client’s list of Reservations.
	 * 
	 * @param reservation Reservation to be removed
	 * @throws IllegalArgumentException If the Client for the given Reservation is
	 *                                  not this Client
	 */
	public void cancelReservation(Reservation reservation) {
		if (!reservation.getClient().equals(this)) {
			throw new IllegalArgumentException();
		}
		int index = -1;
		for (int i = 0; i < myReservations.size(); i++) {
			if (myReservations.get(i).equals(reservation)) {
				index = i;
			}
		}
		if (index > -1) {
			myReservations.remove(index);
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Returns an array of Strings representing the Client’s Reservations.
	 * 
	 * @return Array of Strings representing the Client’s Reservations
	 */
	public String[] listOfReservations() {
		String[] res = null;
		if (getNumberOfReservations() == 0) {
			res = new String[0];
		} else {
			res = new String[getNumberOfReservations()];
			for (int i = 0; i < getNumberOfReservations(); i++) {
				res[i] = getReservation(i).displayReservationTour();
			}
		}
		return res;
	}

	/**
	 * Returns a String that contains the Client name followed by contact inside
	 * parentheses.
	 * 
	 * @return String that contains the Client name followed by contact inside
	 *         parentheses
	 */
	public String summaryInfo() {
		return "" + name + " (" + contact + ")";
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
		Client other = (Client) obj;
		if (contact == null) {
			if (other.contact != null)
				return false;
		} else if (!contact.toUpperCase().equals(other.contact.toUpperCase()))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.toUpperCase().equals(other.name.toUpperCase()))
			return false;
		return true;
	}

	/**
	 * Generates a hashCode for the Client using all fields.
	 * 
	 * @return HashCode for the Client
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contact == null) ? 0 : contact.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
}