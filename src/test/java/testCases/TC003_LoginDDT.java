package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	
	
	@Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class,groups = "Datadriven") // getting dataprovider from different class
	public void verifyDDT (String email , String pwd , String expresult) throws InterruptedException
	{
		
		logger.info("Started TC003");
		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage p1 = new LoginPage(driver);
		
		p1.setemail(email);
		
		p1.setPass(pwd);
		
		p1.setbutton();
		
		
		MyAccountPage mc = new MyAccountPage(driver);
		boolean target = mc.isMyAccountExists();
	//	mc.Logout();
		
		
		if(expresult.equalsIgnoreCase("Valid"))
		{
			if(target==true)
			{
				mc.Logout();
				Assert.assertTrue(true);
				
			}
			else
			{   
				
				Assert.assertTrue(false);
			}
		}
		if(expresult.equalsIgnoreCase("inValid"))
		{
			if(target==true)
			{
		
			Assert.assertTrue(false);
			mc.Logout();
		}
		else
		{
			
			Assert.assertTrue(true);
		}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		Thread.sleep(3000);
		
		logger.info("TestCase TC003 Passed");
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	}
}
