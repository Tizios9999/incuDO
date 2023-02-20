package model;

import java.time.LocalDate;

public class Prenotazioni {
	
	private int id;
	private int idAttività;
	private int idUtente;
	private LocalDate dataInizio;
	private LocalDate dataFine;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdAttività() {
		return idAttività;
	}
	public void setIdAttività(int idAttività) {
		this.idAttività = idAttività;
	}
	public int getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
	public LocalDate getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}
	public LocalDate getDataFine() {
		return dataFine;
	}
	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}
	@Override
	public String toString() {
		return "Prenotazioni [id=" + id + ", idAttività=" + idAttività + ", idUtente=" + idUtente + ", dataInizio="
				+ dataInizio + ", dataFine=" + dataFine + "]";
	}
	
	
}
