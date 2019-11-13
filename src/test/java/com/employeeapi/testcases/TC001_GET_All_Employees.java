package com.employeeapi.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;


public class TC001_GET_All_Employees extends TestBase {
	
	@BeforeClass
	void getAllEmployees() throws InterruptedException
	{
		logger.info("************** Started TC001_GET_All_Employees *****************");
		
		//Specify the Base URI
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		// Send request to the server(Request object)
		httpRequest = RestAssured.given();
		
		//Storing the Response in the response object & input path parameter passed
		response = httpRequest.request(Method.GET,"/employees");
		
		Thread.sleep(5000);
	}
	
	@Test
	void checkResponseBody()
	{
		logger.info("************** Checking Response Body *****************");
		String responseBody = response.getBody().asString(); // Getting Response Body
		logger.info("Response Body==>" + responseBody);
		Assert.assertTrue(responseBody!=null);
	}
	
	@Test
	void checkStatusCode()
	{
		logger.info("************** Checking Status Code *****************");
		int statusCode = response.getStatusCode(); //Checking status code
		logger.info("Status Code is ==>" + statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkResponseTime()
	{
		logger.info("************** Checking Response Time *****************");
		
		long responseTime = response.getTime(); //Checking Response Time
		logger.info("Response Time is ==>" + responseTime);
		
		if (responseTime > 2000)
			logger.warn("Response Time is greater than 2000");
		
		Assert.assertTrue(responseTime < 10000);
	}
	
	@Test
	void checkStatusLine()
	{
		logger.info("************** Checking Status Line *****************");
		String statusLine = response.getStatusLine(); // Checking status code
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
		Assert.assertEquals(serverType, "nginx/1.16.0");	
	}
	
	@Test
	void ContentEncoding()
	{
		logger.info("************** Checking Content Encodng *****************");
		String contentEncoding = response.header("content-encoding"); // Checking Content Encodng
		System.out.println("Content encoding is ==>:"+contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
		
	}
	
	@Test
	void checkContentLength()
	{
		logger.info("************** Checking Content Length *****************");
		
		String contentLength = response.header("Content-Length"); //Checking Response Time
		logger.info("Content Length is ==>" + contentLength);
		
		if (Integer.parseInt(contentLength) < 100)
			logger.warn("Content Length is less than 100");
		
		Assert.assertTrue(Integer.parseInt(contentLength) > 100);
	}
	
	@Test
	void checkCookies()
	{
		logger.info("************** Checking Cookies *****************");
		
		String cookie = response.getCookie("PHPSESSID");
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("************** Finished TC001_GET_All_Employees *****************");
		
	}
	
	

}
