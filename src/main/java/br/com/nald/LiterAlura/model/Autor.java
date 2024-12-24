package br.com.nald.LiterAlura.model;

import java.time.LocalDate;

public class Autor {
	private String nome;
	private LocalDate dataNascimento;
	private LocalDate dataFalecimento;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public LocalDate getDataFalecimento() {
		return dataFalecimento;
	}
	public void setDataFalecimento(LocalDate dataFalecimento) {
		this.dataFalecimento = dataFalecimento;
	}
	
	
	
	
}
