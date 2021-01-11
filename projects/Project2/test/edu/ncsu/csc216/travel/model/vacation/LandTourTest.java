package edu.ncsu.csc216.travel.model.vacation;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests LandTour.
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public class LandTourTest {

	/**
	 * Runs before tests.
	 */
	@Before
	public void setUp() {
		Reservation.resetCodeGenerator();
	}

	/**
	 * Tests the LandTour constructor.
	 */
	@Test
	public void testLandTour() {
		LandTour t = new LandTour("Land Tour", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		assertNotNull(t);
	}

	/**
	 * Tests getName().
	 */
	@Test
	public void testGetName() {
		LandTour t = new LandTour("Land Tour", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		assertEquals("LT-Land Tour", t.getName());
	}

	/**
	 * Tests costFor().
	 */
	@Test
	public void testCostFor() {
		LandTour lt = new LandTour("Land Tour", LocalDate.of(2000, 11, 3), 5, 1000, 10);

		assertEquals(0.0, lt.costFor(0), 0.001);
		assertEquals(1250.0, lt.costFor(1), 0.001);
		assertEquals(2000.0, lt.costFor(2), 0.001);
	}
}