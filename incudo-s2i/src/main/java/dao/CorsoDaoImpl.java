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
	
	public void esportaCsv() {
		
		ArrayList<String[]> listaDaEsportare = new ArrayList<String[]>();
		
		String[] headers = {"Id", "Nome", "Descrizione", "Data", "Durata", "Luogo"};
		
		for (Corso corso : this.listaCorsi) {
			
			if (corso.isDisponibile()) {
				
				String[] valori = new String[headers.length];
				valori[0] = String.valueOf(corso.getId());
				valori[1] = corso.getNome();
				valori[2] = corso.getDescrizione();
				valori[3] = String.valueOf(corso.getDataCorso());
				valori[4] = String.valueOf(corso.getDurata());
				valori[5] = corso.getLuogo();
				listaDaEsportare.add(valori);
			
			}
		 
		}
		
		EstrattoreDati providerDati = new EstrattoreDati();
		providerDati.scriviCsv(headers, listaDaEsportare);
	}

	public List<Corso> getListaCorsi() {
		
		return listaCorsi;
	}

	public void setListaCorsi(List<Corso> listaCorsi) {
		this.listaCorsi = listaCorsi;
	}
	
	
	
}
