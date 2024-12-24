package br.com.nald.LiterAlura.principal;

import java.net.URLEncoder;
import java.util.List;
import java.util.Scanner;

import br.com.nald.LiterAlura.dto.DadosAutor;
import br.com.nald.LiterAlura.dto.DadosLivro;
import br.com.nald.LiterAlura.model.Autor;
import br.com.nald.LiterAlura.model.Livro;
import br.com.nald.LiterAlura.repository.LivroRepository;
import br.com.nald.LiterAlura.service.ConsumoAPI;
import br.com.nald.LiterAlura.service.LivroService;

public class Principal {

	private static final String urlApi = "https://gutendex.com/books/";
	private static Scanner scanner = new Scanner(System.in);
	private static ConsumoAPI consumo = new ConsumoAPI();
	
	private LivroRepository repositorio;
	
	public Principal(LivroRepository repositorio) {
		this.repositorio = repositorio;
	}

	public void menu() {
		
		String menu = """
				Escolha o número da sua opção
				1 - Buscar livro pelo título
				2 - Listar livros registrados
				3 - Listar autores registrados
				4 - Listar autores vivos em um determinado ano
				5 - Listar livros em um determinado idioma
				
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
		
				case 0:
					System.out.println("Saindo do sistema...");
					break;
					
				default:
					System.out.println("Opção inválida");
					break;
			}
		}
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
		
		livro.setAutor(autor);
		repositorio.save(livro);
		System.out.println(livro.toString());
		System.out.println(autor.toString());
	}

	@SuppressWarnings("deprecation")
	private String criacaoConexao(String nomeLivro) {
		String nome = URLEncoder.encode(nomeLivro); 
		return consumo.obterDados(urlApi + "?search=" + nome);
	}
}
