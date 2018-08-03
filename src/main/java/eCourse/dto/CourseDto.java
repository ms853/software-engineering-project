package ecourse.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CourseDto {

  @NotNull
  @NotEmpty
  private String name;

  @NotNull
  @NotEmpty
  private String startDate;

  @NotNull
  @NotEmpty
  private String level;

  @Min(1)
  @Max(300)
  private int maxLearners;

  @NotNull
  @NotEmpty
  private String category;

  @NotNull
  @NotEmpty
  @Size(max = 2000)
  private String description;

  public String getName() {
    return name;
  }

  public String getStartDate() {
    return startDate;
  }

  public String getLevel() {
    return level;
  }

  public int getMaxLearners() {
    return maxLearners;
  }

  public String getCategory() {
    return category;
  }

  public String getDescription() {
    return description;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public void setMaxLearners(int maxLearners) {
    this.maxLearners = maxLearners;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public CourseDto() {}

}
