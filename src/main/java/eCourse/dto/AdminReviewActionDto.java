package ecourse.dto;

public class AdminReviewActionDto {

  private long courseId;
  private long reviewId;

  public long getCourseId() {
    return courseId;
  }

  public void setCourseId(long courseId) {
    this.courseId = courseId;
  }

  public long getReviewId() {
    return reviewId;
  }

  public void setReviewId(long reviewId) {
    this.reviewId = reviewId;
  }


}
