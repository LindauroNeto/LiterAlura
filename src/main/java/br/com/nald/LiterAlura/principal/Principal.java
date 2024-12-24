package br.com.nald.LiterAlura.principal;

import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.nald.LiterAlura.dto.DadosLivro;
import br.com.nald.LiterAlura.model.Autor;
import br.com.nald.LiterAlura.model.Livro;
import br.com.nald.LiterAlura.repository.LivroRepository;
import br.com.nald.LiterAlura.service.ConsumoAPI;
import br.com.nald.LiterAlura.service.LivroService;

public class Principal {

	private final String urlApi = "https://gutendex.com/books/";
	private Scanner scanner = new Scanner(System.in);
	private ConsumoAPI consumo = new ConsumoAPI();
	
	private LivroRepository repositorio;
	
	public Principal(LivroRepository repositorio) {
		this.repositorio = repositorio;
	}

	public void menu() {
		
		String menu = """
				\n
				Escolha o número da sua opção
				1 - Buscar livro pelo título
				2 - Listar livros registrados
				3 - Listar autores registrados
				4 - Listar autores vivos em um determinado ano
				
				0 - Sair				
				""";
		
		var opcao = -1;
		while (opcao != 0) {
			System.out.println(menu);
			opcao = scanner.nextInt();
			scanner.nextLine();
			
			switch (opcao) {
				case 1:
					buscaLivro();
					break;
				
				case 2:
					listagemLivros();
					break;
					
				case 3:
					listagemAutores();
					break;
					
				case 4:
					listagemAutoresPorData();
					break;
		
				case 0:
					System.out.println("Saindo do sistema...");
					break;
					
				default:
					System.out.println("Opção inválida");
					break;
			}
		}
	}

	private void listagemAutoresPorData() {
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

	private void listagemAutores() {
		List<Autor> autores = repositorio.findAll().stream()
				.map(l -> l.getAutor())
				.collect(Collectors.toList());
		autores.forEach(System.out::println);
		
	}

	private void listagemLivros() {
		System.out.println("Livros registrados: ");
		List<Livro> livros = repositorio.findAll();
		livros.forEach(System.out::println);
		
	}

	private void buscaLivro() {
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
			livro.setAutor(autorExiste.get());
			autorExiste.get().getLivros().add(livro);
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
