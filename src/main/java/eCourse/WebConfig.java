package ecourse;

import ecourse.storage.StorageProperties;
import ecourse.validator.CreateCourseValidator;
import ecourse.validator.PasswordResetFormValidator;
import ecourse.validator.PasswordValidator;
import ecourse.validator.ReviewValidator;
import ecourse.validator.SignUpFormValidator;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Properties;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

  // Handles HTTP GET requests for /resources/** by efficiently serving up static
  // resources in the ${webappRoot}/resources/ directory
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
  }

  // Java configuration equivalent to
  // <mvc:default-servlet-handler/> in spring-servlet.xml
  // used to use bootstrap when security is enabled
  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  /**
   * Creates a new {@link InternalResourceViewResolver} that resolves views selected for rendering
   * by Controllers to .jsp resources in the /WEB-INF/views directory
   * 
   * @return {@link InternalResourceViewResolver}
   */
  @Bean
  public InternalResourceViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setViewClass(JstlView.class);
    viewResolver.setPrefix("/WEB-INF/views/");
    viewResolver.setSuffix(".jsp");
    viewResolver.setOrder(2);
    return viewResolver;
  }

  /**
   * Creates a new {@link SimpleMappingExceptionResolver} that resolves exceptions within
   * Controllers to a .jsp view.
   * 
   * @return {@link SimpleMappingExceptionResolver}
   */
  @Bean
  public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
    SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
    Properties mappings = new Properties();
    mappings.put("ecourse.controller.SpringException", "form/exception-page");
    mappings.put("defaultErrorView", "form/error");
    exceptionResolver.setExceptionMappings(mappings);
    return exceptionResolver;
  }

  /**
   * Creates a new {@link ResourceBundleMessageSource} that resolves error messages for form
   * validation.
   * 
   * @return {@link ResourceBundleMessageSource}
   */
  @Bean
  public MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename("messages");
    return messageSource;
  }

  @Bean
  public SignUpFormValidator signUpValidatorFactoryBean() {
    return new SignUpFormValidator();
  }

  @Bean
  public PasswordValidator passwordValidatorFactoryBean() {
    return new PasswordValidator();
  }

  @Bean
  public PasswordResetFormValidator passwordResetFormValidatorFactoryBean() {
    return new PasswordResetFormValidator();
  }

  @Bean
  public CreateCourseValidator createCourseValidatorFactoryBean() {
    return new CreateCourseValidator();
  }

  @Bean
  public ReviewValidator reviewValidatorFactoryBean() {
    return new ReviewValidator();
  }

  /**
   * Creates a new {@link JavaMailSenderImpl} that resolves email properties for sending an email.
   * 
   * @return {@link JavaMailSenderImpl}
   */
  @Bean
  public JavaMailSenderImpl javaMailSenderImpl() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost("smtp.gmail.com");
    mailSender.setPort(587);
    // Set mail user and pass
    mailSender.setUsername("getdigital321@gmail.com");
    mailSender.setPassword("Getdigit@l123");

    Properties prop = mailSender.getJavaMailProperties();
    prop.put("mail.transport.protocol", "smtp");
    prop.put("mail.smtp.auth", "true");
    prop.put("mail.smtp.starttls.enable", "true");
    prop.put("mail.debug", "true");

    return mailSender;
  }

  @Bean
  public EmbeddedServletContainerCustomizer notFoundCustomizer() {
    return new NotFoundIndexTemplate();
  }

  private static class NotFoundIndexTemplate implements EmbeddedServletContainerCustomizer {
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
      container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error-page"));
    }
  }

  @Bean(name = "storage")
  public StorageProperties localStoragePropertiesBean() {
    StorageProperties properties = new StorageProperties();
    return properties;
  }

}
