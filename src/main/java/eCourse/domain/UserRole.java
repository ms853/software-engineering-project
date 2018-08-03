package ecourse.domain;

public enum UserRole {

  ADMIN("Admin"), LEARNER("Learner"), TEACHER("Teacher");

  private final String text;

  private UserRole(final String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }

}
