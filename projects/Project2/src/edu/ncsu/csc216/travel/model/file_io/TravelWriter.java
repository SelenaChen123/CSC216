package edu.ncsu.csc216.travel.model.file_io;

import java.io.File;
import java.io.PrintStream;

import edu.ncsu.csc216.travel.model.office.TourCoordinator;

/**
 * A class for writing Wolf Travel data files.
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public class TravelWriter {

	/**
	 * Writes WolfTravel data to a file.
	 * 
	 * @param fileName Name of the file to be written to
	 */
	public static void writeTravelData(String fileName) {
		PrintStream fileWriter = null;
		try {
			fileWriter = new PrintStream(new File(fileName));

			TourCoordinator tc = TourCoordinator.getInstance();
			String[] clients = tc.listClients();
			Object[][] tourInfo = tc.filteredTourData();

			for (int i = 0; i < clients.length; i++) {
				fileWriter.println(clients[i]);
			}
			for (int i = 0; i < tourInfo.length; i++) {
				fileWriter.println("#" + tourInfo[i][0] + ":  " + tourInfo[i][1] + "  " + tourInfo[i][2] + "   "
						+ tourInfo[i][3] + "  " + tourInfo[i][4]);
				String[] reservations = tc.reservationsForATour(i);
				for (int j = 0; j < reservations.length; j++) {
					fileWriter.println(reservations[j]);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException();
		} finally {
			if (fileWriter != null) {
				fileWriter.close();
			}
		}
	}
}