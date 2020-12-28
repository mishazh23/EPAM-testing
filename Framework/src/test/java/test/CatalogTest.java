package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.CatalogPage;

import java.util.ArrayList;
import java.util.List;

public class CatalogTest extends CommonConditions{
    private final String SEARCH_FIRST = "5";
    private final String SEARCH_SECOND = "7";
    private final String STRING_MASSAGE = "Рекомендуем:";

    @Test(description = "test for searching in catalog using cost")
    public void searchInCatalogTest() {
        List<Double> res= new CatalogPage(driver)
                .openPage()
                .search(SEARCH_FIRST, SEARCH_SECOND);
        int expected = res.size();
        List<Double> filtRes = new ArrayList<>();
        for(int i = 0; i < expected; i++){
            if(res.get(i) >= 5 && res.get(i) <= 7)
                filtRes.add(res.get(i));
        }
        int actual = filtRes.size();
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "test for searching in catalog using incorrect values of costs")
    public void searchInCatalogNegativeTest() {
        String actual = new CatalogPage(driver)
                .openPage()
                .searchNegative(SEARCH_SECOND, SEARCH_FIRST);
        String expected = STRING_MASSAGE;
        Assert.assertEquals(actual, expected);
    }
}
