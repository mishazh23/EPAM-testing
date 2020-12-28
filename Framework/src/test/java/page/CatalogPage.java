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
import util.FilterString;

import java.util.List;
import java.util.stream.Collectors;

public class CatalogPage extends AbstractPage{
    private final Logger logger = LogManager.getRootLogger();
    private final String URL = "https://korm.shop.by/";

    private By searchButton = By.className("ok-menu-top__href");
    private By selector = By.xpath("//span[@class=\"ok-product__price-main\"]");

    @FindBy(xpath = "//input[@name=\"price_before_new\"]")
    private WebElement priceFirst;

    @FindBy(xpath = "//input[@name=\"price_after_new\"]")
    private WebElement priceSecond;

    @FindBy(xpath = "//button[@id=\"find_submit\"]")
    private WebElement submitButton;

    @FindBy(xpath = "//span[@class=\"f-size-medium\"]")
    private WebElement massage ;

    public CatalogPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public CatalogPage openCatalog() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(searchButton))
                .click();
        return this;
    }

    public List<Double> search(String searchFirst, String searchSecond) {
        openCatalog();
        priceFirst.sendKeys(searchFirst);
        priceSecond.sendKeys(searchSecond);
        submitButton.click();
        return driver.findElements(selector).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList())
                .stream()
                .map(FilterString::filterDouble)
                .collect(Collectors.toList());
    }

    public String searchNegative(String searchFirst, String searchSecond) {
        openCatalog();
        priceFirst.sendKeys(searchFirst);
        priceSecond.sendKeys(searchSecond);
        submitButton.click();
        return massage.getText();
    }

    @Override
    public CatalogPage openPage() {
        driver.navigate().to(URL);
        logger.info("Login page opened.");
        return this;
    }
}
