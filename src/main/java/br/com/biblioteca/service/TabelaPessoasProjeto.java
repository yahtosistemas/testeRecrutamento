package br.com.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.biblioteca.model.Pessoa;

@Component
public class TabelaPessoasProjeto {

	private List<Pessoa> pessoas = new ArrayList<>();
	
	public void adicionarPessoa(Pessoa pessoa) {
		if (!pessoas.contains(pessoa)) {
			pessoas.add(0, pessoa);
		}
	}
	
	public void excluirPessoa(Pessoa pessoa) {
		pessoas.remove(pessoa);
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
}
