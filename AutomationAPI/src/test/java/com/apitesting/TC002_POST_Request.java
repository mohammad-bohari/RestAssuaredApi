package com.apitesting;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC002_POST_Request {
	
	@Test
	void createNewStudent() {
		
		//Specify based URI
		RestAssured.baseURI = "http://localhost:8080/student";
		
		//Request object create
		RequestSpecification httprequest=RestAssured.given();
		
		//json object
		JSONObject requestParams = new JSONObject();
		
		//Sending data 
		requestParams.put("firstName","Mohammad");
		requestParams.put("lastName","Bohari");
		requestParams.put("email","Mohammad123@gmail.com");
		requestParams.put("programme","Chairman");

		
		httprequest.header("Content-Type", "application/json");
		httprequest.body(requestParams.toJSONString());
	
		Response response = httprequest.request(Method.POST, "");
		
		
		//Get response body
		String responseBody=response.getBody().asString();	
		System.out.println("This is response Body: " + responseBody);
		
		String msgResponse = response.jsonPath().get("msg");
		
		//Get Status code
		int statusCode = response.getStatusCode();
		System.out.println("This is response Code: " +statusCode);
		
		Assert.assertEquals(statusCode, 201);
		Assert.assertEquals(msgResponse, "Student added");
	
	}

}
