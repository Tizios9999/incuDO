package controller;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import dao.CorsoDaoImpl;
import service.Service;
import view.CorsoView;

public class Controller {
	
	private Service service;
	
	public Controller() {
	       this.service = Service.getInstance();
	    }
	
	public void attendiConferma() {
		System.out.println();
		System.out.println("Premi invio per continuare.");
		Scanner scan = new Scanner(System.in);
	    scan.nextLine();
		System.out.println();
	}
	
	public void start() {
		
		service.caricaDati();
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		CorsoDaoImpl dao = new CorsoDaoImpl();
		CorsoView corsoView = new CorsoView();
		
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
		
		while(true) {
		
			try {
				
				System.out.print("Seleziona una opzione e premi invio: ");
				choice = scan.nextInt();
				break;
			
			} catch(InputMismatchException e) {
				
				System.out.println("Prego inserire un numero da 0 in su.");
				scan.nextLine();
				System.out.println();
			} 
			
		}
		
		scan.nextLine(); // This is to clean the scanner buffer.
		
		switch (choice) {
		case 1:
			corsoView.displayCorsi(service.getListaCorsi());
			attendiConferma();
			break;
		case 2:
			System.out.println("Prenoterò un corso esistente");
			
			try {
				System.out.println("Inserisci ID corso");
				Integer idCorso = scan.nextInt();
				
				System.out.println("Inserisci ID utente");
				Integer idUtente = scan.nextInt();
				
				service.creaPrenotazione(idCorso, idUtente);
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			attendiConferma();
			
			break;
		case 3:
			System.out.println("Disdirò una prenotazione");
			
			try {
				System.out.println("Inserisci ID corso");
				Integer idCorso = scan.nextInt();
				
				System.out.println("Inserisci ID utente");
				Integer idUtente = scan.nextInt();
				
				service.disdiciPrenotazione(idCorso, idUtente);
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			attendiConferma();
			break;
		case 4:
			System.out.println("Aggiungerò un nuovo utente");
			scan.nextLine();
			
			System.out.println("Inserisci Nome utente: ");
			String nomeUtente = scan.nextLine();
				
			System.out.println("Inserisci Cognome utente: ");
			String cognomeUtente = scan.nextLine();
				
			System.out.println("Inserisci la data di nascita (formato dd/mm/yyyy)");
			String dataNascitaUtente = scan.nextLine();
				
			System.out.println("Inserisci indirizzo: ");
			String indirizzoUtente = scan.nextLine();
				
			System.out.println("Inserisci l'ID del documento di identità: ");
			String documentoIdUtente = scan.nextLine();
				
			service.aggiungiUtente(nomeUtente, cognomeUtente, dataNascitaUtente, indirizzoUtente, documentoIdUtente);
			
			attendiConferma();
			break;
		case 5:
			
			// Export all available courses to CSV File
			
			service.esportaCsvCorsi();
			
			attendiConferma();
			break;
		case 0:
			System.out.println("Arrivederci!");
			break;
		default:
			System.out.println("Opzione non disponibile");
		}
		
		} while (choice != 0);
		
		}
	}
	

