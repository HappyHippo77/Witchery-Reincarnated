package io.github.happyhippo77.witchery2.util.brewing;

import net.minecraft.item.ItemStack;

public abstract class Modifier {
    private final String id;

    protected Modifier(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.id;
    }
}
