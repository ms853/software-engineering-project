package ecourse.controller;

import ecourse.domain.Course;
import ecourse.domain.Teacher;
import ecourse.domain.User;
import ecourse.dto.CourseDto;
import ecourse.exception.SpringException;
import ecourse.repository.TeacherRepository;
import ecourse.service.CourseService;
import ecourse.service.TeacherService;
import ecourse.service.UserService;
import ecourse.validator.CreateCourseValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.validation.Valid;

@Controller
@RequestMapping("/teacher/create-course")
public class CourseCreationController {

  @Autowired
  private CourseService courseService;
  @Autowired
  private UserService userService;
  @Autowired
  private TeacherService teacherService;
  @Autowired
  private TeacherRepository teacherRepo;
  @Autowired
  private CreateCourseValidator validator;

  CourseDto courseDto = new CourseDto();

  /**
   * Create a course.
   * 
   * @param model The model
   * @return The view
   */
  @RequestMapping({"/", ""})
  public String create(Model model) {

    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date today = Calendar.getInstance().getTime();
    String startDate = df.format(today);
    courseDto.setStartDate(startDate);

    boolean verified = userService.isCurrentLoggedInUserVerified();
    if (!verified) {
      model.addAttribute("error", true);
      return "form/teacher";
    }
    Teacher teacher = teacherService.findByTeacherId(userService.getCurrentLoggedInUser().getId());
    if (teacher.hasCourse()) {
      model.addAttribute("url", "/");
      model.addAttribute("exception",
          new SpringException("This Teacher account is already registered to a Course."));
      return "security/error-page";
    }

    model.addAttribute("course", courseDto);
    return "form/create-course";
  }

  /**
   * Add the course to create.
   * 
   * @param model The model
   * @param courseDto The create course form
   * @param results Form verification results
   * @return The view
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public String add(Model model, @Valid @ModelAttribute("course") CourseDto courseDto,
      BindingResult results) {

    validator.validate(courseDto, results);

    if (results.hasErrors()) {
      System.out.println(results.getAllErrors());
      return "form/create-course";
    }

    User teacher = userService.getCurrentLoggedInUser();
    Course course = courseService.saveCourse(courseDto, teacher);
    Teacher courseTeacher = teacherService.findByTeacherId(teacher.getId());
    courseTeacher.setCourse(course);
    teacherRepo.save(courseTeacher);
    return "redirect:/teacher/course/" + course.getId();
  }
}
