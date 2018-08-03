package ecourse.repository;

import org.springframework.data.repository.CrudRepository;

import ecourse.domain.Meetup;
import ecourse.domain.Course;
import java.util.List;

public interface MeetupRepository extends CrudRepository<Meetup, Long> {

  List<Meetup> findByCourse(Course course);
  
}
