package service;

import java.time.LocalDate;

import dao.CorsoDaoImpl;
import dao.PrenotazioneDaoImpl;
import dao.UtenteDaoImpl;
import model.Corso;
import model.Prenotazione;
import model.Utente;

public class Service {
	
		private CorsoDaoImpl corsoDaoImpl;
		private UtenteDaoImpl utenteDaoImpl;
		private PrenotazioneDaoImpl prenotazioneDaoImpl;
	
		private static Service service;
	
		private Service() {}
	
		public static Service getInstance() {
			if(service == null) {
				service = new Service();
			}
			return service;
		}
		
		public void caricaDati() {
			this.corsoDaoImpl = new CorsoDaoImpl();
			this.utenteDaoImpl = new UtenteDaoImpl();
			this.prenotazioneDaoImpl = new PrenotazioneDaoImpl();
			
			corsoDaoImpl.caricaCorsi("corsi.csv");
			utenteDaoImpl.caricaUtenti("utenti.csv");
			prenotazioneDaoImpl.caricaPrenotazioni("prenotazioni.csv");
			
		}
		
		public void creaPrenotazione(Integer idCorso, Integer idUtente) {
			
			// Controlla se prenotazione già esistente
			
			Corso corso = this.corsoDaoImpl.cercaCorsoPerId(idCorso);
			
			Utente utente = this.utenteDaoImpl.cercaUtentePerId(idUtente);
			
			System.out.println("Corso:" + corso);
			System.out.println("Utente:" + utente);
			
			if (utente == null || corso == null) {
				System.out.println("Corso o utente non trovato");
			} else {
				
				// Cerco se c'è una prenotazione disponibile
				
				Integer nuovoId = this.prenotazioneDaoImpl.disponibilitàIdPrenotazione(idCorso, idUtente);
				
				if (nuovoId > 0) {
					
					LocalDate dataInizio = corso.getDataCorso();
					LocalDate dataFine = dataInizio.plusDays(corso.getDurata()/24);
					
					// creare prenotazione
					
					System.out.println("Nuova prenotazione corso: " + corso.getNome() + " iscritto " + utente.getNome() + " da " + dataInizio + " a " + dataFine);
					
					Prenotazione nuovaPrenotazione = new Prenotazione(nuovoId, corso.getId(), utente.getId(), dataInizio, dataFine);
					
					this.prenotazioneDaoImpl.aggiungiPrenotazione(nuovaPrenotazione);
					
					System.out.println(nuovaPrenotazione);
					
					corsoDaoImpl.setDisponibilitàCorso(corso.getId(), false);
					
				} else {
					System.out.println("Prenotazione non disponibile");
				}
				
			}
			
		}
	
		public void disdiciPrenotazione(Integer idCorso, Integer idUtente) {
			
			Corso corso = this.corsoDaoImpl.cercaCorsoPerId(idCorso);
			
			Utente utente = this.utenteDaoImpl.cercaUtentePerId(idUtente);
			
			System.out.println("Corso:" + corso);
			System.out.println("Utente:" + utente);
			
			if (utente == null || corso == null) {
				System.out.println("Corso o utente non trovato");
			} else {
				
			if (this.prenotazioneDaoImpl.cancellaPrenotazione(idCorso, idUtente)) {
				System.out.println("Prenotazione cancellata");
			} else {
				System.out.println("Prenotazione non trovata");
			}
				
			}
						
		}
		
		public void aggiungiUtente(String nome, String cognome, String dataNascita, String indirizzoUtente, String documentoIdUtente) {
			
			String id = String.valueOf(this.utenteDaoImpl.trovaUltimoId() + 1);
			
			String[] arrayUtente = {id, nome, cognome, dataNascita, indirizzoUtente, documentoIdUtente};
			
			Utente utente = new Utente.UtenteBuilder(arrayUtente).build();
			
			this.utenteDaoImpl.inserisciUtente(utente);
			
			
		}
		
		public void esportaCsvCorsi() {
			
			this.corsoDaoImpl.esportaCsv();
			
		}
}
