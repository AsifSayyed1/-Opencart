package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;



import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
public static WebDriver driver;  // static is use for utilities of extentreportmanager there baseclass object can call
public Logger logger;	  // log4j
public Properties p;

	@SuppressWarnings("deprecation")
	@BeforeClass(groups = {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os ,String br) throws IOException
	{
		// loading config.properties
		FileReader f = new FileReader("./src//test//resources//config.properties");
		
		p = new  Properties();
		
		p.load(f);
		
		
		logger=LogManager.getLogger(this.getClass());  // this will log to any class at runtime
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			
			DesiredCapabilities cap = new DesiredCapabilities();
			
			// os
			if(os.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				cap.setPlatform(Platform.LINUX);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.MAC);
	
			}
			else 
			{
				System.out.println("No matching os");
				return;
			}
		   
			// browser
		
			switch(br.toLowerCase())
			{
			case "chrome" : cap.setBrowserName("chrome"); break;
			case "edge" : cap.setBrowserName("MicrosoftEdge"); break;
			case "firefox" : cap.setBrowserName("firefox"); break;

			
			default: System.out.println("No matching browser");
			return;
			}	
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case "chrome" : driver=new ChromeDriver(); break;
			case "edge" : driver= new EdgeDriver();  break;
			case "firefox" : driver= new FirefoxDriver();  break;
			
			default : System.out.println("Invalid Browser"); return;
			}
			
			

		}
		
		
		
	
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	//	driver.get("https://tutorialsninja.com/demo/");
		driver.get(p.getProperty("appURL"));  // Config.Properties
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups = {"Sanity","Regression","Master"})
	public void teardown()
	{
		
	}
	
	public String randomString()
	{// predefined class
		
		String generatedstring =RandomStringUtils.randomAlphabetic(5);
		
		
		return generatedstring;
	}
	
	public String randomNumber()
	{
		String generatednumber = RandomStringUtils.randomNumeric(10);
		
		return generatednumber;
	}
	
	public String randomAlphaNumeric()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		
		return (generatedString+"@"+generatedNumber);
	}
	
	
	public String captureScreen(String tname)
	{
		  String timeStamp = new SimpleDateFormat("yyyy.MM.dd..HH.mm.ss").format(new Date()); // Timestamp
		  TakesScreenshot takescreenshot = (TakesScreenshot) driver;
		  
		  File sourcefile = takescreenshot.getScreenshotAs(OutputType.FILE);
		  
		  String targetFilepath = System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		  File targetfile = new File(targetFilepath);
		  
		  sourcefile.renameTo(targetfile);
		  
		return targetFilepath;

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
