package br.com.nald.LiterAlura.model;

import br.com.nald.LiterAlura.dto.DadosLivro;
import br.com.nald.LiterAlura.dto.RequesicaoDto;

public class Livro {
	private String titulo;
	private Autor autor;
	private Idiomas idioma;
	private Integer numeroDownloads;
	
	public Livro(RequesicaoDto dadosRequesicao) {
		DadosLivro dados = dadosRequesicao.infoLivro().get(0);
		this.titulo = dados.titulo();
		this.autor = new Autor(dados.autor().get(0));
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
