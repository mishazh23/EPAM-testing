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

public class SpamPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();
    private final String URL = "https://korm.shop.by/";
    private By spamButton = By.linkText("АКЦИИ И СКИДКИ");
    private By subscriptionButton = By.name("subscribe");

    @FindBy(xpath = "//input[@class=\"email\"]")
    private WebElement emailField;

    public SpamPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public SpamPage openSpamPage() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(spamButton))
                .click();
        return this;
    }

    public SpamPage goToSubscriptionPage(){
        logger.info("Click on \"Subscription\" button");
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(subscriptionButton))
                .click();
        return this;
    }

    public SpamPage subscribe(String email){
        openSpamPage();
        emailField.sendKeys(email);
        goToSubscriptionPage();
        return this;
    }

    public SubscriptionPage openSubscriptionPage() {
        return new SubscriptionPage(driver);
    }

    @Override
    public SpamPage openPage() {
        driver.navigate().to(URL);
        logger.info("Login page opened.");
        return this;
    }
}
