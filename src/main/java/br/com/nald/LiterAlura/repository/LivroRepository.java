package br.com.nald.LiterAlura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nald.LiterAlura.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
