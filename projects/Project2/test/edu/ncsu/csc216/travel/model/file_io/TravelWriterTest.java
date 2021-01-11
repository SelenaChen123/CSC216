package edu.ncsu.csc216.travel.model.file_io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.travel.model.office.DuplicateClientException;
import edu.ncsu.csc216.travel.model.office.DuplicateTourException;
import edu.ncsu.csc216.travel.model.office.TourCoordinator;
import edu.ncsu.csc216.travel.model.participants.Client;
import edu.ncsu.csc216.travel.model.vacation.CapacityException;
import edu.ncsu.csc216.travel.model.vacation.Tour;

/**
 * Tests TravelWriter.
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public class TravelWriterTest {

	/**
	 * Tests writeTravelData().
	 */
	@Test
	public void testWriteTravelData() {
		TourCoordinator tc = TourCoordinator.getInstance();
		tc.flushLists();

		Client tt = null;
		Client gta = null;
		Client jd = null;
		try {
			tc.addNewClient("Over the Top Tours", "John 413-299-2999 x29");
			tt = tc.addNewClient("Tetterton Travels", "James T");
			gta = tc.addNewClient("Goodnight Travel Agency", "Cheryl");
			jd = tc.addNewClient("John Doe", "John Doe");
			tc.addNewClient("Family Adventures", "919-239-0000");
		} catch (DuplicateClientException e) {
			fail();
		}

		Tour rc = null;
		Tour lt1 = null;
		Tour lt2 = null;
		Tour lt3 = null;
		Tour ed1 = null;
		Tour ed2 = null;
		Tour ed3 = null;
		Tour ed4 = null;
		Tour ed5 = null;
		try {
			tc.addNewTour("LT", "Bicycling the Pacific Coast", LocalDate.of(2002, 9, 9), 10, 2000, 8);
			tc.addNewTour("LT", "Great Castles of the British Isles", LocalDate.of(2019, 4, 18), 10, 3456, 10);
			rc = tc.addNewTour("RC", "Amsterdam to Basel", LocalDate.of(2019, 5, 5), 8, 3999, 20);
			ed1 = tc.addNewTour("ED", "Go Manhattan", LocalDate.of(2019, 5, 5), 1, 300, 5); // HALVED
			tc.addNewTour("RC", "Muddy Mississippi - Up Close", LocalDate.of(2019, 5, 6), 5, 2000, 12);
			tc.addNewTour("ED", "Go Manhattan", LocalDate.of(2019, 5, 8), 1, 300, 5);
			tc.addNewTour("RC", "Through the Suez Canal", LocalDate.of(2019, 5, 15), 4, 1600, 20);
			tc.addNewTour("LT", "Great Wall of China", LocalDate.of(2019, 5, 20), 10, 5500, 10);
			tc.addNewTour("ED", "Basque for a Day", LocalDate.of(2019, 6, 1), 1, 180, 5);
			ed2 = tc.addNewTour("ED", "Creole Cooking", LocalDate.of(2019, 7, 1), 1, 150, 3); // HALVED
			lt1 = tc.addNewTour("LT", "A Week in Tuscany and Umbria", LocalDate.of(2019, 7, 2), 7, 2700, 10);
			ed3 = tc.addNewTour("ED", "Costa Rican Wildlife", LocalDate.of(2019, 7, 16), 4, 2500, 3); // HALVED
			tc.addNewTour("LT", "Budapest, Vienna, Prague in 10 Days", LocalDate.of(2019, 8, 7), 10, 4200, 10);
			lt2 = tc.addNewTour("LT", "Along the Underground Railroad", LocalDate.of(2019, 9, 8), 10, 2500, 8);
			tc.addNewTour("LT", "New England in Color", LocalDate.of(2019, 10, 1), 5, 1200, 10);
			lt3 = tc.addNewTour("LT", "Warsaw to Krakow", LocalDate.of(2019, 10, 5), 6, 3200, 20);
			ed4 = tc.addNewTour("ED", "A Day at MOMA", LocalDate.of(2020, 1, 1), 1, 75, 3); // HALVED
			tc.addNewTour("ED", "Birding in Antarctica", LocalDate.of(2020, 1, 12), 10, 8900, 6);
			ed5 = tc.addNewTour("ED", "A Day at MOMA", LocalDate.of(2020, 5, 1), 1, 75, 3); // HALVED
		} catch (DuplicateTourException e) {
			fail();
		}

		try {
			tc.addOldReservation(jd, ed1, 5, 0);
			tc.cancelReservation(3, 0);

			tc.addOldReservation(jd, ed2, 3, 0);
			tc.cancelReservation(3, 0);

			tc.addOldReservation(jd, ed3, 3, 0);
			tc.cancelReservation(3, 0);

			tc.addOldReservation(jd, ed4, 3, 0);
			tc.cancelReservation(3, 0);

			tc.addOldReservation(jd, ed5, 3, 0);
			tc.cancelReservation(3, 0);

			tc.addOldReservation(tt, rc, 3, 0);
			tc.addOldReservation(tt, lt3, 2, 3);
			tc.addOldReservation(tt, lt2, 4, 4);
			tc.addOldReservation(gta, lt2, 2, 5);
			tc.addOldReservation(jd, lt2, 1, 10);
			tc.addOldReservation(jd, lt1, 3, 11);
		} catch (CapacityException e) {
			fail();
		}

		tc.saveFile("test-files/actual.sample.out.md");

		checkFiles("test-files/sample.out.md", "test-files/actual.sample.out.md");
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