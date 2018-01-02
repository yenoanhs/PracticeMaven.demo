package Lazada_Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSuite {


	@Test
	public void openWeb() {

		String pathProject = System.getProperty("user.dir");
//		System.setProperty("webdriver.gecko.driver", pathProject + "\\src\\main\\resources\\geckodriver.exe");
//		WebDriver driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", pathProject + "\\src\\main\\resources\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		String linkURL = "http://www.lazada.sg/";
		driver.get(linkURL);

		String temp=driver.getTitle();
		System.out.println(temp);
		Assert.assertTrue(temp.contains("Lazada"));

		// DashBoard oDashBoard = new DashBoard(driver);
		driver.close();



	}


	@Test
	public void Equal() {
		Assert.assertEquals(1, 1);
	}


	@Test
	public void NotEqual() {
		Assert.assertEquals(1, 2);
	}

}
