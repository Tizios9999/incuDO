package dao;

import java.util.ArrayList;
import java.util.List;

import model.Utente;
import util.CsvDataManager;

public class UtenteDaoImpl implements UtenteDao {

	private String[] campiTabella = {"Id", "Nome", "Cognome", "Data di Nascita", "Indirizzo", "Documento ID"};
	private List<Utente> listaUtenti = new ArrayList<Utente>();
	
	public void addUtente(Utente utente) {
		// TODO Auto-generated method stub
		
		this.listaUtenti.add(utente);
		
	}

	public void loadUtenteTable(String csvFile) {
		// TODO Auto-generated method stub
		
		CsvDataManager dataProvider = new CsvDataManager();
		
		ArrayList<String[]> dataTable = dataProvider.loadFromCsv(csvFile, campiTabella);
		
		for (String[] row : dataTable) {
			Utente utente = new Utente.UtenteBuilder(row).build();
			listaUtenti.add(utente);
		}
     		
	}
	
	public Utente searchUtenteById(Integer id) {
		
		for (Utente utente : this.listaUtenti) {
			if (id == utente.getId()) {
				return utente;
			}
		}
		
		return null;
	}
	
	public Integer findLastId() {
		
		Integer newId = -1;
		
		for (Utente utente : this.listaUtenti) {
			newId = utente.getId() > newId ? utente.getId() : newId;
		}
		
		return newId;
	}
	
	public List<Utente> getListaUtenti() {
		
		return listaUtenti;
	}
}
