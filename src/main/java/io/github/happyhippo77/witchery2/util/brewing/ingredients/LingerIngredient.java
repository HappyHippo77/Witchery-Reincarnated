package io.github.happyhippo77.witchery2.util.brewing.ingredients;

import net.minecraft.item.Item;

public class LingerIngredient extends AbstractIngredient {
    private final int ceiling;

    public LingerIngredient(Item item, int requiredPower, int ceiling) {
        super(item, requiredPower);
        this.ceiling = ceiling;
    }
    @Override
    public IngredientUse getUse() {
        return IngredientUse.LINGER;
    }

    public int getCeiling() {
        return ceiling;
    }
}
