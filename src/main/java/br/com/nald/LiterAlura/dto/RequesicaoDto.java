package br.com.nald.LiterAlura.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RequesicaoDto(@JsonAlias("results") List<DadosLivro> infoLivro) {

}
