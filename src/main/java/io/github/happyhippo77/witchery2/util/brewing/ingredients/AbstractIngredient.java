package io.github.happyhippo77.witchery2.util.brewing.ingredients;

import net.minecraft.item.Item;

public abstract class AbstractIngredient {
    private final Item item;
    private final int requiredPower;
    public AbstractIngredient(Item givenItem, int givenRequiredPower) {
        item = givenItem;
        requiredPower = givenRequiredPower;
    }
    public abstract IngredientUse getUse();

    public Item getItem() {
        return item;
    }

    public int getRequiredPower() {
        return requiredPower;
    }

    public int getRequiredRitualPower() {
        return (int) (requiredPower * 1.4);
    }
}
