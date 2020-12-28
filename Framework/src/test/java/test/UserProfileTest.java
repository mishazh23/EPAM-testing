package test;

import model.AccountInfo;
import model.User;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LandingPage;
import page.RegistrationPage;


public class UserProfileTest extends CommonConditions{
    private final User userWithCorrectData = new User("test@gmail.com", "123456");
    private final User userWithIncorrectPassword = new User("test@gmail.com", "12345");
    private final AccountInfo info = new AccountInfo("Сабуров", "Бледный", "Jisus", "13");
    private final String errorColor = "#dd5555";

    @Test(description = "test for registration with blank fields")
    public void signUpErrorTest() {
        String color = new RegistrationPage(driver)
                .openPage()
                .signUp();
        String expected = errorColor;

        String actual = Color.fromString(color).asHex();
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "test for signing in with correct data")
    public void signInTest() {
        String actual = new LandingPage(driver)
                .openPage()
                .signIn(userWithCorrectData)
                .checkLogin();
        String expected = userWithCorrectData.getLogin();
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "test for signing in with Incorrect data")
    public void signInWithIncorrectPasswordTest() {
        String color = new LandingPage(driver)
                .openPage()
                .signInWithIncorrectPassword(userWithIncorrectPassword);
        String actual = Color.fromString(color).asHex();;
        String expected = errorColor;
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "test for changing profile info")
    public void changeProfileInfoTest() {
        String expected;
        String res = new LandingPage(driver).openPage().signIn(userWithCorrectData).getSurname();
        if(res.equals(info.getFirstSurname())){
            expected = info.getSecondSurname();
        }else{
            expected = info.getFirstSurname();
        }
        String actual =  new LandingPage(driver)
                .changeSurname(expected)
                .getSurname();

        Assert.assertEquals(actual, expected);
    }

    @Test(description = "test for adding new address")
    public void addAddressTest() {
        int expected = new LandingPage(driver).openPage().signIn(userWithCorrectData).countAddresses() + 1;
        int actual = new LandingPage(driver)
                .addAddress(info.getStreet(), info.getHouse())
                .countAddresses();
        Assert.assertEquals(actual, expected);
    }


}

