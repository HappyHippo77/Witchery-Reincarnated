package io.github.happyhippo77.witchery2.util.brewing.crafting;

import io.github.happyhippo77.witchery2.util.brewing.ingredients.AbstractIngredient;
import io.github.happyhippo77.witchery2.util.brewing.ingredients.IngredientRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class CauldronRecipe {
    private final List<Item> input;
    private final ItemStack output;
    private final int powerNeeded;

    public CauldronRecipe(List<Item> input, ItemStack output) {
        this.input = input;
        this.output = output;

        int i = 0;
        for (Item item : input) {
            AbstractIngredient ingredient = IngredientRegistry.fromItem(item);
            i += ingredient.getRequiredPower();
        }

        this.powerNeeded = i;
    }

    public boolean matches(List<Item> input) {
        return input.equals(this.input);
    }

    public boolean isPrecursor(List<Item> givenInput) {
        if (givenInput.size() < this.input.size()) {
            for (Item item : givenInput) {
                if (!this.input.get(givenInput.indexOf(item)).equals(item)) {
                    return false;
                }
            }
            return true;
        } else if (givenInput.size() == this.input.size()) {
            return matches(givenInput);
        } else {
            return false;
        }
    }

    public ItemStack getOutput() {
        return output;
    }

    public int getPowerNeeded() {
        return powerNeeded;
    }
}
