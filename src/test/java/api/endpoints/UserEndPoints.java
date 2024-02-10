package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


// userendpoints.java --- created for perform CRUD operaations (create, read, update,delete) request of user

public class UserEndPoints {
	
	public static Response CreateUser(User payload)
	{
	Response response=	given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(Routes.Post_url);
	
	return response;
		
		
	}
	
	public static Response ReadUser(String username)
	{
	Response response=	given()
		.pathParam("username", username)
		
		.when()
		.get(Routes.get_url);
	
	return response;
		
		
	}
	
	public static Response UpdateUser(User payload, String username)
	{
	Response response=	given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("username", username)
		.body(payload)
		
		.when()
		.put(Routes.update_url);
	
	return response;
		
		
	}
	
	public static Response DeleteUser(String username)
	{
	Response response=	given()
		.pathParam("username", username)
		
		.when()
		.delete(Routes.delete_url);
	
	return response;
}
}