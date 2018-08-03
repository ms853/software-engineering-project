package ecourse.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ecourse.dto.ReviewDto;

public class ReviewValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return ReviewDto.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ReviewDto review = (ReviewDto) target;

    if (review.getStructureRating().equals("") || review.getStructureRating() == null) {
      errors.rejectValue("structureRating", "Empty");
    }

    if (review.getDifficultyRating().equals("") || review.getDifficultyRating() == null) {
      errors.rejectValue("difficultyRating", "Empty");
    }

    if (review.getSupportRating().equals("") || review.getSupportRating() == null) {
      errors.rejectValue("supportRating", "Empty");
    }

    if (review.getOverallRating().equals("") || review.getOverallRating() == null) {
      errors.rejectValue("overallRating", "Empty");
    }

  }

}
