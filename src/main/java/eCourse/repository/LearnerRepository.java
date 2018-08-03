package ecourse.repository;

import ecourse.domain.Learner;
import ecourse.domain.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LearnerRepository extends CrudRepository<Learner, Long> {

  Learner findByLearner_Id(long learnerId);

  @Query("SELECT learner FROM Learner WHERE course_course_id IS NOT NULL")
  List<User> findByCourse();
  
  @Query("SELECT l FROM Learner l WHERE course_course_id IS NULL")
  List<Learner> findLearnersNotEnrolled();
}
