package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
		
		public UtenteBuilder(String[] data) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			this.id = Integer.parseInt(data[0]);
			this.nome = data[1];
			this.cognome = data[2];
			this.dataNascita = LocalDate.parse(data[3], formatter);
			this.indirizzo = data[4];
			this.documentoId = data[5];
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
