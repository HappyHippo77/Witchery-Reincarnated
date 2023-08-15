package io.github.happyhippo77.witchery2.util.brewing.ingredients;

import net.minecraft.item.Item;

public class DurationIngredient extends AbstractIngredient {
    private final int ceiling;

    public DurationIngredient(Item item, int requiredPower, int ceiling) {
        super(item, requiredPower);
        this.ceiling = ceiling;
    }
    @Override
    public IngredientUse getUse() {
        return IngredientUse.DURATION;
    }

    public int getCeiling() {
        return ceiling;
    }
}
