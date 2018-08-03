package ecourse.repository;

import ecourse.domain.Course;
import ecourse.domain.CourseLevel;
import ecourse.domain.Review;
import ecourse.domain.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import javax.persistence.Tuple;

public interface CourseRepository extends CrudRepository<Course, Long> {

  Course findByName(String name);

  Course findByTeacher(User teacher);

  Course findByTeacher_Id(long teacherId);

  List<Course> findByLevel(CourseLevel level);

  List<Course> findByCategory(String category);

  @Query("SELECT id, name FROM Course")
  List<Tuple> findCourses();

  @Query("SELECT c.reviews FROM Course c, Review r WHERE c.id = ?1 AND r.learner = ?2")
  Review findReviewForLearner(long courseId, User learner);

}
