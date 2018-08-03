package ecourse.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import ecourse.domain.Course;
import ecourse.domain.CourseUploadFile;
import ecourse.exception.StorageException;
import ecourse.exception.StorageFileNotFoundException;
import ecourse.repository.CourseUploadFileRepository;
import ecourse.storage.StorageProperties;

@Service
class CourseUploadFileServiceImpl implements CourseUploadFileService {

  private final Path location;

  @Autowired
  private CourseUploadFileRepository uploadFileRepo;

  @Autowired
  public CourseUploadFileServiceImpl(StorageProperties properties) {
    this.location = Paths.get(properties.getLocation());
  }

  @Override
  public void init() {
    try {
      Files.createDirectory(location);
    } catch (IOException e) {
      throw new StorageException("Could not initialize storage folder", e);
    }
  }

  @Override
  public void save(CourseUploadFile uploadFile, MultipartFile file) throws StorageException {
    try {
      if (file.isEmpty()) {
        throw new StorageException("Failed to store empty file " + uploadFile.getFileName());
      }
      Files.copy(file.getInputStream(), this.location.resolve(uploadFile.getFileName()));
    } catch (IOException e) {
      throw new StorageException("Failed to save file " + uploadFile.getFileName(), e);
    }
    uploadFile.setOriginalFilename(file.getOriginalFilename());
    uploadFile.setContentType(file.getContentType());
    uploadFile.setSize(file.getSize());
    uploadFileRepo.save(uploadFile);
  }

  @Override
  public Path load(String filename) {
    return location.resolve(filename);
  }

  @Override
  public Stream<Path> loadAll() {
    try {
      return Files.walk(location, 1).filter(path -> !path.equals(location))
          .map(path -> location.relativize(path));
    } catch (IOException e) {
      throw new StorageException("Failed to load files from storage", e);
    }
  }

  @Override
  public Resource loadAsResource(String filename) {
    try {
      Path file = load(filename);
      Resource resource = new UrlResource(file.toUri());
      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new StorageFileNotFoundException("Could not read file: " + filename);
      }
    } catch (MalformedURLException e) {
      throw new StorageFileNotFoundException("Could not read file: " + filename, e);
    }
  }

  @Override
  public CourseUploadFile findById(long uploadId) {
    return uploadFileRepo.findById(uploadId);
  }

  @Override
  public List<CourseUploadFile> findByCourse(Course course) {
    return uploadFileRepo.findByCourse(course);
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(location.toFile());
  }

  @Override
  public boolean verifyContentType(String contentType) {
    boolean returned = false;
    if (contentType.equals("application/pdf")) {
      returned = true;
    } else if (contentType.equals("application/msword")) {
      returned = true;
    } else if (contentType
        .equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
      returned = true;
    } else if (contentType.equals("application/vnd.ms-excel")) {
      returned = true;
    } else if (contentType.equals("application/vnd.ms-powerpoint")) {
      returned = true;
    } else if (contentType
        .equals("application/vnd.openxmlformats-officedocument.presentationml.presentation")) {
      returned = true;
    } else if (contentType.equals("video/mp4")) {
      returned = true;
    } else if (contentType.equals("application/mp4")) {
      returned = true;
    } else if (contentType.equals("video/quicktime")) {
      returned = true;
    } else if (contentType.equals("video/x-msvideo")) {
      returned = true;
    } else if (contentType.equals("video/x-ms-wmv")) {
      returned = true;
    } else if (contentType.equals("video/x-flv")) {
      returned = true;
    } else if (contentType.equals("text/html")) {
      returned = true;
    } else if (contentType.equals("text/plain")) {
      returned = true;
    } else if (contentType.equals("image/png")) {
      returned = true;
    } else if (contentType.equals("image/jpeg")) {
      returned = true;
    } else if (contentType.equals("image/gif")) {
      returned = true;
    } else if (contentType.equals("image/png")) {
      returned = true;
    }

    return returned;
  }



}
