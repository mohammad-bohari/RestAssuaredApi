package com.apitesting;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC001_GET_Request {
	
	@Test
	void getStudentData() {
		
		//Specify based URI
		RestAssured.baseURI = "http://localhost:8080/student";
		
		//Request object create
		RequestSpecification httprequest=RestAssured.given();
		
		//Response object
		Response response = httprequest.request(Method.GET,"/100");
		
		//Get response body
		String responseBody=response.getBody().asString();	
		System.out.println(response.getBody());
		System.out.println("This is response Body: " + responseBody);
		
		//Get Status code
		int statusCode = response.getStatusCode();
		System.out.println("This is response Code: " +statusCode);
		
		//Status code verification
		Assert.assertEquals(statusCode, 200);
		
	}
	

}
