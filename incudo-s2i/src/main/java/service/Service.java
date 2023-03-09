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

public class Service {

	private CorsoDaoImpl corsoDaoImpl;
	private UtenteDaoImpl utenteDaoImpl;
	private PrenotazioneDaoImpl prenotazioneDaoImpl;
	private static Map<Boolean, String> boolToItaMap = new HashMap<>();
	
	static {
		boolToItaMap.put(true, "SI");
		boolToItaMap.put(false, "NO");
	}
	
	private static Service service;

	private Service() {
	}

	public static Service getInstance() {
		if (service == null) {
			service = new Service();
		}
		return service;
	}

	public void loadTablesData() {
		this.corsoDaoImpl = new CorsoDaoImpl();
		this.utenteDaoImpl = new UtenteDaoImpl();
		this.prenotazioneDaoImpl = new PrenotazioneDaoImpl();

		corsoDaoImpl.loadCorsoTable("corsi.csv");
		utenteDaoImpl.loadUtenteTable("utenti.csv");
		prenotazioneDaoImpl.loadPrenotazioneTable("prenotazioni.csv");

	}
	
	public static String convertDateIntoString(LocalDate date) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		String formattedDate = date.format(formatter);

		return formattedDate;
	}

	public void createPrenotazione(Integer idCorso, Integer idUtente) {

		// Controlla se prenotazione già esistente

		Corso corso = this.corsoDaoImpl.searchCorsoById(idCorso);

		Utente utente = this.utenteDaoImpl.searchUtenteById(idUtente);

		if (utente == null || corso == null) {
			System.out.println("Corso o utente non trovato");
		} else {

			// Cerco se c'è una prenotazione disponibile

			Integer nuovoId = this.prenotazioneDaoImpl.firstPrenotazioneIdAvailable(idCorso, idUtente);

			if (nuovoId > 0) {

				LocalDate dataInizio = corso.getDataCorso();
				LocalDate dataFine = dataInizio.plusDays(corso.getDurata() / 24);

				// creare prenotazione

				System.out.println("Nuova prenotazione corso: " + corso.getNome() + " iscritto " + utente.getNome()
						+ " da " + dataInizio + " a " + dataFine);

				Prenotazione nuovaPrenotazione = new Prenotazione(nuovoId, corso.getId(), utente.getId(), dataInizio,
						dataFine);

				this.prenotazioneDaoImpl.addPrenotazione(nuovaPrenotazione);
				
				corsoDaoImpl.setDisponibilitàCorso(corso.getId(), false);

			} else {
				System.out.println("Prenotazione non disponibile");
			}

		}

	}

	public void cancelPrenotazione(Integer idCorso, Integer idUtente) {

		Corso corso = this.corsoDaoImpl.searchCorsoById(idCorso);
		Utente utente = this.utenteDaoImpl.searchUtenteById(idUtente);

		if (utente == null || corso == null) {
			System.out.println("Corso o utente non trovato");
		} else {

			if (this.prenotazioneDaoImpl.removePrenotazione(idCorso, idUtente)) {
				System.out.println("Prenotazione cancellata");
			} else {
				System.out.println("Prenotazione non trovata");
			}

		}

	}

	public void createNewUtente(String nome, String cognome, String dataNascita, String indirizzoUtente,
			String documentoIdUtente) {

		String id = String.valueOf(this.utenteDaoImpl.findLastId() + 1);

		String[] arrayUtente = { id, nome, cognome, dataNascita, indirizzoUtente, documentoIdUtente };

		Utente utente = new Utente.UtenteBuilder(arrayUtente).build();

		this.utenteDaoImpl.addUtente(utente);

	}

	public void exportCorsoCsvTable() {

		this.corsoDaoImpl.exportCsv();

	}

	public List<Corso> getListaCorsi() {

		return this.corsoDaoImpl.getListaCorsi();
	}


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
	
	public List<String[]> getListStringAvailableCorsi() {

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
