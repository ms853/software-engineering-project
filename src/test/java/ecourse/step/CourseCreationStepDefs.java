package ecourse.step;

import cucumber.api.java.Before;

import ecourse.ECourseApp;
import ecourse.SecurityConfig;
import ecourse.WebConfig;
import ecourse.controller.CourseController;
import ecourse.controller.CourseCreationController;
import ecourse.controller.SignUpController;
import ecourse.domain.CourseLevel;
import ecourse.domain.User;
import ecourse.domain.UserRole;
import ecourse.repository.CourseRepository;
import ecourse.repository.LearnerRepository;
import ecourse.repository.TeacherRepository;
import ecourse.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {ECourseApp.class, WebConfig.class, CourseCreationController.class,
    CourseController.class, SignUpController.class, SecurityConfig.class})
@Transactional
public class CourseCreationStepDefs {

  // @Autowired
  // private WebApplicationContext context;
  // private MockMvc mockMvc;
  // private ResultActions result;

  @Autowired
  private UserRepository userRepo;
  @Autowired
  private CourseRepository courseRepo;
  @Autowired
  private LearnerRepository learnerRepo;
  @Autowired
  private TeacherRepository teacherRepo;

  // private CourseDto formCourseDto;
  private User teacher;
  private String courseName = null;
  private Date startDate = null;
  private CourseLevel level = null;
  private int maxLearners = -1;
  private String category = null;
  private String description = null;
  private Map<String, Object> fieldMap;
  private Map<String, Object> validFieldMap;


  // ---------- Course Creation Feature ---------- //

  /**
   * Construct the setup.
   * 
   */
  @Before
  public void setup() {
    // this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    // Clear repos
    teacherRepo.deleteAll();
    learnerRepo.deleteAll();
    courseRepo.deleteAll();
    userRepo.deleteAll();
    // formCourseDto = new CourseDto();
    // Create new User
    BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
    teacher = new User(UserRole.TEACHER, "Teacher", "teacher@gmail.com", pe.encode("password1"),
        "123", true);
    userRepo.save(teacher);
    // SecurityContextHolder.getContext().setAuthentication(
    // new UsernamePasswordAuthenticationToken("teacher@mail.com", "password1"));

    // Setup Form Fields
    fieldMap = new HashMap<String, Object>();
    fieldMap.put("nameKey", courseName);
    fieldMap.put("dateKey", startDate);
    fieldMap.put("difficultyKey", level);
    fieldMap.put("maxKey", maxLearners);
    fieldMap.put("categoryKey", category);
    fieldMap.put("descriptionKey", description);
    // Valid fields
    final String validName = "Test Name";
    final Date validDate = new Date();
    final CourseLevel validDifficulty = CourseLevel.valueOf("BEGINNER");
    final int validMax = 15;
    final String validCategory = "Test Category";
    final String validDescription = "This is a valid description";
    validFieldMap = new HashMap<String, Object>();
    validFieldMap.put("nameKey", validName);
    validFieldMap.put("dateKey", validDate);
    validFieldMap.put("difficultyKey", validDifficulty);
    validFieldMap.put("maxKey", validMax);
    validFieldMap.put("categoryKey", validCategory);
    validFieldMap.put("descriptionKey", validDescription);
  }

