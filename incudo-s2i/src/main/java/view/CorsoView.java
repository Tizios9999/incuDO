package view;

import java.util.List;

public class CorsoView {
	
	public void displayCorsi(List<String[]> corsiList) {
		
		for (String[] corso : corsiList) {
			System.out.println();
			ViewUtils.printBarra(128);
			System.out.println();
			System.out.println("ID: " + corso[0]);
			System.out.println("Nome corso: " + corso[1]);
			System.out.println();
			System.out.println("Descrizione: ");
			System.out.println();
			System.out.println(ViewUtils.formattaPerBox(corso[2], 128));
			System.out.println();
			System.out.println("Inizia in data " + corso[3] + " a " + corso[4]);
			System.out.println("Disponibile: " + corso[5]);
		}
		
	}
	
	public void displayAvailableCorsi(List<String[]> availableCorsiList) {
		
		System.out.println("Corsi disponibili:");
		
		for (String[] corso : availableCorsiList) {
			System.out.println("ID: " + corso[0] + " / " + "Nome: " + corso[1]);
		} 
		
	}
}
