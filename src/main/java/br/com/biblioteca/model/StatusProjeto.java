package br.com.biblioteca.model;

public enum StatusProjeto {

	EM_ANALISE("Em análise"),
	ANALISE_REALIZADA("Análise realizada"),
	ANALISE_APROVADA("Análise aprovada"),
	INICIADO("Iniciado"),
	PLANEJADO("Planejado"),
	EM_ANDAMENTO("Em andamento"),
	ENCERRADO("Encerrado"),
	CANCELADO("cancelado");
	
	private String descricao;
	
	private StatusProjeto(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
