package com.employeeapi.utilities;
// Listener class used to generate Extent Report
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
//import com.aventstack.extentreports.LogStatus;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter{
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent; 
	public ExtentTest logger;
	
	
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// Curent time stamp
		String repName = "Test-Report-"+timeStamp+".html";
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/"+repName); // Specify location
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		
		extent = new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Project Name", "CarFinance");
		extent.setSystemInfo("Host Name", "LocalHost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", "Chetan");
		
		htmlReporter.config().setDocumentTitle("CF Automation Report"); // Title of Report
		htmlReporter.config().setReportName("RestApi Automation Test Report"); // Name of the Report
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); // Location of the chart
		htmlReporter.config().setTheme(Theme.DARK);
	}
	
	public void onTestSuccess(ITestResult tr)
	{
		logger = extent.createTest(tr.getName());// create new entry in the report
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));// send the passed information
	}
	
	public void onTestFailure(ITestResult tr)
	{
		logger = extent.createTest(tr.getName());// create new entry in the report
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));// send the failed information
		
		String screenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		
		File f = new File(screenshotPath);
		
		if (f.exists())
		{
			try {
				logger.fail("Screenshot is below:"+logger.addScreenCaptureFromPath(screenshotPath));
			    }
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		logger = extent.createTest(tr.getName());// create new entry in the report
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));// send the passed information
	}
	
   public void onFinish(ITestContext testContext)
	{
	extent.flush();
    }
		

}
