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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class Topic04_TextBox_TextArea_DropDownList {
	WebDriver driver;
	String name = "LinhNguyen";
	String birth = "15/06/1995";
	String address = "101 Nguyen Van dau,Phuong 5, Binh Thanh, HCM";
	String city = "hochiminh";
	String state = "hochiminh";
	long pin = 1234;
	String mobileNumber = "0975151444";
	String passWord = "Thaolinh01";
	
	 @BeforeClass
	  public void beforeClass() {
		 driver = new FirefoxDriver();
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
		 WebElement nameElement = driver.findElement(By.xpath("//input[@onblur='validatecustomername();']"));
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
		 Assert.assertEquals("Address02", driver.findElement(By.xpath("//textarea[@name='addr']")).getAttribute("value"));
		 Assert.assertEquals("DaNang", driver.findElement(By.xpath("//input[@name='city']")).getAttribute("value"));
		 
		 	 
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
			 System.out.println(temp);
			 if (temp == 19) {
				 System.out.println(temp);
				 listNumber.get(i).click();             
		         break;
		        }
		 }
	 }
	 
	 
	 @Test 
	  public void TC04_HandleCustomDropdownList() {
		 driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		 List<WebElement> listNumber = driver.findElements(By.xpath("//span[@id=\"number\"]/option"));
		 WebElement element = driver.findElement(By.xpath("//*[@id=\"number\"]"));
		 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		 for(int i = 0; i < listNumber.size(); i++) {
			 System.out.println(listNumber.get(i).getAttribute("value"));
			 int temp = Integer.parseInt(listNumber.get(i).getAttribute("value"));
			 System.out.println(temp);
			 if (temp == 19) {
				 System.out.println(temp);
				 listNumber.get(i).click();             
		         break;
		        }
		 }
	 }
	 
	 
	 @Test
	 public void TC02_CustomDropdownList() {
	  System.out.println("Start the second test!");
	  driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

	  WebElement numberDropdown = new WebDriverWait(driver, 15)
	    .until(ExpectedConditions.elementToBeClickable(By.id("number-button")));
	  numberDropdown.click();

	  List<WebElement> options = new WebDriverWait(driver, 15)
	    .until(ExpectedConditions.numberOfElementsToBe(By.xpath("//li[@class='ui-menu-item']/div"), 19));

	  for (int i = 0; i < options.size(); i++) {
	   String value = options.get(i).getText();
	   if (Integer.valueOf(value) == 19) {
	    options.get(i).click();
	   }
	  }
	  
	  try {
	   // Wait to ensure that element is selected.
	   Thread.sleep(5000);
	  } catch (InterruptedException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }

	  WebElement resultSelected = new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(
	    By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")));

	  String value = resultSelected.getText();

	  Assert.assertEquals("19", value);

	 }
	 /*Click vào dropdown
	 Wait để tất cả phần tử trong dropdown được hiển thị
	 Get tất cả item trong dropdown vào 1 list element (List <WebElement>)
	 Dùng vòng lặp for duyệt qua từng phần tử sau đó getText
	 Nếu actual text = expected text thì click vào phần tử đó và break khỏi vòng lặp */
	 

	 @AfterClass
	  public void afterClass() {
		  driver.quit();
	  }
 

}
