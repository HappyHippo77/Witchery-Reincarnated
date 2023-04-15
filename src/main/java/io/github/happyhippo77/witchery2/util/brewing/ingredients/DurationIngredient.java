package io.github.happyhippo77.witchery2.util.brewing.ingredients;

import net.minecraft.item.Item;

public class DurationIngredient extends AbstractIngredient {
    private final int order;

    public DurationIngredient(Item item, int requiredPower, int order) {
        super(item, requiredPower);
        this.order = order;
    }
    @Override
    public IngredientUse getUse() {
        return IngredientUse.DURATION;
    }

    public int getOrder() {
        return order;
    }
}
