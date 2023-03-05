package dao;

import java.util.ArrayList;
import java.util.List;

import model.Prenotazione;
import util.EstrattoreDati;

public class PrenotazioneDaoImpl implements PrenotazioneDao {

	private String[] campiTabella = { "ID", "ID Attività", "ID Utente", "Data Inizio", "Data Fine" };
	private List<Prenotazione> listaPrenotazioni = new ArrayList<Prenotazione>();

	public void aggiungiPrenotazione(Prenotazione prenotazione) {
		this.listaPrenotazioni.add(prenotazione);
	}

	public void caricaPrenotazioni(String csvFile) {

		EstrattoreDati providerDati = new EstrattoreDati();

		ArrayList<String[]> tabellaDati = providerDati.caricaDaCsv(csvFile, campiTabella);

		for (String[] riga : tabellaDati) {
			Prenotazione prenotazione = new Prenotazione.PrenotazioneBuilder(riga).build();
			this.aggiungiPrenotazione(prenotazione);
		}

	}

	// Returns an Integer with the first id available to ensure unique id

	public Integer disponibilitàIdPrenotazione(Integer idCorso, Integer idUtente) {

		Integer slotDisponibile = -1;

		for (Prenotazione prenotazione : this.listaPrenotazioni) {

			if (idCorso == prenotazione.getIdAttività() || idUtente == prenotazione.getIdUtente()) {
				return -1;
			} else {
				slotDisponibile = slotDisponibile < prenotazione.getId() ? prenotazione.getId() : slotDisponibile;
			}

		}

		return slotDisponibile + 1;
	}

	public Boolean cancellaPrenotazione(Integer idCorso, Integer idUtente) {

		for (Prenotazione prenotazione : this.listaPrenotazioni) {

			if (idCorso == prenotazione.getIdAttività() && idUtente == prenotazione.getIdUtente()) {
				listaPrenotazioni.remove(prenotazione);

				return true;
			}
		}
		return false;
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
