package Travel_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class pageHome {
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath="//li[@id='li_myaccount'][1]")
	WebElement btnMyAccount;

	@FindBy(xpath="//a[text()=' Login'][1]")
	WebElement btnLogin;

	@FindBy(xpath="//a[text()='  Sign Up'][1]")
	WebElement btnSignUp;

	public pageHome(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,120);
	}

	public void waitElement(WebElement element){
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void clickMyAccount(){
		waitElement(btnMyAccount);
		btnMyAccount.click();
	}


}
