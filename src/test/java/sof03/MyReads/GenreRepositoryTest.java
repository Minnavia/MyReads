package sof03.MyReads;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sof03.MyReads.domain.Genre;
import sof03.MyReads.domain.GenreRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class GenreRepositoryTest {
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Test
	public void findByNameShouldReturnGenre() {
		Genre genre = genreRepository.findByName("Fantasy");
		assertThat(genre.getGenre_id()).isEqualTo(1);
	}
	
	@Test
	public void createNewGenre() {
		Genre genre = new Genre("Horror");
		genreRepository.save(genre);
		assertThat(genre.getGenre_id()).isNotNull();
	}
	
	@Test
	public void deleteGenre() {
		Genre genre = genreRepository.findById(Long.valueOf(1)).get();
		genreRepository.delete(genre);
		Optional<Genre> deleteGenre = genreRepository.findById(Long.valueOf(1));
		assertThat(deleteGenre).isEmpty();
	}
}
