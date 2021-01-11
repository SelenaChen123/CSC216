package edu.ncsu.csc216.travel.model.office;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.travel.model.participants.Client;
import edu.ncsu.csc216.travel.model.vacation.CapacityException;
import edu.ncsu.csc216.travel.model.vacation.EducationalTrip;
import edu.ncsu.csc216.travel.model.vacation.LandTour;
import edu.ncsu.csc216.travel.model.vacation.Reservation;
import edu.ncsu.csc216.travel.model.vacation.RiverCruise;
import edu.ncsu.csc216.travel.model.vacation.Tour;

/**
 * Tests TourCoordinator.
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public class TourCoordinatorTest {

	/** Instance of TourCoordinator to be used in tests */
	private TourCoordinator tc;

	/**
	 * Tests getInstance().
	 */
	@Test
	public void testGetInstance() {
		tc = TourCoordinator.getInstance();
		tc.flushLists();
		assertEquals(0, tc.filteredTourData().length);
		assertEquals(0, tc.listClients().length);
	}

	/**
	 * Tests addNewTour().
	 */
	@Test
	public void testAddNewTour() {
		tc = TourCoordinator.getInstance();
		tc.flushLists();
		assertEquals(0, tc.filteredTourData().length);

		try {
			tc.addNewTour("RC", "River Cruise", LocalDate.of(2019, 1, 1), 3, 100, 5);
		} catch (DuplicateTourException dte) {
			fail();
		}
		assertEquals(1, tc.filteredTourData().length);

		try {
			tc.addNewTour("LT", "Land Tour", LocalDate.of(2019, 2, 1), 3, 100, 5);
		} catch (DuplicateTourException dte) {
			fail();
		}
		assertEquals(2, tc.filteredTourData().length);

		try {
			tc.addNewTour("ED", "Educational Trip", LocalDate.of(2019, 3, 1), 3, 100, 5);
		} catch (DuplicateTourException dte) {
			fail();
		}
		assertEquals(3, tc.filteredTourData().length);

		try {
			tc.addNewTour("ED", "Educational Trip", LocalDate.of(2019, 3, 1), 3, 100, 5);
			fail();
		} catch (DuplicateTourException dte) {
			assertEquals(3, tc.filteredTourData().length);
		}

		try {
			tc.addNewTour("LT", "Land Tour", LocalDate.of(2019, 2, 1), 3, 100, 5);
			fail();
		} catch (DuplicateTourException dte) {
			assertEquals(3, tc.filteredTourData().length);
		}

		try {
			tc.addNewTour("RC", "River Cruise", LocalDate.of(2019, 1, 1), 3, 100, 5);
			fail();
		} catch (DuplicateTourException dte) {
			assertEquals(3, tc.filteredTourData().length);
		}
	}

	/**
	 * Tests addNewClient().
	 */
	@Test
	public void testAddNewClient() {
		tc = TourCoordinator.getInstance();
		tc.flushLists();
		assertEquals(0, tc.listClients().length);

		try {
			tc.addNewClient("name 1", "contact 1");
		} catch (DuplicateClientException e) {
			fail();
		}
		assertEquals(1, tc.listClients().length);

		try {
			tc.addNewClient("name 2", "contact 2");
		} catch (DuplicateClientException e) {
			fail();
		}
		assertEquals(2, tc.listClients().length);

		try {
			tc.addNewClient("name 3", "contact 3");
		} catch (DuplicateClientException e) {
			fail();
		}
		assertEquals(3, tc.listClients().length);

		try {
			tc.addNewClient("name 3", "contact 3");
			fail();
		} catch (DuplicateClientException e) {
			assertEquals(3, tc.listClients().length);
		}

		try {
			tc.addNewClient("name 2", "contact 2");
			fail();
		} catch (DuplicateClientException e) {
			assertEquals(3, tc.listClients().length);
		}

		try {
			tc.addNewClient("name 1", "contact 1");
			fail();
		} catch (DuplicateClientException e) {
			assertEquals(3, tc.listClients().length);
		}
	}

	/**
	 * Tests setFilters().
	 */
	@Test
	public void testSetFilters() {
		tc = TourCoordinator.getInstance();
		tc.flushLists();

		tc.setFilters("RC", 1, 3);
		assertEquals(0, tc.filteredTourData().length);

		try {
			tc.addNewTour("RC", "River Cruise 1", LocalDate.of(2019, 1, 1), 5, 100, 5);
		} catch (DuplicateTourException e) {
			fail();
		}
		assertEquals(0, tc.filteredTourData().length);

		try {
			tc.addNewTour("RC", "River Cruise 2", LocalDate.of(2019, 2, 1), 1, 100, 5);
		} catch (DuplicateTourException e) {
			fail();
		}
		assertEquals(1, tc.filteredTourData().length);

		try {
			tc.addNewTour("RC", "River Cruise 3", LocalDate.of(2019, 3, 1), 3, 100, 5);
		} catch (DuplicateTourException e) {
			fail();
		}
		assertEquals(2, tc.filteredTourData().length);

		try {
			tc.addNewTour("LT", "Land Tour", LocalDate.of(2019, 2, 1), 2, 100, 5);
		} catch (DuplicateTourException e) {
			fail();
		}
		assertEquals(2, tc.filteredTourData().length);

		try {
			tc.addNewTour("ED", "Educational Trip", LocalDate.of(2019, 1, 1), 2, 100, 5);
		} catch (DuplicateTourException e) {
			fail();
		}
		assertEquals(2, tc.filteredTourData().length);

		tc.setFilters("ED", 1, 3);
		assertEquals(1, tc.filteredTourData().length);

		tc.setFilters("LT", 1, 3);
		assertEquals(1, tc.filteredTourData().length);

		tc.setFilters("RC", 3, 5);
		assertEquals(2, tc.filteredTourData().length);
	}

	/**
	 * Tests addNewReservation().
	 */
	@Test
	public void testAddNewReservation() {
		tc = TourCoordinator.getInstance();
		tc.flushLists();
		assertEquals(0, tc.filteredTourData().length);
		assertEquals(0, tc.listClients().length);

		try {
			tc.addNewTour("RC", "River Cruise", LocalDate.of(2019, 1, 1), 3, 100, 5);
			tc.addNewTour("LT", "Land Tour", LocalDate.of(2019, 2, 1), 3, 100, 5);
			tc.addNewTour("ED", "Educational Trip", LocalDate.of(2019, 3, 1), 3, 100, 5);
		} catch (DuplicateTourException e) {
			fail();
		}
		assertEquals(3, tc.filteredTourData().length);

		try {
			tc.addNewClient("name 1", "contact 1");
			tc.addNewClient("name 2", "contact 2");
			tc.addNewClient("name 3", "contact 3");
		} catch (DuplicateClientException e) {
			fail();
		}
		assertEquals(3, tc.listClients().length);

		try {
			tc.addNewReservation(0, 0, 1);
			tc.addNewReservation(1, 1, 1);
			tc.addNewReservation(2, 2, 1);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(3, tc.reservationsForATour(0).length + tc.reservationsForATour(1).length
				+ tc.reservationsForATour(2).length);
		assertEquals(3, tc.reservationsForAClient(0).length + tc.reservationsForAClient(1).length
				+ tc.reservationsForAClient(2).length);
	}

	/**
	 * Tests addOldReservation().
	 */
	@Test
	public void testAddOldReservation() {
		tc = TourCoordinator.getInstance();
		tc.flushLists();

		Tour rc = null;
		Tour lt = null;
		Tour ed = null;
		try {
			rc = tc.addNewTour("RC", "River Cruise", LocalDate.of(2019, 1, 1), 3, 100, 5);
			lt = tc.addNewTour("LT", "Land Tour", LocalDate.of(2019, 2, 2), 3, 100, 5);
			ed = tc.addNewTour("ED", "Educational Trip", LocalDate.of(2019, 3, 1), 3, 100, 5);
		} catch (DuplicateTourException e) {
			fail();
		}
		assertEquals(3, tc.filteredTourData().length);

		Client client1 = null;
		Client client2 = null;
		Client client3 = null;
		try {
			client1 = tc.addNewClient("name 1", "contact 1");
			client2 = tc.addNewClient("name 2", "contact 2");
			client3 = tc.addNewClient("name 3", "contact 3");
		} catch (DuplicateClientException e) {
			fail();
		}
		assertEquals(3, tc.listClients().length);

		try {
			tc.addOldReservation(client1, rc, 1, 000001);
			tc.addOldReservation(client2, lt, 1, 000002);
			tc.addOldReservation(client3, ed, 1, 000004);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(3, tc.reservationsForATour(0).length + tc.reservationsForATour(1).length
				+ tc.reservationsForATour(2).length);
		assertEquals(3, tc.reservationsForAClient(0).length + tc.reservationsForAClient(1).length
				+ tc.reservationsForAClient(2).length);
	}

	/**
	 * Tests cancelReservation().
	 */
	@Test
	public void testCancelReservation() {
		tc = TourCoordinator.getInstance();
		tc.flushLists();

		try {
			tc.addNewClient("name 1", "contact 1");
			tc.addNewClient("name 2", "contact 2");
			tc.addNewClient("name 3", "contact 3");
		} catch (DuplicateClientException e) {
			fail();
		}

		try {
			tc.addNewTour("RC", "River Cruise", LocalDate.of(2019, 1, 1), 3, 100, 5);
			tc.addNewTour("LT", "Land Tour", LocalDate.of(2019, 2, 1), 3, 100, 5);
			tc.addNewTour("ED", "Educational Trip", LocalDate.of(2019, 3, 1), 3, 100, 5);
		} catch (DuplicateTourException e) {
			fail();
		}

		try {
			tc.addNewReservation(0, 0, 1);
			tc.addNewReservation(0, 1, 1);
			tc.addNewReservation(0, 2, 1);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(3, tc.reservationsForATour(0).length + tc.reservationsForATour(1).length
				+ tc.reservationsForATour(2).length);
		assertEquals(3, tc.reservationsForAClient(0).length);

		tc.cancelReservation(0, 2);
		tc.cancelReservation(0, 1);
		tc.cancelReservation(0, 0);

		assertEquals(0, tc.reservationsForATour(0).length + tc.reservationsForATour(1).length
				+ tc.reservationsForATour(2).length);
		assertEquals(0, tc.reservationsForAClient(0).length);

		try {
			tc.addNewReservation(1, 0, 1);
			tc.addNewReservation(1, 1, 1);
			tc.addNewReservation(1, 2, 1);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(3, tc.reservationsForATour(0).length + tc.reservationsForATour(1).length
				+ tc.reservationsForATour(2).length);
		assertEquals(3, tc.reservationsForAClient(1).length);

		tc.cancelReservation(1, 2);
		tc.cancelReservation(1, 1);
		tc.cancelReservation(1, 0);

		assertEquals(0, tc.reservationsForATour(0).length + tc.reservationsForATour(1).length
				+ tc.reservationsForATour(2).length);
		assertEquals(0, tc.reservationsForAClient(1).length);

		try {
			tc.addNewReservation(2, 0, 1);
			tc.addNewReservation(2, 1, 1);
			tc.addNewReservation(2, 2, 1);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(3, tc.reservationsForATour(0).length + tc.reservationsForATour(1).length
				+ tc.reservationsForATour(2).length);
		assertEquals(3, tc.reservationsForAClient(2).length);

		tc.cancelReservation(2, 2);
		tc.cancelReservation(2, 1);
		tc.cancelReservation(2, 0);

		assertEquals(0, tc.reservationsForATour(0).length + tc.reservationsForATour(1).length
				+ tc.reservationsForATour(2).length);
		assertEquals(0, tc.reservationsForAClient(2).length);
	}

	/**
	 * Tests cancelTour().
	 */
	@Test
	public void testCancelTour() {
		tc = TourCoordinator.getInstance();
		tc.flushLists();

		try {
			tc.addNewTour("RC", "River Cruise", LocalDate.of(2019, 1, 1), 3, 100, 5);
			tc.addNewTour("LT", "Land Tour", LocalDate.of(2019, 2, 1), 3, 100, 5);
			tc.addNewTour("ED", "Educational Trip", LocalDate.of(2019, 3, 1), 3, 100, 5);
		} catch (DuplicateTourException e) {
			fail();
		}
		assertEquals(3, tc.filteredTourData().length);

		tc.cancelTour(2);
		tc.cancelTour(1);
		tc.cancelTour(0);

		assertEquals(0, tc.filteredTourData().length);

		try {
			tc.addNewTour("RC", "River Cruise", LocalDate.of(2019, 1, 1), 1, 100, 5);
			tc.addNewTour("LT", "Land Tour", LocalDate.of(2019, 2, 1), 3, 100, 5);
			tc.addNewTour("ED", "Educational Trip", LocalDate.of(2019, 3, 1), 3, 100, 5);
		} catch (DuplicateTourException e) {
			fail();
		}
		assertEquals(3, tc.filteredTourData().length);

		tc.setFilters("", 2, 3);
		assertEquals(2, tc.filteredTourData().length);

		tc.cancelTour(1);
		tc.cancelTour(0);
		assertEquals(0, tc.filteredTourData().length);

		tc.flushLists();

		try {
			tc.addNewTour("RC", "River Cruise", LocalDate.of(2019, 1, 1), 3, 100, 5);
			tc.addNewTour("LT", "Land Tour", LocalDate.of(2019, 2, 1), 3, 100, 5);
		} catch (DuplicateTourException e) {
			fail();
		}
		assertEquals(2, tc.filteredTourData().length);

		try {
			tc.addNewClient("name 1", "contact 1");
		} catch (DuplicateClientException e) {
			fail();
		}
		assertEquals(1, tc.listClients().length);

		try {
			tc.addNewReservation(0, 0, 1);
			tc.addNewReservation(0, 1, 1);
			tc.addNewReservation(0, 1, 1);
			tc.addNewReservation(0, 1, 1);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(4, tc.reservationsForAClient(0).length);
		assertEquals(1, tc.reservationsForATour(0).length);
		assertEquals(3, tc.reservationsForATour(1).length);

		tc.cancelTour(1);
		assertEquals(1, tc.reservationsForAClient(0).length);
	}

	/**
	 * Tests totalClientCost().
	 */
	@Test
	public void testTotalClientCost() {
		tc = TourCoordinator.getInstance();
		tc.flushLists();

		try {
			tc.addNewClient("name", "contact");
		} catch (DuplicateClientException e1) {
			fail();
		}

		try {
			tc.addNewTour("RC", "River Cruise 1", LocalDate.of(2019, 1, 1), 3, 100, 5);
			tc.addNewTour("LT", "Land Tour 1", LocalDate.of(2019, 2, 1), 3, 100, 5);
			tc.addNewTour("ED", "Educational Trip", LocalDate.of(2019, 3, 1), 3, 100, 5);
			tc.addNewTour("LT", "Land Tour 2", LocalDate.of(2019, 4, 1), 3, 100, 5);
			tc.addNewTour("RC", "River Cruise 2", LocalDate.of(2019, 5, 1), 3, 100, 5);
			tc.addNewTour("RC", "River Cruise 3", LocalDate.of(2019, 6, 1), 3, 100, 5);
		} catch (DuplicateTourException e) {
			fail();
		}

		try {
			tc.addNewReservation(0, 0, 1);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(150.0, tc.totalClientCost(0), 0.01);

		try {
			tc.addNewReservation(0, 1, 1);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(275.0, tc.totalClientCost(0), 0.01);

		try {
			tc.addNewReservation(0, 2, 1);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(375.0, tc.totalClientCost(0), 0.01);

		try {
			tc.addNewReservation(0, 3, 2);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(575.0, tc.totalClientCost(0), 0.01);

		try {
			tc.addNewReservation(0, 4, 2);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(775.0, tc.totalClientCost(0), 0.01);

		try {
			tc.addNewReservation(0, 5, 3);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(1125.0, tc.totalClientCost(0), 0.01);
	}

	/**
	 * Tests listClients().
	 */
	@Test
	public void testListClients() {
		tc = TourCoordinator.getInstance();
		tc.flushLists();
		assertEquals(0, tc.listClients().length);

		try {
			tc.addNewClient("name 1", "contact 1");
			tc.addNewClient("name 2", "contact 2");
			tc.addNewClient("name 3", "contact 3");
		} catch (DuplicateClientException e) {
			fail();
		}
		assertEquals(3, tc.listClients().length);

		assertEquals("name 1 (contact 1)", tc.listClients()[0]);
		assertEquals("name 2 (contact 2)", tc.listClients()[1]);
		assertEquals("name 3 (contact 3)", tc.listClients()[2]);
	}

	/**
	 * Tests filteredTourData().
	 */
	@Test
	public void testFilteredTourData() {
		tc = TourCoordinator.getInstance();
		tc.flushLists();
		assertEquals(0, tc.filteredTourData().length);

		Tour rc = null;
		Tour lt = null;
		Tour ed = null;
		try {
			rc = tc.addNewTour("RC", "River Cruise", LocalDate.of(2019, 1, 1), 3, 100, 5);
			lt = tc.addNewTour("LT", "Land Tour", LocalDate.of(2019, 2, 1), 3, 100, 5);
			ed = tc.addNewTour("ED", "Educational Trip", LocalDate.of(2019, 3, 1), 3, 100, 5);
		} catch (DuplicateTourException e) {
			fail();
		}
		assertEquals(3, tc.filteredTourData().length);

		Object[] rcData = rc.getAllData();
		Object[] ltData = lt.getAllData();
		Object[] edData = ed.getAllData();

		assertEquals(rcData[0], tc.filteredTourData()[0][0]);
		assertEquals(rcData[1], tc.filteredTourData()[0][1]);
		assertEquals(rcData[2], tc.filteredTourData()[0][2]);
		assertEquals(rcData[3], tc.filteredTourData()[0][3]);
		assertEquals(rcData[4], tc.filteredTourData()[0][4]);

		assertEquals(ltData[0], tc.filteredTourData()[1][0]);
		assertEquals(ltData[1], tc.filteredTourData()[1][1]);
		assertEquals(ltData[2], tc.filteredTourData()[1][2]);
		assertEquals(ltData[3], tc.filteredTourData()[1][3]);
		assertEquals(ltData[4], tc.filteredTourData()[1][4]);

		assertEquals(edData[0], tc.filteredTourData()[2][0]);
		assertEquals(edData[1], tc.filteredTourData()[2][1]);
		assertEquals(edData[2], tc.filteredTourData()[2][2]);
		assertEquals(edData[3], tc.filteredTourData()[2][3]);
		assertEquals(edData[4], tc.filteredTourData()[2][4]);
	}

	/**
	 * Tests reservationsForAClient().
	 */
	@Test
	public void testReservationsForAClient() {
		tc = TourCoordinator.getInstance();
		tc.flushLists();
		assertEquals(0, tc.listClients().length);

		try {
			tc.addNewClient("name", "contact");
		} catch (DuplicateClientException e) {
			fail();
		}
		assertEquals(1, tc.listClients().length);

		try {
			tc.addNewTour("RC", "River Cruise", LocalDate.of(2019, 1, 1), 3, 100, 5);
			tc.addNewTour("LT", "Land Tour", LocalDate.of(2019, 2, 1), 3, 100, 5);
			tc.addNewTour("ED", "Educational Trip", LocalDate.of(2019, 3, 1), 3, 100, 5);
		} catch (DuplicateTourException e) {
			fail();
		}
		assertEquals(3, tc.filteredTourData().length);

		try {
			tc.addNewReservation(0, 0, 1);
			tc.addNewReservation(0, 1, 1);
			tc.addNewReservation(0, 2, 1);
		} catch (CapacityException e) {
			fail();
		}

		assertEquals("000000   1 RC-River Cruise: 01/01/19 3 days", tc.reservationsForAClient(0)[0]);
		assertEquals("000001   1 LT-Land Tour: 02/01/19 3 days", tc.reservationsForAClient(0)[1]);
		assertEquals("000002   1 ED-Educational Trip: 03/01/19 3 days", tc.reservationsForAClient(0)[2]);
	}

	/**
	 * Tests reservationsForATour().
	 */
	@Test
	public void testReservationsForATour() {
		tc = TourCoordinator.getInstance();
		tc.flushLists();
		assertEquals(0, tc.filteredTourData().length);

		try {
			tc.addNewClient("name 1", "contact 1");
			tc.addNewClient("name 2", "contact 2");
			tc.addNewClient("name 3", "contact 3");
		} catch (DuplicateClientException e) {
			fail();
		}
		assertEquals(3, tc.listClients().length);

		try {
			tc.addNewTour("RC", "River Cruise", LocalDate.of(2019, 1, 1), 3, 100, 5);
			tc.addNewTour("LT", "Land Tour", LocalDate.of(2019, 2, 1), 3, 100, 5);
			tc.addNewTour("ED", "Educational Trip", LocalDate.of(2019, 3, 1), 3, 100, 5);
		} catch (DuplicateTourException e) {
			fail();
		}
		assertEquals(3, tc.filteredTourData().length);

		try {
			tc.addNewReservation(0, 0, 1);
			tc.addNewReservation(1, 0, 1);
			tc.addNewReservation(2, 0, 1);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(3, tc.reservationsForATour(0).length);

		try {
			tc.addNewReservation(0, 1, 1);
			tc.addNewReservation(1, 1, 1);
			tc.addNewReservation(2, 1, 1);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(3, tc.reservationsForATour(1).length);

		try {
			tc.addNewReservation(0, 2, 1);
			tc.addNewReservation(1, 2, 1);
			tc.addNewReservation(2, 2, 1);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(3, tc.reservationsForATour(2).length);

		assertEquals(9, tc.reservationsForATour(0).length + tc.reservationsForATour(1).length
				+ tc.reservationsForATour(2).length);
	}

	/**
	 * Tests getFilename().
	 */
	@Test
	public void testGetFilename() {
		tc = TourCoordinator.getInstance();
		tc.flushLists();
		assertNull(tc.getFilename());

		tc.saveFile("test-files/filename.test.md");
		assertEquals("test-files/filename.test.md", tc.getFilename());
	}

	/**
	 * Tests loadFile().
	 */
	@Test
	public void testLoadFile() {
		tc = TourCoordinator.getInstance();
		tc.flushLists();

		tc.loadFile("test-files/load.file.test.md");

		assertEquals(2, tc.listClients().length);
		assertEquals("name 1 (contact 1)", tc.listClients()[0]);
		assertEquals("name 2 (contact 2)", tc.listClients()[1]);

		assertEquals(3, tc.filteredTourData().length);
		Tour rc = new RiverCruise("Amsterdam to Basel", LocalDate.of(2019, 5, 5), 8, 3999, 20);
		assertEquals(rc.getAllData()[0], tc.filteredTourData()[0][0]);
		assertEquals(rc.getAllData()[1], tc.filteredTourData()[0][1]);
		assertEquals(rc.getAllData()[2], tc.filteredTourData()[0][2]);
		assertEquals(rc.getAllData()[3], tc.filteredTourData()[0][3]);
		assertEquals(rc.getAllData()[4], tc.filteredTourData()[0][4]);
		Tour lt = new LandTour("A Week in Tuscany and Umbria", LocalDate.of(2019, 7, 2), 7, 2700, 10);
		assertEquals(lt.getAllData()[0], tc.filteredTourData()[1][0]);
		assertEquals(lt.getAllData()[1], tc.filteredTourData()[1][1]);
		assertEquals(lt.getAllData()[2], tc.filteredTourData()[1][2]);
		assertEquals(lt.getAllData()[3], tc.filteredTourData()[1][3]);
		assertEquals(lt.getAllData()[4], tc.filteredTourData()[1][4]);
		Tour ed = new EducationalTrip("Birding in Antarctica", LocalDate.of(2020, 1, 12), 10, 8900, 6);
		assertEquals(ed.getAllData()[0], tc.filteredTourData()[2][0]);
		assertEquals(ed.getAllData()[1], tc.filteredTourData()[2][1]);
		assertEquals(ed.getAllData()[2], tc.filteredTourData()[2][2]);
		assertEquals(ed.getAllData()[3], tc.filteredTourData()[2][3]);
		assertEquals(ed.getAllData()[4], tc.filteredTourData()[2][4]);

		assertEquals(1, tc.reservationsForATour(0).length);
		Reservation reservation = new Reservation(rc, new Client("name 1", "contact 1"), 3);
		assertEquals(reservation.displayReservationClient(), tc.reservationsForATour(0)[0]);
	}

	/**
	 * Tests saveFile().
	 */
	@Test
	public void testSaveFile() {
		tc = TourCoordinator.getInstance();
		tc.flushLists();

		try {
			tc.addNewClient("name 1", "contact 1");
			tc.addNewClient("name 2", "contact 2");
		} catch (DuplicateClientException e) {
			fail();
		}

		try {
			tc.addNewTour("RC", "Amsterdam to Basel", LocalDate.of(2019, 5, 5), 8, 3999, 20);
			tc.addNewTour("LT", "A Week in Tuscany and Umbria", LocalDate.of(2019, 7, 2), 7, 2700, 10);
			tc.addNewTour("ED", "Birding in Antarctica", LocalDate.of(2020, 1, 12), 10, 8900, 6);
		} catch (DuplicateTourException e) {
			fail();
		}

		try {
			tc.addNewReservation(0, 0, 3);
		} catch (CapacityException e) {
			fail();
		}

		tc.saveFile("test-files/act.save.file.test.md");

		checkFiles("test-files/exp.save.file.test.md", "test-files/act.save.file.test.md");
	}

	/**
	 * Tests flushLists().
	 */
	@Test
	public void testFlushLists() {
		tc = TourCoordinator.getInstance();
		tc.flushLists();

		try {
			tc.addNewTour("RC", "River Cruise", LocalDate.of(2019, 1, 1), 3, 100, 5);
			tc.addNewTour("LT", "Land Tour", LocalDate.of(2019, 2, 1), 3, 100, 5);
			tc.addNewTour("ED", "Educational Trip", LocalDate.of(2019, 3, 1), 3, 100, 5);
		} catch (DuplicateTourException e) {
			fail();
		}
		assertEquals(3, tc.filteredTourData().length);

		try {
			tc.addNewClient("name 1", "contact 1");
			tc.addNewClient("name 2", "contact 2");
			tc.addNewClient("name 3", "contact 3");
		} catch (DuplicateClientException e) {
			fail();
		}
		assertEquals(3, tc.listClients().length);

		try {
			tc.addNewReservation(0, 0, 1);
			tc.addNewReservation(1, 1, 1);
			tc.addNewReservation(2, 2, 1);
		} catch (CapacityException e) {
			fail();
		}
		assertEquals(3, tc.reservationsForATour(0).length + tc.reservationsForATour(1).length
				+ tc.reservationsForATour(2).length);
		assertEquals(3, tc.reservationsForAClient(0).length + tc.reservationsForAClient(1).length
				+ tc.reservationsForAClient(2).length);

		tc.flushLists();
		assertEquals(0, tc.filteredTourData().length);
		assertEquals(0, tc.listClients().length);

	}

	/**
	 * Tests dataShouldBeSaved().
	 */
	@Test
	public void testDataShouldBeSaved() {
		tc = TourCoordinator.getInstance();
		tc.flushLists();

		assertFalse(tc.dataShouldBeSaved());
	}

	/**
	 * Helper method to compare two files for the same contents
	 * 
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new File(expFile));
			Scanner actScanner = new Scanner(new File(actFile));

			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}