package service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.CorsoDaoImpl;
import dao.PrenotazioneDaoImpl;
import dao.UtenteDaoImpl;
import model.Corso;
import model.Prenotazione;
import model.Utente;

/**
 * This singleton Service class is an intermediary between the
 * implementations of Dao interfaces, the Controller and the views.
 */

public class Service {

	private CorsoDaoImpl corsoDaoImpl;
	private UtenteDaoImpl utenteDaoImpl;
	private PrenotazioneDaoImpl prenotazioneDaoImpl;
	
	/**
     * A map that associates boolean values with their Italian translation.
     */
	
	private static Map<Boolean, String> boolToItaMap = new HashMap<>();
	
	static {
		boolToItaMap.put(true, "SI");
		boolToItaMap.put(false, "NO");
	}
	
	 /**
     * The instance of this class that is returned by the {@link #getInstance()} method.
     */
	
	private static Service service;
	
	private Service() {
	}

	/**
     * Returns the singleton instance of this class.
     * @return the singleton instance of this class.
     */
	
	public static Service getInstance() {
		if (service == null) {
			service = new Service();
		}
		return service;
	}

	/**
     * Loads the tables data from CSV files.
     */
	
	public void loadTablesData() {
		this.corsoDaoImpl = new CorsoDaoImpl();
		this.utenteDaoImpl = new UtenteDaoImpl();
		this.prenotazioneDaoImpl = new PrenotazioneDaoImpl();

		corsoDaoImpl.loadCorsoTable("corsi.csv");
		utenteDaoImpl.loadUtenteTable("utenti.csv");
		prenotazioneDaoImpl.loadPrenotazioneTable("prenotazioni.csv");

	}
	
	/**
     * Converts a {@link LocalDate} object into a string in the format "dd/MM/yyyy".
     * 
     * @param date the date to be converted.
     * @return the string representation of the date.
     */
	
	public static String convertDateIntoString(LocalDate date) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = date.format(formatter);

