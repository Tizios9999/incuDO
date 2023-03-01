package dao;

import model.Utente;

public interface UtenteDao {
	
	public void caricaUtenti(String csvPath);
	public Utente cercaUtentePerId(Integer id); 
	public void aggiungiUtente(Utente utente);
	
}
