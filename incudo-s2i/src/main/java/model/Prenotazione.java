package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

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
		
		private static final Map<Integer, String> FIELDS_MAP = new HashMap<>();
	    static {
	    	 FIELDS_MAP.put(0, "id");
	         FIELDS_MAP.put(1, "idAttività");
	         FIELDS_MAP.put(2, "idUtente");
	         FIELDS_MAP.put(3, "dataInizio");
	         FIELDS_MAP.put(4, "dataFine");
	    }
		
		public PrenotazioneBuilder(String[] data) {
			
			for (int i = 0; i < data.length; i++) {
	            String field = FIELDS_MAP.get(i);
	            if (field != null) {
	                switch (field) {
	                    case "id":
	                        this.id = Integer.parseInt(data[i]);
	                        break;
	                    case "idAttività":
	                        this.idAttività = Integer.parseInt(data[i]);
	                        break;
	                    case "idUtente":
	                        this.idUtente = Integer.parseInt(data[i]);
	                        break;
	                    case "dataInizio":
	                        this.dataInizio = ModelUtilities.StringToDate(data[i]);
	                        break;
	                    case "dataFine":
	                        this.dataFine = ModelUtilities.StringToDate(data[i]);
	                        break;
	                    default:
	                        break;
	                }
	            }
	        }
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
	
	public Prenotazione(Integer id, Integer idAttività, Integer idUtente, LocalDate dataInizio, LocalDate dataFine) {
		this.id = id;
		this.idAttività = idAttività;
		this.idUtente = idUtente;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
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
