package utility;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Report 
{
  public static void main(String[] args)
  {
	  ExtentReports report = new ExtentReports("d:\\Report.html",true,DisplayOrder.OLDEST_FIRST);
	  report.config().documentTitle("Report Title");
	  report.config().reportHeadline("HeadLine Avinash");
	  ExtentTest test = report.startTest("Login", "Login to Facebook");
	  test.log(LogStatus.PASS, "Navigate", "Navigate to http://www.facebook.com");
	  test.log(LogStatus.PASS, "type", "Enter username as Avinash");
	  test.log(LogStatus.PASS, "type", "Enter password as Avinash");
	  test.log(LogStatus.FAIL, "Click", "Click on LogIN button");
	  report.endTest(test);
	  
	  ExtentTest test2 = report.startTest("Login", "Login to Gmail");
	  test2.log(LogStatus.PASS, "Navigate", "Navigate to <b> http://www.facebook.com");
	  test2.log(LogStatus.PASS, "type", "Enter username as <b>Avinash");
	  test2.log(LogStatus.PASS, "type", "Enter password as <b>Avinash");
	  test2.log(LogStatus.PASS, "Click", "Click on LogIN button");
	  report.endTest(test2);
	  
	  report.flush();
	  report.close();
	  
	  
	  
  }
}
