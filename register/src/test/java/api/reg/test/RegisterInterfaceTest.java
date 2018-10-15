package api.reg.test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import api.reg.bean.RegisterRequest;
import api.reg.bean.RegisterResponse;
import api.reg.businesses.RegisterBussiness;
import api.reg.exception.ConflictException;
import api.reg.exception.RequireFieldsException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterInterfaceTest {

	private static int count = 46;
	@Autowired
    private RegisterBussiness registerBussiness;
	
	@Test
	public void successRegisterNewMemberSilverMin() {
	    // given
		Integer respCode = 201;
		String memberType = "Silver";
		RegisterRequest request = new RegisterRequest("note_"+(count++)+"@yahoo.com","ap1@Test","15000");
	 
	    // when
		RegisterResponse resp = registerBussiness.registerMember(request);
	 
	    // then
	    assertTrue(respCode.equals(resp.getResponseCode()));
	    assertTrue(memberType.equals(resp.getMemberType()));
	}
	
	@Test
	public void successRegisterNewMemberSilverMax() {
	    // given
		Integer respCode = 201;
		String memberType = "Silver";
		RegisterRequest request = new RegisterRequest("note_"+(count++)+"@yahoo.com","ap1@Test","30000");
	 
	    // when
		RegisterResponse resp = registerBussiness.registerMember(request);
	 
	    // then
	    assertTrue(respCode.equals(resp.getResponseCode()));
	    assertTrue(memberType.equals(resp.getMemberType()));
	}
	
	@Test
	public void successRegisterNewMemberGoldMin() {
	    // given
		Integer respCode = 201;
		String memberType = "Gold";
		RegisterRequest request = new RegisterRequest("note_"+(count++)+"@yahoo.com","ap1@Test","30001");
	 
	    // when
		RegisterResponse resp = registerBussiness.registerMember(request);
	 
	    // then
	    assertTrue(respCode.equals(resp.getResponseCode()));
	    assertTrue(memberType.equals(resp.getMemberType()));
	}
	
	@Test
	public void successRegisterNewMemberGoldMax() {
	    // given
		Integer respCode = 201;
		String memberType = "Gold";
		RegisterRequest request = new RegisterRequest("note_"+(count++)+"@yahoo.com","ap1@Test","50000");
	 
	    // when
		RegisterResponse resp = registerBussiness.registerMember(request);
	 
	    // then
	    assertTrue(respCode.equals(resp.getResponseCode()));
	    assertTrue(memberType.equals(resp.getMemberType()));
	}
	
	@Test
	public void successRegisterNewMemberPlatinumMin() {
	    // given
		Integer respCode = 201;
		String memberType = "Platinum";
		RegisterRequest request = new RegisterRequest("note_"+(count++)+"@yahoo.com","ap1@Test","50001");
	 
	    // when
		RegisterResponse resp = registerBussiness.registerMember(request);
	 
	    // then
	    assertTrue(respCode.equals(resp.getResponseCode()));
	    assertTrue(memberType.equals(resp.getMemberType()));
	}
	
	@Test
	public void failRegisterNewMemberBlankemail() {
	    // given
		RegisterRequest request = new RegisterRequest(null,"ap1@Test","50001");
	 
	    // when
		RegisterResponse resp = null;
		try {
			resp = registerBussiness.registerMember(request);
		}catch(RequireFieldsException ex) {}
	    // then
	    assertNull("must error RequireFieldsException",resp);
	}
	
	@Test
	public void failRegisterNewMemberInvalidmail() {
	    // given
		RegisterRequest request = new RegisterRequest("note_20@yahoo.com..","ap1@Test","50001");
	 
	    // when
		RegisterResponse resp = null;
		try {
			resp = registerBussiness.registerMember(request);
		}catch(RequireFieldsException ex) {}
	    // then
	    assertNull("must error RequireFieldsException",resp);
	}
	
	@Test
	public void failRegisterNewMemberDuplicatemail() {
	    // given
		RegisterRequest request = new RegisterRequest("note_45@yahoo.com","ap1@Test","50001");
	 
	    // when
		RegisterResponse resp = null;
		try {
			resp = registerBussiness.registerMember(request);
		}catch(ConflictException ex) {}
	    // then
	    assertNull("must error ConflictException",resp);
	}
	
	@Test
	public void failRegisterNewMemberBlankpassword() {
	    // given
		RegisterRequest request = new RegisterRequest("note_"+(count++)+"@yahoo.com","","50001");
	 
	    // when
		RegisterResponse resp = null;
		try {
			resp = registerBussiness.registerMember(request);
		}catch(RequireFieldsException ex) {}
	    // then
	    assertNull("must error RequireFieldsException",resp);
	}
	
	@Test
	public void failRegisterNewMemberInvalidpassword() {
	    // given
		RegisterRequest request = new RegisterRequest("note_"+(count++)+"@yahoo.com","password","50001");
	 
	    // when
		RegisterResponse resp = null;
		try {
			resp = registerBussiness.registerMember(request);
		}catch(RequireFieldsException ex) {}
	    // then
	    assertNull("must error RequireFieldsException",resp);
	}
	
	@Test
	public void failRegisterNewMemberBlanksalary() {
	    // given
		RegisterRequest request = new RegisterRequest("note_"+(count++)+"@yahoo.com","ap1@Test","");
	 
	    // when
		RegisterResponse resp = null;
		try {
			resp = registerBussiness.registerMember(request);
		}catch(RequireFieldsException ex) {}
	    // then
	    assertNull("must error RequireFieldsException",resp);
	}
	
	@Test
	public void failRegisterNewMemberLowerMinsalary() {
	    // given
		RegisterRequest request = new RegisterRequest("note_"+(count++)+"@yahoo.com","ap1@Test","300");
	 
	    // when
		RegisterResponse resp = null;
		try {
			resp = registerBussiness.registerMember(request);
		}catch(RequireFieldsException ex) {}
	    // then
	    assertNull("must error RequireFieldsException",resp);
	}
	
	@Test
	public void successInsertLogBlankAll() {
	    // given
		int insertRow = 0;
		Integer responseStatus = null;
		String message = null;
		RegisterRequest request = new RegisterRequest();
	 
	    // when
		insertRow = registerBussiness.inserRegisterLog(request, responseStatus, message);
	 
	    // then
	    assertTrue(insertRow>0);
	}
	
	@Test
	public void successInsertLogNormalAll() {
	    // given
		int insertRow = 0;
		Integer responseStatus = 201;
		String message = null;
		RegisterRequest request = new RegisterRequest("note_"+(count++)+"@yahoo.com","ap1@Test","80000");
	 
	    // when
		insertRow = registerBussiness.inserRegisterLog(request, responseStatus, message);
	 
	    // then
	    assertTrue(insertRow>0);
	}
}
