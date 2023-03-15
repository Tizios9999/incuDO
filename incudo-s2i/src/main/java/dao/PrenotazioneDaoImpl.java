package dao;

import java.util.ArrayList;
import java.util.List;

import model.Prenotazione;

/**
 * Implementation of the {@link PrenotazioneDao} interface that uses an in-memory
 * list to store and manage prenotazioni data. It also provides a method to load
 * data from a CSV file.
 * 
 * @author Davide Santonocito
 *
 */

public class PrenotazioneDaoImpl implements PrenotazioneDao {

	// Class fields and constants	
	
	/**
	 * Array containing the headers for the CSV table used to load prenotazioni data.
	 * Each element of the array is a two-element array containing the column name
	 * and the expected data type (as a string).
	 */
	
	private String[][] tableHeaders = { 
			{"ID", "Integer"},
			{"ID Attività", "Integer"},
			{"ID Utente", "Integer"},
			{"Data Inizio", "Date"},
			{"Data Fine", "Date"}
	};
	
	/**
	 * List containing the prenotazioni data loaded or added to the DAO.
	 */
	
	private List<Prenotazione> prenotazioniList = new ArrayList<Prenotazione>();

	// Public interface methods
	
	public void addPrenotazione(Prenotazione prenotazione) {
		this.prenotazioniList.add(prenotazione);
	}
	
	/**
	 * Loads the data from a CSV file into the list of {@link Prenotazione} objects. The
	 * CSV file must have a header row that matches the tableHeaders parameter. Each
	 * subsequent row must contain data for each column in the same order as the
	 * tableHeaders parameter.
	 * 
	 * @param csvFile the path of the CSV file to load
	 * @throws RuntimeException if the CSV file contains invalid data or if an I/O
	 *                          error occurs while reading the file
	 *
	 */

	public void loadPrenotazioneTable(String csvFile) {

		List<String[]> dataTable = DaoUtils.loadDataFromCsvTable(csvFile, tableHeaders);

		for (String[] row : dataTable) {
			
			Prenotazione prenotazione = new Prenotazione.PrenotazioneBuilder(row).build();
			this.addPrenotazione(prenotazione);
		}
	}

	/**
	 * Returns the first available booking ID for the specified course and user
	 * to ensure unique id.
	 * If the user is enrolling another course, or the course is not available,
	 * -1 is returned instead
	 * 
	 * @param idCorso  the ID of the course.
	 * @param idUtente the ID of the user.
	 * @return the first available booking ID, or -1 if not available.
	 * 
	 */

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

	/**
	 * Removes the prenotazione object in the DAO's list that matches the given
	 * IDs for course and user.
	 * 
	 * @param idCorso  the ID of the course for which the booking was made.
	 * @param idUtente the ID of the user who made the booking.
	 * @return true if the booking was successfully removed, false otherwise.
	 */
	
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
