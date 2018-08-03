package ecourse.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CoursePages")
public class CoursePage {

  @Id
  @GeneratedValue
  @Column(name = "cPage_id")
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Course course;

  public void setId(long id) {
    this.id = id;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public long getId() {
    return id;
  }

  public Course getCourse() {
    return course;
  }

  public CoursePage() {}

  public CoursePage(Course course) {
    this.course = course;
  }

}
