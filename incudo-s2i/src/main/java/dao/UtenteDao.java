package dao;

import java.util.List;

import model.Utente;

public interface UtenteDao {
	
	public void addUtente(Utente utente);
	public void loadUtenteTable(String csvPath);
	public Utente searchUtenteById(Integer id); 
	public Integer findLastId();
	public List<Utente> getListaUtenti();
	
}
