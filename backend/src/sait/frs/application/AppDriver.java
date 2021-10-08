package sait.frs.application;

import java.io.*;
import sait.frs.gui.*;
import sait.frs.gui.MainWindow;
import sait.frs.manager.*;

/**
 * Application driver.
 * 
 * @author Maria Laura Diaz Pena, Justin Van Groningen
 * @version July 10, 2020
 */
public class AppDriver {

	/**
	 * Entry point to Java application.
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		
		MainWindow mainWindow = new MainWindow();
		
		mainWindow.display();
	}

}
