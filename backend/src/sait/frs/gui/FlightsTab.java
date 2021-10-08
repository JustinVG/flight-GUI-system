package sait.frs.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.JTextComponent;

import sait.frs.exception.*;
import sait.frs.manager.*;
import sait.frs.problemdomain.Flight;
import sait.frs.problemdomain.Reservation;

/**
 * Holds the components for the flights tab.
 * 
 * @author Maria Laura Diaz Pena, Justin Van Groningen
 * @version July 10, 2020
 */
public class FlightsTab extends TabBase {
	/**
	 * Instance of travel manager.
	 */
	private FlightManager flightM;
	private ReservationManager reserveM;
	
	
	/**
	 * List of flights.
	 */
	private JList<Flight> flightsList;
	
	/**
	 * Action listener for JList.
	 */
	private MyListSelectionListener listSelectionListener;

	private DefaultListModel<Flight> flightsModel = new DefaultListModel<Flight>();
	
	
	JTextField textFlight;
	JTextField textAirline;
	JTextField textDay;
	JTextField textTime;
	JTextField textCost;
	JTextField textName;
	JTextField textCitizenship;
	JButton Reserve;
	JComboBox comboFrom;
	JComboBox comboTo;
	JComboBox comboDay;
	JButton FindFlight;
	/**
	 * Creates the components for flights tab.
	 * @param flightM 
	 * @param reserveM  
	 */
	public FlightsTab(FlightManager flightM, ReservationManager reserveM) {
		this.flightM = flightM;
		this.reserveM = reserveM;
		
		
		panel.setLayout(new BorderLayout());
		
		JPanel northPanel = this.createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = this.createCenterPanel();
		panel.add(centerPanel, BorderLayout.CENTER);
		
		JPanel westPanel = this.createWestPanel();
		panel.add(westPanel, BorderLayout.WEST);
		
		JPanel eastPanel = this.createEastPanel();
		panel.add(eastPanel, BorderLayout.EAST);
		
		JPanel southPanel = this.createSouthPanel();
		panel.add(southPanel, BorderLayout.SOUTH);
		
	}
	
