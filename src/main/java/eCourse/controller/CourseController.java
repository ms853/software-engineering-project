package ecourse.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ecourse.domain.Course;
import ecourse.domain.Review;
import ecourse.domain.User;
import ecourse.dto.AdminReviewActionDto;
import ecourse.dto.ReviewDto;
import ecourse.exception.SpringException;
import ecourse.repository.ReviewRepository;
import ecourse.service.CourseService;
import ecourse.service.CourseUploadFileService;
import ecourse.service.LearnerService;
import ecourse.service.ReviewService;
import ecourse.service.UserService;
import ecourse.validator.ReviewValidator;

@Controller
public class CourseController {

	@Autowired
	private UserService userService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private ReviewRepository reviewRepo;

	@Autowired
	private CourseUploadFileService uploadService;

	@Autowired
	private ReviewValidator validator;

	@Autowired
	private LearnerService learnerService;

	// ------------------- Unauthorised users ----------------------//

	/**
	 * The course info page for unauthorised users.
	 * 
	 * @param model
	 *            The model.
	 * @param courseId
	 *            The course ID.
	 * @return The view.
	 */
	@RequestMapping(value = "/courses/course/{courseId}", method = RequestMethod.GET)
	public String courseHome(Model model, @PathVariable long courseId) {
		Course course = courseService.findById(courseId);
		model.addAttribute("averageReviews", reviewService.calculateAverageOfReviews(course));
		model.addAttribute("allReviews", course.getReviews());
		model.addAttribute("course", course);
		model.addAttribute("user", true);
		return "form/course-home";
	}

	// -------------------------- Teacher --------------------------//

	/**
	 * The course info page for teachers.
	 * 
	 * @param model
	 *            The model.
	 * @param courseId
	 *            The course ID.
	 * @return The view.
	 */
	@RequestMapping(value = "/teacher/course/{courseId}", method = RequestMethod.GET)
	public String teacherHome(Model model, @PathVariable long courseId) {
		Course course = courseService.findById(courseId);
		System.out.println("Course: " + course.getName() + " | Course ID: " + course.getId());
		model.addAttribute("averageReviews", reviewService.calculateAverageOfReviews(course));
		model.addAttribute("allReviews", course.getReviews());
		model.addAttribute("course", course);
		model.addAttribute("learners", learnerService.findByCourse(courseId));
		model.addAttribute("teacher", true);
		// load all the file paths
		model.addAttribute("files",
				uploadService.loadAll()
						.map(path -> MvcUriComponentsBuilder.fromMethodName(CourseController.class, "serveFile",
								courseId, path.getFileName().toString()).build().toString())
						.collect(Collectors.toList()));
		return "form/course-home";
	}

	@RequestMapping(value = "/teacher/course/report-review", method = RequestMethod.GET)
	public String teacherReportReview(Model model, @RequestParam(value = "id", required = true) long reviewId,
			RedirectAttributes redirectAttributes) {
		Review review = reviewRepo.findOne(reviewId);
		if (review == null) {
			return "redirect:/";
		}

		review.setReported(true);
		review = reviewRepo.save(review);
		redirectAttributes.addFlashAttribute("reported", "yes");
		return "redirect:/teacher/course/" + review.getCourse().getId();
	}

	// -------------------------- Admin --------------------------//

	/**
	 * The course info page for admins.
	 * 
	 * @param model
	 *            The model.
	 * @param courseId
	 *            The course ID.
	 * @return The view.
	 */
	@RequestMapping(value = "/admin/course/{courseId}", method = RequestMethod.GET)
	public String adminHome(Model model, @PathVariable long courseId) {
		Course course = courseService.findById(courseId);
		System.out.println("Course: " + course.getName() + " | Course ID: " + course.getId());
		model.addAttribute("averageReviews", reviewService.calculateAverageOfReviews(course));
		model.addAttribute("allReviews", course.getReviews());
		model.addAttribute("course", course);
		model.addAttribute("learners", learnerService.findByCourse(courseId));
		model.addAttribute("admin", true);
		// load all the file paths
		model.addAttribute("files",
				uploadService.loadAll()
						.map(path -> MvcUriComponentsBuilder.fromMethodName(CourseController.class, "serveFile",
								courseId, path.getFileName().toString()).build().toString())
						.collect(Collectors.toList()));
		model.addAttribute("delete", new AdminReviewActionDto());
		return "form/course-home";
	}

	// -------------------------- Learner --------------------------//

