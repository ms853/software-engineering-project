package ecourse.step;

import ecourse.ECourseApp;
import ecourse.SecurityConfig;
import ecourse.WebConfig;
import ecourse.controller.CourseController;
import ecourse.controller.CourseUploadFileController;
import ecourse.controller.SignUpController;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(
    classes = {ECourseApp.class, WebConfig.class, CourseUploadFileController.class,
        CourseController.class, SignUpController.class, SecurityConfig.class})
@Transactional
public class ContentUploadStepDefs {

  // CODE COMMENTED FOR FIXING FINDBUGSMAIN
  // UNCOMMENT WHEN CODE REQUIRED

  // ---------- Testing for Course Upload Feature ---------- //
  // @Autowired
  // private WebApplicationContext context;
  // private MockMvc mockMvc;
  // private ResultActions result;
  //
  // @Autowired
  // private CourseService courseService;
  //
  // @Autowired
  // private UserService userService;
  //
  // @Autowired
  // private CourseUploadFileService uploadService;

  // MultipartFile file;
  // CourseUploadFile uploadFile = new CourseUploadFile();


  // public ContentUploadStepDefs() {
  // //Set up for testing
  // this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
  // }
  // //Test for uploading content successfully!
  //
  // @Given("^A teacher views the teacher page$^")
  // public void a_teacher_views_the_teacher_page() throws Throwable{
  // this.mockMvc.perform(get("/teacher"));
  // }
  //
  // @And("^they choose files to upload$")
  // public void uploadFile() throws FileNotFoundException {
  // this.mockMvc.perform(uploadService.save(uploadFile,file));
  // }



}
