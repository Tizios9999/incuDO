package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Prenotazione {
	
	private Integer id;
	private Integer idAttività;
	private Integer idUtente;
	private LocalDate dataInizio;
	private LocalDate dataFine;
	
	public static class PrenotazioneBuilder {
		private Integer id;
		private Integer idAttività;
		private Integer idUtente;
		private LocalDate dataInizio;
		private LocalDate dataFine;
		
		public PrenotazioneBuilder(String[] data) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			this.id = Integer.parseInt(data[0]);
			this.idAttività = Integer.parseInt(data[1]);
			this.idUtente = Integer.parseInt(data[2]);
			this.dataInizio = LocalDate.parse(data[3], formatter);
			this.dataFine = LocalDate.parse(data[4], formatter);
			
		}
		
		public Prenotazione build() {
			return new Prenotazione(this);
		}
	}
	
	private Prenotazione(PrenotazioneBuilder builder) {
		this.id = builder.id;
		this.idAttività = builder.idAttività;
		this.idUtente = builder.idUtente;
		this.dataInizio = builder.dataInizio;
		this.dataFine = builder.dataFine;
	}
	
	
	
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
		return "Prenotazione [id=" + id + ", idAttività=" + idAttività + ", idUtente=" + idUtente + ", dataInizio="
				+ dataInizio + ", dataFine=" + dataFine + "]";
	}
	
	
}
