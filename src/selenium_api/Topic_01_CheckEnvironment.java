package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_CheckEnvironment {
    WebDriver driver;
    private static String OS = System.getProperty("os.name").toLowerCase();

	@Test
	public void TC_01_CheckUrlAndTitle() {
		
		System.out.println("Check homepage title");
		System.out.println("Check homepage title");
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Guru99 Bank Home Page");

		System.out.println("Check homepage url");
		String homePageUrl = driver.getCurrentUrl();
		Assert.assertEquals(homePageUrl, "http://demo.guru99.com/v4/");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println(OS);
		if (OS.indexOf( "win" ) >= 0) {

	        // this doesn't support showing urls in the form of "page.html#nameLink" 
		driver = new FirefoxDriver();
		}
		if(OS.indexOf("mac") >=0 ) {
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver");
			driver = new ChromeDriver();
		}
		driver.get("http://demo.guru99.com/v4/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}