package io.github.happyhippo77.witchery2.util.brewing.ingredients;

import net.minecraft.item.Item;

public class CapacityIngredient extends AbstractIngredient {

    private final int slots;
    private final int order;

    public CapacityIngredient(Item item, int requiredPower, int slots, int order) {
        super(item, requiredPower);
        this.slots = slots;
        this.order = order;
    }
    @Override
    public IngredientUse getUse() {
        return IngredientUse.CAPACITY;
    }

    public int getSlots() {
        return slots;
    }

    public int getOrder() {
        return order;
    }
}
