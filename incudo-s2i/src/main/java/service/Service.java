package service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

	private static Service service;

	private Service() {
	}

	public static Service getInstance() {
		if (service == null) {
			service = new Service();
		}
		return service;
	}

	public void caricaDati() {
		this.corsoDaoImpl = new CorsoDaoImpl();
		this.utenteDaoImpl = new UtenteDaoImpl();
		this.prenotazioneDaoImpl = new PrenotazioneDaoImpl();

		corsoDaoImpl.caricaCorsi("corsi.csv");
		utenteDaoImpl.caricaUtenti("utenti.csv");
		prenotazioneDaoImpl.caricaPrenotazioni("prenotazioni.csv");

	}

	public void creaPrenotazione(Integer idCorso, Integer idUtente) {

		// Controlla se prenotazione già esistente

		Corso corso = this.corsoDaoImpl.cercaCorsoPerId(idCorso);

		Utente utente = this.utenteDaoImpl.cercaUtentePerId(idUtente);

		System.out.println("Corso:" + corso);
		System.out.println("Utente:" + utente);

		if (utente == null || corso == null) {
			System.out.println("Corso o utente non trovato");
		} else {

			// Cerco se c'è una prenotazione disponibile

			Integer nuovoId = this.prenotazioneDaoImpl.disponibilitàIdPrenotazione(idCorso, idUtente);

			if (nuovoId > 0) {

				LocalDate dataInizio = corso.getDataCorso();
				LocalDate dataFine = dataInizio.plusDays(corso.getDurata() / 24);

				// creare prenotazione

				System.out.println("Nuova prenotazione corso: " + corso.getNome() + " iscritto " + utente.getNome()
						+ " da " + dataInizio + " a " + dataFine);

				Prenotazione nuovaPrenotazione = new Prenotazione(nuovoId, corso.getId(), utente.getId(), dataInizio,
						dataFine);

				this.prenotazioneDaoImpl.aggiungiPrenotazione(nuovaPrenotazione);
				System.out.println(nuovaPrenotazione);
				
				corsoDaoImpl.setDisponibilitàCorso(corso.getId(), false);

			} else {
				System.out.println("Prenotazione non disponibile");
			}

		}

	}

	public void disdiciPrenotazione(Integer idCorso, Integer idUtente) {

		Corso corso = this.corsoDaoImpl.cercaCorsoPerId(idCorso);
		Utente utente = this.utenteDaoImpl.cercaUtentePerId(idUtente);

		if (utente == null || corso == null) {
			System.out.println("Corso o utente non trovato");
		} else {

			if (this.prenotazioneDaoImpl.cancellaPrenotazione(idCorso, idUtente)) {
				System.out.println("Prenotazione cancellata");
			} else {
				System.out.println("Prenotazione non trovata");
			}

		}

	}

	public void aggiungiUtente(String nome, String cognome, String dataNascita, String indirizzoUtente,
			String documentoIdUtente) {

		String id = String.valueOf(this.utenteDaoImpl.trovaUltimoId() + 1);

		String[] arrayUtente = { id, nome, cognome, dataNascita, indirizzoUtente, documentoIdUtente };

		Utente utente = new Utente.UtenteBuilder(arrayUtente).build();

		this.utenteDaoImpl.inserisciUtente(utente);

	}

	public void esportaCsvCorsi() {

		this.corsoDaoImpl.esportaCsv();

	}

	public List<Corso> getListaCorsi() {

		return this.corsoDaoImpl.getListaCorsi();
	}

	public static String ConvertDateIntoString(LocalDate date) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		String formattedDate = date.format(formatter);

		return formattedDate;
	}

	public List<String[]> getStringPrenotazioni() {

		List<Prenotazione> listaPrenotazioni = prenotazioneDaoImpl.getListaPrenotazioni();

		List<String[]> listaPrenotazioniStr = new ArrayList<String[]>();

		for (Prenotazione prenotazione : listaPrenotazioni) {
			String id = prenotazione.getId().toString();
			String idCorso = prenotazione.getIdAttività().toString();
			String idUtente = prenotazione.getIdUtente().toString();
			String dataInizio = ConvertDateIntoString(prenotazione.getDataInizio());
			String dataFine = ConvertDateIntoString(prenotazione.getDataFine());

			String[] prenotazioneStr = { id, idCorso, idUtente, dataInizio, dataFine };
			listaPrenotazioniStr.add(prenotazioneStr);
		}

		return listaPrenotazioniStr;
	}

	public List<String[]> getStringPrenotazioniUtentiCorsi() {

		List<Prenotazione> listaPrenotazioni = prenotazioneDaoImpl.getListaPrenotazioni();
		
		List<String[]> listaPrenotazioniUtentiCorsiStr = new ArrayList<String[]>();

		for (Prenotazione prenotazione : listaPrenotazioni) {
			
			Utente utente = this.utenteDaoImpl.cercaUtentePerId(prenotazione.getIdUtente());
			Corso corso = this.corsoDaoImpl.cercaCorsoPerId(prenotazione.getIdAttività());
			
			String id = prenotazione.getId().toString();
			String idCorso = prenotazione.getIdAttività().toString();
			String nomeCorso = corso.getNome();
			String idUtente = prenotazione.getIdUtente().toString();
			String nominativoUtente = utente.getNome() + " " + utente.getCognome();
			String dataInizio = ConvertDateIntoString(prenotazione.getDataInizio());
			String dataFine = ConvertDateIntoString(prenotazione.getDataFine());

			String[] prenotazioneStr = { id, idCorso, nomeCorso, idUtente, nominativoUtente, dataInizio, dataFine };
			listaPrenotazioniUtentiCorsiStr.add(prenotazioneStr);
		}

		return listaPrenotazioniUtentiCorsiStr;
	}
}
