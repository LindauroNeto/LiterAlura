package br.com.nald.LiterAlura.principal;

import java.util.Scanner;

import br.com.nald.LiterAlura.service.PrincipalService;

public class Principal {

	private PrincipalService servico;
	
	public Principal(PrincipalService repositorio) {
		this.servico = repositorio;
	}
	
	private Scanner scanner = new Scanner(System.in);

	public void menu() {
		
		String menu = """
				\n
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
					servico.buscaLivro();
					break;
				
				case 2:
					servico.listagemLivros();
					break;
					
				case 3:
					servico.listagemAutores();
					break;
					
				case 4:
					servico.listagemAutoresPorData();
					break;
					
				case 5:
					servico.listagemLivrosPorIdioma();
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

}
