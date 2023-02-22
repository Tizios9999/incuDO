package model;

import java.time.LocalDate;

public class Corso {

	private int id;
	private String nome;
	private String descrizione;
	private LocalDate data;
	private int durata;
	private String luogo;
	private boolean disponibile;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public int getDurata() {
		return durata;
	}
	public void setDurata(int durata) {
		this.durata = durata;
	}
	public String getLuogo() {
		return luogo;
	}
	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}
	public boolean isDisponibile() {
		return disponibile;
	}
	public void setDisponibile(boolean disponibile) {
		this.disponibile = disponibile;
	}
	@Override
	public String toString() {
		return "Corso [id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", data=" + data + ", durata="
				+ durata + ", luogo=" + luogo + ", disponibile=" + disponibile + "]";
	}
	
	
}
