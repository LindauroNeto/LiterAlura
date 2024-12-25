package br.com.nald.LiterAlura.service;

import org.springframework.stereotype.Service;

import br.com.nald.LiterAlura.dto.DadosAutor;
import br.com.nald.LiterAlura.dto.DadosLivro;
import br.com.nald.LiterAlura.dto.RequesicaoDto;
import br.com.nald.LiterAlura.model.Autor;

@Service
public class LivroService {
	private ConversaoDados conversor = new ConversaoDados();
	
	public DadosLivro obtencaoDadosLivro(String json) {
		RequesicaoDto requesicao = conversor.obterDados(json, RequesicaoDto.class);
		return requesicao.infoLivro().get(0);
	}
	
	public Autor obtencaoDadosAutor(DadosLivro dadosLivro) {
		DadosAutor dadosAutor = dadosLivro.autor().get(0);
		return new Autor(dadosAutor);
	}
}
