package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(id="input-email") WebElement Email;
	@FindBy(id="input-password") WebElement Password;
	@FindBy(xpath="//input[@value='Login']") WebElement Button;
	
	public void setemail(String Em)
	{
		Email.sendKeys(Em);
	}
	
	public void setPass(String P)
	{
		Password.sendKeys(P);
	}
	
	public void setbutton()
	{
		Button.click();
	}
	
	

}
