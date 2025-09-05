package io.github.happyhippo77.witchery2.customrecipe;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public abstract class CustomRecipe {
    private final CustomRecipeType TYPE;

    public CustomRecipe(CustomRecipeType type) {
        this.TYPE = type;
    }

    public CustomRecipeType getType() {
        return this.TYPE;
    }

    public abstract boolean matches(DefaultedList<ItemStack> inventory);

    public abstract boolean canCraft(DefaultedList<ItemStack> inventory);
}
