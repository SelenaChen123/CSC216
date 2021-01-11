package edu.ncsu.csc216.travel.model.vacation;

import java.time.LocalDate;

import edu.ncsu.csc216.travel.model.participants.Client;

/**
 * Represents an educational trip tour. This class extends Tour to include a
 * unique prefix, calculate the cost for an educational trip, create educational
 * trip reservations, and get the name of the educational trip.
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public class EducationalTrip extends Tour {

	/** Short String representation for an EducationalTrip */
	private static String prefix = "ED";

	/**
	 * Constructs an EducationalTrip object with a name, start date, duration (in
	 * days), base price, and capacity.
	 * 
	 * @param name      Name of the EducationalTrip
	 * @param start     Start date of the EducationalTrip
	 * @param duration  Duration of the EducationalTrip (in days)
	 * @param basePrice Base price of the EducationalTrip
	 * @param capacity  Capacity of the EducationalTrip
	 */
	public EducationalTrip(String name, LocalDate start, int duration, int basePrice, int capacity) {
		super(name, start, duration, basePrice, capacity);
	}

	/**
	 * Calculates the cost of a given Reservation.
	 * 
	 * @param partySize Number of people in the party of the Reservation
	 * @return Cost of the given Reservation
	 */
	@Override
	public double costFor(int partySize) {
		return getBasePrice() * partySize;
	}

	/**
	 * Creates a Reservation of the given party size for the given Client.
	 * 
	 * @param client    Given Client to create the Reservation for
	 * @param partySize Given party size of the Reservation
	 * @return Reservation of the given party size for the given Client
	 * @throws CapacityException If the EducationalTrip cannot accommodate all
	 *                           people in the Reservation
	 */
	@Override
	public Reservation createReservationFor(Client client, int partySize) throws CapacityException {
		if(partySize > spacesLeft() && !isCapacityFixed()) {
			setCapacity(getCapacity() * 2);
			fixCapacity();
		}
		return super.createReservationFor(client, partySize);
	}

	/**
	 * Returns the name of the EducationalTrip.
	 * 
	 * @return Name of the EducationalTrip
	 */
	@Override
	public String getName() {
		return prefix + "-" + super.getName();
	}
}