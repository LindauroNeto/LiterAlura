package br.com.nald.LiterAlura.model;

import br.com.nald.LiterAlura.dto.DadosAutor;

public class Autor {
	private String nome;
	private Integer dataNascimento;
	private Integer dataFalecimento;
	
	public Autor(DadosAutor dados) {
		this.nome = dados.nome();
		this.dataNascimento = dados.anoNascimento();
		this.dataFalecimento = dados.anoFalecimento();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Integer dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Integer getDataFalecimento() {
		return dataFalecimento;
	}
	public void setDataFalecimento(Integer dataFalecimento) {
		this.dataFalecimento = dataFalecimento;
	}
	
	
	
	
}
