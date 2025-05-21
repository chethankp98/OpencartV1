package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginpage extends Basepage{

	public loginpage(WebDriver driver) 
	{
		super(driver);
		
	}
	
	// locators
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtemail;
	
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtpassword;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btnlogin;
	
	
	// Action methods
	public void setemail(String email)
	{
		txtemail.sendKeys(email);
	}
    
	public void setpassword(String password)
	{
		txtpassword.sendKeys(password);
	}
	
	public void clicklogin()
	{
		btnlogin.click();
	}
}
