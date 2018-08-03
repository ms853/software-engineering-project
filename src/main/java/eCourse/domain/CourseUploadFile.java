package ecourse.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.persistence.Column;

@Entity
@Table(name = "CourseUploadFiles")
public class CourseUploadFile {

  @Id
  @GeneratedValue
  @Column(name = "upload_id")
  private long id;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Course course;

  @Column
  private String fileName;

  @Column
  private String originalFilename;

  @Column
  private String contentType;

  @Column
  private long fileSize;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getOriginalFilename() {
    return originalFilename;
  }

  public void setOriginalFilename(String originalFilename) {
    this.originalFilename = originalFilename;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public long getSize() {
    return fileSize;
  }

  public void setSize(long fileSize) {
    this.fileSize = fileSize;
  }

}
