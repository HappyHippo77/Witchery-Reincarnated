package io.github.happyhippo77.witchery2.util.brewing.ingredients;

import io.github.happyhippo77.witchery2.util.brewing.Effect;
import net.minecraft.item.Item;

import java.util.ArrayList;

public class EffectIngredient extends AbstractIngredient {

    private final int slots;
    private final Effect effect;

    public EffectIngredient(Item givenItem, int givenRequiredPower, int slots, Effect effect) {
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

    public Effect getEffect() {
        return effect;
    }

    public boolean brewHasCapacity(ArrayList<Item> ingredients) {
        // This variable stores the capacity ingredient most towards the end of the correct order found so far.
        CapacityIngredient latestCapacityIngredient = null;
        int capacity = 0;

        for (Item item : ingredients) {
            if (IngredientRegistry.isIngredientType(item, IngredientUse.CAPACITY)) {
                CapacityIngredient capacityIngredient = (CapacityIngredient) IngredientRegistry.fromItem(item);
                if (latestCapacityIngredient == null) {
                    latestCapacityIngredient = capacityIngredient;
                    capacity += capacityIngredient.getSlots();
                }
                else if (capacityIngredient.getOrder() > latestCapacityIngredient.getOrder()) {
                    latestCapacityIngredient = capacityIngredient;
                    capacity += capacityIngredient.getSlots();
                }
            }
            else if (IngredientRegistry.isIngredientType(item, IngredientUse.EFFECT)) {
                EffectIngredient effectIngredient = (EffectIngredient) IngredientRegistry.fromItem(item);
                capacity -= effectIngredient.getSlots();
            }
        }

        return capacity >= this.getSlots();
    }
}
