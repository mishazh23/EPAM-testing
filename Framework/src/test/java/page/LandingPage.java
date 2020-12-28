package page;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LandingPage extends AbstractPage{
    private final Logger logger = LogManager.getRootLogger();
    private final String URL = "https://korm.shop.by/";

    private By menuButton = By.className("ok-auth__info");
    private By errorSignInBlock = By.xpath("//span[@class=\"input-group -state-error\"]");
    private By selector = By.className("ok-address-box");

    @FindBy(xpath = "//button[@data-btn-validate=\"login\"]")
    private WebElement enterButton;

    @FindBy(xpath = "//a[@class=\"ok-open-modal-userinfo ok-profile__href\"]")
    private WebElement profileInfoButton;

    @FindBy(xpath = "//input[@name=\"log_email\"]")
    private WebElement loginField;

    @FindBy(xpath = "//input[@name=\"log_password\"]")
    private WebElement passwordField;

    @FindBy(xpath = "//span[@class=\"ok-auth__info\"]")
    private WebElement profileButton;

    @FindBy(xpath = "//a[@data-target=\".ok-modal-address\"]")
    private WebElement addressButton;

    @FindBy(xpath = "//button[@class=\"ok-btn ok-address-ok-btn  -btn-theme-action -width-full\"]")
    private WebElement keepAddress;

    @FindBy(xpath = "//button[@class=\"ok-btn -mt-article -btn-theme-action ok-add-address-btn -width-full\"]")
    private WebElement addAddressButton;

    @FindBy(xpath = "//input[@id=\"profile_surname\"]")
    private WebElement surnameField;

    @FindBy(xpath = "//input[@id=\"address_street\"]")
    private WebElement streetField;

    @FindBy(xpath = "//input[@id=\"address_house\"]")
    private WebElement houseField;

    @FindBy(xpath = "//div[@class=\"-mt-article\"]/descendant::button[@class=\"ok-btn -btn-theme-action -width-full\"]")
    private WebElement changeButton;

    @FindBy(xpath = "//div[@class=\"ok-shcart-box -state-disabled\"]")
    private WebElement emptyCartButton;

    public LandingPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LandingPage openSignInForm() {
        driver.navigate().refresh();
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(menuButton))
                .click();
        return this;
    }


    public LandingPage signIn(User user) {
        openSignInForm();
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOf(loginField));
        loginField.sendKeys(user.getLogin());
        passwordField.sendKeys(user.getPassword());
        enterButton.click();
        driver.navigate().refresh();

        return this;
    }

    public String checkLogin(){
        logger.info("Login was checked.");
        return profileButton.getText();
    }

    public String signInWithIncorrectPassword(User user){
        openSignInForm();
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOf(loginField));
        loginField.sendKeys(user.getLogin());
        passwordField.sendKeys(user.getPassword());
        enterButton.click();
        WebElement res = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(errorSignInBlock));
        return res.getCssValue("color");
    }


    public LandingPage changeSurname(String surname){
        surnameField.clear();
        surnameField.sendKeys(surname);
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(changeButton));
        changeButton.click();
        return this;
    }

    public String getSurname(){
        openSignInForm();
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(profileInfoButton))
                .click();
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(surnameField));
        String result = surnameField.getAttribute("value");
        logger.info("Surname was gotten.");
        return result;
    }

    public LandingPage addAddress(String street, String house){
        addAddressButton.click();
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(streetField));
        streetField.sendKeys(street);
        houseField.sendKeys(house);
        keepAddress.click();
        logger.info("Address was added.");
        return this;
    }

    public int countAddresses(){
        openSignInForm();
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(addressButton))
                .click();
         new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(selector));
        logger.info("Addresses are counted.");
        return driver.findElements(selector)
                .size();
    }

    public boolean checkIfCartIsEmpty(){
        emptyCartButton.click();
        driver.navigate().refresh();
        return driver.getCurrentUrl().equals(URL);
    }

    public ItemPage openItemPage(){
        return new ItemPage(driver);
    }

    @Override
    public LandingPage openPage() {
        driver.navigate().to(URL);
        logger.info("Landing page opened.");
        return this;
    }

}
