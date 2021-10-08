package sait.frs.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sait.frs.exception.*;
import sait.frs.manager.*;
import sait.frs.problemdomain.*;

/**
 * Holds the components for the reservations tab.
 * 
 * @author Maria Laura Diaz Pena, Justin Van Groningen
 * @version July 10, 2020
 */
public class ReservationsTab extends TabBase {
	/**
	 * Instance of travel manager.
	 */
	private FlightManager flightM;
	private ReservationManager reserveM;
	
	/**
	 * List of reservations.
	 */
	private JList<Reservation> reserveList;
	
	/**
	 * Action listener for JList.
	 */
	private MyListSelectionListener listSelectionListener;

	private DefaultListModel<Reservation> ReservationModel = new DefaultListModel<Reservation>();
	
	JTextField textCodeE;
	JTextField textFlight;
	JTextField textAirlineE;
	JTextField textCost;
	JTextField textNameE;
	JTextField textCitizenship;
	JComboBox comboStatus;
	JButton Update;
	JTextField textCodeS;
	JTextField textAirlineS;
	JTextField textNameS;
	JButton findReserve;
	
	/**
	 * Creates the components for reservations tab.
	 * @param manager 
	 */
	public ReservationsTab(ReservationManager manager) {
		this.reserveM = manager;
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
		
		JLabel title = new JLabel("Reservations", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);
		
		return panel;
	}
	
	/**
	 * @return
	 */
	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout(10,10));
		
		ReservationModel = new DefaultListModel<>();
		reserveList = new JList(ReservationModel);
		
		// User can only select one item at a time.
		reserveList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.reserveList);
		
		this.listSelectionListener = new MyListSelectionListener();
		this.reserveList.addListSelectionListener(this.listSelectionListener);
		
		panel.add(scrollPane, BorderLayout.CENTER);
		
		return panel;
	}
	
	/**
	 * @return
	 */
	private JPanel createWestPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		
		return panel;
	}
	
	private JPanel createEastPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Reserve", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title, BorderLayout.NORTH);
		
		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new GridLayout(10,10));
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new FlowLayout());
		centerPanel.add(eastPanel);
		
		JLabel codeLabel = new JLabel ("Code: ", SwingConstants.RIGHT);
		eastPanel.add(codeLabel);
		textCodeE = new JTextField(10);
		textCodeE.setEditable(false);
		eastPanel.add(textCodeE);
		
		JLabel flightLabel = new JLabel ("Flight: ", SwingConstants.RIGHT);
		eastPanel.add(flightLabel);
		textFlight = new JTextField(10);
		textFlight.setEditable(false);
		eastPanel.add(textFlight);
		
		JLabel airlineLabel = new JLabel ("Airline: ", SwingConstants.RIGHT);
		eastPanel.add(airlineLabel);
		textAirlineE = new JTextField(10);
		textAirlineE.setEditable(false);
		eastPanel.add(textAirlineE);
		
		JLabel costLabel = new JLabel ("Cost: ", SwingConstants.RIGHT);
		eastPanel.add(costLabel);
		textCost = new JTextField(10);
		textCost.setEditable(false);
		eastPanel.add(textCost);
		
		JLabel nameLabel = new JLabel ("Name: ", SwingConstants.RIGHT);
		eastPanel.add(nameLabel);
		textNameE = new JTextField(10);
		eastPanel.add(textNameE);
		
		JLabel citizenshipLabel = new JLabel ("Citizenship: ", SwingConstants.RIGHT);
		eastPanel.add(citizenshipLabel);
		textCitizenship = new JTextField(10);
		eastPanel.add(textCitizenship);
		
		JLabel statusLabel = new JLabel ("Status: ", SwingConstants.RIGHT);
		eastPanel.add(statusLabel);
		String [] s = {"Active", "Inactive"};
		comboStatus = new JComboBox<>(s);
		eastPanel.add(comboStatus);
		
		panel.add(centerPanel, BorderLayout.CENTER);
		
		Update = new JButton("Update");
		panel.add(Update, BorderLayout.SOUTH);
		Update.addActionListener(new MyActionListener());
		return panel;
	}
	
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
		
		JLabel CodeLabel = new JLabel ("Code: ", SwingConstants.RIGHT);
		westPanel.add(CodeLabel);
		textCodeS = new JTextField(10);
		southPanel.add(textCodeS);
		
		JLabel airlineLabel = new JLabel ("Airline: ", SwingConstants.RIGHT);
		westPanel.add(airlineLabel);
		textAirlineS = new JTextField(10);
		southPanel.add(textAirlineS);
		
		JLabel nameLabel = new JLabel ("Name: ", SwingConstants.RIGHT);
		westPanel.add(nameLabel);
		textNameS = new JTextField(10);
		southPanel.add(textNameS);
		
		panel.add(westPanel, BorderLayout.WEST);
		panel.add(southPanel, BorderLayout.CENTER);
		
		findReserve = new JButton("Find Reservations");
		panel.add(findReserve, BorderLayout.SOUTH);
		findReserve.addActionListener(new MyActionListener());
		return panel;
	}

		
	class MyListSelectionListener implements ListSelectionListener {
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {
			
			try {
				Reservation r = reserveList.getSelectedValue();
	
				textCodeE.setText(r.getCode());
				textFlight.setText(r.getFlightCode());
				textAirlineE.setText(r.getAirline());
				textCost.setText(Double.toString(r.getCost()));
				textNameE.setText(r.getName());
				textCitizenship.setText(r.getCitizenship());
				comboStatus.setSelectedItem(r.isActive());
				
			
			} catch (Exception e1) {}
		}
		
	}

	/**
	 * 
	 *
	 */
	class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == Update) {
			
				String code = textCodeE.getText();
				String name = textNameE.getText();
				String citizenship = textCitizenship.getText();
				
				Reservation r = reserveM.findReservationByCode(code);
				
				try {
					
					r.setName(name);
					r.setCitizenship(citizenship); 
					
					if (comboStatus.getSelectedItem().equals("Inactive")) {
						r.setActive(false);
						ReservationModel.removeElement(r);
						
					}
					
					reserveM.persist();
					
					JOptionPane.showMessageDialog(null, " Reservation " + code + " has been updated");
					ReservationModel.clear();
					
					textCodeE.setText("");
					
					textFlight.setText("");
					
					textAirlineE.setText("");
					
					textCost.setText("");
					
					textNameE.setText("");
					
					textCitizenship.setText("");
					
					comboStatus.setSelectedItem("");
					
					
				} catch (InvalidCitizenshipException | InvalidNameException | IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
			}
			else if (e.getSource() == findReserve) {
				
				String code = textCodeS.getText();
				String airline = textAirlineS.getText(); 
				String name = textNameS.getText();
				
				ArrayList<Reservation> reserve = reserveM.findReservations(code, airline, name);
				
				for (Reservation r : reserve) {
					
					ReservationModel.addElement(r);
					
				}
			
			}	
				
				
			}
			
		}
		
	}

