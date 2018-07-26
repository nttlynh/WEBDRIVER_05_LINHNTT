package selenium_api;


import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
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
    String msgActual;
    @BeforeClass
	public void beforeClass() {
		
		if (OS.indexOf( "win" ) >= 0) {
		driver = new FirefoxDriver();
		}
		if(OS.indexOf("mac") >=0 ) {
			System.setProperty("webdriver.chrome.driver", ".//driver//chromedriver");
			driver = new ChromeDriver();
		}
		driver.get("http://live.guru99.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
    
	@Test 
	public void TC_01_CheckUrlAndTitle() {
				
		//Check home page title
		System.out.println("Check homepage title");
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Home page");
		
		// Click my account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[contains(@title,'Create')]")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().back();
		
		String URLlogin = driver.getCurrentUrl();
		System.out.println(URLlogin);
		Assert.assertEquals(URLlogin, "http://live.guru99.com/index.php/customer/account/login/" );
		
		driver.navigate().forward();
		String URLcreate = driver.getCurrentUrl();
		System.out.println(URLcreate);
		Assert.assertEquals(URLcreate, "http://live.guru99.com/index.php/customer/account/create/");		
		
	}
	
	@Test 
	public void TC_02_LoginEmpty() throws InterruptedException{
		
		
		driver.get("http://live.guru99.com");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();	
		
		msgActual = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		System.out.println(msgActual);
		String msgExpect = "This is a required field.";
		Assert.assertEquals(msgActual, msgExpect);
		
		msgActual = driver.findElement(By.xpath(".//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(msgActual, msgExpect);
		
	}
	
	@Test 
	public void TC_03_LoginWithInvalidEmail() throws InterruptedException {
		Thread.sleep(2000);
		driver.get("http://live.guru99.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		msgActual = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		String msgExpect = "Please enter a valid email address. For example johndoe@domain.com.";
		Assert.assertEquals(msgActual, msgExpect);	
		
	}
	
	@Test
	public void TC_04_LoginWithInvalidPassword() throws InterruptedException {
		Thread.sleep(2000);
		driver.get("http://live.guru99.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		msgActual = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		String msgExpect = "Please enter 6 or more characters without leading or trailing spaces.";
		Assert.assertEquals(msgActual, msgExpect);	
		
	}
	@Test
	public void TC_05_CreateAnAccount() throws InterruptedException {
		//Thread.sleep(2000);
	    String randomEmail = randomEmail();
		driver.get("http://live.guru99.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Linh");
		driver.findElement(By.xpath("//input [@id='middlename']")).sendKeys("Thao");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Nguyen");
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(randomEmail);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Linhtest01");
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("Linhtest01");
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		msgActual = driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div/div/ul/li/ul/li")).getText();
		Assert.assertEquals(msgActual, "Thank you for registering with Main Website Store.");
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		driver.findElement(By.xpath("//a[@class='logo']")).click();
		String URLhome = driver.getCurrentUrl();
		System.out.println(URLhome);
		Assert.assertEquals(URLhome, "http://live.guru99.com/index.php/");		
		
		
	}
	private static String randomEmail() {
        return "random-" + UUID.randomUUID().toString() + "@example.com";
    }
	
	
	


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}