package io.github.happyhippo77.witchery2.util.brewing.ingredients;

import io.github.happyhippo77.witchery2.util.brewing.BrewModifier;
import net.minecraft.item.Item;

public class BrewModifierIngredient extends AbstractIngredient {
    private final BrewModifier brewModifier;
    public BrewModifierIngredient(Item givenItem, int givenRequiredPower, BrewModifier brewModifier) {
        super(givenItem, givenRequiredPower);
        this.brewModifier = brewModifier;
    }

    @Override
    public IngredientUse getUse() {
        return IngredientUse.EFFECT_MODIFIER;
    }

    public BrewModifier getModifier() {
        return brewModifier;
    }
}
