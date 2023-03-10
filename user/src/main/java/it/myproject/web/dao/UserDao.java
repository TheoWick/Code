package it.myproject.web.dao;

import it.myproject.web.entity.User;

public interface UserDao {

	public void add(User c);
	public void update(User c);
	public void delete(int id);
	public User getById(int id);
}
