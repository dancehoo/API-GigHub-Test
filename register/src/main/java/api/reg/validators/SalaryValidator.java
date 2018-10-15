package api.reg.validators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.reg.constants.AppConstants;

public class SalaryValidator {

	private static final Logger logger = LogManager.getLogger(SalaryValidator.class);
	
	public static String validateSalary(String salary) {
		String error =null;
		if(salary==null || salary.isEmpty()) {
			error ="Salary is require field";
		}else{
			try {
				int mySalary = Integer.parseInt(salary);
				if(mySalary<AppConstants.MIN_SALARY) {
					error = "Based salary must more than or equal "+AppConstants.MIN_SALARY;
				}
			}catch (Exception e) {
				error = "Salary must be integer";
			}
		}
		logger.info("Error salary :"+error);
		return error;
	}
}
