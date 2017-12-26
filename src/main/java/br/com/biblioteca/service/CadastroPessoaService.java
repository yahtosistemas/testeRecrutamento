package br.com.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.biblioteca.model.Pessoa;
import br.com.biblioteca.repository.Pessoas;

@Service
public class CadastroPessoaService {

	@Autowired
	private Pessoas pessoas;

	@Transactional
	public Pessoa salvar(Pessoa pessoa) {
		return pessoas.save(pessoa);
	}
}
