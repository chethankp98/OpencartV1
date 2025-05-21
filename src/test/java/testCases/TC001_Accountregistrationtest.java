package testCases;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import testBase.Baseclass;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Accountregistrationpage;
import pageObjects.Homepage;
import testBase.Baseclass;

public class TC001_Accountregistrationtest extends Baseclass {
	
	
	@Test(groups= {"Sanity","Master"})
	public void verify_Accountregistration()
	{
		try
		{
			
		logger.info("********** Starting Execution of TC001_Accountregistrationtest ************");
		Homepage hp=new Homepage(driver);
		Thread.sleep(1000);
		hp.clickmyaccount();
		logger.info("clicked on homepage button");
		Thread.sleep(1000);
		hp.clickregister();
		logger.info("clicked on register button");
		
		Accountregistrationpage regpage= new Accountregistrationpage(driver);
		Thread.sleep(1000);
		logger.info("Providing customer details....");
		regpage.setfirstname(randomString());
		regpage.setlastname(randomString());
		regpage.setemail(randomString()+"@gmail.com");
		regpage.settelephone(randomNumber());
		String password=randomAlphanumeric();
		regpage.setpassword(password);
		regpage.setconpassword(password);
		regpage.setchckpolicy();
		regpage.setbtnContinue();
		
		logger.info("Validating expected message..");
		String conmsg=regpage.msgconfirmation();
		AssertJUnit.assertEquals(conmsg,"Congratulations! Your new account has been successfully created!");
		
		regpage.setlogout();
		regpage.setclkcontinuebtn();
		}
		catch(Exception e) 
		{
		    logger.error("Test failed...", e);
		    AssertJUnit.fail(e.getMessage());
		}
		logger.info("********** Finished TC001_Accountregistrationtest ************");
		
	}

}
