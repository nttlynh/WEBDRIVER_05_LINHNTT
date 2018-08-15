package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic08_IFRAME_WINDOW_POPUP {
	WebDriver driver;
	WebDriverWait wait;
	@BeforeClass
	  public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 20);
	  }
	
	
	@Test
	  public void TC01() throws InterruptedException {
		driver.get("http://www.hdfcbank.com/");
		// find  the total number of iframe
		
		int size = driver.findElements(By.tagName("iframe")).size();
		
		//Step 02 - Close popup nếu có hiển thị (switch qua iframe nếu có)  - F5 (refresh page) nhiều lần thì sẽ xuất hiện popup
		By popupBy = By.id("vizury-notification-template");
		if(isDisplayElement(popupBy)) {
			driver.switchTo().frame(driver.findElement(popupBy));
			driver.findElement(By.xpath("//*[@id='div-close']")).click();;
		}
		
		//Step 03 - Verify đoạn text được hiển thị:  What are you looking for? (switch qua iframe nếu có)
		driver.switchTo().defaultContent();
		WebElement textElement = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe[starts-with(@id, 'viz_iframe')]"));
		driver.switchTo().frame(textElement);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='messageText']")).getText(), "What are you looking for?");
		
		//Step 04:Verify banner image được hiển thị (switch qua iframe nếu có)//Verify banner có đúng 6 images
		driver.switchTo().defaultContent();
		WebElement bannerElement = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe[starts-with(@id,'viz_iframe')]"));
		driver.switchTo().frame(bannerElement);
		
		By locator = By.xpath("//div[@id='productcontainer']//img");
		List<WebElement> listBanner = driver.findElements(locator);
		Assert.assertEquals(listBanner.size(), 6);
		for(int j=0; j < listBanner.size(); j++) {
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			System.out.println("Linh");
		}
		
		
		//Step 05 - Verify flipper banner được hiển thị và có 8 items
		driver.switchTo().defaultContent();
		List<WebElement> imgElement = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
		Assert.assertEquals(imgElement.size(), 8);
		for(int i=0; i < imgElement.size(); i++) {
			System.out.println(imgElement.get(i));
			Assert.assertTrue(imgElement.get(i).isDisplayed());
			System.out.println("It is displayed");
		}
		
	  }
	
	/*Test Script 02:
		Step 01 - Truy cập vào trang: http://daominhdam.890m.com/
		Step 02 - Click "Opening a new window: Click Here" link -> Switch qua tab mới
		Step 03 - Kiểm tra title của window mới = Google
		Step 04 - Close window mới
		Step 05 - Switch về parent window
		*/
	
	@Test
	  public void TC02(){
		driver.get("http://daominhdam.890m.com/");
		driver.findElement(By.xpath("//a[contains(text(),'Click Here')]")).click();
		
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
