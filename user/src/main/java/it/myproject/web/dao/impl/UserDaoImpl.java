package it.myproject.web.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import it.myproject.web.dao.UserDao;
import it.myproject.web.entity.User;

public class UserDaoImpl implements UserDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void add(User c) {
		em.persist(c);
	}

	@Override
	public User getById(int id) {
		return em.find(User.class, id);
	}

	@Override
	@Transactional
	public void update(User c) {
		em.merge(c);		
	}

	@Override
	@Transactional
	public void delete(int id) {
		User c = getById(id);
		em.remove(c);
	}



}
