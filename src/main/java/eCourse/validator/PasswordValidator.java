package ecourse.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PasswordValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return PasswordConfirmPair.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    PasswordConfirmPair passwordPair = (PasswordConfirmPair) target;

    if (!passwordPair.getPassword().equals(passwordPair.getPassword2())) {
      // password and confirm password do not match
      errors.rejectValue("password", "Password.duplicate.password");
    } else if (passwordPair.getPassword().length() < 8
        || !passwordPair.getPassword().matches("^[a-zA-Z0-9]*$")) {
      // password bad format - >= 8 chars and numbers and letters
      errors.rejectValue("password", "Password.bad.format");
    }

  }

}
