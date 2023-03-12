package controller;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import service.Service;
import util.DataValidator;
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
				
			Integer idCorsoToBeInserted = insertCheckedNumber("Inserisci ID corso");
			Integer idUtenteToBeInserted = insertCheckedNumber("Inserisci ID utente");
					
			service.createPrenotazione(idCorsoToBeInserted, idUtenteToBeInserted);			
			
			waitForConfirmation();
			
			break;
		case 3:
			System.out.println("Disdetta prenotazione");
			System.out.println();
			
			PrenotazioneView pview = new PrenotazioneView();
			
			System.out.println("Questa è la lista di prenotazioni attive: ");
			System.out.println();
			
			pview.displayPrenotazioniUtentiCorsiAttivi(service.getStringPrenotazioniUtentiCorsi());
			
			Integer idCorsoToDelete = insertCheckedNumber("Inserisci ID corso");
			Integer idUtenteToDelete = insertCheckedNumber("Inserisci ID utente");
				
			service.cancelPrenotazione(idCorsoToDelete, idUtenteToDelete);
		
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
		
		String data = null;

		System.out.println(message);
		
		boolean valid = false;
		
		while(!valid) {
			
			data = scan.nextLine();

			valid = DataValidator.isValidData("Integer", data);
			
			if (!valid) {
				System.out.println("Prego inserire un numero maggiore di 0:");
			} 
		}
		
		return Integer.parseInt(data);
		
	}
	
	private String insertCheckedDate(String message) {
		
		String data = null;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		boolean valid = false;
		
		System.out.println(message);
		
		while(!valid) {
			
			data = scan.nextLine();

			valid = DataValidator.isValidData("Date", data);
			
			if (!valid) {
				System.out.println("Prego inserire una data corretta:");
			} 
		}
		
		return data;
	}
	
	private String insertValidDoc(String message) {
		
		String data = null;
		
		boolean valid = false;
		
		System.out.println(message);
		
		while(!valid) {
			
			data = scan.nextLine();

			valid = DataValidator.isValidData("Alfanumeric", data);
			
			if (!valid) {
				System.out.println("Prego inserire un codice alfanumerico senza caratteri speciali:");
			} 
		}
		
		return data;

	}	
	
	private String insertField(String message) {
		
		String data;
		
		while(true) {
			
			System.out.println(message);
			data = scan.nextLine();
			
			if (!data.isBlank()) {
				break;
			}
		}
		
		return data;
	}
	
	
	}
	

