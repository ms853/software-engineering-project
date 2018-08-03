package ecourse.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import ecourse.AjaxResponseBody;
import ecourse.SmtpMailSender;
import ecourse.domain.User;
import ecourse.domain.Views;
import ecourse.dto.PasswordResetRequestDto;
import ecourse.repository.UserRepository;

@RestController
public class AjaxPasswordResetController {

  @Autowired
  private UserRepository userRepo;
  @Autowired
  private SmtpMailSender mailSender;

  /**
   * Creates a new {@link AjaxResponseBody} object.
   * 
   * @param resetPass The password reset form
   * @return {@link AjaxResponseBody}
   */
  @JsonView(Views.Public.class)
  @RequestMapping(value = "/reset-password/request", method = RequestMethod.POST)
  public AjaxResponseBody passwordResetRequest(
      @ModelAttribute(value = "resetPassword") PasswordResetRequestDto resetPass) {

    if (resetPass != null) {
      User user = userRepo.findByEmail(resetPass.getEmail());

      if (user != null) {
        // user with matching email found - generate new password recovery token
        user.setPasswordResetId(UUID.randomUUID().toString());
        user = userRepo.save(user);

        // attempt to send recovery email
        try {
          mailSender.sendPasswordResetRequestMail(user.getEmail(), user.getId(),
              user.getPasswordResetId());
        } catch (Exception e) {
          return new AjaxResponseBody("500", "Failed to send recovery email!", user.getEmail());
        }

        return new AjaxResponseBody("200", "Password recovery email sent!", user.getEmail());
      }
    }

    // failed to find user with that email
    return new AjaxResponseBody("404", "No user with that email found.");

  }

}
