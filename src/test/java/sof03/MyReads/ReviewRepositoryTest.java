package sof03.MyReads;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sof03.MyReads.domain.Review;
import sof03.MyReads.domain.ReviewRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ReviewRepositoryTest {

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Test
	public void findByRatingShouldReturnList() {
		List<Review> reviews = reviewRepository.findByRating(5);
		assertThat(reviews).hasSize(3);
		assertThat(reviews.get(2).getReview()).isEqualTo("Love love love.");
	}
	
	@Test
	public void createNewReview() {
		Review review = new Review("Boring", 1);
		reviewRepository.save(review);
		assertThat(review.getReview_id()).isNotNull();
	}
	
	@Test
	public void deleteReview() {
		Review review = new Review("Boring", 1);
		reviewRepository.save(review);
		Long id = review.getReview_id();
		reviewRepository.delete(review);
		assertThat(reviewRepository.existsById(id)).isFalse();
	}
}
