package br.com.nald.LiterAlura.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

@Service
public class PrincipalService {
	
	private final String urlApi = "https://gutendex.com/books/";
	private Scanner scanner = new Scanner(System.in);
	
	private LivroService servicoLivro;
	
	public PrincipalService( LivroService servicoLivro) {
		this.servicoLivro = servicoLivro;
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
		var opcao = scanner.nextLine().toLowerCase().trim();
		
		servicoLivro.buscandoLivrosPorIdioma(opcao);
	}
	
	public void listagemAutoresPorData() {
		System.out.println("Coloque a data limite: ");
		var dataAno = scanner.nextInt();
		scanner.nextLine();
		
		servicoLivro.listandoAutoresPelaData(dataAno);
		
	}
	
	public void listagemAutores() {
		servicoLivro.listandoAutoresRegistrados();
		
	}
	
	public void listagemLivros() {
		System.out.println("Livros registrados: ");
		servicoLivro.listandoLivrosRegistrados();
		
	}
	
	public void buscaLivro() {
		System.out.println("Digite o nome do livro: ");
		var nomeLivro = scanner.nextLine().toLowerCase();
		
		servicoLivro.buscandoERegistrandoDeLivro(urlApi, nomeLivro);
		
	}
	
	

}
