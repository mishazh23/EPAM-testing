package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ItemPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();
    private final String URL = "https://korm.shop.by/tovaryforcats/suxie-korma-dlya-koshek/matisse-chicken-turkey-1-5-kg/";

    @FindBy(xpath = "//button[@class=\"ok-product__add-shcart ok-btn  -btn-shopping-cart ok-product__btn\"]")
    private WebElement toCartButton;

    @FindBy(xpath = "//button[@class=\"ok-shcart__info\"]")
    private WebElement goToCartButton;

    public ItemPage addToCart(){
        new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds())
                .until(ExpectedConditions.elementToBeClickable(toCartButton));
        toCartButton.click();
        driver.navigate().refresh();
        logger.info("Item is added to cart.");
        return this;
    }

    public ItemPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ItemPage goToCartPage(){
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(goToCartButton))
                .click();
        return this;
    }


    public CartPage openCartPage(){
        return new CartPage(driver);
    }

    @Override
    public ItemPage openPage() {
        driver.navigate().to(URL);
        logger.info("Login page opened.");
        return this;
    }
}
