package dao;

import model.Utenti;

public interface UtentiDao {
	
	public void caricaUtenti(String csvPath);
	public void aggiungiUtente(Utenti utente);
	
}
