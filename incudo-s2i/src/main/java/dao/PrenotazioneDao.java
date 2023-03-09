package dao;

import java.util.List;

import model.Prenotazione;

public interface PrenotazioneDao {
	
	public void addPrenotazione(Prenotazione prenotazione);
	public void loadPrenotazioneTable(String CSVPath);
	public Integer firstPrenotazioneIdAvailable(Integer idCorso, Integer idUtente);
	public Boolean removePrenotazione(Integer idCorso, Integer idUtente);
	public List<Prenotazione> getListaPrenotazioni();
	public void setListaPrenotazioni(List<Prenotazione> listaPrenotazioni);

}
