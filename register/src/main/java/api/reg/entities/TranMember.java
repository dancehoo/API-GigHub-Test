package api.reg.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
public class TranMember implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String passwordHash;
	private Integer salary;
	private Integer classifiedId;
	private String isActive;
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	private String createdBy;
	@Temporal(TemporalType.DATE)
	private Date updatedDate;
	private String updatedBy;
	
	public TranMember() {
		
	}
	
	public TranMember(String email,String passwordHash,Integer salary, Integer classifiedId,String isActive, Date createdDate, String createdBy, Date updatedDate,String updatedBy) {
		this.email = email;
		this.passwordHash = passwordHash;
		this.salary = salary;
		this.classifiedId = classifiedId;
		this.isActive = isActive;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
	}
}
