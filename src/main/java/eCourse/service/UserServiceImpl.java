package ecourse.service;

import ecourse.ECourseUserDetails;
import ecourse.domain.Course;
import ecourse.domain.CourseLevel;
import ecourse.domain.Learner;
import ecourse.domain.Review;
import ecourse.domain.Teacher;
import ecourse.domain.User;
import ecourse.domain.UserRole;
import ecourse.dto.SignUpDto;
import ecourse.repository.CourseRepository;
import ecourse.repository.LearnerRepository;
import ecourse.repository.ReviewRepository;
import ecourse.repository.TeacherRepository;
import ecourse.repository.UserRepository;

import org.jboss.aerogear.security.otp.api.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CourseRepository courseRepo;
	@Autowired
	private LearnerRepository learnerRepo;
	@Autowired
	private TeacherRepository teacherRepo;
	@Autowired
	private ReviewRepository reviewRepo;

	@Override
	public User registerUserFromDto(SignUpDto user) throws IllegalArgumentException {
		// create new user from the DTO info
		User newUser = new User();
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());

		// generate new password hash
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		newUser.setPasswordHash(pe.encode(user.getPassword()));

		// generate 2fa secret
		newUser.setSecret2Fa(Base32.random());
		newUser.setUsing2Fa(true);

		// generate new verification id
		newUser.setEmailVerificationId(java.util.UUID.randomUUID().toString());
		newUser.setEmailVerified(false);

		// role-specific configuration
		switch (user.getUserRole()) {
		case TEACHER:
			newUser.setRole(UserRole.TEACHER);
			newUser = userRepo.save(newUser);
			Teacher teacher = new Teacher();
			teacher.setTeacher(newUser);
			teacherRepo.save(teacher);
			break;

		case LEARNER:
			newUser.setRole(UserRole.LEARNER);
			newUser = userRepo.save(newUser);
			Learner learner = new Learner();
			learner.setLearner(newUser);
			learnerRepo.save(learner);
			break;

		default:
			throw new IllegalArgumentException("User DTO to register has unsupported role!");
		}

		return newUser;
	}

	@PostConstruct
	private void iniDataForTesting() {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();

		final User user = new User(UserRole.TEACHER, "Teacher", "teacher@gmail.com", pe.encode("pass"), null, true);
		final User user1 = new User(UserRole.LEARNER, "Learner", "learner@gmail.com", pe.encode("pass"), null, true);
		final User user2 = new User(UserRole.TEACHER, "Goku", "goku@gmail.com", pe.encode("pass"), null, true);
		final User user3 = new User(UserRole.LEARNER, "Learner2", "learner2@gmail.com", pe.encode("pass"), null, true);
		final User user4 = new User(UserRole.TEACHER, "Teacher2", "teacher2@gmail.com", pe.encode("pass"), null, true);
		final User user5 = new User(UserRole.LEARNER, "Unverif", "unverif@gmail.com", pe.encode("pass"), "abc", false);
		final User user6 = new User(UserRole.LEARNER, "2FA", "2faunverif@gmail.com", pe.encode("pass"), "abc", false);
		user6.setUsing2Fa(true);
		user6.setSecret2Fa("abcdefghijklmnopqrs");
		final User user7 = new User(UserRole.LEARNER, "2FA", "2fa@gmail.com", pe.encode("pass"), null, true);
		user7.setUsing2Fa(true);
		user7.setSecret2Fa("abcdefghijklmnopqrs");
		final User user8 = new User(UserRole.TEACHER, "Teacher3", "teacher3@gmail.com", pe.encode("pass"), null, true);
		final User user9 = new User(UserRole.ADMIN, "Admin", "admin@gmail.com", pe.encode("pass"), null, true);

		userRepo.save(user);
		userRepo.save(user1);
		userRepo.save(user2);
		userRepo.save(user3);
		userRepo.save(user4);
		userRepo.save(user5);
		userRepo.save(user6);
		userRepo.save(user7);
		userRepo.save(user8);
		userRepo.save(user9);

		Course course = new Course("Computer science", "Information Technology",
				"The Computer Science covers the scientific and theoretical foundations of the subject. "
				+ "You will also learn about logic and scientific problem solving which will help you tackle the challenges "
				+ "of the workplace in a truly organised way. A wide range of third year option modules typically "
				+ "include algorithms and security, cryptography, compression methods for multimedia, advanced "
				+ "web technologies and concurrent processes. ",
				CourseLevel.BEGINNER, 2, user);
		course.addLearner(user1);
		courseRepo.save(course);

		Course course2 = new Course("Mathematics", "Mathematics", "Another new course", CourseLevel.BEGINNER, 15,
				user4);
		courseRepo.save(course2);

		Course course3 = new Course("Physics", "Science", "This is a difficult course", CourseLevel.BEGINNER, 15,
				user4);
		courseRepo.save(course3);

		Course course4 = new Course("Chemistry", "Science", "All about that chemistry", CourseLevel.BEGINNER, 15,
				user4);
		courseRepo.save(course4);

		Course course5 = new Course("Biology", "Science", "Some biology is necessary", CourseLevel.BEGINNER, 15, user4);
		courseRepo.save(course5);

		Course course6 = new Course("Business", "Business", "How the world operates", CourseLevel.BEGINNER, 15, user4);
		courseRepo.save(course6);

		Course course7 = new Course("Business and Management", "Business", "Lets manage that business",
				CourseLevel.BEGINNER, 15, user4);
		courseRepo.save(course7);

		Course course8 = new Course("Accounting", "Business", "New level of financing", CourseLevel.BEGINNER, 15,
				user4);
		courseRepo.save(course8);

		Learner learner = new Learner(course, user1);
		Learner learner2 = new Learner(course, user3);
		Learner learner3 = new Learner(null, user5);
		Learner learner4 = new Learner(null, user6);
		Learner learner5 = new Learner(null, user7);

		learnerRepo.save(learner);
		learnerRepo.save(learner2);
		learnerRepo.save(learner3);
		learnerRepo.save(learner4);
		learnerRepo.save(learner5);

		Teacher teacher = new Teacher();
		teacher.setTeacher(user);
		teacher.setCourse(course);

		Teacher teacher2 = new Teacher();
		teacher2.setTeacher(user4);
		teacher2.setCourse(course2);

		Teacher teacher3 = new Teacher();
		teacher3.setTeacher(user2);

		Teacher teacher4 = new Teacher();
		teacher4.setTeacher(user8);

		teacherRepo.save(teacher);
		teacherRepo.save(teacher2);
		teacherRepo.save(teacher3);
		teacherRepo.save(teacher4);

		// -------Review------//

		Review newReview = new Review();

		newReview.setComment("This course is really good");
		newReview.setStructureRating(4.5f);
		newReview.setDifficultyRating(3.5f);
		newReview.setSupportRating(3.0f);
		newReview.setOverallRating(4.0f);
		newReview.setLearner(user1);
		newReview.setCourse(course);

		reviewRepo.save(newReview);

		Review newReview2 = new Review();

		newReview2.setComment("This course is really bad");
		newReview2.setStructureRating(1.5f);
		newReview2.setDifficultyRating(3.5f);
		newReview2.setSupportRating(1.5f);
		newReview2.setOverallRating(3.0f);
		newReview2.setLearner(user3);
		newReview2.setCourse(course);

		reviewRepo.save(newReview2);

	}

	@Override
	public Authentication getCurrentLoggedInUserAuth() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@Override
	public boolean isCurrentUserLoggedIn() {
		return !(getCurrentLoggedInUserAuth() instanceof AnonymousAuthenticationToken);
	}

	@Override
	public ECourseUserDetails getCurrentLoggedInUserDetails() {
		return isCurrentUserLoggedIn() ? (ECourseUserDetails) getCurrentLoggedInUserAuth().getPrincipal() : null;
	}

	@Override
	public User getCurrentLoggedInUser() {
		return isCurrentUserLoggedIn() ? userRepo.findOne(getCurrentLoggedInUserDetails().getUserId()) : null;
	}

	@Override
	public boolean isCurrentLoggedInUserVerified() {
		User user = getCurrentLoggedInUser();
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		return user.isEmailVerified();
	}

	/**
	 * Verifies a user account.
	 * 
	 * @param userId
	 *            The user's ID
	 * @param verificationId
	 *            The verification token
	 * @return True if the user has now been verified after previously being
	 *         unverified. False otherwise.
	 */
	@Override
	public boolean verifyUser(long userId, String verificationId) {
		User user = userRepo.findOne(userId);
		if (user == null || user.isEmailVerified()) {
			return false;
		}

		if (user.getEmailVerificationId().equals(verificationId)) {
			user.setEmailVerificationId(null);
			user.setEmailVerified(true);
			userRepo.save(user);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public User getUserById(long id) {
		return userRepo.findOne(id);
	}

}
