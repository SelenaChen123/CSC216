package edu.ncsu.csc216.travel.model.vacation;

import java.time.LocalDate;

/**
 * Represents a land tour. This class extends Tour to include a unique prefix,
 * calculate the cost for a land tour, and get the name of the land tour.
 * 
 * @author SelenaChen
 * @author Alex Snezhko
 */
public class LandTour extends Tour {

	/** Short String representation for a LandTour */
	private static String prefix = "LT";

	/**
	 * Constructs a LandTour with a name, start date, duration (in days), base
	 * price, and capacity.
	 * 
	 * @param name      Name of the LandTour
	 * @param start     Start date of the LandTour
	 * @param duration  Duration of the LandTour (in days)
	 * @param basePrice Base price of the LandTour
	 * @param capacity  Capacity of the LandTour
	 */
	public LandTour(String name, LocalDate start, int duration, int basePrice, int capacity) {
		super(name, start, duration, basePrice, capacity);
		fixCapacity();
	}

	/**
	 * Calculates the cost of a given Reservation.
	 * 
	 * @param partySize Number of people in the party of the Reservation
	 * @return Cost of the given Reservation
	 */
	@Override
	public double costFor(int partySize) {
		return getBasePrice() * (partySize == 1 ? 1.25 : partySize);
	}

	/**
	 * Returns the name of the LandTour.
	 * 
	 * @return Name of the LandTour
	 */
	@Override
	public String getName() {
		return prefix + "-" + super.getName();
	}
}