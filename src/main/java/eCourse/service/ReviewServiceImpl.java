package ecourse.service;

import ecourse.domain.Course;
import ecourse.domain.Review;
import ecourse.domain.User;
import ecourse.dto.ReviewDto;
import ecourse.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.persistence.Tuple;

@Service
class ReviewServiceImpl implements ReviewService {

  @Autowired
  private UserService userService;
  @Autowired
  private ReviewRepository reviewRepo;

  @Override
  public void saveReviewFromReviewDto(ReviewDto review, Course course) {

    User user = userService.getCurrentLoggedInUser();
    Review newReview = new Review();

    newReview.setComment(review.getComment());
    newReview.setStructureRating(Float.parseFloat(review.getStructureRating()));
    newReview.setDifficultyRating(Float.parseFloat(review.getDifficultyRating()));
    newReview.setSupportRating(Float.parseFloat(review.getSupportRating()));
    newReview.setOverallRating(Float.parseFloat(review.getOverallRating()));
    newReview.setLearner(user);
    newReview.setCourse(course);

    reviewRepo.save(newReview);

  }

  @Override
  public Tuple calculateAverageOfReviews(Course course) {
    return reviewRepo.findAllAverageRatings(course);
  }

  @Override
  public List<Tuple> getAllReviews() {
    return reviewRepo.findReviews();
  }
  
}
