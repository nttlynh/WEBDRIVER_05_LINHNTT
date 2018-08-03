package selenium_api;

import org.testng.annotations.Test;

import org.testng.Assert;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic06_Button_RadioButton_Checkbox_Alert {
	WebDriver driver;
	@BeforeClass
	  public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	  }
	
	 @Test
	  public void TC_01() {
		 driver.get("http://live.guru99.com/");
		 driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		 Assert.assertTrue(driver.findElement(By.xpath("//div[@class='account-login']")).isDisplayed());
		 String URLlogin = driver.getCurrentUrl();
		 Assert.assertEquals("http://live.guru99.com/index.php/customer/account/login/", URLlogin);
		 
		 
		 String locator = "//a[@class='button']";
		 clickElementByJavascript(locator);
		 String URLCreate = driver.getCurrentUrl();
		 Assert.assertEquals("http://live.guru99.com/index.php/customer/account/create/", URLCreate);
		 
		 
	  }
	 
	 @Test
	 public void TC_02(){
		 driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		 String locator = "//label[text()='Dual-zone air conditioning']/preceding-sibling::input";
		 clickElementByJavascript(locator);
		 Assert.assertTrue(isElementSelected(locator));
		 
		 clickElementByJavascript(locator);
		 Assert.assertTrue(!isElementSelected(locator));
		 
		 
	 }
	 
	 public void clickElementByJavascript(String locator) {
		 WebElement element = driver.findElement(By.xpath(locator));
		 JavascriptExecutor je = (JavascriptExecutor) driver;
		 je.executeScript("arguments[0].click();", element);
		    
	 }
	 
	 public boolean isElementSelected(String locator) {
		 WebElement element = driver.findElement(By.xpath(locator));
		 return element.isSelected();
		 
	 }
	  
	
	  @AfterClass
	  public void afterClass() {
		  driver.quit();
	  }

}
