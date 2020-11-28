import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.concurrent.TimeUnit;

public class WebDriverTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        System.setProperty("webdriver.chrome.driver", "D://webdriver/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("disable-gpu");
        chromeOptions.addArguments("window-size=1920,1080");

        driver = new ChromeDriver(chromeOptions);
        // driver = new ChromeDriver();
    }

    @Test
    public void loginTest() throws InterruptedException {
        driver.get("https://korm.shop.by/");
        String login = "test@gmail.com";
        String password = "123456";

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        WebElement searchEnterButton = driver.findElement(By.className("ok-auth__info"));
        searchEnterButton.click();

        WebElement enterLogin = driver.findElement(By.xpath("//input[@name=\"log_email\"]"));
        enterLogin.sendKeys(login);

        WebElement enterPassword = driver.findElement(By.xpath("//input[@name=\"log_password\"]"));
        enterPassword.sendKeys(password);

        WebElement loginSubmitButton = driver.findElement(By.xpath("//button[@data-btn-validate=\"login\"]"));
        loginSubmitButton.click();

        driver.navigate().refresh();

        String log = driver.findElement(By.className("ok-auth__info")).getText();
        Assert.assertEquals(log, login);
    }

    @AfterClass
    public void quitWebDriver() {
        driver.quit();
    }
}
