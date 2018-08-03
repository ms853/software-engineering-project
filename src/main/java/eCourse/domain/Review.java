package ecourse.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Reviews")
public class Review {

  @Id
  @GeneratedValue
  @Column(name = "review_id")
  private long id;

  @Column(length = 2000)
  private String comment;

  @Column(nullable = false)
  private float structureRating;

  @Column(nullable = false)
  private float difficultyRating;

  @Column(nullable = false)
  private float supportRating;

  @Column(nullable = false)
  private float overallRating;

  @ManyToOne(fetch = FetchType.LAZY)
  private Course course;

  @ManyToOne(optional = true, fetch = FetchType.LAZY)
  private User learner;
  
  @Column(nullable = false)
  private boolean reported;

  public User getLearner() {
    return learner;
  }

  public void setLearner(User learner) {
    this.learner = learner;
  }

  public String getComment() {
    return comment;
  }

  public float getDifficultyRating() {
    return difficultyRating;
  }

  public long getId() {
    return id;
  }

  public float getOverallRating() {
    return overallRating;
  }

  public float getStructureRating() {
    return structureRating;
  }

  public float getSupportRating() {
    return supportRating;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public void setDifficultyRating(float difficultyRating) {
    this.difficultyRating = difficultyRating;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setOverallRating(float overallRating) {
    this.overallRating = overallRating;
  }

  public void setStructureRating(float structureRating) {
    this.structureRating = structureRating;
  }

  public void setSupportRating(float supportRating) {
    this.supportRating = supportRating;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public boolean isReported() {
    return reported;
  }

  public void setReported(boolean reported) {
    this.reported = reported;
  }

}
