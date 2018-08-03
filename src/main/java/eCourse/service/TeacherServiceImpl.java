package ecourse.service;

import ecourse.domain.Teacher;
import ecourse.domain.User;
import ecourse.repository.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class TeacherServiceImpl implements TeacherService {

  @Autowired
  private TeacherRepository teacherRepo;

  @Override
  public Iterable<Teacher> findAllTeachers() {
    return teacherRepo.findAll();
  }

  @Override
  public Teacher findByTeacherId(Long id) {
    return teacherRepo.findByTeacher_Id(id);
  }

  @Override
  public Teacher findByUser(User user) {
    return findByTeacherId(user.getId());
  }

}
