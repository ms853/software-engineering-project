package ecourse.service;

import ecourse.domain.Course;
import ecourse.domain.CourseLevel;
import ecourse.domain.User;
import ecourse.dto.CourseDto;
import ecourse.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;

@Service
class CourseServiceImpl implements CourseService {

  @Autowired
  private CourseRepository courseRepo;

  @Autowired
  private UserService userService;

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Course saveCourse(CourseDto courseDto, User teacher) {
    Course course = new Course();
    course.setName(courseDto.getName().trim());
    Date startDate = new Date();
    try {
      startDate = new SimpleDateFormat("dd/MM/yyyy").parse(courseDto.getStartDate());
    } catch (ParseException ex) {
      System.err.println(ex.getMessage());
    }
    course.setStartDate(startDate);
    course.setLevel(CourseLevel.valueOf(courseDto.getLevel()));
    course.setMaxLearners(courseDto.getMaxLearners());
    course.setCategory(courseDto.getCategory());
    course.setDescription(courseDto.getDescription().trim());
    course.setTeacher(teacher);
    return courseRepo.save(course);
  }

  @Override
  public Course findByTeacherId(long teacherId) {
    return courseRepo.findByTeacher_Id(teacherId);
  }

  @Override
  public List<Tuple> findAll() {
    return courseRepo.findCourses();
  }

  @Override
  public Course findById(long courseId) {
    return courseRepo.findOne(courseId);
  }

  @Override
  public Course findByTeacher(User teacher) {
    return courseRepo.findByTeacher(teacher);
  }

  @Override
  public Iterable<Course> findAllCourses() {
    return courseRepo.findAll();
  }

  @Override
  public Course findByName(String name) {
    return courseRepo.findByName(name);
  }

  @Override
  public Boolean findLearnerInReviews(Course course) {
    return courseRepo.findReviewForLearner(course.getId(),
        userService.getCurrentLoggedInUser()) != null;
  }

  @Override
  public List<Tuple> findTopTweleveCourses() {
    String sql = "SELECT id, name, description, startDate FROM Course";
    TypedQuery<Tuple> query = entityManager.createQuery(sql, Tuple.class);
    query.setMaxResults(12);
    return query.getResultList();
  }

}
