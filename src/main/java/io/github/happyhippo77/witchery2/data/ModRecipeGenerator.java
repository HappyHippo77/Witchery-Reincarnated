package io.github.happyhippo77.witchery2.data;

import io.github.happyhippo77.witchery2.item.ModItemTags;
import io.github.happyhippo77.witchery2.block.ModBlocks;
import io.github.happyhippo77.witchery2.item.ModItems;
import io.github.happyhippo77.witchery2.util.ShapedPattern;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerCorrectedShapelessRecipe(exporter, RecipeCategory.MISC, ModItems.ANOINTING_PASTE, 1,
                ModItems.BELLADONNA_SEEDS,
                ModItems.MANDRAKE_SEEDS,
                ModItems.WATER_ARTICHOKE_SEEDS,
                ModItems.SNOWBELL_SEEDS);
        offerCorrectedSmeltingRecipe(exporter, RecipeCategory.MISC, ModItems.CLAY_JAR, 0, 200, ModItems.SOFT_CLAY_JAR);
        offerCorrectedShapelessRecipe(exporter, RecipeCategory.MISC, ModItems.QUICKLIME, 1, ModItems.WOOD_ASH);
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModItems.EARMUFFS, new ShapedPattern(
                Map.of(
                        Items.LEATHER, Arrays.asList(0, 1, 2, 3, 5),
                        Items.WHITE_WOOL, Arrays.asList(6, 8)
                )
        ));
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModItems.SOFT_CLAY_JAR, new ShapedPattern(
                Map.of(
                        Items.CLAY_BALL, Arrays.asList(1, 3, 4, 5)
                )
        ));
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.WITCHS_OVEN, new ShapedPattern(
                Map.of(
                        Items.IRON_INGOT, Arrays.asList(3, 4, 5, 6, 8),
                        Items.IRON_BARS, Arrays.asList(1, 7)
                )
        ));

        offerPlanksRecipe(exporter, ModBlocks.ROWAN_PLANKS, ModItemTags.ROWAN_LOGS, 4);
        offerBarkBlockRecipe(exporter, ModBlocks.ROWAN_WOOD, ModBlocks.ROWAN_LOG);
        offerBarkBlockRecipe(exporter, ModBlocks.STRIPPED_ROWAN_WOOD, ModBlocks.STRIPPED_ROWAN_LOG);
    }

    private void offerCorrectedSmeltingRecipe(Consumer<RecipeJsonProvider> exporter, RecipeCategory category, @NotNull ItemConvertible output, float experience, int cookingTime, @NotNull ItemConvertible... inputs) {
        CookingRecipeJsonBuilder builder = CookingRecipeJsonBuilder.create(Ingredient.ofItems(inputs), category, output, experience, cookingTime, RecipeSerializer.SMELTING);
        for (ItemConvertible item : inputs) {
            builder.criterion(RecipeProvider.hasItem(item), RecipeProvider.conditionsFromItem(item));
        }
        builder.offerTo(exporter);
    }

    private void offerCorrectedShapelessRecipe(Consumer<RecipeJsonProvider> exporter, RecipeCategory category, @NotNull ItemConvertible output, int count, @NotNull ItemConvertible... inputs) {
        ShapelessRecipeJsonBuilder builder = ShapelessRecipeJsonBuilder.create(category, output, count);
        for (ItemConvertible item : inputs) {
            builder.input(item);
            builder.criterion(RecipeProvider.hasItem(item), RecipeProvider.conditionsFromItem(item));
        }
        builder.offerTo(exporter);
    }

    private void offerCraftingRecipe(Consumer<RecipeJsonProvider> exporter, RecipeCategory category, @NotNull ItemConvertible output, ShapedPattern pattern) {
        ShapedRecipeJsonBuilder builder = ShapedRecipeJsonBuilder.create(category, output)
                .pattern(pattern.getRow1())
                .pattern(pattern.getRow2())
                .pattern(pattern.getRow3());
        for (Character label : pattern.getIngredients().keySet()) {
            builder.input(label, pattern.getIngredients().get(label));
            builder.criterion(RecipeProvider.hasItem(pattern.getIngredients().get(label)), RecipeProvider.conditionsFromItem(pattern.getIngredients().get(label)));
        }
        builder.offerTo(exporter);
    }
}
