package dao;

import java.util.List;

import model.Corso;

public interface CorsoDao {
	
	public void loadCorsoTable(String CSVpath);
	public Corso searchCorsoById(Integer id);
	public void setDisponibilitàCorso(Integer id, Boolean disponibile);
	public void exportCsv();
	public List<Corso> getListaCorsi();
	public void setListaCorsi(List<Corso> listaCorsi);
	
}
