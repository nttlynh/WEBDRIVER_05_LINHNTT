package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;


import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic09_JAVASCRIPT_EXECUTOR {
	WebDriver driver;
	String os = System.getProperty("os.name").toLowerCase();
	@BeforeClass
	  public void beforeClass() {
		if (os.indexOf( "win" ) >= 0) {
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		if (os.indexOf( "mac" ) >= 0) {
			System.setProperty("webdriver.chrome.driver", "driver//chromedriver");
			driver = new ChromeDriver();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	  }
	  @Test 
	  public void TC01() {
		  //Step 01 - Truy cập vào trang: http://live.guru99.com/
		  driver.get("http://live.guru99.com/");
		  
		 // Step 02 - Sử dụng JE để get domain của page, Verify domain =  live.guru99.com
		  
		  String domain = (String) executeForBrowserElement("return document.domain;");
		  Assert.assertEquals(domain, "live.guru99.com");
		  
		  //Step 03 - Sử dụng JE để get URL của page, Verify URL =  http://live.guru99.com/
		  String urlHome = (String) executeForBrowserElement("return document.URL;");
		  Assert.assertEquals(urlHome, "http://live.guru99.com/");
		  
		  //Step 04 - Open MOBILE page (Sử dụng JE)
		  WebElement mobileElement = driver.findElement(By.xpath("//a[contains(text(), 'Mobile')]"));
		  clickForWebElement(mobileElement);
		  
		  //Step 05 - Add sản phẩm SAMSUNG GALAXY vào Cart (click vào ADD TO CART button bằng JE)
		  WebElement addCart = driver.findElement(By.xpath("//h2/a[@title='Samsung Galaxy']/../following-sibling::div/button"));
		  clickForWebElement(addCart);
		  
		  //Step 06 - Verify message được hiển thị:  Samsung Galaxy was added to your shopping cart. (Sử dụng JE - Get innertext of the entire webpage )
		//span[contains(text(), 'Samsung Galaxy was added to your shopping cart.')]
		  String sText = (String) executeForBrowserElement("return document.documentElement.innerText;");
		  Assert.assertTrue(sText.contains("Samsung Galaxy was added to your shopping cart."));
		  
//		  Step 07 - Open PRIVACY POLICY page (Sử dụng JE), Verify title của page = Privacy Policy (Sử dụng JE)
		  WebElement privacyElement = driver.findElement(By.xpath("//a[contains(text(), 'Privacy Policy')]"));
		  clickForWebElement(privacyElement);
		  String titlePrivacy = (String) executeForBrowserElement("return document.title;");
		  Assert.assertEquals(titlePrivacy, "Privacy Policy");
		  
//		  Step 08 - Srcoll xuống cuối page
		  scrollToBottomPage(driver);
		  
//		  Step 09 - Verify dữ liệu có hiển thị với chỉ 1 xpath: 
		  WebElement wishText = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']"));
		  Assert.assertTrue(wishText.isDisplayed());
		  
//		  Step 10 - Navigate tới domain: http://demo.guru99.com/v4/  (Sử dụng JE) Verify domain sau khi navigate = http://demo.guru99.com/v4/ 
		  JavascriptExecutor js = (JavascriptExecutor)driver;
		  js.executeScript("window.location = 'http://demo.guru99.com/v4/'");
		  String domainHome = (String) executeForBrowserElement("return document.domain;");
		  Assert.assertEquals(domainHome, "demo.guru99.com");
		
	  }
	  
	  
	  @Test
	  public void TC02() {
//		  Step 01 - Truy cập vào trang: https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled
		  driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
		  
//		  Step 02 - Remove thuộc tính disabled của field Last name
//		  Switch qua iframe nếu có
		  driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id = 'iframeResult']")));
		  WebElement lname = driver.findElement(By.xpath("//input[@name='lname']"));
		  removeAttributeInDOM(lname, "disabled");
		  
//		  Step 03 - Sendkey vào field Last name
		  lname.sendKeys("Linh Nguyen");
		  
		  clickForWebElement(driver.findElement(By.xpath("//input[@type='submit']")));
//		  Step 04 - Click Submit button
//		  Step 05 - Verify dữ liệu sau khi submit chứa đoạn text đã fill trong field Lastname (Automation Testing)
		  String sText = (String) executeForBrowserElement("return document.documentElement.innerText;");
		  Assert.assertTrue(sText.contains("Linh Nguyen"));
		
	  }
	  
	  
//	  	Highlight an element:
		  public void highlightElement(WebElement element) {
			  JavascriptExecutor js = (JavascriptExecutor) driver;
              js.executeScript("arguments[0].style.border='6px groove red'", element);
		  }
		  
//		Execute for Browser
		  public Object executeForBrowserElement(String javaSript) {
			  try {
                  JavascriptExecutor js = (JavascriptExecutor) driver;
                  return js.executeScript(javaSript);
			  } catch (Exception e) {
                  e.getMessage();
                  return null;
			  }
		  	}	
		  
//		  Execute for WebElement
		  public Object clickForWebElement(WebElement element) {
			  try {
                  JavascriptExecutor js = (JavascriptExecutor) driver;
                  return js.executeScript("arguments[0].click();", element);
			  } catch (Exception e) {
                  e.getMessage();
                  return null;
			  }
		  }
		  
//		  Remove attribute in DOM
		  public Object removeAttributeInDOM(WebElement element, String attribute) {
			  try {
                  JavascriptExecutor js = (JavascriptExecutor) driver;
                  return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
			  } catch (Exception e) {
				  	e.getMessage();
				  	return null;
			  }                      
		  }
		   

//		  Scroll to bottom page
		  public Object scrollToBottomPage(WebDriver driver) {
			  try {
                  JavascriptExecutor js = (JavascriptExecutor) driver;
                  return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			  } catch (Exception e) {
                  e.getMessage();
                  return null;
			  }                        
		  }
		                        
		                         
		              
		                          
		              
	  

	  @AfterClass
	  public void afterClass() {
		  driver.quit();
	  }


}
