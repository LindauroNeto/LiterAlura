package br.com.nald.LiterAlura.principal;

import java.util.Scanner;

public class Principal {

	private static final String urlApi = "https://gutendex.com/books/";
	private static Scanner scanner = new Scanner(System.in);
	
	public static void menu() {
		
		String menu = """
				Escolha o número da sua opção
				1 - Buscar livro pelo título
				2 - Listar livros registrados
				3 - Listar autores registrados
				4 - Listar autores vivos em um determinado ano
				5 - Listar livros em um determinado idioma
				
				0 - Sair				
				""";
		
		System.out.println(menu);
		var opcao = scanner.nextInt();
		
		switch (opcao) {
			case 1:
				
				break;
	
			default:
				break;
		}
	}
}
