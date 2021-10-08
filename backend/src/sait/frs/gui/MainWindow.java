package sait.frs.gui;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import sait.frs.manager.FlightManager;
import sait.frs.manager.ReservationManager;



/**
 * The main window (JFrame).
 * 
 * @author Maria Laura Diaz Pena, Justin Van Groningen
 * @version July 10, 2020
 */
public class MainWindow extends JFrame {
	
	private static final String TAB_FLIGHTS = "flights";
	private static final String TAB_RESERVATIONS = "reservations";
	
	/**
	 * Holds the flight and reservation manager.
	 */
    private ReservationManager reserveM;
    private FlightManager flightM;
	
    /**
     * The main JFrame window.
     */
	private JFrame frame;
	
	/**
	 * Card layout to display tab content.
	 */
	private CardLayout cardLayout;
	
	/**
	 * North panel.
	 */
	private JPanel northPanel;
	
	/**
	 * Center panel.
	 */
	private JPanel centerPanel;
	
	/**
	 * Flights tab button.
	 */
	private JButton flightsButton;
	
	/**
	 * Reservations tab button.
	 */
	private JButton reservationsButton;
	
	/**
	 * Flights tab panel.
	 */
	private TabBase flightsTab;
	
	/**
	 * Reservations tab panel.
	 */
	private TabBase reservationsTab;
	
	/**
	 * Creates the JFrame and any components inside it.
	 * @throws IOException 
	 */
	public MainWindow() throws IOException {
		this.reserveM = new ReservationManager();
		this.flightM = new FlightManager();
		
		this.frame = new JFrame("Flight Reservation Management System");
		
		this.frame.setSize(500, 600);
		this.frame.setLayout(new BorderLayout());
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.northPanel = this.createNorthPanel();
		this.frame.add(northPanel, BorderLayout.NORTH);
		
		this.centerPanel = this.createCenterPanel();
		this.frame.add(centerPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Creates the north panel.
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		
		JPanel tabPanel = this.createTabPanel();
		panel.add(tabPanel, BorderLayout.SOUTH);
		
		return panel;
	}
	
	/**
	 * Creates the center panel.
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();
		
		this.cardLayout = new CardLayout();
		
		this.flightsTab = new FlightsTab(flightM, reserveM);
		this.reservationsTab = new ReservationsTab(reserveM);
		
		panel.setLayout(this.cardLayout);
		
		panel.add(this.flightsTab.getPanel(), TAB_FLIGHTS);
		panel.add(this.reservationsTab.getPanel(), TAB_RESERVATIONS);
		
		this.cardLayout.first(panel);
		
		return panel;
	}
	
	/**
	 * Creates the tab buttons.
	 * @return JPanel containing tab buttons.
	 */
	private JPanel createTabPanel() {
		JPanel tabPanel = new JPanel();
		
		tabPanel.setLayout(new GridLayout(1, 2));
		
		this.flightsButton = new JButton("Flights");
		this.reservationsButton = new JButton("Reservations");
		
		TabButtonActionListener actionListener = new TabButtonActionListener();
		this.flightsButton.addActionListener(actionListener);
		this.reservationsButton.addActionListener(actionListener);
		
		tabPanel.add(flightsButton);
		tabPanel.add(reservationsButton);
		
		return tabPanel;
	}
	
	/**
	 * Displays the JFrame window.
	 */
	public void display() {
		
		pack();
		this.frame.setVisible(true);
	}
	
	/**
	 * Inner action listener class that listens for a tab button to be clicked.
	 * 
	 * 
	 */
	class TabButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == MainWindow.this.flightsButton) {
				
				MainWindow.this.cardLayout.show(MainWindow.this.centerPanel, TAB_FLIGHTS);
				
			} else if (e.getSource() == MainWindow.this.reservationsButton) {
				
				MainWindow.this.cardLayout.show(MainWindow.this.centerPanel, TAB_RESERVATIONS);
				
			}
		}
		
	}
}
