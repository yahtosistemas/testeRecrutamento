package br.com.biblioteca.repository.helper;

import java.util.List;

import br.com.biblioteca.model.Pessoa;

public interface PessoasQueries {

	public List<Pessoa> porCpfCnpjOuNome(String cpfOuNome);
}
