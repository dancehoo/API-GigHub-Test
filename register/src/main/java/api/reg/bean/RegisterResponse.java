package api.reg.bean;

import lombok.Data;

@Data
public class RegisterResponse {
	private Integer responseCode;
	private String responseCdoeDescription;
	private String memberType;
	public RegisterResponse() {}
	public RegisterResponse(Integer responseCode,String responseCdoeDescription){
		this.responseCode = responseCode;
		this.responseCdoeDescription = responseCdoeDescription;
	}
}
