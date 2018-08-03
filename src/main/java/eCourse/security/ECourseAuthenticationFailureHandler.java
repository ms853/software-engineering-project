package ecourse.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import ecourse.AjaxResponseBody;
import ecourse.exception.UnverifiedUserException;

@Component
public class ECourseAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {

    setDefaultFailureUrl("/error-login");
    super.onAuthenticationFailure(request, response, exception);

    // The response to give.
    AjaxResponseBody authResponse = new AjaxResponseBody("401", "auth.failed");

    if (exception.getClass().isAssignableFrom(BadCredentialsException.class)
        || exception.getClass().isAssignableFrom(UsernameNotFoundException.class)) {
      // bad credentials
      authResponse.setMsg("auth.badcredentials");
    } else if (exception.getClass().isAssignableFrom(UnverifiedUserException.class)) {
      // email unverified
      authResponse.setMsg("auth.user.unverified");
      authResponse.setResult(String.valueOf(((UnverifiedUserException) exception).getUserId()));
    } else if (exception.getClass().isAssignableFrom(DisabledException.class)) {
      // account disabled
      authResponse.setMsg("auth.user.disabled");
    }

    request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, authResponse);

  }

}
