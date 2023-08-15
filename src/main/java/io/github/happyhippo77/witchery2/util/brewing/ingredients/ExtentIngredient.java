package io.github.happyhippo77.witchery2.util.brewing.ingredients;

import net.minecraft.item.Item;

public class ExtentIngredient extends AbstractIngredient {
    private final int ceiling;

    public ExtentIngredient(Item item, int requiredPower, int ceiling) {
        super(item, requiredPower);
        this.ceiling = ceiling;
    }
    @Override
    public IngredientUse getUse() {
        return IngredientUse.EXTENT;
    }

    public int getCeiling() {
        return ceiling;
    }
}
