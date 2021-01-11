package edu.ncsu.csc216.travel.model.file_io;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import edu.ncsu.csc216.travel.model.office.TourCoordinator;
import edu.ncsu.csc216.travel.model.vacation.EducationalTrip;
import edu.ncsu.csc216.travel.model.vacation.LandTour;
import edu.ncsu.csc216.travel.model.vacation.RiverCruise;
import edu.ncsu.csc216.travel.model.vacation.Tour;

/**
 * Tests TravelReader.
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public class TravelReaderTest {

	/** Valid test file */
	private final String validTestFile = "test-files/sample.md";

	/** Invalid test file 1 */
	private final String invalidClientTestFile = "test-files/invalid.sample.client.md";
	/** Invalid test file 2 */
	private final String invalidTourTestFile = "test-files/invalid.sample.tour.md";

	/** Expected results for valid Client data */
	private final String validClient1 = "Over the Top Tours (John 413-299-2999 x29)";
	private final String validClient2 = "Tetterton Travels (James T)";
	private final String validClient3 = "Goodnight Travel Agency (Cheryl)";
	private final String validClient4 = "John Doe (John Doe)";
	private final String validClient5 = "Family Adventures (919-239-0000)";

	/** Array to hold expected Client results */
	private final String[] validClients = { validClient1, validClient2, validClient3, validClient4, validClient5 };

	/** Expected results for valid Tour data */
	private final Tour validTour11 = new LandTour("A Week in Tuscany and Umbria", LocalDate.of(2019, 7, 2), 7, 2700,
			10);
	private final Tour validTour1 = new LandTour("Bicycling the Pacific Coast", LocalDate.of(2002, 9, 9), 10, 2000, 8);
	private Tour validTour17 = new EducationalTrip("A Day at MOMA", LocalDate.of(2020, 1, 1), 1, 75, 6);
	private final Tour validTour18 = new EducationalTrip("Birding in Antarctica", LocalDate.of(2020, 1, 12), 10, 8900,
			6);
	private final Tour validTour2 = new LandTour("Great Castles of the British Isles", LocalDate.of(2019, 4, 18), 10,
			3456, 10);
	private final Tour validTour3 = new RiverCruise("Amsterdam to Basel", LocalDate.of(2019, 5, 5), 8, 3999, 20);
	private Tour validTour4 = new EducationalTrip("Go Manhattan", LocalDate.of(2019, 5, 5), 1, 300, 10);
	private final Tour validTour7 = new RiverCruise("Through the Suez Canal", LocalDate.of(2019, 5, 15), 4, 1600, 20);
	private final Tour validTour8 = new LandTour("Great Wall of China", LocalDate.of(2019, 5, 20), 10, 5500, 10);
	private final Tour validTour9 = new EducationalTrip("Basque for a Day", LocalDate.of(2019, 6, 1), 1, 180, 5);
	private Tour validTour10 = new EducationalTrip("Creole Cooking", LocalDate.of(2019, 7, 1), 1, 150, 6);
	private Tour validTour12 = new EducationalTrip("Costa Rican Wildlife", LocalDate.of(2019, 7, 16), 4, 2500, 6);
	private final Tour validTour13 = new LandTour("Budapest, Vienna, Prague in 10 Days", LocalDate.of(2019, 8, 7), 10,
			4200, 10);
	private final Tour validTour14 = new LandTour("Along the Underground Railroad", LocalDate.of(2019, 9, 8), 10, 2500,
			8);
	private final Tour validTour15 = new LandTour("New England in Color", LocalDate.of(2019, 10, 1), 5, 1200, 10);
	private final Tour validTour16 = new LandTour("Warsaw to Krakow", LocalDate.of(2019, 10, 5), 6, 3200, 20);
	private Tour validTour19 = new EducationalTrip("A Day at MOMA", LocalDate.of(2020, 5, 1), 1, 75, 6);
	private final Tour validTour5 = new RiverCruise("Muddy Mississippi - Up Close", LocalDate.of(2019, 5, 6), 5, 2000,
			12);
	private final Tour validTour6 = new EducationalTrip("Go Manhattan", LocalDate.of(2019, 5, 8), 1, 300, 5);

	/** Array to hold expected Tour results */
	private Tour[] validTours = { validTour1, validTour2, validTour3, validTour4, validTour5, validTour6, validTour7,
			validTour8, validTour9, validTour10, validTour11, validTour12, validTour13, validTour14, validTour15,
			validTour16, validTour17, validTour18, validTour19 };

	/** Expected results for valid Reservation data */
	private final String validReservation1 = "000000   3 Tetterton Travels (James T)";
	private final String validReservation2 = "000003   2 Tetterton Travels (James T)";
	private final String validReservation3 = "000004   4 Tetterton Travels (James T)";
	private final String validReservation4 = "000005   2 Goodnight Travel Agency (Cheryl)";
	private final String validReservation5 = "000010   1 John Doe (John Doe)";
	private final String validReservation6 = "000011   3 John Doe (John Doe)";

	/** Array to hold expected Reservation results */
	private String[] validReservations = { validReservation1, validReservation2, validReservation3, validReservation4,
			validReservation5, validReservation6 };

	/**
	 * Tests readTravelData() with a valid test file.
	 */
	@Test
	public void testReadValidTravelData() {
		TourCoordinator tc = TourCoordinator.getInstance();
		tc.flushLists();

		validTours[3].fixCapacity();
		validTours[16].fixCapacity();
		validTours[9].fixCapacity();
		validTours[11].fixCapacity();
		validTours[18].fixCapacity();

		tc.loadFile(validTestFile);

		assertEquals(validClients.length, tc.listClients().length);
		assertEquals(validTours.length, tc.filteredTourData().length);
		assertEquals(validReservations.length,
				tc.reservationsForAClient(0).length + tc.reservationsForAClient(1).length
						+ tc.reservationsForAClient(2).length + tc.reservationsForAClient(3).length
						+ tc.reservationsForAClient(4).length);

		for (int i = 0; i < tc.listClients().length; i++) {
			assertEquals(validClients[i], tc.listClients()[i]);
		}

		for (int i = 0; i < tc.filteredTourData().length; i++) {
			assertEquals(validTours[i].getAllData()[0], tc.filteredTourData()[i][0]);
			assertEquals(validTours[i].getAllData()[1], tc.filteredTourData()[i][1]);
			assertEquals(validTours[i].getAllData()[2], tc.filteredTourData()[i][2]);
			assertEquals(validTours[i].getAllData()[3], tc.filteredTourData()[i][3]);
			assertEquals(validTours[i].getAllData()[4], tc.filteredTourData()[i][4]);
		}

		assertEquals(validReservations[0], tc.reservationsForATour(2)[0]);
		assertEquals(validReservations[1], tc.reservationsForATour(15)[0]);
		assertEquals(validReservations[2], tc.reservationsForATour(13)[0]);
		assertEquals(validReservations[3], tc.reservationsForATour(13)[1]);
		assertEquals(validReservations[4], tc.reservationsForATour(13)[2]);
		assertEquals(validReservations[5], tc.reservationsForATour(10)[0]);
	}

	/**
	 * Tests readTravelData() with an invalid test file.
	 */
	@Test
	public void testReadInvalidTravelData() {
		TourCoordinator tc = TourCoordinator.getInstance();
		tc.flushLists();

		assertEquals(0, tc.listClients().length);

		try {
			tc.loadFile("blahblah.md");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, tc.listClients().length);
		}

		try {
			tc.loadFile("test-files/invalid1.md");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, tc.listClients().length);
		}

		try {
			tc.loadFile(invalidClientTestFile);
			fail();
		} catch (Exception e) {
			assertEquals(0, tc.listClients().length);
			assertEquals(0, tc.filteredTourData().length);
		}

		try {
			tc.loadFile(invalidTourTestFile);
			fail();
		} catch (Exception e) {
			assertEquals(0, tc.listClients().length);
			assertEquals(0, tc.filteredTourData().length);
		}
	}
}