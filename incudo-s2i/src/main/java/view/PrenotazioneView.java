package view;

import java.util.List;

import model.Prenotazione;

public class PrenotazioneView {
	
	public void displayPrenotazioniAttive(List<Prenotazione> listaPrenotazioni) {
		
		for (Prenotazione prenotazione : listaPrenotazioni) {
			System.out.println("ID: " + prenotazione.getId());
			System.out.println("ID Corso: " + prenotazione.getIdAttività());
			System.out.println("ID Utente: " + prenotazione.getIdUtente());
			System.out.println("Data Inizio: " + prenotazione.getDataInizio());
			System.out.println("Data Fine: " + prenotazione.getDataFine());
			System.out.println("-------------------------------------------------------");
		}
		
	}
	
}
