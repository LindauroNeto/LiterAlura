package br.com.nald.LiterAlura.service;

import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.nald.LiterAlura.dto.DadosLivro;
import br.com.nald.LiterAlura.exceptions.MaisDeUmAutorException;
import br.com.nald.LiterAlura.model.Autor;
import br.com.nald.LiterAlura.model.Livro;
import br.com.nald.LiterAlura.repository.LivroRepository;

@Service
public class PrincipalService {
	
	private final String urlApi = "https://gutendex.com/books/";
	private Scanner scanner = new Scanner(System.in);
	private ConsumoAPI consumo = new ConsumoAPI();
	
	private LivroRepository repositorio;
	
	public PrincipalService(LivroRepository repositorio) {
		this.repositorio = repositorio;
	}
	
	// Métodos
	
	public void listagemLivrosPorIdioma() {
		String menuIdiomas = """
				es - Espanhol
				en - Inglês
				fr - Francês
				pt - Português
				""";
		
		System.out.println(menuIdiomas);
	}
	
	public void listagemAutoresPorData() {
		System.out.println("Coloque a data limite: ");
		var dataAno = scanner.nextInt();
		scanner.nextLine();
		
		Optional<List<Autor>> autoresVivos = repositorio.autoresAindaVivos(dataAno);
		
		if (autoresVivos.isPresent()) {
			System.out.println("Segue lista de autores vivos em " + dataAno);
			autoresVivos.get().forEach(System.out::println);
		} else {
			System.out.println("Não há autores vivos no ano de: " + dataAno);
		}
		
	}
	
	public void listagemAutores() {
		List<Autor> autores = repositorio.findAll().stream()
				.map(l -> l.getAutor())
				.collect(Collectors.toList());
		autores.forEach(System.out::println);
		
	}
	
	public void listagemLivros() {
		System.out.println("Livros registrados: ");
		List<Livro> livros = repositorio.findAll();
		livros.forEach(System.out::println);
		
	}
	
	public void buscaLivro() {
		System.out.println("Digite o nome do livro: ");
		var nomeLivro = scanner.nextLine();
		String json = criacaoConexao(nomeLivro);
		
		DadosLivro dadosLivro = LivroService.obtencaoDadosLivro(json);
		Livro livro = new Livro(dadosLivro);
		Autor autor = LivroService.obtencaoDadosAutor(dadosLivro);
		
		Optional<Autor> autorExiste = repositorio.autoresJaExistentes(autor.getNome());
		if (autorExiste.isEmpty()) {
			livro.setAutor(autor);
			autor.getLivros().add(livro);
		} else {
			throw new MaisDeUmAutorException("Nessa aplicação (até o momento), só se pode 1 autor por livro, escolha um livro escrito por um outro autor.");
		}
		
		repositorio.save(livro);
		
		System.out.println(livro.toString());
//		System.out.println(autor.toString());
	}
	
	@SuppressWarnings("deprecation")
	private String criacaoConexao(String nomeLivro) {
		String nome = URLEncoder.encode(nomeLivro); 
		return consumo.obterDados(urlApi + "?search=" + nome);
	}

}
