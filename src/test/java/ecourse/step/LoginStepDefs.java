package ecourse.step;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import ecourse.repository.CourseRepository;
import ecourse.repository.LearnerRepository;
import ecourse.repository.TeacherRepository;
import ecourse.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class LoginStepDefs {

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

    // SecurityContextHolder.getContext()
    // .setAuthentication(new UsernamePasswordAuthenticationToken("learner@gmail.com", "pass"));
  }

  @Given("^an un-authorised user views the login page$")
  public void an_un_authorised_user_views_the_login_page() throws Throwable {
    result = this.mockMvc.perform(get("/"));
    result.andReturn(); // just to stop findbugs from complaining about result not being used
  }

  /**
   * Test for valid email.
   * 
   */
  @And("^they enter an valid email$")
  public void they_enter_an_valid_email() throws Throwable {
    user.setName("learner@gmail.com");
    // List<String> listStrings = new ArrayList<String>();
    // listStrings.add("ROLE_LEARNER");
  }

  @And("^they enter their password$")
  public void they_enter_their_password() throws Throwable {
    user.setPassword("pass");
  }

  /**
   * Test for when the login form is submitted.
   * 
   */
  @When("^they submit the login form$")
  public void they_submit_the_login_form() throws Throwable {
    // mockMvc
    // .perform(post("/login").contentType(MediaType.APPLICATION_FORM_URLENCODED)
    // .param("username", "learner@gmail.com").param("password", "pass"))
    // .andExpect(status().isOk());
  }

  @Then("^an user is redirected to repective page$")
  public void an_user_is_redirected_to_repective_page() throws Throwable {
    // result.andExpect(redirectedUrl("/learner/"));
    // .andExpect(view().name("form/learner"));
  }

}
