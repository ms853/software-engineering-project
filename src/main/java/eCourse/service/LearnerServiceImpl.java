package ecourse.service;

import ecourse.domain.Course;
import ecourse.domain.Learner;
import ecourse.domain.User;
import ecourse.repository.CourseRepository;
import ecourse.repository.LearnerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
class LearnerServiceImpl implements LearnerService {

  @Autowired
  private UserService userService;
  @Autowired
  private CourseRepository courseRepo;
  @Autowired
  private LearnerRepository learnerRepo;
  @Autowired
  private LearnerService learnerService;

  @Override
  public void save(Learner learner) {
    learnerRepo.save(learner);
  }

  @Override
  public Learner findById(long id) {
    return learnerRepo.findOne(id);
  }

  @Override
  public Iterable<Learner> findAllLearners() {
    return learnerRepo.findAll();
  }

  @Override
  public Learner findByUser(User user) {
    Learner learner = null;
    for (Iterator<Learner> it = learnerRepo.findAll().iterator(); it.hasNext();) {
      Learner checker = it.next();
      if (checker.getLearner().equals(user)) {
        learner = checker;
        break;
      }
    }
    return learner;
  }

  @Override
  public String enrolLearner(long courseId) {
    // get the current logged in user
    User user = userService.getCurrentLoggedInUser();
    // get the course the learner wants to enrol on
    Course course = courseRepo.findOne(courseId);

    // check if learner is already enrolled on a course
    Learner learner = learnerRepo.findByLearner_Id(user.getId());
    if (learner.getCourse() != null) {
      return "You are already enrolled on " + learner.getCourse().getName()
          + " course. You must finish this course or "
          + "quit your current course to enrol to a new course";
    }

    // check if space available for enrolment
    if (course.getLearners().size() < course.getMaxLearners()) {
      // enrol learner
      course.addLearner(user);
      courseRepo.save(course);
      learner.setCourse(course);
      learnerRepo.save(learner);
      return null;
    }

    return "Limited spaces have filled out. Please try again later.";
  }

  @Override
  public Course findEnrolledCourse(long learnerId) {
    return learnerRepo.findByLearner_Id(learnerId).getCourse();
  }

  @Override
  public String quitCourse(long courseId) {
    // get the current logged in user
    User user = userService.getCurrentLoggedInUser();
    // get the course the learner wants to enrol on
    Course course = courseRepo.findOne(courseId);

    // get learner
    Learner learner = learnerRepo.findByLearner_Id(user.getId());

    if (learner.getCourse().equals(course)) {
      learner.setCourse(null);
      learnerRepo.save(learner);
      System.out.println(course.getLearners().remove(user));
      courseRepo.save(course);
      return null;
    }

    return "An error occurred. Please try again later";
  }

  @Override
  public List<Learner> findByCourse(long courseId) {
    List<Learner> returned = new ArrayList<Learner>();
    for (Iterator<User> it = learnerRepo.findByCourse().iterator(); it.hasNext();) {
      Learner learner = learnerService.findByUser(it.next());
      if (learner.getCourse().getId() == courseId) {
        returned.add(learner);
      }
    }
    return returned;
  }

  @Override
  public void setLearnerInvitedToEnrol(User user, long courseId) {
    Learner learner = learnerService.findByUser(user);
    Course course = courseRepo.findOne(courseId);
    learner.setInviteCourse(course);
    learnerRepo.save(learner);
  }

  @Override
  public List<Learner> findNotEnrolledLearners() {
    return learnerRepo.findLearnersNotEnrolled();
  }

}
