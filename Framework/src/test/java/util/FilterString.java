package util;

import org.apache.logging.log4j.LoggingException;

public class FilterString {
    public static double filterDouble(String str) throws LoggingException {
        String validStr = str.substring(0,4).replace(",", ".");
            double n = Double.parseDouble(validStr);
            return n;
    }
}
