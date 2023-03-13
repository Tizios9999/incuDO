package dao;

import java.util.List;

import model.Prenotazione;

/**
 * This interface provides methods to manage bookings for courses.
 * 
 * @author Davide Santonocito
 * 
 */
public interface PrenotazioneDao {

	/**
	 * Adds a new booking to the system.
	 * 
	 * @param prenotazione the booking to be added.
	 */
	public void addPrenotazione(Prenotazione prenotazione);

	/**
	 * Loads the booking data from a CSV file.
	 * 
	 * @param CSVPath the path of the CSV file to load.
	 * 
	 */

	public void loadPrenotazioneTable(String CSVPath);

	/**
	 * Returns the first available booking ID for the specified course and user
	 * to ensure unique id.
	 * If the user is enrolling another course, or the course is not available,
	 * -1 is returned instead
	 * 
	 * @param idCorso  the ID of the course.
	 * @param idUtente the ID of the user.
	 * @return the first available booking ID, or -1 if not available.
	 */
	public Integer firstPrenotazioneIdAvailable(Integer idCorso, Integer idUtente);

	/**
	 * Removes a booking from the system.
	 * 
	 * @param idCorso  the ID of the course for which the booking was made.
	 * @param idUtente the ID of the user who made the booking.
	 * @return true if the booking was successfully removed, false otherwise.
	 */
	public Boolean removePrenotazione(Integer idCorso, Integer idUtente);

	public List<Prenotazione> getListaPrenotazioni();

	public void setListaPrenotazioni(List<Prenotazione> prenotazioniList);

}