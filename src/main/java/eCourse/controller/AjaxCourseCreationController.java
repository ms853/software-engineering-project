package ecourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecourse.AjaxResponseBody;
import ecourse.domain.Course;
import ecourse.service.CourseService;

@RestController
public class AjaxCourseCreationController {

  @Autowired
  private CourseService courseService;

  /**
   * Checks the availability of a course name.
   * 
   * @param courseName The name of the course.
   * @return Result of the search.
   */
  @RequestMapping(value = "/teacher/create-course/check-availability")
  public AjaxResponseBody getSearchResult(@RequestBody String courseName) {

    // check if course name being searched is valid
    if (courseName != null) {

      Course course = courseService.findByName(courseName);

      // check if course name already exists
      if (course != null) {
        // user already exists
        return new AjaxResponseBody("200", "Course name found.", course.getName());
      } else {
        // doesn't exists
        return new AjaxResponseBody("204", "No course with such name found!");
      }

    } else {
      // not a valid search
      return new AjaxResponseBody("400", "Search criteria is empty!");
    }
  }

}
