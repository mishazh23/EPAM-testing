package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class LoginPage {
    private WebDriver driver;
    private By buttonBy = By.className("ok-auth__info");
    private By errorSignInBlock = By.xpath("//div[@id=\"enter\"]/descendant::div[@class=\"ok-form-row -input-special data-input-check -state-error\"]");
    private By errorSignUpBlock = By.xpath("//div[@id=\"registration\"]/descendant::div[@class=\"ok-form-row -input-special data-input-check -state-error\"]");

    private final String URL = "https://korm.shop.by/";

    @FindBy(xpath = "//button[@data-btn-validate=\"login\"]")
    private WebElement enterButton;

    @FindBy(xpath = "//input[@name=\"log_email\"]")
    private WebElement loginField;

    @FindBy(xpath = "//input[@name=\"log_password\"]")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@name=\"reg_email\"]")
    private WebElement loginFieldReg;

    @FindBy(xpath = "//input[@name=\"reg_password\"]")
    private WebElement passwordFieldReg;

    @FindBy(xpath = "//input[@name=\"reg_password2\"]")
    private WebElement passwordFieldReg2;

    @FindBy(xpath = "//li[@href=\"#registration\"]")
    private WebElement registerButton;

    @FindBy(xpath = "//*[@id=\"registration\"]/form/div[6]/button")
    private WebElement signUpButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage openTestedPage() {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.get(URL);
        new WebDriverWait(driver, 15)
                .until(CustomConditions.jQueryAjaxCompleted());
        return this;
    }

    public LoginPage openSignInForm() {
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(250))
                .withMessage("Element was not found")
                .until(ExpectedConditions.elementToBeClickable(buttonBy))
                .click();
        return this;
    }

    public LoginPage openSignUpForm() {
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(250))
                .withMessage("Element was not found")
                .until(ExpectedConditions.elementToBeClickable(registerButton))
                .click();
        return this;
    }

    public String signIn(String login, String password) {
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        enterButton.click();
        driver.navigate().refresh();

        return driver.findElement(By.className("ok-auth__info")).getText();
    }

    public String signUp() {
        signUpButton.click();
        WebElement res = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(errorSignUpBlock));
        return res.getCssValue("color");
    }


    public String getErrorColor(String login, String password){
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        enterButton.click();
        WebElement res = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(errorSignInBlock));
        return res.getCssValue("color");
    }
}
