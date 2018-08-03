package ecourse.validator;

import ecourse.dto.CourseDto;
import ecourse.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateCourseValidator implements Validator {

  @Autowired
  CourseRepository courseRepo;

  @Override
  public boolean supports(Class<?> clazz) {
    return CourseDto.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {

    CourseDto course = (CourseDto) target;

    // course name already exists
    if (courseRepo.findByName(course.getName().trim()) != null) {
      errors.rejectValue("name", "NotEmpty.duplicate.name");
    }

    // difficulty level not selected
    if (course.getLevel().equals("none")) {
      errors.rejectValue("level", "Level.not.selected");
    }

    // category not selected
    if (course.getCategory().equals("none")) {
      errors.rejectValue("category", "Category.not.selected");
    }

    try {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      Date startDate = sdf.parse(course.getStartDate());

      if (!course.getStartDate().equals(sdf.format(startDate))) {
        errors.rejectValue("startDate", "Date.format");
      } else {
        String[] date = course.getStartDate().split("/");
        int day = Integer.parseInt(date[0], 10);
        int month = Integer.parseInt(date[1], 10);
        int year = Integer.parseInt(date[2], 10);

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        if (!(year > currentYear || (year == currentYear && month > currentMonth)
            || (year == currentYear && month == currentMonth && day > currentDay))) {
          errors.rejectValue("startDate", "Date.scope");
        }

      }

    } catch (ParseException ex) {
      System.err.println(ex.getMessage());
    }
  }

}
