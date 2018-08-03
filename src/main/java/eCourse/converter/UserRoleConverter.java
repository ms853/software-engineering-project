package ecourse.converter;

import ecourse.domain.UserRole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole, String> {

  @Override
  public String convertToDatabaseColumn(UserRole role) {
    switch (role) {
      case ADMIN:
        return "A";
      case TEACHER:
        return "T";
      case LEARNER:
        return "L";
      default:
        throw new IllegalArgumentException("Unknown value " + role);
    }
  }

  @Override
  public UserRole convertToEntityAttribute(String dbData) {
    switch (dbData) {
      case "A":
        return UserRole.ADMIN;
      case "T":
        return UserRole.TEACHER;
      case "L":
        return UserRole.LEARNER;
      default:
        throw new IllegalArgumentException("Unknown value " + dbData);
    }
  }

}
