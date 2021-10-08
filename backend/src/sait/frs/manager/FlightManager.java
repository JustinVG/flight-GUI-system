package sait.frs.manager;

import java.io.*;
import java.lang.*;
import java.util.*;
import sait.frs.exception.*;
import sait.frs.problemdomain.*;
/**
 * This class is responsible for managing flights and airports.
 * 
 * @author Maria Laura Diaz Pena, Justin Van Groningen
 * @version July 10, 2020
 */

public final class FlightManager{
	
	/**
	 * Where we store the airport data
	 */
	private ArrayList<String> airports;
	
	/**
	 * Where we store the flight data
	 */
	private ArrayList<Flight> flight;
	
	/**
	 * Used to search for reservations on any day of the week.
	 */
	public final String WEEKDAY_ANY = "Any";
	
	/**
	 * Used to search for reservations on Monday.
	 */
	public final String WEEKDAY_MONDAY = "Monday";
	
	/**
	 * Used to search for reservations on Tuesday.
	 */
	public final String WEEKDAY_TUESDAY = "Tuesday";
	
	/**
	 * Used to search for reservations on Wednesday.
	 */
	public final String WEEKDAY_WEDNESDAY = "Wednesday";
	
	/**
	 * Used to search for reservations on Thursday.
	 */
	public final String WEEKDAY_THURSDAY = "Thursday";
	
	/**
	 * Used to search for reservations on Friday.
	 */
	public final String WEEKDAY_FRIDAY = "Friday";
	
	/**
	 * Used to search for reservations on Saturday.
	 */
	public final String WEEKDAY_SATURDAY = "Saturday";
	
	/**
	 * The location of the flights text database file.
	 */
	public final String WEEKDAY_SUNDAY = "Sunday";
	
	/**
	 * Default constructor for FlightManager.
	 * @throws FileNotFoundException 
	 */
	public FlightManager() throws FileNotFoundException {
		this.airports = new ArrayList<String>();
		this.flight = new ArrayList<Flight>();
		this.populateAirports();
		this.populateFlights();
		
	}
	

	/**
	 * Gets all of the airports.
	 * @return ArrayList of airports.
	 */
	public ArrayList<String> getAirports(){
		return airports;
		
	}
	
	/**
	 * Gets all of the flights.
	 * @return ArrayList of Flight objects.
	 */
	public ArrayList<Flight> getFlights() {
		return this.flight;
		
	}
	
	/**
	 * @param code
	 * @return
	 * @throws FileNotFoundException 
	 */
	public String findAirportByCode(String code) throws FileNotFoundException {
		Scanner inFile = new Scanner("res//airports.csv");
		String line = "";
		String[] columns;
		String airportCode = "";
		
		while(inFile.hasNext()) {
			line = inFile.nextLine();
			columns = line.split(",");
			if (columns[0].equals(code)) {
				airportCode = columns[1];
			}
		}
		
		inFile.close();
		
		return airportCode;
		
	}
	
	/**
	 * Finds a flight using code.
	 * @param code - Flight code
	 * @return Flight object or null if code is not found.
	 */
	public Flight findFlightByCode(String code) {
		
		for (Flight f : this.flight) {
			if (f.getCode().equals(code)) {
				return f;
			}
		}
		
		return null;
		
		
	}
	

	/**
	 * Finds flights going between airports on a specified weekday.
	 * @param from - From airport
	 * @param to - To airport
	 * @param weekday - Day of week (one of WEEKDAY_* constants). Use WEEKDAY_ANY for any day of the week.
	 * @return Any found Flight objects.
	 * @throws FileNotFoundException 
	 */
	public ArrayList<Flight> findFlights(String from, 
			String to,
			String weekday) { 

		ArrayList<Flight> findFlights = new ArrayList<Flight> ();

		for (Flight f : flight ) {

			if ( f.getFrom().equals(from) && f.getTo().equals(to) && f.getWeekday().equals(weekday)) {

				findFlights.add(f);

			}
			else if (f.getFrom().equals(from) && f.getTo().equals(to) && weekday.equals(WEEKDAY_ANY) ) {			
				
				findFlights.add(f);
			}
		}

		return findFlights;
	}
	

	/**
	 * Loads up the file that contains all the flights
	 * @throws FileNotFoundException 
	 * 
	 */
	private void populateFlights() throws FileNotFoundException {
		
		Scanner inFile = new Scanner(new FileInputStream("res//flights.csv"));
		inFile.useDelimiter(",");
		String line = "";
		String[] columns;
		
		while(inFile.hasNextLine()) {
			line = inFile.nextLine();
			columns = line.split(",");
			
			String from = columns[1];
			String to = columns[2];
			String weekday = columns[3];
			String time = columns[4];
			String seats = columns[5];
			String costPerSeat = columns[6];
			
			String[] codeColumn = columns[0].split("-");
			
			String airline = codeColumn[1];
			String code = codeColumn[0];
			
			Flight tmpFlight = new Flight(airline, code, from, to, weekday, time, Integer.parseInt(seats), Double.parseDouble(costPerSeat));
			
			
			this.flight.add(tmpFlight);
			
			
			}
			

		inFile.close();
		
		
	}

	/**
	 * Loads up the file that contains all the airport information
	 * @throws FileNotFoundException 
	 * 
	 */
	private void populateAirports() throws FileNotFoundException{
		Scanner inFile = new Scanner(new FileInputStream("res//airports.csv"));
		String line = "";
		
		
		while(inFile.hasNextLine()) {
			line = inFile.nextLine();
			String[] columns = line.split(",");
			
			
			airports.add(columns[0]);
			
			
		}	
		
		inFile.close();
	}
	
}
