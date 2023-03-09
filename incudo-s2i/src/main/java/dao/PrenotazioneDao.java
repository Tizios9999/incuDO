package dao;

import java.util.List;

import model.Prenotazione;

public interface PrenotazioneDao {
	
	public void aggiungiPrenotazione(Prenotazione prenotazione);
	public void caricaPrenotazioni(String CSVPath);
	public Integer disponibilitàIdPrenotazione(Integer idCorso, Integer idUtente);
	public Boolean cancellaPrenotazione(Integer idCorso, Integer idUtente);
	public List<Prenotazione> getListaPrenotazioni();
	public void setListaPrenotazioni(List<Prenotazione> listaPrenotazioni);

}
