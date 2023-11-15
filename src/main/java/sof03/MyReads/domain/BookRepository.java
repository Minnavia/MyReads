package sof03.MyReads.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long>, JpaRepository<Book, Long> {
	List<Book> findByTitle(String title);
	List<Book> findByAuthor(String author);
	List<Book> findByStatus_Name(String name);
	List<Book> findByFormat_Name(String name);
	List<Book> findByGenres_Name(String name);
	Book getById(Long id);
}
