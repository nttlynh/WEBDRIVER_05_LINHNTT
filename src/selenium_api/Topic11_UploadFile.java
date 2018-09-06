package selenium_api;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Topic11_UploadFile {
    WebDriver driver;
    WebDriverWait wait;

    String os = System.getProperty("os.name").toLowerCase();

    String filename = "img01.jpg";
    String projectDirectory = System.getProperty("user.dir");
    String uploadFilePath = projectDirectory + "\\img\\" + filename;
    String chromeUpload = projectDirectory + "\\upload\\chrome.exe";
    String firefoxUpload = projectDirectory + "\\upload\\firefox.exe";
    String ieUpload = projectDirectory + "\\upload\\ie.exe";

    //data


    @BeforeClass
    public void beforeClass() {
        if (os.indexOf("win") >= 0) {
//            System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
//            driver = new ChromeDriver();
//             driver = new FirefoxDriver();

            System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
              driver = new InternetExplorerDriver();
        }
        if (os.indexOf("mac") >= 0) {
            System.setProperty("webdriver.chrome.driver", "//driver//chromedriver");
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }


    @Test(enabled = false)
    public void TC01_SendkeyFireFox() {
        wait = new WebDriverWait(driver, 10);
        driver.get("http://blueimp.github.com/jQuery-File-Upload/");
        WebElement uploadElement = driver.findElement(By.xpath("//input[@type='file']"));
        uploadElement.sendKeys(uploadFilePath);
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + filename + "']")).isDisplayed());
        //  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']"))));
        driver.findElement(By.xpath("//button[@type='submit']")).click();
       // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table//button[@class='btn btn-danger delete']")));
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + filename + "']")).isDisplayed());
    }


    @Test(enabled = false)
    public void TC01_SendkeyChrome() {
        wait = new WebDriverWait(driver, 10);
        driver.get("http://blueimp.github.com/jQuery-File-Upload/");
        WebElement uploadElement = driver.findElement(By.xpath("//input[@type='file']"));
        uploadElement.sendKeys(uploadFilePath);
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + filename + "']")).isDisplayed());
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']"))));
        driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
        //  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table//button[@class='btn btn-primary start']"));
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table//button[@class='btn btn-danger delete']")));
        Assert.assertTrue(wait.
                until(ExpectedConditions.
                        visibilityOfElementLocated(By.xpath("//p[@class='name']/a[@title='" + filename + "']"))).
                isDisplayed());

    }

    @Test(enabled = false)
    public void TC01_SendkeyForIE() {
        wait = new WebDriverWait(driver, 10);
        driver.get("http://blueimp.github.com/jQuery-File-Upload/");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement uploadElement = driver.findElement(By.xpath("//span[contains(text(),'Add files...')]"));
        js.executeScript("arguments[0].click();", uploadElement);
        uploadElement.sendKeys(uploadFilePath);

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + filename + "']")).isDisplayed());
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']"))));

        JavascriptExecutor js01 = (JavascriptExecutor) driver;
        WebElement startElement = driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']"));
        js01.executeScript("arguments[0].click();", startElement);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table//button[@class='btn btn-danger delete']")));
        Assert.assertTrue(driver.findElement(By.xpath("//table//button[@class='btn btn-danger delete']")).isDisplayed());
    }

    @Test (enabled = false)
    public void TC02_AutoIT() throws IOException {
        driver.get("http://blueimp.github.com/jQuery-File-Upload/");
        WebElement uploadChrome = driver.findElement(By.cssSelector(".fileinput-button"));
        uploadChrome.click();
        Runtime.getRuntime().exec(new String[] { ieUpload, uploadFilePath });
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + filename + "']")).isDisplayed());
        //  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']"))));
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table//button[@class='btn btn-danger delete']")));
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + filename + "']")).isDisplayed());
    }

    @Test (enabled = false)
    public void TC03_Robot() throws InterruptedException, AWTException {
        driver.get("http://blueimp.github.com/jQuery-File-Upload/");


        //Specify the file location with extension
        StringSelection select = new StringSelection(uploadFilePath);

        //Copy to clipboard
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

        //Click
        WebElement uploadChrome = driver.findElement(By.cssSelector(".fileinput-button"));
        uploadChrome.click();

        Robot robot = new Robot();
        Thread.sleep(1000);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        Thread.sleep(1000);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + filename + "']")).isDisplayed());
        //  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']"))));
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table//button[@class='btn btn-danger delete']")));
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + filename + "']")).isDisplayed());
    }

    @Test
    public void TC04_UploadFile() {
        driver.get("https://encodable.com/uploaddemo/");

        //    Step 02 - Choose Files to Upload (Ex: UploadFile.jpg)
        WebElement uploadFile01 = driver.findElement(By.xpath("//input[@type='file' and @id = 'uploadname1']"));
        uploadFile01.sendKeys(uploadFilePath);

        //    Step 03 - Select dropdown (Upload to: /uploaddemo/files/)
        Select uploadSelect = new Select(driver.findElement(By.xpath("//select[@class='upform_field picksubdir_field']")));
        uploadSelect.selectByVisibleText("/uploaddemo/files/");

        //    Step 04 - Input random folder to 'New subfolder? Name:) textbox (Ex: dam1254353)
        String folderName = getRanFolder();
        driver.findElement(By.xpath("//input[@id='newsubdir1']")).sendKeys(folderName);

        //    Step 05 - Input email and firstname (dam@gmail.com/ DAM DAO)
        String email = getRanEmail();
        driver.findElement(By.xpath("//input[@id='formfield-email_address']")).sendKeys(email);

        driver.findElement(By.xpath("//input[@id='formfield-first_name']")).sendKeys("LINH NGUYEN");

        //    Step 06 - Click Begin Upload (Note: Wait for page load successfully)
        driver.findElement(By.xpath("//input[@id='uploadbutton']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//dl[@id='fcuploadsummary']//dd[contains(text(), 'Email Address')]")).isDisplayed());
    }

    private String getRanFolder() {
        return "Folder" + new Random().nextInt();
    }

    private String getRanEmail() {
        return "auto" + new Random().nextInt() + "@gmail.com";
    }




    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
