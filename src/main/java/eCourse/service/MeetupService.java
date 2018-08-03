package ecourse.service;

import java.util.List;

import ecourse.domain.Course;
import ecourse.domain.Meetup;
import ecourse.dto.MeetupDto;

public interface MeetupService {
  
  Meetup saveMeetup(MeetupDto meetupDto);
  List<Meetup> getAllMeetups(Course course);

}
