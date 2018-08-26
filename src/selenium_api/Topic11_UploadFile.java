package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class Topic11_UploadFile {
	WebDriver driver;
	String projectDirectory = System.getProperty("user.dir");
	String uploadFilePath = projectDirectory + "/img/img01.png";
	@BeforeClass
	  public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "driver//chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	  }
	
	
	@Test
	  public void TC01_Sendkey() {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		WebElement uploadElement = driver.findElement(By.xpath("//input[@type='file']"));
		uploadElement.sendKeys(uploadFilePath);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='img01.png']")).isDisplayed());
		
	  }
	  

	  @AfterClass
	  public void afterClass() {
	  }
  

}
