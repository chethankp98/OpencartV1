package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Accountregistrationpage extends Basepage{

	public Accountregistrationpage(WebDriver driver) 
	{
		super(driver);
		
	}
	
	// locators
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtfirstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtlastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtemail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txttelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtpassword;

	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtconpassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chckpolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//p[contains(text(),'Congratulations! Your new account has been success')]")
	WebElement msgconfirmation;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement logout;
	
	@FindBy(xpath="//a[normalize-space()='Continue']")
	WebElement clkcontinuebtn;
	
	// Action methods
		public void setfirstname(String fname)
		{
			txtfirstname.sendKeys(fname);
		}
		
		public void setlastname(String lname)
		{
			txtlastname.sendKeys(lname);
		}

		public void setemail(String email)
		{
			txtemail.sendKeys(email);
		}
		
		public void settelephone(String telephone)
		{
			txttelephone.sendKeys(telephone);
		}
		
		public void setpassword(String password)
		{
			txtpassword.sendKeys(password);
		}
		
		public void setconpassword(String password)
		{
			txtconpassword.sendKeys(password);
		}
		
		public void setchckpolicy()
		{
			chckpolicy.click();
		}
		
		public void setbtnContinue()
		{
			btnContinue.click();
		}
		
		public String msgconfirmation()
		{
			try
			{
				return msgconfirmation.getText();
			}
			catch(Exception e)
			{
				return(e.getMessage());
			}	
		}
		
		public void setlogout()
		{
			logout.click();
		}
		
		public void setclkcontinuebtn()
		{
			clkcontinuebtn.click();
		}
}
