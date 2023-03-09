package dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Corso;
import util.CsvDataManager;

public class CorsoDaoImpl implements CorsoDao {
	
	private String[] tableHeaders = {"Id", "Nome", "Descrizione", "Data", "Durata", "Luogo", "Disponibile"};
	
	private List<Corso> listaCorsi = new ArrayList<>();
	
	public void loadCorsoTable(String csvFile) {
		
		CsvDataManager dataProvider = new CsvDataManager();
		
		ArrayList<String[]> dataTable = dataProvider.loadFromCsv(csvFile, tableHeaders);
		
		for (String[] row : dataTable) {
			Corso corso = new Corso.CorsoBuilder(row).build();
			listaCorsi.add(corso);
		}
		
	}
	
	public Corso searchCorsoById(Integer id) {
		
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
				break;
			}
		}
		
	}
	
	public void exportCsv() {
		
		ArrayList<String[]> listToExport = new ArrayList<String[]>();
		
		String[] headers = {"Id", "Nome", "Descrizione", "Data", "Durata", "Luogo"};
		
		for (Corso corso : this.listaCorsi) {
			
			if (corso.isDisponibile()) {
				
				String[] values = new String[headers.length];
				values[0] = String.valueOf(corso.getId());
				values[1] = corso.getNome();
				values[2] = corso.getDescrizione();
				values[3] = String.valueOf(corso.getDataCorso());
				values[4] = String.valueOf(corso.getDurata());
				values[5] = corso.getLuogo();
				listToExport.add(values);
			
			}
		 
		}
		
		CsvDataManager providerDati = new CsvDataManager();
		providerDati.writeCsv(headers, listToExport);
	}

	public List<Corso> getListaCorsi() {
		
		return listaCorsi;
	}

	public void setListaCorsi(List<Corso> listaCorsi) {
		this.listaCorsi = listaCorsi;
	}
	
	
	
}
