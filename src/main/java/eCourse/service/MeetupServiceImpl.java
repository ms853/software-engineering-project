package ecourse.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecourse.domain.Course;
import ecourse.domain.Meetup;
import ecourse.dto.MeetupDto;
import ecourse.repository.CourseRepository;
import ecourse.repository.MeetupRepository;

@Service
class MeetupServiceImpl implements MeetupService {

  @Autowired
  private MeetupRepository meetupRepo;
  @Autowired
  private CourseRepository courseRepo;

  @Override
  public Meetup saveMeetup(MeetupDto meetupDto) {

    Course course = courseRepo.findOne(meetupDto.getCourseId());
    Date scheduledDate = new Date();

    String temp = meetupDto.getLocation().substring(7, meetupDto.getLocation().length());
    String[] location = temp.split(",");
    String latitude = location[0];
    String longitude = location[1].substring(0, location[1].length() - 1);

    try {
      scheduledDate = new SimpleDateFormat("dd/MM/yyyy").parse(meetupDto.getScheduledDate());
    } catch (ParseException ex) {
      System.err.println(ex.getMessage());
    }

    Meetup meetup = new Meetup(course, longitude, latitude, scheduledDate);

    return meetupRepo.save(meetup);
  }

  @Override
  public List<Meetup> getAllMeetups(Course course) {
    return meetupRepo.findByCourse(course);
  }

}
