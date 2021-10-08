package sait.frs.problemdomain;

import java.lang.Object;
import sait.frs.problemdomain.Flight;

/**
 * Represents a Flight.
 * @author Maria Laura Diaz Pena, Justin Van Groningen
 * @version July 10, 2020
 */

public final class Flight extends Object {
	
	private String code;
	private String airlineName;
	private String from;
	private String to;
	private String weekday; 
	private String time; 
	private int	seats; 
	private double costPerSeat; 
	 
	
	/**
	 * User-defined constructor for Flight.
	 * @param code
	 * @param airlineName
	 * @param from
	 * @param to
	 * @param weekday
	 * @param time
	 * @param seats
	 * @param costPerSeat
	 */ 
	public Flight(String code, String airlineName, String from, String to, String weekday, String time, int seats, double costPerSeat) {
		this.code = code;
		this.airlineName = airlineName;
		this.from = from;
		this.to = to;
		this.weekday = weekday;
		this.time = time;
		this.seats = seats; 
		this.costPerSeat = costPerSeat;
		//parseCode(code);
	}
	

	/**
	 * Gets the flight code.
	 * @return Flight code.
	 */
	public String getCode() {
		return code;
		
	}
	
	/**
	 * Gets the airline.
	 * @return Name of airline.
	 */
	public String getAirline() {
		return airlineName;
		
	}
	
	/**
	 * Gets the originating airport.
	 * @return Originating airport.
	 */
	public String getFrom() {
		return from;
		
	}
	
	/**
	 * Gets the destination airport.
	 * @return Destination airport.
	 */
	public String getTo() {
		return to;
		
	}
	
	/**
	 * Gets the day of the week the flight leaves.
	 * @return Week day.
	 */
	public String getWeekday(){
		return weekday;
		
	}
	/**
	 * Gets the time the flight leaves (in 24 hour format).
	 * @return Time flight leaves.
	 */
	public String getTime(){
		return time;
		
	}
	

	/**
	 * Gets the number of seats on the flight.
	 * @return Total number of seats.
	 */
	public int getSeats() {
		return seats;
		
	}
	
	/**
	 * Gets the cost per seat on the flight.
	 * @return Cost per seat.
	 */
	public double getCostPerSeat() {
		return costPerSeat;
		
	}
	
	/**
	 * Is this a domestic flight?
	 * @return True if it is domestic or false if it is international.
	 */
	public boolean isDomestic() {
		if (this.from.charAt(0)=='Y' && this.to.charAt(0)=='Y') {
			return true;
		}
		else return false;
		
	}
	

	
	/**
	 * @param code
	 */
	private void parseCode(String code) {
		switch(code) {
		case "OA":
			this.airlineName = "Otto Airlines";
			break;
		case "CA":
			this.airlineName = "Conned Air";
			break;
		case "TB":
			this.airlineName = "Try a Bus Airways";
			break; 
		case "VA":
			this.airlineName = "Vertical Airways";
			break;
		default:
			break;
		}
	}
	
	/**
	 * Gets the human readable representation of a flight.
	 * @override toString in class java.lang.Object
	 * @return Flight information.
	 */
	public String toString(){
		return airlineName +"-"+code + ", From: " + from+ ", To: "+ to+ ", Day: "+ weekday+ ", Cost: "+ costPerSeat;

	}


	
}