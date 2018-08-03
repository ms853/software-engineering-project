package ecourse.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Learners")
public class Learner {

  @Id
  @GeneratedValue
  @Column
  private long id;

  @OneToOne(optional = true, fetch = FetchType.LAZY)
  private User learner;

  @OneToOne(optional = true, fetch = FetchType.LAZY)
  private Course course;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Learner> friends = new HashSet<Learner>();
  
  @OneToOne(optional = true, fetch = FetchType.LAZY)
  private Course inviteCourse;

  public Learner() {}

  public Learner(Course course, User learner) {
    this.course = course;
    this.learner = learner;
  }

  public void addFriend(Learner friend) {
    this.friends.add(friend);
  }

  public Course getCourse() {
    return course;
  }

  public Learner getFriend(long id) {
    Learner returned = null;
    for (Iterator<Learner> it = friends.iterator(); it.hasNext();) {
      Learner friend = it.next();
      if (friend.getId() == id) {
        returned = friend;
        break;
      }
    }
    return returned;
  }

  public Set<Learner> getFriends() {
    return this.friends;
  }

  public long getId() {
    return this.id;
  }

  public Course getInvitedCourse() {
    return inviteCourse;
  }

  public User getLearner() {
    return learner;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public void setFriends(Set<Learner> friends) {
    this.friends = friends;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setInviteCourse(Course inviteCourse) {
    this.inviteCourse = inviteCourse;
  }

  public void setLearner(User learner) {
    this.learner = learner;
  }

}
