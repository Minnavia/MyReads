package sof03.MyReads.domain;

import org.springframework.data.repository.CrudRepository;

public interface FormatRepository extends CrudRepository<Format, Long> {
	Format findByName(String name);
}
