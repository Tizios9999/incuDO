package view;

import java.util.List;

import model.Prenotazione;

/**
 * The PrenotazioneView class provides methods to display information about courses reservations
 * (prenotazioni).
 */

public class PrenotazioneView {
	
	/**
	 * Displays a list of active reservations (prenotazioni) and their details.
	 * 
	 * @param listaPrenotazioni A list of String arrays 
	 * 		  representing active reservations. Each array 
	 * 		  contains the following elements in order: ID, course ID, user ID, 
	 * 		  start date and end date.
	 */
	
	public void displayPrenotazioniAttive(List<String[]> listaPrenotazioni) {
		
		for (String[] prenotazione : listaPrenotazioni) {
			System.out.println("ID: " + prenotazione[0]);
			System.out.println("ID Corso: " + prenotazione[1]);
			System.out.println("ID Utente: " + prenotazione[2]);
			System.out.println("Durata: dal " + prenotazione[3] + " al " + prenotazione[4]);
			System.out.println("-------------------------------------------------------");
		}
		
	}
	
	/**
	 * Displays a list of active reservations (prenotazioni) for users and courses and their details.
	 * 
	 * @param listaPrenotazioniUtentiCorsi A list of String arrays representing active reservations 
	 * 		  for users and courses. Each array contains the following elements in order: 
	 * 		  ID, course ID, course name, user ID, user name, start date and end date.
	 */
	
	public void displayPrenotazioniUtentiCorsiAttivi(List<String[]> listaPrenotazioniUtentiCorsi) {
		
		for (String[] prenotazione : listaPrenotazioniUtentiCorsi) {
			System.out.println("ID: " + prenotazione[0]);
			System.out.println("ID corso: " + prenotazione[1] + " / Nome corso: " + prenotazione[2]);
			System.out.println("ID utente: " + prenotazione[3] + " / Nominativo: " + prenotazione[4]);
			System.out.println("Durata: dal " + prenotazione[5] + " al " + prenotazione[6]);
			System.out.println("-------------------------------------------------------");
		}
		
	}
	
}
