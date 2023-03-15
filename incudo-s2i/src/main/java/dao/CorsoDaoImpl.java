package dao;

import java.util.ArrayList;
import java.util.List;

import model.Corso;
import util.CsvDataManager;

/**
 * Implementation of the CorsoDao interface that provides data access methods
 * for managing Corsi objects. The class loads a CSV file containing course
 * data, validates the data, creates Corso objects and stores them in a list. It
 * also provides methods for searching and updating Corso objects, exporting a
 * subset of course data to a new CSV file, and getting or setting the list of
 * Corso objects.
 *
 * @author Davide Santonocito
 */

public class CorsoDaoImpl implements CorsoDao {

	// Fields

	/**
	 * The headers of the CSV file containing course data. Each header contains a
	 * name and a type.
	 */

	private String[][] tableHeaders = { { "Id", "Integer" }, { "Nome", "String" }, { "Descrizione", "String" },
			{ "Data", "Date" }, { "Durata", "Integer" }, { "Luogo", "String" }, { "Disponibile", "Disponibile" } };

	/**
	 * The list of Corso objects containing the course data loaded from the CSV
	 * file.
	 */

	private List<Corso> corsiList = new ArrayList<>();

	// Methods

	/**
	 * Loads the data from a CSV file into the list of {@link Corso} objects. The
	 * CSV file must have a header row that matches the tableHeaders parameter. Each
	 * subsequent row must contain data for each column in the same order as the
	 * tableHeaders parameter.
	 * 
	 * @param csvFile the path of the CSV file to load
	 * @throws RuntimeException if the CSV file contains invalid data or if an I/O
	 *                          error occurs while reading the file
	 *
	 */

	public void loadCorsoTable(String csvFile) {

		List<String[]> dataTable = DaoUtils.loadDataFromCsvTable(csvFile, tableHeaders);
		
		for (String[] row : dataTable) {

			Corso corso = new Corso.CorsoBuilder(row).build();
			corsiList.add(corso);
		}

	}

	/**
	 * Searches for a Corso object in the list of Corsi by its ID.
	 * 
	 * @param id the ID of the Corso object to search for
	 * @return the Corso object with the specified ID, or null if it is not found
	 */

	public Corso searchCorsoById(Integer id) {

		for (Corso corso : this.corsiList) {

			if (id == corso.getId()) {
				return corso;
			}
		}

		return null;
	}

	/**
	 * Sets the disponibile field of a Corso object in the list of Corsi by its ID.
	 * 
	 * @param id        the ID of the Corso object to update
	 * @param available the new value of the disponibile field
	 */

	public void setDisponibileCorso(Integer id, Boolean available) {

		for (Corso corso : this.corsiList) {

			if (id == corso.getId()) {
				corso.setDisponibile(available);
				break;
			}
		}
	}

	/**
	 * Exports a subset of course data to a new CSV file. Only the Corsi that have
	 * the disponibile (available) field set to true are exported.
	 * 
	 * @throws RuntimeException if an error occurs while writing the CSV file
	 */

	public void exportCsv() {

		ArrayList<String[]> listToExport = new ArrayList<String[]>();
		String[] headers = { "Id", "Nome", "Descrizione", "Data", "Durata", "Luogo" };

		for (Corso corso : this.corsiList) {

			if (corso.isDisponibile()) {

				String[] values = new String[headers.length];
				values[0] = String.valueOf(corso.getId());
				values[1] = corso.getNome();
				values[2] = corso.getDescrizione();
				values[3] = String.valueOf(corso.getDataCorso());
				values[4] = String.valueOf(corso.getDurata());
				values[5] = corso.getLuogo();
				listToExport.add(values);
			}
		}

		CsvDataManager providerDati = new CsvDataManager();
		providerDati.writeCsv(headers, listToExport);
	}

	public List<Corso> getListaCorsi() {
		return corsiList;
	}

	public void setListaCorsi(List<Corso> corsiList) {
		this.corsiList = corsiList;
	}

}
