package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Utente;

public class UtenteDaoImpl implements UtenteDao {

	private String[] campiTabellaUtenti = {"Id", "Nome", "Cognome", "Data di Nascita", "Indirizzo", "Documento ID"};
	private List<Utente> listaUtenti = new ArrayList<>();
	
	public void aggiungiUtente(Utente utente) {
		// TODO Auto-generated method stub
		
		System.out.println("Aggiunto utente:" + utente.toString());
	}

	public void caricaUtenti(String csvPath) {
		// TODO Auto-generated method stub
		System.out.println("Caricamento utenti");
		
		
		
		String csvFile = "src\\main\\resources\\utenti.csv";
        String line = "";
        String csvDelimiter = ";";
        String[] data;
        int pos = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                data = line.split(csvDelimiter);
                // process data here
                
                if (pos > 0) {
                	Utente utente = new Utente.UtenteBuilder(data).build();
                	listaUtenti.add(utente);
                }
                pos++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        System.out.println(listaUtenti);
	}
	
}
