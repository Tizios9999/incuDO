package dao;

import java.util.ArrayList;
import java.util.List;

import model.Prenotazione;
import util.CsvDataManager;

public class PrenotazioneDaoImpl implements PrenotazioneDao {

	private String[] campiTabella = { "ID", "ID Attività", "ID Utente", "Data Inizio", "Data Fine" };
	private List<Prenotazione> listaPrenotazioni = new ArrayList<Prenotazione>();

	public void addPrenotazione(Prenotazione prenotazione) {
		this.listaPrenotazioni.add(prenotazione);
	}

	public void loadPrenotazioneTable(String csvFile) {

		CsvDataManager providerDati = new CsvDataManager();

		ArrayList<String[]> tabellaDati = providerDati.loadFromCsv(csvFile, campiTabella);

		for (String[] riga : tabellaDati) {
			Prenotazione prenotazione = new Prenotazione.PrenotazioneBuilder(riga).build();
			this.addPrenotazione(prenotazione);
		}

	}

	// Returns an Integer with the first id available to ensure unique id

	public Integer firstPrenotazioneIdAvailable(Integer idCorso, Integer idUtente) {

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

	public Boolean removePrenotazione(Integer idCorso, Integer idUtente) {

		for (Prenotazione prenotazione : this.listaPrenotazioni) {

			if (idCorso == prenotazione.getIdAttività() && idUtente == prenotazione.getIdUtente()) {
				listaPrenotazioni.remove(prenotazione);

				return true;
			}
		}
		return false;
	}


	public List<Prenotazione> getListaPrenotazioni() {
		
		return listaPrenotazioni;
	}

	public void setListaPrenotazioni(List<Prenotazione> listaPrenotazioni) {
		this.listaPrenotazioni = listaPrenotazioni;
	}

}
