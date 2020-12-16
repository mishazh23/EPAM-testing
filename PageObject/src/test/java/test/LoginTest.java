package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.LoginPage;

public class LoginTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        /*ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("disable-gpu");
        chromeOptions.addArguments("window-size=1920,1080");

        driver = new ChromeDriver(chromeOptions);*/
        driver = new ChromeDriver();
    }

    @Test
    public void loginTest() {
        String login = "test@gmail.com";
        String password = "123456";
        new LoginPage(driver)
                .openTestedPage()
                .openSignInForm();

        String actual = new LoginPage(driver)
                .signIn(login, password);

        Assert.assertEquals(actual, login);
    }

    @Test
    public void loginErrorTest() {
        String login = "test@gmail";
        String password = "123456";
        new LoginPage(driver)
                .openTestedPage()
                .openSignInForm();

        String expected = "#dd5555";
        String color = new LoginPage(driver)
                .getErrorColor(login,password);
        String actual = Color.fromString(color).asHex();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void signUpErrorTest() {
        new LoginPage(driver)
                .openTestedPage()
                .openSignInForm()
                .openSignUpForm();

        String expected = "#dd5555";
        String color = new LoginPage(driver)
                .signUp();
        String actual = Color.fromString(color).asHex();
        Assert.assertEquals(actual, expected);
    }

    @AfterClass
    public void quitWebDriver() {
        driver.quit();
    }

}
