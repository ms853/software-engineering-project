package ecourse.repository;

import ecourse.domain.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {

  Teacher findByTeacher_Id(long teacherId);

}
