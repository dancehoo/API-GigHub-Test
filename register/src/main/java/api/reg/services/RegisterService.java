package api.reg.services;

import api.reg.bean.RegisterRequest;
import api.reg.exception.ServerErrorException;

public interface RegisterService {

	public boolean checkEmailExist(String email) throws ServerErrorException;
	public String inserMember(RegisterRequest request) throws ServerErrorException;
	public int inserRegisterLog(RegisterRequest request,Integer responseStatus,String message) throws ServerErrorException;
}
