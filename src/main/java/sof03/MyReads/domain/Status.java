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
public class Status {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long status_id;
	
	@NotBlank
	private String name;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
	private List<Book> books;

	public Long getStatus_id() {
		return status_id;
	}

	public void setStatus_id(Long status_id) {
		this.status_id = status_id;
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

	public Status(String name) {
		super();
		this.name = name;
	}

	public Status() {
		super();
	}

	@Override
	public String toString() {
		return "Status [status_id=" + status_id + ", name=" + name + "]";
	}
}
