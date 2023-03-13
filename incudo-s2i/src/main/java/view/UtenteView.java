package view;

import java.util.List;

/**
 * The UtenteView class provides methods to display information about users (utenti).
 */

public class UtenteView {

	/**
	 * Displays a list of available users (utenti).
	 * @param listaUtentiLiberi A list of String arrays representing available users. Each array contains the following elements in order: ID and name.
	 */
	
	public void displayUtentiLiberi(List<String[]> listaUtentiLiberi) {
		
		System.out.println("Utenti disponibili:");
		
		for (String[] utente : listaUtentiLiberi) {
			System.out.println("ID: " + utente[0] + " / " + "Nome: " + utente[1]);
		} 
	}
	
	/**
	 * Displays a preview of a user's details.
	 * 
	 * @param nome The user's first name.
	 * @param cognome The user's last name.
	 * @param dataNascita The user's date of birth.
	 * @param indirizzo The user's address.
	 * @param documento The user's identification document number.
	 */
	
	public void previewUtente(String nome, String cognome, String dataNascita, String indirizzo, String documento) {
		
		System.out.println("Nome: " + nome);
		System.out.println("Cognome: " + cognome);
		System.out.println("dataNascita: " + dataNascita);
		System.out.println("Indirizzo: " + indirizzo);
		System.out.println("Documento: " + documento);
	}
}
