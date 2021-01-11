package edu.ncsu.csc216.travel.model.vacation;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.travel.model.participants.Client;

/**
 * Tests Tour.
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public class TourTest {

	/**
	 * Runs before tests.
	 */
	@Before
	public void setUp() {
		Reservation.resetCodeGenerator();
	}

	/**
	 * Tests the Tour constructor.
	 */
	@Test
	public void testTour() {
		Tour t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		assertEquals("RC-River Cruise", t.getName());

		try {
			t = new RiverCruise(null, LocalDate.of(2000, 11, 3), 5, 1000, 10);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("RC-River Cruise", t.getName());
		}

		try {
			t = new RiverCruise("", LocalDate.of(2000, 11, 3), 5, 1000, 10);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("RC-River Cruise", t.getName());
		}

		try {
			t = new RiverCruise("River Cruise", null, 5, 1000, 10);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(LocalDate.of(2000, 11, 3), t.getStartDate());
		}

		try {
			t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 0, 1000, 10);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(5, t.getDuration());
		}

		try {
			t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 0, 10);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1000, t.getBasePrice());
		}

		try {
			t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(10, t.getCapacity());
		}
	}

	/**
	 * Tests hashCode().
	 */
	@Test
	public void testHashCode() {
		Tour t1 = new RiverCruise("River Cruise 1", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		Tour t2 = new RiverCruise("River Cruise 2", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		Tour t3 = new RiverCruise("River Cruise 1", LocalDate.of(2001, 11, 3), 5, 1000, 10);
		Tour t4 = new RiverCruise("River Cruise 1", LocalDate.of(2000, 11, 3), 6, 1000, 10);
		Tour t5 = new RiverCruise("River Cruise 1", LocalDate.of(2000, 11, 3), 5, 100, 10);
		Tour t6 = new RiverCruise("River Cruise 1", LocalDate.of(2000, 11, 3), 5, 1000, 5);
		Tour t7 = new RiverCruise("River Cruise 1", LocalDate.of(2000, 11, 3), 5, 1000, 10);

		assertEquals(t1.hashCode(), t1.hashCode());
		assertEquals(t1.hashCode(), t7.hashCode());
		assertNotEquals(t1.hashCode(), t2.hashCode());
		assertNotEquals(t1.hashCode(), t3.hashCode());
		assertNotEquals(t1.hashCode(), t4.hashCode());
		assertEquals(t1.hashCode(), t5.hashCode());
		assertEquals(t1.hashCode(), t6.hashCode());
	}

	/**
	 * Tests compareTo().
	 */
	@Test
	public void testCompareTo() {
		Tour t1 = new RiverCruise("River Cruise 1", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		Tour t2 = new RiverCruise("River Cruise 1", LocalDate.of(2000, 11, 4), 5, 1000, 10);
		Tour t3 = new RiverCruise("River Cruise 2", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		Tour t4 = new RiverCruise("River Cruise 1", LocalDate.of(2000, 11, 3), 6, 1000, 10);
		Tour t5 = new RiverCruise("River Cruise 1", LocalDate.of(2000, 11, 3), 5, 1000, 10);

		assertEquals(0, t1.compareTo(t5));
		assertTrue(t1.compareTo(t2) < 0);
		assertTrue(t2.compareTo(t1) > 0);
		assertTrue(t1.compareTo(t3) < 0);
		assertTrue(t3.compareTo(t1) > 0);
		assertTrue(t1.compareTo(t4) < 0);
		assertTrue(t4.compareTo(t1) > 0);
	}

	/**
	 * Tests setCapacity().
	 */
	@Test
	public void testSetCapacity() {
		Tour t = new EducationalTrip("Educational Trip", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		try {
			t.setCapacity(3);
			assertEquals(3, t.getCapacity());
		} catch (CapacityException e) {
			fail();
		}

		t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		try {
			t.setCapacity(2);
			fail();
		} catch (CapacityException e) {
			assertEquals(10, t.getCapacity());
		}

		t = new LandTour("LandTour", LocalDate.of(2000, 11, 3), 5, 1000, 8);
		try {
			t.setCapacity(2);
			fail();
		} catch (CapacityException e) {
			assertEquals(8, t.getCapacity());
		}
	}

	/**
	 * Tests numberOfClientReservations().
	 */
	@Test
	public void testNumberOfClientReservations() {
		Tour t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		assertEquals(0, t.numberOfClientReservations());

		try {
			t.createReservationFor(new Client("Alex Snezhko", "avsnezhk"), 5);
			assertEquals(1, t.numberOfClientReservations());
			t.createReservationFor(new Client("Selena Chen", "schen53"), 5);
			assertEquals(2, t.numberOfClientReservations());
		} catch (CapacityException e) {
			fail();
		}
	}

	/**
	 * Tests spacesLeft().
	 */
	@Test
	public void testSpacesLeft() {
		Tour t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		assertEquals(10, t.spacesLeft());

		try {
			t.createReservationFor(new Client("Alex Snezhko", "avsnezhk"), 2);
			assertEquals(8, t.spacesLeft());
			t.createReservationFor(new Client("Selena Chen", "schen53"), 5);
			assertEquals(3, t.spacesLeft());
		} catch (CapacityException e) {
			fail();
		}
	}

	/**
	 * Tests getCapacity().
	 */
	@Test
	public void testGetCapacity() {
		Tour t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		assertEquals(10, t.getCapacity());
	}

	/**
	 * Tests isCapacityFixed().
	 */
	@Test
	public void testIsCapacityFixed() {
		Tour rc = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		assertTrue(rc.isCapacityFixed());
		Tour lt = new LandTour("Land Tour", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		assertTrue(lt.isCapacityFixed());
		Tour et = new EducationalTrip("Educational Trip", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		assertFalse(et.isCapacityFixed());
	}

	/**
	 * Tests fixCapacity().
	 */
	@Test
	public void testFixCapacity() {
		Tour t = new EducationalTrip("Educational Trip", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		assertFalse(t.isCapacityFixed());
		t.fixCapacity();
		assertTrue(t.isCapacityFixed());
	}

	/**
	 * Tests getStartDate().
	 */
	@Test
	public void testGetStartDate() {
		Tour t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		assertEquals(LocalDate.of(2000, 11, 3), t.getStartDate());
	}

	/**
	 * Tests getEndDate().
	 */
	@Test
	public void testGetEndDate() {
		Tour t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		assertEquals(LocalDate.of(2000, 11, 7), t.getEndDate());
	}

	/**
	 * Tests getDuration().
	 */
	@Test
	public void testGetDuration() {
		Tour t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		assertEquals(5, t.getDuration());
	}

	/**
	 * Tests getBasePrice().
	 */
	@Test
	public void testGetBasePrice() {
		Tour t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		assertEquals(1000, t.getBasePrice());
	}

	/**
	 * Tests getName().
	 */
	@Test
	public void testGetName() {
		Tour t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		assertEquals("RC-River Cruise", t.getName());
	}

	/**
	 * Tests getReservation().
	 */
	@Test
	public void testGetReservation() {
		Tour t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		Client c = new Client("Alex Snezhko", "avsnezhk");
		Reservation r = new Reservation(t, c, 5);

		try {
			t.getReservation(0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("RC-River Cruise", t.getName());
		}

		try {
			t.addOldReservation(r);
			assertEquals(r, t.getReservation(0));
		} catch (CapacityException e) {
			fail();
		}

		c = new Client("Selena Chen", "schen53");
		try {
			r = t.createReservationFor(c, 1);
			assertEquals(r, t.getReservation(1));
		} catch (CapacityException e) {
			fail();
		}
	}

	/**
	 * Tests summaryInfo().
	 */
	@Test
	public void testSummaryInfo() {
		Tour t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		assertEquals("RC-River Cruise: 11/03/00 5 days", t.summaryInfo());
		t = new LandTour("Land Tour", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		assertEquals("LT-Land Tour: 11/03/00 5 days", t.summaryInfo());
		t = new EducationalTrip("Educational Trip", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		assertEquals("ED-Educational Trip: 11/03/00 5 days", t.summaryInfo());
	}

	/**
	 * Tests equals().
	 */
	@Test
	public void testEqualsObject() {
		Tour t1 = new RiverCruise("River Cruise 1", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		Tour t2 = new RiverCruise("River Cruise 2", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		Tour t3 = new RiverCruise("River Cruise 1", LocalDate.of(2001, 11, 3), 5, 1000, 10);
		Tour t4 = new RiverCruise("River Cruise 1", LocalDate.of(2000, 11, 3), 6, 1000, 10);
		Tour t5 = new RiverCruise("River Cruise 1", LocalDate.of(2000, 11, 3), 5, 1000, 5);
		Tour t6 = new RiverCruise("River Cruise 1", LocalDate.of(2000, 11, 3), 5, 1000, 10);

		assertTrue(t1.equals(t6));
		assertFalse(t1.equals(t2));
		assertFalse(t1.equals(t3));
		assertFalse(t1.equals(t4));
		assertTrue(t1.equals(t5));
	}

	/**
	 * Tests getAllData().
	 */
	@Test
	public void testGetAllData() {
		Tour rc = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		Tour lt = new LandTour("Land Tour", LocalDate.of(2000, 11, 4), 4, 500, 9);
		Tour et = new EducationalTrip("Educational Trip", LocalDate.of(2000, 11, 5), 3, 100, 8);

		Object[] rcData = rc.getAllData();
		assertEquals("RC-River Cruise", rcData[0]);
		assertEquals("11/03/00", rcData[1]);
		assertEquals("5", rcData[2]);
		assertEquals("$1000", rcData[3]);
		assertEquals("10", rcData[4]);

		Object[] ltData = lt.getAllData();
		assertEquals("LT-Land Tour", ltData[0]);
		assertEquals("11/04/00", ltData[1]);
		assertEquals("4", ltData[2]);
		assertEquals("$500", ltData[3]);
		assertEquals("9", ltData[4]);

		Object[] etData = et.getAllData();
		assertEquals("ED-Educational Trip", etData[0]);
		assertEquals("11/05/00", etData[1]);
		assertEquals("3", etData[2]);
		assertEquals("$100", etData[3]);
		assertEquals("8*", etData[4]);
	}

	/**
	 * Tests listOfReservations().
	 */
	@Test
	public void testListOfReservations() {
		Tour t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
//		assertEquals("", t.listOfReservations()[0]);

		Reservation r1 = new Reservation(t, new Client("Alex Snezhko", "avsnezhk"), 5);
		Reservation r2 = new Reservation(t, new Client("Selena Chen", "schen53"), 5);
		try {
			t.addOldReservation(r1);
			t.addOldReservation(r2);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(2, t.listOfReservations().length);
		assertEquals(r1.displayReservationClient(), t.listOfReservations()[0]);
		assertEquals(r2.displayReservationClient(), t.listOfReservations()[1]);
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

		LandTour lt = new LandTour("Land Tour", LocalDate.of(2000, 11, 3), 5, 1000, 10);

		assertEquals(0.0, lt.costFor(0), 0.001);
		assertEquals(1250.0, lt.costFor(1), 0.001);
		assertEquals(2000.0, lt.costFor(2), 0.001);

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
		Tour t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		assertEquals(0, t.numberOfClientReservations());

		try {
			t.createReservationFor(new Client("Alex Snezhko", "avsnezhk"), 2);
			assertEquals(1, t.numberOfClientReservations());

			t.createReservationFor(new Client("Selena Chen", "schen53"), 5);
			assertEquals(2, t.numberOfClientReservations());
		} catch (CapacityException e) {
			fail();
		}
	}

	/**
	 * Tests addOldReservation().
	 */
	@Test
	public void testAddOldReservation() {
		Tour t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		assertEquals(0, t.numberOfClientReservations());

		try {
			t.addOldReservation(new Reservation(t, new Client("Alex Snezhko", "avsnezhk"), 2));
			assertEquals(1, t.numberOfClientReservations());

			t.addOldReservation(new Reservation(t, new Client("Selena Chen", "schen53"), 5));
			assertEquals(2, t.numberOfClientReservations());

			assertEquals(3, t.spacesLeft());
		} catch (CapacityException e) {
			fail();
		}

		try {
			t.addOldReservation(new Reservation(t, new Client("John Doe", "jdoe"), 5));
			fail();
		} catch (CapacityException e) {
			assertEquals(2, t.numberOfClientReservations());
		}
	}

	/**
	 * Tests cancelReservation().
	 */
	@Test
	public void testCancelReservation() {
		Tour t = new RiverCruise("River Cruise", LocalDate.of(2000, 11, 3), 5, 1000, 10);
		assertEquals(0, t.numberOfClientReservations());

		Reservation r1 = new Reservation(t, new Client("Alex Snezhko", "avsnezhk"), 2);
		Reservation r2 = new Reservation(t, new Client("Selena Chen", "schen53"), 5);
		try {
			t.addOldReservation(r1);
			t.addOldReservation(r2);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(2, t.numberOfClientReservations());

		t.cancelReservation(r1);
		assertEquals(1, t.numberOfClientReservations());

		t.cancelReservation(r2);
		assertEquals(0, t.numberOfClientReservations());
	}
}