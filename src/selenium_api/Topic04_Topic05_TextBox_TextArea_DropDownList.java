package selenium_api;

import org.testng.annotations.Test;


import org.testng.annotations.BeforeClass;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;


public class Topic04_Topic05_TextBox_TextArea_DropDownList {
	WebDriver driver;
	WebDriverWait wait;
	
	 @BeforeClass
	  public void beforeClass() {
		 System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		 driver = new ChromeDriver();
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 driver.manage().window().maximize();
		 
	  }
	 
	 
	 @Test (enabled = false)
	  public void TC01_HandleDropdownList() {
		 String actualValue;
		 driver.get("http://daominhdam.890m.com/");
		 driver.findElement(By.xpath("//option[@value='mobile']")).click();
		 //get element of dropdown list
		 Select select = new Select(driver.findElement(By.id("job1")));
		 Assert.assertFalse(select.isMultiple());
		 
		 //Use selectVisible method to select the value
		 select.selectByVisibleText("Automation Tester");
		 actualValue = select.getFirstSelectedOption().getText();
		 Assert.assertEquals("Automation Tester", actualValue);
		 
		 //Use selectValue method to select the value
		 select.selectByValue("manual");
		 actualValue = select.getFirstSelectedOption().getText();
		 Assert.assertEquals("Manual Tester", actualValue);
		 
		 //Use selectIndex method to select the value
		 select.selectByIndex(3);
		 actualValue = select.getFirstSelectedOption().getText();
		 Assert.assertEquals("Mobile Tester", actualValue);
		 
		 //Verify the number of dropdown list
		 int value = select.getOptions().size();
		 Assert.assertEquals(5, value);
		 
	  }
	 
