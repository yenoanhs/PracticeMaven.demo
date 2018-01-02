package Lazada_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import init.pageBase;

public class DashBoard extends pageBase{

	public DashBoard(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//img[@class='l-logo__image']")
	WebElement lnkLogo;

	@FindBy(xpath="//a[text()='Signup']")
	WebElement lnkSignup;

	@FindBy(xpath="//a[@title='Login']")
	WebElement lnkLogin;

	@FindBy(xpath="//span[contains(text(),'Track my order')]")
	WebElement lnkTrackMyOrder;

	@FindBy(xpath="//span[contains(text(),'Customer Care')]")
	WebElement lnkCustomerCare;

//	@FindBy(xpath="")
//	WebElement ;

	public void clickLogo()
	{
		clickAction(lnkLogo);
	}

	public void clickSignup()
	{
		clickAction(lnkSignup);
	}

	public void clickLogin()
	{
		clickAction(lnkLogin);
	}

	public void clickTrackMyOrder()
	{
		clickAction(lnkTrackMyOrder);
	}

	public void clickCustomerCare()
	{
		clickAction(lnkCustomerCare);
	}
}
