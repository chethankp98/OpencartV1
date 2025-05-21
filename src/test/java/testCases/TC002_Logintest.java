package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.MyAccountpage;
import pageObjects.loginpage;
import testBase.Baseclass;

public class TC002_Logintest extends Baseclass{
	
	@Test(groups= {"Regression","Master"})
	public void verify_login() throws InterruptedException
	{
		logger.info(" *********** Starting TC002_Logintest *************");
		
		logger.info("clicking on register in homepage ");
		Homepage hp=new Homepage(driver);
		hp.clickmyaccount();
		logger.info("clicked on homepage button");
		Thread.sleep(1000);
		hp.clicklogin();
		
		logger.info("enetering login details");
		loginpage lp=new loginpage(driver);
		lp.setemail(p.getProperty("email"));
		lp.setpassword(p.getProperty("password"));
		lp.clicklogin();
		
		logger.info("verifying whether Account page is displayed or not");
		MyAccountpage macc=new MyAccountpage(driver);
		boolean targetpage=macc.isMyAccountPageDisplayed();
		
		//Assert.assertEquals(targetpage, true, "login failed");
		AssertJUnit.assertTrue(targetpage);
		
		logger.info(" *********** finishing TC002_Logintest *************");
		
	}

}
