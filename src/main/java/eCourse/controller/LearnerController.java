package ecourse.controller;

import ecourse.domain.Course;
import ecourse.exception.SpringException;
import ecourse.service.CourseService;
import ecourse.service.LearnerService;
import ecourse.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Tuple;

@Controller
@RequestMapping("/learner")
public class LearnerController {

  @Autowired
  private UserService userService;
  @Autowired
  private CourseService courseService;
  @Autowired
  private LearnerService learnerService;

  /**
   * Creates a new {@link String} object.
   * 
   * @param model The model {@link Model} used to populate the view.
   * @return {@link String}
   */
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String learner(Model model) {

    boolean verified = userService.isCurrentLoggedInUserVerified();
    if (!verified) {
      model.addAttribute("emailNotVerified", true);
    }

    Course course = learnerService.findEnrolledCourse(userService.getCurrentLoggedInUser().getId());
    model.addAttribute("course", course);
    Iterable<Course> courseList = courseService.findAllCourses();
    model.addAttribute("courseList", courseList);

    List<Tuple> result = courseService.findTopTweleveCourses();
    List<Tuple> item1 = new ArrayList<>(4);
    List<Tuple> item2 = new ArrayList<>(4);
    List<Tuple> item3 = new ArrayList<>(4);

    if (result != null) {
      for (int count = 0; count < result.size(); count++) {

        if (course != null) {
          if (result.get(count).get(0).equals(course.getId())) {
            result.remove(count);
          }
        }

        if (count >= 0 && count < 2) {
          item1.add(result.get(count));
        }
        if (count >= 2 && count < 4) {
          item2.add(result.get(count));
        }
        if (count >= 4 && count < 6) {
          item3.add(result.get(count));
        }
      }
    }

    model.addAttribute("item1", item1);
    model.addAttribute("item2", item2);
    model.addAttribute("item3", item3);
    model.addAttribute("friends",
        learnerService.findByUser(userService.getCurrentLoggedInUser()).getFriends());

    return "form/learner";

  }

  /**
   * Enrol learner to a course.
   * 
   * @param model The model to populate the view.
   * @param courseId The course ID.
   * @return The view.
   */
  @RequestMapping(value = "/enrol", method = RequestMethod.GET)
  public String enrol(@RequestParam(value = "courseId", required = true) int courseId,
      Model model) {
    String outcome = learnerService.enrolLearner(courseId);
    if (outcome != null) {
      model.addAttribute("url", "/");
      model.addAttribute("exception", new SpringException(outcome));
      return "security/error-page";
    }
    return "redirect:/learner/";
  }

  /**
   * Remove learner's enrolled course.
   * 
   * @param model The model to populate the view.
   * @param courseId The course ID.
   * @return The view.
   */
  @RequestMapping(value = "/quit", method = RequestMethod.GET)
  public String quitCourse(@RequestParam(value = "courseId", required = true) int courseId,
      Model model) {
    String outcome = learnerService.quitCourse(courseId);
    if (outcome != null) {
      model.addAttribute("url", "/");
      model.addAttribute("exception", new SpringException(outcome));
      return "security/error-page";
    }
    return "redirect:/learner/";
  }
  
  @RequestMapping(value = "/map", method = RequestMethod.GET)
  public String map(Model model) {
	  model.addAttribute("longitude", 52.64056);
	  model.addAttribute("latitude", -1.14962);
    return "form/map";
  }
}
