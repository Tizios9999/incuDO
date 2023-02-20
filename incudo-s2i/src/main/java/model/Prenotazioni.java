package model;

import java.time.LocalDate;

public class Prenotazioni {
	
	private Integer id;
	private Integer idAttività;
	private Integer idUtente;
	private LocalDate dataInizio;
	private LocalDate dataFine;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdAttività() {
		return idAttività;
	}

	public void setIdAttività(Integer idAttività) {
		this.idAttività = idAttività;
	}
	
	public Integer getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Integer idUtente) {
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
