package it;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
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
		
		do {
		
		System.out.println();
		System.out.print("Seleziona una opzione e premi invio: ");
		choice = scan.nextLine();
		
		switch (choice) {
		case "1":
			System.out.println("Visualizzerò i corsi");
			break;
		case "2":
			System.out.println("Prenoterò un corso esistente");
			break;
		case "3":
			System.out.println("Disdirò una prenotazione");
			break;
		case "4":
			System.out.println("Aggiungerò un nuovo utente");
			break;
		case "5":
			System.out.println("Esporterò in un file i corsi ancora disponibili");
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
