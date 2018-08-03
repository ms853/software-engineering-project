package ecourse.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class MeetupDto {

  private long courseId;
  private long teacherId;
  @NotNull
  @NotEmpty
  private String courseName;
  @NotNull
  @NotEmpty
  private String teacherName;
  @NotNull
  @NotEmpty
  private String scheduledDate;
  @NotNull
  @NotEmpty
  private String location;
  
  public long getCourseId() {
    return courseId;
  }

  public String getCourseName() {
    return courseName;
  }

  public String getLocation() {
    return location;
  }
  
  public String getScheduledDate() {
    return scheduledDate;
  }
  
  public long getTeacherId() {
    return teacherId;
  }
  
  public String getTeacherName() {
    return teacherName;
  }
  
  public void setCourseId(long courseId) {
    this.courseId = courseId;
  }
  
  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }
  
  public void setLocation(String location) {
    this.location = location;
  }
  
  public void setScheduledDate(String scheduledDate) {
    this.scheduledDate = scheduledDate;
  }
  
  public void setTeacherId(long teacherId) {
    this.teacherId = teacherId;
  }
  
  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }
  
}
