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
import br.com.nald.LiterAlura.exceptions.LivroNaoEcontradoException;
import br.com.nald.LiterAlura.exceptions.MaisDeUmAutorException;
import br.com.nald.LiterAlura.model.Autor;
import br.com.nald.LiterAlura.model.Idiomas;
import br.com.nald.LiterAlura.model.Livro;
import br.com.nald.LiterAlura.repository.LivroRepository;

@Service
public class LivroService {
	private ConversaoDados conversor = new ConversaoDados();
	private ConsumoAPI consumo = new ConsumoAPI();
	
	@Autowired
	private LivroRepository repositorio;

	// Métodos vv
	
	public void buscandoLivrosPorIdioma(String idioma) {
		Idiomas idiomaBuscado = Idiomas.pegarIdioma(idioma);
		
		List<Livro> livroPorIdioma = repositorio.findByIdioma(idiomaBuscado);
		
		if (livroPorIdioma.isEmpty()) {
			System.out.println("Livros no idioma \"" + idioma + "\" não foram encontrados no catálogo");
		} else {
			contagemDeItensDeBusca(livroPorIdioma);
			livroPorIdioma.forEach(System.out::println);
		}
		
	}
	
	public void listandoAutoresPelaData(Integer dataAno) {
		List<Autor> autoresVivos = repositorio.autoresAindaVivos(dataAno);
		
		if (autoresVivos.isEmpty()) {
			System.out.println("Não há autores vivos no ano de: " + dataAno);
		} else {
			System.out.println("Segue lista de autores vivos em " + dataAno + ":");
			contagemDeItensDeBusca(autoresVivos);
			autoresVivos.forEach(System.out::println);
		}
		
	}
	
	public void listandoAutoresRegistrados() {
		List<Autor> autores = repositorio.findAll().stream()
				.map(l -> l.getAutor())
				.collect(Collectors.toList());
		
		contagemDeItensDeBusca(autores);
		autores.forEach(System.out::println);
		
	}
	
	public void listandoLivrosRegistrados() {
		List<Livro> livros = repositorio.findAll();
		
		contagemDeItensDeBusca(livros);
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
	
	private DadosLivro obtencaoDadosLivro(String json) {
		RequesicaoDto requesicao = conversor.obterDados(json, RequesicaoDto.class);
		if (requesicao.infoLivro().isEmpty()) {
			throw new LivroNaoEcontradoException("O livro não foi encontrado dentro do catálogo");
			
		} else {
			return requesicao.infoLivro().get(0);
		}
	}
	
	private Autor obtencaoDadosAutor(DadosLivro dadosLivro) {
		DadosAutor dadosAutor = dadosLivro.autor().get(0);
		return new Autor(dadosAutor);
	}
	
	private <T> void contagemDeItensDeBusca(List<T> lista) {
		Long total = (long) lista.size();
		String totalTexto = (total > 1 ? " encontrados." : " econtrado.");
		
		System.out.println(total + totalTexto);
	}
	
	@SuppressWarnings("deprecation")
	private String criacaoConexao(String url, String nomeLivro) {
		String nome = URLEncoder.encode(nomeLivro); 
		return consumo.obterDados(url + "?search=" + nome);
	}
}
