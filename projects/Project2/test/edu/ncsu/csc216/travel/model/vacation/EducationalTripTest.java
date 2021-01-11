package edu.ncsu.csc216.travel.model.vacation;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.travel.model.participants.Client;

/**
 * Tests EducationalTrip.
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public class EducationalTripTest {

	/**
	 * Runs before tests.
	 */
	@Before
	public void setUp() {
		Reservation.resetCodeGenerator();
	}

	/**
	 * Tests the EducationalTrip constructor.
	 */
	@Test
	public void testEducationalTrip() {
		EducationalTrip t = new EducationalTrip("Educational Trip", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		assertNotNull(t);
	}

	/**
	 * Tests getName().
	 */
	@Test
	public void testGetName() {
		EducationalTrip t = new EducationalTrip("Educational Trip", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		assertEquals("ED-Educational Trip", t.getName());
	}

	/**
	 * Tests costFor().
	 */
	@Test
	public void testCostFor() {
		EducationalTrip et = new EducationalTrip("Educational Trip", LocalDate.of(2000, 11, 3), 5, 1000, 10);

		assertEquals(0.0, et.costFor(0), 0.001);
		assertEquals(1000.0, et.costFor(1), 0.001);
		assertEquals(2000.0, et.costFor(2), 0.001);
	}

	/**
	 * Tests createReservationFor().
	 */
	@Test
	public void testCreateReservationFor() {
		EducationalTrip t = new EducationalTrip("Educational Trip", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		try {
			t.createReservationFor(new Client("Alex Snezhko", "avsnezhk"), 11);
			assertEquals(9, t.spacesLeft());
		} catch (CapacityException e) {
			fail();
		}
		try {
			t.createReservationFor(new Client("Selena Chen", "schen53"), 10);
			fail();
		} catch (CapacityException e) {
			assertEquals(9, t.spacesLeft());
		}
	}
}