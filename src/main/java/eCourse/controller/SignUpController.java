package ecourse.controller;

import ecourse.SmtpMailSender;
import ecourse.domain.User;
import ecourse.dto.SignUpDto;
import ecourse.service.UserService;
import ecourse.validator.SignUpFormValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/signup")
public class SignUpController {

  @Autowired
  private UserService userService;
  @Autowired
  private SignUpFormValidator validator;
  @Autowired
  private SmtpMailSender mailSender;

  /**
   * Shows the sign up form when the page is accessed through a GET.
   * 
   * @param model The model used to populate the view.
   * @return {@link String}
   */
  @RequestMapping(method = RequestMethod.GET)
  public String signupForm(Model model) {
    if (userService.isCurrentUserLoggedIn()) {
      return "redirect:/";
    }

    model.addAttribute("user", new SignUpDto());
    return "form/signup";
  }

  /**
   * Handles the signing in of a user.
   * 
   * @param userDto The user {@link SignUpDto} information that is posted from the sign up form.
   * @param result Where the results {@link BindingResult} of the input validation is stored.
   * @param request The current HTTP request so we can start a new session for the newly registered
   *        user
   * @param model The view's model
   * @return {@link String}
   */
  @RequestMapping(method = RequestMethod.POST)
  public String signupAdd(@Valid @ModelAttribute("user") SignUpDto userDto, BindingResult result,
      HttpServletRequest request, Model model) {

    // validate the form (on the server-side now)
    validator.validate(userDto, result);

    if (result.hasErrors()) {
      // form has validation errors
      model.addAttribute("user", userDto);
      return "form/signup";
    } else {
      // register the user
      User newUser = userService.registerUserFromDto(userDto);

      // try to send the verification e-mail
      try {
        mailSender.sendVerificationMail(newUser.getEmail(), newUser.getId(),
            newUser.getEmailVerificationId());
      } catch (Exception e) {
        System.err
            .println("Failed to send verify email to " + newUser.getEmail() + " - " + e.toString());
      }

      // success screen
      model.addAttribute("displayName", newUser.getName());
      model.addAttribute("email", newUser.getEmail());
      model.addAttribute("secret2fa", newUser.getSecret2Fa());
      try {
        model.addAttribute("qrData",
            URLEncoder.encode("otpauth://totp/Get Digital:" + newUser.getEmail() + "?secret="
                + newUser.getSecret2Fa() + "&issuer=Get Digital", "UTF-8"));
      } catch (Exception e) {
        System.err.println(
            "Couldn't generate TOTP QR for user " + newUser.getId() + " - " + e.getMessage());
      }

      return "security/signup-success";
    }


  }


  /**
   * Handles the verification of a user account.
   * 
   * @param userId The User's ID to be verified.
   * @param verificationId The email verification id received {@link String}.
   * @param redirectAttributes So that we can set the model of the redirect
   * @return {@link String}
   */
  @RequestMapping(value = "/confirm", method = RequestMethod.GET)
  public String confirmEmail(@RequestParam(value = "uId", required = true) long userId,
      @RequestParam(value = "vId", required = true) String verificationId,
      RedirectAttributes redirectAttributes) {
    if (userService.verifyUser(userId, verificationId)) {
      redirectAttributes.addFlashAttribute("loginModalLoadAction", "verify-success");
    }
    return "redirect:/";
  }

}
