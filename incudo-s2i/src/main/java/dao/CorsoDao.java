package dao;

import java.util.List;

import model.Corso;

public interface CorsoDao {
	
	public void caricaCorsi(String CSVpath);
	public Corso cercaCorsoPerId(Integer id);
	public void setDisponibilitàCorso(Integer id, Boolean disponibile);
	public void esportaCsv();
	public List<Corso> getListaCorsi();
	public void setListaCorsi(List<Corso> listaCorsi);
	
}
