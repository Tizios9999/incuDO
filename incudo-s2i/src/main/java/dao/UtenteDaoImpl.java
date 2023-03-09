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
		
		CsvDataManager providerDati = new CsvDataManager();
		
		ArrayList<String[]> tabellaDati = providerDati.loadFromCsv(csvFile, campiTabella);
		
		for (String[] riga : tabellaDati) {
			Utente utente = new Utente.UtenteBuilder(riga).build();
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
		
		Integer nuovoId = -1;
		
		for (Utente utente : this.listaUtenti) {
			nuovoId = utente.getId() > nuovoId ? utente.getId() : nuovoId;
		}
		
		return nuovoId;
	}
	
	public List<Utente> getListaUtenti() {
		
		return listaUtenti;
	}
}
