package sof03.MyReads.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Format {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long format_id;
	
	@NotBlank
	private String name;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "format")
	private List<Book> books;

	public Long getFormat_id() {
		return format_id;
	}

	public void setFormat_id(Long format_id) {
		this.format_id = format_id;
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

	public Format(String name) {
		super();
		this.name = name;
	}

	public Format() {
		super();
	}

	@Override
	public String toString() {
		return "Format [id=" + format_id + ", name=" + name + "]";
	}
}
