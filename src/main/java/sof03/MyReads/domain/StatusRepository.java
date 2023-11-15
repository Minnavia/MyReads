package sof03.MyReads.domain;

import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository<Status, Long>{
	Status findByName(String name);
}