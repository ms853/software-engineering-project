package ecourse.service;

import ecourse.ECourseUserDetails;
import ecourse.domain.User;
import ecourse.dto.SignUpDto;

import org.springframework.security.core.Authentication;

public interface UserService {

  User registerUserFromDto(SignUpDto user) throws IllegalArgumentException;

  boolean verifyUser(long userId, String verificationId);

  boolean isCurrentLoggedInUserVerified();

  boolean isCurrentUserLoggedIn();

  Authentication getCurrentLoggedInUserAuth();

  ECourseUserDetails getCurrentLoggedInUserDetails();

  User getCurrentLoggedInUser();
  
  User getUserById(long id);

}
