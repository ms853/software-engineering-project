package ecourse.step;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import ecourse.domain.Learner;
import ecourse.domain.Teacher;
import ecourse.domain.User;
import ecourse.domain.UserRole;
import ecourse.dto.SignUpDto;
import ecourse.repository.CourseRepository;
import ecourse.repository.LearnerRepository;
import ecourse.repository.TeacherRepository;
import ecourse.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class SignupStepDefs {

  @Autowired
  private WebApplicationContext wac;
  @Autowired
  private UserRepository userRepo;
  @Autowired
  private CourseRepository courseRepo;
  @Autowired
  private LearnerRepository learnerRepo;
  @Autowired
  private TeacherRepository teacherRepo;
  private MockMvc mockMvc;
  private SignUpDto formUser;
  private User user;
  private ResultActions result;

  /**
   * Construct the setup.
   * 
   */
  @Before
  public void setup() throws Exception {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    System.out.println("WE ARE HERE " + SecurityContextHolder.getContext().getAuthentication());
    // clear repos
    teacherRepo.deleteAll();
    learnerRepo.deleteAll();
    courseRepo.deleteAll();
    userRepo.deleteAll();
    formUser = new SignUpDto();
    // SecurityContextHolder.getContext()
    // .setAuthentication(new UsernamePasswordAuthenticationToken("learner@gmail.com", "a1234567"));
  }

  /**
   * Test for when an unauthorised user views the sign up page.
   * 
   */
  @Given("^an un-authorised user views the sign up page$")
  public void an_un_authorised_user_views_the_sign_up_page() throws Throwable {
    this.mockMvc.perform(get("/signup"))
        // .andExpect(status().isOk())
        .andExpect(redirectedUrl("/signup"));
  }

  @And("^they enter a valid full name$")
  public void they_enter_a_valid_full_name() throws Throwable {
    formUser.setName("learner");
  }

  @And("^the email and confirm email fields match$")
  public void the_email_and_confirm_email_fields_match() throws Throwable {
    formUser.setEmail("learner@gmail.com");
    formUser.setEmail2("learner@gmail.com");
  }

  @And("^the password and confirm password fields match$")
  public void the_password_and_confirm_password_fields_match() throws Throwable {
    formUser.setPassword("a1234567");
    formUser.setPassword2("a1234567");
  }

  /**
   * Test for when the register form is submitted.
   * 
   */
  @When("^they submit the register form$")
  public void they_submit_the_register_form() throws Throwable {
    mockMvc
        .perform(post("/signup").param("userRole", "TEACHER").param("name", "Teacher")
            .param("email", "teacher@gmail.com").param("email2", "teacher@gmail.com")
            .param("password", "a1234567").param("password2", "a1234567"))
        .andExpect(redirectedUrl("/signup"));

    result = this.mockMvc.perform(post("/signup").param("userRole", formUser.getUserRole().name())
        .param("name", formUser.getName()).param("email", formUser.getEmail())
        .param("email2", formUser.getEmail2()).param("password", formUser.getPassword())
        .param("password2", formUser.getPassword2()));
  }

  @Then("^the account should be stored in the database$")
  public void the_account_should_be_stored_in_the_database() throws Throwable {
    user = userRepo.findByEmail(formUser.getEmail());
    Assert.notNull(user, "This object should not be null");
  }

  /**
   * Test to check if the account is type learner.
   * 
   */
  @And("^the account should be of the type learner$")
  public void the_account_should_be_of_the_type_learner() throws Throwable {
    assertThat(user.getRole(), is(UserRole.LEARNER));
    Learner learner = learnerRepo.findByLearner_Id(user.getId());
    Assert.notNull(learner, "Learner should not be null");
  }

  /**
   * Test to check if the account is type teacher.
   * 
   */
  @And("^the account should be of the type teacher$")
  public void the_account_should_be_of_the_type_teacher() throws Throwable {
    assertThat(user.getRole(), is(UserRole.TEACHER));
    Teacher teacher = teacherRepo.findByTeacher_Id(user.getId());
    Assert.notNull(teacher, "Teacher should not be null");
  }

  @And("^the account email verification status should be false$")
  public void the_account_email_verification_status_should_be_false() throws Throwable {
    assertThat(user.isEmailVerified(), is(false));
  }

  @And("^the user should be directed to the login page$")
  public void they_should_be_directed_to_the_login_page() throws Throwable {
    result.andExpect(redirectedUrl("/user-login"));
  }

  @And("^the password and confirm password fields do not match$")
  public void the_password_and_confirm_password_fields_do_not_match() throws Throwable {
    formUser.setPassword("pass");
    formUser.setPassword2("pass2");
  }

  /**
   * Test for passwords fields not matching.
   * 
   */
  @Then("^an error message explains that the password fields do not match$")
  public void an_error_message_explains_that_the_password_fields_do_not_match() throws Throwable {
    // Errors errors = new BeanPropertyBindingResult(formUser, "formUser");
    // validator.validate(formUser, errors);
    // assertTrue(errors.hasErrors());
    // assertNotNull(errors.getFieldError("password"));
    // String msg = messageSource.getMessage(errors.getFieldError("password").getCode(), null,
    // null);
    // assertThat(msg, is("Passwords are different"));

    result.andExpect(
        model().attributeHasFieldErrorCode("user", "password", "NotEmpty.duplicate.password"));
  }

  @And("^the user is on the sign up page$")
  public void the_user_is_on_the_sign_up_page() throws Throwable {
    result.andExpect(view().name("form/signup"));
  }

  @And("^one or more of the password fields are empty$")
  public void one_or_more_of_the_password_fields_are_empty() throws Throwable {
    formUser.setPassword("");
    formUser.setPassword2("");
  }

  /**
   * Test for password fields empty.
   * 
   */
  @Then("^an error message explains that the password fields are empty$")
  public void an_error_message_explains_that_the_password_fields_are_empty() throws Throwable {
    // Errors errors = new BeanPropertyBindingResult(formUser, "formUser");
    // validator.validate(formUser, errors);
    // assertTrue(errors.hasErrors());
    // assertNotNull(errors.getFieldError("password"));
    // String msg = messageSource.getMessage(errors.getFieldError("password").getCode(), null,
    // null);
    // assertThat(msg, is("Field cannot be empty"));

    result.andExpect(model().attributeHasFieldErrorCode("user", "password", "NotEmpty"))
        .andExpect(model().attributeHasFieldErrorCode("user", "password2", "NotEmpty"));
  }

  @And("^the name field is empty$")
  public void the_name_field_is_empty() throws Throwable {
    formUser.setName("");
  }

  @Then("^an error message explains that name field is empty$")
  public void an_error_message_explains_that_name_field_is_empty() throws Throwable {
    result.andExpect(model().attributeHasFieldErrorCode("user", "name", "NotEmpty"));
  }

  @And("^they enter an email already in the database$")
  public void they_enter_an_email_already_in_the_database() throws Throwable {
    formUser.setEmail("teacher@gmail.com");
  }

  /**
   * Test for all fields valid.
   * 
   */
  @And("^all values in other fields are valid$")
  public void all_values_in_other_fields_are_valid() throws Throwable {
    formUser.setUserRole(UserRole.TEACHER);
    formUser.setName("abc");
    formUser.setEmail2("teacher@gmail.com");
    formUser.setPassword("pass");
    formUser.setPassword2("pass");
  }

  /**
   * Test for already registered email.
   * 
   */
  @Then("^an error message explains that the email address is already registered to a user$")
  public void an_error_message_explains_that_the_email_address_is_already_registered_to_a_user()
      throws Throwable {
    // Errors errors = new BeanPropertyBindingResult(formUser, "formUser");
    // validator.validate(formUser, errors);
    // assertTrue(errors.hasErrors());
    // assertNotNull(errors.getFieldError("email"));
    // String msg = messageSource.getMessage(errors.getFieldError("email").getCode(), null, null);
    // assertThat(msg, is("Email already exists"));
    result.andExpect(model().attributeHasFieldErrorCode("user", "email", "NotEmpty.exists.email"));
  }

  @And("^the email and confirm email do not match$")
  public void the_email_and_confirm_email_do_not_match() throws Throwable {
    formUser.setEmail("learner@gmail.com");
    formUser.setEmail2("lear@gmail.com");
  }

  @Then("^an error message explains that the email fields do not match$")
  public void an_error_message_explains_that_the_email_fields_do_not_match() throws Throwable {
    result.andExpect(model().attributeHasFieldErrorCode("user", "email", "Email.duplicate.email"));
  }

  /**
   * Test for all fields empty.
   * 
   */
  @And("^they leave all fields empty$")
  public void they_leave_all_fields_empty() throws Throwable {
    formUser.setName("");
    formUser.setEmail("");
    formUser.setEmail2("");
    formUser.setPassword("");
    formUser.setPassword2("");
  }

  /**
   * Test for explaining all fields empty.
   * 
   */
  @Then("^an error message explains that the fields are empty$")
  public void an_error_message_explains_that_the_fields_are_empty() throws Throwable {
    result.andExpect(model().attributeHasFieldErrorCode("user", "name", "NotEmpty"))
        .andExpect(model().attributeHasFieldErrorCode("user", "email", "NotEmpty"))
        .andExpect(model().attributeHasFieldErrorCode("user", "email2", "NotEmpty"))
        .andExpect(model().attributeHasFieldErrorCode("user", "password", "NotEmpty"))
        .andExpect(model().attributeHasFieldErrorCode("user", "password2", "NotEmpty"));
  }

  @And("^one or more of the email fields are empty$")
  public void one_or_more_of_the_email_fields_are_empty() throws Throwable {
    formUser.setEmail("");
    formUser.setEmail2("");
  }

  @Then("^an error message explains that the email fields are empty$")
  public void an_error_message_explains_that_the_email_fields_are_empty() throws Throwable {
    result.andExpect(model().attributeHasFieldErrorCode("user", "email", "NotEmpty"))
        .andExpect(model().attributeHasFieldErrorCode("user", "email2", "NotEmpty"));
  }

  @And("^they choose learner option$")
  public void they_choose_learner_option() throws Throwable {
    formUser.setUserRole(UserRole.LEARNER);
  }

  @And("^they choose teacher option$")
  public void they_choose_teacher_option() throws Throwable {
    formUser.setUserRole(UserRole.TEACHER);
  }
}
