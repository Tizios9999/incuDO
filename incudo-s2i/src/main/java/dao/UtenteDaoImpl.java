package dao;

import java.util.ArrayList;
import java.util.List;

import model.Utente;
import util.EstrattoreDati;

public class UtenteDaoImpl implements UtenteDao {

	private String[] campiTabella = {"Id", "Nome", "Cognome", "Data di Nascita", "Indirizzo", "Documento ID"};
	private List<Utente> listaUtenti = new ArrayList<Utente>();
	
	public void aggiungiUtente(Utente utente) {
		// TODO Auto-generated method stub
		
		System.out.println("Aggiunto utente:" + utente.toString());
	}

	public void caricaUtenti(String csvFile) {
		// TODO Auto-generated method stub
		
		EstrattoreDati providerDati = new EstrattoreDati();
		
		ArrayList<String[]> tabellaDati = providerDati.caricaDaCsv(csvFile, campiTabella);
		
		for (String[] riga : tabellaDati) {
			Utente utente = new Utente.UtenteBuilder(riga).build();
			listaUtenti.add(utente);
		}
     		
	}
	
}
