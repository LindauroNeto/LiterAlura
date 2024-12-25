package br.com.nald.LiterAlura.service;

import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nald.LiterAlura.dto.DadosAutor;
import br.com.nald.LiterAlura.dto.DadosLivro;
import br.com.nald.LiterAlura.dto.RequesicaoDto;
import br.com.nald.LiterAlura.exceptions.MaisDeUmAutorException;
import br.com.nald.LiterAlura.model.Autor;
import br.com.nald.LiterAlura.model.Livro;
import br.com.nald.LiterAlura.repository.LivroRepository;

@Service
public class LivroService {
	private ConversaoDados conversor = new ConversaoDados();
	private ConsumoAPI consumo = new ConsumoAPI();
	
	@Autowired
	private LivroRepository repositorio;
	
	
	
	public void listandoAutoresPelaData(Integer dataAno) {
		Optional<List<Autor>> autoresVivos = repositorio.autoresAindaVivos(dataAno);
		
		if (autoresVivos.isPresent()) {
			System.out.println("Segue lista de autores vivos em " + dataAno);
			autoresVivos.get().forEach(System.out::println);
		} else {
			System.out.println("Não há autores vivos no ano de: " + dataAno);
		}
		
	}
	
	public void listandoAutoresRegistrados() {
		List<Autor> autores = repositorio.findAll().stream()
				.map(l -> l.getAutor())
				.collect(Collectors.toList());
		autores.forEach(System.out::println);
		
	}
	
	public void listandoLivrosRegistrados() {
		List<Livro> livros = repositorio.findAll();
		livros.forEach(System.out::println);
	}
	
	public void buscandoERegistrandoDeLivro(String url, String nomeLivro) {
		String json = criacaoConexao(url, nomeLivro);
		
		DadosLivro dadosLivro = obtencaoDadosLivro(json);
		Livro livro = new Livro(dadosLivro);
		Autor autor = obtencaoDadosAutor(dadosLivro);
		
		Optional<Autor> autorExiste = repositorio.autoresJaExistentes(autor.getNome());
		if (autorExiste.isEmpty()) {
			livro.setAutor(autor);
			autor.getLivros().add(livro);
		} else {
			throw new MaisDeUmAutorException("Nessa aplicação (até o momento), só se pode 1 autor por livro, escolha um livro escrito por um outro autor.");
		}
		
		repositorio.save(livro);
		
		System.out.println(livro.toString());
	}
	
	public DadosLivro obtencaoDadosLivro(String json) {
		RequesicaoDto requesicao = conversor.obterDados(json, RequesicaoDto.class);
		return requesicao.infoLivro().get(0);
	}
	
	public Autor obtencaoDadosAutor(DadosLivro dadosLivro) {
		DadosAutor dadosAutor = dadosLivro.autor().get(0);
		return new Autor(dadosAutor);
	}
	
	@SuppressWarnings("deprecation")
	private String criacaoConexao(String url, String nomeLivro) {
		String nome = URLEncoder.encode(nomeLivro); 
		return consumo.obterDados(url + "?search=" + nome);
	}
}
