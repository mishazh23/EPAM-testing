package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();
    private final String URL = "https://korm.shop.by/shcart/?step=2&nstep=2";

    @FindBy(xpath = "//span[@class=\"ok-order__code f-size-small f-color-grey\"]")
    private WebElement itemCode;

    @FindBy(xpath = "//a[@class=\"ok-order-action\"]")
    private WebElement deleteButton;

    public CartPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getItemCode(){
      new WebDriverWait(driver,15)
                .until(ExpectedConditions.visibilityOf(itemCode));
      return itemCode.getText();
    }

    public CartPage deleteItem(){
        new WebDriverWait(driver,15)
                .until(ExpectedConditions.visibilityOf(deleteButton));
                deleteButton.click();
        logger.info("Item was deleted from cart.");
        return this;
    }

    public LandingPage openLoginPage(){
        return new LandingPage(driver);
    }

    @Override
    public CartPage openPage() {
        driver.navigate().to(URL);
        logger.info("Login page opened.");
        return this;
    }
}
