package dao;

import model.Corso;

public interface CorsoDao {
	
	public void caricaCorsi(String CSVpath);
	public Corso cercaCorsoPerId(Integer id);
}
