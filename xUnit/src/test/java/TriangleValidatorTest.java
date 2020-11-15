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
    public void triangleWithPositiveSidesTest(double a, double b, double c) throws TriangleException {
        Assertions.assertTrue(TriangleValidator.isTriangleValid(a, b, c));
    }

    @DataProvider
    private Object[][] negativeSides() {
        return new Object[][] {
                {-2, 3, 4},
                {12, -15, 16},
                {-200, -300, 400},
                {21, -30, 50},
                {-1000, -1002, -1005}
        };
    }
    @Test(
            dataProvider = "negativeSides",
            expectedExceptions = { TriangleException.class },
            expectedExceptionsMessageRegExp = "Values equal or less then 0 are not allowed!"
    )
    public void triangleWithNegativeSidesTest(double a, double b, double c) throws TriangleException {
        Assertions.assertTrue(TriangleValidator.isTriangleValid(a, b, c));
    }

    @DataProvider
    private Object[][] decimalsSides() {
        return new Object[][] {
                {2.13, 3.12, 4.11},
                {12.102, 15.103, 16.144},
                {200.1011, 300.12, 400.1},
                {21.12, 30.03, 50.05},
                {1000.0001, 1002.2001, 1005.5001}
        };
    }
    @Test( dataProvider = "decimalsSides")
    public void triangleWithDecimalsSidesTest(double a, double b, double c) throws TriangleException {
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
    public void triangleWithZeroSidesTest(double a, double b, double c) throws  TriangleException {
        Assertions.assertTrue(TriangleValidator.isTriangleValid(a, b, c));
    }

    @DataProvider
    private Object[][] nanSides() {
        return new Object[][] {
                {1, Double.NaN, 2},
                {Double.NaN, 15, Double.NaN},
                {Double.NaN, Double.NaN, Double.NaN},
                {Double.NaN, 13, 12},
                {3, 4, Double.NaN}
        };
    }
    @Test(
            dataProvider = "nanSides",
            expectedExceptions = { TriangleException.class },
            expectedExceptionsMessageRegExp = "Cannot read values that are not defined"
    )
    public void triangleWithNaNSidesTest(double a, double b, double c) throws  TriangleException {
        Assertions.assertTrue(TriangleValidator.isTriangleValid(a, b, c));
    }

    @DataProvider
    private Object[][] rightTriangleSides() {
        return new Object[][] {
                {3, 4, 5},
                {12, 5, 13},
                {7, 24, 25},
                {11, 60, 61},
                {13, 84, 85}
        };
    }
    @Test( dataProvider = "rightTriangleSides")
    public void rightTriangleTest(double a, double b, double c) throws TriangleException {
        Assertions.assertTrue(TriangleValidator.isTriangleValid(a, b, c));
    }

    @DataProvider
    private Object[][] equalSides() {
        return new Object[][] {
                {3, 3, 3},
                {13, 13, 13},
                {100, 100, 100},
                {12312, 12312, 12312},
                {85, 85, 85}
        };
    }
    @Test( dataProvider = "equalSides")
    public void equilateralTriangleTest(double a, double b, double c) throws TriangleException {
        Assertions.assertTrue(TriangleValidator.isTriangleValid(a, b, c));
    }

    @DataProvider
    private Object[][] isoscelesTriangleSides() {
        return new Object[][] {
                {3, 3, 4},
                {13, 12, 13},
                {15, 15, 29},
                {82, 82, 160},
                {100, 100, 199}
        };
    }
    @Test( dataProvider = "isoscelesTriangleSides")
    public void isoscelesTriangleTest(double a, double b, double c) throws TriangleException {
        Assertions.assertTrue(TriangleValidator.isTriangleValid(a, b, c));
    }

    @DataProvider
    private Object[][] maxSides() {
        return new Object[][] {
                {Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE},
                {Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE - 2.0},
                {Double.MAX_VALUE - 30.0, Double.MAX_VALUE - 34.0, Double.MAX_VALUE},
                {Double.MAX_VALUE - 100.0, Double.MAX_VALUE - 101.0, Double.MAX_VALUE - 102.0},
                {Double.MAX_VALUE - 1203.0, Double.MAX_VALUE - 1205.0, Double.MAX_VALUE - 1000.0}
        };
    }
    @Test( dataProvider = "maxSides")
    public void triangleWithMaxSidesTest(double a, double b, double c) throws TriangleException {
        Assertions.assertTrue(TriangleValidator.isTriangleValid(a, b, c));
    }

    @DataProvider
    private Object[][] minSides() {
        return new Object[][] {
                {Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE},
                {0.0000000002d, 0.0000000003d, 0.00000000015d},
                {Double.MIN_VALUE + 100.0, Double.MIN_VALUE + 101.0, Double.MIN_VALUE + 102.0},
                {Double.MIN_VALUE + 1203.0, Double.MIN_VALUE + 1205.0, Double.MIN_VALUE + 1000.0}
        };
    }
    @Test( dataProvider = "minSides")
    public void triangleWithMinSidesTest(double a, double b, double c) throws TriangleException {
        Assertions.assertTrue(TriangleValidator.isTriangleValid(a, b, c));
    }
}
