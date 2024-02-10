package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {

	@Test(priority=1, dataProvider="Data",dataProviderClass=DataProviders.class) // data provider is in seperate package
	public void testPostUser(String UserID,String UserName,String FirstName,String Lname,String email,String password,String phone) {
		
		User userpayload = new User();
        userpayload.setId(Integer.parseInt(UserID));
		
		userpayload.setUsername(UserName); 
		userpayload.setFirstname(FirstName); 
		userpayload.setLastname(Lname); 
		userpayload.setEmail(email);
		userpayload.setPassword(password);
		userpayload.setPhone(phone);	
		Response response= UserEndPoints.CreateUser(userpayload);
	//	response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
	}
	
	@Test(priority=2, dataProvider="UserNames",dataProviderClass=DataProviders.class) // data provider is in seperate package
	public void testDeleteByUserName(String UserName) {
		Response response= UserEndPoints.DeleteUser(UserName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
		
}
