package test;

import driver.DriverSingleton;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class CommonConditions {
    protected WebDriver driver;


    @BeforeMethod()
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "D://webdriver/chromedriver.exe");
        driver = DriverSingleton.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser()
    {
        DriverSingleton.closeDriver();
    }
  /*  public static ExpectedCondition<Boolean> jQueryAjaxCompleted() {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor) driver)
                        .executeScript(
                                "return (window.jQuery != null) && (jQuery.active == 0);"
                        );
            }
        };
    }*/

}
