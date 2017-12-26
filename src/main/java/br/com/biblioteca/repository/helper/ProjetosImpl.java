package br.com.biblioteca.repository.helper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.transaction.annotation.Transactional;

import br.com.biblioteca.model.Projeto;

public class ProjetosImpl implements ProjetosQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@Transactional(readOnly = true)
	@Override
	public Projeto buscarComPessoas(Long id) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Projeto.class);
		criteria.createAlias("pessoas", "p", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("id", id));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (Projeto) criteria.uniqueResult();
	}

}
