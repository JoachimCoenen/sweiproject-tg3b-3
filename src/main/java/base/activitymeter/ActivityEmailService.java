package base.activitymeter;

import javax.mail.internet.AddressException;

public interface ActivityEmailService {
	public void sendConfirmationEmail(String mailAddress, String link) throws AddressException;
}
