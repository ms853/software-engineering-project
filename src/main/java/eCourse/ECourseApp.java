package ecourse;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ecourse.service.CourseUploadFileService;

@SpringBootApplication
public class ECourseApp implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(ECourseApp.class, args);
  }

  @Override
  public void run(String... args) throws Exception {}

  @Bean
  CommandLineRunner init(CourseUploadFileService storageService) {
    return (args) -> {
      storageService.deleteAll();
      storageService.init();
    };
  }

}
