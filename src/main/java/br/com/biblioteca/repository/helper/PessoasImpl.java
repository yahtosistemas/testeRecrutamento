package br.com.biblioteca.repository.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import br.com.biblioteca.model.Pessoa;

public class PessoasImpl implements PessoasQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Pessoa> porCpfCnpjOuNome(String cpfOuNome) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Pessoa.class);
		criteria.add(Restrictions.or(Restrictions.ilike("cpf", cpfOuNome, MatchMode.ANYWHERE),
									(Restrictions.ilike("nome", cpfOuNome, MatchMode.ANYWHERE))));
		criteria.add(Restrictions.eq("funcionario", Boolean.TRUE));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		return (List<Pessoa>) criteria.list();
	}

}
