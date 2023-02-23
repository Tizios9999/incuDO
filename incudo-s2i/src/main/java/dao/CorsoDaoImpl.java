package dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Corso;
import util.EstrattoreDati;

public class CorsoDaoImpl {
	
	private String[] campiTabella = {"Id", "Nome", "Descrizione", "Data", "Durata", "Luogo", "Disponibile"};
	
	private List<Corso> listaCorsi = new ArrayList<>();
	
	public void caricaCorsi(String csvFile) {
		
		System.out.println("Caricamento corsi");
		
		EstrattoreDati providerDati = new EstrattoreDati();
		
		ArrayList<String[]> tabellaDati = providerDati.caricaDaCsv(csvFile, campiTabella);
		
		for (String[] riga : tabellaDati) {
//			Corso corso = new Corso.CorsoBuilder(riga).build();
//			listaCorsi.add(corso);
//        	System.out.println(corso);
			System.out.println(Arrays.toString(riga));
		}
		
		System.out.println(listaCorsi);
		
	}
	
}
