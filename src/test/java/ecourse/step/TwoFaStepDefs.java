package ecourse.step;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ecourse.domain.User;
import ecourse.repository.CourseRepository;
import ecourse.repository.LearnerRepository;
import ecourse.repository.TeacherRepository;
import ecourse.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class TwoFaStepDefs {

  @Autowired
  private WebApplicationContext wac;
  @Autowired
  private FilterChainProxy springSecurityFilterChain;
  private MockMvc mockMvc;
  private ResultActions result;
  private User user = new User();
  @Autowired
  private UserRepository userRepo;
  @Autowired
  private CourseRepository courseRepo;
  @Autowired
  private LearnerRepository learnerRepo;
  @Autowired
  private TeacherRepository teacherRepo;

  /**
   * Construct the setup.
   * 
   */
  @Before
  public void setup() {
    this.mockMvc =
        MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
    teacherRepo.deleteAll();
    learnerRepo.deleteAll();
    courseRepo.deleteAll();
    userRepo.deleteAll();

    SecurityContextHolder.getContext()
        .setAuthentication(new UsernamePasswordAuthenticationToken("2fa@gmail.com", "pass"));
  }

  /**
   * Test for when the learner views he login modal.
   * 
   */
  @Given("^learner views the login-modal page$")
  public void learner_views_the_login_modal_page() throws Throwable {
    result = this.mockMvc.perform(get("/"));
    // just to stop findbugs from complaining about result not being used
    result.andReturn();
  }

  @Given("^they enter an authorised email address$")
  public void they_enter_an_authorised_email_address() throws Throwable {
    user.setName("2fa@gmail.com");
  }

  @Given("^they enter a valid password matching the email address$")
  public void they_enter_a_valid_password_matching_the_email_address() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  /**
   * Test for when the login form is submitted.
   * 
   */
  @When("^when they submit the login form$")
  public void when_they_submit_the_login_form() throws Throwable {
    mockMvc
        .perform(post("/").contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("username", "2fa@gmail.com").param("password", "pass"))
        .andExpect(status().isOk());
  }

  @When("^they are on the (\\d+)FA page$")
  public void they_are_on_the_FA_page(int arg1) throws Throwable {
    result = this.mockMvc.perform(get("/auth-continue/2fa"));
    result.andReturn(); // just to stop findbugs from complaining about result not being used
  }

  /**
   * Test for valid two factor code entry.
   * 
   */
  @When("^they enter a valid (\\d+)FA code$")
  public void they_enter_a_valid_FA_code(int arg1) throws Throwable {
    mockMvc
        .perform(post("/auth-continue/2fa").contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("username", "2fa@gmail.com").param("password", "pass"))
        .andExpect(status().isOk());
  }

  @Then("^they will successfully login$")
  public void they_will_successfully_login() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @When("^they enter an invalid (\\d+)FA code$")
  public void they_enter_an_invalid_FA_code(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @Then("^an error message explains that they need to enter a valid code$")
  public void an_error_message_explains_that_they_need_to_enter_a_valid_code() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

}
