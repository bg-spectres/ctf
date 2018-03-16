package org.spectres.ctf;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SecretRepository extends CrudRepository<Secret, Long> {
	 public List<Secret> findAll();
}
