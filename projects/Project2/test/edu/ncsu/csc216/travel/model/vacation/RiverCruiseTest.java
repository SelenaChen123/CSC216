package edu.ncsu.csc216.travel.model.vacation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.*;

/**
 * Tests RiverCruise.
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public class RiverCruiseTest {

	/**
	 * Runs before tests.
	 */
	@Before
	public void setUp() {
		Reservation.resetCodeGenerator();
	}

	/**
	 * Tests the RiverCruise constructor.
	 */
	@Test
	public void testRiverCruise() {
		RiverCruise t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		assertNotNull(t);
	}

	/**
	 * Tests getName().
	 */
	@Test
	public void testGetName() {
		RiverCruise t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		assertEquals("RC-River Cruise", t.getName());
	}

	/**
	 * Tests costFor().
	 */
	@Test
	public void testCostFor() {
		RiverCruise rc = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);

		assertEquals(0.0, rc.costFor(0), 0.001);
		assertEquals(1500.0, rc.costFor(1), 0.001);
		assertEquals(2000.0, rc.costFor(2), 0.001);
		assertEquals(3500.0, rc.costFor(3), 0.001);
	}
}