package ecourse.controller;


import ecourse.AjaxResponseBody;
import ecourse.SmtpMailSender;
import ecourse.domain.User;
import ecourse.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class AjaxSignUpController {

  @Autowired
  private UserRepository userRepo;
  @Autowired
  private SmtpMailSender mailSender;

  /**
   * Check if an email is available for registration.
   * 
   * @param email The email that is being searched.
   * @return {@link AjaxResponseBody}
   */
  @RequestMapping(value = "/check-availability")
  public AjaxResponseBody checkEmailAvailability(@RequestBody String email) {

    if (email != null) {
      User user = userRepo.findByEmail(email);

      // check if user already exists
      if (user != null) {
        // user already exists
        return new AjaxResponseBody("200", "Email already registered.", user.getEmail());
      } else {
        // doesn't exist
        return new AjaxResponseBody("204", "Email available!");
      }

    } else {
      // not a valid search
      return new AjaxResponseBody("400", "No email given.");
    }

  }

  /**
   * Send verification email to user.
   * 
   * @param userId The ID of the user to send the mail to.
   * @return {@link AjaxResponseBody}
   */
  @RequestMapping(value = "/resend-verify", method = RequestMethod.GET)
  public AjaxResponseBody sendVerifyMail(
      @RequestParam(value = "uId", required = true) long userId) {

    User user = userRepo.findOne(userId);
    if (user == null) {
      return new AjaxResponseBody("404", "No user found!");
    } else if (user.isEmailVerified()) {
      return new AjaxResponseBody("400", "User is already verified.");
    }

    try {
      mailSender.sendVerificationMail(user.getEmail(), user.getId(), user.getEmailVerificationId());
    } catch (Exception e) {
      System.err
          .println("Failed to send verify email to " + user.getEmail() + " - " + e.toString());
      return new AjaxResponseBody("500", "Failed to send verify email to " + user.getEmail());
    }

    return new AjaxResponseBody("200", "Sent verify email to " + user.getEmail());

  }

}
