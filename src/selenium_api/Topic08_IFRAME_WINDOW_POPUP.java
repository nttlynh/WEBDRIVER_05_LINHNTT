package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic08_IFRAME_WINDOW_POPUP {
	WebDriver driver;
	@BeforeClass
	  public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	  }
	
	
	@Test
	  public void TC01() throws InterruptedException {
		driver.get("http://www.hdfcbank.com/");
		// find  the total number of iframe
		/*
		int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println(size);
		By popupBy = By.id("vizury-notification-template");
		if(isDisplayElement(popupBy)) {
			driver.switchTo().frame(driver.findElement(popupBy));
			driver.close();
		}
		*/
		WebElement textElement = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe[starts-with(@id, 'viz_iframe')]"));
		driver.switchTo().frame(textElement);
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='messageText']")), "What are you looking for?");
		
		
	  }
	  
	public boolean isElementExists(By by) {
	    boolean isExists = true;
	    try {
	        driver.findElement(by);
	    } catch (NoSuchElementException e) {
	        isExists = false;
	    }
	    return isExists;
	}
	
	public boolean isDisplayElement(By by) {
		try {
			return driver.findElement(by).isDisplayed();
			
		}catch (NoSuchElementException e) {
			return false;
		}
	}

	  @AfterClass
	  public void afterClass() {
		  driver.quit();
	  }
  

}
