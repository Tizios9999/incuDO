package dao;

import java.util.List;

import model.Utente;

public interface UtenteDao {
	
	public void inserisciUtente(Utente utente);
	public void caricaUtenti(String csvPath);
	public Utente cercaUtentePerId(Integer id); 
	public Integer trovaUltimoId();
	public List<Utente> getListaUtenti();
	
}
