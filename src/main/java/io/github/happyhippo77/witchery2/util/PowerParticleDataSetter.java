package io.github.happyhippo77.witchery2.util;

import java.awt.*;

public class PowerParticleDataSetter {
    private Color color;
    private boolean circling;
    private boolean ritualInProgress;
    private int maxAge;
    public void setData(Color color, boolean circling, boolean ritualInProgress, int maxAge) {
        this.color = color;
        this.circling = circling;
        this.ritualInProgress = ritualInProgress;
        this.maxAge = maxAge;
    }

    public Color getColor() {
        return color;
    }

    public int getRed() {
        return color.getRed();
    }

    public int getGreen() {
        return color.getGreen();
    }

    public int getBlue() {
        return color.getBlue();
    }

    public int getAlpha() {
        return color.getAlpha();
    }

    public boolean isCircling() {
        return circling;
    }

    public boolean isRitualInProgress() {
        return ritualInProgress;
    }

    public int getMaxAge() {
        return maxAge;
    }
}
