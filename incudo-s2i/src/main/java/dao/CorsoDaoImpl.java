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
		
		EstrattoreDati providerDati = new EstrattoreDati();
		
		ArrayList<String[]> tabellaDati = providerDati.caricaDaCsv(csvFile, campiTabella);
		
		for (String[] riga : tabellaDati) {
			Corso corso = new Corso.CorsoBuilder(riga).build();
			listaCorsi.add(corso);
		}
		
	}
	
	public Corso cercaCorsoPerId(Integer id) {
		
		for (Corso corso : this.listaCorsi) {
			
			if (id == corso.getId()) {
				return corso;
			}
		}
		
		return null;
	}
	
	public void setDisponibilitàCorso(Integer id, Boolean disponibile) {
		
		for (Corso corso : this.listaCorsi) {
			
			if (id == corso.getId()) {
				corso.setDisponibile(disponibile);
				System.out.println(corso);
				break;
			}
		}
		
	}

	public List<Corso> getListaCorsi() {
		return listaCorsi;
	}

	public void setListaCorsi(List<Corso> listaCorsi) {
		this.listaCorsi = listaCorsi;
	}
	
	
	
}
