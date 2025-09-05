package io.github.happyhippo77.witchery2.recipe;

import io.github.happyhippo77.witchery2.Witchery2;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Witchery2.MOD_ID, WitchsOvenRecipe.Serializer.ID),
                WitchsOvenRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(Witchery2.MOD_ID, WitchsOvenRecipe.Type.ID),
                WitchsOvenRecipe.Type.INSTANCE);
    }
}