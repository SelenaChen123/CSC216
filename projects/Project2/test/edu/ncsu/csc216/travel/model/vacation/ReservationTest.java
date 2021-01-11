package edu.ncsu.csc216.travel.model.vacation;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.travel.model.participants.Client;

/**
 * Tests Reservation.
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public class ReservationTest {

	/**
	 * Runs before tests.
	 */
	@Before
	public void setUp() {
		Reservation.resetCodeGenerator();
	}

	/**
	 * Tests the Reservation constructor with a given Tour, Client, and party size.
	 */
	@Test
	public void testReservationTourClientInt() {
		Tour t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		Client c = new Client("Alex Snezhko", "avsnezhk");
		Reservation res = new Reservation(t, c, 2);
		assertEquals(t, res.getTour());
		assertEquals(c, res.getClient());
		assertEquals(2, res.getNumInParty());
	}

	/**
	 * Tests the Reservation constructor with a given Tour, Client, party size, and
	 * confirmation code.
	 */
	@Test
	public void testReservationTourClientIntInt() {
		Tour t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		Client c = new Client("Alex Snezhko", "avsnezhk");
		Reservation res = new Reservation(t, c, 2, 1);
		assertEquals(t, res.getTour());
		assertEquals(c, res.getClient());
		assertEquals(2, res.getNumInParty());
		assertEquals("000001", res.getConfirmationCode());
	}

	/**
	 * Tests getTour().
	 */
	@Test
	public void testGetTour() {
		Tour t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		Client c = new Client("Alex Snezhko", "avsnezhk");
		Reservation res = new Reservation(t, c, 2, 1);
		assertEquals(t, res.getTour());
	}

	/**
	 * Tests getClient().
	 */
	@Test
	public void testGetClient() {
		Tour t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		Client c = new Client("Alex Snezhko", "avsnezhk");
		Reservation res = new Reservation(t, c, 2, 1);
		assertEquals(c, res.getClient());
	}

	/**
	 * Tests getNumInParty().
	 */
	@Test
	public void testGetNumInParty() {
		Tour t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		Client c = new Client("Alex Snezhko", "avsnezhk");
		Reservation res = new Reservation(t, c, 2, 1);
		assertEquals(2, res.getNumInParty());
	}

	/**
	 * Tests getConfirmationCode().
	 */
	@Test
	public void testGetConfirmationCode() {
		Tour t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		Client c = new Client("Alex Snezhko", "avsnezhk");
		Reservation res = new Reservation(t, c, 2, 1);
		assertEquals("000001", res.getConfirmationCode());
	}

	/**
	 * Tests getCost().
	 */
	@Test
	public void testGetCost() {
		Tour t1 = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);

		Client c1 = new Client("Alex Snezhko", "avsnezhk");
		Reservation res1 = new Reservation(t1, c1, 3);
		try {
			t1.addOldReservation(res1);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(3500.0, res1.getCost(), 0.001);

		Client c2 = new Client("Selena Chen", "schen53");
		Reservation res2 = new Reservation(t1, c2, 2);
		try {
			t1.addOldReservation(res2);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(2000.0, res2.getCost(), 0.001);

		Tour t2 = new LandTour("Land Tour", LocalDate.of(2000, 11, 3), 5, 1000, 10);

		Reservation res3 = new Reservation(t2, c1, 3);
		try {
			t2.addOldReservation(res3);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(3000.0, res3.getCost(), 0.001);

		Reservation res4 = new Reservation(t2, c2, 1);
		try {
			t2.addOldReservation(res4);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(1250.0, res4.getCost(), 0.001);

		Tour t3 = new EducationalTrip("Educational Trip", LocalDate.of(2000, 11, 3), 5, 1000, 10);

		Reservation res5 = new Reservation(t3, c1, 3);
		try {
			t3.addOldReservation(res5);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(3000.0, res5.getCost(), 0.001);
	}

	/**
	 * Tests cancel().
	 */
	@Test
	public void testCancel() {
		Tour t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		Client c = new Client("Alex Snezhko", "avsnezhk");
		Reservation res = new Reservation(t, c, 3);
		try {
			t.addOldReservation(res);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(1, t.numberOfClientReservations());

		t.getReservation(0).cancel();
		assertEquals(0, t.numberOfClientReservations());
	}

	/**
	 * Tests displayReservationTour().
	 */
	@Test
	public void testDisplayReservationTour() {
		Tour t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		Client c = new Client("Alex Snezhko", "avsnezhk");
		Reservation res = new Reservation(t, c, 3);
		try {
			t.addOldReservation(res);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals("000000   3 RC-River Cruise: 11/03/00 5 days", t.getReservation(0).displayReservationTour());
	}

	/**
	 * Tests displayReservationClient().
	 */
	@Test
	public void testDisplayReservationClient() {
		Tour t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		Client c = new Client("Alex Snezhko", "avsnezhk");
		Reservation res = new Reservation(t, c, 3);
		try {
			t.addOldReservation(res);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals("000000   3 Alex Snezhko (avsnezhk)", t.getReservation(0).displayReservationClient());
	}

	/**
	 * Tests resetCodeGenerator().
	 */
	@Test
	public void testResetCodeGenerator() {
		Tour t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		Client c1 = new Client("Alex Snezhko", "avsnezhk");
		Reservation res1 = new Reservation(t, c1, 3);
		assertEquals("000000", res1.getConfirmationCode());

		Client c2 = new Client("Selena Chen", "schen53");
		Reservation res2 = new Reservation(t, c2, 2);
		assertEquals("000001", res2.getConfirmationCode());

		Reservation.resetCodeGenerator();

		Client c3 = new Client("name", "contact");
		Reservation res3 = new Reservation(t, c3, 2);
		assertEquals("000000", res3.getConfirmationCode());
	}

	/**
	 * Tests hashCode().
	 */
	@Test
	public void testHashCode() {
		Tour t1 = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		Client c1 = new Client("name1", "contact1");
		Reservation res1 = new Reservation(t1, c1, 1);

		Tour t2 = new LandTour("Land Tour", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		Client c2 = new Client("name2", "contact2");
		Reservation res2 = new Reservation(t2, c2, 1);

		Reservation res3 = new Reservation(t1, c2, 1);
		Reservation res4 = new Reservation(t2, c1, 1);

		assertEquals(res1.hashCode(), res1.hashCode());
		assertNotEquals(res1.hashCode(), res2.hashCode());
		assertNotEquals(res1.hashCode(), res3.hashCode());
		assertNotEquals(res1.hashCode(), res4.hashCode());
	}

	/**
	 * Tests equals().
	 */
	@Test
	public void testEqualsObject() {
		Tour t1 = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		Client c1 = new Client("name1", "contact1");
		Reservation res1 = new Reservation(t1, c1, 1);

		Tour t2 = new LandTour("Land Tour", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		Client c2 = new Client("name2", "contact2");
		Reservation res2 = new Reservation(t2, c2, 1);

		Reservation res3 = new Reservation(t1, c2, 1);
		Reservation res4 = new Reservation(t2, c1, 1);

		assertTrue(res1.equals(res1));
		assertFalse(res1.equals(res2));
		assertFalse(res1.equals(res3));
		assertFalse(res1.equals(res4));

		Reservation.resetCodeGenerator();
		Reservation res5 = new Reservation(t1, c1, 1);
		Reservation.resetCodeGenerator();
		Reservation res6 = new Reservation(t1, c1, 1);
		assertTrue(res5.equals(res6));
	}
}