package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	
	
	public ExtentSparkReporter sparkrepoter; // UI of the report
	public ExtentReports extent; // populate common info on the report
	public ExtentTest test; // creating test case entries in the report and update status of the test methods
	String repName;
	
	  public void onStart(ITestContext context)  // creating UI of the report
	  {
		  
		/**  SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		  Date dt = new Date();
		  String currentdatetimestamp = df.format(dt);  */
		  
		  String timeStamp = new SimpleDateFormat("yyyy.MM.dd..HH.mm.ss").format(new Date()); // Timestamp
		  repName = "Test-Report-"+ timeStamp + ".html";
		  
		  sparkrepoter = new ExtentSparkReporter(".\\reports\\" + repName); // TO MAINTAIN HISTORY // repName
		  
		  sparkrepoter.config().setDocumentTitle("opencart Automation Report"); //Title of Report
		  sparkrepoter.config().setReportName("opencart Functional Testing");  // name of the report
		  sparkrepoter.config().setTheme(Theme.DARK);
		  
		  extent = new ExtentReports(); // populate common details on the report
		  extent.attachReporter(sparkrepoter);
		  
		  extent.setSystemInfo("Application", "opencart");
		  extent.setSystemInfo("Module", "Admin");
		  extent.setSystemInfo("Sub Module", "Customers");
		  extent.setSystemInfo("User Name", System.getProperty("user.name"));  // return Testername // Username
		  extent.setSystemInfo("Environment", "QA");
		  
		  String os = context.getCurrentXmlTest().getParameter("os");
		  extent.setSystemInfo("Operating System", os);
		  
		  String browser = context.getCurrentXmlTest().getParameter("browser");
		  extent.setSystemInfo("browser", browser);
		  
		  List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		  if(!includedGroups.isEmpty())
		  {
			  extent.setSystemInfo("Groups", includedGroups.toString());
		  }
		  
	  }


	  public void onTestSuccess(ITestResult result) {
		 
		  test=extent.createTest(result.getTestClass().getName()); // create a new entry in the report
		  test.assignCategory(result.getMethod().getGroups());
		  test.log(Status.PASS, result.getName()+"Test case Passed is:");  // update status
		  
		  } 
		  
		  
	  public void onTestFailure(ITestResult result) {
		  
		  test = extent.createTest(result.getTestClass().getName());
		  test.assignCategory(result.getMethod().getGroups());
		  test.log(Status.FAIL, "Testcase Failed :"+result.getName());
		  test.log(Status.INFO, result.getThrowable().getMessage());
		  
		  try {
			  
			  String imgPath = new BaseClass().captureScreen(result.getName());  // attaching screenshot to report of failure test
			  test.addScreenCaptureFromPath(imgPath);
		  }
		  catch(Exception e1)
		  {
			  e1.printStackTrace();
		  }
		  
		   } 
		  
	  public void onTestSkipped(ITestResult result) {
		    
		  test = extent.createTest(result.getTestClass().getName());
		  test.assignCategory(result.getMethod().getGroups());

		  test.log(Status.SKIP, "got Skipped:"+result.getName());
		  test.log(Status.INFO, result.getThrowable().getMessage());

		   
		  }
	  
	  public void onFinish(ITestContext context) {
		    extent.flush();
		    
		          // Report will open automatically
		    String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		    
		    File extentReport = new File(pathOfExtentReport);
		    
		    try {
		    Desktop.getDesktop().browse(extentReport.toURI()); // open the report in browser automatically
		    }
		    catch(IOException e)
		    {
		    	e.printStackTrace();
		    }
		    
		  } 
		  
	  
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
