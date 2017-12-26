package br.com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.biblioteca.model.Projeto;
import br.com.biblioteca.repository.helper.ProjetosQueries;

@Repository
public interface Projetos extends JpaRepository<Projeto, Long>, ProjetosQueries {

}
