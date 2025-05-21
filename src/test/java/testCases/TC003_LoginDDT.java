package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.MyAccountpage;
import pageObjects.loginpage;
import testBase.Baseclass;
import utilities.DataProviders;

public class TC003_LoginDDT extends Baseclass{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups= {"DataDriven","Master"})
	public void verify_loginDDT(String email, String password, String exp) throws InterruptedException
	{
		logger.info(" *********** Starting TC002_Logintest *************");
		
		
		logger.info("clicking on register in homepage ");
		Homepage hp=new Homepage(driver);
		logger.info("clicked on homepage button");
		hp.clickmyaccount();
		logger.info("clicked on login button");
		hp.clicklogin();
		
		logger.info("entering login details");
		loginpage lp=new loginpage(driver);
		lp.setemail(email);
		lp.setpassword(password);
		lp.clicklogin();
		
		logger.info("verifying whether Account page is displayed or not");
		MyAccountpage macc=new MyAccountpage(driver);
		boolean targetpage=macc.isMyAccountPageDisplayed();
		
		/*
		 * Data is Valid  --> Login success - test pass  - logout
		 *                    Login failed - test fail
		 *                    
		 *  Data is InValid  -->Login success - test fail  - logout
		 *                    Login failed - test pass
		 */
		try
		{
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetpage==true)
			{
			    macc.setclicklogout();
			    AssertJUnit.assertTrue(true);
		    }
			else
			{
			AssertJUnit.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("InValid"))
		{
			if(targetpage==true)
			{
			    macc.setclicklogout();
			    AssertJUnit.assertTrue(false);
		    }
			else
			{
			AssertJUnit.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			AssertJUnit.fail();
		}
			
		logger.info(" *********** finishing TC002_Logintest *************");
		
	}

}
