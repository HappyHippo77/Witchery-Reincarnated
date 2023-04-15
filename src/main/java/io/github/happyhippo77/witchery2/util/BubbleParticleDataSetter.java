package io.github.happyhippo77.witchery2.util;

import java.awt.*;

public class BubbleParticleDataSetter {
    private Color color;
    public void setData(Color color) {
        this.color = color;
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
}
