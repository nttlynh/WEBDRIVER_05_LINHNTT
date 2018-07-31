package selenium_api;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomDropDownList {
	WebDriver driver;
	WebDriverWait wait;
	
	public void selectCustomDropdownLisdt(String dropdown, String listitem, String valueitem) {
		driver.findElement(By.xpath("//span[@id='speed-button']")).click();
		System.out.println("linh");
		List<WebElement> allitem = driver.findElements(By.xpath(listitem));
		wait.until(ExpectedConditions.visibilityOfAllElements(allitem));
		for(WebElement item : allitem) {
			if(item.getText().equals(valueitem)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
				item.click();
				break;
			}
		}
	}

}
