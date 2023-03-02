package controller;

import java.util.Scanner;

import dao.CorsoDaoImpl;
import dao.PrenotazioneDaoImpl;
import dao.UtenteDaoImpl;
import service.Service;
import view.CorsoView;

public class Controller {
	
	private Service service;
	
	public Controller() {
	       this.service = Service.getInstance();
	    }
	 
	public void start() {
		
		service.caricaDati();
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		CorsoDaoImpl dao = new CorsoDaoImpl();
		UtenteDaoImpl dao2 = new UtenteDaoImpl();
		PrenotazioneDaoImpl dao3 = new PrenotazioneDaoImpl();
		CorsoView corsoView = new CorsoView();
		
		String choice;
		
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
		
		dao.caricaCorsi("corsi.csv");
		dao2.caricaUtenti("utenti.csv");
		dao3.caricaPrenotazioni("prenotazioni.csv");
	
		
		do {
		
		System.out.println();
		System.out.print("Seleziona una opzione e premi invio: ");
		choice = scan.nextLine();
		
		switch (choice) {
		case "1":
			corsoView.displayCorsi(dao.getListaCorsi());
			break;
		case "2":
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
			
			
			
			
			
			break;
		case "3":
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
			
			break;
		case "4":
			System.out.println("Aggiungerò un nuovo utente");
			
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
			
			break;
		case "5":
			System.out.println("Esporterò in un file i corsi ancora disponibili");
			
			service.esportaCsvCorsi();
			
			break;
		case "0":
			System.out.println("Esco.");
			break;
		default:
			System.out.println("Opzione non disponibile");
		}
		
		System.out.println("Hai scelto: " + choice);
		
		} while (!choice.equals("0"));
		
		System.out.println("Ciao ciao!");
		}
	}
	

