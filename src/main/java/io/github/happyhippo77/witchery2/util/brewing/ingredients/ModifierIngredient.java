package io.github.happyhippo77.witchery2.util.brewing.ingredients;

import net.minecraft.item.Item;

public class ModifierIngredient extends AbstractIngredient {
    private final String modifier;
    public ModifierIngredient(Item givenItem, int givenRequiredPower, String modifier) {
        super(givenItem, givenRequiredPower);
        this.modifier = modifier;
    }

    @Override
    public IngredientUse getUse() {
        return IngredientUse.MODIFIER;
    }

    public String getModifier() {
        return modifier;
    }
}
