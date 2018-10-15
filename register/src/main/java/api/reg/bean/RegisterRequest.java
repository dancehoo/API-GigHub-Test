package api.reg.bean;

import lombok.Data;

@Data
public class RegisterRequest {

	private String email;
	private String password;
	private String salary;
	
	public RegisterRequest() {}
	
	public RegisterRequest(String email,String password,String salary) {
		this.email = email;
		this.password = password;
		this.salary = salary;
	}
}
