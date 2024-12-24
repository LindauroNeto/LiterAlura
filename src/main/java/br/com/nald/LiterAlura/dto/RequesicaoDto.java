package br.com.nald.LiterAlura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RequesicaoDto(@JsonAlias("title") String titulo,
						@JsonAlias("authors") DadosAutor autor,
						@JsonAlias("languages") String lingua,
						@JsonAlias("download_count") Integer numeroDeDownloads) {

}
