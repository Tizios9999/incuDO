package dao;

import java.util.ArrayList;
import java.util.List;

import model.Utente;
import util.CsvDataManager;
import util.DataValidator;

public class UtenteDaoImpl implements UtenteDao {

	private String[][] tableHeaders = {
			{"Id", "Integer"}, 
			{"Nome", "String"},
			{"Cognome", "String"}, 
			{"Data di Nascita", "Date"}, 
			{"Indirizzo", "String"},
			{"Documento ID", "Alfanumeric"},
	};
	
	private List<Utente> utentiList = new ArrayList<Utente>();
	
	public void addUtente(Utente utente) {
		// TODO Auto-generated method stub
		
		this.utentiList.add(utente);
		
	}

	public void loadUtenteTable(String csvFile) {
		// TODO Auto-generated method stub
		
		CsvDataManager dataProvider = new CsvDataManager();
		
		ArrayList<String[]> dataTable = dataProvider.loadFromCsv(csvFile, tableHeaders);
		
		for (String[] row : dataTable) {
			
			for (int i = 0; i < tableHeaders.length; i++) {
				
				String type = tableHeaders[i][1];
				
				if (!DataValidator.isValidData(type, row[i])) {
					
					int numRiga = dataTable.indexOf(row) + 1;
					
					String errore = "Errore durante la lettura del file " + csvFile + " nella colonna " + tableHeaders[i][0] + " alla riga " + numRiga +". Ricontrollare il file e riprovare a riavviare l'applicazione.";
					
					throw new RuntimeException(errore);
				}
				
			}
			
			Utente utente = new Utente.UtenteBuilder(row).build();
			utentiList.add(utente);
		}
     		
	}
	
	public Utente searchUtenteById(Integer id) {
		
		for (Utente utente : this.utentiList) {
			if (id == utente.getId()) {
				return utente;
			}
		}
		
		return null;
	}
	
	public Integer findLastId() {
		
		Integer newId = -1;
		
		for (Utente utente : this.utentiList) {
			newId = utente.getId() > newId ? utente.getId() : newId;
		}
		
		return newId;
	}
	
	public List<Utente> getListaUtenti() {
		
		return utentiList;
	}
}
