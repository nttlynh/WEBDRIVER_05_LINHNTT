package selenium_api;

import org.testng.annotations.Test;


import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic07_UserInteractions {
	WebDriver driver;
	
	@BeforeClass
	 public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	  }

	@Test 
	public void TC_01_HoverMouse() throws InterruptedException {
		driver.get("http://daominhdam.890m.com/");
		WebElement element = driver.findElement(By.xpath("//a[text()='Hover over me']"));
		
		Actions action = new Actions(driver);
		//hover mouse
		action.moveToElement(element).perform();
		Thread.sleep(4000);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='tooltip']/div[@class='tooltip-inner']")).getText(), "Hooray!");
		
		driver.get("http://www.myntra.com/");
		WebElement accElement = driver.findElement(By.xpath("//div[@class='desktop-userIconsContainer']"));
		WebElement loginElement = driver.findElement(By.xpath("//a[text()='login']"));
		
		action.moveToElement(accElement).moveToElement(loginElement).perform();
		loginElement.click();
		Thread.sleep(4000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-box']")).isDisplayed());
		
		//div[@class='desktop-userActions']
	
		
	  } 
	
	
	@Test 
	public void TC_02_ClickandHold() throws InterruptedException {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		//List element
		List <WebElement> listNumber = driver.findElements(By.xpath("//ol[@id= 'selectable']/li"));
		
		//Define actions
		Actions action = new Actions(driver);
		
		action.clickAndHold(listNumber.get(0)).moveToElement(listNumber.get(3)).perform();
		action.release(listNumber.get(3)).perform();
	
		List<WebElement> listSelected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		
		Assert.assertEquals(listSelected.size(), 4);
	//	Thread.sleep(3000);
		
	}
	
	@Test 
	public void TC_02_ClickandHold_Shift() throws InterruptedException {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		//List element
		List <WebElement> listNumber = driver.findElements(By.xpath("//ol[@id= 'selectable']/li"));
		
		//Define actions
		Actions action = new Actions(driver);
		
		action.clickAndHold(listNumber.get(0)).moveToElement(listNumber.get(3)).perform();
		action.release(listNumber.get(3)).perform();
	
		List<WebElement> listSelected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		
		Assert.assertEquals(listSelected.size(), 4);
	//	Thread.sleep(3000);
		
	}
	
	
	@Test 
	public void TC_03_DoubleClick() throws InterruptedException {
		driver.get("http://www.seleniumlearn.com/double-click");
		WebElement doubleElement = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
		
		Actions action = new Actions (driver);
		action.doubleClick(doubleElement).perform();
		Thread.sleep(3000);
		
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "The Button was double-clicked.");
		alert.accept();
	}
	
	@Test 
	public void TC_04_RightClick(){
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		WebElement clickrightElement = driver.findElement(By.xpath("//span[text()='right click me']"));
		WebElement quitElement = driver.findElement(By.xpath("//ul[@class='context-menu-list context-menu-root']//li/span[text()='Quit']"));
		
		Actions action = new Actions(driver);
		action.contextClick(clickrightElement).moveToElement(quitElement).perform();
		Assert.assertEquals(driver.findElement(By.xpath("//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='Quit']")).getText(), "Quit");
		quitElement.click();
		
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "clicked: quit");
		alert.accept();
	}
	
	@Test 
	public void TC_05_DragAndDrop() {
		//Scrip 01
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		
		WebElement sourceElement = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement targetElement = driver.findElement(By.xpath("//div[@id='droptarget']"));
		
		Actions action = new Actions(driver);
		action.dragAndDrop(sourceElement, targetElement).release().perform();
		System.out.println(targetElement.getText());
		Assert.assertEquals("You did great!", targetElement.getText());
		
		
		
		//Scrip 02
		driver.get("http://jqueryui.com/resources/demos/droppable/default.html");
		WebElement sourceElement02 = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement targetElement02 = driver.findElement(By.xpath("//div[@id='droppable']"));
		action.dragAndDrop(sourceElement02, targetElement02).release().perform();
		System.out.println(targetElement02.getText());
		Assert.assertEquals("Dropped!", targetElement02.getText());
		
		
	}
	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
  

}
