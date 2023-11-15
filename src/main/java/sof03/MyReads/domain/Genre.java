package sof03.MyReads.domain;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Genre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long genre_id;
	
	@NotBlank
	private String name;
	
	@JsonIgnoreProperties("genres")
	@ManyToMany(mappedBy = "genres")
	private List<Book> books = new ArrayList<>();

	public Long getGenre_id() {
		return genre_id;
	}

	public void setGenre_id(Long genre_id) {
		this.genre_id = genre_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Genre(String name) {
		super();
		this.name = name;
	}

	public Genre() {
		super();
	}

	@Override
	public String toString() {
		return this.name;
	}
}
