package ecourse.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ecourse.SmtpMailSender;
import ecourse.domain.User;
import ecourse.dto.PasswordResetDto;
import ecourse.repository.UserRepository;
import ecourse.validator.PasswordResetFormValidator;

@Controller
@RequestMapping("/reset-password")
public class PasswordResetController {

  @Autowired
  private PasswordResetFormValidator validator;
  @Autowired
  private UserRepository userRepo;
  @Autowired
  private SmtpMailSender mailSender;

  /**
   * Password reset view.
   * 
   * @param userId The user's ID.
   * @param passwordResetId The password reset token.
   * @param model The model.
   * @return The view.
   */
  @RequestMapping(method = RequestMethod.GET)
  public String passwordResetForm(@RequestParam(value = "uId", required = true) long userId,
      @RequestParam(value = "pId", required = true) String passwordResetId, Model model) {

    User user = userRepo.findOne(userId);

    // check if the user is valid and the reset token is valid
    if (user != null && user.getPasswordResetId() != null
        && user.getPasswordResetId().equals(passwordResetId)) {
      model.addAttribute("email", user.getEmail());
      model.addAttribute("displayName", user.getName());
      model.addAttribute("reset", new PasswordResetDto(userId, passwordResetId));
      return "security/reset-password";
    } else {
      return "redirect:/";
    }

  }

  /**
   * Password reset submit.
   * 
   * @param resetDto The reset form.
   * @param result The validation result.
   * @param model The model.
   * @param redirectAttributes The redirect attributes.
   * @return The view.
   */
  @RequestMapping(method = RequestMethod.POST)
  public String passwordReset(@Valid @ModelAttribute("reset") PasswordResetDto resetDto,
      BindingResult result, Model model, RedirectAttributes redirectAttributes) {

    // make sure the userId and password reset token is valid
    User user = userRepo.findOne(resetDto.getUserId());

    if (user != null && user.getPasswordResetId() != null
        && user.getPasswordResetId().equals(resetDto.getPasswordResetId())) {
      // validate the form (on the server-side now)
      validator.validate(resetDto, result);

      if (result.hasErrors()) {
        // form has validation errors
        model.addAttribute("email", user.getEmail());
        model.addAttribute("displayName", user.getName());
        model.addAttribute("reset", resetDto);
        return "security/reset-password";
      } else {
        user.setPasswordHash(new BCryptPasswordEncoder().encode(resetDto.getPassword()));
        user.setPasswordResetId(null);
        userRepo.save(user);

        // try and send notification e-mail
        try {
          mailSender.sendPasswordResetNotifMail(user.getEmail(), new Date());
        } catch (Exception e) {
          System.err.println("Failed to send password reset notif email to " + user.getEmail()
              + " - " + e.toString());
        }

        redirectAttributes.addFlashAttribute("loginModalLoadAction", "reset-success");
        return "redirect:/";
      }
    }

    return "redirect:/";
  }

}
