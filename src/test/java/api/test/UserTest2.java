package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest2 {
	Faker faker;
	User userpayload;
	public  Logger logger;
	
	@BeforeTest
	public void setupData() {
		 faker = new Faker();
		 userpayload= new User();
		
		userpayload.setId(faker.idNumber().hashCode());
		
		userpayload.setUsername( faker.name().username()); 
		userpayload.setFirstname(faker.name().firstName()); 
		userpayload.setLastname(faker.name().lastName()); 
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password());
		userpayload.setPhone(faker.phoneNumber().cellPhone());	
		
		// logs
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority=1)
	public void testpostuser() {
		logger.info("******* creating user*********");
		Response response= UserEndPoints2.CreateUser(userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority=2)
	public void testgetuser() {
		logger.info("******* reading user*********");
		Response response=	UserEndPoints2.ReadUser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	@Test(priority=3)
	public void testUpdateuser() {
		logger.info("******* updating user*********");
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password());
		userpayload.setPhone(faker.phoneNumber().cellPhone());	
		Response response= UserEndPoints2.UpdateUser(userpayload, this.userpayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		// checking data after upadte
		
		Response responseafterupdate=	UserEndPoints2.ReadUser(this.userpayload.getUsername());
		responseafterupdate.then().log().all();
		Assert.assertEquals(responseafterupdate.getStatusCode(), 200);
		
	}
	@Test(priority=4)
	public void testDeleteuser() {
		logger.info("******* deleting user*********");
		
		Response response=	UserEndPoints2.DeleteUser(this.userpayload.getUsername());
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

}
