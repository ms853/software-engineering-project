package ecourse.step;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import ecourse.ECourseApp;
import ecourse.SecurityConfig;
import ecourse.WebConfig;
import ecourse.controller.CourseController;
import ecourse.controller.SignUpController;
import ecourse.controller.TeacherController;
import ecourse.domain.User;
import ecourse.domain.UserRole;
import ecourse.repository.CourseRepository;
import ecourse.repository.LearnerRepository;
import ecourse.repository.TeacherRepository;
import ecourse.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {ECourseApp.class, WebConfig.class, TeacherController.class,
    CourseController.class, SignUpController.class, SecurityConfig.class})
@Transactional
public class MeetUpStepDefs {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private WebApplicationContext context;
  @Autowired
  private ResultActions result;

  // Repositories
  @Autowired
  private TeacherRepository teacherRepo;
  @Autowired
  private UserRepository userRepo;
  @Autowired
  private LearnerRepository learnerRepo;
  @Autowired
  private CourseRepository courseRepo;

  // ------Data attributes for testing----//
  private User teacher;
  private String courseName = "";
  private String address = "";
  private Date scheduledDate = new Date();
  private Map<String, Object> fieldMap;
  private Map<String, Object> validFieldMap;

  /**
   * Construct the setup.
   * 
   */
  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();

    // Clearing all repositories
    teacherRepo.deleteAll();
    learnerRepo.deleteAll();
    courseRepo.deleteAll();
    userRepo.deleteAll();

    // Making a new Teacher (for testing)
    BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
    teacher = new User(UserRole.TEACHER, "Teacher", "teacher@gmail.com", pe.encode("passteacher1"),
        "124", true);
    userRepo.save(teacher);
    SecurityContextHolder.getContext().setAuthentication(
        new UsernamePasswordAuthenticationToken("teacher@mail.com", "passteacher1"));

    // Set the form fields
    fieldMap = new HashMap<String, Object>();
    fieldMap.put("coursenameKey", courseName);
    fieldMap.put("addressKey", address);
    fieldMap.put("dateKey", scheduledDate);

    // Fields for validation
    final String validAddress = "Test location";
    final String ValidCourseName = "Test course name";
    final Date validDate = new Date();
    validFieldMap = new HashMap<String, Object>();
    validFieldMap.put("ValidNameKey", ValidCourseName);
    validFieldMap.put("ValidAddressKey", validAddress);
    validFieldMap.put("ValidDateKey", validDate);
  }

  // Arrange a meet-up

  /*
   * Scenario: Arranging a meetup Given a teacher is on the meetup page And they enter a valid
   * location When they submit that location Then the learners on that course are notified And can
   * view the location on the map
   */

  @Given("^a teacher is on the meetup page$")
  public void teacher_goes_to_teacherPage() throws Throwable {
    this.result = mockMvc.perform(get("/teacher"));
    result.andReturn();
  }

  @Given("^they enter a valid location$")
  public void enter_a_valid_location() {

  }

  @When("^they submit that location$")
  public void they_submit_that_location() {}

  @Then("^the learners on that course are notified$")
  public void the_learners_on_that_course_are_notified() {}

  @Given("^can view the location on the map$")
  public void can_view_the_location_on_the_map() throws Throwable {
    mockMvc.perform(get("/teacher/meetup"));
  }


}
