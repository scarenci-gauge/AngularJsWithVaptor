package br.com.lino.repository;

import java.util.List;

import br.com.lino.model.Users;

public interface UserRepository {

	public void save(Users user);

	public void update(Users user);

	public void delete(Users user);

	public List<Users> list();

}
