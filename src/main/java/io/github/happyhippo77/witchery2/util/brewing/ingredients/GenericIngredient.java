package io.github.happyhippo77.witchery2.util.brewing.ingredients;

import net.minecraft.item.Item;

public class GenericIngredient extends AbstractIngredient {
    public GenericIngredient(Item givenItem, int givenRequiredPower) {
        super(givenItem, givenRequiredPower);
    }

    @Override
    public IngredientUse getUse() {
        return IngredientUse.GENERIC;
    }
}
