package io.github.happyhippo77.witchery2.customrecipe;

import io.github.happyhippo77.witchery2.customrecipe.recipes.DistilleryRecipe;
import io.github.happyhippo77.witchery2.item.ModItems;
import io.github.happyhippo77.witchery2.util.Pair;
import io.github.happyhippo77.witchery2.util.Quadruple;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ModCustomRecipes {
    public static void registerRecipes() {
        CustomRecipeManager.registerRecipe(new DistilleryRecipe(
                new Pair<>(
                        new ItemStack(ModItems.FOUL_FUME),
                        new ItemStack(ModItems.QUICKLIME)
                ),
                new Quadruple<>(
                        new ItemStack(ModItems.GYPSUM),
                        new ItemStack(ModItems.OIL_OF_VITRIOL),
                        new ItemStack(Items.SLIME_BALL),
                        ItemStack.EMPTY
                ),
                1
        ));
        CustomRecipeManager.registerRecipe(new DistilleryRecipe(
                new Pair<>(
                        new ItemStack(ModItems.BREATH_OF_THE_GODDESS),
                        new ItemStack(Items.LAPIS_LAZULI)
                ),
                new Quadruple<>(
                        new ItemStack(ModItems.TEAR_OF_THE_GODDESS),
                        new ItemStack(ModItems.WHIFF_OF_MAGIC),
                        new ItemStack(Items.SLIME_BALL),
                        new ItemStack(ModItems.FOUL_FUME)
                ),
                3
        ));
        CustomRecipeManager.registerRecipe(new DistilleryRecipe(
                new Pair<>(
                        new ItemStack(Items.DIAMOND),
                        new ItemStack(ModItems.OIL_OF_VITRIOL)
                ),
                new Quadruple<>(
                        new ItemStack(ModItems.DIAMOND_VAPOR),
                        new ItemStack(ModItems.DIAMOND_VAPOR),
                        new ItemStack(ModItems.ODOUR_OF_PURITY),
                        ItemStack.EMPTY
                ),
                3
        ));
        CustomRecipeManager.registerRecipe(new DistilleryRecipe(
                new Pair<>(
                        new ItemStack(ModItems.DIAMOND_VAPOR),
                        new ItemStack(Items.GHAST_TEAR)
                ),
                new Quadruple<>(
                        new ItemStack(ModItems.ODOUR_OF_PURITY),
                        new ItemStack(ModItems.REEK_OF_MISFORTUNE),
                        new ItemStack(ModItems.FOUL_FUME),
                        new ItemStack(ModItems.REFINED_EVIL)
                ),
                3
        ));
        CustomRecipeManager.registerRecipe(new DistilleryRecipe(
                new Pair<>(
                        new ItemStack(Items.ENDER_PEARL),
                        ItemStack.EMPTY
                ),
                new Quadruple<>(
                        new ItemStack(ModItems.ENDER_DEW, 2),
                        new ItemStack(ModItems.ENDER_DEW, 2),
                        new ItemStack(ModItems.ENDER_DEW),
                        new ItemStack(ModItems.WHIFF_OF_MAGIC)
                ),
                6
        ));
        CustomRecipeManager.registerRecipe(new DistilleryRecipe(
                new Pair<>(
                        new ItemStack(Items.BLAZE_POWDER),
                        new ItemStack(Items.GUNPOWDER)
                ),
                new Quadruple<>(
                        new ItemStack(Items.GLOWSTONE_DUST),
                        new ItemStack(Items.GLOWSTONE_DUST),
                        new ItemStack(ModItems.REEK_OF_MISFORTUNE),
                        ItemStack.EMPTY
                ),
                1
        ));
        CustomRecipeManager.registerRecipe(new DistilleryRecipe(
                new Pair<>(
                        new ItemStack(ModItems.DEMON_HEART),
                        new ItemStack(ModItems.DIAMOND_VAPOR)
                ),
                new Quadruple<>(
                        new ItemStack(ModItems.DEMONIC_BLOOD, 2),
                        new ItemStack(ModItems.DEMONIC_BLOOD, 2),
                        new ItemStack(ModItems.REFINED_EVIL),
                        ItemStack.EMPTY
                ),
                4
        ));
        CustomRecipeManager.registerRecipe(new DistilleryRecipe(
                new Pair<>(
                        new ItemStack(ModItems.DEMON_HEART),
                        new ItemStack(Items.NETHERRACK)
                ),
                new Quadruple<>(
                        new ItemStack(Items.SOUL_SAND),
                        new ItemStack(ModItems.DEMONIC_BLOOD),
                        new ItemStack(ModItems.DEMONIC_BLOOD),
                        ItemStack.EMPTY
                ),
                2
        ));
    }
}
