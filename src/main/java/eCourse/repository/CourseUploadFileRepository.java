package ecourse.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ecourse.domain.Course;
import ecourse.domain.CourseUploadFile;

public interface CourseUploadFileRepository extends CrudRepository<CourseUploadFile, Long> {

  CourseUploadFile findById(long uploadId);

  List<CourseUploadFile> findByCourse(Course course);

}
