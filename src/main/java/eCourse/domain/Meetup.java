package ecourse.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Meetups")
public class Meetup {

  @Id
  @GeneratedValue
  @Column(name = "meetup_id")
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Course course;

  @NotNull
  @Column(nullable = false)
  private String longitude;

  @NotNull
  @Column(nullable = false)
  private String latitude;

  @NotNull
  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date scheduledDate;

  public Meetup() {}

  public Meetup(Course course, String longitude, String latitude, Date scheduledDate) {
    this.course = course;
    this.longitude = longitude;
    this.latitude = latitude;
    this.scheduledDate = new Date(scheduledDate.getTime());
  }

  public Course getCourse() {
    return course;
  }

  public long getId() {
    return id;
  }

  public String getLatitude() {
    return latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public Date getScheduledDate() {
    return new Date(scheduledDate.getTime());
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public void setScheduledDate(Date scheduledDate) {
    this.scheduledDate = new Date(scheduledDate.getTime());
  }

}
