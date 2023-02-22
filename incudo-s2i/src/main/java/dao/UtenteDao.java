package dao;

import model.Utente;

public interface UtenteDao {
	
	public void caricaUtenti(String csvPath);
	public void aggiungiUtente(Utente utente);
	
}
