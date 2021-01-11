package edu.ncsu.csc216.travel.model.office;

import java.time.LocalDate;
import java.util.Observable;

import edu.ncsu.csc216.travel.list_utils.SimpleArrayList;
import edu.ncsu.csc216.travel.list_utils.SortedLinkedListWithIterator;
import edu.ncsu.csc216.travel.model.file_io.TravelReader;
import edu.ncsu.csc216.travel.model.file_io.TravelWriter;
import edu.ncsu.csc216.travel.model.participants.Client;
import edu.ncsu.csc216.travel.model.vacation.CapacityException;
import edu.ncsu.csc216.travel.model.vacation.EducationalTrip;
import edu.ncsu.csc216.travel.model.vacation.LandTour;
import edu.ncsu.csc216.travel.model.vacation.Reservation;
import edu.ncsu.csc216.travel.model.vacation.RiverCruise;
import edu.ncsu.csc216.travel.model.vacation.Tour;

/**
 * Implements TravelManager and represents the person coordinating tours,
 * clients, and reservations. TourCoordinator provides the glue to coordinate
 * the data for the backend model classes. The TourCoordinator maintains a
 * sorted list of tours and a list of clients. Implements the Singleton,
 * Observer, and Factory design patterns. Has methods to get an instance of
 * itself, add new tours/clients/reservations, set filters, cancel
 * reservations/tours, calculate the total cost for a client, get the list of
 * clients, get the list of tours (with optional filters), get the reservations
 * for a client or tour, get the name of the file associated with the system,
 * load data from a file, save data to a file, flush all lists, and check
 * whether or not the data should be saved.
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public class TourCoordinator extends Observable implements TravelManager {

	/** Instance of the TourCoordinator */
	private static TourCoordinator instance;
	/** List of tours within the Wolf Travel system */
	private SortedLinkedListWithIterator<Tour> tours;
	/** List of clients within the Wolf Travel system */
	private SimpleArrayList<Client> customer;
	/** Whether or not the system's data has been saved to a file */
	private boolean dataNotSaved;
	/** File name to save data to if needed */
	private String filename;
	/** Tour kind the user uses as a filter */
	private String kindFilter;
	/** Minimum Tour duration the user uses as a filter */
	private int durationMinFilter;
	/** Maximum Tour duration the user uses as a filter */
	private int durationMaxFilter;

	/**
	 * Constructs a TourCoordinator object.
	 */
	private TourCoordinator() {
		tours = new SortedLinkedListWithIterator<Tour>();
		customer = new SimpleArrayList<Client>();
		dataNotSaved = true;
		kindFilter = "";
		durationMinFilter = 0;
		durationMaxFilter = Integer.MAX_VALUE;
	}

	/**
	 * Returns an instance of the TourCoordinator.
	 * 
	 * @return Instance of the TourCoordinator
	 */
	public static TourCoordinator getInstance() {
		if (instance == null) {
			instance = new TourCoordinator();
		}
		return instance;
	}

	/**
	 * Creates a Tour according to the given parameters.
	 * 
	 * @param kind      Kind of Tour (River Cruise, Land Tour, Educational Trip)
	 * @param name      Tour name
	 * @param startDate Tour start date
	 * @param duration  Length of Tour in days
	 * @param basePrice Minimum per-person price
	 * @param capacity  Maximum number of Tour participants
	 * @return Newly created Tour
	 * @throws IllegalArgumentException If any parameters are not valid
	 * @throws DuplicateTourException   If a duplicate tour has already been added
	 */
	@Override
	public Tour addNewTour(String kind, String name, LocalDate startDate, int duration, int basePrice, int capacity)
			throws DuplicateTourException {
		Tour tour = null;
		if (kind.equals("RC") || kind.equals("River Cruise")) {
			tour = new RiverCruise(name, startDate, duration, basePrice, capacity);
		} else if (kind.equals("LT") || kind.equals("Land Tour")) {
			tour = new LandTour(name, startDate, duration, basePrice, capacity);
		} else if (kind.equals("ED") || kind.equals("Education")) {
			tour = new EducationalTrip(name, startDate, duration, basePrice, capacity);
		} else {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < tours.size(); i++) {
			if (tours.get(i).getName().substring(3).toUpperCase().equals(tour.getName().substring(3).toUpperCase())
					&& tours.get(i).getStartDate().equals(tour.getStartDate())
					&& tours.get(i).getDuration() == tour.getDuration()) {
				throw new DuplicateTourException("Such tour already exists");
			}
		}
		tours.add(tour);
		dataNotSaved = true;
		setChanged();
		notifyObservers(this);
		return tour;
	}

	/**
	 * Creates a new Client with the given parameters.
	 * 
	 * 
	 * @param userName Client user name
	 * @param contact  Client contact name
	 * @return Newly created Client
	 * @throws IllegalArgumentException If contact is null or blank
	 * @throws DuplicateClientException If new Client is the same as an existing
	 *                                  Client
	 */
	@Override
	public Client addNewClient(String userName, String contact) throws DuplicateClientException {
		Client client = new Client(userName, contact);
		if (customer.contains(client)) {
			throw new DuplicateClientException("Client is already registered.");
		}
		customer.add(client);
		dataNotSaved = true;
		setChanged();
		notifyObservers(this);
		return client;
	}

	/**
	 * Sets filters on the kinds of Tours under consideration.
	 * 
	 * @param kind Kind of Tour ("River Cruise", "Land Tour", "Educational Trip")
	 * @param min  Minimum Tour duration
	 * @param max  Maximum Tour duration
	 * @throws IllegalArgumentException If min is greater than max
	 */
	@Override
	public void setFilters(String kind, int min, int max) {
		if (min > max) {
			throw new IllegalArgumentException("Minimum duration cannot exceed maximum duration");
		}

		kindFilter = kind;
		durationMinFilter = min;
		durationMaxFilter = max;
	}

	/**
	 * Adds a new Reservation for a Client on a Tour with the given number of
	 * participants.
	 * 
	 * @param clientIndex       Location of the Client in the list of Clients
	 * @param filteredTourIndex Location of the Tour in the list of Tours restricted
	 *                          to the current filters
	 * @param numInParty        Number of participants for this Reservation
	 * @return Newly added Reservation
	 * @throws IllegalArgumentException If either index is invalid
	 * @throws CapacityException        If the Tour does not have the capacity to
	 *                                  accommodate the Reservation
	 */
	@Override
	public Reservation addNewReservation(int clientIndex, int filteredTourIndex, int numInParty)
			throws CapacityException {
		if (clientIndex < 0 || clientIndex >= customer.size()) {
			throw new IllegalArgumentException("Client not selected");
		}
		Client client = customer.get(clientIndex);

		SimpleArrayList<Tour> filteredTours = new SimpleArrayList<Tour>();
		for (int i = 0; i < tours.size(); i++) {
			if ((kindFilter.equals("") || kindFilter.equals("A")
					|| kindFilter.substring(0, 1).equals(tours.get(i).getName().substring(0, 1)))
					&& tours.get(i).getDuration() >= durationMinFilter
					&& tours.get(i).getDuration() <= durationMaxFilter) {
				filteredTours.add(tours.get(i));
			}
		}

		if (filteredTourIndex < 0 || filteredTourIndex >= filteredTours.size()) {
			throw new IllegalArgumentException("Tour not selected");
		}

		Reservation reservation;
		try {
			reservation = filteredTours.get(filteredTourIndex).createReservationFor(client, numInParty);
		} catch (IllegalArgumentException e) {
			reservation = null;
		}

		dataNotSaved = true;
		setChanged();
		notifyObservers(this);
		return reservation;
	}

	/**
	 * Adds an existing Reservation for a given Client and Tour.
	 * 
	 * @param client           Client for the Reservation
	 * @param tour             Tour for the Reservation
	 * @param numInParty       Size of the party for the Reservation
	 * @param confirmationCode Confirmation code for the Reservation
	 * @return Added Reservation
	 * @throws CapacityException        If the Reservation could not be established
	 *                                  because of lack of capacity
	 * @throws IllegalArgumentException If any Reservation data are not valid
	 *                                  (Client/Tour don't exist etc)
	 */
	@Override
	public Reservation addOldReservation(Client client, Tour tour, int numInParty, int confirmationCode)
			throws CapacityException {
		Reservation reservation = tour.addOldReservation(new Reservation(tour, client, numInParty, confirmationCode));
		dataNotSaved = true;
		setChanged();
		notifyObservers(this);
		return reservation;
	}

	/**
	 * Cancels a Reservation on the list of Reservations for a particular Client.
	 * 
	 * @param clientIndex      Location of the Client in the list of Clients
	 * @param reservationIndex Location of the Reservation in the Client's list of
	 *                         Reservations
	 * @return Cancelled Reservation
	 * @throws IllegalArgumentException If either index is invalid
	 */
	@Override
	public Reservation cancelReservation(int clientIndex, int reservationIndex) {
		Reservation reservation;
		try {
			reservation = customer.get(clientIndex).getReservation(reservationIndex);
			customer.get(clientIndex).getReservation(reservationIndex).cancel();
		} catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Select a client then the client's reservation to cancel");
		}
		dataNotSaved = true;
		setChanged();
		notifyObservers(this);
		return reservation;
	}

	/**
	 * Cancels a Tour and all Reservations for this Tour.
	 * 
	 * @param filteredTourIndex Location of the Tour in the list of Tours restricted
	 *                          to the current filters
	 * @return Canceled Tour
	 */
	@Override
	public Tour cancelTour(int filteredTourIndex) {
		if (filteredTourIndex > 0 || filteredTourIndex <= filteredTourData().length) {
			int matched = 0;
			for (int i = 0; i < tours.size(); i++) {
				if ((kindFilter.equals("") || kindFilter.equals("A")
						|| kindFilter.substring(0, 1).equals(tours.get(i).getName().substring(0, 1)))
						&& tours.get(i).getDuration() >= durationMinFilter
						&& tours.get(i).getDuration() <= durationMaxFilter) {
					matched++;
				}
			}
			Tour tour = null;
			int index = 0;
			int actIndex = 0;
			if (matched != 0) {
				for (int i = 0; i < tours.size(); i++) {
					if ((kindFilter.equals("") || kindFilter.equals("A")
							|| kindFilter.substring(0, 1).equals(tours.get(i).getName().substring(0, 1)))
							&& tours.get(i).getDuration() >= durationMinFilter
							&& tours.get(i).getDuration() <= durationMaxFilter) {
						if (index == filteredTourIndex) {
							actIndex = i;
							tour = tours.get(i);
							break;
						}
						index++;
					}
				}
				if (tour == null) {
					throw new IllegalArgumentException("No tour was selected");
				}

				int size = tour.listOfReservations().length;
				while (size > 0) {
					tour.getReservation(0).cancel();
					size--;
				}
				tours.remove(actIndex);
				dataNotSaved = true;
			}
			setChanged();
			notifyObservers(this);
			return tour;
		}
		return null;
	}

	/**
	 * Gets the total cost of a Client's Reservations.
	 * 
	 * @param clientIndex Location of the Client in the list of Clients
	 * @return Total cost of this Client's Reservations
	 * @throws IllegalArgumentException If clientIndex is out of range
	 */
	@Override
	public double totalClientCost(int clientIndex) {
		return customer.get(clientIndex).totalReservationCost();
	}

	/**
	 * Get an array that lists all Clients for the TourCoordinator.
	 * 
	 * @return Array with each Client represented as a String
	 */
	public String[] listClients() {
		String[] list = new String[customer.size()];
		for (int i = 0; i < customer.size(); i++) {
			list[i] = customer.get(i).summaryInfo();
		}
		return list;
	}

	/**
	 * Get the list of Tour data filtered according to filter settings.
	 * 
	 * @return Tour data list (each row represents a tour)
	 */
	public Object[][] filteredTourData() {
		int matched = 0;
		for (int i = 0; i < tours.size(); i++) {
			if ((kindFilter.equals("") || kindFilter.equals("A")
					|| kindFilter.substring(0, 1).equals(tours.get(i).getName().substring(0, 1)))
					&& tours.get(i).getDuration() >= durationMinFilter
					&& tours.get(i).getDuration() <= durationMaxFilter) {
				matched++;
			}
		}
		int index = 0;
		Object[][] data = new Object[matched][5];
		if (matched != 0) {
			for (int i = 0; i < tours.size(); i++) {
				if ((kindFilter.equals("") || kindFilter.equals("A")
						|| kindFilter.substring(0, 1).equals(tours.get(i).getName().substring(0, 1)))
						&& tours.get(i).getDuration() >= durationMinFilter
						&& tours.get(i).getDuration() <= durationMaxFilter) {
					data[index] = tours.get(i).getAllData();
					index++;
				}
			}
		}
		return data;
	}

	/**
	 * String of Reservations for a given tour.
	 * 
	 * @param clientIndex Index of Client in Client list
	 * @return Array of Strings, one for each Reservation
	 * @throws IllegalArgumentException If clientIndex is out of range
	 */
	@Override
	public String[] reservationsForAClient(int clientIndex) {
		if (clientIndex < 0 || clientIndex >= customer.size()) {
			throw new IllegalArgumentException();
		}
		String[] reservations = new String[customer.get(clientIndex).listOfReservations().length];
		if (customer.get(clientIndex).listOfReservations().length != 0) {
			for (int i = 0; i < customer.get(clientIndex).listOfReservations().length; i++) {
				reservations[i] = customer.get(clientIndex).listOfReservations()[i];
			}
		}
		return reservations;
	}

	/**
	 * String of Reservations for a given Tour.
	 * 
	 * @param filteredTourIndex Location of the Tour in the list when filtering is
	 *                          applied
	 * @return Array of Strings, one for each Reservation
	 * @throws IllegalArgumentException If filteredTourIndex is out of range
	 */
	@Override
	public String[] reservationsForATour(int filteredTourIndex) {
		if (filteredTourIndex < 0 || filteredTourIndex >= tours.size()) {
			throw new IllegalArgumentException();
		} else {
			int matched = 0;
			for (int i = 0; i < tours.size(); i++) {
				if ((kindFilter.equals("") || kindFilter.equals("A")
						|| kindFilter.substring(0, 1).equals(tours.get(i).getName().substring(0, 1)))
						&& tours.get(i).getDuration() >= durationMinFilter
						&& tours.get(i).getDuration() <= durationMaxFilter) {
					matched++;
				}
			}
			Tour tour = null;
			int index = 0;
			if (matched != 0) {
				for (int i = 0; i < tours.size(); i++) {
					if ((kindFilter.equals("") || kindFilter.equals("A")
							|| kindFilter.substring(0, 1).equals(tours.get(i).getName().substring(0, 1)))
							&& tours.get(i).getDuration() >= durationMinFilter
							&& tours.get(i).getDuration() <= durationMaxFilter) {
						if (index == filteredTourIndex) {
							tour = tours.get(i);
							break;
						}
						index++;
					}
				}
			}
			String[] reservations = null;
			try {
				reservations = new String[tour.listOfReservations().length];
			} catch (NullPointerException e) {
				throw new IllegalArgumentException();
			}
			if (tour.listOfReservations().length != 0) {
				for (int i = 0; i < tour.listOfReservations().length; i++) {
					reservations[i] = tour.listOfReservations()[i];
				}
			}
			return reservations;
		}
	}

	/**
	 * Returns the last used filename.
	 * 
	 * @return Last used filename, or null if there is none
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Loads file with Tour/Client/Reservation info.
	 * 
	 * @param fileName Name of file to read
	 * @throws IllegalArgumentException If the file contains any irregularities or a
	 *                                  read error occurs
	 */
	@Override
	public void loadFile(String fileName) {
		flushLists();
		TravelReader.readTravelData(fileName);
		filename = fileName;
		dataNotSaved = false;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Writes current Tours and Clients to file.
	 * 
	 * @param fileName File to write
	 * @throws IllegalArgumentException If file error occurs while attempting to
	 *                                  write
	 */
	@Override
	public void saveFile(String fileName) {
		filename = fileName;
		setFilters("", 0, Integer.MAX_VALUE);
		TravelWriter.writeTravelData(fileName);
		dataNotSaved = false;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Clears all client and tour data from the customer and tours lists, sets
	 * dataNotSaved to false, and notifies observers.
	 */
	public void flushLists() {
		Reservation.resetCodeGenerator();
		tours = new SortedLinkedListWithIterator<Tour>();
		customer = new SimpleArrayList<Client>();
		dataNotSaved = false;
		setFilters("", 0, Integer.MAX_VALUE);
		filename = null;
	}

	/**
	 * Returns whether or not the WolfTravel data should be saved to the associated
	 * file.
	 * 
	 * @return True if the WolfTravel data should be saved to the associated file,
	 *         false otherwise
	 */
	public boolean dataShouldBeSaved() {
		return dataNotSaved;
	}
}