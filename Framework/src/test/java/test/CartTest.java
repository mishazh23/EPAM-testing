package test;

import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LandingPage;

public class CartTest extends CommonConditions {
    private final User userWithCorrectData = new User("test@gmail.com", "123456");
    private final String itemCode = "Код: 8010276032089";

    @Test(description = "add to cart test")
    public void addToCartTest() {
        String actual =  new LandingPage(driver)
                 .openPage()
                 .signIn(userWithCorrectData)
                 .openItemPage()
                 .openPage()
                 .addToCart()
                 .goToCartPage()
                 .openCartPage()
                 .getItemCode();
        String expected = itemCode;
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "delete from cart test")
    public void deleteFromCartTest() {
        boolean cartIsEmpty = new LandingPage(driver)
                .openPage()
                .signIn(userWithCorrectData)
                .openItemPage()
                .openPage()
                .addToCart()
                .goToCartPage()
                .openCartPage()
                .deleteItem()
                .openLoginPage()
                .checkIfCartIsEmpty();
        Assert.assertTrue(cartIsEmpty);
    }

}
