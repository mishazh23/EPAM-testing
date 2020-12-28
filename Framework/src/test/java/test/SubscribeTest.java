package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.SpamPage;

public class SubscribeTest extends CommonConditions{
    private final String email = "nomoresorrow23@gmail.com";
    private final String massage = "ПОЧТИ ВСЁ...";

    @Test(description = "test for spam subscription")
    public void subscribeSpanTest() {
   String actual = new SpamPage(driver)
            .openPage()
            .subscribe(email)
            .openSubscriptionPage()
            .openPage()
            .getSubscriptionMassage();
    String expected = massage;
    Assert.assertEquals(actual, expected);
    }
}
