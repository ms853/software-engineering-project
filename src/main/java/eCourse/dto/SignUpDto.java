package ecourse.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import ecourse.domain.UserRole;

import javax.validation.constraints.NotNull;

public class SignUpDto {

  @NotNull
  @NotEmpty
  @Email
  private String email;

  @NotNull
  @NotEmpty
  @Email
  private String email2;

  @NotNull
  @NotEmpty
  private String password;

  @NotNull
  @NotEmpty
  private String password2;

  @NotNull
  @NotEmpty
  private String name;

  @NotNull
  private UserRole userRole;

  public UserRole getUserRole() {
    return userRole;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public String getPassword2() {
    return password2;
  }

  public void setUserRole(UserRole userRole) {
    this.userRole = userRole;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setEmail2(String email2) {
    this.email2 = email2;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setPassword2(String password2) {
    this.password2 = password2;
  }

  public SignUpDto() {}

}
