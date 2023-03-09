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
	private Scanner scan;
	
	public Controller() {
	       this.service = Service.getInstance();
	       scan = new Scanner(System.in);
	    }
	
	public void attendiConferma() {
		System.out.println();
		System.out.println("Premi invio per continuare.");
	    scan.nextLine();
		System.out.println();
	}
	
	
	public Integer controllaNumero(String messaggio) {
		
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
	
	public String controllaData(String messaggio) {
		
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
	
	public String inserisciStrDocValida(String messaggio) {
		
		String strDoc;
		
		while(true) {
			
			System.out.println(messaggio);
			
			strDoc = scan.nextLine();
			
			boolean contieneCaratteriSpeciali = strDoc.matches(".*[^a-zA-Z0-9 ].*");
			
			
			if (contieneCaratteriSpeciali || strDoc.isBlank() ) {
				System.out.println("Prego inserire un id documento valido (senza caratteri speciali)");
				System.out.println();
			} else {
				break;
			}
			
		}
		
		return strDoc;
	}
	
	
	public String inserisciCampo(String messaggio) {
		
		String campo;
		
		while(true) {
			
			System.out.println(messaggio);
			campo = scan.nextLine();
			
			if (!campo.isBlank()) {
				break;
			}
		}
		
		return campo;
	}
	
	public void start() {
		
		service.loadTablesData();
		CorsoView cView = new CorsoView();
		UtenteView uView = new UtenteView();
		
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
		
		choice = controllaNumero("Seleziona una opzione e premi invio: ");
		
		switch (choice) {
		case 1:
			cView.displayCorsi(service.getStringCorsi());
			attendiConferma();
			break;
		case 2:
			System.out.println("Prenotazione corsi esistenti");
			
			cView.displayCorsiLiberi(service.getStringAvailableCorsi());
			System.out.println();
			uView.displayUtentiLiberi(service.getStringAvailableUtenti());
			System.out.println();
				
			Integer idCorsoDaInserire = controllaNumero("Inserisci ID corso");
			Integer idUtenteDaInserire = controllaNumero("Inserisci ID utente");
					
			service.createPrenotazione(idCorsoDaInserire, idUtenteDaInserire);			
			
			attendiConferma();
			
			break;
		case 3:
			System.out.println("Disdetta prenotazione");
			System.out.println();
			
			PrenotazioneView pview = new PrenotazioneView();
			
			System.out.println("Questa è la lista di prenotazioni attive: ");
			System.out.println();
			
			pview.displayPrenotazioniUtentiCorsiAttivi(service.getStringPrenotazioniUtentiCorsi());
			
			Integer idCorsoDaCancellare = controllaNumero("Inserisci ID corso");
			System.out.println();
			Integer idUtenteDaCancellare = controllaNumero("Inserisci ID utente");
			System.out.println();
				
			service.cancelPrenotazione(idCorsoDaCancellare, idUtenteDaCancellare);
		
			attendiConferma();
			break;
		case 4:
			System.out.println("Inserimento nuovo utente");
			System.out.println();
			
			String nomeUtente = inserisciCampo("Inserisci Nome utente:");
			String cognomeUtente = inserisciCampo("Inserisci Cognome utente:");
			String dataNascitaUtente = controllaData("Inserisci la data di nascita (formato dd/mm/yyyy)");
			String indirizzoUtente = inserisciCampo("Inserisci indirizzo:");	
			String documentoIdUtente = inserisciStrDocValida("Inserisci l'ID del documento di identità:");
			
			service.createNewUtente(nomeUtente, cognomeUtente, dataNascitaUtente, indirizzoUtente, documentoIdUtente);
			
			System.out.println("Utente inserito: ");
			uView.previewUtente(nomeUtente, cognomeUtente, dataNascitaUtente, indirizzoUtente, documentoIdUtente);
			
			attendiConferma();
			break;
		case 5:
			
			// Export all available courses to CSV File
			
			service.exportCorsoCsvTable();
			
			attendiConferma();
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
	

