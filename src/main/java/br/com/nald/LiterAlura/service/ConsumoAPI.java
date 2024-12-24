package br.com.nald.LiterAlura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
	public String obterDados(String endereco) {
		HttpClient cliente = HttpClient.newHttpClient();
		HttpRequest requesicao = HttpRequest.newBuilder().uri(URI.create(endereco)).build();
		
		HttpResponse<String> resposta = null;
		
		try {
			resposta = cliente.send(requesicao, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		return resposta.body();
	}
}
