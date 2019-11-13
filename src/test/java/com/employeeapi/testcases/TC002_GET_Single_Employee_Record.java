package com.employeeapi.testcases;

import com.employeeapi.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC002_GET_Single_Employee_Record extends TestBase {
	
	@BeforeClass
	void getEmployeeData() throws InterruptedException
	{
        logger.info("************** Started TC002_GET_Single_Employee_Record *****************");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employee/"+empID);
		
		Thread.sleep(5000);
	}
	
	@Test
	void checkResponseBody()
	{
		logger.info("************** Checking Response Body *****************");
		String responseBody = response.getBody().asString(); // Getting Response Body
		logger.info("Response Body==>" + responseBody);
		//Assert.assertTrue(responseBody!=null);
		Assert.assertEquals(responseBody.contains(empID),true);
	}
	
	@Test
	void checkStatusCode()
	{
		logger.info("************** Checking Status Code *****************");
		int statusCode = response.getStatusCode(); //Checking status code
		//logger.info("Status Code is ==>" + statusCode);
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
		String statusLine = response.getStatusLine(); // Checking status code
		//logger.info("Status Line is ==>" + statusLine);
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
		String contentEncoding = response.header("content-encoding"); // Checking Content Encoding
		System.out.println("Content encoding is ==>:"+contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
		
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
