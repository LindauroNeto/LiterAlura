package br.com.nald.LiterAlura.service;

public interface IConversaoDeDados {
	<T> T obterDados(String json, Class<T> classe);
}
