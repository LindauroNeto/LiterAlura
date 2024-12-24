package br.com.nald.LiterAlura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.nald.LiterAlura.principal.Principal;
import br.com.nald.LiterAlura.repository.LivroRepository;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner{

	@Autowired
	public LivroRepository repositorio;
	
	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorio);
		principal.menu();
	}

}
