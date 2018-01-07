/**
 * 
 */
package base.activitymeter;

import java.util.Arrays;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;

/**
 * @author Joachim Coenen
 *
 */
@Service
//@Slf4j
public class DefaultActivityEmailService implements ActivityEmailService {
	public static final String REPLY_TO_MAIL_ADDRESS = "noreply@ias-se-se-se";
	public static final String FROM_MAIL_ADDRESS = "ias.se.se.se.2@gmail.com";
	@Autowired
	public EmailService emailService;

	/* (non-Javadoc)
	 * @see base.activitymeter.ActivityEmailService#sendConfirmationEmail(java.lang.String)
	 */
	@Override
	public void sendConfirmationEmail(String mailAddress, String link) throws AddressException {
		Email email;
		email = DefaultEmail.builder()
		        .from(new InternetAddress(FROM_MAIL_ADDRESS))
		        .replyTo(new InternetAddress(REPLY_TO_MAIL_ADDRESS))
		        .to(Arrays.asList(new InternetAddress(mailAddress)))
		        .subject("ias-se-se-se Confirmation of ")
		        .body("Lorem ipsum dolor sit amet [...] " + link)
		        .encoding("UTF-8").build();
		emailService.send(email);

	}

}
