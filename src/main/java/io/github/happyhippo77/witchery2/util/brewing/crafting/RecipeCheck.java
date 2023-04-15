package io.github.happyhippo77.witchery2.util.brewing.crafting;

import net.minecraft.item.ItemStack;

public record RecipeCheck(boolean valid, int requiredPower, ItemStack output) {
}
