import exception.TriangleException;
import operation.TriangleValidator;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class TriangleValidatorTest {
    @DataProvider
    private Object[][] positiveSides() {
        return new Object[][] {
                {2, 3, 4},
                {12, 15, 16},
                {200, 300, 400},
                {21, 30, 50},
                {1000, 1002, 1005}
        };
    }
    @Test( dataProvider = "positiveSides")
    public void TriangleWithPositiveSidesTest(double a, double b, double c) throws TriangleException {
        Assertions.assertTrue(TriangleValidator.isTriangleValid(a, b, c));
    }

    @DataProvider
    private Object[][] zeroSides() {
        return new Object[][] {
                {1, 3, 0},
                {0, 15, 0},
                {0, 15, 20},
                {0, 0, 12},
                {0, 0, 0}
        };
    }
    @Test(
            dataProvider = "zeroSides",
            expectedExceptions = { TriangleException.class },
            expectedExceptionsMessageRegExp = "Values equal or less then 0 are not allowed!"
    )
    public void TriangleWithZeroSidesTest(double a, double b, double c) throws  TriangleException {
        Assertions.assertTrue(TriangleValidator.isTriangleValid(a, b, c));
    }
}
