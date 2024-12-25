package br.com.nald.LiterAlura.exceptions;

public class LivroNaoEcontradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String mensagem;
	
	public LivroNaoEcontradoException(String mensagem) {
		this.mensagem = mensagem;
	}
	
	@Override
	public String getMessage() {
		return this.mensagem;
	}
}
