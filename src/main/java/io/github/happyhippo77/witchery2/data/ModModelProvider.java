package io.github.happyhippo77.witchery2.data;

import io.github.happyhippo77.witchery2.block.ModBlocks;
import io.github.happyhippo77.witchery2.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TexturedModel;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.WITCHS_OVEN);

        blockStateModelGenerator.registerCrop(ModBlocks.BELLADONNA, ModBlocks.BELLADONNA.getAgeProperty(), 0, 1, 2, 3, 4);
        blockStateModelGenerator.registerCrop(ModBlocks.MANDRAKE, ModBlocks.MANDRAKE.getAgeProperty(), 0, 1, 2, 3, 4);
        blockStateModelGenerator.registerCrop(ModBlocks.WATER_ARTICHOKE, ModBlocks.WATER_ARTICHOKE.getAgeProperty(), 0, 1, 2, 3, 4);
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.SNOWBELL, BlockStateModelGenerator.TintType.NOT_TINTED, ModBlocks.SNOWBELL.getAgeProperty(), 0, 1, 2, 3, 4);
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.WOLFSBANE, BlockStateModelGenerator.TintType.NOT_TINTED, ModBlocks.WOLFSBANE.getAgeProperty(), 0, 1, 2, 3, 4, 5, 6, 7);
        blockStateModelGenerator.registerCrop(ModBlocks.GARLIC, ModBlocks.GARLIC.getAgeProperty(), 0, 1, 2, 3, 4, 5);

        blockStateModelGenerator.registerTintableCross(ModBlocks.EMBER_MOSS, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerTintableCross(ModBlocks.GLINT_WEED, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerWallPlant(ModBlocks.SPANISH_MOSS);

        blockStateModelGenerator.registerLog(ModBlocks.ROWAN_LOG).log(ModBlocks.ROWAN_LOG).wood(ModBlocks.ROWAN_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_ROWAN_LOG).log(ModBlocks.STRIPPED_ROWAN_LOG).wood(ModBlocks.STRIPPED_ROWAN_WOOD);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ROWAN_PLANKS);
        blockStateModelGenerator.registerSingleton(ModBlocks.ROWAN_LEAVES, TexturedModel.LEAVES);
        blockStateModelGenerator.registerTintableCross(ModBlocks.ROWAN_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.ANOINTING_PASTE, Models.GENERATED);

        itemModelGenerator.register(ModItems.BELLADONNA_FLOWER, Models.GENERATED);
        itemModelGenerator.register(ModItems.MANDRAKE_ROOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.WATER_ARTICHOKE_GLOBE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ICY_NEEDLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.WOLFSBANE, Models.GENERATED);

        itemModelGenerator.register(ModItems.EXHALE_OF_THE_HORNED_ONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUTANDIS, Models.GENERATED);
        itemModelGenerator.register(ModItems.CLAY_JAR, Models.GENERATED);
        itemModelGenerator.register(ModItems.SOFT_CLAY_JAR, Models.GENERATED);
        itemModelGenerator.register(ModItems.WOOD_ASH, Models.GENERATED);
        itemModelGenerator.register(ModItems.QUICKLIME, Models.GENERATED);
        itemModelGenerator.register(ModItems.EARMUFFS, Models.GENERATED);

        itemModelGenerator.register(ModItems.ROWAN_BERRIES, Models.GENERATED);
    }
}
