package ecourse.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Teachers")
public class Teacher {

  @Id
  @GeneratedValue
  @Column
  private long id;

  @OneToOne(optional = true, fetch = FetchType.LAZY)
  private User teacher;

  @OneToOne(optional = true, fetch = FetchType.LAZY)
  private Course course;
  
  @OneToMany(fetch = FetchType.LAZY)
  private Set<Meetup> meetups = new HashSet<Meetup>();

  public Teacher() {}

  public Course getCourse() {
    return course;
  }

  public long getId() {
    return id;
  }

  public User getTeacher() {
    return teacher;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setTeacher(User teacher) {
    this.teacher = teacher;
  }

  public boolean hasCourse() {
    return course != null;
  }

  public void removeCourse() {
    this.course = null;
  }

}
