package br.com.nald.LiterAlura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConversaoDados implements IConversaoDeDados {
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public <T> T obterDados(String json, Class<T> classe) {
		try {
			return mapper.readValue(json, classe);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}