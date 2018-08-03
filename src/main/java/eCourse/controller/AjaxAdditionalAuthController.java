package ecourse.controller;

import java.util.ArrayList;
import java.util.List;

import org.jboss.aerogear.security.otp.Totp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ecourse.AjaxResponseBody;
import ecourse.domain.User;
import ecourse.dto.TwoFactorAuthDto;
import ecourse.service.UserService;

@RestController
@RequestMapping("/auth-continue")
public class AjaxAdditionalAuthController {

  @Autowired
  UserService userService;

  /**
   * Completes a successful auth for the currently logged in user.
   */
  public void completeUserAuth() {
    final Authentication auth = userService.getCurrentLoggedInUserAuth();

    // authenticate the user depending on their entity role
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(auth.getAuthorities());
    authorities.remove(new SimpleGrantedAuthority("ROLE_PRE_AUTH"));
    authorities.add(new SimpleGrantedAuthority(
        "ROLE_" + userService.getCurrentLoggedInUser().getRole().name()));

    // update the user's authentication with the new authorities
    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
        auth.getPrincipal(), auth.getCredentials(), authorities));
  }

  /**
   * Continue auth and do additional auth for user if necessary.
   * 
   * @return The response
   */
  @RequestMapping(method = RequestMethod.GET)
  public AjaxResponseBody authContinue() {

    User user = userService.getCurrentLoggedInUser();

    if (user.isUsing2Fa() && user.getSecret2Fa() != null) {
      return new AjaxResponseBody("401", "auth.user.requires2fa");
    } else {
      completeUserAuth();
      return new AjaxResponseBody("200", "auth.user.success");
    }

  }

  /**
   * Handle 2FA auth if required.
   * 
   * @param tfaDto The 2FA form
   * @return 2FA auth result
   */
  @RequestMapping(value = "/2fa", method = RequestMethod.POST)
  public AjaxResponseBody authContinue2Fa(@ModelAttribute(value = "tfa") TwoFactorAuthDto tfaDto) {

    User user = userService.getCurrentLoggedInUser();

    if (user.isUsing2Fa() && user.getSecret2Fa() != null) {
      // verify 2fa code
      try {
        if (!new Totp(user.getSecret2Fa()).verify(tfaDto.getCode().replaceAll("\\s", ""))) {
          throw new IllegalArgumentException("Incorrect TOTP.");
        }
      } catch (Exception e) {
        return new AjaxResponseBody("401", "auth.user.bad2fa");
      }
    }

    completeUserAuth();
    return new AjaxResponseBody("200", "auth.user.success");

  }

}
