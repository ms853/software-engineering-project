package ecourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ecourse.domain.User;
import ecourse.service.UserService;
import ecourse.domain.Learner;
import ecourse.service.LearnerService;

@Controller
@RequestMapping("/learner/profile")
public class ProfileController {

  @Autowired
  private UserService userService;
  @Autowired
  private LearnerService learnerService;

  @RequestMapping("/")
  public String profile(Model model) {
    User user = userService.getCurrentLoggedInUser();
    Learner learner = learnerService.findByUser(user);
    model.addAttribute("friends", learner.getFriends());
    model.addAttribute("learner", learner);
    model.addAttribute("user", user);
    model.addAttribute("owner", true);
    return "form/profile";
  }

  @RequestMapping(value = "/{learnerId}")
  public String learnerProfile(Model model, @PathVariable long learnerId) {
    Learner learner = learnerService.findById(learnerId);
    User visitorUser = userService.getCurrentLoggedInUser();
    Learner visitorLearner = learnerService.findByUser(visitorUser);
    if (visitorLearner.getFriend(learnerId) == null) {
      model.addAttribute("notFriends", true);
    } else {
      model.addAttribute("notFriends", false);
    }
    User user = learner.getLearner();
    if (visitorLearner == learner) {
      model.addAttribute("notFriends", false);
    }
    model.addAttribute("learner", learner);
    model.addAttribute("friends", visitorLearner.getFriends());
    model.addAttribute("user", user);
    model.addAttribute("visitorUser", visitorUser);
    model.addAttribute("visitor", true);
    return "form/profile";
  }

  @RequestMapping(value = "/add-friend/{learnerId}")
  public String addFriend(Model model, @PathVariable long learnerId) {
    Learner friend = learnerService.findById(learnerId);
    Learner learner = learnerService.findByUser(userService.getCurrentLoggedInUser());
    learner.addFriend(friend);
    learnerService.save(learner);
    return "redirect:/learner/profile/";
  }

}
