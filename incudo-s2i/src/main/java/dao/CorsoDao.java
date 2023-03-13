package dao;

import java.util.List;

import model.Corso;

/**
 * 
 * The CorsoDao interface provides methods for managing and manipulating
 * courses.
 * <p>
 * Methods include loading a CSV file of course data, searching for a course by
 * ID, setting a course's availability, exporting course data to a CSV file, getting
 * a list of all courses, and setting the list of courses.
 * </p>
 */
public interface CorsoDao {

	/**
	 * Load course data from a CSV file.
	 * 
	 * @param CSVpath the file path of the CSV file containing course data
	 */
	public void loadCorsoTable(String CSVpath);

	/**
	 * Search for a course by ID.
	 * 
	 * @param id the ID of the course to search for
	 * @return the Corso object corresponding to the given ID, or null if not found
	 */
	public Corso searchCorsoById(Integer id);

	/**
	 * Set the availability of a course.
	 * 
	 * @param id        the ID of the course to set availability for
	 * @param available the availability status to set (true for available, false
	 *                  for not available)
	 */
	public void setDisponibileCorso(Integer id, Boolean available);

	/**
	 * Export course data to a CSV file.
	 */
	public void exportCsv();

	/**
	 * Get a list of all courses.
	 * 
	 * @return a list of all courses
	 */
	public List<Corso> getListaCorsi();

	/**
	 * Set the list of courses.
	 * 
	 * @param corsiList the list of courses to set
	 */
	public void setListaCorsi(List<Corso> corsiList);

}