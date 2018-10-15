package api.reg.validators;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class EmailValidator {

	private static final Logger logger = LogManager.getLogger(EmailValidator.class);
	
	public static String validateEmail(String email) {
		String error = null;
		logger.info("Email :"+email);
		if(email==null || email.isEmpty()) {
			error ="Email is require field";
		}else {
			try {
				// Create InternetAddress object and validated the supplied
				// address which is this case is an email address.
				InternetAddress internetAddress = new InternetAddress(email);
				internetAddress.validate();
			} catch (AddressException e) {
				error ="Invalid email";
			}
		}
		logger.info("Error Email :"+error);
		return error;
	}
}
