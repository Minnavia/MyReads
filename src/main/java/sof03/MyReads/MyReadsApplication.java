package sof03.MyReads;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sof03.MyReads.domain.Book;
import sof03.MyReads.domain.BookRepository;
import sof03.MyReads.domain.Format;
import sof03.MyReads.domain.FormatRepository;
import sof03.MyReads.domain.Genre;
import sof03.MyReads.domain.GenreRepository;
import sof03.MyReads.domain.Review;
import sof03.MyReads.domain.ReviewRepository;
import sof03.MyReads.domain.Status;
import sof03.MyReads.domain.StatusRepository;
import sof03.MyReads.domain.User;
import sof03.MyReads.domain.UserRepository;

@SpringBootApplication
public class MyReadsApplication {
	private static final Logger log = LoggerFactory.getLogger(MyReadsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MyReadsApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner readDemo(BookRepository bookRepository, FormatRepository formatRepository, GenreRepository genreRepository, ReviewRepository reviewRepository, StatusRepository statusRepository, UserRepository userRepository) {
		return (args) -> {
			log.info("save some formats");
			Format format1 = new Format("print");
			formatRepository.save(format1);
			Format format2 = new Format("audio");
			formatRepository.save(format2);
			Format format3 = new Format("digital");
			formatRepository.save(format3);
			
			log.info("fetch formats");
			for (Format format: formatRepository.findAll()) {
				log.info(format.toString());
			}
			
			log.info("save some genres");
			Genre genre1 = new Genre("Fantasy");
			genreRepository.save(genre1);
			Genre genre2 = new Genre("Classics");
			genreRepository.save(genre2);
			Genre genre3 = new Genre("Literary");
			genreRepository.save(genre3);
			Genre genre4 = new Genre("Sci-fi");
			genreRepository.save(genre4);
			Genre genre5 = new Genre("Romance");
			genreRepository.save(genre5);
			Genre genre6 = new Genre("Mystery");
			genreRepository.save(genre6);
			Genre genre7 = new Genre("Historical");
			genreRepository.save(genre7);
			
			log.info("fetch genres");
			for (Genre genre: genreRepository.findAll()) {
				log.info(genre.toString());
			}
			
			log.info("save some statuses");
			Status status1 = new Status("Want to read");
			statusRepository.save(status1);
			Status status2 = new Status("Currently reading");
			statusRepository.save(status2);
			Status status3 = new Status("Read");
			statusRepository.save(status3);
			
			log.info("fetch statuses");
			for (Status status: statusRepository.findAll()) {
				log.info(status.toString());
			}
			
			log.info("save some books");
			Book book1 = new Book();
			book1.setTitle("The Lies of Locke Lamora");
			book1.setAuthor("Scott Lynch");
			book1.setFormat(format2);
			book1.setStatus(status3);
			Review review1 = new Review();
			review1.setRating(5);
			review1.setReview("Heartwarming, funny and brutal.");
			book1.setReview(review1);
			book1.getGenres().add(genre1);
			bookRepository.save(book1);
						
			List<Genre> genres1 = new ArrayList<Genre>();
			genres1.add(genre1);
			Review review2 = new Review("Best fantasy book ever.", 5);
			Book book2 = new Book("The Way of Kings", "Brandon Sanderson", format1, genres1, status3, review2);
			bookRepository.save(book2);
			
			List<Genre> genres2 = new ArrayList<Genre>();
			genres2.add(genre5);
			genres2.add(genre7);
			Review review3 = new Review("Love love love.", 5);
			Book book3 = new Book("The Song of Achilles", "Madeline Miller", format1, genres2, status3, review3);
			bookRepository.save(book3);
			
			List<Genre> genres3 = new ArrayList<Genre>();
			genres3.add(genre7);
			genres3.add(genre2);
			Book book4 = new Book("Iceland's Bell", "Halldor Laxness", format1, genres3, status1, null);
			bookRepository.save(book4);
			
			List<Genre> genres4 = new ArrayList<Genre>();
			genres4.add(genre4);
			Book book5 = new Book("All Systems Red", "Martha Wells", format2, genres4, status1, null);
			bookRepository.save(book5);
			
			List<Genre> genres5 = new ArrayList<Genre>();
			genres5.add(genre5);
			genres5.add(genre2);
			Book book6 = new Book("The Wife", "Sigrid Undset", format1, genres5, status2, null);
			bookRepository.save(book6);
			
			List<Genre> genres6 = new ArrayList<Genre>();
			genres6.add(genre3);
			Book book7 = new Book("Drive Your Plow Over the Bones of the Dead", "Olga Tokarczuk", format1, genres6, status2, null);
			bookRepository.save(book7);

			log.info("fetch books");
			for (Book book: bookRepository.findAll()) {
				log.info(book.toString());
			}
			
			log.info("save users");
			User user1 = new User("user", "$2a$10$8FAx5AX8G3q4cK/r/H.ALeXvibbkZeF9lD/j4QXCnZg.TB8CRwPwO", "USER");
			User user2 = new User("admin", "$2a$10$hQOuSJW9b3s.W5BCrmRtsOEVk8W6DryBFwqm7RaoiIXYPeggyNGEy", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);
		};
	}
}
