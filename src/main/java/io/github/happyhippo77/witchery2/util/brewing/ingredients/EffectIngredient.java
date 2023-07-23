package io.github.happyhippo77.witchery2.util.brewing.ingredients;

import net.minecraft.item.Item;

public class EffectIngredient extends AbstractIngredient {

    private final int slots;
    private final String effect;

    public EffectIngredient(Item givenItem, int givenRequiredPower, int slots, String effect) {
        super(givenItem, givenRequiredPower);
        this.slots = slots;
        this.effect = effect;
    }

    @Override
    public IngredientUse getUse() {
        return IngredientUse.EFFECT;
    }

    public int getSlots() {
        return slots;
    }

    public String getEffect() {
        return effect;
    }
}
