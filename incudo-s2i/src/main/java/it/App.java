package it;

import controller.Controller;

/**
 * Main method of the Java application
 * 
 * @author Davide Santonocito
 *
 */

public class App {

	/**
	 * Just starts the controller that will take care 
	 * of user interaction.
	 * 
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Controller controller = new Controller();
		
		controller.start();

	}
}