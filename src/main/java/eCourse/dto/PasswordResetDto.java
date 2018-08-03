package ecourse.dto;

public class PasswordResetDto {

  private long userId;
  private String passwordResetId;
  private String password;
  private String password2;

  public PasswordResetDto() {}

  public PasswordResetDto(long userId, String passwordResetId) {
    this.userId = userId;
    this.passwordResetId = passwordResetId;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getPasswordResetId() {
    return passwordResetId;
  }

  public void setPasswordResetId(String passwordResetId) {
    this.passwordResetId = passwordResetId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword2() {
    return password2;
  }

  public void setPassword2(String password2) {
    this.password2 = password2;
  }

}
