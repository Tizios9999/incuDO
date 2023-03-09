package dao;

import java.util.ArrayList;
import java.util.List;

import model.Prenotazione;
import util.CsvDataManager;

public class PrenotazioneDaoImpl implements PrenotazioneDao {

	private String[] tableHeaders = { "ID", "ID Attività", "ID Utente", "Data Inizio", "Data Fine" };
	private List<Prenotazione> prenotazioniList = new ArrayList<Prenotazione>();

	public void addPrenotazione(Prenotazione prenotazione) {
		this.prenotazioniList.add(prenotazione);
	}

	public void loadPrenotazioneTable(String csvFile) {

		CsvDataManager dataProvider = new CsvDataManager();

		ArrayList<String[]> dataTable = dataProvider.loadFromCsv(csvFile, tableHeaders);

		for (String[] row : dataTable) {
			Prenotazione prenotazione = new Prenotazione.PrenotazioneBuilder(row).build();
			this.addPrenotazione(prenotazione);
		}

	}

	// Returns an Integer with the first id available to ensure unique id

	public Integer firstPrenotazioneIdAvailable(Integer idCorso, Integer idUtente) {

		Integer availableSlot = -1;

		for (Prenotazione prenotazione : this.prenotazioniList) {

			if (idCorso == prenotazione.getIdAttività() || idUtente == prenotazione.getIdUtente()) {
				return -1;
			} else {
				availableSlot = availableSlot < prenotazione.getId() ? prenotazione.getId() : availableSlot;
			}

		}

		return availableSlot + 1;
	}

	public Boolean removePrenotazione(Integer idCorso, Integer idUtente) {

		for (Prenotazione prenotazione : this.prenotazioniList) {

			if (idCorso == prenotazione.getIdAttività() && idUtente == prenotazione.getIdUtente()) {
				prenotazioniList.remove(prenotazione);

				return true;
			}
		}
		return false;
	}


	public List<Prenotazione> getListaPrenotazioni() {
		
		return prenotazioniList;
	}

	public void setListaPrenotazioni(List<Prenotazione> prenotazioniList) {
		this.prenotazioniList = prenotazioniList;
	}

}
