package edu.ncsu.csc216.travel.model.vacation;

import edu.ncsu.csc216.travel.model.participants.Client;

/**
 * Represents a client’s reservation for a tour within the WolfTravel system. A
 * reservation connects a client to a tour. Has getters for each of its
 * associated tour, associated client, number of people in the party,
 * confirmation code, and cost fields. Also has methods to cancel the
 * reservation, display the reservation's information for the associated tour,
 * display the reservation's information for the associated client, and reset
 * the code generator.
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public class Reservation {

	/** Tour associated with the Reservation */
	private Tour theTour;
	/** Client associated with the Reservation */
	private Client theClient;
	/** Determines the confirmation code of the next Reservation */
	private static int codeGenerator = 0;
	/** Maximum possible confirmation code */
	private static int maxCode = 999999;
	/** Confirmation code for the Reservation */
	private String confirmationCode;
	/** Number of people in the party of the Reservation */
	private int numInParty;
	/** Cost of the Reservation */
	private double cost;

	/**
	 * Constructs a Reservation object with a given Tour, Client, and party size.
	 * 
	 * @param tour      Tour associated with the Reservation
	 * @param client    Client associated with the Reservation
	 * @param partySize Party size of the Reservation
	 */
	public Reservation(Tour tour, Client client, int partySize) {
		this(tour, client, partySize, codeGenerator);
	}

	/**
	 * Constructs a Reservation object with a given Tour, Client, party size, and
	 * confirmation code.
	 * 
	 * @param tour             Tour associated with the Reservation
	 * @param client           Client associated with the Reservation
	 * @param partySize        Party size of the Reservation
	 * @param confirmationCode Confirmation code for the Reservation
	 * @throws IllegalArgumentException If the Tour is null, the Client is null, or
	 *                                  the party size is less than 0
	 */
	public Reservation(Tour tour, Client client, int partySize, int confirmationCode) {
		if (tour == null || client == null || partySize <= 0 || confirmationCode > maxCode || confirmationCode < 0) {
			throw new IllegalArgumentException();
		}
		theTour = tour;
		theClient = client;
		numInParty = partySize;
		this.confirmationCode = String.format("%06d", confirmationCode);

		if (confirmationCode >= codeGenerator) {
			codeGenerator = confirmationCode;
			codeGenerator++;
			if (codeGenerator > maxCode) {
				resetCodeGenerator();
			}
		}

	}

	/**
	 * Returns the Tour associated with the Reservation.
	 * 
	 * @return Tour associated with the Reservation
	 */
	public Tour getTour() {
		return theTour;
	}

	/**
	 * Returns the Client associated with the Reservation.
	 * 
	 * @return Client associated with the Reservation
	 */
	public Client getClient() {
		return theClient;
	}

	/**
	 * Returns the party size of the Reservation.
	 * 
	 * @return Party size of the Reservation
	 */
	public int getNumInParty() {
		return numInParty;
	}

	/**
	 * Returns the confirmation code for the Reservation as a 6-digit String.
	 * 
	 * @return Confirmation code for the Reservation as a 6-digit String
	 */
	public String getConfirmationCode() {
		return confirmationCode;
	}

	/**
	 * Returns the cost of the Reservation.
	 * 
	 * @return Cost of the Reservation
	 */
	public double getCost() {
		cost = theTour.costFor(numInParty);
		return cost;
	}

	/**
	 * Cancels the Reservation.
	 */
	public void cancel() {
		theTour.cancelReservation(this);
		theClient.cancelReservation(this);
	}

	/**
	 * Returns a String with the Reservation's information for the associated Tour
	 * (confirmation code, size of the party, associated Tour kind, associated Tour
	 * name, associated Tour start date, and associated Tour duration).
	 * 
	 * @return String with the Reservation's information for the associated Tour
	 *         (confirmation code, size of the party, associated Tour kind,
	 *         associated Tour name, associated Tour start date, and associated Tour
	 *         duration)
	 */
	public String displayReservationTour() {
		String confirmationCodeAndNumInParty = confirmationCode + String.format("%4d", numInParty);
		return confirmationCodeAndNumInParty + " " + theTour.summaryInfo();
	}

	/**
	 * Returns a String with theReservation's information for the associated Client
	 * (confirmation code, size of the party, corresponding Client name, and
	 * corresponding Client contact information).
	 * 
	 * @return String with theReservation's information for the associated Client
	 *         (confirmation code, size of the party, corresponding Client name, and
	 *         corresponding Client contact information)
	 */
	public String displayReservationClient() {
		String confirmationCodeAndNumInParty = confirmationCode + String.format("%4d", numInParty);
		return confirmationCodeAndNumInParty + " " + theClient.summaryInfo();
	}

	/**
	 * Resets the confirmation code generator so that the next confirmation code
	 * will be 0.
	 */
	public static void resetCodeGenerator() {
		codeGenerator = 0;
	}

	/**
	 * Generates a hashCode for Reservation using confirmation code.
	 * 
	 * @return HashCode for the Reservation
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((confirmationCode == null) ? 0 : confirmationCode.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this Reservation for equality on confirmation
	 * codes.
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
		Reservation other = (Reservation) obj;
		if (confirmationCode == null) {
			if (other.confirmationCode != null)
				return false;
		} else if (!confirmationCode.equals(other.confirmationCode))
			return false;
		return true;
	}
}