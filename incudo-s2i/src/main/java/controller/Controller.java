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
		
		choice = insertCheckedNumber("Seleziona una opzione e premi invio: ");
		
		switch (choice) {
		case 1:
			cView.displayCorsi(service.getStringCorsi());
			waitForConfirmation();
			break;
		case 2:
			System.out.println("Prenotazione corsi esistenti");
			
			cView.displayAvailableCorsi(service.getStringAvailableCorsi());
			System.out.println();
			uView.displayUtentiLiberi(service.getStringAvailableUtenti());
			System.out.println();
				
			Integer idCorsoDaInserire = insertCheckedNumber("Inserisci ID corso");
			Integer idUtenteDaInserire = insertCheckedNumber("Inserisci ID utente");
					
			service.createPrenotazione(idCorsoDaInserire, idUtenteDaInserire);			
			
			waitForConfirmation();
			
			break;
		case 3:
			System.out.println("Disdetta prenotazione");
			System.out.println();
			
			PrenotazioneView pview = new PrenotazioneView();
			
			System.out.println("Questa è la lista di prenotazioni attive: ");
			System.out.println();
			
			pview.displayPrenotazioniUtentiCorsiAttivi(service.getStringPrenotazioniUtentiCorsi());
			
			Integer idCorsoDaCancellare = insertCheckedNumber("Inserisci ID corso");
			System.out.println();
			Integer idUtenteDaCancellare = insertCheckedNumber("Inserisci ID utente");
			System.out.println();
				
			service.cancelPrenotazione(idCorsoDaCancellare, idUtenteDaCancellare);
		
			waitForConfirmation();
			break;
		case 4:
			System.out.println("Inserimento nuovo utente");
			System.out.println();
			
			String nomeUtente = insertField("Inserisci Nome utente:");
			String cognomeUtente = insertField("Inserisci Cognome utente:");
			String dataNascitaUtente = insertCheckedDate("Inserisci la data di nascita (formato dd/mm/yyyy)");
			String indirizzoUtente = insertField("Inserisci indirizzo:");	
			String documentoIdUtente = insertValidDoc("Inserisci l'ID del documento di identità:");
			
			service.createNewUtente(nomeUtente, cognomeUtente, dataNascitaUtente, indirizzoUtente, documentoIdUtente);
			
			System.out.println("Utente inserito: ");
			uView.previewUtente(nomeUtente, cognomeUtente, dataNascitaUtente, indirizzoUtente, documentoIdUtente);
			
			waitForConfirmation();
			break;
		case 5:
			
			// Export all available courses to CSV File
			
			service.exportCorsoCsvTable();
			
			waitForConfirmation();
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
	
	private void waitForConfirmation() {
		System.out.println();
		System.out.println("Premi invio per continuare.");
	    scan.nextLine();
		System.out.println();
	}
	
	
	private Integer insertCheckedNumber(String message) {
		
		Integer n;
		
		while(true) {
			
			try {
				
				System.out.println(message);
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
	
	private String insertCheckedDate(String message) {
		
		String stringDate;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		while(true) {
			
			try {
				
				System.out.println(message);
				
				stringDate = scan.nextLine();
				
				// If the following date is not parsed correctly, I will catch the error
				LocalDate dateToCheck = LocalDate.parse(stringDate, formatter); 
				
				break;
			
			} catch(DateTimeParseException e) {
				
				System.out.println("Il formato della data non è corretto.");
				System.out.println();
			} 
			
		}
		
		return stringDate;
	}
	
	private String insertValidDoc(String message) {
		
		String strDoc;
		
		while(true) {
			
			System.out.println(message);
			
			strDoc = scan.nextLine();
			
			boolean hasSpecialCharacters = strDoc.matches(".*[^a-zA-Z0-9 ].*");
			
			
			if (hasSpecialCharacters || strDoc.isBlank() ) {
				System.out.println("Prego inserire un id documento valido (senza caratteri speciali)");
				System.out.println();
			} else {
				break;
			}
			
		}
		
		return strDoc;
	}
	
	
	private String insertField(String message) {
		
		String field;
		
		while(true) {
			
			System.out.println(message);
			field = scan.nextLine();
			
			if (!field.isBlank()) {
				break;
			}
		}
		
		return field;
	}
	
	
	}
	

