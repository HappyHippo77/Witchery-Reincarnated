package io.github.happyhippo77.witchery2.data;

import io.github.happyhippo77.witchery2.item.ModItemTags;
import io.github.happyhippo77.witchery2.block.ModBlocks;
import io.github.happyhippo77.witchery2.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
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
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModItems.EARMUFFS, 1,
                """
                       xxx
                       x x
                       # #
                       """,
                Map.of(
                        'x', Items.LEATHER,
                        '#', Items.WHITE_WOOL
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModItems.SOFT_CLAY_JAR, 4,
                """
                        o\s
                       ooo
                       """,
                Map.of(
                        'o', Items.CLAY_BALL
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.WITCHS_OVEN, 1,
                """
                        #\s
                       ---
                       -#-
                       """,
                Map.of(
                        '-', Items.IRON_INGOT,
                        '#', Items.IRON_BARS
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModItems.FUME_FILTER, 1,
                """
                       ###
                       -o-
                       ###
                       """,
                Map.of(
                        '#', Items.GLASS,
                        '-', Items.IRON_INGOT,
                        'o', ModItems.ATTUNED_STONE
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.FUME_FUNNEL, 1,
                """
                       blb
                       bgb
                       i#i
                       """,
                Map.of(
                        'b', Items.BUCKET,
                        'l', Items.LAVA_BUCKET,
                        'g', Items.GLOWSTONE,
                        'i', Items.IRON_BLOCK,
                        '#', Items.IRON_BARS
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.FILTERED_FUME_FUNNEL, 1,
                """
                       I
                       -
                       """,
                Map.of(
                        'I', ModBlocks.FUME_FUNNEL,
                        '-', ModItems.FUME_FILTER
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.ALTAR, 3,
                """
                       BWE
                       SRS
                       SRS
                       """,
                Map.of(
                        'B', ModItems.BREATH_OF_THE_GODDESS,
                        'W', Items.POTION,
                        'E', ModItems.EXHALE_OF_THE_HORNED_ONE,
                        'S', Blocks.STONE_BRICKS,
                        'R', ModBlocks.ROWAN_LOG
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.CANDELABRA, 1,
                """
                       |||
                       -o-
                        -\s
                       """,
                Map.of(
                        '|', Items.TORCH,
                        '-', Items.IRON_INGOT,
                        'o', ModItems.ATTUNED_STONE
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.CHALICE, 1,
                """
                       'o'
                       '-'
                        -\s
                       """,
                Map.of(
                        '\'', Items.GOLD_NUGGET,
                        '-', Items.GOLD_INGOT,
                        'o', ModItems.ATTUNED_STONE
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.FILLED_CHALICE, 1,
                """
                       o
                       u
                       """,
                Map.of(
                        'o', ModItems.REDSTONE_SOUP,
                        'u', ModBlocks.CHALICE
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.PENTACLE, 1,
                """
                       '-'
                       -o-
                       '-'
                       """,
                Map.of(
                        '\'', ModItems.KOBOLDITE_NUGGET,
                        '-', ModItems.KOBOLDITE_INGOT,
                        'o', Items.DIAMOND
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.ROWAN_STAIRS, 4,
                """
                         #
                        ##
                       ###
                       """,
                Map.of(
                        '#', ModBlocks.ROWAN_PLANKS
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.ROWAN_SLAB, 6,
                """
                       ###
                       """,
                Map.of(
                        '#', ModBlocks.ROWAN_PLANKS
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.ROWAN_FENCE, 3,
                """
                       #/#
                       #/#
                       """,
                Map.of(
                        '#', ModBlocks.ROWAN_PLANKS,
                        '/', Items.STICK
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.ROWAN_FENCE_GATE, 1,
                """
                       /#/
                       /#/
                       """,
                Map.of(
                        '#', ModBlocks.ROWAN_PLANKS,
                        '/', Items.STICK
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.ROWAN_DOOR, 3,
                """
                       ##
                       ##
                       ##
                       """,
                Map.of(
                        '#', ModBlocks.ROWAN_PLANKS
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.ROWAN_TRAPDOOR, 2,
                """
                       ###
                       ###
                       """,
                Map.of(
                        '#', ModBlocks.ROWAN_PLANKS
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.REDSTONE, ModBlocks.ROWAN_PRESSURE_PLATE, 1,
                """
                       ##
                       """,
                Map.of(
                        '#', ModBlocks.ROWAN_PLANKS
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.REDSTONE, ModBlocks.ROWAN_SIGN, 3,
                """
                       ###
                       ###
                        /\s
                       """,
                Map.of(
                        '#', ModBlocks.ROWAN_PLANKS,
                        '/', Items.STICK
                )
        );
        offerCorrectedShapelessRecipe(exporter, RecipeCategory.REDSTONE, ModBlocks.ROWAN_BUTTON, 1, ModBlocks.ROWAN_PLANKS);
        offerPlanksRecipe(exporter, ModBlocks.ROWAN_PLANKS, ModItemTags.ROWAN_LOGS, 4);
        offerBarkBlockRecipe(exporter, ModBlocks.ROWAN_WOOD, ModBlocks.ROWAN_LOG);
        offerBarkBlockRecipe(exporter, ModBlocks.STRIPPED_ROWAN_WOOD, ModBlocks.STRIPPED_ROWAN_LOG);

        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.ALDER_STAIRS, 4,
                """
                         #
                        ##
                       ###
                       """,
                Map.of(
                        '#', ModBlocks.ALDER_PLANKS
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.ALDER_SLAB, 6,
                """
                       ###
                       """,
                Map.of(
                        '#', ModBlocks.ALDER_PLANKS
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.ALDER_FENCE, 3,
                """
                       #/#
                       #/#
                       """,
                Map.of(
                        '#', ModBlocks.ALDER_PLANKS,
                        '/', Items.STICK
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.ALDER_FENCE_GATE, 1,
                """
                       /#/
                       /#/
                       """,
                Map.of(
                        '#', ModBlocks.ALDER_PLANKS,
                        '/', Items.STICK
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.ALDER_DOOR, 3,
                """
                       ##
                       ##
                       ##
                       """,
                Map.of(
                        '#', ModBlocks.ALDER_PLANKS
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.ALDER_TRAPDOOR, 2,
                """
                       ###
                       ###
                       """,
                Map.of(
                        '#', ModBlocks.ALDER_PLANKS
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.REDSTONE, ModBlocks.ALDER_PRESSURE_PLATE, 1,
                """
                       ##
                       """,
                Map.of(
                        '#', ModBlocks.ALDER_PLANKS
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.REDSTONE, ModBlocks.ALDER_SIGN, 3,
                """
                       ###
                       ###
                        /\s
                       """,
                Map.of(
                        '#', ModBlocks.ALDER_PLANKS,
                        '/', Items.STICK
                )
        );
        offerCorrectedShapelessRecipe(exporter, RecipeCategory.REDSTONE, ModBlocks.ALDER_BUTTON, 1, ModBlocks.ALDER_PLANKS);
        offerPlanksRecipe(exporter, ModBlocks.ALDER_PLANKS, ModItemTags.ALDER_LOGS, 4);
        offerBarkBlockRecipe(exporter, ModBlocks.ALDER_WOOD, ModBlocks.ALDER_LOG);
        offerBarkBlockRecipe(exporter, ModBlocks.STRIPPED_ALDER_WOOD, ModBlocks.STRIPPED_ALDER_LOG);

        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.HAWTHORN_STAIRS, 4,
                """
                         #
                        ##
                       ###
                       """,
                Map.of(
                        '#', ModBlocks.HAWTHORN_PLANKS
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.HAWTHORN_SLAB, 6,
                """
                       ###
                       """,
                Map.of(
                        '#', ModBlocks.HAWTHORN_PLANKS
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.HAWTHORN_FENCE, 3,
                """
                       #/#
                       #/#
                       """,
                Map.of(
                        '#', ModBlocks.HAWTHORN_PLANKS,
                        '/', Items.STICK
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.HAWTHORN_FENCE_GATE, 1,
                """
                       /#/
                       /#/
                       """,
                Map.of(
                        '#', ModBlocks.HAWTHORN_PLANKS,
                        '/', Items.STICK
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.HAWTHORN_DOOR, 3,
                """
                       ##
                       ##
                       ##
                       """,
                Map.of(
                        '#', ModBlocks.HAWTHORN_PLANKS
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModBlocks.HAWTHORN_TRAPDOOR, 2,
                """
                       ###
                       ###
                       """,
                Map.of(
                        '#', ModBlocks.HAWTHORN_PLANKS
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.REDSTONE, ModBlocks.HAWTHORN_PRESSURE_PLATE, 1,
                """
                       ##
                       """,
                Map.of(
                        '#', ModBlocks.HAWTHORN_PLANKS
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.REDSTONE, ModBlocks.HAWTHORN_SIGN, 3,
                """
                       ###
                       ###
                        /\s
                       """,
                Map.of(
                        '#', ModBlocks.HAWTHORN_PLANKS,
                        '/', Items.STICK
                )
        );

        offerCorrectedShapelessRecipe(exporter, RecipeCategory.REDSTONE, ModBlocks.HAWTHORN_BUTTON, 1, ModBlocks.HAWTHORN_PLANKS);


        offerPlanksRecipe(exporter, ModBlocks.HAWTHORN_PLANKS, ModItemTags.HAWTHORN_LOGS, 4);
        offerBarkBlockRecipe(exporter, ModBlocks.HAWTHORN_WOOD, ModBlocks.HAWTHORN_LOG);
        offerBarkBlockRecipe(exporter, ModBlocks.STRIPPED_HAWTHORN_WOOD, ModBlocks.STRIPPED_HAWTHORN_LOG);

        offerCraftingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.OAK_STOCKADE, 9,
                """
                        #\s
                       #+#
                       ###
                       """,
                Map.of(
                        '#', Blocks.OAK_LOG,
                        '+', ModItems.EXHALE_OF_THE_HORNED_ONE
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SPRUCE_STOCKADE, 9,
                """
                        #\s
                       #+#
                       ###
                       """,
                Map.of(
                        '#', Blocks.SPRUCE_LOG,
                        '+', ModItems.EXHALE_OF_THE_HORNED_ONE
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BIRCH_STOCKADE, 9,
                """
                        #\s
                       #+#
                       ###
                       """,
                Map.of(
                        '#', Blocks.BIRCH_LOG,
                        '+', ModItems.EXHALE_OF_THE_HORNED_ONE
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.JUNGLE_STOCKADE, 9,
                """
                        #\s
                       #+#
                       ###
                       """,
                Map.of(
                        '#', Blocks.JUNGLE_LOG,
                        '+', ModItems.EXHALE_OF_THE_HORNED_ONE
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ACACIA_STOCKADE, 9,
                """
                        #\s
                       #+#
                       ###
                       """,
                Map.of(
                        '#', Blocks.ACACIA_LOG,
                        '+', ModItems.EXHALE_OF_THE_HORNED_ONE
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DARK_OAK_STOCKADE, 9,
                """
                        #\s
                       #+#
                       ###
                       """,
                Map.of(
                        '#', Blocks.DARK_OAK_LOG,
                        '+', ModItems.EXHALE_OF_THE_HORNED_ONE
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MANGROVE_STOCKADE, 9,
                """
                        #\s
                       #+#
                       ###
                       """,
                Map.of(
                        '#', Blocks.MANGROVE_LOG,
                        '+', ModItems.EXHALE_OF_THE_HORNED_ONE
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROWAN_STOCKADE, 9,
                """
                        #\s
                       #+#
                       ###
                       """,
                Map.of(
                        '#', ModBlocks.ROWAN_LOG,
                        '+', ModItems.EXHALE_OF_THE_HORNED_ONE
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ALDER_STOCKADE, 9,
                """
                        #\s
                       #+#
                       ###
                       """,
                Map.of(
                        '#', ModBlocks.ALDER_LOG,
                        '+', ModItems.EXHALE_OF_THE_HORNED_ONE
                )
        );
        offerCraftingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.HAWTHORN_STOCKADE, 9,
                """
                        #\s
                       #+#
                       ###
                       """,
                Map.of(
                        '#', ModBlocks.HAWTHORN_LOG,
                        '+', ModItems.EXHALE_OF_THE_HORNED_ONE
                )
        );

        offerCraftingRecipe(exporter, RecipeCategory.MISC, ModItems.ATTUNED_STONE, 1,
                """
                       +
                       o
                       u
                       """,
                Map.of(
                        '+', ModItems.WHIFF_OF_MAGIC,
                        'o', Items.DIAMOND,
                        'u', Items.LAVA_BUCKET
                )
        );
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

    private void offerCraftingRecipe(Consumer<RecipeJsonProvider> exporter, RecipeCategory category, @NotNull ItemConvertible output, int count, String pattern, Map<Character, ItemConvertible> ingredients) {
        ShapedRecipeJsonBuilder builder = ShapedRecipeJsonBuilder.create(category, output, count);
        for (String row : pattern.split("\n")) {
            builder.pattern(row);
        }

        for (Character label : ingredients.keySet()) {
            builder.input(label, ingredients.get(label));
            builder.criterion(RecipeProvider.hasItem(ingredients.get(label)), RecipeProvider.conditionsFromItem(ingredients.get(label)));
        }
        builder.offerTo(exporter);
    }
}
