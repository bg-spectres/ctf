package org.spectres.ctf;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RelicRepository extends CrudRepository<Relic, Long> {
	 public List<Relic> findAll();
}
