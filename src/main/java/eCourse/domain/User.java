package ecourse.domain;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Users")
public class User {

  @Id
  @GeneratedValue
  @Column(name = "user_id")
  private long id;

  @NotNull
  @Column(nullable = false)
  private UserRole role;

  @NotNull
  @Column(length = 80, nullable = false)
  private String name;

  @NotNull
  @Column(length = 80, nullable = false, unique = true)
  @JsonView(Views.Public.class)
  private String email;

  @NotNull
  @Column(length = 100, nullable = false)
  private String passwordHash;

  @Column(nullable = false)
  private boolean using2Fa;

  @Column
  private String secret2Fa;

  @NotNull
  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate = new Date();

  @NotNull
  @Column(nullable = false)
  private boolean emailVerified;

  @Column
  private String emailVerificationId;

  @Column
  private String passwordResetId;

  @Column(nullable = false)
  private boolean isDisabled;

  public String getPasswordResetId() {
    return passwordResetId;
  }

  public void setPasswordResetId(String passwordResetId) {
    this.passwordResetId = passwordResetId;
  }

  public String getEmailVerificationId() {
    return emailVerificationId;
  }

  public boolean isEmailVerified() {
    return emailVerified;
  }

  public Date getCreationDate() {
    return new Date(creationDate.getTime());
  }

  public long getId() {
    return id;
  }

  public String getPasswordHash() {
    return passwordHash;
  }

  public UserRole getRole() {
    return role;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmailVerificationId(String verifyId) {
    this.emailVerificationId = verifyId;
  }

  public void setEmailVerified(boolean emailVerified) {
    this.emailVerified = emailVerified;
  }

  public void setCreationDate(Date date) {
    this.creationDate = new Date(date.getTime());
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  public void setRole(UserRole role) {
    this.role = role;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isUsing2Fa() {
    return using2Fa;
  }

  public void setUsing2Fa(boolean using2Fa) {
    this.using2Fa = using2Fa;
  }

  public String getSecret2Fa() {
    return secret2Fa;
  }

  public void setSecret2Fa(String secret2Fa) {
    this.secret2Fa = secret2Fa;
  }

  public User() {}

  /**
   * Creates a new {@link User} object.
   * 
   * @param role The User's {@link UserRole} that is used for permissions.
   * @param name The User's name.
   * @param passwordHash The User's hashed password.
   * @param email The User's contact email address.
   * @param emailVerificationId The User's email verification code
   * @param emailVerificationStatus The User's state of email verification
   */
  public User(UserRole role, String name, String email, String passwordHash,
      String emailVerificationId, boolean emailVerificationStatus) {
    this.role = role;
    this.name = name;
    this.passwordHash = passwordHash;
    this.email = email;
    this.emailVerificationId = emailVerificationId;
    this.emailVerified = emailVerificationStatus;
  }

  public boolean isDisabled() {
    return isDisabled;
  }

  public void setDisabled(boolean isDisabled) {
    this.isDisabled = isDisabled;
  }

}
