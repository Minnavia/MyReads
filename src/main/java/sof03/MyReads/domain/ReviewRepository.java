package sof03.MyReads.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long>{
	List<Review> findByRating(int rating);
}
