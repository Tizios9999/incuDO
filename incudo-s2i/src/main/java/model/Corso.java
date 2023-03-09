package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Corso {

	private Integer id;
	private String nome;
	private String descrizione;
	private LocalDate dataCorso;
	private Integer durata;
	private String luogo;
	private Boolean disponibile;
	
	public static class CorsoBuilder {
		private Integer id;
		private String nome;
		private String descrizione;
		private LocalDate dataCorso;
		private Integer durata;
		private String luogo;
		private Boolean disponibile;
		
		private static final Map<Integer, String> MAP_CAMPI = new HashMap<>();
	    static {
	        MAP_CAMPI.put(0, "id");
	        MAP_CAMPI.put(1, "nome");
	        MAP_CAMPI.put(2, "descrizione");
	        MAP_CAMPI.put(3, "dataCorso");
	        MAP_CAMPI.put(4, "durata");
	        MAP_CAMPI.put(5, "luogo");
	        MAP_CAMPI.put(6, "disponibile");
	    }
		
		public CorsoBuilder(String[] data) {
			
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
		
		public Corso build() {
			return new Corso(this);
		}
	}
	
	private Corso(CorsoBuilder builder) {
		this.id = builder.id;
		this.nome = builder.nome;
		this.descrizione = builder.descrizione;
		this.dataCorso = builder.dataCorso;
		this.durata = builder.durata;
		this.luogo = builder.luogo;
		this.disponibile = builder.disponibile;
	}
	
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