  // @Given("^a teacher views the course creation page$")
  // public void a_teacher_views_the_course_creation_page() throws Throwable {
  // this.mockMvc.perform(get("/create-course"));
  // }
  //
  // @Given("^they enter a valid name for a course$")
  // public void they_enter_a_valid_name_for_a_course() throws Throwable {
  // courseName = "Test Name";
  // fieldMap.put("name", courseName);
  // }
  //
  // @Given("^all other input fields are valid$")
  // public void all_other_input_fields_are_valid() throws Throwable {
  // for (Map.Entry<String,Object> entry : fieldMap.entrySet()) {
  // if (entry.equals(null) || entry.equals(-1)) {
  // String entryKey = entry.getKey();
  // // Replace all empty values with valid values
  // if (entryKey.equals("nameKey")) {
  // fieldMap.put("nameKey", validFieldMap.get("nameKey"));
  // }
  // else if (entryKey.equals("dateKey")) {
  // fieldMap.put("dateKey", validFieldMap.get("dateKey"));
  // }
  // else if (entryKey.equals("difficultyKey")) {
  // fieldMap.put("difficultyKey", validFieldMap.get("difficultyKey"));
  // }
  // else if (entryKey.equals("maxKey")) {
  // fieldMap.put("maxKey", validFieldMap.get("maxKey"));
  // }
  // else if (entryKey.equals("categoryKey")) {
  // fieldMap.put("categoryKey", validFieldMap.get("categoryKey"));
  // }
  // else if (entryKey.equals("descriptionKey")) {
  // fieldMap.put("descriptionKey", validFieldMap.get("descriptionKey"));
  // }
  // }
  // }
  // }
  //
  // @When("^they submit the course creation form$")
  // public void they_submit_the_course_creation_form() throws Throwable {
  // result = this.mockMvc.perform(post("/create-course/add")
  // .param("action", "Submit")
  // .param("name", fieldMap.get("nameKey").toString())
  // .param("startDate", fieldMap.get("dateKey").toString())
  // .param("level", fieldMap.get("difficultyKey").toString())
  // .param("maxLearners", fieldMap.get("maxKey").toString())
  // .param("category", fieldMap.get("categoryKey").toString())
  // .param("description", fieldMap.get("descriptionKey").toString()));
  //
  // }

  // @Then("^the course's name is the one they entered$")
  // public void the_course_s_name_is_the_one_they_entered() throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  // throw new PendingException();
  // }
  //
  // @Then("^the values they entered remain$")
  // public void the_values_they_entered_remain() throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  // throw new PendingException();
  // }
  //
  // @Given("^they enter an invalid name for a course$")
  // public void they_enter_an_invalid_name_for_a_course() throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  // throw new PendingException();
  // }
  //
  // @Then("^an error message explains the criteria for a valid course name$")
  // public void an_error_message_explains_the_criteria_for_a_valid_course_name() throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  // throw new PendingException();
  // }
  //
  // @Given("^they enter more than the maximum amount of characters for a description$")
  // public void they_enter_more_than_the_maximum_amount_of_characters_for_a_description() throws
  // Throwable {
  // // Write code here that turns the phrase above into concrete actions
  // throw new PendingException();
  // }
  //
  // @Then("^an error message explains the maximum amount of characters allowed for a description$")
  // public void
  // an_error_message_explains_the_maximum_amount_of_characters_allowed_for_a_description() throws
  // Throwable {
  // // Write code here that turns the phrase above into concrete actions
  // throw new PendingException();
  // }
  //
  // @Given("^all input fields have valid values$")
  // public void all_input_fields_have_valid_values() throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  // throw new PendingException();
  // }
  //
  // @Then("^they are redirected to their course control panel$")
  // public void they_are_redirected_to_their_course_control_panel() throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  // throw new PendingException();
  // }
  //
  // @Then("^the values they entered are shown$")
  // public void the_values_they_entered_are_shown() throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  // throw new PendingException();
  // }
  //
  // @Given("^all input values are valid$")
  // public void all_input_values_are_valid() throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  // throw new PendingException();
  // }
  //
  // @Given("^no category is selected$")
  // public void no_category_is_selected() throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  // throw new PendingException();
  // }
  //
  // @Then("^an error message explains that no category is selected$")
  // public void an_error_message_explains_that_no_category_is_selected() throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  // throw new PendingException();
  // }
  //
  // @Then("^the teacher is on the course creation page$")
  // public void the_teacher_is_on_the_course_creation_page() throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  // throw new PendingException();
  // }
  //
  // @Then("^all values they entered remain$")
  // public void all_values_they_entered_remain() throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  // throw new PendingException();
  // }
  //
  // @Given("^they enter the name of a course that is already taken$")
  // public void they_enter_the_name_of_a_course_that_is_already_taken() throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  // throw new PendingException();
  // }
  //
  // @Given("^all other fields are valid$")
  // public void all_other_fields_are_valid() throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  // throw new PendingException();
  // }
  //
  // @Then("^an error message explains that the name is already taken$")
  // public void an_error_message_explains_that_the_name_is_already_taken() throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  // throw new PendingException();
  // }
  //
  // @Given("^they leave the description field empty$")
  // public void they_leave_the_description_field_empty() throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  // throw new PendingException();
  // }
  //
  // @Then("^an error message explains that the description field is empty$")
  // public void an_error_message_explains_that_the_description_field_is_empty() throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  // throw new PendingException();
  // }



}
