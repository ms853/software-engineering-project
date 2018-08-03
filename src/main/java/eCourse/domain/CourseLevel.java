package ecourse.domain;

public enum CourseLevel {

  BEGINNER("Beginner"), INTERMEDIATE("Intermediate"), ADVANCED("Advanced");

  private final String text;

  private CourseLevel(final String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }

}
