package ecourse.dto;

import javax.validation.constraints.Size;

public class ReviewDto {

  @Size(max = 2000)
  private String comment;

  @Size(max = 3)
  private String structureRating = "0";

  @Size(max = 3)
  private String difficultyRating = "0";

  @Size(max = 3)
  private String supportRating = "0";

  @Size(max = 3)
  private String overallRating = "0";

  public String getComment() {
    return comment;
  }

  public String getDifficultyRating() {
    return difficultyRating;
  }

  public String getOverallRating() {
    return overallRating;
  }

  public String getStructureRating() {
    return structureRating;
  }

  public String getSupportRating() {
    return supportRating;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public void setDifficultyRating(String difficultyRating) {
    this.difficultyRating = difficultyRating;
  }

  public void setOverallRating(String overallRating) {
    this.overallRating = overallRating;
  }

  public void setStructureRating(String structureRating) {
    this.structureRating = structureRating;
  }

  public void setSupportRating(String supportRating) {
    this.supportRating = supportRating;
  }

  public ReviewDto() {}
}