	/**
	 * The course info page for learners.
	 * 
	 * @param model
	 *            The model.
	 * @param courseId
	 *            The course ID.
	 * @return The view.
	 */
	@RequestMapping(value = "/learner/course/{courseId}", method = RequestMethod.GET)
	public String learnerHome(Model model, @PathVariable long courseId) {
		User user = userService.getCurrentLoggedInUser();
		if (learnerService.findByUser(user).getCourse() != null) {
			model.addAttribute("learnerEnrolled", true);
		}
		Course course = courseService.findById(courseId);
		if (learnerService.findByUser(user).getCourse() == course) {
			model.addAttribute("homeCourse", true);
			// load all the file paths
			if (!course.getCourseUploadFiles().isEmpty()) {
				model.addAttribute("files",
						uploadService.loadAll()
								.map(path -> MvcUriComponentsBuilder.fromMethodName(CourseController.class, "serveFile",
										courseId, path.getFileName().toString()).build().toString())
								.collect(Collectors.toList()));
			}
		}
		model.addAttribute("averageReviews", reviewService.calculateAverageOfReviews(course));
		model.addAttribute("allReviews", course.getReviews());
		model.addAttribute("course", course);
		model.addAttribute("learners", learnerService.findByCourse(courseId));
		model.addAttribute("learner", true);
		model.addAttribute("thisLearner", user);
		return "form/course-home";
	}

	/**
	 * Course review page for learners.
	 * 
	 * @param model
	 *            The model.
	 * @param courseId
	 *            The course ID.
	 * @return The view.
	 */
	@RequestMapping(value = "/learner/course/{courseId}/review", method = RequestMethod.GET)
	public String learnerReviewCourse(Model model, @PathVariable long courseId) {
		Course course = courseService.findById(courseId);
		if (courseService.findLearnerInReviews(course)) {
			model.addAttribute("url", "/");
			model.addAttribute("exception", new SpringException("You have already reviewed this course."));
			return "security/error-page";
		}
		model.addAttribute("course", course);
		model.addAttribute("review", new ReviewDto());
		return "form/review";
	}

	/**
	 * Course review submit.
	 * 
	 * @param reviewDto
	 *            The review form.
	 * @param result
	 *            The validation result.
	 * @param courseId
	 *            The course ID.
	 * @param model
	 *            The model.
	 * @return The view.
	 */
	@RequestMapping(value = "/learner/course/{courseId}/review", method = RequestMethod.POST)
	public String learnerAddReviewOfCourse(@Valid @ModelAttribute("review") ReviewDto reviewDto, BindingResult result,
			@PathVariable long courseId, Model model) {

		// validate the form (on the server-side now)
		validator.validate(reviewDto, result);

		Course course = courseService.findById(courseId);
		if (result.hasFieldErrors("comment")) {
			// form has validation errors
			model.addAttribute("course", course);
			model.addAttribute("review", reviewDto);
			return "form/review";
		} else {

			if (result.hasFieldErrors("structureRating")) {
				reviewDto.setStructureRating("0");
			}

			if (result.hasFieldErrors("difficultyRating")) {
				reviewDto.setDifficultyRating("0");
			}

			if (result.hasFieldErrors("supportRating")) {
				reviewDto.setSupportRating("0");
			}

			if (result.hasFieldErrors("overallRating")) {
				reviewDto.setOverallRating("0");
			}

			if ((reviewDto.getComment() == null || reviewDto.getComment().equals(""))
					&& reviewDto.getStructureRating().equals("0") && reviewDto.getDifficultyRating().equals("0")
					&& reviewDto.getSupportRating().equals("0") && reviewDto.getOverallRating().equals("0")) {
				model.addAttribute("url", "/learner/course/" + course.getId() + "/review");
				model.addAttribute("exception", new SpringException("You did not review this course."));
				return "security/error-page";
			}

			// save the review of the course
			reviewService.saveReviewFromReviewDto(reviewDto, course);
		}

		return "redirect:/";
	}

	@RequestMapping(value = "/learner/course/report-review", method = RequestMethod.GET)
	public String learnerReportReview(Model model, @RequestParam(value = "id", required = true) long reviewId,
			RedirectAttributes redirectAttributes) {
		Review review = reviewRepo.findOne(reviewId);
		if (review == null) {
			return "redirect:/";
		}

		review.setReported(true);
		review = reviewRepo.save(review);
		redirectAttributes.addFlashAttribute("reported", "yes");
		return "redirect:/learner/course/" + review.getCourse().getId();
	}

	/**
	 * Gets a file.
	 * 
	 * @param courseId
	 *            The ID of the course.
	 * @param filename
	 *            The name of the file.
	 * @return A response with the file's content.
	 */
	@RequestMapping("/{courseId}/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable long courseId, @PathVariable String filename) {
		Resource file = uploadService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

}
