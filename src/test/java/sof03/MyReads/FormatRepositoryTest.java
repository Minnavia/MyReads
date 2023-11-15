package sof03.MyReads;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sof03.MyReads.domain.Format;
import sof03.MyReads.domain.FormatRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class FormatRepositoryTest {

	@Autowired
	private FormatRepository formatRepository;
	
	@Test
	public void findByNameShouldReturnFormat() {
		Format format = formatRepository.findByName("audio");
		assertThat(format.getFormat_id()).isEqualTo(2);
	}
	
	@Test
	public void createNewFormat() {
		Format format = new Format("Comic");
		formatRepository.save(format);
		assertThat(format.getFormat_id()).isNotNull();
	}
	
	@Test
	public void deleteGenre() {
		Format format = formatRepository.findById(Long.valueOf(1)).get();
		formatRepository.delete(format);
		Optional<Format> deleteFormat = formatRepository.findById(Long.valueOf(1));
		assertThat(deleteFormat).isEmpty();
	}
}
