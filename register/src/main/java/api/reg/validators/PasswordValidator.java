package api.reg.validators;

import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PasswordValidator {
	
	private static final Logger logger = LogManager.getLogger(PasswordValidator.class);
	private static final Pattern hasUppercase = Pattern.compile("[A-Z]");
	private static final Pattern hasLowercase = Pattern.compile("[a-z]");
	private static final Pattern hasNumber = Pattern.compile("\\d");
	private static final Pattern hasSpecialChar = Pattern.compile("[^a-zA-Z0-9 ]");
	
	public static String validateNewPass(String password) {
	    if (password == null || password.isEmpty()) {
	        logger.info("Password = null");
	        return "Password is require field";
	    }

	    String error=null;

        if (password.length() < 6) {
            error = "Password is too short. Needs to have 6 characters";
        }else

        if (!hasUppercase.matcher(password).find()) {
            error = "Password needs an upper case";
        }else

        if (!hasLowercase.matcher(password).find()) {
            error = "Password needs a lowercase";
        }else

        if (!hasNumber.matcher(password).find()) {
            error = "Password needs a number";
        }else

        if (!hasSpecialChar.matcher(password).find()) {
            error = "Password needs a special character i.e. !,@,#, etc.";
        }
	    
        logger.info("Error password :" + error);
	    return error;
	}
}
