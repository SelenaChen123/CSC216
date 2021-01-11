package edu.ncsu.csc216.travel.model.participants;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.travel.model.vacation.CapacityException;
import edu.ncsu.csc216.travel.model.vacation.EducationalTrip;
import edu.ncsu.csc216.travel.model.vacation.LandTour;
import edu.ncsu.csc216.travel.model.vacation.Reservation;
import edu.ncsu.csc216.travel.model.vacation.RiverCruise;
import edu.ncsu.csc216.travel.model.vacation.Tour;

/**
 * Tests Client.
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public class ClientTest {

	/**
	 * Runs before tests.
	 */
	@Before
	public void setUp() {
		Reservation.resetCodeGenerator();
	}

	/**
	 * Tests the Client constructor.
	 */
	@Test
	public void testClient() {
		Client client = new Client("name", "contact");
		assertEquals("name", client.getName());
		assertEquals("contact", client.getContact());
	}

	/**
	 * Tests getName().
	 */
	@Test
	public void testGetName() {
		Client client = new Client("name", "contact");
		assertEquals("name", client.getName());
	}

	/**
	 * Tests getContact().
	 */
	@Test
	public void testGetContact() {
		Client client = new Client("name", "contact");
		assertEquals("contact", client.getContact());
	}

	/**
	 * Tests getNumberOfReservations().
	 */
	@Test
	public void testGetNumberOfReservations() {
		Client client = new Client("name", "contact");
		assertEquals(0, client.getNumberOfReservations());

		Tour tour = new RiverCruise("river", LocalDate.of(2019, 1, 1), 3, 100, 5);
		Reservation reservation1 = new Reservation(tour, client, 1);
		client.addReservation(reservation1);
		assertEquals(1, client.getNumberOfReservations());

		tour = new LandTour("land", LocalDate.of(2019, 2, 1), 3, 100, 5);
		Reservation reservation2 = new Reservation(tour, client, 1);
		client.addReservation(reservation2);
		assertEquals(2, client.getNumberOfReservations());

		tour = new EducationalTrip("education", LocalDate.of(2019, 3, 1), 3, 100, 5);
		Reservation reservation3 = new Reservation(tour, client, 1);
		client.addReservation(reservation3);
		assertEquals(3, client.getNumberOfReservations());

		client.cancelReservation(reservation2);
		assertEquals(2, client.getNumberOfReservations());

		client.cancelReservation(reservation3);
		assertEquals(1, client.getNumberOfReservations());

		client.cancelReservation(reservation1);
		assertEquals(0, client.getNumberOfReservations());
	}

	/**
	 * Tests getReservation().
	 */
	@Test
	public void testGetReservation() {
		Client client = new Client("name", "contact");
		assertEquals(0, client.getNumberOfReservations());

		Tour tour = new RiverCruise("river", LocalDate.of(2019, 1, 1), 3, 100, 5);
		Reservation reservation1 = new Reservation(tour, client, 1);
		client.addReservation(reservation1);

		tour = new LandTour("land", LocalDate.of(2019, 2, 1), 3, 100, 5);
		Reservation reservation2 = new Reservation(tour, client, 1);
		client.addReservation(reservation2);

		tour = new EducationalTrip("education", LocalDate.of(2019, 3, 1), 3, 100, 5);
		Reservation reservation3 = new Reservation(tour, client, 1);
		client.addReservation(reservation3);

		assertEquals(3, client.getNumberOfReservations());
		assertEquals(reservation1, client.getReservation(0));
		assertEquals(reservation2, client.getReservation(1));
		assertEquals(reservation3, client.getReservation(2));
	}

	/**
	 * Tests totalReservationCost().
	 */
	@Test
	public void testTotalReservationCost() {
		Client client = new Client("name", "contact");
		assertEquals(0, client.getNumberOfReservations());

		Tour tour = new RiverCruise("river1", LocalDate.of(2019, 1, 1), 3, 100, 5);
		Reservation reservation1 = new Reservation(tour, client, 1);
		try {
			tour.addOldReservation(reservation1);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(150.0, client.totalReservationCost(), 0.01);

		tour = new LandTour("land1", LocalDate.of(2019, 2, 1), 3, 100, 5);
		Reservation reservation2 = new Reservation(tour, client, 1);
		try {
			tour.addOldReservation(reservation2);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(275.0, client.totalReservationCost(), 0.01);

		tour = new EducationalTrip("education", LocalDate.of(2019, 3, 1), 3, 100, 5);
		Reservation reservation3 = new Reservation(tour, client, 1);
		try {
			tour.addOldReservation(reservation3);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(375.0, client.totalReservationCost(), 0.01);

		tour = new LandTour("land2", LocalDate.of(2019, 4, 1), 3, 100, 5);
		Reservation reservation4 = new Reservation(tour, client, 2);
		try {
			tour.addOldReservation(reservation4);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(575.0, client.totalReservationCost(), 0.01);

		tour = new RiverCruise("river2", LocalDate.of(2019, 5, 1), 3, 100, 5);
		Reservation reservation5 = new Reservation(tour, client, 2);
		try {
			tour.addOldReservation(reservation5);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(775.0, client.totalReservationCost(), 0.01);

		tour = new RiverCruise("river3", LocalDate.of(2019, 5, 1), 3, 100, 5);
		Reservation reservation6 = new Reservation(tour, client, 3);
		try {
			tour.addOldReservation(reservation6);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(1125.0, client.totalReservationCost(), 0.01);
	}

	/**
	 * Tests addReservation().
	 */
	@Test
	public void testAddReservation() {
		Client client = new Client("name1", "contact1");
		assertEquals(0, client.getNumberOfReservations());

		Tour tour = new RiverCruise("river", LocalDate.of(2019, 1, 1), 3, 100, 5);
		Reservation reservation1 = new Reservation(tour, client, 1);
		client.addReservation(reservation1);
		assertEquals(1, client.getNumberOfReservations());

		tour = new LandTour("land", LocalDate.of(2019, 2, 1), 3, 100, 5);
		Reservation reservation2 = new Reservation(tour, client, 1);
		client.addReservation(reservation2);
		assertEquals(2, client.getNumberOfReservations());

		tour = new EducationalTrip("education1", LocalDate.of(2019, 3, 1), 3, 100, 5);
		Reservation reservation3 = new Reservation(tour, client, 1);
		client.addReservation(reservation3);
		assertEquals(3, client.getNumberOfReservations());

		tour = new EducationalTrip("education2", LocalDate.of(2019, 4, 1), 3, 100, 5);
		Reservation reservation4 = new Reservation(tour, new Client("name2", "contact2"), 1);
		try {
			client.addReservation(reservation4);
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(3, client.getNumberOfReservations());
		}
	}

	/**
	 * Tests cancelReservation().
	 */
	@Test
	public void testCancelReservation() {
		Client client = new Client("name", "contact");
		assertEquals(0, client.getNumberOfReservations());

		Tour tour = new RiverCruise("river", LocalDate.of(2019, 1, 1), 3, 100, 5);
		Reservation reservation1 = new Reservation(tour, client, 1);
		client.addReservation(reservation1);
		assertEquals(1, client.getNumberOfReservations());
		assertEquals(reservation1, client.getReservation(0));

		tour = new LandTour("land", LocalDate.of(2019, 2, 1), 3, 100, 5);
		Reservation reservation2 = new Reservation(tour, client, 1);
		client.addReservation(reservation2);
		assertEquals(2, client.getNumberOfReservations());
		assertEquals(reservation1, client.getReservation(0));
		assertEquals(reservation2, client.getReservation(1));

		tour = new EducationalTrip("education", LocalDate.of(2019, 3, 1), 3, 100, 5);
		Reservation reservation3 = new Reservation(tour, client, 1);
		client.addReservation(reservation3);
		assertEquals(3, client.getNumberOfReservations());
		assertEquals(reservation1, client.getReservation(0));
		assertEquals(reservation2, client.getReservation(1));
		assertEquals(reservation3, client.getReservation(2));

		client.cancelReservation(reservation2);
		assertEquals(2, client.getNumberOfReservations());
		assertEquals(reservation1, client.getReservation(0));
		assertEquals(reservation3, client.getReservation(1));

		client.cancelReservation(reservation3);
		assertEquals(1, client.getNumberOfReservations());
		assertEquals(reservation1, client.getReservation(0));

		client.cancelReservation(reservation1);
		assertEquals(0, client.getNumberOfReservations());
	}

	/**
	 * Tests listOfReservations().
	 */
	@Test
	public void testListOfReservations() {
		Client client = new Client("name", "contact");
		assertEquals(0, client.getNumberOfReservations());

		Tour tour = new RiverCruise("river", LocalDate.of(2019, 1, 1), 3, 100, 5);
		Reservation reservation1 = new Reservation(tour, client, 1);
		client.addReservation(reservation1);

		tour = new LandTour("land", LocalDate.of(2019, 2, 1), 3, 100, 5);
		Reservation reservation2 = new Reservation(tour, client, 1);
		client.addReservation(reservation2);

		tour = new EducationalTrip("education", LocalDate.of(2019, 3, 1), 3, 100, 5);
		Reservation reservation3 = new Reservation(tour, client, 1);
		client.addReservation(reservation3);

		assertEquals(3, client.getNumberOfReservations());

		assertEquals("000000   1 RC-river: 01/01/19 3 days", client.listOfReservations()[0]);
		assertEquals("000001   1 LT-land: 02/01/19 3 days", client.listOfReservations()[1]);
		assertEquals("000002   1 ED-education: 03/01/19 3 days", client.listOfReservations()[2]);
	}

	/**
	 * Tests summaryInfo().
	 */
	@Test
	public void testSummaryInfo() {
		Client client = new Client("name", "contact");
		assertEquals("name (contact)", client.summaryInfo());
	}

	/**
	 * Tests hashCode().
	 */
	@Test
	public void testHashCode() {
		Client client1 = new Client("name1", "contact1");
		Client client2 = new Client("name1", "contact1");
		Client client3 = new Client("name2", "contact2");
		Client client4 = new Client("name2", "contact2");

		assertEquals(client1.hashCode(), client2.hashCode());
		assertEquals(client3.hashCode(), client4.hashCode());
		assertNotEquals(client1.hashCode(), client3.hashCode());
		assertNotEquals(client2.hashCode(), client4.hashCode());
	}

	/**
	 * Tests equals().
	 */
	@Test
	public void testEquals() {
		Client c1 = new Client("name1", "contact1");
		Client c2 = new Client("name2", "contact1");
		Client c3 = new Client("name1", "contact2");
		Client c4 = new Client("NAME1", "contact1");
		Client c5 = new Client("name1", "CONTACT1");
		Client c6 = new Client("NAME1", "CONTACT1");
		Client c7 = new Client(" name1", "contact1");
		Client c8 = new Client("name1", " contact1");
		Client c9 = new Client(" name1", " contact1");

		assertTrue(c1.equals(c1));
		assertFalse(c1.equals(c2));
		assertFalse(c1.equals(c3));
		assertTrue(c1.equals(c4));
		assertTrue(c1.equals(c5));
		assertTrue(c1.equals(c6));
		assertTrue(c1.equals(c7));
		assertTrue(c1.equals(c8));
		assertTrue(c1.equals(c9));
	}
}