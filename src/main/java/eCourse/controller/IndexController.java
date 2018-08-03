package ecourse.controller;

import ecourse.domain.Course;
import ecourse.exception.SpringException;
import ecourse.service.CourseService;
import ecourse.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Tuple;

@Controller
@RequestMapping("/")
public class IndexController {

  @Autowired
  UserService userService;

  @Autowired
  private CourseService courseService;

  /**
   * Creates a new {@link String} object. decide which view to show according to user
   * 
   * @param model The model
   * @return {@link String}
   */
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(Model model) {
    final Authentication auth = userService.getCurrentLoggedInUserAuth();
    if (auth != null) {
      for (GrantedAuthority a : auth.getAuthorities()) {
        if (a.getAuthority().equals("ROLE_PRE_AUTH")) {
          model.addAttribute("loginModalLoadAction", "2fa");
          return "index";
        } else if (a.getAuthority().equals("ROLE_ADMIN")) {
          return "redirect:/admin/";
        } else if (a.getAuthority().equals("ROLE_LEARNER")) {
          return "redirect:/learner/";
        } else if (a.getAuthority().equals("ROLE_TEACHER")) {
          return "redirect:/teacher/";
        }
      }
    }

    Iterable<Course> courseList = courseService.findAllCourses();
    model.addAttribute("courseList", courseList);

    List<Tuple> result = courseService.findTopTweleveCourses();
    List<Tuple> item1 = new ArrayList<>(4);
    List<Tuple> item2 = new ArrayList<>(4);
    List<Tuple> item3 = new ArrayList<>(4);

    if (result != null) {
      for (int count = 0; count < result.size(); count++) {
        if (count >= 0 && count < 4) {
          item1.add(result.get(count));
        }
        if (count >= 4 && count < 8) {
          item2.add(result.get(count));
        }
        if (count >= 8 && count < 12) {
          item3.add(result.get(count));
        }
      }
    }

    model.addAttribute("item1", item1);
    model.addAttribute("item2", item2);
    model.addAttribute("item3", item3);

    return "index";
  }

  /**
   * Lists courses.
   * 
   * @param model The model.
   * @return The view.
   */
  @RequestMapping("/courses")
  public String allCourses(Model model) {

    boolean user = true;

    final Authentication auth = userService.getCurrentLoggedInUserAuth();
    if (auth != null) {
      for (GrantedAuthority a : auth.getAuthorities()) {
        if (a.getAuthority().equals("ROLE_LEARNER")) {
          user = false;
          model.addAttribute("learner", true);
        } else if (a.getAuthority().equals("ROLE_TEACHER")) {
          user = false;
          model.addAttribute("teacher", true);
        } else if (a.getAuthority().equals("ROLE_ADMIN")) {
          user = false;
          model.addAttribute("admin", true);
        }
      }
    }

    model.addAttribute("user", user);

    Iterable<Course> courseList = courseService.findAllCourses();
    model.addAttribute("courseList", courseList);
    return "form/courses";
  }

  @RequestMapping("/about")
  public String about(Model model) {
    boolean user = true;

    final Authentication auth = userService.getCurrentLoggedInUserAuth();
    if (auth != null) {
      for (GrantedAuthority a : auth.getAuthorities()) {
        if (a.getAuthority().equals("ROLE_LEARNER")) {
          user = false;
        } else if (a.getAuthority().equals("ROLE_TEACHER")) {
          user = false;
        } else if (a.getAuthority().equals("ROLE_ADMIN")) {
          user = false;
        }
      }
    }

    model.addAttribute("user", user);
    return "form/about";
  }

  /**
   * The error page for bad urls.
   * 
   * @param model The model to populate the view.
   * @return The error-page view.
   */
  @RequestMapping("/error-page")
  public String errorPage(Model model) {
    model.addAttribute("url", "/");
    model.addAttribute("exception", new SpringException(
        "Error occurred - Bad gateway, if this problem persists, please contact the webmaster"));
    return "security/error-page";
  }

  /**
   * The error page for users trying to access page they are not allowed to.
   * 
   * @param model The model to populate the view.
   * @return The error-page view.
   */
  @RequestMapping("/access-denied")
  public String error(Model model) {
    model.addAttribute("url", "/");
    model.addAttribute("exception",
        new SpringException("Access Denied - You do not have access to this resource."));
    return "security/error-page";
  }

}