	/**
	 * Creates the north panel.
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();
		
		JLabel title = new JLabel("Flights", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);
		
		return panel;
	}
	
	/**
	 * Creates the center panel.
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout(10,10));
		
		flightsModel = new DefaultListModel<>();
		flightsList = new JList<>(flightsModel);
		
		// User can only select one item at a time.
		flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.flightsList);
		
		this.listSelectionListener = new MyListSelectionListener();
		this.flightsList.addListSelectionListener(this.listSelectionListener);
		
		panel.add(scrollPane, BorderLayout.CENTER);
		
		return panel;
	}
	
	/**
	 * Creates the west panel.
	 * @return JPanel that goes in the west.
	 */
	private JPanel createWestPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		
		return panel;
	}
	
	/**
	 * @return
	 */
	private JPanel createEastPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout(10,10));
		
		JLabel title = new JLabel("Reserve", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title, BorderLayout.NORTH);
		
		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new GridLayout(10,10));
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new FlowLayout());
		centerPanel.add(eastPanel);
		
		JLabel flightLabel = new JLabel ("Flight: ", SwingConstants.RIGHT);
		eastPanel.add(flightLabel);
		textFlight = new JTextField(10);
		textFlight.setEditable(false);
		eastPanel.add(textFlight);
		
		JLabel airlineLabel = new JLabel ("Airline: ", SwingConstants.RIGHT);
		eastPanel.add(airlineLabel);
		textAirline = new JTextField(10);
		textAirline.setEditable(false);
		eastPanel.add(textAirline);
		
		JLabel dayLabel = new JLabel ("Day: ", SwingConstants.RIGHT);
		eastPanel.add(dayLabel);
		textDay = new JTextField(10);
		textDay.setEditable(false);
		eastPanel.add(textDay);
		
		JLabel timeLabel = new JLabel ("Time: ", SwingConstants.RIGHT);
		eastPanel.add(timeLabel);
		textTime = new JTextField(10);
		textTime.setEditable(false);
		eastPanel.add(textTime);
		
		JLabel costLabel = new JLabel ("Cost: ", SwingConstants.RIGHT);
		eastPanel.add(costLabel);
		textCost = new JTextField(10);
		textCost.setEditable(false);
		eastPanel.add(textCost);
		
		JLabel nameLabel = new JLabel ("Name: ", SwingConstants.RIGHT);
		eastPanel.add(nameLabel);
		textName = new JTextField(10);
		eastPanel.add(textName);
		
		JLabel citizenshipLabel = new JLabel ("Citizenship: ", SwingConstants.RIGHT);
		eastPanel.add(citizenshipLabel);
		textCitizenship = new JTextField(10);
		eastPanel.add(textCitizenship);
		
		panel.add(centerPanel, BorderLayout.CENTER);
		
		Reserve = new JButton("Reserve");
		panel.add(Reserve, BorderLayout.SOUTH);
		Reserve.addActionListener(new MyActionListener());
		
		return panel;
	}
	
	/**
	 * @return
	 */
	private JPanel createSouthPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Flight Finder", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title, BorderLayout.NORTH);
		
		JPanel westPanel = new JPanel();
		westPanel.setLayout(new GridLayout(3,10));
		
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(3,10));		
		
		JLabel fromLabel = new JLabel ("From: ", SwingConstants.RIGHT);
		westPanel.add(fromLabel);
		ArrayList<String> airports = this.flightM.getAirports();
		comboFrom = new JComboBox<>(airports.toArray());
		comboFrom.setEditable(false);
		southPanel.add(comboFrom);
		
		JLabel toLabel = new JLabel ("To: ", SwingConstants.RIGHT);
		westPanel.add(toLabel);
		comboTo = new JComboBox<>(airports.toArray());
		comboTo.setEditable(false);
		southPanel.add(comboTo);
		
		JLabel dayLabel = new JLabel ("Day: ", SwingConstants.RIGHT);
		westPanel.add(dayLabel);
		
		String [] weekdays = {this.flightM.WEEKDAY_ANY, this.flightM.WEEKDAY_SUNDAY, this.flightM.WEEKDAY_MONDAY, this.flightM.WEEKDAY_TUESDAY, this.flightM.WEEKDAY_WEDNESDAY, this.flightM.WEEKDAY_THURSDAY, this.flightM.WEEKDAY_FRIDAY, this.flightM.WEEKDAY_SATURDAY};
		comboDay = new JComboBox<>(weekdays);
		comboDay.setEditable(false);
		southPanel.add(comboDay);
		
		panel.add(westPanel, BorderLayout.WEST);
		panel.add(southPanel, BorderLayout.CENTER);
		
		FindFlight = new JButton("Find Flights");
		panel.add(FindFlight, BorderLayout.SOUTH);
		FindFlight.addActionListener(new MyActionListener());
		
		return panel;
		
	}
	
	class MyListSelectionListener implements ListSelectionListener {
		
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {
		 				
			try {	
				Flight f = flightsList.getSelectedValue();
			
				textFlight.setText(f.getCode());
				
				textAirline.setText(f.getAirline());
				
				textDay.setText(f.getWeekday());
					
				textTime.setText(f.getTime());
				
				textCost.setText(Double.toString(f.getCostPerSeat()));
				
			} catch (Exception e1) {}
				
				


		}
		
	}
	

	/**
	 * 
	 *
	 */
	class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == Reserve) {
				
				String flight = textFlight.getText();
				String name = textName.getText();
				String citizenship = textCitizenship.getText();
				
				
				try {
					
					JOptionPane.showMessageDialog(null, "Reservation booked. Your code is " + reserveM.makeReservation(flightM.findFlightByCode(flight), name, citizenship));
					
					
				} catch (NullFlightException | NoMoreSeatsException | InvalidNameException | InvalidCitizenshipException | IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
				flightsModel.clear();
				
				textFlight.setText("");
				
				textAirline.setText("");
				
				textDay.setText("");
					
				textTime.setText("");
				
				textCost.setText("");
				
				textName.setText("");
				
				textCitizenship.setText("");
				
			}
			else if (e.getSource() == FindFlight) {
				String from = (String) comboFrom.getSelectedItem();
				String to = (String) comboTo.getSelectedItem();
				String weekday = (String) comboDay.getSelectedItem();
				
				
				ArrayList<Flight> flights = flightM.findFlights(from, to, weekday);
				

					for (Flight f : flights) {
							flightsModel.addElement(f);
							
						}
					
					
			
			}
		
		}
	
	}
}
