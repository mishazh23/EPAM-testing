import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
public class WebDriverTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
    }

    @Test
    public void loginTest(){
        driver.get("https://korm.shop.by/");
        String login = "test@gmail.com";
        String password = "123456";

        WebElement searchEnterButton = driver.findElement(By.className("ok-auth__info"));
        searchEnterButton.click();
        WebElement enterLogin = driver.findElement(By.name("log_email"));
        enterLogin.sendKeys(login);
        WebElement enterPassword = driver.findElement(By.name("log_password"));
        enterPassword.sendKeys(password);
        WebElement loginSubmitButton = driver.findElement(By.xpath("//button[@data-btn-validate=\"login\"]"));
        loginSubmitButton.click();
        driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(0));
        String log = driver.findElement(By.className("ok-auth__info")).getText();
        Assert.assertEquals(login, log);
    }

    @AfterClass
    public void quitWebDriver() {
        driver.quit();
    }
}
