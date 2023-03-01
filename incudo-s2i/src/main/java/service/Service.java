package service;

import java.time.LocalDate;

import dao.CorsoDaoImpl;
import dao.PrenotazioneDaoImpl;
import dao.UtenteDaoImpl;
import model.Corso;
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
		
		public void aggiungiPrenotazione(Integer idCorso, Integer idUtente) {
			
			// Controlla se prenotazione già esistente
			
			Corso corso = this.corsoDaoImpl.cercaCorsoPerId(idCorso);
			
			Utente utente = this.utenteDaoImpl.cercaUtentePerId(idUtente);
			
			System.out.println("Corso:" + corso);
			System.out.println("Utente:" + utente);
			
			if (utente == null || corso == null) {
				System.out.println("Corso o utente non trovato");
			} else {
				
				// Cerco se c'è una prenotazione disponibile
				
				if (this.prenotazioneDaoImpl.disponibilitàPrenotazione(idCorso, idUtente) == true) {
					
					LocalDate dataInizio = corso.getDataCorso();
					LocalDate dataFine = dataInizio.plusDays(corso.getDurata()/24);
					
					
					// creare prenotazione
					
					System.out.println("Nuova prenotazione corso: " + corso.getNome() + " iscritto " + utente.getNome() + " da " + dataInizio + " a " + dataFine);
					
				}
				
				// Se no:
			
				// Creo nuova prenotazione con i dati sopra
			
				// dao.corsoBuilder
			
				// Su corsi, cerco il corso con idCorso e da true lo imposto a false.
			
				// dao.modificaCorso
				
			}
			
			
			
		}
	
		public void rimuoviPrenotazione() {
			
			// Controlla se prenotazione già esistente
			
				// dao.cercaCorsoPerId
				// dao.cercaUtentePerId
						
				// Se sì:
							
					// dao.deleteCorso (ritorna true se è stato fatto)
						
					// se dao.deleteCorso torna true: 
						// Su corsi, cerco il corso con idCorso e da false lo imposto a true.
												
						// dao.modificaCorso
			
				// Se no: non c'è nessuna prenotazione con questi dati
		}
}
