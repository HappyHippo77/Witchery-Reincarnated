package io.github.happyhippo77.witchery2.customrecipe;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomRecipeManager {
    private static HashMap<CustomRecipeType, List<CustomRecipe>> recipes = new HashMap<>();

    public static void registerRecipe(CustomRecipe recipe) {
        if (recipes.containsKey(recipe.getType())) {
            recipes.get(recipe.getType()).add(recipe);
        } else {
            recipes.put(recipe.getType(), new ArrayList<>(List.of(recipe)));
        }
    }

    public static CustomRecipe matchRecipe(CustomRecipeType type, DefaultedList<ItemStack> inventory) {
        for (CustomRecipe recipe : recipes.get(type)) {
            if (recipe.matches(inventory)) {
                return recipe;
            }
        }
        return null;
    }
}
