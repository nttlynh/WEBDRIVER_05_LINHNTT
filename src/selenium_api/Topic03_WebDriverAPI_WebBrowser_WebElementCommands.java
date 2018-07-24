package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic03_WebDriverAPI_WebBrowser_WebElementCommands {
	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		
		//System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver = new FirefoxDriver();
	}
	
	
	@Test
	public void TC_01_CheckTheDisplayElementOnThePage() {
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		if (isElementDisplayed(driver, "//input[@id='mail']")) {
			driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("Automation Testing");
		}
		if (driver.findElement(By.cssSelector("#under_18")).isDisplayed()) {
			driver.findElement(By.cssSelector("#under_18")).click();
		}
		if (driver.findElement(By.cssSelector("#edu")).isDisplayed()) {
			driver.findElement(By.cssSelector("#edu")).sendKeys("Automation Testing");
		}
		  
	}
	
	@Test
	public void TC_02_CheckTheEnableElementOnThePage() {
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		if(driver.findElement(By.xpath("//input[@id='mail']")).isEnabled()) {
			System.out.println("Element is enabled");
		}
		else {
			System.out.println("Element is disbale");
		}
		
		if(driver.findElement(By.cssSelector("#under_18")).isEnabled()) {
			System.out.println("Element is enabled");
		}
		else {
			System.out.println("Element is disbale");
		}
		if(driver.findElement(By.cssSelector("#edu")).isEnabled()) {
			System.out.println("Element is enabled");
		}
		else {
			System.out.println("Element is disbale");
		}
		if(driver.findElement(By.cssSelector("#job1")).isEnabled()) {
			System.out.println("Element is enabled");
		}
		else {
			System.out.println("Element is disbale");
		}
		if(driver.findElement(By.xpath("//input[@id='development']")).isEnabled()) {
			System.out.println("Element is enabled");
		}
		else {
			System.out.println("Element is disbale");
		}
		if(driver.findElement(By.xpath("//input[@id='slider-1']")).isEnabled()) {
			System.out.println("Element is enabled");
		}
		else {
			System.out.println("Element is disbale");
		}
		if(driver.findElement(By.xpath("//button[@id='button-enabled']")).isEnabled()) {
			System.out.println("Element is enabled");
		}
		else {
			System.out.println("Element is disbale");
		}
		if(isElementDisable(driver, "//input[@id='slider-2']")) {
			System.out.println("Element is enabled");
		}
		else {
			System.out.println("Element is disbale");
		}
		if(isElementDisable(driver, "//input[@id='password']")) {
			System.out.println("Element is enabled");
		}
		else {
			System.out.println("Element is disbale");
		}
		if(isElementDisable(driver, "//input[@id='radio-disabled']")) {
			System.out.println("Element is enabled");
		}
		else {
			System.out.println("Element is disbale");
		}
		if(isElementDisable(driver, "//textarea[@id='bio']")) {
			System.out.println("Element is enabled");
		}
		else {
			System.out.println("Element is disbale");
		}
		if(isElementDisable(driver, "//select[@id='job2']")) {
			System.out.println("Element is enabled");
		}
		else {
			System.out.println("Element is disbale");
		}
		if(isElementDisable(driver, "//input[@id='check-disbaled']")) {
			System.out.println("Element is enabled");
		}
		else {
			System.out.println("Element is disbale");
		}
		
		if(isElementDisable(driver, "//button[@id='button-disabled']")) {
			System.out.println("Element is enabled");
		}
		else {
			System.out.println("Element is disbale");
		}
					
	}
	
	
	@Test
	public void TC_03_CheckTheSelectedElementOnThePage() {
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@id='under_18']")).click();
		driver.findElement(By.xpath("//input[@id='development']")).click();
		if(!isElementSelected(driver, "//input[@id='under_18']")) {
			driver.findElement(By.xpath("//input[@id='under_18']")).click();
		}
		
		
		if(!isElementSelected(driver, "//input[@id='development']")) {
			driver.findElement(By.xpath("//input[@id='development']")).click();
		}
		
	}
	
	
	public Boolean isElementDisplayed(WebDriver driver, String yourLocator) {
		try {
			WebElement element;
			element = driver.findElement(By.xpath(yourLocator));
			return element.isDisplayed();
		}catch (NoSuchElementException e){
			return false;
		}
		
    }
	
	public Boolean isElementDisable(WebDriver driver, String locator) {
		try {
			WebElement element;
			element = driver.findElement(By.xpath(locator));
			return element.isDisplayed();
			
		}catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public Boolean isElementSelected(WebDriver driver, String locator) {
		try {
			WebElement element;
			element = driver.findElement(By.xpath(locator));
			return element.isSelected();
			
		}catch (NoSuchElementException e) {
			return false;
		}
	}
	@AfterClass
	  public void afterClass() {
		driver.quit();
	  }

	  
	
 

}
