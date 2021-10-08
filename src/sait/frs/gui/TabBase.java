package sait.frs.gui;


import java.awt.*;
import javax.swing.*;


/**
 * Abstract class for a tab in the JFrame.
 * 
 * @author Maria Laura Diaz Pena, Justin Van Groningen
 * @version June 26, 2020
 */
public abstract class TabBase {
	/**
	 * Tab panel attribute.
	 */
	protected JPanel panel;
	
	/**
	 * Default constructor.
	 */
	protected TabBase() {
		this.panel = new JPanel();
	}
	
	/**
	 * Gets the panel containing the tab components.
	 * @return JPanel with components.
	 */
	public JPanel getPanel() {
		return this.panel;
	}
}
