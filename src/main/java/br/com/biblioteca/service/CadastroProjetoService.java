package br.com.biblioteca.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.biblioteca.model.Projeto;
import br.com.biblioteca.model.StatusProjeto;
import br.com.biblioteca.repository.Projetos;
import br.com.biblioteca.service.Exception.GerenteObrigatorioException;
import br.com.biblioteca.service.Exception.ImpossivelExcluirEntidadeException;
import br.com.biblioteca.service.Exception.MembrosProjetoObrigatorioException;

@Service
public class CadastroProjetoService {

	@Autowired
	private Projetos projetos;

	@Transactional
	public void salvar(Projeto projeto) {
		validarGerente(projeto);
		validarMembros(projeto);
		
		projetos.save(projeto);
	}
	
	@Transactional
	public void excluir(Projeto projeto) {
		try {
			validaDeleteProjeto(projeto);
			projetos.delete(projeto);
			projetos.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar projeto.");
		}
	}

	private void validaDeleteProjeto(Projeto projeto) {
		if (projeto.getStatus().equals(StatusProjeto.INICIADO) || 
				projeto.getStatus().equals(StatusProjeto.EM_ANDAMENTO) ||
				projeto.getStatus().equals(StatusProjeto.ENCERRADO)) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar projeto.");
		}
	}

	private void validarMembros(Projeto projeto) {
		if (projeto.getPessoas().isEmpty()) {
			throw new MembrosProjetoObrigatorioException("Adicione pelo menos um membro ao projeto");
		}
	}

	private void validarGerente(Projeto projeto) {
		if (projeto.getGerente().getId() == null) {
			throw new GerenteObrigatorioException("Informar gerente do projeto");
		}
	}
}
