package br.com.nald.LiterAlura.service;

import br.com.nald.LiterAlura.dto.RequesicaoDto;
import br.com.nald.LiterAlura.model.Livro;

public class LivroService {
	private static ConversaoDados conversor = new ConversaoDados();
	
	public static Livro obtencaoDadosLivro(String json) {
		RequesicaoDto request = conversor.obterDados(json, RequesicaoDto.class);
		return new Livro(request);
	}
}
