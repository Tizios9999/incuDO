package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * The Utente model represents a course entity in the system.
 * It contains information about the course such as its name, surname, 
 * birth date, address and document ID code.
 */

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
		
		/**
		 * FIELDS_MAP is an Hashmap that links the String array positions that
		 * will be used to build the Utente object with the object corresponding
		 * properties. This is for keeping the code more readable.
		 */
		
		private static final Map<Integer, String> FIELDS_MAP = new HashMap<>();
	    static {
	        FIELDS_MAP.put(0, "id");
	        FIELDS_MAP.put(1, "nome");
	        FIELDS_MAP.put(2, "cognome");
	        FIELDS_MAP.put(3, "dataNascita");
	        FIELDS_MAP.put(4, "indirizzo");
	        FIELDS_MAP.put(5, "documentoId");
	    }
		
	    /**
	     * Creates a new instance of the {@link UtenteBuilder} class with the given input data.
	     * 
	     * @param data the input data used to create the user
	     */
	    
	    public UtenteBuilder(String[] data) {
	        for (int i = 0; i < data.length; i++) {
	            String field = FIELDS_MAP.get(i);
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
		
	    /**
	     * Creates a new instance of the {@link Utente} class using the data stored in this builder.
	     * 
	     * @return a new instance of the {@link Utente} class
	     */
	    
		public Utente build() {
			return new Utente(this);
		}
	}
	
	/**
	 * Creates a new instance of the {@link Utente} class with the given parameters.
	*/
	
	private Utente(UtenteBuilder builder) {
		this.id = builder.id;
		this.nome = builder.nome;
		this.cognome = builder.cognome;
		this.dataNascita = builder.dataNascita;
		this.indirizzo = builder.indirizzo;
		this.documentoId = builder.documentoId;
	}
	
	// Getters and Setters
	
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
