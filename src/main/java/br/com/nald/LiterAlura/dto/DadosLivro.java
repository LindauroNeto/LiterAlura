package br.com.nald.LiterAlura.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(@JsonAlias("title") String titulo,
						@JsonAlias("authors") List<DadosAutor> autor,
						@JsonAlias("languages") List<String> lingua,
						@JsonAlias("download_count") Integer numeroDeDownloads) {

}