	 @Test (enabled = false)
	 public void TC02_HandleTextbox_TextArea() throws InterruptedException {
		 Customer cus = new Customer();
		 driver.get("http://demo.guru99.com/v4");
		 WebElement emailElement = driver.findElement(By.xpath("//input[@type='text']"));
		 emailElement.clear();
		 emailElement.sendKeys("mngr145100");
		 WebElement passElement = driver.findElement(By.xpath("//input[@type='password']"));
		 passElement.clear();
		 passElement.sendKeys("deqetut");
		 passElement.submit();
		 driver.findElement(By.xpath("//a[contains(text(),'New Customer')]")).click();
		 Thread.sleep(2000);
		//input name
		// WebElement nameElement = driver.findElement(By.xpath("//input[@onblur='validatecustomername();']"));
		 driver.findElement(By.xpath("//input[@onblur='validatecustomername();']")).sendKeys(cus.name);
		 
		 //  	 gender
		 driver.findElement(By.xpath("//input[@type='radio'and @value='f']")).click();
		 
		 //input birthday
		 driver.findElement(By.xpath("//input[@id='dob']")).sendKeys(cus.birth);
		 
		 //input address
		 WebElement addressElement = driver.findElement(By.xpath("//textarea[@onblur='validateAddress();']"));
		 addressElement.sendKeys(cus.address);
		 
		 //input city
		 WebElement cityElement = driver.findElement(By.xpath("//input[@onblur='validateCity();']"));
		 cityElement.sendKeys(cus.city);
		 
		 //input state
		 driver.findElement(By.xpath("//input[@onblur='validateState();']")).sendKeys(cus.state);
		 
		 //input pin and mobile number
		 driver.findElement(By.xpath("//input[@onblur='validatePIN();']")).sendKeys(cus.pin);
		 driver.findElement(By.xpath("//input[@onblur='validateTelephone();']")).sendKeys(cus.mobileNumber);
		 
		 //input email and pasword
		 driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(RandomEmail.getRandomEmail());
		 driver.findElement(By.xpath("//input[@onblur='validatepassword();']")).sendKeys(cus.mobileNumber);
		 driver.findElement(By.xpath("//input[@type='submit']")).click();
		 
		 String customerid = driver.findElement(By.xpath("//td[contains(text(),'Customer ID')]/following-sibling::td")).getText();
		 driver.findElement(By.xpath("//a[contains(text(),'Edit Customer')]")).click();
		 driver.findElement(By.xpath("//input[@onblur='validatecustomerid();']")).sendKeys(customerid);
		 driver.findElement(By.xpath("//input[@type='submit']")).click();
		 
		 Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Edit Customer')]")).isDisplayed());
		 
		 //Verify customer name and address
		 Assert.assertEquals(cus.name, driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"));
		 Assert.assertEquals(cus.address, driver.findElement(By.xpath("//textarea[@onblur='validateAddress();']")).getAttribute("value"));
		 
		 //edit address and city
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 WebElement addr = driver.findElement(By. xpath( "//textarea[@name='addr']"));
		 js.executeScript( "arguments[0].removeAttribute('disabled')" ,addr );
		 addr.clear();
		 addr.sendKeys("Address02");
		 
		 WebElement city = driver.findElement(By. xpath( "//input[@name='city']"));
		 js.executeScript( "arguments[0].removeAttribute('disabled')" ,city );
		 city.clear();
		 city.sendKeys("DaNang");
		 driver.findElement(By.xpath("//input[@type='submit']")).click();
		 Assert.assertEquals("Address02", driver.findElement(By.xpath("//td[contains(text(),'Address')]/following-sibling::td")).getText());
		 Assert.assertEquals("DaNang", driver.findElement(By.xpath("//td[contains(text(),'City')]/following-sibling::td")).getText());
		 
		 	 
	 }
	 
	 
	 @Test (enabled = false)
	  public void TC03_HandleCustomDropdownList() {
		 driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		 List<WebElement> listNumber = driver.findElements(By.xpath("//*[@id=\"number\"]/option"));
		 WebElement element = driver.findElement(By.xpath("//*[@id=\"number\"]"));
		 ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('style')", element);
		 for(int i = 0; i < listNumber.size(); i++) {
			 System.out.println(listNumber.get(i).getAttribute("value"));
			 int temp = Integer.parseInt(listNumber.get(i).getAttribute("value"));
			 if (temp == 19) {
				 listNumber.get(i).click();             
		         break;
		        }
		 }
	 }
	 
	 
	 @Test 
	  public void TC04_HandleCustomDropdownList() throws InterruptedException{
		 driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		 selectCustomDropdownList("//span[@id='speed-button']", "//ul[@id='speed-menu']//li[@class='ui-menu-item']/div", "Fast");
		 Assert.assertTrue(driver.findElement(By.xpath("//span[@id='speed-button']//span[@class='ui-selectmenu-text' and text()='Fast']")).isDisplayed());
		 
		 selectCustomDropdownList("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']//div", "19");
		 Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());
		 
		 driver.get("https://material.angular.io/components/select/examples");
		 selectCustomDropdownList("//span[@class='mat-select-placeholder ng-tns-c21-4 ng-star-inserted']", "//mat-option//span", "Pizza");
		 Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mat-select-value']//span[text()='Pizza']")).isDisplayed());
		 
		 driver.get("https://material.angular.io/components/select/examples");
		 selectCustomDropdownList("//mat-select[@placeholder='Panel color']", "//mat-option/span", "Green");
		 //Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mat-select-value']//span[text()='Green']")).isDisplayed());
		 
		 
	 }
	 
	 public void selectCustomDropdownList(String dropdown, String listitem, String valueitem) throws InterruptedException {
		 WebDriverWait wait = new WebDriverWait(driver, 20);
		 WebElement element = driver.findElement(By.xpath(dropdown));
		 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		 element.click();
		// Thread.sleep(3000);
		 List<WebElement> allitem = driver.findElements(By.xpath(listitem));
		 wait.until(ExpectedConditions.visibilityOfAllElements(allitem));
		 for(WebElement item : allitem) {
				if(item.getText().equals(valueitem)) {	
					//System.out.println(driver.findElement(By.xpath("//mat-option/span")).getText());
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);	
					item.click();
					Thread.sleep(3000);
					break;
			}
		 }
	 }
	 
//
	 


	 @AfterClass
	  public void afterClass() {
		  driver.quit();
	  }
 

}
