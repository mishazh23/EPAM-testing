package operation;

import exception.TriangleException;

public class TriangleValidator {
    public static boolean isTriangleValid(double a, double b, double c) throws TriangleException {
        if ((a > 0) && (b > 0) && (c > 0)){
            return ((a + b > c) && (a + c > b) && (b + c > a));
        }else if (Double.isNaN(a) || Double.isNaN(b) || Double.isNaN(c)) {
            throw new TriangleException("Cannot read values that are not defined");
        }
        else{
            throw new TriangleException("Values equal or less then 0 are not allowed!");
        }
    }
}
