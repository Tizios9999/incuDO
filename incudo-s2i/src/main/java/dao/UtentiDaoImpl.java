package dao;

import java.util.ArrayList;
import java.util.List;

import model.Utenti;

public class UtentiDaoImpl implements UtentiDao {

	private List<Utenti> listaUtenti = new ArrayList<>();
	
	public void aggiungiUtente(Utenti utente) {
		// TODO Auto-generated method stub
		
		System.out.println("Aggiunto utente:" + utente.toString());
	}

	public void caricaUtenti(String csvPath) {
		// TODO Auto-generated method stub
		System.out.println("Caricamento utenti");
	}
	
}
