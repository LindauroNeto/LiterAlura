package br.com.nald.LiterAlura.service;

import br.com.nald.LiterAlura.dto.DadosAutor;
import br.com.nald.LiterAlura.dto.DadosLivro;
import br.com.nald.LiterAlura.dto.RequesicaoDto;
import br.com.nald.LiterAlura.model.Autor;

public class LivroService {
	private static ConversaoDados conversor = new ConversaoDados();
	
	public static DadosLivro obtencaoDadosLivro(String json) {
		RequesicaoDto requesicao = conversor.obterDados(json, RequesicaoDto.class);
		return requesicao.infoLivro().get(0);
	}
	
	public static Autor obtencaoDadosAutor(DadosLivro dadosLivro) {
		DadosAutor dadosAutor = dadosLivro.autor().get(0);
		return new Autor(dadosAutor);
	}
}
