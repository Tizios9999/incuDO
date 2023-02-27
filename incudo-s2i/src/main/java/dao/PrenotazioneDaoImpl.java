package dao;

import java.util.ArrayList;
import java.util.List;

import model.Prenotazione;
import util.EstrattoreDati;

public class PrenotazioneDaoImpl implements PrenotazioneDao {

	private String[] campiTabella = { "ID", "ID Attività", "ID Utente", "Data Inizio", "Data Fine" };
	private List<Prenotazione> listaPrenotazioni = new ArrayList<Prenotazione>();

	public void caricaPrenotazioni(String csvFile) {

		EstrattoreDati providerDati = new EstrattoreDati();

		ArrayList<String[]> tabellaDati = providerDati.caricaDaCsv(csvFile, campiTabella);

		for (String[] riga : tabellaDati) {
			Prenotazione prenotazione = new Prenotazione.PrenotazioneBuilder(riga).build();
			listaPrenotazioni.add(prenotazione);
		}

	}

	public void togglePrenotazione(Integer idCorso, Integer idUtente) {
		// TODO Auto-generated method stub
		System.out.println(idCorso + " " + idUtente);
	}

	public List<Prenotazione> getListaPrenotazioni() {
		return listaPrenotazioni;
	}

	public void setListaPrenotazioni(List<Prenotazione> listaPrenotazioni) {
		this.listaPrenotazioni = listaPrenotazioni;
	}
	
	

}
