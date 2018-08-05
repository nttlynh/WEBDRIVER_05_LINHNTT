package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic07_UserInteractions {
	WebDriver driver;
	
	@BeforeClass
	 public void beforeClass() {
		driver = new FirefoxDriver();
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
	
		
	  }  
	
	@Test
	public void TC_02_ClickandHold() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		//Khai bao actions
		Actions action = new Actions(driver);
		
	}
	@Test
	public void TC_03_DoubleClick() {
		driver.get("http://www.seleniumlearn.com/double-click");
	}
	
	@Test
	public void TC_04_RightClick() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	}
	
	@Test
	public void TC_05_DragAndDrop() {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
		
		WebElement sourceElement = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement targetElement = driver.findElement(By.xpath("//div[@id='droptarget']"));
		
		Actions action = new Actions(driver);
		action.dragAndDrop(sourceElement, targetElement).release().perform();
		
	}
	
	  
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
  

}
