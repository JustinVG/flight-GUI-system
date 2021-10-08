package sait.frs.manager;

import java.io.*;
import java.lang.*;
import java.util.*;
import sait.frs.exception.*;
import sait.frs.problemdomain.*;



/**
 * This class is responsible for managing reservations.
 * @author Maria Laura Diaz Pena, Justin Van Groningen
 * @version July 10, 2020
 *
 */
public class ReservationManager {

	
	/**
	 * ArrayList storing all the reservations
	 */
	private ArrayList<Reservation> reservations;
	
	
	/**
	 * Default constructor for ReservationManager.
	 * @throws IOException 
	 */
	public ReservationManager() throws IOException {
		reservations = new ArrayList<Reservation>();
		populateFromBinary();
	}


	/**
	 * Gets all the reservations.
	 * @return ArrayList of Reservation objects.
	 */
	public ArrayList<Reservation> getReservations(){
		return reservations;
		
	}
	
	/**
	 * Creates a new Reservation object
	 * @param flight - Flight to book reservation for.
	 * @param name - Name of person (cannot be null or empty).
	 * @param citizenship - Citizenship of person (cannot be null or empty).
	 * @return Created reservation instance.
	 * @throws NullFlightException - Thrown if flight is null.
	 * @throws NoMoreSeatsException - Thrown if flight is booked up.
	 * @throws InvalidNameException - Thrown if name is null or empty.
	 * @throws InvalidCitizenshipException - Thrown if citizenship is null or empty.
	 * @throws IOException 
	 */
	public Reservation makeReservation(Flight flight,
            String name,
            String citizenship)
     throws NullFlightException,
            NoMoreSeatsException,
            InvalidNameException,
            InvalidCitizenshipException, IOException {
		if (flight == null) {
			throw new NullFlightException();
		} 
		else if (getAvailableSeats(flight) == 0) {
			throw new NoMoreSeatsException();
		} 
		else if (name.equals("")) {
			throw new InvalidNameException();
		}
		else if (citizenship.equals("")) {
			throw new InvalidCitizenshipException();
		}		
		else {
			Reservation reserve = new Reservation(
					
					generateReservationCode(flight), 
					flight.getCode(), 
					flight.getAirline(), 
					name, 
					citizenship, 
					flight.getCostPerSeat(), 
					true);
			
			reservations.add(reserve);
			persist();
		
			return reserve;
		} 
		
	}

	
	/**
	 * Finds reservations containing either reservation code, airline or traveler's name.
	 * @param code - Reservation code to search for.
	 * @param airline - Airline to search for.
	 * @param name - Travelers name to search for.
	 * @return Any matching Reservation objects.
	 */
	public ArrayList<Reservation> findReservations(String code,
            String airline,
            String name) {
		ArrayList<Reservation> findReservations = new ArrayList<Reservation> ();
		
		for (Reservation tmpReserve : this.reservations ) {
			if ((tmpReserve.getCode().equals(code) || code.equals("") ) &&
				(tmpReserve.getAirline().equals(airline) || airline.equals("")) && 
				(tmpReserve.getName().equals(name) || name.equals(""))) {
				
				findReservations.add(tmpReserve);
			} 
					
		} return findReservations;
		

	}
	
	
	/**
	 * Finds reservation with the exact reservation code.
	 * @param code - Reservation code.
	 * @return Reservation object or null if none found.
	 */
	public Reservation findReservationByCode(String code) {
		for (Reservation r : this.reservations) {
			if (r.getCode().equals(code)) {
				return r;
			}
		}  return null;
		
	}
	
	/**
	 * Saves the new reservation or the changes made to an old reservation
	 * and saves it in the reservation file.
	 * @throws IOException 
	 * 
	 */
	public void persist() throws IOException {
		RandomAccessFile file = new RandomAccessFile ("res//reservations.dat", "rw");
		
		for (Reservation reserve : reservations ) {
		
				file.writeUTF(reserve.getCode()); 
				file.writeUTF(reserve.getFlightCode());
				file.writeUTF(reserve.getAirline()); 
				file.writeUTF(reserve.getName()); 
				file.writeUTF(reserve.getCitizenship()); 
				file.writeDouble(reserve.getCost()); 
				file.writeBoolean(reserve.isActive());
		}
		file.close();
	}
	
	/**
	 * Gets the number of available seats for a flight.
	 * @param flight - Flight instance.
	 * @return Number of available seats.
	 */
	private int getAvailableSeats(Flight flight) {
		
		return flight.getSeats();
		
	}
	
	/**
	 * Generates the unique flight code.
	 * @param flight
	 * @return
	 */
	private String generateReservationCode(Flight flight) {
		
		String ReservationCode = "";
		int max = 9999;
		int min = 1000;
		
		if (flight.isDomestic()==true) {
			ReservationCode = "D" + (new Random().nextInt(max-min)+min);
		}
		else 
			ReservationCode = "I" + (new Random().nextInt(max-min)+min);
		
		return ReservationCode;
	}
	
	
	/**
	 * Loads the file where the reservations are stored.
	 * @throws IOException 
	 * 
	 */
	private void populateFromBinary() throws IOException {
		RandomAccessFile file = new RandomAccessFile ("res//reservations.dat", "rw");
		
		do {
		Reservation tmpReserve = new Reservation(
				file.readUTF(), 
				file.readUTF(), 
				file.readUTF(), 
				file.readUTF(), 
				file.readUTF(), 
				file.readDouble(), 
				file.readBoolean());
		
		
		reservations.add(tmpReserve);
		
		} while (file.getFilePointer() < file.length());
		
		file.close();
	}
	
}
