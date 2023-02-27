package view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Corso;

public class CorsoView {
	
	public void displayCorsi(List<Corso> listaCorsi) {
		
		Map<Boolean, String> boolToItaMap = new HashMap<>();
		boolToItaMap.put(true, "SI");
		boolToItaMap.put(false, "NO");
		
		for (Corso corso : listaCorsi) {
			System.out.println("ID: " + corso.getId());
			System.out.println("Nome: " + corso.getNome());
			System.out.println("Descrizione: " + corso.getDescrizione());
			System.out.println("Data Corso: " + corso.getDataCorso());
			System.out.println("Luogo: " + corso.getLuogo());
			System.out.println("Disponibile: " + boolToItaMap.get(corso.isDisponibile()));
			System.out.println("-------------------------------------------------------");
		}
		
	}
	
}
