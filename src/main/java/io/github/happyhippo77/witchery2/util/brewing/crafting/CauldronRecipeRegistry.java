package io.github.happyhippo77.witchery2.util.brewing.crafting;

import io.github.happyhippo77.witchery2.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CauldronRecipeRegistry {
    private static final List<CauldronRecipe> recipes = Arrays.asList(
            new CauldronRecipe(
                    Collections.singletonList(Items.PORKCHOP),
                    new ItemStack(Items.COOKED_PORKCHOP, 1)
            ),
            new CauldronRecipe(
                    Collections.singletonList(Items.CHICKEN),
                    new ItemStack(Items.COOKED_CHICKEN, 1)
            ),
            new CauldronRecipe(
                    Collections.singletonList(Items.BEEF),
                    new ItemStack(Items.COOKED_BEEF, 1)
            ),
            new CauldronRecipe(
                    Collections.singletonList(Items.MUTTON),
                    new ItemStack(Items.COOKED_MUTTON, 1)
            ),
            new CauldronRecipe(
                    Collections.singletonList(Items.RABBIT),
                    new ItemStack(Items.COOKED_RABBIT, 1)
            ),
            new CauldronRecipe(
            Arrays.asList(ModItems.MANDRAKE_ROOT, ModItems.EXHALE_OF_THE_HORNED_ONE, Items.EGG),
                    new ItemStack(ModItems.MUTANDIS, 6)
            )
    );

    public static RecipeCheck checkRecipe(List<Item> givenRecipe) {
        boolean match = false;
        int requiredPower = 0;
        ItemStack output = null;
        for (CauldronRecipe recipe : recipes) {
            if (recipe.matches(givenRecipe)) {
                match = true;
                requiredPower = recipe.getPowerNeeded();
                output = recipe.getOutput();
            }
        }
        return new RecipeCheck(match, requiredPower, output);
    }

    public static boolean checkPrecursor(List<Item> input) {
        for (CauldronRecipe recipe : recipes) {
            if (recipe.isPrecursor(input)) {
                return true;
            }
        }
        return false;
    }
}
