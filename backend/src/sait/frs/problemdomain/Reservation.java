package sait.frs.problemdomain;

import java.lang.Object;
import sait.frs.exception.*;
import sait.frs.problemdomain.Reservation;

/**
 * Represents a Reservation.
 * @author Maria Laura Diaz Pena, Justin Van Groningen
 * @version July 10, 2020
 */

public final class Reservation extends Object {
	
	private String code;
	private String flightCode;
	private String airline;
	private String name;
	private String citizenship;
	private double cost;
	private boolean	active;
	
	  
	/**
	 * User-defined constructor for Reservation.
	 * @param code
	 * @param flightCode
	 * @param name
	 * @param citizenship
	 * @param active
	 * @param cost
	 * @param airline
	 */
	public Reservation(String code, 
		String flightCode,	
		String airline,
		String name,
		String citizenship,
		double cost,
		boolean active) {
		
		this.code = code;
		this.flightCode = flightCode;
		this.airline = airline;
		this.name = name;
		this.citizenship = citizenship;
		this.cost = cost;
		this.active = active;
		
	}
	
	/**
	 * Gets the reservation code
	 * @return
	 */
	public String getCode(){
		return code;
		
	}
	
	/**
	 * Gets the code of the flight
	 * @return flightCode
	 */
	public String getFlightCode() {
		return flightCode;
		
	}
	
		
	/**
	 * Gets the airline code
	 * @return airline
	 */
	public String getAirline () {
		return airline;
	}
	/**
	 * Gets the name of the traveler
	 * @return
	 */
	public String getName() {
		return name;
		
	}
		
	/**
	 * Gets the citizenship of the traveler
	 * @return citizenship
	 */
	public String getCitizenship(){
		return citizenship;
		
	}

	
	/**
	 * Gets the cost per seat
	 * @return cost
	 */
	public double getCost() {
		return cost;
		
	}
	
	/**
	 * Checks if the reservation is active or not
	 * @return
	 */
	public boolean isActive() {
		return active;
		
	}
	
	/**
	 * Sets the traveler's name.
	 * @param name
	 * @throws InvalidNameException
	 */
	public void setName(String name) throws InvalidNameException{
		if (name.contentEquals("")) {
			throw new InvalidNameException();
		}
		else this.name = name;
	}
	
	/**
	 * Sets the traveler's citizenship.
	 * @param citizenship
	 * @throws InvalidCitizenshipException
	 */
	public void setCitizenship(String citizenship) throws InvalidCitizenshipException {
		if (citizenship.equals("")) {
			throw new InvalidCitizenshipException();
		}
		else this.citizenship = citizenship;
	}
	
	/**
	 * Setter of boolean active
	 * @param active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/**
	 * Gets the generated reservation code.
	 */
	public String toString(){
		return code;
		
	}



}