package ecourse.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ecourse.domain.Course;
import ecourse.domain.CourseUploadFile;
import ecourse.service.CourseService;
import ecourse.service.CourseUploadFileService;
import ecourse.service.UserService;

@Controller
@RequestMapping("/teacher/course")
public class CourseUploadFileController {

  @Autowired
  private CourseService courseService;
  @Autowired
  private UserService userService;

  private final CourseUploadFileService uploadService;

  @Autowired
  public CourseUploadFileController(CourseUploadFileService uploadService) {
    this.uploadService = uploadService;
  }

  /**
   * Handles file upload.
   * 
   * @param model The model.
   * @param courseId The ID of the course.
   * @param file The uploaded file.
   * @param fileName The file name.
   * @param extension The file extension.
   * @param redirectAttributes Redirect attributes.
   * @param request The request.
   * @return The view.
   */
  @RequestMapping(value = "/{courseId}/upload/new-upload", method = RequestMethod.POST)
  public String uploadFile(Model model, @PathVariable long courseId,
      @RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName,
      @RequestParam("ext") String extension, RedirectAttributes redirectAttributes,
      HttpServletRequest request) {

    if (file.isEmpty()) {
      redirectAttributes.addFlashAttribute("message", "Choose a non-empty file to upload!");
      return "redirect:/teacher/";
    }

    if (file.getContentType().equals("application/octet-stream")) {
      redirectAttributes.addFlashAttribute("message", "Choose a file to upload first!");
      return "redirect:/teacher/";
    }

    if (uploadService.verifyContentType(file.getContentType()) == false) {
      redirectAttributes.addFlashAttribute("message",
          "This content type is not allowed: " + file.getContentType() + "!");
      return "redirect:/teacher/";
    }

    Course course = courseService.findByTeacher(userService.getCurrentLoggedInUser());
    fileName = fileName + extension;
    System.out.println("Saving file: " + fileName);
    CourseUploadFile uploadFile = new CourseUploadFile();
    uploadFile.setCourse(course);
    uploadFile.setFileName(fileName);
    System.out.println("COURSE ID: " + course.getId());
    try {
      uploadService.save(uploadFile, file);
    } catch (Exception e) {
      model.addAttribute("url", "/");
      model.addAttribute("exception", e);
      return "security/error-page";
    }
    redirectAttributes.addFlashAttribute("message",
        "You successfully uploaded " + file.getOriginalFilename() + "!");
    return "redirect:/teacher/";
  }

}
