package io.github.happyhippo77.witchery2.data;

import io.github.happyhippo77.witchery2.item.ModItemTags;
import io.github.happyhippo77.witchery2.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;

import java.util.function.Consumer;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerPlanksRecipe(exporter, ModBlocks.ROWAN_PLANKS, ModItemTags.ROWAN_LOGS, 4);
        offerBarkBlockRecipe(exporter, ModBlocks.ROWAN_WOOD, ModBlocks.ROWAN_LOG);
        offerBarkBlockRecipe(exporter, ModBlocks.STRIPPED_ROWAN_WOOD, ModBlocks.STRIPPED_ROWAN_LOG);
    }
}
