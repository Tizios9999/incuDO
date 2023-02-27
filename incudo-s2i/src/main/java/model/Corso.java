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
		
		public CorsoBuilder(String[] data) {
			
			Map<String, Boolean> itaToBoolMap = new HashMap<>();
			itaToBoolMap.put("SI", true);
			itaToBoolMap.put("NO", false);
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			this.id = Integer.parseInt(data[0]);
			this.nome = data[1];
			this.descrizione = data[2];
			this.dataCorso = LocalDate.parse(data[3], formatter);
			this.durata = Integer.parseInt(data[4]);
			this.luogo = data[5];
			
			this.disponibile = itaToBoolMap.get(data[6]);
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
