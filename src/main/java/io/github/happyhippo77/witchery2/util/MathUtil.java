package io.github.happyhippo77.witchery2.util;

public class MathUtil {
    public static int clamp(int i, int min, int max) {
        if (i > max) {
            i = max;
        }
        if (i < min) {
            i = min;
        }
        return i;
    }
}
