package api.reg.businesses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import api.reg.bean.RegisterRequest;
import api.reg.bean.RegisterResponse;
import api.reg.exception.ConflictException;
import api.reg.exception.RequireFieldsException;
import api.reg.exception.ServerErrorException;
import api.reg.services.RegisterService;
import api.reg.validators.EmailValidator;
import api.reg.validators.PasswordValidator;
import api.reg.validators.SalaryValidator;

@Component
public class RegisterBussinessImpl implements RegisterBussiness {
	
	@Autowired
	private RegisterService registerService;
	
	@Transactional
	public RegisterResponse registerMember(RegisterRequest request) throws RequireFieldsException,ConflictException,ServerErrorException{
		/*validate parameters*/
		String paramValidate = parameterValidate(request);
		if(paramValidate!=null && !paramValidate.isEmpty()) {
			throw new RequireFieldsException(paramValidate);
		}
		/*end validate parameters*/
		
		/*validate data*/
		/*email exist*/
		boolean emailExist = registerService.checkEmailExist(request.getEmail());
		if(emailExist) {
			throw new ConflictException("User already exist");
		}
		/*end email exist*/
		/*end validate data*/
		
		/*insert new Account*/
		String memberType = registerService.inserMember(request);
		/*end insert new Account*/
		RegisterResponse result = new RegisterResponse();
		result.setMemberType(memberType);
		result.setResponseCdoeDescription("Created");
		result.setResponseCode(201);
		return result;
	}
	
	private String parameterValidate(RegisterRequest request) {
		String error=null;
		error = EmailValidator.validateEmail(request.getEmail());
		if(error==null || error.isEmpty()) {
			error = PasswordValidator.validateNewPass(request.getPassword());
		}
		if(error==null || error.isEmpty()) {
			error = SalaryValidator.validateSalary(request.getSalary());
		}
		return error;
	}
	
	public int inserRegisterLog(RegisterRequest request,Integer responseStatus,String message) throws ServerErrorException{
		return registerService.inserRegisterLog(request, responseStatus, message);
	}
}
