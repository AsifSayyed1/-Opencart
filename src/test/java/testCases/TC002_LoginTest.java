package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups = {"Sanity","Master"})
	public void VerifyLoginTest()
	{
		logger.info("Starting Login Test");
		
		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage p1 = new LoginPage(driver);
		
		p1.setemail(p.getProperty("email"));
		
		p1.setPass(p.getProperty("password"));
		
		p1.setbutton();
		
		
		MyAccountPage mc = new MyAccountPage(driver);
		boolean target = mc.isMyAccountExists();
//		mc.Logout();
		Assert.assertEquals(target, true,"Login Failed");
	
		
		
	   //Assert.assertTrue(target);	
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("Testcases Completed");
		
		
		
		
		
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
