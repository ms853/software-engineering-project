package ecourse.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Courses")
public class Course {

  @Id
  @GeneratedValue
  @Column(name = "course_id")
  private long id;

  @NotNull
  @Column(length = 100, nullable = false)
  private String name;

  // TODO: Shouldn't this be an enumeration of possible categories instead?
  // Or allow teachers to define their own categories...
  @NotNull
  @Column(length = 50, nullable = false)
  private String category;

  @NotNull
  @Column(length = 2000, nullable = false)
  private String description;

  @NotNull
  @Column(nullable = false)
  private CourseLevel level;

  @Min(0)
  @Max(300)
  @Column(nullable = false)
  private int maxLearners;

  @NotNull
  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date startDate = new Date();

  @NotNull
  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate = new Date();

  @OneToOne(optional = true, fetch = FetchType.LAZY)
  private User teacher;

  // @OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
  // private DiscussionBoard discussionBoard = new DiscussionBoard();

  @ManyToMany(fetch = FetchType.LAZY)
  private Set<User> learners = new HashSet<User>();

  @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL,
      orphanRemoval = true)
  private Set<CoursePage> coursePages = new HashSet<CoursePage>();

  @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL,
      orphanRemoval = true)
  private List<CourseUploadFile> uploadFiles = new ArrayList<CourseUploadFile>();

  @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL,
      orphanRemoval = true)
  private Set<Review> reviews = new HashSet<Review>();

  // @OneToMany(mappedBy="course", fetch=FetchType.LAZY, cascade=CascadeType.ALL,
  // orphanRemoval=true)
  // private List<Meetup> meetups = new ArrayList<Meetup>();

  // @OneToMany(mappedBy="course", fetch=FetchType.LAZY, cascade=CascadeType.ALL,
  // orphanRemoval=true)
  // private Set<Test> tests = new HashSet<Test>();

  public Set<Review> getReviews() {
    return reviews;
  }

  public void setReviews(Set<Review> reviews) {
    this.reviews = reviews;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCategory(String field) {
    this.category = field;
  }

  public void setLevel(CourseLevel level) {
    this.level = level;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setStartDate(Date startDate) {
    this.startDate = new Date(startDate.getTime());
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = new Date(creationDate.getTime());
  }

  public void setTeacher(User teacher) {
    this.teacher = teacher;
  }

  public void setLearners(Set<User> learners) {
    this.learners = learners;
  }

  public void setMaxLearners(int maxLearners) {
    this.maxLearners = maxLearners;
  }

  public void setCoursePages(Set<CoursePage> coursePages) {
    this.coursePages = coursePages;
  }

  public void setCourseUploadFiles(List<CourseUploadFile> uploadFiles) {
    this.uploadFiles = uploadFiles;
  }

  // public void setMeetups(List<Meetup> meetups) {
  // this.meetups = meetups;
  // }
  //
  // public void setDiscussionBoard(DiscussionBoard discussionBoard) {
  // this.discussionBoard = discussionBoard;
  // }
  //
  // public void setTests(Set<Test> tests) {
  // this.tests = tests;
  // }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCategory() {
    return category;
  }

  public CourseLevel getLevel() {
    return level;
  }

  public String getDescription() {
    return description;
  }

  public Date getStartDate() {
    return new Date(startDate.getTime());
  }

  public Date getCreationDate() {
    return new Date(creationDate.getTime());
  }

  public User getTeacher() {
    return teacher;
  }

  public Set<User> getLearners() {
    return learners;
  }

  public int getMaxLearners() {
    return maxLearners;
  }

  public Set<CoursePage> getCoursePages() {
    return coursePages;
  }

  public List<CourseUploadFile> getCourseUploadFiles() {
    return uploadFiles;
  }

  // public List<Meetup> getMeetups() {
  // return meetups;
  // }
  //
  // public DiscussionBoard getDiscussionBoard() {
  // return discussionBoard;
  // }
  //
  // public Set<Test> getTests() {
  // return tests;
  // }

  public Course() {}

  /**
   * Create a new {@link Course} object.
   * 
   * @param name The name of the Course.
   * @param category The Course's category.
   * @param description A general description for the Course's contents.
   * @param level The difficulty {@link CourseLevel} of the Course.
   * @param maxLearners The maximum amount of {@link User}s that can be enrolled on this Course at
   *        any given time.
   * @param teacher The teacher {@link User} for this Course.
   */
  public Course(String name, String category, String description, CourseLevel level,
      int maxLearners, User teacher) {
    this.name = name;
    this.category = category;
    this.description = description;
    this.level = level;
    this.maxLearners = maxLearners;
    this.teacher = teacher;
  }

  public void addLearner(User learner) {
    this.learners.add(learner);
  }

}
