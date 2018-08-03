package ecourse.controller;

import ecourse.SmtpMailSender;
import ecourse.domain.Course;
import ecourse.domain.Learner;
import ecourse.domain.Meetup;
import ecourse.domain.User;
import ecourse.dto.MeetupDto;
import ecourse.service.CourseService;
import ecourse.service.CourseUploadFileService;
import ecourse.service.LearnerService;
import ecourse.service.MeetupService;
import ecourse.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

  @Autowired
  private UserService userService;
  @Autowired
  private CourseService courseService;
  @Autowired
  private LearnerService learnerService;
  @Autowired
  private MeetupService meetupService;
  @Autowired
  private SmtpMailSender mailSender;

  private final CourseUploadFileService uploadService;

  @Autowired
  public TeacherController(CourseUploadFileService uploadService) {
    this.uploadService = uploadService;
  }

  /**
   * Teacher info home.
   * 
   * @param model The model.
   * @return The view.
   */
  @RequestMapping("/")
  public String index(Model model) {
    boolean verified = userService.isCurrentLoggedInUserVerified();
    if (!verified) {
      model.addAttribute("emailNotVerified", true);
    }

    User teacher = userService.getCurrentLoggedInUser();
    model.addAttribute("teacher", teacher);
    Course course = courseService.findByTeacher(teacher);
    model.addAttribute("course", course);

    List<Learner> learnersList = learnerService.findNotEnrolledLearners();
    model.addAttribute("learnersList", learnersList);

    // uploaded files
    model.addAttribute("files",
        uploadService.loadAll()
            .map(path -> MvcUriComponentsBuilder.fromMethodName(CourseController.class,
                "serveFile", course.getId(), path.getFileName().toString()).build().toString())
            .collect(Collectors.toList()));

    // meetups
    List<Meetup> meetups = meetupService.getAllMeetups(course);
    model.addAttribute("meetups", meetups);
    
    return "form/teacher";
  }

  @RequestMapping(value = "/meetup/{courseId}", method = RequestMethod.GET)
  public String teacherMeetup(Model model, @PathVariable long courseId) {
    model.addAttribute("course", courseService.findById(courseId));
    model.addAttribute("meetup", new MeetupDto());
    return "form/meetup";
  }

  @RequestMapping(value = "/meetup/{courseId}", method = RequestMethod.POST)
  public String subitTeacherMeetup(@ModelAttribute("meetup") MeetupDto meetupDto, Model model,
      @PathVariable long courseId, RedirectAttributes redirectAttributes) {

    meetupService.saveMeetup(meetupDto);
    
    redirectAttributes.addFlashAttribute("arranged", true);
    return "redirect:/teacher/";
  }

  /**
   * Teacher invite learner on course.
   * 
   * @param learnerId The learner to enrol on course
   * @param courseId The course the learner will enrol
   * @param redirectAttributes So that we can set the model of the redirect
   * @return redirect to teacher home page.
   */
  @RequestMapping("/invite/{learnerId}/{courseId}")
  public String teacherInvite(@PathVariable long learnerId, @PathVariable long courseId,
      RedirectAttributes redirectAttributes) {

    User learner = userService.getUserById(learnerId);
    User teacher = userService.getCurrentLoggedInUser();

    // try to send the invite e-mail
    try {
      mailSender.sendLearnerInviteMail(teacher, learner.getEmail(), courseId);
      learnerService.setLearnerInvitedToEnrol(learner, courseId);
      redirectAttributes.addFlashAttribute("invited", true);
    } catch (Exception ex) {
      System.err
          .println("Failed to send invite email to " + learner.getEmail() + " - " + ex.toString());
    }

    return "redirect:/teacher/";
  }

}
