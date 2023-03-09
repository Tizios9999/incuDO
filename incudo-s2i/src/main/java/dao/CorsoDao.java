package dao;

import java.util.List;

import model.Corso;

public interface CorsoDao {
	
	public void loadCorsoTable(String CSVpath);
	public Corso searchCorsoById(Integer id);
	public void setDisponibileCorso(Integer id, Boolean available);
	public void exportCsv();
	public List<Corso> getListaCorsi();
	public void setListaCorsi(List<Corso> corsiList);
	
}
