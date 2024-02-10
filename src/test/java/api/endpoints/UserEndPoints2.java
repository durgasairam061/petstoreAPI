package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import javax.mail.Quota.Resource;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;




public class UserEndPoints2 {
	
	// load properties class
	// method created for getting url's from from properties file
 static	ResourceBundle getURL(){
		ResourceBundle routes = ResourceBundle.getBundle("routes");  // loades properties file
		return routes;
	}
	
	public static Response CreateUser(User payload)
	{
	String post_url=	getURL().getString("post_url");
	Response response=	given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(post_url);
	
	return response;
		
		
	}
	
	public static Response ReadUser(String username)
	{
		String get_url=	getURL().getString("get_url");
	Response response=	given()
		.pathParam("username", username)
		
		.when()
		.get(get_url);
	
	return response;
		
		
	}
	
	public static Response UpdateUser(User payload, String username)
	{
		String update_url=	getURL().getString("update_url");
	Response response=	given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("username", username)
		.body(payload)
		
		.when()
		.put(update_url);
	
	return response;
		
		
	}
	
	public static Response DeleteUser(String username)
	{
		String delete_url=	getURL().getString("delete_url");
	Response response=	given()
		.pathParam("username", username)
		
		.when()
		.delete(delete_url);
	
	return response;
}
}