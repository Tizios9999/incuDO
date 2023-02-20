package model;

import java.time.LocalDate;

public class Utenti {
	
	private int id;
	private int Nome;
	private int Cognome;
	private LocalDate dataNascita;
	private String indirizzo;
	private String documentoId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNome() {
		return Nome;
	}
	public void setNome(int nome) {
		Nome = nome;
	}
	public int getCognome() {
		return Cognome;
	}
	public void setCognome(int cognome) {
		Cognome = cognome;
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
	
}
