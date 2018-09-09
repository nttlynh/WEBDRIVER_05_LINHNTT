package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
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
		
		
		//Step 02 - Close popup nếu có hiển thị (switch qua iframe nếu có)  - F5 (refresh page) nhiều lần thì sẽ xuất hiện popup
		List<WebElement> iframeList = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		for(int i=0;i < iframeList.size(); i++) {
			if(iframeList.get(0).isDisplayed()) {
				driver.switchTo().frame(0);
				driver.close();
			}
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
	
	
	@Test 
	  public void TC02(){
		//Truy cập vào trang: http://daominhdam.890m.com/
		driver.get("http://daominhdam.890m.com/");
		
		//Click "Opening a new window: Click Here" link -> Switch qua tab mới
		driver.findElement(By.xpath("//a[contains(text(),'Click Here')]")).click();
	    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    
	    
	    //Kiểm tra title của window mới = Google
	    String title = driver.getTitle();
	    Assert.assertEquals(title, "Google");
		driver.close();
		
		//Switch về parent window
		driver.switchTo().window(tabs2.get(0));
		String title01 = driver.getTitle();
		System.out.println(title01);
		Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), "SELENIUM WEBDRIVER FORM DEMO");
	}
	
	
	@Test
	public void TC03() throws InterruptedException {
		driver.get("http://www.hdfcbank.com/");
		String parentWindown = driver.getWindowHandle();
		
		//Kiểm tra và close quảng cáo nếu có xuất hiện
		List<WebElement> iframeList = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		for(int i=0;i < iframeList.size(); i++) {
			if(iframeList.get(0).isDisplayed()) {
				System.out.println("Linxh");
				driver.switchTo().frame(0);
				driver.close();
			}
		}
		
		//Click Angri link -> Mở ra tab/window mới -> Switch qua tab mới
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//div[@class='sectionnav']//a[contains(text() , 'Agri')]")).click();
		
		//Step 04 - Click Account Details link -> Mở ra tab/window mới -> Switch qua tab mới
		ArrayList<String> allWindowns = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(allWindowns.get(1));
		driver.findElement(By.xpath("//p[contains(text(),'Account Details')]")).click();
		ArrayList<String> allWindowns02 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(allWindowns02.get(2));
		String xxx = driver.getTitle();
		System.out.println(xxx);
		
		//Step 05- Click Privacy Policy link (nằm trong frame) -> Mở ra tab/window mới -> Switch qua tab mới
		List<WebElement> frameList = driver.findElements(By.xpath("//frame[@name='footer'"));
		System.out.println(frameList.size());
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//a[contains(text(), 'Privacy Policy')]")).click();
		
		//Step 06- Click CSR link on Privacy Policy page
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		    driver.manage().window().maximize();
		    
		}
		driver.findElement(By.xpath("//a[@title='Corporate Social Responsibility']")).click();
		
		//Step 07- Back về Main window (Parent window)
		//Step 08 - Close tất cả popup khác - chỉ giữ lại parent window 
		for(String winHandle : driver.getWindowHandles()){
		    if(!winHandle.equals(parentWindown)) {
		    	driver.switchTo().window(winHandle);
		    	driver.close();
		    }
		}
		
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
