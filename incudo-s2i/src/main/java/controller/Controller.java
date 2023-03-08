package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import dao.CorsoDaoImpl;
import service.Service;
import view.CorsoView;
import view.PrenotazioneView;
import view.UtenteView;

public class Controller {
	
	private Service service;
	
	public Controller() {
	       this.service = Service.getInstance();
	    }
	
	public void attendiConferma(Scanner scan) {
		System.out.println();
		System.out.println("Premi invio per continuare.");
	    scan.nextLine();
		System.out.println();
	}
	
	public Integer controllaNumero(String messaggio, Scanner scan) {
		
		Integer n;
		
		while(true) {
			
			try {
				
				System.out.println(messaggio);
				n = scan.nextInt();
				break;
			
			} catch(InputMismatchException e) {
				
				System.out.println("Prego inserire un numero.");
				scan.nextLine();
				System.out.println();
			} 
			
		}
		
		scan.nextLine(); // This is to clean the scanner buffer.
		
		return n;
	}
	
	public String controllaData(String messaggio, Scanner scan) {
		
		String dataInStringa;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		while(true) {
			
			try {
				
				System.out.println(messaggio);
				
				dataInStringa = scan.nextLine();
				
				// If the following date is not parsed correctly, I will catch the error
				LocalDate dataControllo = LocalDate.parse(dataInStringa, formatter); 
				
				break;
			
			} catch(DateTimeParseException e) {
				
				System.out.println("Il formato della data non è corretto.");
				System.out.println();
			} 
			
		}
		
		return dataInStringa;
	}
	
	public String inserisciCampo(String messaggio, Scanner scan) {
		
		String campo;
		
		System.out.println(messaggio);
		campo = scan.nextLine();
		
		return campo;
	}
	
	public void start() {
		
		service.caricaDati();
		CorsoView cView = new CorsoView();
		UtenteView uView = new UtenteView();
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		Integer choice = -1;
		
		do {
		
		System.out.println("Opzioni disponibili:");
		System.out.println();
		System.out.println("+--------+----------------------------------------------------+");
		System.out.println("|Comando | Descrizione                                        |");
		System.out.println("+--------+----------------------------------------------------+");
		System.out.println("|1   	 | Visualizzare tutti i corsi all'interno del sistema |");
		System.out.println("+--------+----------------------------------------------------+");
		System.out.println("|2   	 | Prenotare un corso esistente                       |");
		System.out.println("+--------+----------------------------------------------------+");
		System.out.println("|3   	 | Disdire la prenotazione di un corso                |");
		System.out.println("+--------+----------------------------------------------------+");
		System.out.println("|4   	 | Aggiungere un nuovo utente                         |");
		System.out.println("+--------+----------------------------------------------------+");
		System.out.println("|5   	 | Esportare un file con i corsi disponibili          |");
		System.out.println("+--------+----------------------------------------------------+");
		System.out.println("|0   	 | Uscire dal programma                               |");
		System.out.println("+--------+----------------------------------------------------+");
		
		System.out.println();
		
		choice = controllaNumero("Seleziona una opzione e premi invio: ", scan);
		
		switch (choice) {
		case 1:
			cView.displayCorsi(service.getStringCorsi());
			attendiConferma(scan);
			break;
		case 2:
			System.out.println("Prenotazione corsi esistenti");
			
			cView.displayCorsiLiberi(service.getStringCorsiLiberi());
			System.out.println();
			uView.displayUtentiLiberi(service.getStringUtentiLiberi());
			System.out.println();
				
			Integer idCorsoDaInserire = controllaNumero("Inserisci ID corso", scan);
			Integer idUtenteDaInserire = controllaNumero("Inserisci ID utente", scan);
					
			service.creaPrenotazione(idCorsoDaInserire, idUtenteDaInserire);			
			
			attendiConferma(scan);
			
			break;
		case 3:
			System.out.println("Disdetta prenotazione");
			System.out.println();
			
			PrenotazioneView pview = new PrenotazioneView();
			
			System.out.println("Questa è la lista di prenotazioni attive: ");
			System.out.println();
			
			pview.displayPrenotazioniUtentiCorsiAttivi(service.getStringPrenotazioniUtentiCorsi());
			
			Integer idCorsoDaCancellare = controllaNumero("Inserisci ID corso", scan);
			System.out.println();
			Integer idUtenteDaCancellare = controllaNumero("Inserisci ID utente", scan);
			System.out.println();
				
			service.disdiciPrenotazione(idCorsoDaCancellare, idUtenteDaCancellare);
		
			attendiConferma(scan);
			break;
		case 4:
			System.out.println("Inserimento nuovo utente");
			System.out.println();
			
			String nomeUtente = inserisciCampo("Inserisci Nome utente: ", scan);
			String cognomeUtente = inserisciCampo("Inserisci Cognome utente: ", scan);
			String dataNascitaUtente = controllaData("Inserisci la data di nascita (formato dd/mm/yyyy)", scan);
			String indirizzoUtente = inserisciCampo("Inserisci indirizzo: ", scan);	
			String documentoIdUtente = inserisciCampo("Inserisci l'ID del documento di identità: ", scan);
			
			service.aggiungiUtente(nomeUtente, cognomeUtente, dataNascitaUtente, indirizzoUtente, documentoIdUtente);
			
			System.out.println("Utente inserito: ");
			uView.previewUtente(nomeUtente, cognomeUtente, dataNascitaUtente, indirizzoUtente, documentoIdUtente);
			
			attendiConferma(scan);
			break;
		case 5:
			
			// Export all available courses to CSV File
			
			service.esportaCsvCorsi();
			
			attendiConferma(scan);
			break;
		case 0:
			System.out.println("Arrivederci!");
			break;
		default:
			System.out.println();
			System.out.println("Opzione non disponibile");
			System.out.println();
		}
		
		} while (choice != 0);
		
		}
	}
	

