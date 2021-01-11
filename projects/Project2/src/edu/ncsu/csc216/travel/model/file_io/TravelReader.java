package edu.ncsu.csc216.travel.model.file_io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

import edu.ncsu.csc216.travel.list_utils.SimpleArrayList;
import edu.ncsu.csc216.travel.model.office.DuplicateClientException;
import edu.ncsu.csc216.travel.model.office.DuplicateTourException;
import edu.ncsu.csc216.travel.model.office.TourCoordinator;
import edu.ncsu.csc216.travel.model.participants.Client;
import edu.ncsu.csc216.travel.model.vacation.CapacityException;
import edu.ncsu.csc216.travel.model.vacation.Reservation;
import edu.ncsu.csc216.travel.model.vacation.Tour;

/**
 * A class for reading Wolf Travel data files.
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public class TravelReader {

	/**
	 * Reads Wolf Travel data from a file.
	 * 
	 * @param fileName Name of the file to be read from
	 */
	public static void readTravelData(String fileName) {
		Scanner file = null;

		SimpleArrayList<Client> clients = new SimpleArrayList<Client>();
		SimpleArrayList<Tour> tours = new SimpleArrayList<Tour>();
		SimpleArrayList<Reservation> reservations = new SimpleArrayList<Reservation>();

		try {
			file = new Scanner(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		}
		boolean allClientsRead = false;
		boolean tourRead = false;

		Tour latestTour = null;

		while (file.hasNextLine()) {
			String line = file.nextLine();
			if (line.trim().equals("")) {
				continue;
			}
			if (Character.isLetter(line.trim().charAt(0)) && !allClientsRead) {
				try {
					Client client = readClient(line, clients);
					clients.add(client);
				} catch (Exception e) {
					TourCoordinator.getInstance().flushLists();
					throw new IllegalArgumentException();
				}

			} else if (line.trim().charAt(0) == '#') {
				allClientsRead = true;
				try {
					latestTour = readTour(line, tours);
					tours.add(latestTour);
				} catch (Exception e) {
					TourCoordinator.getInstance().flushLists();
					throw new IllegalArgumentException();
				}
				tourRead = true;
			} else if (tourRead) {
				try {
					Reservation reservation = readReservation(line, latestTour, clients);
					reservations.add(reservation);
				} catch (Exception e) {
					TourCoordinator.getInstance().flushLists();
					throw new IllegalArgumentException();
				}
				Reservation.resetCodeGenerator();
			} else {
				TourCoordinator.getInstance().flushLists();
				throw new IllegalArgumentException();
			}
		}
	}

	private static Client readClient(String line, SimpleArrayList<Client> clients) throws DuplicateClientException {
		String name = "";
		String contact = "";

		try {
			name = line.substring(0, line.indexOf('(')).trim();
			contact = line.substring(line.indexOf('(') + 1, line.lastIndexOf(')'));
		} catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException();
		}
		if (contact.isEmpty()) {
			throw new IllegalArgumentException();
		}

		Client client = new Client(name, contact);
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).equals(client)) {
				throw new IllegalArgumentException();
			}
		}

		return TourCoordinator.getInstance().addNewClient(name, contact);

	}

	private static Tour readTour(String line, SimpleArrayList<Tour> tours) throws DuplicateTourException {
		Scanner scan = new Scanner(line.trim());

		String kind = "";
		String name = "";
		LocalDate startDate;
		int duration;
		int basePrice;
		int capacity;

		scan.useDelimiter(":");
		String nameString = scan.next().trim();
		nameString = nameString.substring(1).trim();

		kind = nameString.substring(0, 2);
		name = nameString.substring(2).trim();
		name = name.substring(1);

		String rest = scan.nextLine().substring(1).trim();
		scan.close();
		scan = new Scanner(rest);

		String date = scan.next();

		Scanner dateScan = new Scanner(date);
		dateScan.useDelimiter("/");

		int month = dateScan.nextInt();
		int day = dateScan.nextInt();
		int year = dateScan.nextInt();
		dateScan.close();
		startDate = LocalDate.of(2000 + year, month, day);

		duration = scan.nextInt();

		String basePriceStr = scan.next();
		if (basePriceStr.charAt(0) != '$') {
			scan.close();
			throw new IllegalArgumentException();
		}
		basePrice = Integer.parseInt(basePriceStr.substring(1));

		String capacityStr = scan.next();
		boolean starLast = capacityStr.charAt(capacityStr.length() - 1) == '*';
		if (starLast && !kind.equals("ED")) {
			scan.close();
			throw new IllegalArgumentException();
		}
		capacity = Integer.parseInt(capacityStr.substring(0, capacityStr.length() - (starLast ? 1 : 0)));

		scan.close();

		Tour tour;
		try {
			tour = TourCoordinator.getInstance().addNewTour(kind, name, startDate, duration, basePrice, capacity);
		} catch (Exception e) {
			throw new IllegalArgumentException(line);
		}

		for (int i = 0; i < tours.size(); i++) {
			if (tours.get(i).equals(tour)) {
				throw new IllegalArgumentException();
			}
		}
		if (kind.equals("ED") && !starLast) {
			tour.fixCapacity();
		}
		return tour;
	}

	private static Reservation readReservation(String line, Tour tour, SimpleArrayList<Client> clients)
			throws CapacityException {
		Scanner scan = new Scanner(line.trim());
		int confirmationCode = -1;
		int numInParty = -1;

		confirmationCode = scan.nextInt();

		numInParty = scan.nextInt();

		String clientPortion = scan.nextLine().trim();
		scan.close();

		String name = clientPortion.substring(0, clientPortion.indexOf("(")).trim();
		String contact = clientPortion.substring(clientPortion.indexOf("(")).trim();

		String clientInfo = name + " " + contact;

		Client client = null;
		scan.close();

		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).summaryInfo().equals(clientInfo)) {
				client = clients.get(i);
			}
		}

		if (client == null) {
			String clientsString = "";
			for (int i = 0; i < clients.size(); i++) {
				clientsString += clients.get(i).summaryInfo();
			}
			throw new IllegalArgumentException("list:" + clientsString + ";" + clientInfo);
		}

		return TourCoordinator.getInstance().addOldReservation(client, tour, numInParty, confirmationCode);
	}
}