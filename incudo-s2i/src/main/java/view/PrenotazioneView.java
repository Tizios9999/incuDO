package view;

import java.util.List;

import model.Prenotazione;

public class PrenotazioneView {
	
	public void displayPrenotazioniAttive(List<String[]> listaPrenotazioni) {
		
		for (String[] prenotazione : listaPrenotazioni) {
			System.out.println("ID: " + prenotazione[0]);
			System.out.println("ID Corso: " + prenotazione[1]);
			System.out.println("ID Utente: " + prenotazione[2]);
			System.out.println("Durata: dal " + prenotazione[3] + " al " + prenotazione[4]);
			System.out.println("-------------------------------------------------------");
		}
		
	}
	
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
