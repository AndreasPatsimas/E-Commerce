package org.patsimas.e_commerce.utils;

public class NumericUtils {

    public static double roundNumberToCentimeters(double x) {

        double parsedNumber = Math.round(x * 100) / 100.0;

        if (parsedNumber == -0.0)
            return 0.0;

        return parsedNumber;
    }
}
