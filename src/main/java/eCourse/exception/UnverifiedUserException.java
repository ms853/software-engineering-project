package ecourse.exception;

import org.springframework.security.core.AuthenticationException;

public class UnverifiedUserException extends AuthenticationException {

  private static final long serialVersionUID = -471706760122885381L;

  private long userId;

  public UnverifiedUserException(String msg, long userId) {
    super(msg);
    this.setUserId(userId);
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


}
