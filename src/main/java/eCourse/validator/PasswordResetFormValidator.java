package ecourse.validator;

import ecourse.dto.PasswordResetDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PasswordResetFormValidator implements Validator {

  @Autowired
  private PasswordValidator passValidator;

  @Override
  public boolean supports(Class<?> clazz) {
    return PasswordResetDto.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    PasswordResetDto resetDto = (PasswordResetDto) target;

    // check password fields
    passValidator.validate(new PasswordConfirmPair(resetDto.getPassword(), resetDto.getPassword2()),
        errors);

  }

}
