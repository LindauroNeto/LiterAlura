package br.com.nald.LiterAlura.model;

import java.util.ArrayList;
import java.util.List;

import br.com.nald.LiterAlura.dto.DadosAutor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "autores")
public class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	private Integer dataNascimento;
	private Integer dataFalecimento;
	
	@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
	private List<Livro> livros = new ArrayList<>();
	
	public Autor() { }

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
	
	@Override
	public String toString() {
		return "\nAutor(a): " + getNome() +
				"\nAno de Nascimento: " + getDataNascimento() +
				"\nAno de Falecimento: " + getDataFalecimento();
	}
	
	
}
