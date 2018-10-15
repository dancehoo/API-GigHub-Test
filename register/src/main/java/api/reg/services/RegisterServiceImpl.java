package api.reg.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import api.reg.bean.RegisterRequest;
import api.reg.exception.ServerErrorException;
import api.reg.utils.MD5HashVal;

@Service
public class RegisterServiceImpl implements RegisterService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public boolean checkEmailExist(String email) throws ServerErrorException{
		boolean exist = false;
		try {
			Integer result = jdbcTemplate.queryForObject("select count(*) as CNT from tran_member where email=?", new Object[] { email },Integer.class);
			if(result>0) {
				exist = true;
			}
		}catch(Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}
		return exist;
	}
	
	public String inserMember(RegisterRequest request) throws ServerErrorException{
		String memberType = null;
		try {
			String email = request.getEmail().trim();
			String passwordHash = MD5HashVal.getMD5HashVal(request.getPassword());
			Integer salary = Integer.parseInt(request.getSalary());
			String[] memberTypeArray = getMemberType(salary);
			String sql = "insert into tran_member (email, password_hash, salary, classified_id,created_by,updated_by ) values(?,?,?,?,?,?)";
			jdbcTemplate.update(sql, new Object[] { email,passwordHash,salary,memberTypeArray[0],"ADMIN","ADMIN" });
			memberType = memberTypeArray[1];
		}catch(Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}
		return memberType;
	}
	
	public String[] getMemberType(Integer salary) throws ServerErrorException{
		String[] result = null;
		try {
			String sql ="SELECT TOP 1 ID, CLASS_DESCRIPTION  FROM MT_MEMBER_CLASSIFIED WHERE MIN_SALARY <= ? AND IS_ACTIVE=? ORDER BY MIN_SALARY DESC";
			result = jdbcTemplate.queryForObject(sql,new Object[] { salary,"Y" },
					new RowMapper<String[]>() {
		        public String[] mapRow(ResultSet rs, int rowNum) throws SQLException {
		        	String[] c = new String[2];
		        	c[0] = rs.getString(1);
		        	c[1] = rs.getString(2);
		            return c;
		        }
			});
		}catch(Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}
		return result;
	}
	
	public int inserRegisterLog(RegisterRequest request,Integer responseStatus,String message) throws ServerErrorException{
		int result = 0;
		try {
			String email = request.getEmail();
			String salary = request.getSalary();
			String sql = "insert into log_register (email, salary,message, response_status,created_by ) values(?,?,?,?,?)";
			result = jdbcTemplate.update(sql, new Object[] { email,salary,message,responseStatus,"ADMIN"});
		}catch(Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}
		return result;
	}
}
