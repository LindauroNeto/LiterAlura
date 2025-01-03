package br.com.nald.LiterAlura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.nald.LiterAlura.model.Autor;
import br.com.nald.LiterAlura.model.Idiomas;
import br.com.nald.LiterAlura.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

	@Query("SELECT a FROM Livro l JOIN l.autor a WHERE a.dataFalecimento >= :dataAno AND a.dataNascimento <= :dataAno")
	List<Autor> autoresAindaVivos(Integer dataAno);

	@Query("SELECT a FROM Livro l JOIN l.autor a WHERE a.nome = :nome")
	Optional<Autor> autoresJaExistentes(String nome);

	List<Livro> findByIdioma(Idiomas idiomaBuscado);

}
