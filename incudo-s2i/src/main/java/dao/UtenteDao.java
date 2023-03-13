package dao;

import java.util.List;

import model.Utente;

/**
 * The UtenteDao interface provides methods for managing {@link Utente} objects in a data store.
 * 
 * @author Davide Santonocito
 *
 */

public interface UtenteDao {
	
	/**
	 * Adds a new Utente object to the data store.
	 * 
	 * @param utente the Utente object to add
	 */
	
	public void addUtente(Utente utente);
	
	/**
	 * Loads a table of users from a CSV file located at the specified path.
	 *
	 * @param csvPath the path to the CSV file containing the table of users.
	 */
	
	public void loadUtenteTable(String csvPath);
	
	/**
	 * Searches for an Utente object in the data store by its ID.
	 *
	 * @param id the ID of the Utente object to search for
	 * @return an Utente object with the specified ID if found; null otherwise
	 */
	
	public Utente searchUtenteById(Integer id); 
	
	/**
	 * Finds the highest ID inside the users table
	 * 
	 * @return The highest ID found in the users table
	 */
	
	public Integer findLastId();
	public List<Utente> getListaUtenti();
	
}
