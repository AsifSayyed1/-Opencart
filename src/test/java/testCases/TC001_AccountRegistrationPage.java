package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationPage extends BaseClass{
	
	
	
	@Test(groups = {"Regression","Master"})
	public void verify_AccountRegristration()
	{
		try
		{
		logger.info("***** Started TC001_AccountRegistrationPage ****** ");
		
			HomePage p = new HomePage(driver);
		p.clickMyAccount();
		
		logger.info("***** CLick on Myaccount Link ****** ");

		p.clickRegister();
		logger.info("***** Click on Register Link ****** ");

		
	AccountRegistrationPage a = new AccountRegistrationPage(driver);
		
		logger.info("***** Providing Customer Details ****** ");

	//	a.setFirstName("Asif");
		a.setFirstName(randomString().toUpperCase());
	//	a.setLirstName("Sayyed");
		a.setLirstName(randomString().toUpperCase());
	//	a.setEmail("syedasif081@gmail.com");
		a.setEmail(randomString()+"@gmail.com");
		
	//	a.setTelephone("23474444");
		a.setTelephone(randomNumber());
		
		String password = randomAlphaNumeric();
		a.setPassword(password);
		a.setConfirmPassword(password);
		a.setCheckPolicy();
		a.setContinueButton();
		
		logger.info("***** Validate expected Message ****** ");

		String comf = a.getConfirmationMsg();
		
		if(comf.equalsIgnoreCase("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed....");
			logger.debug("Debug logs");
			Assert.assertTrue(false);
		}    
		
	//	Assert.assertEquals(comf, "Your Account Has Been Created!!!!!!");
		
		}
		catch(Exception e)
		{
		//	logger.error(e.getMessage()+"Test Failed....");
		//	logger.debug("Debug logs");
			
			Assert.fail();
		}
		
		logger.info("***** Finished TC001_AccountRegistrationPage ****** ");

		
	}			
}
