package ecourse.service;

import ecourse.domain.Teacher;
import ecourse.domain.User;

public interface TeacherService {

  Iterable<Teacher> findAllTeachers();

  Teacher findByTeacherId(Long id);

  Teacher findByUser(User user);

}
