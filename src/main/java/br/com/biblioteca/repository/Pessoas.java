package br.com.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.biblioteca.model.Pessoa;
import br.com.biblioteca.repository.helper.PessoasQueries;

public interface Pessoas extends JpaRepository<Pessoa, Long>, PessoasQueries {

	List<Pessoa> findByNomeContainingIgnoreCase(String nome);
}
