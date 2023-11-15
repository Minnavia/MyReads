package sof03.MyReads;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sof03.MyReads.domain.Book;
import sof03.MyReads.domain.BookRepository;
import sof03.MyReads.domain.FormatRepository;
import sof03.MyReads.domain.Genre;
import sof03.MyReads.domain.GenreRepository;
import sof03.MyReads.domain.StatusRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private GenreRepository genreRepository;
	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private FormatRepository formatRepository;
	
	@Test
	public void findByTitleShouldReturnAuthor() {
		List<Book> books = bookRepository.findByTitle("The Way of Kings");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Brandon Sanderson");
	}
	
	@Test
	public void findByStatusShouldReturnBooks() {
		List<Book> books = bookRepository.findByStatus_Name("Read");
		assertThat(books).hasSize(3);
	}
	
	@Test
	public void createNewBook() {
		List<Genre> genres = new ArrayList<Genre>();
		genres.add(genreRepository.findByName("Literary"));
		Book book = new Book("The Waves", "Virginia Woolf", formatRepository.findByName("print"), genres, statusRepository.findByName("Read"), null);
		bookRepository.save(book);
		assertThat(book.getBook_id()).isNotNull();
	}
	
	@Test
	public void deleteBook() {
		Book book = bookRepository.findById(Long.valueOf(4)).get();
		bookRepository.delete(book);
		Optional<Book> deleteBook = bookRepository.findById(Long.valueOf(4));
		assertThat(deleteBook).isEmpty();
	}
}
