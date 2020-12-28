package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SubscriptionPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();
    private final String URL = "https://korm.shop.by/podpiska_na_skidku.xhtml";

    @FindBy(xpath = "//h1")
    private WebElement massage ;

    public SubscriptionPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getSubscriptionMassage(){
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(massage));
        return massage.getText();
    }

    @Override
    public SubscriptionPage openPage() {
        driver.navigate().to(URL);
        logger.info("Login page opened.");
        return this;
    }
}
