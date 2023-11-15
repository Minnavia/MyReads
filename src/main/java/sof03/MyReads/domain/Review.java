package sof03.MyReads.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long review_id;
	
	private String review;
	
	private int rating;
	
	@JsonIgnoreProperties("format")
	@OneToOne(mappedBy = "review")
	private Book book;

	public Long getReview_id() {
		return review_id;
	}

	public void setReview_id(Long review_id) {
		this.review_id = review_id;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Review(String review, int rating) {
		super();
		this.review = review;
		this.rating = rating;
	}

	public Review() {
		super();
	}

	@Override
	public String toString() {
		return "Review [review_id=" + review_id + ", review=" + review + ", rating=" + rating + ", book=" + book + "]";
	}
	
	
}
