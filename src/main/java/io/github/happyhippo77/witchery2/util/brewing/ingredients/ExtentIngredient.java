package io.github.happyhippo77.witchery2.util.brewing.ingredients;

import net.minecraft.item.Item;

public class ExtentIngredient extends AbstractIngredient {
    private final int order;

    public ExtentIngredient(Item item, int requiredPower, int order) {
        super(item, requiredPower);
        this.order = order;
    }
    @Override
    public IngredientUse getUse() {
        return IngredientUse.EXTENT;
    }

    public int getOrder() {
        return order;
    }
}
