package api.reg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import api.reg.bean.RegisterResponse;

@ControllerAdvice
@RestController
public class GlobalControllerExceptionHandler {
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)  // 400
    @ExceptionHandler(RequireFieldsException.class)
	public final ResponseEntity<RegisterResponse> handleUserBadReqestException(RequireFieldsException ex, WebRequest request) {
		RegisterResponse errorDetails = new RegisterResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(ConflictException.class)
	public final ResponseEntity<RegisterResponse> handleConflictException(ConflictException ex, WebRequest request) {
		RegisterResponse errorDetails = new RegisterResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
	    return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 500
    @ExceptionHandler(ServerErrorException.class)
	public final ResponseEntity<RegisterResponse> handleServerException(ServerErrorException ex, WebRequest request) {
		RegisterResponse errorDetails = new RegisterResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
	    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
