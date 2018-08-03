package ecourse.validator;

import ecourse.domain.UserRole;
import ecourse.dto.SignUpDto;
import ecourse.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SignUpFormValidator implements Validator {

  @Autowired
  private UserRepository userRepo;
  @Autowired
  private PasswordValidator passValidator;

  @Override
  public boolean supports(Class<?> clazz) {
    return SignUpDto.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    SignUpDto userDto = (SignUpDto) target;

    // check role field
    if (userDto.getUserRole() != UserRole.LEARNER && userDto.getUserRole() != UserRole.TEACHER) {
      // can't register here with that role
      errors.rejectValue("userRole", "Role.invalid");
    }

    // check email fields
    if (!userDto.getEmail().equals(userDto.getEmail2())) {
      // email and confirm email do not match
      errors.rejectValue("email", "Email.duplicate.email");
    } else if (userRepo.findByEmail(userDto.getEmail()) != null) {
      // user already exits
      errors.rejectValue("email", "Email.exists.email");
    }

    // check password fields
    passValidator.validate(new PasswordConfirmPair(userDto.getPassword(), userDto.getPassword2()),
        errors);

  }

}
