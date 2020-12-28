package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RegistrationPage extends AbstractPage{
    private final Logger logger = LogManager.getRootLogger();
    private final String URL = "https://korm.shop.by/";
    private By menuBtn = By.className("ok-auth__info");
    private By errorSignUpBlock = By.xpath("//div[@id=\"registration\"]/descendant::div[@class=\"ok-form-row -input-special data-input-check -state-error\"]");

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

    public RegistrationPage(WebDriver driver){
       super(driver);
       PageFactory.initElements(driver, this);
    }

    public RegistrationPage openSignInForm() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(menuBtn))
                .click();
        return this;
    }

    public RegistrationPage openSignUpForm() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(registerButton))
                .click();
        return this;
    }


    public String signUp() {
        openSignInForm();
        openSignUpForm();
        signUpButton.click();
        logger.info("Registration was completed.");
        WebElement res = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(errorSignUpBlock));
        return res.getCssValue("color");
    }

    @Override
    public RegistrationPage openPage() {
        driver.navigate().to(URL);
        logger.info("Login page opened.");
        return this;
    }

}
