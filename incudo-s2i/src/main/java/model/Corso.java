package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * The Corso model represents a course entity in the system.
 * It contains information about the course such as its name, description, location,
 * date, duration and availability.
 */

public class Corso {

	private Integer id;
	private String nome;
	private String descrizione;
	private LocalDate dataCorso;
	private Integer durata;
	private String luogo;
	private Boolean disponibile;
	
	/**
	 * A builder class used to create instances of Corso.
	 */
	
	public static class CorsoBuilder {
		private Integer id;
		private String nome;
		private String descrizione;
		private LocalDate dataCorso;
		private Integer durata;
		private String luogo;
		private Boolean disponibile;
		
		/**
		 * FIELDS_MAP is an Hashmap that links the String array positions that
		 * will be used to build the Corso object with the object corresponding
		 * properties. This is for keeping the code more readable.
		 */
		
		private static final Map<Integer, String> FIELDS_MAP = new HashMap<>();
	    static {
	        FIELDS_MAP.put(0, "id");
	        FIELDS_MAP.put(1, "nome");
	        FIELDS_MAP.put(2, "descrizione");
	        FIELDS_MAP.put(3, "dataCorso");
	        FIELDS_MAP.put(4, "durata");
	        FIELDS_MAP.put(5, "luogo");
	        FIELDS_MAP.put(6, "disponibile");
	    }
		
	    /**
		 * Constructs a new instance of CorsoBuilder using the given data.
		 *
		 * @param data the data to use for building the Corso
		 */
	    
		public CorsoBuilder(String[] data) {
			
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
	                    case "descrizione":
	                        this.descrizione = data[i];
	                        break;
	                    case "dataCorso":
	                        this.dataCorso = ModelUtilities.StringToDate(data[i]);
	                        break;
	                    case "durata":
	                        this.durata = Integer.parseInt(data[i]);
	                        break;
	                    case "luogo":
	                        this.luogo = data[i];
	                        break;
	                    case "disponibile":
	                        this.disponibile = ModelUtilities.itaToBoolMap.get(data[i]);
	                        break;
	                    default:
	                        break;
	                }
	            }
	        }
		}
		
		/**
		 * Builds and returns a new instance of Corso using the data provided to the builder.
		 *	
		 * @return a new instance of Corso
		 */
		
		public Corso build() {
			return new Corso(this);
		}
	}
	
	/**
	 * Constructs a new instance of Corso using the provided builder.
	 * 
	 * @param builder the builder to use for constructing the Corso
	 */
	
	private Corso(CorsoBuilder builder) {
		this.id = builder.id;
		this.nome = builder.nome;
		this.descrizione = builder.descrizione;
		this.dataCorso = builder.dataCorso;
		this.durata = builder.durata;
		this.luogo = builder.luogo;
		this.disponibile = builder.disponibile;
	}
	
	// Getters and Setters
	
	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public LocalDate getDataCorso() {
		return dataCorso;
	}
	public void setDataCorso(LocalDate dataCorso) {
		this.dataCorso = dataCorso;
	}
	public int getDurata() {
		return durata;
	}
	public void setDurata(Integer durata) {
		this.durata = durata;
	}
	public String getLuogo() {
		return luogo;
	}
	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}
	public Boolean isDisponibile() {
		return disponibile;
	}
	public void setDisponibile(Boolean disponibile) {
		this.disponibile = disponibile;
	}
	@Override
	public String toString() {
		return "Corso [id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", data=" + dataCorso + ", durata="
				+ durata + ", luogo=" + luogo + ", disponibile=" + disponibile + "]";
	}
	
	
}
