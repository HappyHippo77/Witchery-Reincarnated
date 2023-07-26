package io.github.happyhippo77.witchery2.util.brewing.ingredients;

import io.github.happyhippo77.witchery2.util.brewing.EffectModifier;
import net.minecraft.item.Item;

public class EffectModifierIngredient extends AbstractIngredient {
    private final EffectModifier effectModifier;
    public EffectModifierIngredient(Item item, int requiredPower, EffectModifier effectModifier) {
        super(item, requiredPower);
        this.effectModifier = effectModifier;
    }

    @Override
    public IngredientUse getUse() {
        return IngredientUse.EFFECT_MODIFIER;
    }

    public EffectModifier getModifier() {
        return effectModifier;
    }
}
