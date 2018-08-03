package ecourse.security;

import ecourse.ECourseUserDetails;
import ecourse.domain.User;
import ecourse.exception.UnverifiedUserException;
import ecourse.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ECourseAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  UserRepository userRepo;
  @Autowired
  UserDetailsService userDetailsService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    UsernamePasswordAuthenticationToken token =
        (UsernamePasswordAuthenticationToken) authentication;
    ECourseUserDetails userDetails =
        (ECourseUserDetails) userDetailsService.loadUserByUsername(token.getName());

    BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
    if (!pe.matches(token.getCredentials().toString(), userDetails.getPassword())) {
      // passwords do not match
      throw new BadCredentialsException("User credentials not valid.");
    }

    User user = userRepo.findOne(userDetails.getUserId());

    if (user.isDisabled()) {
      // user is disabled
      throw new DisabledException("User is disabled.");
    } else if (!user.isEmailVerified()) {
      // user not verified
      throw new UnverifiedUserException("User is not verified.", user.getId());
    }

    return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
        userDetails.getAuthorities());

  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.equals(authentication);
  }

}
