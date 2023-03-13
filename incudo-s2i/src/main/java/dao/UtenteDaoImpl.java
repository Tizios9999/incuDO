package dao;

import java.util.ArrayList;
import java.util.List;

import model.Corso;
import model.Utente;
import util.CsvDataManager;
import util.DataValidator;

/**
* Implementation of the {@link UtenteDao} interface that manages a list of {@link Utente} objects.
* The implementation provides methods to add, search, and retrieve {@link Utente} objects from the list.
*/

public class UtenteDaoImpl implements UtenteDao {

	/**
	 * Defines the headers and types of columns of the table that contains the data for {@link Utente} objects.
    */
	
	private String[][] tableHeaders = {
			{"Id", "Integer"}, 
			{"Nome", "String"},
			{"Cognome", "String"}, 
			{"Data di Nascita", "Date"}, 
			{"Indirizzo", "String"},
			{"Documento ID", "Alfanumeric"},
	};
	
	/**
    * The list that contains all {@link Utente} objects managed by this implementation.
    */
	
	private List<Utente> utentiList = new ArrayList<Utente>();
	
	/**
	 * Adds a new Utente object to the data store.
	 * 
	 * @param utente the Utente object to add
	 */
	
	public void addUtente(Utente utente) {
	
		this.utentiList.add(utente);	
	}

	/**
	 * Loads the data from a CSV file into the list of {@link Utente} objects. The
	 * CSV file must have a header row that matches the tableHeaders parameter. Each
	 * subsequent row must contain data for each column in the same order as the
	 * tableHeaders parameter.
	 * 
	 * @param csvFile the path of the CSV file to load
	 * @throws RuntimeException if the CSV file contains invalid data or if an I/O
	 *                          error occurs while reading the file
	 *
	 */
	
	public void loadUtenteTable(String csvFile) {

		List<String[]> dataTable = DaoUtils.loadDataFromCsvTable(csvFile, tableHeaders);
		
		for (String[] row : dataTable) {
			
			Utente utente = new Utente.UtenteBuilder(row).build();
			utentiList.add(utente);
		}
	}
	
	/**
	 * Searches for an Utente object in the data store by its ID.
	 *
	 * @param id the ID of the Utente object to search for
	 * @return an Utente object with the specified ID if found; null otherwise
	 */
	
	public Utente searchUtenteById(Integer id) {
		
		for (Utente utente : this.utentiList) {
			if (id == utente.getId()) {
				return utente;
			}
		}
		
		return null;
	}
	
	/**
	 * Finds the highest ID inside the users table
	 * 
	 * @return The highest ID found in the users table
	 */
	
	public Integer findLastId() {
		
		Integer newId = -1;
		
		for (Utente utente : this.utentiList) {
			newId = utente.getId() > newId ? utente.getId() : newId;
		}
		
		return newId;
	}
	
	public List<Utente> getListaUtenti() {
		
		return utentiList;
	}
}
