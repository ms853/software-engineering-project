package ecourse;

import ecourse.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
public class SmtpMailSender {

  private static final String FROM_EMAIL = "getdigital321@gmail.com";
  private static final String FROM_PERSONAL = "Get Digital";
  private static final String BASE_URL = "https://localhost:8090";

  @Autowired
  JavaMailSender mailSender;

  private InternetAddress fromAddress;

  public SmtpMailSender() throws UnsupportedEncodingException {
    fromAddress = new InternetAddress(FROM_EMAIL, FROM_PERSONAL);
  }

  /**
   * Sends a new email to the user to verify their email.
   * 
   * @param to {@link String} The recipient of the email.
   * @param userId The ID of the User to verify.
   * @param verificationId {@link String} The verification id.
   * @throws MessagingException if an error occurred while sending the message
   */
  public void sendVerificationMail(String to, long userId, String verificationId)
      throws MessagingException {

    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage, false, "utf-8");
    mailMsg.setFrom(fromAddress);
    mailMsg.setTo(to);
    mailMsg.setSubject("Verify your email address - Get Digital");

    final String verificationUrl =
        BASE_URL + "/signup/confirm?uId=" + userId + "&vId=" + verificationId;
    mailMsg.setText(
        "<p>Welcome to Get Digital!<br/>Follow this link to verify your account: <a href=\""
            + verificationUrl + "\">" + verificationUrl + "</a></p>",
        true);
    mailSender.send(mimeMessage);

  }

  /**
   * Sends a new email to the user to reset their password.
   * 
   * @param to {@link String} The recipient of the email.
   * @param userId The ID of the User to password reset.
   * @param passwordResetId {@link String} The password reset token.
   * @throws MessagingException if an error occurred while sending the message
   */
  public void sendPasswordResetRequestMail(String to, long userId, String passwordResetId)
      throws MessagingException {

    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage, false, "utf-8");
    mailMsg.setFrom(fromAddress);
    mailMsg.setTo(to);
    mailMsg.setSubject("Password reset request - Get Digital");

    final String passwordResetUrl =
        BASE_URL + "/reset-password?uId=" + userId + "&pId=" + passwordResetId;
    mailMsg.setText(
        "<p>A request was made to reset the password for your account.<br/>Follow this link to do so: <a href=\""
            + passwordResetUrl + "\">" + passwordResetUrl + "</a></p>",
        true);
    mailSender.send(mimeMessage);

  }

  /**
   * Sends a new email to the user that notifies of a recent password reset.
   * 
   * @param to {@link String} The recipient of the email.
   * @param resetTime The time that the reset happened.
   * @throws MessagingException if an error occurred while sending the message
   */
  public void sendPasswordResetNotifMail(String to, Date resetTime) throws MessagingException {

    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage, false, "utf-8");
    mailMsg.setFrom(fromAddress);
    mailMsg.setTo(to);
    mailMsg.setSubject("Your password was changed - Get Digital");

    mailMsg.setText("<p>Your password was changed at "
        + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(resetTime) + ".</p>", true);
    mailSender.send(mimeMessage);

  }

  /**
   * Sends a new email to the user to enrol on course.
   * 
   * @param teacher {@link User} The teacher of the course.
   * @param to {@link String} The recipient of the email.
   * @param courseId The id of the course.
   * @throws MessagingException if an error occurred while sending the message
   */
  public void sendLearnerInviteMail(User teacher, String to, long courseId)
      throws MessagingException {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage, false, "utf-8");
    mailMsg.setFrom(fromAddress);
    mailMsg.setTo(to);
    mailMsg.setSubject("Invite to enrol - Get Digital");

    final String inviteUrl = BASE_URL + "/learner/enrol?courseId=" + courseId;
    mailMsg.setText("<p>Teacher: " + teacher.getName() + " has invited you to their course!"
        + "<br/>Follow this link to enrol on this course: <a href=\"" + inviteUrl + "\">"
        + inviteUrl + "</a>"
        + "<br/>If you wish to contact the teacher, please email them on their email: "
        + teacher.getEmail() + "</p>", true);
    mailSender.send(mimeMessage);
  }

}
