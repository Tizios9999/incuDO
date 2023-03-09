package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Utente {
	
	private Integer id;
	private String nome;
	private String cognome;
	private LocalDate dataNascita;
	private String indirizzo;
	private String documentoId;
	
	public static class UtenteBuilder {
		private Integer id;
		private String nome;
		private String cognome;
		private LocalDate dataNascita;
		private String indirizzo;
		private String documentoId;
		
		private static final Map<Integer, String> MAP_CAMPI = new HashMap<>();
	    static {
	        MAP_CAMPI.put(0, "id");
	        MAP_CAMPI.put(1, "nome");
	        MAP_CAMPI.put(2, "cognome");
	        MAP_CAMPI.put(3, "dataNascita");
	        MAP_CAMPI.put(4, "indirizzo");
	        MAP_CAMPI.put(5, "documentoId");
	    }
		
	    public UtenteBuilder(String[] data) {
	        for (int i = 0; i < data.length; i++) {
	            String field = MAP_CAMPI.get(i);
	            if (field != null) {
	                switch (field) {
	                    case "id":
	                        this.id = Integer.parseInt(data[i]);
	                        break;
	                    case "nome":
	                        this.nome = data[i];
	                        break;
	                    case "cognome":
	                        this.cognome = data[i];
	                        break;
	                    case "dataNascita":
	                        this.dataNascita = ModelUtilities.StringToDate(data[i]);
	                        break;
	                    case "indirizzo":
	                        this.indirizzo = data[i];
	                        break;
	                    case "documentoId":
	                        this.documentoId = data[i];
	                        break;
	                    default:
	                        break;
	                }
	            }
	        }
	    }
		
		public Utente build() {
			return new Utente(this);
		}
	}
	
	private Utente(UtenteBuilder builder) {
		this.id = builder.id;
		this.nome = builder.nome;
		this.cognome = builder.cognome;
		this.dataNascita = builder.dataNascita;
		this.indirizzo = builder.indirizzo;
		this.documentoId = builder.documentoId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) { // int type
		this.id = id;
	}
	public void setId(String id) { // String type
		
		try {
			this.id = Integer.parseInt(id);
		}
		
		catch (NumberFormatException e){
			e.printStackTrace();
		}
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public LocalDate getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getDocumentoId() {
		return documentoId;
	}
	public void setDocumentoId(String documentoId) {
		this.documentoId = documentoId;
	}
	@Override
	public String toString() {
		return "Utente [id=" + id + ", Nome=" + nome + ", Cognome=" + cognome + ", dataNascita=" + dataNascita
				+ ", indirizzo=" + indirizzo + ", documentoId=" + documentoId + "]";
	}

}
