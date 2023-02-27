package dao;

import java.util.ArrayList;
import java.util.List;

import model.Prenotazione;
import util.EstrattoreDati;

public class PrenotazioneDaoImpl implements PrenotazioneDao {

	private String[] campiTabella = { "ID", "ID Attività", "ID Utente", "Data Inizio", "Data Fine" };
	private List<Prenotazione> listaPrenotazioni = new ArrayList<Prenotazione>();

	public void caricaPrenotazioni(String csvFile) {
		System.out.println("Carico Prenotazioni");

		EstrattoreDati providerDati = new EstrattoreDati();

		ArrayList<String[]> tabellaDati = providerDati.caricaDaCsv(csvFile, campiTabella);

		for (String[] riga : tabellaDati) {
			Prenotazione prenotazione = new Prenotazione.PrenotazioneBuilder(riga).build();
			listaPrenotazioni.add(prenotazione);
			System.out.println(prenotazione);
		}

		System.out.println(listaPrenotazioni);

	}

	public void togglePrenotazione(Integer idCorso, Integer idUtente) {
		// TODO Auto-generated method stub
		System.out.println(idCorso + " " + idUtente);
	}

}
