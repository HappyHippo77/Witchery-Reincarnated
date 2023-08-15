package io.github.happyhippo77.witchery2.util;

import net.minecraft.block.Block;

public class PowerSource {
    private final int factor;
    public final int limit;
    public int count;
    public PowerSource(int factor, int limit) {
        this.factor = factor;
        this.limit = limit;
        this.count = 0;
    }

    public int getPower() {
        return Math.min(this.count, this.limit) * this.factor;
    }
}
