package ecourse.service;

import ecourse.domain.Course;
import ecourse.domain.User;
import ecourse.dto.CourseDto;

import java.util.List;

import javax.persistence.Tuple;

public interface CourseService {

  Course saveCourse(CourseDto course, User teacher);

  Course findByTeacherId(long teacherId);

  List<Tuple> findAll();

  Course findById(long courseId);

  Course findByTeacher(User teacher);

  Course findByName(String name);

  Iterable<Course> findAllCourses();

  Boolean findLearnerInReviews(Course course);

  List<Tuple> findTopTweleveCourses();

}
