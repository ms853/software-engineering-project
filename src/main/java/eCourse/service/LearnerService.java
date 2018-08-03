package ecourse.service;

import ecourse.domain.Course;
import ecourse.domain.Learner;
import ecourse.domain.User;

import java.util.List;

public interface LearnerService {
  void save(Learner learner);

  Learner findById(long id);

  Iterable<Learner> findAllLearners();
  
  List<Learner> findNotEnrolledLearners();

  Learner findByUser(User user);

  String enrolLearner(long courseId);

  String quitCourse(long courseId);

  Course findEnrolledCourse(long learnerId);

  List<Learner> findByCourse(long courseId);
  
  void setLearnerInvitedToEnrol(User user, long courseId);
  
}
