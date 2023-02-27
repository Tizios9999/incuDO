package dao;

public interface PrenotazioneDao {
	
	public void caricaPrenotazioni(String CSVPath);
	public void togglePrenotazione(Integer idCorso, Integer idUtente);
	
}
