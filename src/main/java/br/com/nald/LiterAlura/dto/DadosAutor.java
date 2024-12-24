package br.com.nald.LiterAlura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(@JsonAlias("birth_year") Integer anoNascimento,
						@JsonAlias("death_year") Integer anoFalecimento,
						@JsonAlias("name") String nome) {

}
