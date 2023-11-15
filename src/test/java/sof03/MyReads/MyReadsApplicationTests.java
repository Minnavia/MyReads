package sof03.MyReads;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sof03.MyReads.web.BookController;

@SpringBootTest
class MyReadsApplicationTests {

	@Autowired
	private BookController readsController;
	
	@Test
	public void contextLoads() throws Exception{
		assertThat(readsController).isNotNull();
	}

}
