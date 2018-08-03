package ecourse.service;

import java.util.List;

import javax.persistence.Tuple;

import ecourse.domain.Course;
import ecourse.dto.ReviewDto;

public interface ReviewService {

  void saveReviewFromReviewDto(ReviewDto review, Course course);

  Tuple calculateAverageOfReviews(Course course);

  List<Tuple> getAllReviews();

}
