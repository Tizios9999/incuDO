package view;

import java.util.List;

/**
 * The CorsoView class provides methods to display information about courses (corsi).
 */

public class CorsoView {
	
	/**
	 * Displays a list of courses (corsi) and their details.
	 * 
	 * @param corsiList A list of String arrays representing courses. 
	 * 		  Each array contains the following elements in order: 
	 * 		  ID, course name, description, start date, start time and availability.
	 * 
	 */
	
	public void displayCorsi(List<String[]> corsiList) {
		
		for (String[] corso : corsiList) {
			System.out.println();
			ViewUtils.printBarra(128);
			System.out.println();
			System.out.println("ID: " + corso[0]);
			System.out.println("Nome corso: " + corso[1]);
			System.out.println();
			System.out.println("Descrizione: ");
			System.out.println();
			System.out.println(ViewUtils.formattaPerBox(corso[2], 128));
			System.out.println();
			System.out.println("Inizia in data " + corso[3] + " a " + corso[4]);
			System.out.println("Disponibile: " + corso[5]);
		}
	}
	
	/**
	 * Displays a list of available courses (corsi).
	 * 
	 * @param availableCorsiList A list of String arrays representing available courses. 
	 * Each array contains the following elements in order: ID and course name.
	 */
	
	public void displayAvailableCorsi(List<String[]> availableCorsiList) {
		
		System.out.println("Corsi disponibili:");
		
		for (String[] corso : availableCorsiList) {
			System.out.println("ID: " + corso[0] + " / " + "Nome: " + corso[1]);
		} 
	}
}
