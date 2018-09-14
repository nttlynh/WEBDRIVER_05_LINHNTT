package selenium_api;

import org.testng.annotations.Test;

import com.google.common.base.Function;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic10_VERIFY_ASSERT_AND_WAITS {
	
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
		driver.manage().window().maximize();
	  }
	@Test(enabled = false)
	  public void TC01_Implicit_Wait() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
//		Step 02 - Define an implicit wait (If you set 2 seconds, test will fail)
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		
//		Step 04 - Wait result text will appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'Hello World!')]")));
		
//		Step 05 - Check result text is "Hello World!"
		Assert.assertEquals("Hello World!", driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).getText());
		
	  }
	
	@Test (enabled = false)
	  public void TC02_Implicit_Wait() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
//		Step 02 - Wait cho "Date Time Picker" được hiển thị (sử dụng: presence or visibility)
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='calendarContainer']")));
		
//		Step 03 - In ra ngày đã chọn (Before AJAX call) -> hiện tại chưa chọn nên in ra = "No Selected Dates to display."
		WebElement textElement = driver.findElement(By.xpath("//div[@id='ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel']/span"));
		System.out.println(textElement.getText());
		
//		Step 04 - Chọn ngày hiện tại (VD: 23/09/2017) (hoặc 1 ngày bất kì tương ứng trong tháng/ năm hiện tại)
		By locator = By.xpath("//*[@id='ctl00_ContentPlaceholder1_RadCalendar1_Top']//a[text()='20']");
		driver.findElement(locator).click();
			
//		Step 05 - Wait cho đến khi "loader ajax" không còn visible (sử dụng: invisibility)
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
		
//		Step 06 - Wait cho selected date = 20 được visible ((sử dụng: visibility)
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'rcSelected')]//a[text()='20']")));
		WebElement textElement01 = driver.findElement(By.xpath("//div[@id='ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel']/span"));
		System.out.println(textElement01.getText());
			
	  }
	
	@Test
	  public void TC03_Fluent_Wait(){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.get("https://stuntcoders.com/snippets/javascript-countdown/");
		
//		Step 02 - Wait cho đến khi countdown time được visible (visibility)
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='javascript_countdown_time']")));
		
		WebElement countdount = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		
		new FluentWait<WebElement>(countdount)
        // Tổng time wait là 15s
        .withTimeout(15, TimeUnit.SECONDS)
         // Tần số mỗi 1s check 1 lần
         .pollingEvery(1, TimeUnit.SECONDS)
        // Nếu gặp exception là find ko thấy element sẽ bỏ  qua
         .ignoring(NoSuchElementException.class)
         // Kiểm tra điều kiện
         .until(new Function<WebElement, Boolean>() {
             public Boolean apply(WebElement element) {
                        // Kiểm tra điều kiện countdount = 00
                        boolean flag =  element.getText().endsWith("00");
                        System.out.println("Time = " +  element.getText());
                        // return giá trị cho function apply
                        return flag;
                   }
            });
		
	}
	
//	Test Script 03: Fluent Wait 01
//	Step 01 - Truy cập vào trang: 
//	https://stuntcoders.com/snippets/javascript-countdown/

//	Step 03 - Sử dụng Fluent wait để:
//	Mỗi 1s kiểm tra countdount= 00 được available trên page (giây đếm ngược về 00)
//	Tức là trong vòng 15s, cứ mỗi 1 giây verify xem nó đã đếm ngược về giây 00 hay chưa
	


	
	@AfterClass
	  public void afterClass() {
		driver.quit();
	  }
	  

	  
  

}
