package utils;

public class NumberUtils {
    public static double truncate(double value, int decimalPlaces) {
        double scale = Math.pow(10, decimalPlaces);
        return Math.floor(value * scale) / scale;
    }
}
