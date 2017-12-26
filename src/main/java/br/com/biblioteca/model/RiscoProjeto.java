package br.com.biblioteca.model;

public enum RiscoProjeto {

	BAIXO_RISCO("Baixo risco"),
	MEDIO_RISCO("Médio risco"),
	ALTO_RISCO("Alto risco");
	
	private String descricao;
	
	private RiscoProjeto(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
