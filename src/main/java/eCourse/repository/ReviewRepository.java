package ecourse.repository;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ecourse.domain.Course;
import ecourse.domain.Review;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	Review findByLearner_Id(long id);

	@Query("Select AVG(structureRating), AVG(difficultyRating), "
			+ "AVG(supportRating), AVG(overallRating) From Review WHERE course = ?1")
	Tuple findAllAverageRatings(Course course);

	@Query("Select id, comment, overallRating, learner From Review")
	List<Tuple> findReviews();

	List<Review> findByReported(boolean reported);

}
