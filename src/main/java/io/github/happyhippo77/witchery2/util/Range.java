package io.github.happyhippo77.witchery2.util;

public class Range {
    private final int min;
    private final int max;

    public Range(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public boolean contains(int i) {
        return (i >= this.min) && (i <= this.max);
    }

    public String toString() {
        return this.min + "-" + this.max;
    }
}
