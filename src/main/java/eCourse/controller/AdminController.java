package ecourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ecourse.domain.Course;
import ecourse.domain.Review;
import ecourse.domain.User;
import ecourse.dto.AdminReviewActionDto;
import ecourse.dto.AdminUserActionDto;
import ecourse.exception.SpringException;
import ecourse.repository.CourseRepository;
import ecourse.repository.ReviewRepository;
import ecourse.repository.UserRepository;
import ecourse.service.LearnerService;
import ecourse.service.TeacherService;

@Controller
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  UserRepository userRepo;

  @Autowired
  LearnerService learnerService;

  @Autowired
  TeacherService teacherService;

  @Autowired
  CourseRepository courseRepo;

  @Autowired
  ReviewRepository reviewRepo;

  @RequestMapping(method = RequestMethod.GET)
  public String admin(Model model) {
    model.addAttribute("allReviews", reviewRepo.findByReported(true));
    model.addAttribute("delete", new AdminReviewActionDto());
    return "form/admin";
  }

  @RequestMapping(value = "/user-search", method = RequestMethod.GET)
  public String adminUserSearch(@RequestParam(value = "search", defaultValue = "") String search,
      @RequestParam(value = "page", defaultValue = "0") int page, Model model) {
    final PageRequest request = new PageRequest(page, 5);

    Page<User> users = null;
    if (StringUtils.isEmpty(search)) {
      users = userRepo.findAll(request);
    } else {
      users = userRepo.findUsers(search, request);
    }

    model.addAttribute("search", search);
    model.addAttribute("userPage", users);
    return "form/admin-user-search";
  }

  @RequestMapping(value = "/user", method = RequestMethod.GET)
  public String adminUserDetails(@RequestParam(value = "id", required = true) long userId,
      Model model) {
    User user = userRepo.findOne(userId);
    if (user == null) {
      model.addAttribute("url", "/admin/user-search");
      model.addAttribute("exception", new SpringException("User does not exist."));
      return "security/error-page";
    }

    model.addAttribute("user", user);
    model.addAttribute("learner", learnerService.findByUser(user));
    model.addAttribute("teacher", teacherService.findByUser(user));
    model.addAttribute("enable", new AdminUserActionDto(user.getId()));
    model.addAttribute("disable", new AdminUserActionDto(user.getId()));
    model.addAttribute("delete", new AdminUserActionDto(user.getId()));
    return "form/admin-user-details";
  }

  @RequestMapping(value = "/user-disable", method = RequestMethod.POST)
  public String adminUserDisable(@ModelAttribute(value = "disable") AdminUserActionDto dto,
      Model model) {
    User user = userRepo.findOne(dto.getId());
    if (user == null) {
      model.addAttribute("url", "/admin/user-search");
      model.addAttribute("exception", new SpringException("User does not exist."));
      return "security/error-page";
    }

    user.setDisabled(true);
    user = userRepo.save(user);
    return "redirect:/admin/user?id=" + user.getId();
  }

  @RequestMapping(value = "/user-enable", method = RequestMethod.POST)
  public String adminUserEnable(@ModelAttribute(value = "enable") AdminUserActionDto dto,
      Model model) {
    User user = userRepo.findOne(dto.getId());
    if (user == null) {
      model.addAttribute("url", "/admin/user-search");
      model.addAttribute("exception", new SpringException("User does not exist."));
      return "security/error-page";
    }

    user.setDisabled(false);
    user = userRepo.save(user);
    return "redirect:/admin/user?id=" + user.getId();
  }

  @RequestMapping(value = "/review-delete", method = RequestMethod.POST)
  public String adminReviewDelete(@ModelAttribute(value = "delete") AdminReviewActionDto dto,
      Model model) {
    Review review = reviewRepo.findOne(dto.getReviewId());
    if (review == null) {
      model.addAttribute("url", "/admin/course/" + dto.getCourseId());
      model.addAttribute("exception", new SpringException("Review does not exist."));
      return "security/error-page";
    }

    reviewRepo.delete(review);
    return "redirect:/admin/course/" + dto.getCourseId();
  }

  @RequestMapping(value = "/panel-review-delete", method = RequestMethod.POST)
  public String adminPanelReviewDelete(@ModelAttribute(value = "delete") AdminReviewActionDto dto,
      Model model) {
    Review review = reviewRepo.findOne(dto.getReviewId());
    if (review == null) {
      model.addAttribute("url", "/");
      model.addAttribute("exception", new SpringException("Review does not exist."));
      return "security/error-page";
    }

    reviewRepo.delete(review);
    return "redirect:/";
  }

  @RequestMapping(value = "/review-dismiss", method = RequestMethod.GET)
  public String adminReviewDelete(@RequestParam(value = "id", required = true) long reviewId) {
    Review review = reviewRepo.findOne(reviewId);
    if (review == null) {
      return "redirect:/";
    }

    review.setReported(false);
    reviewRepo.save(review);
    return "redirect:/";
  }
  
}
