package br.com.nald.LiterAlura.model;

public enum Idiomas {
	EN("en"),
	PT("pt"),
	FR("fr"),
	ES("es");
	
	private String lingua;

	private Idiomas(String lingua) {
		this.lingua = lingua;
	}
	
	public Idiomas pegarIdioma(String lingua) {
		for (Idiomas iterable_element : Idiomas.values()) {
			if (iterable_element.lingua.equalsIgnoreCase(lingua)) {
				return iterable_element;
			}
		}
		return null;
	}
	
}
