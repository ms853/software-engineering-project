package ecourse.service;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import ecourse.domain.Course;
import ecourse.domain.CourseUploadFile;
import ecourse.exception.StorageException;

public interface CourseUploadFileService {

  void init();

  void save(CourseUploadFile uploadFile, MultipartFile file) throws StorageException;

  CourseUploadFile findById(long uploadId);

  List<CourseUploadFile> findByCourse(Course course);

  Path load(String filename);

  Stream<Path> loadAll();

  Resource loadAsResource(String filename);

  void deleteAll();

  boolean verifyContentType(String contentType);

}
