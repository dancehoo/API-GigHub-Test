package api.reg.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import api.reg.bean.RegisterRequest;
import api.reg.bean.RegisterResponse;
import api.reg.businesses.RegisterBussiness;
import api.reg.exception.ConflictException;
import api.reg.exception.RequireFieldsException;
import api.reg.exception.ServerErrorException;

@RestController
@RequestMapping("/api")
public class RegisterController {

	private static final Logger logger = LogManager.getLogger(RegisterController.class);
	
	@Autowired
	private RegisterBussiness registerBussiness;
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/v1/register", method = RequestMethod.POST, produces={"application/json"}, consumes="application/json")
    public RegisterResponse registerV1(@RequestBody(required=true) RegisterRequest request) {
		RegisterResponse resp = null;
		Integer responseStatus = 201;
		String message=null;
		logger.info("registerV1");
		try {
			resp = registerBussiness.registerMember(request);
			
		}catch(RequireFieldsException ex) {
			responseStatus = 400;
			message = ex.getMessage();
			throw new RequireFieldsException(ex.getMessage());
		}catch(ConflictException ex) {
			responseStatus = 409;
			message = ex.getMessage();
			throw new ConflictException(ex.getMessage());
		}catch(ServerErrorException ex) {
			responseStatus = 500;
			message = ex.getMessage();
			throw new ServerErrorException(ex.getMessage());
		}finally {
			logger.info("Insert req log");
			registerBussiness.inserRegisterLog(request,responseStatus,message);
		}
		
        return resp;
    }
	
	
}
