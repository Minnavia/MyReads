package sof03.MyReads;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sof03.MyReads.domain.Status;
import sof03.MyReads.domain.StatusRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class StatusRepositoryTest {

	@Autowired
	private StatusRepository statusRepository;
	
	@Test
	public void findByNameShouldReturnStatus() {
		Status status = statusRepository.findByName("Read");
		assertThat(status.getStatus_id()).isEqualTo(3);
	}
	
	@Test
	public void createNewStatus() {
		Status status = new Status("Did not finish");
		statusRepository.save(status);
		assertThat(status.getStatus_id()).isNotNull();
	}
	
	@Test
	public void deleteStatus() {
		Status status = new Status("Did not finish");
		statusRepository.save(status);
		Long id = status.getStatus_id();
		statusRepository.delete(status);
		assertThat(statusRepository.existsById(id)).isFalse();
	}
}
