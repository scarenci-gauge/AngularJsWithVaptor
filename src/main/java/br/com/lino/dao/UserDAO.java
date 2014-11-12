package br.com.lino.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Qualifier;

import br.com.caelum.vraptor.ioc.Component;
import br.com.lino.model.Users;
import br.com.lino.repository.UserRepository;

@Component
@Qualifier("users")
public class UserDAO implements UserRepository {

	private final Session session;

	public UserDAO(Session session) {
		this.session = session;
	}

	public void save(Users user) {
		session.save(user);
	}

	public void update(Users user) {
		session.update(user);
	}

	public void delete(Users user) {
		session.delete(user);
	}

	@SuppressWarnings("unchecked")
	public List<Users> list() {
		return session.createCriteria(Users.class).list();
	}

}
