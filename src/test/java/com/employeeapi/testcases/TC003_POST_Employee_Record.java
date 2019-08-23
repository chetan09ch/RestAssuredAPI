package com.employeeapi.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class TC003_POST_Employee_Record extends TestBase {
	
	String empName = RestUtils.empName();
	String empSal = RestUtils.empSal();
	String empAge = RestUtils.empAge();

	@BeforeClass
	void getEmployeeData() throws InterruptedException
	{
        logger.info("************** Started TC003_POST_Employee_Record *****************");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		//Request payload sending along with post request
		//Here we created data which can send along with POST request
		JSONObject requestParams = new JSONObject();
	
		requestParams.put("name", empName); // Parametrized
		requestParams.put("salary", empSal);
		requestParams.put("age", empAge);
		
		//Add a header stating the Request body is a JSON
		httpRequest.header("Content-Type", "application/json");
		
		//Add the JSON to the body of the Request 
		httpRequest.body(requestParams.toJSONString()); // Attach above data to the request 
		
		//Response object or POST Request
		response = httpRequest.request(Method.POST,"/create");		
		
		Thread.sleep(5000);
	}
	
	@Test
	void checkResponseBody()
	{
		logger.info("************** Checking Response Body *****************");
		String responseBody = response.getBody().asString(); // Getting Response Body
		//logger.info("Response Body==>" + responseBody);
		//Assert.assertTrue(responseBody!=null);
		Assert.assertEquals(responseBody.contains(empName),true);
		Assert.assertEquals(responseBody.contains(empSal),true);
		Assert.assertEquals(responseBody.contains(empAge),true);
	}
	
	@Test
	void checkStatusCode()
	{
		logger.info("************** Checking Status Code *****************");	
		int statusCode = response.getStatusCode();
		logger.info("Status Code is ==>" + statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkResponseTime()
	{
		logger.info("************** Checking Response Time *****************");
		
		long responseTime = response.getTime(); //Checking Response Time
		logger.info("Response Time is ==>" + responseTime);
		Assert.assertTrue(responseTime < 6000);
	}
	
	@Test
	void checkStatusLine()
	{
		logger.info("************** Checking Status Line *****************");		
		String statusLine = response.getStatusLine();
		logger.info("Status Line is ==>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType()
	{
		logger.info("************** Checking Content Type *****************");
		String contentType = response.header("content-type"); // Checking Content Type
		logger.info("Content Type is ==>" + contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");	
	}
	
	@Test
	void checkServerType()
	{
		logger.info("************** Checking Server Type *****************");
		String serverType = response.header("server"); // Checking Server Type
		logger.info("Server Type is ==>" + serverType);
		Assert.assertEquals(serverType, "Apache");	
	}
	
	
	@Test
	void checkContentLength()
	{
		logger.info("************** Checking Content Length *****************");
		
		String contentLength = response.header("Content-Length"); //Checking Response Time
		logger.info("Content Length is ==>" + contentLength);
		Assert.assertTrue(Integer.parseInt(contentLength) < 1500);
	}
	
	
	@AfterClass
	void tearDown()
	{
		logger.info("************** Finished TC002_GET_Single_Employee_Record *****************");
		
	}

}
