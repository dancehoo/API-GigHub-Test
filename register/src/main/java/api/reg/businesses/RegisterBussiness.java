package api.reg.businesses;

import api.reg.bean.RegisterRequest;
import api.reg.bean.RegisterResponse;
import api.reg.exception.ConflictException;
import api.reg.exception.RequireFieldsException;
import api.reg.exception.ServerErrorException;

public interface RegisterBussiness {

	public RegisterResponse registerMember(RegisterRequest request)  throws RequireFieldsException, ConflictException, ServerErrorException;
	public int inserRegisterLog(RegisterRequest request,Integer responseStatus,String message) throws ServerErrorException;
}
