package view;

import java.util.List;

public class UtenteView {

	public void displayUtentiLiberi(List<String[]> listaUtentiLiberi) {
		
		System.out.println("Utenti disponibili:");
		
		for (String[] utente : listaUtentiLiberi) {
			System.out.println("ID: " + utente[0] + " / " + "Nome: " + utente[1]);
		} 
		
	}
	
	public void previewUtente(String nome, String cognome, String dataNascita, String indirizzo, String documento) {
		
		System.out.println("Nome: " + nome);
		System.out.println("Cognome: " + cognome);
		System.out.println("dataNascita: " + dataNascita);
		System.out.println("Indirizzo: " + indirizzo);
		System.out.println("Documento: " + documento);

	}
	
}
