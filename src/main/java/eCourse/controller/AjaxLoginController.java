package ecourse.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.WebAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ecourse.AjaxResponseBody;

@RestController
public class AjaxLoginController {

  /**
   * Handles failed logins.
   * 
   * @param request The request.
   * @return Response.
   */
  @RequestMapping(value = "/error-login", method = RequestMethod.GET)
  public AjaxResponseBody invalidLogin(HttpServletRequest request) {

    final AjaxResponseBody authResponse = (AjaxResponseBody) request.getSession()
        .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    if (authResponse == null) {
      return new AjaxResponseBody("401", "Login auth failed with no response.");
    }

    request.getSession().removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    return authResponse;

  }

}
