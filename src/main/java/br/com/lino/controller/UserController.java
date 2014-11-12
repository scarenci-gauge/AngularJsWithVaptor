package br.com.lino.controller;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.lino.model.Users;
import br.com.lino.repository.UserRepository;

@Resource
public class UserController {

	private Result result;

	private UserRepository userRepository;

	public UserController(Result result, UserRepository userRepository) {
		this.result = result;
		this.userRepository = userRepository;
	}

	@Post("/users")
	@Consumes("application/json")
	public void save(Users users) {
		userRepository.save(users);
		result.use(Results.json()).withoutRoot().from(users).serialize();
	}

	@Put("/users/{user.id}")
	@Consumes("application/json")
	public void update(Users users) {
		userRepository.update(users);
		result.nothing();
	}

	@Delete("/users/{user.id}")
	public void delete(Users users) {
		userRepository.delete(users);
		result.nothing();
	}

	@Get("/users")
	public void list() {
		result.use(Results.json())
				.withoutRoot()
				.from(userRepository.list())
				.serialize();
	}

}
