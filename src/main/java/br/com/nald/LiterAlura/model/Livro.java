package br.com.nald.LiterAlura.model;

import br.com.nald.LiterAlura.dto.DadosLivro;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "livros")
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String titulo;
	
	@Enumerated(EnumType.STRING)
	private Idiomas idioma;
	
	private Integer numeroDownloads;

	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	private Autor autor;
	
	public Livro() { }

	public Livro(DadosLivro dados) {
		this.titulo = dados.titulo();
		this.idioma = Idiomas.pegarIdioma(dados.lingua().get(0));
		this.numeroDownloads = dados.numeroDeDownloads();
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Idiomas getIdioma() {
		return idioma;
	}

	public void setIdioma(Idiomas idioma) {
		this.idioma = idioma;
	}

	public Integer getNumeroDownloads() {
		return numeroDownloads;
	}

	public void setNumeroDownloads(Integer numeroDownloads) {
		this.numeroDownloads = numeroDownloads;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	@Override
	public String toString() {
		return "\n------------- Livro -------------"
				+ "\nTítulo: " + getTitulo()
				+ "\nAutor(a): " + getAutor().getNome()
				+ "\nIdioma: " + getIdioma()
				+ "\nNúmero de Downloads: " + getNumeroDownloads()
				+ "\n--------------------------------";
	}

}