		return formattedDate;
	}

	/**
     * Creates a new prenotazione with the given corso and utente IDs.
     * It will ensure to create a new ID for this prenotazione as well.
     * 
     * @param idCorso the ID of the corso to be booked.
     * @param idUtente the ID of the utente who wants to book the corso.
     */
	
	public void createPrenotazione(Integer idCorso, Integer idUtente) {

		Corso corso = this.corsoDaoImpl.searchCorsoById(idCorso);
		Utente utente = this.utenteDaoImpl.searchUtenteById(idUtente);

		if (utente == null || corso == null) {
			System.out.println("Corso o utente non trovato");
		} else {

			Integer newId = this.prenotazioneDaoImpl.firstPrenotazioneIdAvailable(idCorso, idUtente);

			if (newId > 0) {

				LocalDate startDate = corso.getDataCorso();
				LocalDate endDate = startDate.plusDays(corso.getDurata() / 24);
				Prenotazione nuovaPrenotazione = new Prenotazione(newId, corso.getId(), utente.getId(), startDate,
						endDate);

				this.prenotazioneDaoImpl.addPrenotazione(nuovaPrenotazione);
				
				System.out.println("Nuova prenotazione corso: " + corso.getNome()); 
				System.out.println("Iscritto: " + utente.getNome() + " " + utente.getCognome()); 
				System.out.println("Da " + startDate + " a " + endDate);
				
				corsoDaoImpl.setDisponibileCorso(corso.getId(), false);

			} else {
				System.out.println("Prenotazione non disponibile");
			}
		}
	}

	/**
     * Cancels an existing prenotazione with the given corso and utente IDs.
     * 
     * @param idCorso the ID of the corso to be cancelled.
     * @param idUtente the ID of the utente who wants to cancel the corso.
     */
	
	public void cancelPrenotazione(Integer idCorso, Integer idUtente) {

		Corso corso = this.corsoDaoImpl.searchCorsoById(idCorso);
		Utente utente = this.utenteDaoImpl.searchUtenteById(idUtente);

		if (utente == null || corso == null) {
			
			System.out.println("Corso o utente non trovato");
			
		} else {

			if (this.prenotazioneDaoImpl.removePrenotazione(idCorso, idUtente)) {
				
				corsoDaoImpl.setDisponibileCorso(corso.getId(), true);
				System.out.println("Prenotazione cancellata");
				
			} else {
			
				System.out.println("Prenotazione non trovata");
			}
		}
	}

	/**
     * Creates a new utente with the given attributes.
     * 
     * @param nome the name of the new utente.
     * @param cognome the surname of the new utente.
     * @param dataNascita the date of birth of the new utente in the format "dd/MM/yyyy".
     * @param indirizzoUtente the address of the new utente.
     * @param documentoIdUtente the ID document of the new utente.
     */
	
	public void createNewUtente(String nome, String cognome, String dataNascita, String indirizzoUtente,
			String documentoIdUtente) {

		String id = String.valueOf(this.utenteDaoImpl.findLastId() + 1);
		String[] arrayUtente = { id, nome, cognome, dataNascita, indirizzoUtente, documentoIdUtente };
		Utente utente = new Utente.UtenteBuilder(arrayUtente).build();

		this.utenteDaoImpl.addUtente(utente);
	}
	
	/**
     * Exports the corso table to a CSV file.
     */

	public void exportCorsoCsvTable() {

		this.corsoDaoImpl.exportCsv();
	}

	
	
	public List<Corso> getListaCorsi() {

		return this.corsoDaoImpl.getListaCorsi();
	}

	 /**
     * Returns the list of all prenotazioni as an array of string arrays.
     * Each string array contains the ID of the prenotazione, the ID of the corso, the ID of the utente,
     * the start date of the prenotazione and the end date of the prenotazione.
	*/
	
	public List<String[]> getStringPrenotazioni() {

		List<Prenotazione> listaPrenotazioni = prenotazioneDaoImpl.getListaPrenotazioni();
		List<String[]> listaPrenotazioniStr = new ArrayList<String[]>();

		for (Prenotazione prenotazione : listaPrenotazioni) {
			String id = prenotazione.getId().toString();
			String idCorso = prenotazione.getIdAttività().toString();
			String idUtente = prenotazione.getIdUtente().toString();
			String dataInizio = convertDateIntoString(prenotazione.getDataInizio());
			String dataFine = convertDateIntoString(prenotazione.getDataFine());

			String[] prenotazioneStr = { id, idCorso, idUtente, dataInizio, dataFine };
			listaPrenotazioniStr.add(prenotazioneStr);
		}

		return listaPrenotazioniStr;
	}

	/**
	 * Returns a List of arrays of Strings based on the list of prenotazioni, with additional
	 * information took out from the Utente and Corso tables, such as the name and id of the 
	 * course and the id, name, surname of the user. Mainly used for the view layer.
	 * 
	 */
	
	public List<String[]> getStringPrenotazioniUtentiCorsi() {

		List<Prenotazione> listaPrenotazioni = prenotazioneDaoImpl.getListaPrenotazioni();
		List<String[]> listaPrenotazioniUtentiCorsiStr = new ArrayList<String[]>();

		for (Prenotazione prenotazione : listaPrenotazioni) {
			
			Utente utente = this.utenteDaoImpl.searchUtenteById(prenotazione.getIdUtente());
			Corso corso = this.corsoDaoImpl.searchCorsoById(prenotazione.getIdAttività());
			
			String id = prenotazione.getId().toString();
			String idCorso = prenotazione.getIdAttività().toString();
			String nomeCorso = corso.getNome();
			String idUtente = prenotazione.getIdUtente().toString();
			String nominativoUtente = utente.getNome() + " " + utente.getCognome();
			String dataInizio = convertDateIntoString(prenotazione.getDataInizio());
			String dataFine = convertDateIntoString(prenotazione.getDataFine());

			String[] prenotazioneStr = { id, idCorso, nomeCorso, idUtente, nominativoUtente, dataInizio, dataFine };
			listaPrenotazioniUtentiCorsiStr.add(prenotazioneStr);
		}

		return listaPrenotazioniUtentiCorsiStr;
	}
	
	/**
	 * Returns a List of arrays of Strings that represents all the courses inside the system.
	 * Mainly used for the view layer.
	 */
	
	public List<String[]> getStringCorsi() {
		
		List<Corso> listaCorsi = corsoDaoImpl.getListaCorsi();
		List<String[]> listaCorsiStr = new ArrayList<String[]>();
		
		for (Corso corso : listaCorsi) {
			
			String id = String.valueOf(corso.getId());
			String nomeCorso = corso.getNome();
			String descrizioneCorso = corso.getDescrizione();
			String dataCorso = convertDateIntoString(corso.getDataCorso());
			String luogo = corso.getLuogo();
			String disponibile = boolToItaMap.get(corso.isDisponibile());
			
			String[] corsoStr = { id, nomeCorso, descrizioneCorso, dataCorso, luogo, disponibile };
			
			listaCorsiStr.add(corsoStr);
		}
		
		return listaCorsiStr;
	}
	
	/**
	 * Returns a List of arrays of Strings that contains the id and names of all
	 * available courses.
	 * Mainly used for the view layer.
	 */
	
	public List<String[]> getStringAvailableCorsi() {

		List<String[]> availableCorsiList = new ArrayList<String[]>();
		List<Corso> corsiList = corsoDaoImpl.getListaCorsi();
		
		for (Corso corso : corsiList) {
			
			if (corso.isDisponibile()) {
				String idCorso = String.valueOf(corso.getId());
				String nomeCorso = corso.getNome();
				String[] corsoStr = { idCorso, nomeCorso };
				availableCorsiList.add(corsoStr);
			}

		}
		return availableCorsiList;
	}
	
	/**
	 * Returns a List of arrays of Strings that contains the id, 
	 * names and surnames of all users that are not enrolled currently to
	 * any course.
	 * Mainly used for the view layer.
	 */
	
	public List<String[]> getStringAvailableUtenti() {

		List<String[]> availableUtentiList = new ArrayList<String[]>();
		List<Utente> utentiList = utenteDaoImpl.getListaUtenti();
		List<Integer> idUtentiPrenotati = new ArrayList<Integer>();
		
		for (Prenotazione prenotazione : prenotazioneDaoImpl.getListaPrenotazioni()) {
			idUtentiPrenotati.add(prenotazione.getIdUtente());
		}
		
		for (Utente utente : utentiList) {
			
			if (!idUtentiPrenotati.contains(utente.getId())) {
				String idUtente = String.valueOf(utente.getId());
				String nomeUtente = utente.getNome() + " " + utente.getCognome();
				String[] utenteStr = { idUtente, nomeUtente };
				availableUtentiList.add(utenteStr);
			}
		}

		return availableUtentiList;
	}
}
