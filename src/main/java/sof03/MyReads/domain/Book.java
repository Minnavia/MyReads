package sof03.MyReads.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long book_id;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String author;
	
	@ManyToOne
	@JsonIgnoreProperties("books")
	@JoinColumn(name="format_id")
	private Format format;
	
	@JsonIgnoreProperties("books")
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(
			name="book_genre",
			joinColumns = {@JoinColumn(name="book_id")},
			inverseJoinColumns = {@JoinColumn(name="genre_id")}
			)
	private List<Genre> genres = new ArrayList<>();
	
	@ManyToOne
	@JsonIgnoreProperties("books")
	@JoinColumn(name="status_id")
	private Status status;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("book")
	@JoinColumn(name="review_id")
	private Review review;

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public Long getBook_id() {
		return book_id;
	}

	public void setBook_id(Long book_id) {
		this.book_id = book_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Book( String title, String author, Format format,
			List<Genre> genres, Status status, Review review) {
		super();
		this.title = title;
		this.author = author;
		this.format = format;
		this.genres = genres;
		this.status = status;
		this.review = review;
	}

	public Book() {
		super();
	}

	@Override
	public String toString() {
		return "Book [book_id=" + book_id + ", title=" + title + ", author=" + author + ", format=" + format + ", status=" + status + "]";
	}
	
	
}
