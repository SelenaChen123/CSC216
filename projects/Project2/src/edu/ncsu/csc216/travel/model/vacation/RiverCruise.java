package edu.ncsu.csc216.travel.model.vacation;

import java.time.LocalDate;

/**
 * Represents a river cruise tour. This class extends Tour to include a unique
 * prefix, calculate the cost for a river cruise, and get the name of the river
 * cruise.
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public class RiverCruise extends Tour {

	/** Short String representation for a RiverCruise */
	private static String prefix = "RC";

	/**
	 * Constructs a RiverCruise with a name, start date, duration (in days), base
	 * price, and capacity.
	 * 
	 * @param name      Name of the RiverCruise
	 * @param start     Start date of the RiverCruise
	 * @param duration  Duration of the RiverCruise (in days)
	 * @param basePrice Base price of the RiverCruise
	 * @param capacity  Capacity of the RiverCruise
	 */
	public RiverCruise(String name, LocalDate start, int duration, int basePrice, int capacity) {
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
		return getBasePrice() * partySize + (partySize % 2 == 0 ? 0 : getBasePrice() * 0.5);
	}

	/**
	 * Returns the name of the RiverCruise.
	 * 
	 * @return Name of the RiverCruise
	 */
	@Override
	public String getName() {
		return prefix + "-" + super.getName();
	}
}