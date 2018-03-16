package org.spectres.ctf;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	 public List<User> findAll();
}
